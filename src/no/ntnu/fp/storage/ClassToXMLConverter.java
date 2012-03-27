package no.ntnu.fp.storage;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.fp.model.Event;
import no.ntnu.fp.model.User;
import no.ntnu.fp.model.ref.UserRef;

public class ClassToXMLConverter {

	public static List<String> EventFromClassToXML(Event event, List<UserRef> participants){
		List<String> xmlList = new ArrayList<String>();
		xmlList.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlList.add("<createevent eventdescription=\""+event.getEventdescription()+"\" place=\""+event.getPlace()+"\" timelength=\""+event.getTimeLength()+"\" name=\""+event.getEventname()+"\" leader=\""+event.getEventowner().getUsername()+"\" type=\""+event.getType().toString()+"\">");
		System.out.println(xmlList.get(1));
		xmlList.add("<participants>");
		for (int i = 0; i < participants.size(); i++) {
			xmlList.add("<user username=\""+participants.get(i).getUsername()+"\">");
			xmlList.add("</user>");
		}
		xmlList.add("</participants>");
		xmlList.add("</createevent>");
		return xmlList;
	}
	
	public static List<String> EventFromClassToXML(Event event){
		List<String> xmlList = new ArrayList<String>();
		xmlList.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlList.add("<createevent eventdescription="+event.getEventdescription()+" place="+event.getPlace()+" " +
				"timelength = "+event.getTimeLength()+" name="+event.getEventname()+"" +
						" leader="+event.getEventowner()+" type="+event.getType().toString()+">");
		return xmlList;
	}
}
