package com.dbalota.show.services;

import com.dbalota.show.models.Event;
import com.dbalota.show.models.User;

import java.util.Date;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public interface DiscountService {
    double getDiscount(User user, Event event, Date date);

}
