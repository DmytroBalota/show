package com.dbalota.show.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Balota on 15/02/2016.
 */
@Aspect
@Component
public class CounterAspect {


    private static Map<String, Integer> counters = new HashMap<>();

    public static final String GET_EVENT_BY_NAME = "getEventByName";

    static {
        counters.put(GET_EVENT_BY_NAME, 0);
    }


    @Pointcut("execution(com.dbalota.show.models.Event com.dbalota.show.services.EventService.getByName(..))")
    private void getEventByName() {
    }

    @Before("getEventByName()")
    public void countGetEventByName(JoinPoint joinPoint) {
        counters.put(GET_EVENT_BY_NAME, counters.get(GET_EVENT_BY_NAME) + 1);
    }

}
