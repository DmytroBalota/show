package com.dbalota.show.services;

import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;

import java.util.Date;
import java.util.List;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public interface EventService {
    Event create();

    void remove(Event event);

    List<Event> getForDateRange(Date from, Date to);

    List<Event> getNextEvents(Date to);

    void assignAuditorium(Event event, Auditorium auditorium, Date date);
}
