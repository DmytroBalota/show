package com.dbalota.show;

import com.dbalota.show.services.AuditoriumService;
import com.dbalota.show.services.BookingService;
import com.dbalota.show.services.EventService;
import com.dbalota.show.services.UserService;

/**
 * Hello world!
 *
 */
public class App {

	private UserService userService;

	private EventService eventService;
	
	private AuditoriumService auditoriumService;

	private BookingService bookingService;

	
	public AuditoriumService getAuditoriumService() {
		return auditoriumService;
	}

	public void setAuditoriumService(AuditoriumService auditoriumService) {
		this.auditoriumService = auditoriumService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public BookingService getBookingService() {
		return bookingService;
	}

	public void setBookingService(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	
}
