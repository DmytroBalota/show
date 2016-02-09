package com.dbalota.show.services.impl;

import com.dbalota.show.models.Event;
import com.dbalota.show.models.User;
import com.dbalota.show.services.DiscountService;
import com.dbalota.show.services.DiscountStrategy;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public class DiscountBirthday implements DiscountStrategy {

    @Override
    public double getDiscountPercentage(User user, Event event, Date date) {
        Date birthday = user.getBirthday();
        Calendar birthdayC = Calendar.getInstance();
        birthdayC.setTime(birthday);
        Calendar dateC = Calendar.getInstance();
        dateC.setTime(date);
        if (birthdayC.get(Calendar.DATE) == dateC.get(Calendar.DATE)) {
            return 5;
        }

        return 0;
    }
}
