package no.ntnu.fp.gui;

import java.util.EventObject;

import no.ntnu.fp.model.Room;

/*
 * @author: Fredrik
 * 
 */

@SuppressWarnings("serial")
public class PlaceRoomSelectEvent extends EventObject 
{

	String place;
	Room room;

	public PlaceRoomSelectEvent(Room room, String place, Object source) {
		super(source);
		this.place = place;
		this.room = room;	
	}

	public String getPlace() {
		return place;
	}

	public Room getRoom() {
		return room;
	}
	
}
