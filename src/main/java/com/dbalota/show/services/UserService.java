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

    List<Ticket> getBookedTickets(User user);

    List<User> getAll();

    User getUserByNameSurname(String firstName, String lastName);

    void edit(User user);
}
