package no.ntnu.fp.model.ref;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.fp.model.User;

public class UserRef {

	private String username;

	private static Map<String, RefCounter<User>> instances = new HashMap<String, RefCounter<User>>();

	public UserRef(String username) {
		this.username = username;
		RefCounter<User> r = instances.get(this.username);
		if (r == null) {
			r = new RefCounter<User>(null);
			instances.put(username, r);
		} else {
			r.ref();
		}
	}

	public UserRef(User user) {
		this.username = user.getUsername();
		RefCounter<User> r = instances.get(this.username);
		if (r == null) {
			r = new RefCounter<User>(user);
			instances.put(username, r);
		} else {
			r.set(user);
			r.ref();
		}
	}

	@Override
	public void finalize() {
		RefCounter<User> r = instances.get(username);
		if (r != null) {
			if (r.unref()) {
				instances.remove(username);
			}
		}
	}

	public User get() {
		User user = instances.get(username).get();
		return user;
	}
}
