package com.dbalota.show.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.dbalota.show.dao.BookingDao;
import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;
import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import com.dbalota.show.services.BookingService;
import com.dbalota.show.services.DiscountService;

public class BookingServiceImpl implements BookingService {

    private DiscountService discountService;
    private BookingDao bookingDao;

    BookingServiceImpl(DiscountService discountService, BookingDao bookingDao) {
        this.discountService = discountService;
        this.bookingDao = bookingDao;
    }

    public double getTicketPrice(Event event, Date date, Integer seat, User user) {
        double calculatedPrice = 0;
        double discount = discountService.getDiscount(user, event, date);
        double price = event.getPrice();
        if (event.getRaiting() == Event.Raiting.HIGH) {
            price = price * 1.2;
        }
        Set<Integer> vipSeats = event.getAuditoriumAndDates().get(date).getVipSeats();

        if (vipSeats.contains(seat)) {
            calculatedPrice = calculatedPrice + price * 2;
        } else {
            calculatedPrice += price;
        }

        calculatedPrice = calculatedPrice - calculatedPrice * (discount / 100);

        return calculatedPrice;
    }

    public double getTicketPrice(Event event, Date date, Set<Integer> seats, User user) {
        double calculatedPrice = 0;
        for (Integer seat : seats) {
            calculatedPrice += getTicketPrice(event, date, seat, user);
        }
        return calculatedPrice;
    }

    public boolean bookTicket(User user, Ticket ticket) {
        if (isAuditoriumFull(ticket.getAuditorium(), ticket.getDate())) return false;
        user.getTickets().add(ticket);
        bookingDao.bookTicket(ticket);
        return true;
    }

    private boolean isAuditoriumFull(Auditorium auditorium, Date date) {
        List<Ticket> tickets = bookingDao.getPurchasedTickets(auditorium, date);
        if (null != tickets && tickets.size() == auditorium.getSeatsNumber()) {
            return true;
        }
        return false;
    }

    public List<Ticket> getTicketsForEvent(Event event, Date date) {
        return bookingDao.getPurchasedTickets(event.getAuditoriumAndDates().get(date), date);
    }

}
