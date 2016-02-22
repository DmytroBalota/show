package com.dbalota.show.dao.mapper;

import com.dbalota.show.models.Event;
import com.dbalota.show.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Balota on 18/02/2016.
 */
public class EventRowMapper implements RowMapper<Event> {
    public Event mapRow(ResultSet rs,
                        int rowNum) throws SQLException {
        Event event = new Event(rs.getString("name"));
        event.setPrice(rs.getDouble("price"));
        event.setId(rs.getInt("id"));
        event.setDuration(rs.getInt("duration"));
        event.setRaiting(Event.Raiting.valueOf(rs.getString("raiting")));
        return event;
    }
}
