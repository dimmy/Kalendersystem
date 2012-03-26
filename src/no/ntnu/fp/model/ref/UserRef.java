package no.ntnu.fp.model.ref;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.fp.model.User;

public class UserRef {

	private String username;
	private User user;

	private static Map<String, RefCounter<User> > instances;

	public UserRef(String username) {
		this.username = username;
		user = null;
	}

	private UserRef(User user) {
		this.username = user.getUsername();
		this.user = user;
		ref();
	}
	
	@Override
	public void finalize() {
		unref();
	}
	
	public static UserRef getRef(String username) {
		User u = instances.get(username).get();
		if (u == null) {
			u = new User();
			u.setUsername(username);
			UserRef r = new UserRef(u);
			return r;
		}
		else {
			return new UserRef(u);
		}
	}
	
	private void ref() {
		RefCounter r = instances.get(username);
		if (r == null) {
			instances.put(username, new RefCounter<User>(user));
		}
		else {
			r.ref();
		}
	}
	
	private void unref() {
		RefCounter r = instances.get(username);
		if (r != null) {
			if (r.unref()) {
				instances.remove(username);
			}
		}
	}
}
