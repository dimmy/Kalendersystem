package no.ntnu.fp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarPerspective implements CalendarChangeEventListener {
	private AbstractCalendar cal;

	List<Event> events;
	Date from;
	Date to;
	ArrayList<CalendarChangeEventListener> listeners;

	public CalendarPerspective(AbstractCalendar cal) {
		this.cal = cal;
		from = new Date();
		to = new Date();
		to.setTime(from.getTime() + 1000 * 60 * 60 * 24 * 7);
		listeners = new ArrayList<CalendarChangeEventListener>();
		cal.addCalendarChangeEventListener(this);
	}

	/**
	 * Removes events listeners, so the object can be removed
	 */
	public void liberate() {
		cal.removeCalendarChangeEventListener(this);
	}

	void addCalendarChangeEventListener(CalendarChangeEventListener listener) {
		listeners.add(listener);
	}

	void removeCalendarChangeEventListener(CalendarChangeEventListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void calendarChanged(CalendarChangeEvent ev) {
		for (CalendarChangeEventListener listener : listeners) {
			listener.calendarChanged(ev);
		}
	}
}
