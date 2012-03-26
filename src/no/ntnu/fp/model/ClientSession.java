package no.ntnu.fp.model;

public class ClientSession {
	private User user;
	private Calendar calendar; // Contains user's own events
	private CompositeCalendar calendars; // Contains user's own calendar, as
											// well as other users' calendars

	// private Inbox inbox; //TODO: Implement inbox model
	
	// TODO: link this model to login session, (including connection)

	public ClientSession(User user) {
		this.user = user;
		calendar = new Calendar();
		calendars = new CompositeCalendar();
		calendars.addCalendar(calendar);
	}
}
