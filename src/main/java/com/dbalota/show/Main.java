package com.dbalota.show;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dbalota.show.aspects.CounterAspect;
import com.dbalota.show.aspects.DiscountAspect;
import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import com.dbalota.show.services.AuditoriumService;
import com.dbalota.show.services.BookingService;
import com.dbalota.show.services.EventService;
import com.dbalota.show.services.UserService;
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
        UserService userService = app.getUserService();
        AuditoriumService auditoriumService = app.getAuditoriumService();
        EventService eventService = app.getEventService();
        BookingService bookingService = app.getBookingService();

        // EVENT OPERATIONS
        Event event = new Event("Saw");
        event.setDuration(87);
        event.setPrice(100);
        event.setRaiting(Event.Raiting.HIGH);
        Date date = null;
        try {
            date = df.parse("2016-11-05T12:00");
        } catch (ParseException e) {
            LOG.error(String.format("Wrong dated format. Thr correct date format is %s", DATE_FORMAT));
        }

        eventService.create(event);
        if (!eventService.create(event)) {
            System.out.println("Event exists:" + event);
        }

        event = eventService.getByName("Saw");
        System.out.println("\nEvent:" + event);
        eventService.remove(event);
        eventService.create(event);

        event = eventService.getAll().get(0);

        if (eventService.assignAuditorium(event, auditoriumService.getAuditoriums().get("Red room"),
                date)) {
            System.out.println("\n Auditorium is free");
        } else {
            System.out.println("\n Auditorium is not free");
        }

        try {
            System.out.println("\nEvents between 2016-11-01T12:00 and 2016-11-06T12:00 " + eventService.getForDateRange(df.parse("2016-11-01T12:00"), df.parse("2016-11-06T12:00")));
        } catch (ParseException e) {
            LOG.error(String.format("Wrong dated format. Thr correct date format is %s", DATE_FORMAT));
        }

        //END EVENT OPERATIONS

        //USER OPERATIONS
        User user = null;
        try {
            user = new User("Dmytro", "Balota", "dmytro_balota@epam.com", df.parse("1985-11-05" + "T00:00"));
        } catch (ParseException e) {
            LOG.error(String.format("Wrong dated format. Thr correct date format is %s", DATE_FORMAT));
        }

        if (!userService.register(user)) {
            System.out.println("User already registered");
        }
        user = userService.getUserByEmail(user.getEmail());
        System.out.println("\n User by mail =" + user);
        System.out.println("\n Users by name =" + userService.getUsersByName(user.getFirstName()));
        System.out.println("\n User by id =" + userService.getById(user.getId()));
        userService.remove(user);
        System.out.println("\n Get user by name after remove =" + userService.getUsersByName(user.getFirstName()));
        userService.register(user);

        //booking service
        Set<Integer> seats = new HashSet<>();
        seats.add(1);
        seats.add(50);

        double price = bookingService.getTicketPrice(event, date, seats);
        double discount = app.getDiscountService().getDiscount(user, event, date);
        price = price - price * (discount / 100);

        System.out.println("\nPRICE = " + price);

        for (Integer seat : seats) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setAuditorium(auditoriumService.getAuditoriums().get("Red room"));
            ticket.setDate(date);
            ticket.setSeat(seat);
            ticket.setAuditoriumName("Red room");
            ticket.setEventId(event.getId());
            bookingService.bookTicket(user, ticket);
        }

        System.out.println("\nBooked tickets:" + bookingService.getTicketsForEvent(event, date));

        System.out.println("\nStatistic:" + CounterAspect.getCounters());

        System.out.println("\nNumber of received discounts:" + DiscountAspect.getUsersDiscountCount());
    }
}
