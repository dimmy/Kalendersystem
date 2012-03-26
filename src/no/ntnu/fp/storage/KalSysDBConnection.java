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
import no.ntnu.fp.model.ref.UserRef;

public class KalSysDBConnection extends DatabaseConnection implements
		KalSysStorage {

	@Override
	public User getUser(String username) throws SQLException {
		PreparedStatement pst = conn
				.prepareStatement("select * from user where username=?");
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

	public void insertParticipant(String userName, int evid, String status)
			throws SQLException {
		PreparedStatement pst = conn
				.prepareStatement("insert into participant values(?, ?, ?)");
		pst.setString(1, userName);
		pst.setInt(2, evid);
		pst.setString(3, status);
		makeUpdate(pst);
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
		PreparedStatement pst = conn
				.prepareStatement("select * from room where roomId=?");
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

	@Override
	public void deleteParticipants(List<UserRef> users, int eventid)
			throws SQLException {
		PreparedStatement pst;
		for (int i = 0; i < users.size(); i++) {
			pst = conn
					.prepareStatement("delete from participant where participant.username = ? and participant.evid = ?");
			pst.setString(1, users.get(i).getUsername());
			pst.setInt(2, eventid);
			makeUpdate(pst);
		}

		// TODO Auto-generated method stub

	}

	@Override
	public void addParticipants(List<UserRef> users, int eventid)
			throws SQLException {

		PreparedStatement pst;
		for (int i = 0; i < users.size(); i++) {
			pst = conn
					.prepareStatement("insert into participant values(?, ?, ?)");
			pst.setString(1, users.get(i).getUsername());
			pst.setInt(2, eventid);
			pst.setString(3, "Waiting");
			makeUpdate(pst);
		}

	}

	@Override
	public void changeEvent(Event event) throws SQLException {
		PreparedStatement pst = conn
				.prepareStatement("update event set eventdescription = ?, leader = ?, name = ?, starttime = ?, timelength = ?, place = ?, roomid = ?, type = ?, status = ? where evid = ?");
		pst.setString(1, event.getEventdescription());
		pst.setString(2, event.getEventowner().getUsername());
		pst.setString(2, "Per");
		pst.setString(3, event.getEventname());
		pst.setString(4, event.getDateText());
		pst.setInt(5, event.getTimeLength());
		pst.setString(6, event.getPlace());
		pst.setString(7, event.getRoom().getRoomid());
		pst.setString(8, event.getType().toString());
		pst.setString(9, event.getStatus());
		pst.setInt(10, event.getEventID());
		makeUpdate(pst);
		// TODO Auto-generated method stub

	}

	public int getLatestEventId() throws SQLException {
		PreparedStatement pst = conn
				.prepareStatement("select LAST_INSERT_ID()");
		ResultSet rs = makeSingleQuery(pst);
		rs.next();
		return rs.getInt(1);
	}

	public void insertEvent(Event event) throws SQLException {
		PreparedStatement pst = conn
				.prepareStatement("insert into event (eventdescription, leader, name, starttime, timelength, place, roomid, type, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
		pst.setString(1, event.getEventdescription());
		pst.setString(2, event.getEventowner().getUsername());
		pst.setString(3, event.getEventname());
		pst.setString(4, event.getDateText());
		pst.setInt(5, event.getTimeLength());
		pst.setString(6, event.getPlace());
		pst.setString(7, event.getRoom().getRoomid());
		pst.setString(8, event.getType().toString());
		pst.setString(9, event.getStatus());
		makeUpdate(pst);

		event.setEventID(getLatestEventId());
	}

	public void deleteEvent(Event event) throws SQLException {
		PreparedStatement pst = conn
				.prepareStatement("delete from event where eventId = ?");
		pst.setInt(1, event.getEventID());
		makeUpdate(pst);
		pst = conn
				.prepareStatement("delete from participants where eventId = ?");
		pst.setInt(1, event.getEventID());
		makeUpdate(pst);
	}

	@Override
	public boolean isValidUser(String username, String password)
			throws SQLException {
		PreparedStatement pst = conn
				.prepareStatement("select from user where user.username = ? and user.password = ?");
		pst.setString(1, username);
		pst.setString(2, password);
		int count = 0;
		ResultSet rs = makeSingleQuery(pst);
		while (rs.next()) {
			count++;
		}
		return count == 1 ? true : false;
	}

	@Override
	public List<Event> getEvents(String username) throws SQLException {
		List<Event> events = new ArrayList<Event>();
		PreparedStatement pst = conn
				.prepareStatement("select from event, participant where event.evid = participant.evid and participant.username = ?");
		pst.setString(1, username);
		ResultSet rs = makeSingleQuery(pst);
		while (rs.next()) {
			events.add(new Event(rs.getInt("evid"), rs
					.getString("eventdescription"), rs.getString("name"), rs
					.getDate("starttime"), rs.getInt("timelength"), rs
					.getString("type"), rs.getString("place"), rs
					.getString("room"), rs.getString("status"), rs
					.getString("eventOwner")));
		}
		return events;
	}

}
