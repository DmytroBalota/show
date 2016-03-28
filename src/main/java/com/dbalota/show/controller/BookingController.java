package com.dbalota.show.controller;

import com.dbalota.show.models.Event;
import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import com.dbalota.show.services.AuditoriumService;
import com.dbalota.show.services.BookingService;
import com.dbalota.show.services.EventService;
import com.dbalota.show.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dima on 19.03.2016.
 */
@Controller
public class BookingController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;


    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static DateFormat dtf = new SimpleDateFormat(DATE_TIME_FORMAT);

    private static final String CLIENT_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm";
    private static DateFormat cdtf = new SimpleDateFormat(CLIENT_DATE_TIME_FORMAT);

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public ModelAndView eventsPage(@RequestParam(required = false) String date) throws ParseException {
        Date d;
        if (date == null) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 7);
            d = c.getTime();
        } else {
            d = cdtf.parse(date);
        }
        return new ModelAndView("booking", "eventList", eventService.getNextEvents(d));
    }

    @RequestMapping(value = "/booking/event/{eventName}", method = RequestMethod.GET)
    public ModelAndView bookingEventPage(@PathVariable String eventName) {

        return new ModelAndView("booking-event", "event", eventService.getByName(eventName));
    }

    @RequestMapping(value = "/booking/event/datelocation/{eventName}/{location}/{date}", method = RequestMethod.GET)
    public ModelAndView bookingTicketPage(@PathVariable String eventName,
                                          @PathVariable String location,
                                          @PathVariable String date) throws ParseException {
        List<Ticket> bookedTickets = bookingService.getTicketsForEvent(eventService.getByName(eventName), dtf.parse(date));
        ModelAndView mv = new ModelAndView("booking-tickets", "bookedTickets", bookedTickets);
        mv.addObject("users", userService.getAll());
        mv.addObject("auditorium", auditoriumService.getAuditorium(location));
        return mv;
    }

    @RequestMapping(value = "/booking/event/datelocation/{eventName}/{location}/{date}", method = RequestMethod.POST)
    public ModelAndView bookTicket(@PathVariable String eventName,
                                   @PathVariable String location,
                                   @PathVariable String date,
                                   @RequestParam long userId,
                                   @RequestParam String seatsNumbers) throws ParseException {

        Set<Integer> seats = Arrays.asList(seatsNumbers.trim().split(",")).stream().map(Integer::valueOf).collect(Collectors.toSet());
        User user = userService.getById(userId);
        Event event = eventService.getByName(eventName);
        Date d = dtf.parse(date);

        for (Integer seat : seats) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setAuditorium(auditoriumService.getAuditorium(location));
            ticket.setDate(d);
            ticket.setSeat(seat);
            ticket.setPrice(bookingService.getTicketPrice(event, d, seat));
            ticket.setAuditoriumName(location);
            ticket.setEventId(event.getId());
            bookingService.bookTicket(user, ticket);
        }

        return new ModelAndView(String.format("redirect:/booking/event/datelocation/%s/%s/%s", eventName, location, date));
    }

}
