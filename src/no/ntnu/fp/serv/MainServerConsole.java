package no.ntnu.fp.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import no.ntnu.fp.model.Event;
import no.ntnu.fp.model.Room;
import no.ntnu.fp.model.Event.Type;
import no.ntnu.fp.model.User;
import no.ntnu.fp.model.ref.UserRef;
import no.ntnu.fp.storage.ClassToXMLConverter;
import no.ntnu.fp.storage.KalSysDBConnection;

import org.xml.sax.SAXException;

// http://stackoverflow.com/questions/920904/reading-multiple-xml-documents-from-a-socket-in-java

/**
 * author: Myklatun
 */
public class MainServerConsole {

	public static void main(String[] args) throws XMLStreamException,
			IOException, ParserConfigurationException, SAXException,
			SQLException {

		KalSysDBConnection conn = new KalSysDBConnection();
		conn.initializeDB();
		XMLInputFactory inputFactory = null;
		XMLStreamReader xmlReader = null;
		ServerSocket serverSocket = null;
		PrintWriter out = null;

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
			out = new PrintWriter(clientSocket.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Accept failed.");
			System.exit(1);
		}

		inputFactory = XMLInputFactory.newInstance();
		xmlReader = inputFactory.createXMLStreamReader(clientSocket
				.getInputStream());


		while (xmlReader.hasNext()) {
			switch (xmlReader.getEventType()) {
			case XMLStreamConstants.END_DOCUMENT:
				break;
			case XMLStreamConstants.START_ELEMENT:
				if (xmlReader.getName().toString() == "createevent") {
					Event event = parseEvent(xmlReader);
					conn.insertEvent(event);
				} else if (xmlReader.getName().toString() == "deleteevent") {
					Event event = parseEvent(xmlReader);
					conn.deleteEvent(event);
				} else if (xmlReader.getName().toString() == "changeevent") {
					Event event = parseEvent(xmlReader);
					conn.changeEvent(event);
				} else if (xmlReader.getName().toString() == "addparticipants") {
					int eventid = Integer.parseInt(xmlReader.getAttributeValue(
							null, "eventid"));
					List<UserRef> participants = new ArrayList<UserRef>();
					xmlReader.next();
					while (xmlReader.getName().toString() == "user") {
						String username = xmlReader.getAttributeValue(null,
								"username");
						participants.add(new UserRef(username));
						xmlReader.next();
						xmlReader.next();
					}
					conn.addParticipants(participants, eventid);
				} else if (xmlReader.getName().toString() == "deleteparticipants") {
					int eventid = Integer.parseInt(xmlReader.getAttributeValue(
							null, "eventid"));
					List<UserRef> participants = new ArrayList<UserRef>();
					xmlReader.next();
					while (xmlReader.getName().toString() == "user") {
						String username = xmlReader.getAttributeValue(null,
								"username");
						participants.add(new UserRef(username));
						xmlReader.next();
						xmlReader.next();
					}
					conn.deleteParticipants(participants, eventid);
				} else if (xmlReader.getName().toString() == "login") {
					String username = xmlReader.getAttributeValue(null, "username");
					String password = xmlReader.getAttributeValue(null, "password");
					conn.isValidUser(username, password);
					
				}else if(xmlReader.getName().toString() == "getevents"){
					List<Event> events = conn.getEvents(xmlReader.getAttributeValue(null,"username"));
					List<String> event = ClassToXMLConverter.EventFromClassToXML(events.get(1));
					for (int i = 0; i < events.size(); i++) {
						out.print(event.get(i));
					}
				}
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
	 * 
	 * @throws SQLException
	 */
	public static Event parseEvent(XMLStreamReader xmlReader)
			throws XMLStreamException, SQLException {

		int eventId = xmlReader.getAttributeValue(null, "eventid") != null ? Integer
				.parseInt(xmlReader.getAttributeValue(null, "eventid")) : 0;
		String place = xmlReader.getAttributeValue(null, "place");
		String status = (xmlReader.getAttributeValue(null, "status"));
		String name = xmlReader.getAttributeValue(null, "name");
		int timeLength = Integer.parseInt((xmlReader.getAttributeValue(null,
				"timelength")));
		String type = xmlReader.getAttributeValue(null, "type");
		String leader = xmlReader.getAttributeValue(null, "leader");
		String roomId = xmlReader.getAttributeValue(null, "roomid");
		String date = xmlReader.getAttributeValue(null, "date");
		String eventDescription = xmlReader.getAttributeValue(null,
				"eventdescription");
		Event event;
		if (eventId != 0) {
			event = new Event(eventId, eventDescription, name, new Date(21),
					timeLength, type, place, roomId, status, leader);
		} else {
			event = new Event(eventId, eventDescription, name, new Date(21),
					timeLength, type, place, roomId, status, leader);
		}
		return event;
	}

	public static Room parseRoom(XMLStreamReader xmlReader) {
		String roomId = xmlReader.getAttributeValue(null, "roomid");
		int capacity = Integer.parseInt(xmlReader.getAttributeValue(null,
				"capacity"));
		Room room = new Room(roomId, capacity);
		return room;
	}

	public static User parseUser(XMLStreamReader xmlReader) {
		String username = xmlReader.getAttributeValue(null, "username");
		String email = xmlReader.getAttributeValue(null, "email");
		String name = xmlReader.getAttributeValue(null, "name");
		int phone = xmlReader.getAttributeValue(null, "phone") != null ? Integer
				.parseInt(xmlReader.getAttributeValue(null, "phone")) : 0;
		User user = new User(username, name, email, phone);
		return user;
	}

}
