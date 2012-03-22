package no.ntnu.fp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CompositeCalendar implements AbstractCalendar,
		CalendarChangeEventListener {

	private ArrayList<CalendarChangeEventListener> listeners;
	private ArrayList<Calendar> calendars;

	public CompositeCalendar() {
		calendars = new ArrayList<Calendar>();
		listeners = new ArrayList<CalendarChangeEventListener>();
	}

	public void addCalendar(Calendar cal) {
		// TODO: Check duplicates
		calendars.add(cal);
		cal.addCalendarChangeEventListener(this);
	}

	public void removeCalendar(Calendar cal) {
		// TODO: Check duplicates
		calendars.remove(cal);
		cal.removeCalendarChangeEventListener(this);
	}

	public void clear() {
		calendars.clear();
	}

	@Override
	public List<Event> getAllEvents() {
		ArrayList<Event> ret = new ArrayList<Event>();

		for (Calendar cal : calendars) {
			ret.addAll(cal.getAllEvents());
		}

		return ret;
	}

	@Override
	public List<Event> getEvents(Date from, Date to) {
		ArrayList<Event> ret = new ArrayList<Event>();

		for (Calendar cal : calendars) {
			ret.addAll(cal.getEvents(from, to));
		}

		return ret;
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
	public void calendarChanged(CalendarChangeEvent ev) {
		for (CalendarChangeEventListener listener : listeners) {
			listener.calendarChanged(ev);
		}
	}
}
