package com.dbalota.show.services.impl;

import java.util.Date;
import java.util.List;

import com.dbalota.show.models.Event;
import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import com.dbalota.show.services.BookingService;

public class BookingServiceImpl implements BookingService {

	public double getTicketPrice(Event event, Date date, int seatsNumber, User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean bookTicket(User user, Ticket ticket) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Ticket> getTicketsForEvent(Event event, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
