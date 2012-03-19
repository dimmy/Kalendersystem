package no.ntnu.fp.model;

import java.util.Date;

import no.ntnu.fp.gui.AppointmentForm;
import no.ntnu.fp.gui.MeetingForm;

/**
 * 
 * @author Myklatun
 *
 */

public class Event {
	
	private int eventID;
	private String eventdescription;
	private Date date;
	private String startTime;
	private String endTime;
	private Type type;
	private String place;
	private RoomReservation room;
	private String status;
	private Person eventowner;
	
	public Event(MeetingForm form){
		
	}
	
	public Event(AppointmentForm form){
		type = Type.appointment;
	}
	
	public void changeEvent(){
		type = Type.meeting;
	}
	
	public void deleteEvent(){
		
	}
	
	public void addPerson(){
		
	}
	
	public void setRoom(RoomReservation room){
		this.room = room;
	}
	
	public void setPlace(String place){
		this.place = place;
	}

}
