package com.dbalota.show.dao.mapper;

import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Created by Dmytro_Balota on 2/26/2016.
 */
public class AuditoriumRowMapper implements RowMapper<Auditorium> {
    @Override
    public Auditorium mapRow(ResultSet rs, int i) throws SQLException {
        Auditorium auditorium = new Auditorium();
        auditorium.setId(rs.getInt("id"));
        auditorium.setName(rs.getString("name"));
        auditorium.setSeatsNumber(rs.getInt("seatsNumber"));
        String[] vipS = rs.getString("vipSeats").trim().split(",");

        auditorium.setVipSeats(Arrays.asList(vipS).stream().map(Integer::valueOf).collect(Collectors.toSet()));

        return auditorium;
    }
}
