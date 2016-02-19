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
        return new Event(rs.getString("name"), rs.getDouble("price"), rs.getInt("duration"), Event.Raiting.valueOf(rs.getString("raiting")), rs.getInt("id"));
    }
}
