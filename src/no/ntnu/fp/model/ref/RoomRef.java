package no.ntnu.fp.model.ref;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.fp.model.Room;

public class RoomRef {

	private String roomid;

	private static Map<String, RefCounter<Room>> instances = new HashMap<String, RefCounter<Room>>();

	public RoomRef(String roomid) {
		this.roomid = roomid;
		RefCounter<Room> r = instances.get(this.roomid);
		if (r == null) {
			r = new RefCounter<Room>(null);
			instances.put(roomid, r);
		} else {
			r.ref();
		}
	}

	public RoomRef(Room room) {
		this.roomid = room.getRoomID();
		RefCounter<Room> r = instances.get(this.roomid);
		if (r == null) {
			r = new RefCounter<Room>(room);
			instances.put(roomid, r);
		} else {
			r.set(room);
			r.ref();
		}
	}

	@Override
	public void finalize() {
		RefCounter<Room> r = instances.get(roomid);
		if (r != null) {
			if (r.unref()) {
				instances.remove(roomid);
			}
		}
	}

	public Room get() {
		Room user = instances.get(roomid).get();
		return user;
	}

	public String getRoomid() {
		return roomid;
	}
}
