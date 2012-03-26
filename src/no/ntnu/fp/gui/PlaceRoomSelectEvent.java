package no.ntnu.fp.gui;

import java.util.EventObject;

import no.ntnu.fp.model.Room;
import no.ntnu.fp.model.ref.RoomRef;

/*
 * @author: Fredrik
 * 
 */

@SuppressWarnings("serial")
public class PlaceRoomSelectEvent extends EventObject 
{

	String place;
	RoomRef room;

	public PlaceRoomSelectEvent(RoomRef room, String place, Object source) {
		super(source);
		this.place = place;
		this.room = room;	
	}

	public String getPlace() {
		return place;
	}

	public RoomRef getRoom() {
		return room;
	}
	
}
