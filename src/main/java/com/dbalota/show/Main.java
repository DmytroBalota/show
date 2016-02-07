package com.dbalota.show;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dbalota.show.models.Event;

public class Main {
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm";
	private static Logger LOG = Logger.getLogger(Main.class);
	private static DateFormat df = new SimpleDateFormat(DATE_FORMAT);

	public static void main(String[] args) {
		LOG.debug("Strarting....");
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/application-beans.xml");
		LOG.debug("Context created");

		App app = context.getBean("app", App.class);

		// creating event
		Event event = new Event("Saw");
		event.setDuration(87);
		event.setPrice(100);
		event.setRaiting(Event.Raiting.HIGH);
		Date date = null;
		try {
			date = df.parse("2016-02-14T12:00");
		} catch (ParseException e) {
			LOG.error(String.format("Wrong dated format. Thr corect date format is %s", DATE_FORMAT));
		}

		if (app.getEventService().assignAuditorium(event, app.getAuditoriumService().getAuditoriums().get("Red room"),
				date)) {
			app.getEventService().create(event);
		}

		System.out.println(event);

	}
}
