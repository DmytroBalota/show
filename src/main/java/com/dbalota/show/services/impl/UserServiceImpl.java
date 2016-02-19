package com.dbalota.show.services.impl;

import com.dbalota.show.dao.UserDao;
import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import com.dbalota.show.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean register(User user) {
        if (userDao.userExists(user.getFirstName(), user.getLastName())) {
            return false;
        }
        userDao.add(user);
        return true;
    }

    public void remove(User user) {
        userDao.remove(user);
    }

    public User getById(long id) {
        return userDao.getById(id);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userDao.getUsersByName(name);
    }

    public List<Ticket> getBookedTickets(User user) {
        return user.getTickets();
    }

}
