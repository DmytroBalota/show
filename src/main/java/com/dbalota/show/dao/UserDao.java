package com.dbalota.show.dao;

import com.dbalota.show.dao.mapper.UserRowMapper;
import com.dbalota.show.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {

    private JdbcTemplate jdbcTemplate;

    public Boolean userExists(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("select count(*) from users where firstName = ? and lastName = ?",
                new Object[]{firstName, lastName}, Boolean.class);
    }

    public User getUserByFirstAndLastName(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("select * from users where firstName = ? and lastName = ?",
                new Object[]{firstName, lastName}, new UserRowMapper());
    }

    public void add(User user) {
        jdbcTemplate.update("insert into users (firstName, lastName, email, birthday, password) values(?,?,?,?,?)", user.getFirstName()
                , user.getLastName(), user.getEmail(), user.getBirthday(), user.getPassword());
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

    public List<User> getAll() {
        return jdbcTemplate.query("select * from users",
                new UserRowMapper());
    }

    public User getUserByNameSurname(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("select * from users where firstName = ? and lastName = ?", new Object[]{firstName, lastName}, new UserRowMapper());
    }
}
