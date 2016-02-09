package com.dbalota.show.services.impl;

import com.dbalota.show.models.Event;
import com.dbalota.show.models.User;
import com.dbalota.show.services.DiscountService;
import com.dbalota.show.services.DiscountStrategy;

import java.util.Date;
import java.util.List;

/**
 * Created by dmytro_balota on 2/9/2016.
 */
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> discountStrategies;

    DiscountServiceImpl(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    @Override
    public double getDiscount(User user, Event event, Date date) {
        double maxDiscount = 0;
        for (DiscountStrategy ds : discountStrategies) {
            double percentage = ds.getDiscountPercentage(user, event, date);
            if (percentage > maxDiscount) {
                maxDiscount = percentage;
            }
        }
        return maxDiscount;
    }
}
