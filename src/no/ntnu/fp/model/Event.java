package no.ntnu.fp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private String eventname;
	private Date startTime;
	private int timeLength;
	private Type type;
	private String place;
	private RoomReservation room;
	private String status;
	private User eventowner;

	public Event() {
		type = Type.appointment;
	}

	public void changeEvent() {
		type = Type.meeting;
	}

	public void deleteEvent() {

	}

	public void addPerson() {

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public void setTime(String time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:ss");
		try {
			setStartTime(df.parse(time));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Date getEndTime() {
		// Return startTime with timeLength seconds added. (60000 ms = 1 minute)
		return new Date(startTime.getTime() + (60000 * timeLength));
	}

	public int getTimeLength() {
		return timeLength;
	}

	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
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

	public User getEventowner() {
		return eventowner;
	}

	public void setEventowner(User eventowner) {
		this.eventowner = eventowner;
	}

	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place){
		this.place = place;
	}

	public RoomReservation getRoom() {
		return room;
	}

	/**
	 * Determines whether the event overlaps the given time interval
	 * 
	 * @param from
	 *            the beginning of the time interval
	 * @param to
	 *            the end of the time interval
	 * @return true if it overlaps; false otherwise
	 */
	public boolean overlaps(Date from, Date to) {
		return (from.before(getEndTime())) && to.after(getStartTime());
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
}
