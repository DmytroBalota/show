package com.dbalota.show.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.dbalota.show.dao.mapper.AuditoriumRowMapper;
import com.dbalota.show.dao.mapper.TicketRowMapper;
import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Ticket;
import com.google.gson.Gson;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuditoriumDao {

    private JdbcTemplate jdbcTemplate;

    private static Map<String, Auditorium> auditoriumsProps = new HashMap<String, Auditorium>();

    private Gson gson = new Gson();

    public List<Auditorium> getAuditoriums() {
        return jdbcTemplate.query("select * from auditoriums",
                new AuditoriumRowMapper());
    }

    public Auditorium getAuditoriumByName(String name) {
        return jdbcTemplate.queryForObject("select * from auditoriums where name = ?", new Object[]{name},
                new AuditoriumRowMapper());
    }

    public void addAuditorium(Auditorium auditorium) {
        jdbcTemplate.update("insert into auditoriums (id, name, seatsNumber, vipSeats) values(?,?,?,?)",
                auditorium.getId(), auditorium.getName(), auditorium.getSeatsNumber(), auditorium.getVipSeats().stream().map(String::valueOf).collect(Collectors.joining(",")));
    }

    private AuditoriumDao(List<Properties> properties) {

        for (Properties prop : properties) {

            Auditorium auditorium = new Auditorium(prop.getProperty("name"),
                    Integer.parseInt(prop.getProperty("seatsNumber")),
                    Arrays.asList(prop.getProperty("vipSeats").split(",")).stream().map(Integer::valueOf).collect(Collectors.toSet())
            );
            auditoriumsProps.put(auditorium.getName(), auditorium);
        }

    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
