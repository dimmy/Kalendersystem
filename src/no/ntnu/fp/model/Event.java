package no.ntnu.fp.model;

import java.util.Date;

/**
 * 
 * @author Myklatun
 *
 */

public class Event {
	
	public enum Type {
		
		appointment, meeting;
	}

	private int eventID;
	private String eventdescription;
	private Date date;
	private String startTime;
	private String endTime;
	private Type type;
	private String place;
	private RoomReservation room;
	private String status;
	private User eventowner;
	
	public Event(){
		type = Type.appointment;
	}
	
	public void changeEvent(){
		type = Type.meeting;
	}
	
	public void deleteEvent(){
		
	}
	
	public void addPerson(){
		
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getEventdescription() {
		return eventdescription;
	}

	public void setEventdescription(String eventdescription) {
		this.eventdescription = eventdescription;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Person getEventowner() {
		return eventowner;
	}

	public void setEventowner(Person eventowner) {
		this.eventowner = eventowner;
	}

	public String getPlace() {
		return place;
	}

	public RoomReservation getRoom() {
		return room;
	}
}
