package com.dbalota.show.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dbalota.show.dao.mapper.EventRowMapper;
import com.dbalota.show.dao.mapper.UserRowMapper;
import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;
import org.springframework.jdbc.core.JdbcTemplate;

public class EventDao {

    private JdbcTemplate jdbcTemplate;

    public Boolean eventNameExists(String eventName) {
        return jdbcTemplate.queryForObject("select count(*) from events where name = ?",
                new Object[]{eventName}, Boolean.class);
    }

    public void add(Event event) {
        jdbcTemplate.update("insert into events (name, price, duration, raiting) values(?,?,?,?)", event.getName()
                , event.getPrice(), event.getDuration(), event.getRaiting().toString());
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
        return jdbcTemplate.query("select * from events",
                new EventRowMapper());
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
}
