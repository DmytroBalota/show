package com.dbalota.show.dao.mapper;

import com.dbalota.show.models.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Balota on 19/02/2016.
 */
public class TicketRowMapper implements RowMapper<Ticket> {
    @Override

    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setEventId(rs.getInt("event_id"));
        ticket.setId(rs.getInt("id"));
        ticket.setPrice(rs.getDouble("price"));
        ticket.setDate(rs.getDate("date"));
        ticket.setAuditoriumName(rs.getString("auditoriumName"));
        ticket.setSeat(rs.getInt("seat"));

        return ticket;
    }

}
