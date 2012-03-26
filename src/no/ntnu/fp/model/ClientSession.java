package no.ntnu.fp.model;

import no.ntnu.fp.model.ref.UserRef;

public class ClientSession {
	private UserRef user;
	private Calendar calendar; // Contains user's own events
	private CompositeCalendar calendars; // Contains user's own calendar, as
											// well as other users' calendars

	// private Inbox inbox; //TODO: Implement inbox model
	
	// TODO: link this model to login session, (including connection)

	public ClientSession(User user) {
		this.user = new UserRef(user);
		calendar = new Calendar();
		calendars = new CompositeCalendar();
		calendars.addCalendar(calendar);
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public AbstractCalendar getCalendars() {
		return calendars;
	}
	
	public User getUser() {
		return user.get();
	}
}
