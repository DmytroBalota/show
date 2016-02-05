package com.dbalota.show.services;

import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;

import java.util.List;

public interface UserService {
    void register(User user);

    void remove(User user);

    User getById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    List<Ticket> getBookedTickets();
}
