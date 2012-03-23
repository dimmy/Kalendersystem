package no.ntnu.fp.serv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import no.ntnu.fp.model.Event.Type;
import no.ntnu.fp.storage.KalSysDBConnection;

import org.xml.sax.SAXException;

// http://stackoverflow.com/questions/920904/reading-multiple-xml-documents-from-a-socket-in-java

public class MainServerConsole {

	public static void main(String[] args) throws XMLStreamException,
			IOException, ParserConfigurationException, SAXException {
		
		KalSysDBConnection connection = new KalSysDBConnection();
		connection.initializeDB();
		XMLInputFactory inputFactory = null;
		XMLStreamReader xmlReader = null;
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException f) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}

		Socket clientSocket = null;

		try {
			clientSocket = serverSocket.accept();
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
				if (xmlReader.getName().toString() == "event") {
					insertEventIntoDatabase(xmlReader);
				} else if (xmlReader.getName().toString() == "room") {
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

	// private static void makeEvent(XMLStreamReader xmlReader){
	// no.ntnu.fp.model.Event event = new no.ntnu.fp.model.Event();
	// event.setPlace(xmlReader.getAttributeValue(null, "place"));
	// event.setEventID(Integer.parseInt((xmlReader.getAttributeValue(null,
	// "eventid"))));
	// event.setStatus(xmlReader.getAttributeValue(null, "status"));
	// event.setTimeLength(Integer.parseInt((xmlReader.getAttributeValue(null,
	// "timelength"))));
	// if(xmlReader.getAttributeValue(null, "type") == "appointment"){
	// event.setType(Type.appointment);
	// }else{
	// event.setType(Type.meeting);
	// }
	//
	//
	// }

	private static void insertEventIntoDatabase(XMLStreamReader xmlReader)
			throws XMLStreamException {
		
		String place = xmlReader.getAttributeValue(null, "place");
		int eventId = Integer.parseInt((xmlReader.getAttributeValue(null,
				"eventid")));
		String status = (xmlReader.getAttributeValue(null, "status"));
		int timeLength = Integer.parseInt((xmlReader.getAttributeValue(null,
				"timelength")));
		String type;
		if (xmlReader.getAttributeValue(null, "type") == "appointment") {
			type = Type.appointment.toString();
		} else {
			type = Type.meeting.toString();
		}
		int eventOwner = Integer.parseInt((xmlReader.getAttributeValue(null,
				"eventowner")));
		String roomId = xmlReader.getAttributeValue(null, "roomid");
		String date = xmlReader.getAttributeValue(null, "date");
		String evenDescription = xmlReader.getAttributeValue(null,
				"eventdescription");

		
		xmlReader.next();
		if (xmlReader.getName().toString() == "participants") {
			xmlReader.next();
			while (xmlReader.getName().toString() == "person") {
				String username = xmlReader.getAttributeValue(null, "username");
				System.out.println(username);
				xmlReader.next();
				xmlReader.next();
			}
		}

		// Insert into database!!
	}

	private static void insertRoomIntoDatabase(XMLStreamReader xmlReader) {
		String roomId = xmlReader.getAttributeValue(null, "roomid");
		int capacity = Integer.parseInt(xmlReader.getAttributeValue(null,
				"capacity"));

		// Insert into Database
	}

}
