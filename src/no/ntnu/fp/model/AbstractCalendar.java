package no.ntnu.fp.model;

import java.util.Date;
import java.util.List;

public interface AbstractCalendar {
	public List<Event> getAllEvents();

	public List<Event> getEvents(Date from, Date to);

	public void addCalendarChangeEventListener(CalendarChangeEventListener listener);
	public void removeCalendarChangeEventListener(CalendarChangeEventListener listener);
}
