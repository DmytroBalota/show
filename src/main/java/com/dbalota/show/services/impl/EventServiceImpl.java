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

	public void create(Event event) {
		eventDao.add(event);
	}

	public void remove(Event event) {
		eventDao.remove(event);
	}

	public Event getByName(String name) {
		return eventDao.getByName(name);
	}

	public Set<Event> getAll() {
		return eventDao.getAll();
	}

	public Set<Event> getForDateRange(Date from, Date to) {
		return eventDao.getForDateRange(from, to);
	}

	public Set<Event> getNextEvents(Date to) {
		return eventDao.getNextEvents(to);
	}

	public boolean assignAuditorium(Event event, Auditorium auditorium, Date date) {
		// check if auditorium is free
		for (Event e : eventDao.getAll()) {

			for (Entry<Date, Auditorium> entry : e.getAuditoriumAndDates().entrySet()) {
				if (entry.getValue().equals(auditorium))
					if ((date.getTime() >= entry.getKey().getTime()
							&& date.getTime() <= (entry.getKey().getTime() + e.getDuration()))
							|| (date.getTime() + event.getDuration()) >= entry.getKey().getTime() && (date.getTime()
									+ event.getDuration()) <= (entry.getKey().getTime() + e.getDuration())) {
						return false;
					}
			}

		}
		// check if event does not happen more then one time per specific date
		for (Date d : event.getAuditoriumAndDates().keySet()) {
			if ((date.getTime() >= d.getTime() && date.getTime() <= (d.getTime() + event.getDuration()))
					|| ((date.getTime() + event.getDuration()) >= d.getTime()
							&& (date.getTime() + event.getDuration()) <= (d.getTime() + event.getDuration()))) {
				return false;
			}
		}

		event.getAuditoriumAndDates().put(date, auditorium);

		return true;
	}

}
