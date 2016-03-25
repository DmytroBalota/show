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
        User user = new User(rs.getString("firstName"), rs.getString("lastName"));
        user.setBirthday(rs.getDate("birthday"));
        user.setEmail(rs.getString("email"));
        user.setId(rs.getInt("id"));
        user.setPassword(rs.getString("password"));
        user.setRoles(rs.getString("roles"));
        return user;
    }
}
