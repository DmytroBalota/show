package com.dbalota.show.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.dbalota.show.dao.mapper.UserRowMapper;
import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public Boolean userExists(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("select count(*) from users where firstName = ? and lastName = ?",
                new Object[]{firstName, lastName}, Boolean.class);
    }

    public void add(User user) {
        jdbcTemplate.update("insert into users (firstName, lastName, email, birthday) values(?,?,?,?)", user.getFirstName()
                , user.getLastName(), user.getEmail(), user.getBirthday());
    }

    public void remove(User user) {
        jdbcTemplate.update("delete from users where id = ?", new Object[]{user.getId()});
    }

    public User getById(long id) {
        return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, new UserRowMapper());
    }

    public User getUserByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from users where email = ?", new Object[]{email}, new UserRowMapper());
    }

    public List<User> getUsersByName(String name) {
        return jdbcTemplate.query("select * from users where firstName = ?", new Object[]{name}, new UserRowMapper());
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
