package com.dbalota.show.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.dbalota.show.dao.BookingDao;
import com.dbalota.show.exceptions.NoEnoughSeatsException;
import com.dbalota.show.exceptions.NotEnoughMoneyException;
import com.dbalota.show.exceptions.SeatIsOccupiedException;
import com.dbalota.show.models.*;
import com.dbalota.show.services.*;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingServiceImpl implements BookingService {

    private DiscountService discountService;
    private BookingDao bookingDao;
    private AuditoriumService auditoriumService;
    private EventService eventService;
    @Autowired
    private UserAccountService userAccountService;

    private BookingServiceImpl() {
    }

    public double getTicketPrice(Event event, Date date, Integer seat) {
        double calculatedPrice = 0;
        double price = event.getPrice();
        if (event.getRating() == Event.Rating.HIGH) {
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
        verifySeat(ticket);

        double discount = discountService.getDiscount(user, ticket.getEvent(), ticket.getDate());
        double price = ticket.getPrice() - ticket.getPrice() * (discount / 100);

        withdrawMoney(user, price);

        ticket.setPrice(price);
        ticket.setUserId(user.getId());
        bookingDao.bookTicket(ticket);

        return true;
    }

    private void verifySeat(Ticket ticket) {
        List<Ticket> tickets = bookingDao.getPurchasedTickets(ticket.getAuditorium(), ticket.getDate());
        if (null != tickets && tickets.size() == ticket.getAuditorium().getSeatsNumber()) {
            throw new NoEnoughSeatsException("There are no enough seats in the auditorium "
                    + ticket.getAuditorium().getName());
        }
        for (Ticket t : tickets) {
            if (t.getSeat() == ticket.getSeat()) {
                throw new SeatIsOccupiedException(String.format("Seat %s is occupied.", t.getSeat()));
            }
        }
    }

    private void withdrawMoney(User user, double price) {
        List<UserAccount> accounts = userAccountService.getUserAccounts(user.getId());
        boolean withdrawFail = true;
        for (UserAccount ua : accounts) {
            if (ua.getBalance() >= price) {
                userAccountService.withdrawAccount(ua.getAccountNumber(), price);
                withdrawFail = false;
                break;
            }
        }
        if (withdrawFail) {
            throw new NotEnoughMoneyException("User has not enough money on his accounts");
        }
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

    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }
}
