package no.ntnu.fp.storage;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;


import no.ntnu.fp.model.Event;
import no.ntnu.fp.model.User;
import no.ntnu.fp.model.Room;
import no.ntnu.fp.model.ref.UserRef;

public interface KalSysStorage {

	public User getUser(String username) throws SQLException;
	
	public void saveUser(User person);
	
	public Event getEvent(int eventId);
	
	public List<Event> getEvents(User user);
	
	public void saveEvent(Event event);
	
	public List<Room> getFreeRooms(Date date) throws SQLException;
	
	public Room getRoom(String roomId) throws SQLException;
	
	public void insertEvent(String evenDescription, String leader, String name, java.sql.Date date, int timeLength, String place, String roomId, String type, String status) throws SQLException;
	
	public void deleteEvent(int eventId) throws SQLException;
	
	public void insertParticipant(String userName, int evid, String status) throws SQLException;
	
	public int getLatestEventId() throws SQLException;
	
	public void changeEvent(int evid, String evenDescription, String name, java.sql.Date date, int timeLength, String place, String roomId, String type, String status) throws SQLException;
	
	public void deleteParticipants(List<UserRef> users, int eventid) throws SQLException;
	
	public void addParticipants(List<UserRef> users, int eventid) throws SQLException; 
}
