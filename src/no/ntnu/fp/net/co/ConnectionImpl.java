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
			
	boolean isSending = false;

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
					
					// TODO: håndter ClException?
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
			
			//TODO: check validity of synack packet

			this.remoteAddress = SYNACK.getSrc_addr();
			this.remotePort = SYNACK.getSrc_port();
			
			lastValidPacketReceived = SYNACK;
			
			sendAck(SYNACK, false);
			
			//TODO: review 1000ms wait in lf
			
			state = State.ESTABLISHED;

		} catch (Exception e) {
			throw new ConnectException("Could not connect");
		}
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

		while (SYN == null || SYN.getFlag() != Flag.SYN) {
			SYN = receivePacket(true);
		}
		ConnectionImpl connection = new ConnectionImpl(findUnusedPort());
		connection.lastValidPacketReceived = SYN;
		connection.remoteAddress = SYN.getSrc_addr();
		connection.remotePort = SYN.getSrc_port();
		connection.state = State.SYN_RCVD;
		
		//TODO: review 1000ms wait
		connection.sendAck(SYN, true);
		
		KtnDatagram ACK = connection.receiveAck();
		if (!isValid(ACK)) throw new IOException("Invalid ACK");
		connection.lastValidPacketReceived = ACK;
		connection.state = State.ESTABLISHED;
		return connection;
	}

	private int findUnusedPort() {
		int port = 10000;
		while (usedPorts.containsKey(port)) {
			port++;
		}
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
		
		while (isSending) {
			try { Thread.sleep(10); } catch (InterruptedException e) {}
		}
		isSending = true;
		
		boolean sent = false;
		int tries = 3;
		while (!sent) {
			try {
				KtnDatagram ACK = null;
				KtnDatagram packet = constructDataPacket(msg);
				while ((ACK == null || !isValid(ACK) || ACK.getFlag() != Flag.ACK || ACK.getAck() < packet.getSeq_nr()) && tries > 0) {
					tries--;
					ACK = sendDataPacketWithRetransmit(packet);
				}
				
				if (ACK == null) {
					return;
				}
				
				if (ACK.getSeq_nr() > lastValidPacketReceived.getSeq_nr()) lastValidPacketReceived = ACK;
				
				sent = true;
			} catch (Exception e) {

			}
		}
		
		isSending = false;
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
		
		int tries = 3;
		KtnDatagram dataPacket = null;

		while (tries > 0) {
			tries--;
			try { dataPacket = receivePacket(false); }
			catch (EOFException e) {
				if (disconnectRequest != null) {
					sendAck(disconnectRequest, false);
					state = State.CLOSE_WAIT;
					throw e;
				}
				else {
					sendAck(lastValidPacketReceived, false);
					continue;
				}
			}
			if (dataPacket.getFlag() == Flag.NONE
				&& isValid(dataPacket)
				&& lastValidPacketReceived.getSeq_nr() 
					< dataPacket.getSeq_nr()
				&& lastValidPacketReceived.getSeq_nr()+2 
					>= dataPacket.getSeq_nr()) {
				
				lastValidPacketReceived = dataPacket;
				sendAck(dataPacket, false);
				return dataPacket.toString();	
			}
			sendAck(lastValidPacketReceived, false);
			
		}
		if (dataPacket != null) lastValidPacketReceived = dataPacket;

		return receive();	
	}

	/**
	 * Close the connection.
	 * 
	 * @see Connection#close()
	 */
	public void close() throws IOException {
		if (state != State.ESTABLISHED && state != State.CLOSE_WAIT) {
			throw new ConnectException("connection is not established (State="+state+")");
		}
		KtnDatagram finPacket = constructInternalPacket(Flag.FIN);
		KtnDatagram ACK = null;
		
		State initstate = state;
		
		while (ACK == null || !isValid(ACK) || ACK.getFlag() != Flag.ACK) {
			state = initstate;
		
			try { simplySendPacket(finPacket); }
			catch (Exception e) {}
			
			if (disconnectRequest != null) {
				state = State.LAST_ACK;
			} else {
				state = State.FIN_WAIT_1;
			}
			
			ACK = receiveAck();
		}
		
		if (ACK.getSeq_nr() > lastValidPacketReceived.getSeq_nr()) {
			lastValidPacketReceived = ACK;
		}

		if (disconnectRequest == null) {
			state = State.FIN_WAIT_2;
			finPacket = null;
			while (!isValid(finPacket)) {
				finPacket = receivePacket(true);
			}
			sendAck(finPacket, false);

			state = State.TIME_WAIT;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
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
