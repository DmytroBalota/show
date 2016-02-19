package com.dbalota.show.dao.mapper;

import com.dbalota.show.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Balota on 18/02/2016.
 */
public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet rs,
                       int rowNum) throws SQLException {
        return new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getDate("birthday"), rs.getLong("id"));
    }
}
