package no.ntnu.fp.storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.ntnu.fp.model.Event;
import no.ntnu.fp.model.User;
import no.ntnu.fp.model.Room;

public class KalSysDBConnection extends DatabaseConnection implements
		KalSysStorage {

	@Override
	public User getUser(String username) throws SQLException {
		PreparedStatement pst = conn.prepareStatement("select * from user where username=?");
		pst.setString(1, username);
		
		ResultSet rs = this.makeSingleQuery(pst);
		
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setName(rs.getString("user"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getInt("phone"));
		return user;
	}

	@Override
	public void saveUser(User person) {
		// TODO Auto-generated method stub

	}

	@Override
	public Event getEvent(int eventId) {
		return null;
	}

	@Override
	public List<Event> getEvents(User user) {
		return null;
	}

	@Override
	public void saveEvent(Event event) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Room> getFreeRooms(Date date) throws SQLException {
		String query = "select * from room";
		ResultSet rs = this.makeSingleQuery(query);
		List<Room> rooms = new ArrayList<Room>();

		while (rs.next()) {
			Room room = new Room(rs.getString("roomId"), rs.getInt("capacity"));
			rooms.add(room);
		}

		return rooms;
	}

	
	@Override
	public Room getRoom(String roomId) throws SQLException {
		PreparedStatement pst = conn.prepareStatement("select * from room where roomId=?");
		pst.setString(1, roomId);
		
		ResultSet rs = this.makeSingleQuery(pst);
		
		return new Room(rs.getString("roomId"), rs.getInt("capacity"));
	}

	public static void main(String[] args) {
		KalSysDBConnection connection = new KalSysDBConnection();
		connection.initializeDB();
		List<Room> rooms = null;
		try {
			rooms = connection.getFreeRooms(new Date());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		for (int i = 0; i < rooms.size(); i++) {
			System.out.println(rooms.get(i));
		}
	}


}
