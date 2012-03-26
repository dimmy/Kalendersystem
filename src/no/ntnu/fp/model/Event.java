package no.ntnu.fp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import no.ntnu.fp.model.ref.RoomRef;
import no.ntnu.fp.model.ref.UserRef;

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
	private RoomRef room;
	private String status;
	private UserRef eventowner;

	// Event listeners

	ArrayList<PropertyChangeListener> pclisteners;

	public Event() {
		type = Type.appointment;
		pclisteners = new ArrayList<PropertyChangeListener>();
	}

	public Event(int eventId, String eventdescription, String eventname, Date startTime, int timelength, String type, String place, String room, String status, String eventOwner){
		this.eventdescription = eventdescription;
		this.eventID = eventId;
		this.eventname = eventname;
		this.eventowner = new UserRef(eventOwner);
		this.status = status;
		this.timeLength = timelength;
		this.type = (type == "appointment") ? Type.appointment : Type.meeting; 
		this.room = new RoomRef(room);
		this.startTime = startTime;
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
		return eventowner.get();
	}

	public void setEventowner(User eventowner) {
		PropertyChangeEvent e = new PropertyChangeEvent(this, "eventowner",
				this.eventowner, eventowner);
		this.eventowner = new UserRef(eventowner);
		firePropertyChangeEvent(e);
	}

	public void setEventowner(String eventownerusername) {
		PropertyChangeEvent e = new PropertyChangeEvent(this, "eventowner",
				this.eventowner, eventowner);
		this.eventowner = new UserRef(eventownerusername);
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

	public Room getRoom() {
		return room.get();
	}

	public void setRoom(Room room) {
		PropertyChangeEvent e = new PropertyChangeEvent(this,
				"room", this.room, room);
		this.room = new RoomRef(room);
		firePropertyChangeEvent(e);
	}

	public void setRoom(String roomid) {
		PropertyChangeEvent e = new PropertyChangeEvent(this,
				"room", this.room, room);
		this.room = new RoomRef(roomid);
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

	public Date getFrom() {
		return startTime;
	}

	public void setFrom(Date from) {
		setStartTime(from);
	}

	public Date getTo() {
		return new Date(startTime.getTime() + 60000 * timeLength);
	}

	public void setTo(Date to) {
		int ms = (int) (to.getTime() - startTime.getTime());
		if (ms <= 0)
			ms = 60000;
		setTimeLength(ms);
	}

	public String getFromText() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(getFrom());
	}

	public String getToText() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(getTo());
	}

	public void setFromText(String fromText) {
		try {
			SimpleDateFormat sdf_prefix = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf_final = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			String fulldate = sdf_prefix.format(startTime) + " " + fromText;
			setFrom(sdf_final.parse(fulldate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setToText(String toText) {
		try {
			SimpleDateFormat sdf_prefix = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf_final = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			String fulldate = sdf_prefix.format(startTime) + " " + toText;
			setTo(sdf_final.parse(fulldate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getDateText() {
		SimpleDateFormat sdf_prefix = new SimpleDateFormat("yyyy-MM-dd");
		return sdf_prefix.format(startTime);
	}

	public void setDateText(String dateText) {
		try {
			SimpleDateFormat sdf_suffix = new SimpleDateFormat("mm:ss");
			SimpleDateFormat sdf_final = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm");
			String fulldate = dateText + " " + sdf_suffix.format(startTime);
			setFrom(sdf_final.parse(fulldate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
