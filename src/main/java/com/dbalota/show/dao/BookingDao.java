package com.dbalota.show.dao;

import com.dbalota.show.dao.mapper.EventRowMapper;
import com.dbalota.show.dao.mapper.TicketRowMapper;
import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.*;

/**
 * Created by dmytro_balota on 2/9/2016.
 */
public class BookingDao {

    private JdbcTemplate jdbcTemplate;

    public List<Ticket> getPurchasedTickets(Auditorium auditorium, Date date) {
        return jdbcTemplate.query("select * from tickets",
                new TicketRowMapper());
    }

    public void bookTicket(Ticket ticket) {
        jdbcTemplate.update("insert into tickets (event_id, date, auditoriumName, seat, price) values(?,?,?,?,?)", ticket.getEventId()
                , ticket.getDate(), ticket.getAuditoriumName(), ticket.getSeat(), ticket.getPrice());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
