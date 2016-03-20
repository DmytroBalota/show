package com.dbalota.show.dao;

import java.util.*;

import com.dbalota.show.dao.mapper.EventRowMapper;
import com.dbalota.show.models.Event;
import org.springframework.jdbc.core.JdbcTemplate;

public class EventDao {

    private JdbcTemplate jdbcTemplate;

    public Boolean eventNameExists(String eventName) {
        return jdbcTemplate.queryForObject("select count(*) from events where name = ?",
                new Object[]{eventName}, Boolean.class);
    }

    public void add(Event event) {
        jdbcTemplate.update("insert into events (name, price, duration, rating) values(?,?,?,?)", event.getName()
                , event.getPrice(), event.getDuration(), event.getRating().toString());
    }

    public void remove(Event event) {
        jdbcTemplate.update("delete from events where id = ?", new Object[]{event.getId()});
    }

    public List<Event> getForDateRange(Date from, Date to) {
        return jdbcTemplate.query("select * from events e left join event_date_location edl on id = event_id where date between ? and ? ",
                new Object[]{from, to}, new EventRowMapper());
    }

    public List<Event> getNextEvents(Date to) {
        return jdbcTemplate.query("select * from events e left join event_date_location edl on id = event_id where date between ? and ? ",
                new Object[]{new Date(), to}, new EventRowMapper());
    }

    public List<Event> getAll() {
        List<Event> events = jdbcTemplate.query("select * from events",
                new EventRowMapper());
        for (Event e : events) {
            e.setDatesLocations(jdbcTemplate.query("select date, auditoriumName from event_date_location where event_id = ?",
                    new Object[]{e.getId()},
                    rs -> {
                        List<Event.DateLocation> datesLocations = new ArrayList<>();
                        while (rs.next()) {
                            datesLocations.add(new Event.DateLocation(rs.getString("date"), rs.getString("auditoriumName")));
                        }
                        return datesLocations;
                    }));
        }
        return events;
    }

    public Event getByName(String name) {
        return jdbcTemplate.queryForObject("select * from events where name = ? ",
                new Object[]{name}, new EventRowMapper());
    }

    public List<Date> getEventDates(long eventId, String auditoriumName) {
        return jdbcTemplate.queryForList("select date from event_date_location where event_id = ? and auditoriumName = ?", new Object[]{eventId, auditoriumName},
                Date.class);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void bookAuditorium(long event_id, Date date, String auditoriumName) {
        jdbcTemplate.update("insert into event_date_location (event_id, date, auditoriumName) values(?,?,?)", event_id, date, auditoriumName);
    }

    public String getAuditoriumName(long eventId, Date date) {
        return jdbcTemplate.queryForObject("select auditoriumName from event_date_location where event_id = ? and date = ?",
                new Object[]{eventId, date}, String.class);
    }

    public void deleteAssignment(long eventId, Date dateTime) {
        jdbcTemplate.update("delete from event_date_location where event_id = ? and date = ?", new Object[]{eventId, dateTime});
    }
}
