package no.ntnu.fp.storage;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


import no.ntnu.fp.model.Event;
import no.ntnu.fp.model.User;
import no.ntnu.fp.model.Room;

public interface KalSysStorage {

	public User getUser(String username) throws SQLException;
	
	public void saveUser(User person);
	
	public Event getEvent(int eventId);
	
	public List<Event> getEvents(User user);
	
	public void saveEvent(Event event);
	
	public List<Room> getFreeRooms(Date date) throws SQLException;
	
	public Room getRoom(String roomId) throws SQLException;
	
	
	
}
