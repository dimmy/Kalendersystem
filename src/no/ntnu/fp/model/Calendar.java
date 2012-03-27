package no.ntnu.fp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.ntnu.fp.model.CalendarChangeEvent.Type;

public class Calendar implements AbstractCalendar, PropertyChangeListener {
	private ArrayList<Event> events;

	private ArrayList<CalendarChangeEventListener> listeners;

	public Calendar() {
		events = new ArrayList<Event>();
		listeners = new ArrayList<CalendarChangeEventListener>();
	}

	public void addEvent(Event event) {
		// TODO: check for duplicate before adding
		events.add(event);
		event.addPropertyChangedListener(this);
		calendarChanged(CalendarChangeEvent.Type.ADDED);
	}

	public void removeEvent(Event event) {
		events.remove(event);
		event.removePropertyChangedListener(this);
		calendarChanged(CalendarChangeEvent.Type.REMOVED);
	}

	public void updateEvent(Event event) {
		if (events.contains(event)) {
			calendarChanged(CalendarChangeEvent.Type.CHANGED);
		}
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

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		calendarChanged(CalendarChangeEvent.Type.CHANGED);
	}

}
