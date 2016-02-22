package com.dbalota.show.dao;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Dmytro_Balota on 22/02/2016.
 */
public class CounterAspectDao {
    private JdbcTemplate jdbcTemplate;

    public Boolean counterExists(String name) {
        return jdbcTemplate.queryForObject("select count(*) from counters where name = ?",
                new Object[]{name}, Boolean.class);
    }

    public void incrementCounter(String name) {
        jdbcTemplate.update("update counters set number = (number + 1) where name = ?",
                new Object[]{name});
    }

    public void addCounter(String name, Integer number) {
        jdbcTemplate.update("insert into counters (name, number) values(?,?)", name, number);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getCounterNumber(String name) {
        return jdbcTemplate.queryForObject("select number from counters where name = ?",
                new Object[]{name}, Integer.class);
    }
}
