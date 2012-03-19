package no.ntnu.fp.model;

import java.util.ArrayList;
import java.util.Date;

public class Room {
	
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
			if(r.id = id)
			{
				reservations.remove(r);
			}
		}
		
	}
	
	public void reserve(Date from, Date to)
	{
		reservations.add(new RoomReservation(from, to));
	}

}
