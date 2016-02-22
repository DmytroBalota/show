package com.dbalota.show.aspects;

import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by Dmytro_Balota on 16/02/2016.
 */

@Aspect
@Component
public class LuckyWinnerAspect {

    private Random r = new Random();

    @Pointcut("execution(* com.dbalota.show.services.BookingService.bookTicket(..)) ")
    private void bookTicket() {
    }

    @Around("bookTicket()")
    public Object isLuckyUser(ProceedingJoinPoint pjp) throws Throwable {

        Boolean success = (Boolean) pjp.proceed();

        if (success && r.nextInt() % 13 == 0) {
            Ticket ticket = (Ticket) pjp.getArgs()[1];
            ticket.setPrice(0);
        }
        return success;

    }

}
