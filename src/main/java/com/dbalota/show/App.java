package com.dbalota.show;

import com.dbalota.show.services.UserService;

/**
 * Hello world!
 *
 */
public class App {
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
}
