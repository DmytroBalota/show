package com.dbalota.show.services;

import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;

import java.util.List;

public interface UserService {
    void register(User user);

    // FIXME: 0.5% never used
    void remove(User user);

    // FIXME: 0.5% never used
    User getById(long id);

    // FIXME: 0.5% never used
    User getUserByEmail(String email);

    // FIXME: 0.5% never used
    List<User> getUsersByName(String name);

    // FIXME: 0.5% never used
    List<Ticket> getBookedTickets(User user);
}
