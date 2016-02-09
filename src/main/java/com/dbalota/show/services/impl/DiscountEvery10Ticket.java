package com.dbalota.show.services.impl;

import com.dbalota.show.models.Event;
import com.dbalota.show.models.User;
import com.dbalota.show.services.DiscountStrategy;

import java.util.Date;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public class DiscountEvery10Ticket implements DiscountStrategy {
    public double getDiscountPercentage(User user, Event event, Date date) {

        if ((user.getTickets().size() + 1) % 10 == 0) {
            return 50;
        }
        return 0;
    }
}
