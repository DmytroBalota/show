package com.dbalota.show.dao;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;

public class UserDao {
	private static Set<User> users = new HashSet<User>();

	public void add(User user) {
		users.add(user);
	}

	public void remove(User user) {
		users.remove(user);
	}

	public User getById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public User getUserByEmail(String email) {
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

	public List<User> getUsersByName(String name) {
		List<User> list = new LinkedList<User>();
		for (User user : users) {
			if (user.getFirstName().equals(name)) {
				list.add(user);
			}
		}
		return list;
	}
}
