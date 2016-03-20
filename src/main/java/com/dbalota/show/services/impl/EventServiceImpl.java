package com.dbalota.show.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.dbalota.show.dao.EventDao;
import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;
import com.dbalota.show.services.EventService;

public class EventServiceImpl implements EventService {
    private EventDao eventDao;

    private EventServiceImpl(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public boolean create(Event event) {
        if (eventDao.eventNameExists(event.getName())) {
            return false;
        }
        eventDao.add(event);
        return true;
    }

    public void remove(Event event) {
        eventDao.remove(event);
    }

    public Event getByName(String name) {
        return eventDao.getByName(name);
    }

    public List<Event> getAll() {
        return eventDao.getAll();
    }

    public List<Event> getForDateRange(Date from, Date to) {
        return eventDao.getForDateRange(from, to);
    }

    public List<Event> getNextEvents(Date to) {
        return eventDao.getNextEvents(to);
    }

    public boolean assignAuditorium(Event event, Auditorium auditorium, Date date) {
        // check if auditorium is free
        for (Event e : eventDao.getAll()) {

            for (Date ed : eventDao.getEventDates(event.getId(), auditorium.getName())) {
                // FIXME: 0.5% too complex condition
                if ((date.getTime() >= ed.getTime()
                        && date.getTime() <= (ed.getTime() + e.getDuration()))
                        || (date.getTime() + event.getDuration()) >= ed.getTime() && (date.getTime()
                        + event.getDuration()) <= (ed.getTime() + e.getDuration())) {
                    return false;
                }
            }

        }

        eventDao.bookAuditorium(event.getId(), date, auditorium.getName());

        return true;
    }

    @Override
    public void deleteAssignment(long eventId, Date dateTime) {
        eventDao.deleteAssignment(eventId, dateTime);
    }

    @Override
    public String getAuditoriumName(long eventId, Date date) {
        return eventDao.getAuditoriumName(eventId, date);
    }

}
