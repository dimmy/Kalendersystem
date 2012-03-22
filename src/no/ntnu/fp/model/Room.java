package no.ntnu.fp.model;

import java.util.ArrayList;
import java.util.Date;


/*
 * @author: Fredrik
 * @author: �yvind
 * 
 */

public class Room {
	
	private String roomId;
	private int capacity;
	private ArrayList<RoomReservation> reservations;
	
	public Room(String roomId, int capacity){
		this.roomId = roomId;
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
//		RoomReservation r = new RoomReservation(date, forHowLong, event);
//		int id = r.getId();
//		reservations.add(id);
		
	}
	
	public String getRoomID() {
		return roomId;
	}

	public int getCapacity() {
		return capacity;
	}


}
