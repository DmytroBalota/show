package com.dbalota.show.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.dbalota.show.models.Auditorium;
import com.google.gson.Gson;

public class AuditoriumDao {

	private static Map<String, Auditorium> auditoriums = new HashMap<String, Auditorium>();

	private Gson gson = new Gson();

	private AuditoriumDao() {
		FileReader fr;
		try {
			fr = new FileReader(getClass().getClassLoader().getResource("auditorium.properties").getFile());
			Auditorium auditorium = gson.fromJson(fr, Auditorium.class);
			auditoriums.put(auditorium.getName(), auditorium);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
