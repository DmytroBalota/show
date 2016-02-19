package com.dbalota.show.services;

import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;

import java.util.List;

public interface UserService {
    boolean register(User user);

    void remove(User user);

    User getById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    // FIXME: 0.5% never used
    List<Ticket> getBookedTickets(User user);
}
