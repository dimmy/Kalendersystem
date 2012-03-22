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

	int week;
	int year;

	public CalendarPerspective(AbstractCalendar cal) {
		this.cal = cal;

		java.util.Calendar c = java.util.Calendar.getInstance();

		listeners = new ArrayList<CalendarChangeEventListener>();

		cal.addCalendarChangeEventListener(this);

		c.setTime(new Date());
		year = c.get(java.util.Calendar.YEAR);
		setWeek(1);
	}

	/**
	 * Removes events listeners, so the object can be removed
	 */
	public void liberate() {
		cal.removeCalendarChangeEventListener(this);
	}

	public void addCalendarChangeEventListener(
			CalendarChangeEventListener listener) {
		listeners.add(listener);
	}

	public void removeCalendarChangeEventListener(
			CalendarChangeEventListener listener) {
		listeners.remove(listener);
	}

	@Override
	public void calendarChanged(CalendarChangeEvent ev) {
		events = cal.getEvents(from, to);

		for (CalendarChangeEventListener listener : listeners) {
			listener.calendarChanged(ev);
		}
	}

	public void setYear(int year) {
		this.year = year;

		updatePerspective();
	}

	public int getYear() {
		return year;
	}

	public void setWeek(int week) {
		this.week = week;

		updatePerspective();
	}

	public int getWeek() {
		return week;
	}

	private void updatePerspective() {

		java.util.Calendar c = java.util.Calendar.getInstance();
		c.set(java.util.Calendar.YEAR, year);
		c.set(java.util.Calendar.WEEK_OF_YEAR, week);
		c.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.MONDAY);
		c.set(java.util.Calendar.HOUR, 0);
		c.set(java.util.Calendar.MINUTE, 0);
		c.set(java.util.Calendar.SECOND, 0);
		c.set(java.util.Calendar.MILLISECOND, 0);

		from = c.getTime();

		c.set(java.util.Calendar.DAY_OF_YEAR,
				c.get(java.util.Calendar.DAY_OF_YEAR) + 7);

		to = c.getTime();

		calendarChanged(new CalendarChangeEvent(cal,
				CalendarChangeEvent.Type.CHANGED));
	}

	public List<Event> getEvents() {
		return events;
	}

	public void update() {
		updatePerspective();
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}
}
