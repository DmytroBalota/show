package com.dbalota.show.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Balota on 15/02/2016.
 */
@Aspect
@Component
public class CounterAspect {


    public static final String GET_TICKET_PRICE = "getTicketPrice";
    public static final String BOOK_TICKET = "bookTicket";
    public static final String GET_EVENT_BY_NAME = "getEventByName";

    private static Map<String, Integer> counters = new HashMap<>();


    static {
        counters.put(GET_EVENT_BY_NAME, 0);
        counters.put(GET_TICKET_PRICE, 0);
        counters.put(BOOK_TICKET, 0);
    }


    @Pointcut("execution(com.dbalota.show.models.Event com.dbalota.show.services.EventService.getByName(..))")
    private void getEventByName() {
    }

    @Pointcut("execution(double com.dbalota.show.services.BookingService.getTicketPrice(..)) ")
    private void getTicketPrice() {
    }

    @Pointcut("execution(* com.dbalota.show.services.BookingService.bookTicket(..)) ")
    private void bookTicket() {
    }

    @Before("getEventByName()")
    public void countGetEventByName(JoinPoint joinPoint) {
        counters.put(GET_EVENT_BY_NAME, counters.get(GET_EVENT_BY_NAME) + 1);
    }

    @Before("bookTicket())")
    public void countBookTicket(JoinPoint joinPoint) {
        counters.put(BOOK_TICKET, counters.get(BOOK_TICKET) + 1);
    }

    @Before("getTicketPrice())")
    public void countGetTicketPrice(JoinPoint joinPoint) {
        counters.put(GET_TICKET_PRICE, counters.get(GET_TICKET_PRICE) + 1);
    }


    public static Map<String, Integer> getCounters() {
        return new HashMap<>(counters);
    }
}
