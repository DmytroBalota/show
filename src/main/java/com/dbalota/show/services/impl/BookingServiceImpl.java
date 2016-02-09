package com.dbalota.show.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.dbalota.show.dao.BookingDao;
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

    public double getTicketPrice(Event event, Date date, Set<Integer> seats, User user) {
        double calculatedPrice = 0;
        double discount = discountService.getDiscount(user, event, date);
        double price = event.getPrice();
        if (event.getRaiting() == Event.Raiting.HIGH) {
            price = price * 1.2;
        }
        Set<Integer> vipSeats = event.getAuditoriumAndDates().get(date).getVipSeats();

        for (Integer seat : seats) {
            if (vipSeats.contains(seat)) {
                calculatedPrice = calculatedPrice + price * 2;
            } else {
                calculatedPrice += price;
            }
        }

        calculatedPrice = calculatedPrice - calculatedPrice * (discount / 100);

        return calculatedPrice;
    }

    public boolean bookTicket(User user, Ticket ticket) {

        user.getTickets().add(ticket);
        bookingDao.bookTicket(ticket);
        return true;
    }

    public List<Ticket> getTicketsForEvent(Event event, Date date) {
        return bookingDao.getPurchasedTickets(event.getAuditoriumAndDates().get(date), date);
    }

}
