package no.ntnu.fp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
	private RoomReservation roomReservation;
	private String status;
	private User eventowner;

	// Event listeners

	ArrayList<PropertyChangeListener> pclisteners;

	public Event() {
		type = Type.appointment;
		pclisteners = new ArrayList<PropertyChangeListener>();
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
		PropertyChangeEvent e = new PropertyChangeEvent(this, "eventID",
				this.eventID, eventID);
		this.eventID = eventID;
		firePropertyChangeEvent(e);
	}

	public String getEventdescription() {
		return eventdescription;
	}

	public void setEventdescription(String eventdescription) {
		PropertyChangeEvent e = new PropertyChangeEvent(this,
				"eventdescription", this.eventdescription, eventdescription);
		this.eventdescription = eventdescription;
		firePropertyChangeEvent(e);
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		PropertyChangeEvent e = new PropertyChangeEvent(this, "startTime",
				this.startTime, startTime);
		this.startTime = startTime;
		firePropertyChangeEvent(e);
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
		PropertyChangeEvent e = new PropertyChangeEvent(this, "timeLength",
				this.timeLength, timeLength);
		this.timeLength = timeLength;
		firePropertyChangeEvent(e);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		PropertyChangeEvent e = new PropertyChangeEvent(this, "type",
				this.type, type);
		this.type = type;
		firePropertyChangeEvent(e);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		PropertyChangeEvent e = new PropertyChangeEvent(this, "status",
				this.status, status);
		this.status = status;
		firePropertyChangeEvent(e);
	}

	public User getEventowner() {
		return eventowner;
	}

	public void setEventowner(User eventowner) {
		PropertyChangeEvent e = new PropertyChangeEvent(this, "eventowner",
				this.eventowner, eventowner);
		this.eventowner = eventowner;
		firePropertyChangeEvent(e);
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		PropertyChangeEvent e = new PropertyChangeEvent(this, "place",
				this.place, place);
		this.place = place;
		firePropertyChangeEvent(e);
	}

	public RoomReservation getRoomReservation() {
		return roomReservation;
	}

	public void setRoomReservation(RoomReservation roomReservation) {
		PropertyChangeEvent e = new PropertyChangeEvent(this, "roomReservation",
				this.roomReservation, roomReservation);
		this.roomReservation = roomReservation;
		firePropertyChangeEvent(e);
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
		PropertyChangeEvent e = new PropertyChangeEvent(this, "eventname",
				this.eventname, eventname);
		this.eventname = eventname;
		firePropertyChangeEvent(e);
	}

	public void addPropertyChangedListener(PropertyChangeListener listener) {
		pclisteners.add(listener);
	}

	public void removePropertyChangedListener(PropertyChangeListener listener) {
		pclisteners.remove(listener);
	}

	private void firePropertyChangeEvent(PropertyChangeEvent ev) {
		for (PropertyChangeListener listener : pclisteners) {
			listener.propertyChange(ev);
		}
	}
}
