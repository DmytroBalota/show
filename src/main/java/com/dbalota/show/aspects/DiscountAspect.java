package com.dbalota.show.aspects;

import com.dbalota.show.models.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Balota on 16/02/2016.
 */

@Aspect
@Component
public class DiscountAspect {

    private static Map<String, Integer> usersDiscountCount = new HashMap<>();

    @Pointcut("execution(double com.dbalota.show.services.DiscountService.getDiscount(..)) )")
    private void getDiscount() {
    }

    @Around("getDiscount()")
    public Object countSuccessDiscounts(ProceedingJoinPoint pjp) throws Throwable {
        double discount = (Double) pjp.proceed();

        if (discount > 0) {
            User user = (User) pjp.getArgs()[0];
            if (usersDiscountCount.containsKey(user.toString())) {
                usersDiscountCount.put(user.toString(), usersDiscountCount.get(user.toString()) + 1);
            } else {
                usersDiscountCount.put(user.toString(), 1);
            }
        }
        return discount;
    }

    public static Map<String, Integer> getUsersDiscountCount() {
        return new HashMap<>(usersDiscountCount);
    }
}
