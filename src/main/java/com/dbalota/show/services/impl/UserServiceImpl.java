package com.dbalota.show.services.impl;

import com.dbalota.show.models.User;
import com.dbalota.show.services.UserService;

public class UserServiceImpl implements UserService {

	public User getById(long id) {
		return new User("Dmytro", "B");
	}

}
