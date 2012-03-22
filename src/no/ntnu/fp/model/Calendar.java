package no.ntnu.fp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calendar implements AbstractCalendar {
	private ArrayList<Event> events;

	private ArrayList<CalendarChangeEventListener> listeners;

	Calendar() {
		events = new ArrayList<Event>();
	}

	public void addEvent(Event event) {
		// TODO: check for duplicate before adding
		events.add(event);
		calendarChanged(CalendarChangeEvent.Type.ADDED);
	}

	public void removeEvent(Event event) {
		events.remove(event);
		calendarChanged(CalendarChangeEvent.Type.REMOVED);
	}

	private void calendarChanged(CalendarChangeEvent.Type type) {
		CalendarChangeEvent ev = new CalendarChangeEvent(this, type);
		for (CalendarChangeEventListener listener : listeners) {
			listener.calendarChanged(ev);
		}
	}

	public void clear() {
		events.clear();
	}

	@Override
	public List<Event> getAllEvents() {
		return events;
	}

	@Override
	public List<Event> getEvents(Date from, Date to) {
		List<Event> sublist = new ArrayList<Event>();

		for (Event event : events) {
			if (event.overlaps(from, to)) {
				sublist.add(event);
			}
		}

		return sublist;
	}

	@Override
	public void addCalendarChangeEventListener(
			CalendarChangeEventListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeCalendarChangeEventListener(
			CalendarChangeEventListener listener) {
		listeners.remove(listener);

	}

}
