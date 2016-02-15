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

    // FIXME: 0.5% never used
    void remove(Event event);

    // FIXME: 0.5% never used
    Event getByName(String name);

    // FIXME: 0.5% never used
    Set<Event> getAll();

    // FIXME: 0.5% never used
    Set<Event> getForDateRange(Date from, Date to);

    // FIXME: 0.5% never used
    Set<Event> getNextEvents(Date to);

    boolean assignAuditorium(Event event, Auditorium auditorium, Date date);
}
