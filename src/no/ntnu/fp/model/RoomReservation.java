package no.ntnu.fp.model;

import java.util.Date;

public class RoomReservation {
	
	private Date timeFrom;
	private Event event;
	private Date timeTo;
	
	//Getter and setter Event
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	//Getter and setter fromTime
	public Date getFromTime() {
		return timeFrom;
	}
	public void setFromTime(Date from) {
		this.timeFrom = from;
	}

	//Getter and setter toTime
	public Date getToTime() {
		return timeTo;
	}
	public void setToTime(Date to) {
		this.timeTo = to;
	}
	
	public RoomReservation(Date fromTime, Date toTime) {
		
	}
	
}
