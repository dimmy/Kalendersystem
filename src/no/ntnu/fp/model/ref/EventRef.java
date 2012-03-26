package no.ntnu.fp.model.ref;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.fp.model.Event;

public class EventRef {

	private Integer evid;

	private static Map<Integer, RefCounter<Event>> instances = new HashMap<Integer, RefCounter<Event>>();

	public EventRef(int evid) {
		this.evid = evid;
		RefCounter<Event> r = instances.get(this.evid);
		if (r == null) {
			r = new RefCounter<Event>(null);
			instances.put(evid, r);
		} else {
			r.ref();
		}
	}

	public EventRef(Event event) {
		this.evid = event.getEventID();
		RefCounter<Event> r = instances.get(this.evid);
		if (r == null) {
			r = new RefCounter<Event>(event);
			instances.put(evid, r);
		} else {
			r.set(event);
			r.ref();
		}
	}

	@Override
	public void finalize() {
		RefCounter<Event> r = instances.get(evid);
		if (r != null) {
			if (r.unref()) {
				instances.remove(evid);
			}
		}
	}

	public Event get() {
		Event user = instances.get(evid).get();
		return user;
	}
	
	public int getEvid() {
		return evid;
	}
}
