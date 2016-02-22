package com.dbalota.show.services.impl;

import com.dbalota.show.dao.BookingDao;
import com.dbalota.show.models.Event;
import com.dbalota.show.models.User;
import com.dbalota.show.services.DiscountStrategy;

import java.util.Date;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public class DiscountEvery10Ticket implements DiscountStrategy {
    private BookingDao bookingDao;

    public double getDiscountPercentage(User user, Event event, Date date) {

        if ((bookingDao.getUsersTicketAmount(user.getId()) + 1) % 10 == 0) {
            return 50;
        }
        return 0;
    }

    public void setBookingDao(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }
}
