package com.dbalota.show.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.dbalota.show.dao.BookingDao;
import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;
import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import com.dbalota.show.services.AuditoriumService;
import com.dbalota.show.services.BookingService;
import com.dbalota.show.services.DiscountService;
import com.dbalota.show.services.EventService;

public class BookingServiceImpl implements BookingService {

    private DiscountService discountService;
    private BookingDao bookingDao;
    private AuditoriumService auditoriumService;
    private EventService eventService;

    private BookingServiceImpl() {
    }

    public double getTicketPrice(Event event, Date date, Integer seat) {
        double calculatedPrice = 0;
        double price = event.getPrice();
        if (event.getRaiting() == Event.Raiting.HIGH) {
            price = price * 1.2;
        }
        Set<Integer> vipSeats = auditoriumService.getAuditorium(eventService.getAuditoriumName(event.getId(), date)).getVipSeats();

        if (vipSeats.contains(seat)) {
            calculatedPrice = calculatedPrice + price * 2;
        } else {
            calculatedPrice += price;
        }

        return calculatedPrice;
    }

    public double getTicketPrice(Event event, Date date, Set<Integer> seats) {
        double calculatedPrice = 0;
        for (Integer seat : seats) {
            calculatedPrice += getTicketPrice(event, date, seat);
        }
        return calculatedPrice;
    }

    public boolean bookTicket(User user, Ticket ticket) {
        if (isAuditoriumFull(ticket.getAuditorium(), ticket.getDate())) {
            return false;
        }
        double discount = discountService.getDiscount(user, ticket.getEvent(), ticket.getDate());
        ticket.setPrice(ticket.getPrice() - ticket.getPrice() * (discount / 100));
        ticket.setUserId(user.getId());
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
        return bookingDao.getPurchasedTickets(auditoriumService.getAuditorium(eventService.getAuditoriumName(event.getId(), date)), date);
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void setBookingDao(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public void setAuditoriumService(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
