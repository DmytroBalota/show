package com.dbalota.show.services;

import java.util.Date;
import java.util.Set;

import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public interface EventService {
    void create(Event event);

    void remove(Event event);
    
    Event getByName(String name);
    
    Set<Event> getAll();

    Set<Event> getForDateRange(Date from, Date to);

    Set<Event> getNextEvents(Date to);

    boolean assignAuditorium(Event event, Auditorium auditorium, Date date);
}
