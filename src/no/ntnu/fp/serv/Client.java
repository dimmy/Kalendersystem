package no.ntnu.fp.serv;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import no.ntnu.fp.model.Event;
import no.ntnu.fp.model.ref.UserRef;
import nu.xom.Document;
import nu.xom.Element;

public class Client {
	public static void main(String[] args) throws IOException,
			XMLStreamException {

		Socket kkSocket = null;
		PrintWriter out = null;
		XMLInputFactory inputFactory = null;
		XMLStreamReader xmlReader = null;
		
		
		String en = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String to = "<getevents username=\"test\">";
		String tre = "</getevents>";
//		String to = "<changeevent eventid=\"7\" eventdescription=\"Laangt\" place=\"Knuts kontor\" timelength = \"110\" name=\"personalfode\" leader=\"test\" type=\"appointment\">";
//		String tre = "<addparticipants eventid = \"7\">";
//		String fire = "<user username = \"perry\">";
//		String fem = "</user>";
//		String seks = "<user username = \"larsy\">";
//		String sju = "</user>";
//		String otte = "</addparticipants>";
//		String ni = "</changeevent>";

		try {
			kkSocket = new Socket("Oyvind-PC", 4444);
			out = new PrintWriter(kkSocket.getOutputStream(), true);
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: Oyvind-PC.");
			System.exit(1);
		} catch (IOException e) {
			System.err
					.println("Couldn't get I/O for the connection to: Oyvind-PC.");
			System.exit(1);
		}
		
		out.print(en);
		out.print(to);
		out.print(tre);
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
		xmlReader = factory.createXMLStreamReader(kkSocket.getInputStream());
		while(xmlReader.hasNext()){
			switch (xmlReader.getEventType()) {
			case XMLStreamConstants.END_DOCUMENT:
				break;
			case XMLStreamConstants.START_ELEMENT:
				if(xmlReader.getName().toString() == "login"){
					
				}
				
				break;
			case XMLStreamConstants.START_DOCUMENT:
				break;

			}
			xmlReader.next();
		}
		xmlReader.close();
	}
}
