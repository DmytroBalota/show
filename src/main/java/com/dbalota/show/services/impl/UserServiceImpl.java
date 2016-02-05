package com.dbalota.show.services.impl;

import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import com.dbalota.show.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

	public void register(User user) {

	}

	public void remove(User user) {

	}

	public User getById(long id) {
		return new User("Dmytro", "B");
	}

	public User getUserByEmail(String email) {
		return null;
	}

	public List<User> getUsersByName(String name) {
		return null;
	}

	public List<Ticket> getBookedTickets() {
		return null;
	}

}
