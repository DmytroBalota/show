package com.dbalota.show.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;

/**
 * Created by Dmytro_Balota on 2/5/2016.
 */
public interface EventService {
    boolean create(Event event);

    void remove(Event event);

    Event getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(Date from, Date to);

    // FIXME: 0.5% never used
    List<Event> getNextEvents(Date to);

    boolean assignAuditorium(Event event, Auditorium auditorium, Date date);

    void deleteAssignment(long eventId, Date dateTime);

    String getAuditoriumName(long eventId, Date date);
}
