package no.ntnu.fp.model;

import java.util.ArrayList;
import java.util.Date;


/*
 * @author: Fredrik
 * @author: ¯yvind
 * 
 */

public class Room {
	
	private int id;
	private String roomID;
	private String description;
	private int capacity;
	private ArrayList<RoomReservation> reservations;
	
	public Room(String roomID, String description, int capacity){
		this.roomID = roomID;
		this.description = description;
		this.capacity = capacity;
	}

	public void unReserve(int id)
	{
		for(RoomReservation r : reservations)
		{
			if(r.getId() == id)
			{
				reservations.remove(r);
			}
		}
		
	}
	
	
	
	/**
	 * Reserves the Room for a date and a period for a event. Checks if the date and start time is avalible
	 * @param  date  It's the date and time for when the room is going to be reserved
	 * @param  forHowLong It's an int for how many minuts the room is going to be reserved
	 * @param  event It's the event the room is going to reserved for.
	 */
	public void reserve(Date date, int forHowLong, Event event)
	{
		reservations.add(new RoomReservation(date, forHowLong, event));
	}
	
	public String getRoomID() {
		return roomID;
	}

	public int getCapacity() {
		return capacity;
	}


}
