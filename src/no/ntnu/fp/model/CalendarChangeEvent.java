package no.ntnu.fp.model;

import java.util.EventObject;

public class CalendarChangeEvent extends EventObject {

	public enum Type {
		ADDED, REMOVED, CHANGED
	};

	private Type type;

	public CalendarChangeEvent(AbstractCalendar source, Type type) {
		super(source);
	}

	public Type getType() {
		return type;
	}

}
