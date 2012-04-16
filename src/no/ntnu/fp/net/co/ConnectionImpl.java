/*
 * Created on Oct 27, 2004
 */
package no.ntnu.fp.net.co;

import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import no.ntnu.fp.net.admin.Log;
import no.ntnu.fp.net.cl.ClException;
import no.ntnu.fp.net.cl.ClSocket;
import no.ntnu.fp.net.cl.KtnDatagram;
import no.ntnu.fp.net.cl.KtnDatagram.Flag;

/**
 * Implementation of the Connection-interface. <br>
 * <br>
 * This class implements the behaviour in the methods specified in the interface
 * {@link Connection} over the unreliable, connectionless network realised in
 * {@link ClSocket}. The base class, {@link AbstractConnection} implements some
 * of the functionality, leaving message passing and error handling to this
 * implementation.
 * 
 * @author Sebj�rn Birkeland and Stein Jakob Nordb�
 * @see no.ntnu.fp.net.co.Connection
 * @see no.ntnu.fp.net.cl.ClSocket
 */
public class ConnectionImpl extends AbstractConnection {

	/** Keeps track of the used ports for each server port. */
	private static Map<Integer, Boolean> usedPorts = Collections
			.synchronizedMap(new HashMap<Integer, Boolean>());

	/**
	 * Initialise initial sequence number and setup state machine.
	 * 
	 * @param myPort
	 *            - the local port to associate with this connection
	 */
	public ConnectionImpl(int myPort) {
		this.myPort = myPort;
		this.myAddress = getIPv4Address();
		this.nextSequenceNo = 0;
		usedPorts.put(myPort, true);
	}
	
	@Override
	public void finalize(){
		usedPorts.remove(myPort);
	}

	private String getIPv4Address() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "127.0.0.1";
		}
	}

	/**
	 * Establish a connection to a remote location.
	 * 
	 * @param remoteAddress
	 *            - the remote IP-address to connect to
	 * @param remotePort
	 *            - the remote portnumber to connect to
	 * @throws IOException
	 *             If there's an I/O error.
	 * @throws java.net.SocketTimeoutException
	 *             If timeout expires before connection is completed.
	 * @see Connection#connect(InetAddress, int)
	 */
	public void connect(InetAddress remoteAddress, int remotePort)
			throws IOException, SocketTimeoutException {
		if (state != State.CLOSED) {
			throw new ConnectException("Socket must be closed");
		}
		this.remoteAddress = remoteAddress.getHostAddress();
		this.remotePort = remotePort;

		try {
			int tries = 3;
			boolean sent = false;
			KtnDatagram SYN = constructInternalPacket(Flag.SYN);

			do {
				try {
					new ClSocket().send(SYN);
					sent = true;
				} catch (ConnectException e) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e2) {

					}
				}
				tries--;
			} while (!sent && tries >= 0);

			if (!sent) {
				nextSequenceNo--;
				throw new ConnectException("Could not send SYN");
			}
			state = State.SYN_SENT;
			KtnDatagram SYNACK = null;
			do {
				SYNACK = receiveAck();
			} while (SYNACK == null);

			this.remoteAddress = SYNACK.getSrc_addr();
			this.remotePort = SYNACK.getSrc_port();
			sendAck(SYNACK, false);

		} catch (Exception e) {
			throw new ConnectException("Could not connect");
		}
		state = State.ESTABLISHED;
	}

	/**
	 * Listen for, and accept, incoming connections.
	 * 
	 * @return A new ConnectionImpl-object representing the new connection.
	 * @see Connection#accept()
	 */
	public Connection accept() throws IOException, SocketTimeoutException {
		state = State.LISTEN;
		KtnDatagram SYN = null;

		while (!isValid(SYN) || SYN.getFlag() != Flag.SYN) {
			SYN = receivePacket(true);
		}
		ConnectionImpl connection = new ConnectionImpl(findUnusedPort());
		connection.lastValidPacketReceived = SYN;
		connection.remoteAddress = SYN.getSrc_addr();
		connection.remotePort = SYN.getSrc_port();
		connection.state = State.SYN_RCVD;

		connection.sendAck(SYN, true);
		while (connection.receiveAck() == null)
			;
		connection.state = State.ESTABLISHED;
		return connection;
	}

	private int findUnusedPort() {
		int port;
		do {
			port = 10000 + (int) (Math.random() * 10000);
		} while (usedPorts.containsKey(port));
		return port;
	}

	/**
	 * Send a message from the application.
	 * 
	 * @param msg
	 *            - the String to be sent.
	 * @throws ConnectException
	 *             If no connection exists.
	 * @throws IOException
	 *             If no ACK was received.
	 * @see AbstractConnection#sendDataPacketWithRetransmit(KtnDatagram)
	 * @see no.ntnu.fp.net.co.Connection#send(String)
	 */
	public void send(String msg) throws ConnectException, IOException {
		if (state != State.ESTABLISHED) {
			throw new ConnectException("Connection is not established");
		}
		boolean sent = false;
		while (!sent) {
			try {
				sendDataPacketWithRetransmit(constructDataPacket(msg));
				sent = true;
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Wait for incoming data.
	 * 
	 * @return The received data's payload as a String.
	 * @see Connection#receive()
	 * @see AbstractConnection#receivePacket(boolean)
	 * @see AbstractConnection#sendAck(KtnDatagram, boolean)
	 */
	public String receive() throws ConnectException, IOException {
		if (state != State.ESTABLISHED) {
			throw new ConnectException("connection is not established");
		}
		boolean received = false;
		KtnDatagram dataPacket = null;

		while (!received || !isValid(dataPacket)) {
			try {
				dataPacket = receivePacket(false);
				lastValidPacketReceived = dataPacket;
				received = true;
				sendAck(dataPacket, false);
			} catch (Exception e) {

			}
		}
		return (String) lastValidPacketReceived.getPayload();
	}

	/**
	 * Close the connection.
	 * 
	 * @see Connection#close()
	 */
	public void close() throws IOException {
		if (state != State.ESTABLISHED) {
			throw new ConnectException("connection is not established");
		}
		KtnDatagram finPacket = constructInternalPacket(Flag.FIN);
		sendDataPacketWithRetransmit(finPacket);
		state = State.FIN_WAIT_1;

		lastValidPacketReceived = receiveAck();
		if (lastValidPacketReceived == null) {
			throw new ConnectException(
					"Unexpected response received while disconnecting");
		}
		state = State.FIN_WAIT_2;

		lastValidPacketReceived = receivePacket(true);
		if (lastValidPacketReceived.getFlag() != Flag.FIN) {
			throw new ConnectException(
					"Unexpected response received while disconnecting") {

			};
		} else {
			lastValidPacketReceived = receiveAck();
			if (lastValidPacketReceived == null) {
				throw new ConnectException(
						"Unexpected response received while disconnecting") {

				};
			}
		}
		state = State.TIME_WAIT;
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
		}
		state = State.CLOSED;
	}

	/**
	 * Test a packet for transmission errors. This function should only called
	 * with data or ACK packets in the ESTABLISHED state.
	 * 
	 * @param packet
	 *            Packet to test.
	 * @return true if packet is free of errors, false otherwise.
	 */
	protected boolean isValid(KtnDatagram packet) {
		if (packet == null) {
			return false;
		}
		return packet.calculateChecksum() == packet.getChecksum();
	}
}
