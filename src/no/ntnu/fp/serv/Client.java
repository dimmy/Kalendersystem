package no.ntnu.fp.serv;

import java.io.*;
import java.net.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import no.ntnu.fp.model.Event;
import no.ntnu.fp.model.User;
import no.ntnu.fp.model.ref.UserRef;
import no.ntnu.fp.storage.ClassToXMLConverter;
import nu.xom.Document;
import nu.xom.Element;

public class Client {
	public static void main(String[] args) throws IOException,
			XMLStreamException {

		Socket kkSocket = null;
		PrintWriter out = null;
		XMLInputFactory inputFactory = null;
		XMLStreamReader xmlReader = null;
		
		Event event = new Event(0,"fdsfddss", "fdsfds", new Date(22), 2, "appointment", "dfsfds", "dfsadsa", "dsadsa", "test");
		List<UserRef> users = new ArrayList<UserRef>();
		users.add(new UserRef("test"));
		users.add(new UserRef("knut"));
//		String en = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
//		String to = "<changeevent eventid=\"7\" eventdescription=\"Laangt\" place=\"Knuts kontor\" timelength = \"110\" name=\"personalfode\" leader=\"test\" type=\"appointment\">";
//		String tre = "<addparticipants eventid = \"7\">";
//		String fire = "<user username = \"perry\">";
//		String fem = "</user>";
//		String seks = "<user username = \"larsy\">";
//		String sju = "</user>";
//		String otte = "</addparticipants>";
//		String ni = "</changeevent>";
		List<String> meeting = ClassToXMLConverter.EventFromClassToXML(event, users);
		
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
		
		for (int i = 0; i < meeting.size(); i++) {
			out.println(meeting.get(i));
		}
//		out.print(en);
//		out.print(to);
//		out.print(tre);
//		out.print(fire);
//		out.print(fem);
//		out.print(seks);
//		out.print(sju);
//		out.print(otte);
//		out.print(ni);
		
		out.close();

	}
}
