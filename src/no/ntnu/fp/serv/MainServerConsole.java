package no.ntnu.fp.serv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import no.ntnu.fp.storage.KalSysDBConnection;

import org.xml.sax.SAXException;


// http://stackoverflow.com/questions/920904/reading-multiple-xml-documents-from-a-socket-in-java

/**
 * author: Myklatun
 */
public class MainServerConsole {

	
	public static void main(String[] args) throws XMLStreamException,
			IOException, ParserConfigurationException, SAXException, SQLException {
		
		XMLInputFactory inputFactory = null;
		XMLStreamReader xmlReader = null;
		ServerSocket serverSocket = null;

		/**
		 * Try to open connection
		 */
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException f) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}

		Socket clientSocket = null;

		/**
		 * Accept connection from client
		 */
		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}
		
		
		inputFactory = XMLInputFactory.newInstance();
		xmlReader = inputFactory.createXMLStreamReader(clientSocket
				.getInputStream());

		
		/**
		 * Use xmlReader to get info and insert into database
		 */
		
		while (xmlReader.hasNext()) {
			switch (xmlReader.getEventType()) {
			case XMLStreamConstants.END_DOCUMENT:
				break;
			case XMLStreamConstants.START_ELEMENT:
				if (xmlReader.getName().toString() == "createevent") {
					insertEvent(xmlReader);
				}else if(xmlReader.getName().toString() == "deleteevent"){
					deleteEvent(xmlReader);
				}else if(xmlReader.getName().toString() == "changeevent"){
					changeEvent(xmlReader);
				}
				else if (xmlReader.getName().toString() == "room") {
					insertRoomIntoDatabase(xmlReader);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				if (xmlReader.getName().toString() == "oppskrift") {
					System.out.println("end");
				}
				break;
			case XMLStreamConstants.START_DOCUMENT:
				break;

			}
			xmlReader.next();
			xmlReader.close();
		}

	}


	/**
	 * Insert Event
	 * @throws SQLException 
	 */
	private static void insertEvent(XMLStreamReader xmlReader)
			throws XMLStreamException, SQLException {
		
		String place = xmlReader.getAttributeValue(null, "place");
		String status = (xmlReader.getAttributeValue(null, "status"));
		String name = xmlReader.getAttributeValue(null, "name");
		int timeLength = Integer.parseInt((xmlReader.getAttributeValue(null,
				"timelength")));
		String type = xmlReader.getAttributeValue(null, "type");
		String leader = xmlReader.getAttributeValue(null,
				"leader");
		String roomId = xmlReader.getAttributeValue(null, "roomid");
		String date = xmlReader.getAttributeValue(null, "date");
		String eventDescription = xmlReader.getAttributeValue(null,
				"eventdescription");
		
		KalSysDBConnection conn = new KalSysDBConnection();
		conn.initializeDB();
		conn.insertEvent(eventDescription, leader, name, new Date(21), timeLength, place, roomId, type, status);
		int latestEventId = conn.getLatestEventId();
		
		xmlReader.next();
		if (xmlReader.getName().toString() == "participants") {
			xmlReader.next();
			while (xmlReader.getName().toString() == "person") {
				String userName = xmlReader.getAttributeValue(null, "username");
				conn.insertParticipant(userName, latestEventId, "waiting");
				xmlReader.next();
				xmlReader.next();
				
			}
		}
	}
	private static void deleteEvent(XMLStreamReader xmlReader) throws SQLException{
		int eventId = Integer.parseInt(xmlReader.getAttributeValue(null, "eventid"));
		KalSysDBConnection conn = new KalSysDBConnection();
		conn.initializeDB();
		conn.deleteEvent(eventId);
	}
	
	private static void changeEvent(XMLStreamReader xmlReader) throws SQLException, XMLStreamException{
		int evid = Integer.parseInt(xmlReader.getAttributeValue(null, "evid"));
		String place = xmlReader.getAttributeValue(null, "place");
		String status = (xmlReader.getAttributeValue(null, "status"));
		int timeLength = Integer.parseInt((xmlReader.getAttributeValue(null,
				"timelength")));
		String type = xmlReader.getAttributeValue(null, "type");
		String eventOwner = xmlReader.getAttributeValue(null,
				"eventowner");
		String roomId = xmlReader.getAttributeValue(null, "roomid");
		String date = xmlReader.getAttributeValue(null, "date");
		String evenDescription = xmlReader.getAttributeValue(null,
				"eventdescription");
		
		KalSysDBConnection conn = new KalSysDBConnection();
		conn.initializeDB();
		conn.changeEvent(evid, evenDescription, eventOwner, new Date(21), timeLength, place, roomId, type, status);
		
		if (xmlReader.getName().toString() == "participants") {
			xmlReader.next();
			while (xmlReader.getName().toString() == "person") {
				String userName = xmlReader.getAttributeValue(null, "username");
				conn.insertParticipant(userName, evid, "waiting");
				xmlReader.next();
				xmlReader.next();
				
			}
		}
	}

	private static void insertRoomIntoDatabase(XMLStreamReader xmlReader) {
		String roomId = xmlReader.getAttributeValue(null, "roomid");
		int capacity = Integer.parseInt(xmlReader.getAttributeValue(null,
				"capacity"));

		// Insert into Database
	}

}
