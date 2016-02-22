package com.dbalota.show.aspects;

import com.dbalota.show.dao.CounterAspectDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    private CounterAspectDao counterAspectDao;

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
        if(counterAspectDao.counterExists(GET_EVENT_BY_NAME)){
            counterAspectDao.incrementCounter(GET_EVENT_BY_NAME);
        }else{
            counterAspectDao.addCounter(GET_EVENT_BY_NAME, 1);
        }
    }

    @Before("bookTicket())")
    public void countBookTicket(JoinPoint joinPoint) {
        if(counterAspectDao.counterExists(BOOK_TICKET)){
            counterAspectDao.incrementCounter(BOOK_TICKET);
        }else{
            counterAspectDao.addCounter(BOOK_TICKET, 1);
        }
    }

    @Before("getTicketPrice())")
    public void countGetTicketPrice(JoinPoint joinPoint) {
        if(counterAspectDao.counterExists(GET_TICKET_PRICE)){
            counterAspectDao.incrementCounter(GET_TICKET_PRICE);
        }else{
            counterAspectDao.addCounter(GET_TICKET_PRICE, 1);
        }
    }


    public Integer getCounterNumber(String name) {
        return counterAspectDao.getCounterNumber(name);
    }

    public void setCounterAspectDao(CounterAspectDao counterAspectDao) {
        this.counterAspectDao = counterAspectDao;
    }
}
