package no.ntnu.fp.model;

import java.util.Date;

public class RoomReservation {
	
	private int id;
	private Date timeFrom;
	private Event event;
	private Date timeTo;
	
	
	
	public RoomReservation(Date fromTime, Date toTime, int id, Event event) {
		this.timeFrom = fromTime;
		this.timeTo = toTime;
		this.event = event;
		this.id = id;
	}
	
	public RoomReservation(Date fromTime, Date toTime, Event event) {
		this.timeFrom = fromTime;
		this.timeTo = toTime;
		this.event = event;
		this.id = getNextId();
	}
	
	
	
	public int getNextId(){
		//Her må vi koble til database og finne neste mulige id
		return 0;
	}
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
	
	//Getter id
	public int getId() {
		return id;
	}
	
	
}
