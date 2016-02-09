package com.dbalota.show.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.dbalota.show.models.Auditorium;
import com.google.gson.Gson;

public class AuditoriumDao {

    private static Map<String, Auditorium> auditoriums = new HashMap<String, Auditorium>();

    private Gson gson = new Gson();

    private AuditoriumDao(List<Properties> properties) {

        for (Properties prop : properties) {

            Auditorium auditorium = new Auditorium(prop.getProperty("name"),
                    Integer.parseInt(prop.getProperty("seatsNumber")),
                    Arrays.asList(prop.getProperty("vipSeats").split(",")).stream().map(Integer::valueOf).collect(Collectors.toSet())
            );
            auditoriums.put(auditorium.getName(), auditorium);
        }

        /*
         * for (Entry<?, ?> prop : props.entrySet()) { String name = (String)
		 * prop.getKey(); String[] seats = ((String)
		 * prop.getValue()).split("//");
		 * 
		 * auditoriums.put(name, new Auditorium(name,
		 * Integer.parseInt(seats[0]), Integer.parseInt(seats[1]))); }
		 */
    }

    public Map<String, Auditorium> getAuditoriums() {
        return auditoriums;
    }

}
