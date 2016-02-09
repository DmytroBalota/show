package com.dbalota.show.services;

import com.dbalota.show.models.Event;
import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public interface BookingService {

    double getTicketPrice(Event event, Date date, Set<Integer> seats, User user);

    boolean bookTicket(User user, Ticket ticket);

    List<Ticket> getTicketsForEvent(Event event, Date date);

}
