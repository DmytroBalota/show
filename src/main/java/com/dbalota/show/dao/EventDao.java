package com.dbalota.show.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;

public class EventDao {

	private static Set<Event> events = new HashSet<Event>();

	public void add(Event event) {
		events.add(event);
	}

	public void remove(Event event) {
		events.remove(event);
	}

	public Set<Event> getForDateRange(Date from, Date to) {
		Set<Event> list = new HashSet<Event>();

		for (Event event : events) {
			for (Date date : event.getAuditoriumAndDates().keySet()) {
				if (date.getTime() >= from.getTime() && date.getTime() <= to.getTime()) {
					list.add(event);
					break;
				}
			}
		}

		return list;
	}

	public Set<Event> getNextEvents(Date to) {
		long now = System.currentTimeMillis();
		Set<Event> list = new HashSet<Event>();

		for (Event event : events) {
			for (Date date : event.getAuditoriumAndDates().keySet()) {
				if (date.getTime() >= now && date.getTime() <= to.getTime()) {
					list.add(event);
					break;
				}
			}
		}

		return list;
	}

	public Set<Event> getAll() {
		return events;
	}

	public Event getByName(String name) {
		for (Event event : events) {
			if (event.getName().equals(name)) {
				return event;
			}

		}
		return null;
	}

}
