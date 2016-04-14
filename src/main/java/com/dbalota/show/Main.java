package com.dbalota.show;

import com.dbalota.show.aspects.CounterAspect;
import com.dbalota.show.aspects.DiscountAspect;
import com.dbalota.show.models.Auditorium;
import com.dbalota.show.models.Event;
import com.dbalota.show.models.Ticket;
import com.dbalota.show.models.User;
import com.dbalota.show.services.AuditoriumService;
import com.dbalota.show.services.BookingService;
import com.dbalota.show.services.EventService;
import com.dbalota.show.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm";
    private static Logger LOG = Logger.getLogger(Main.class);
    private static DateFormat df = new SimpleDateFormat(DATE_FORMAT);

    public static void main(String[] args) {
        LOG.debug("Strarting....");
        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring/application-context.xml");
        LOG.debug("Context created");

        App app = context.getBean("app", App.class);
        UserService userService = app.getUserService();
        AuditoriumService auditoriumService = app.getAuditoriumService();
        EventService eventService = app.getEventService();
        BookingService bookingService = app.getBookingService();
        CounterAspect counterAspect = app.getCounterAspect();

        //AUDITORIUM
        Auditorium auditorium = new Auditorium();
        auditorium.setName("Red room");
        Set<Integer> seats = new HashSet<>();
        seats.add(48);
        seats.add(49);
        seats.add(50);
        auditorium.setVipSeats(seats);
        auditorium.setSeatsNumber(50);
        auditoriumService.addAuditorium(auditorium);

        // EVENT OPERATIONS
        Event event = new Event("Saw");
        event.setDuration(87);
        event.setPrice(100);
        event.setRating(Event.Rating.HIGH);
        Date date = null;
        try {
            date = df.parse("2016-11-05T12:00");
        } catch (ParseException e) {
            LOG.error(String.format("Wrong dated format. Thr correct date format is %s", DATE_FORMAT));
        }

        eventService.create(event);
        if (!eventService.create(event)) {
            print("Event exists:" + event);
        }

        event = eventService.getByName("Saw");
        print("Event:" + event);
        eventService.remove(event);
        eventService.create(event);

        event = eventService.getAll().get(0);
        print("Auditoriums:" + auditoriumService.getAuditoriums());
        auditorium = auditoriumService.getAuditorium("Red room");
        if (eventService.assignAuditorium(event, auditorium,
                date)) {
            print(" Auditorium is free");
        } else {
            print(" Auditorium is not free");
        }

        try {
            print("Events between 2016-11-01T12:00 and 2016-11-06T12:00 " + eventService.getForDateRange(df.parse("2016-11-01T12:00"), df.parse("2016-11-06T12:00")));
        } catch (ParseException e) {
            LOG.error(String.format("Wrong dated format. Thr correct date format is %s", DATE_FORMAT));
        }

        //END EVENT OPERATIONS

        //USER OPERATIONS
        User user = null;
        try {
            user = new User("Dmytro", "Balota");
            user.setEmail("dmytro_balota@epam.com");
            user.setBirthday(df.parse("1985-11-05" + "T00:00"));
        } catch (ParseException e) {
            LOG.error(String.format("Wrong dated format. Thr correct date format is %s", DATE_FORMAT));
        }

        if (!userService.register(user)) {
            print("User already registered");
        }
        user = userService.getUserByEmail(user.getEmail());
        print(" User by mail =" + user);
        print(" Users by name =" + userService.getUsersByName(user.getFirstName()));
        print(" User by id =" + userService.getById(user.getId()));
        userService.remove(user);
        print(" Get user by name after remove =" + userService.getUsersByName(user.getFirstName()));
        userService.register(user);

        //BOOKING SERVICE
        seats = new HashSet<>();
        seats.add(1);
        seats.add(auditoriumService.getVipSeats("Red room").iterator().next());

        print("PRICE = " + bookingService.getTicketPrice(event, date, seats));

        for (Integer seat : seats) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setAuditorium(auditoriumService.getAuditorium("Red room"));
            ticket.setDate(date);
            ticket.setSeat(seat);
            ticket.setPrice(bookingService.getTicketPrice(event, date, seat));
            ticket.setAuditoriumName("Red room");
            ticket.setEventId(event.getId());
            bookingService.bookTicket(user, ticket);
        }

        print("Booked tickets:" + bookingService.getTicketsForEvent(event, date));

        print("User's tickets:" + userService.getBookedTickets(user));

        print("Statistic: "
                + "\n\t" + CounterAspect.BOOK_TICKET + ":" + counterAspect.getCounterNumber(CounterAspect.BOOK_TICKET)
                + "\n\t" + CounterAspect.GET_EVENT_BY_NAME + ":" + counterAspect.getCounterNumber(CounterAspect.GET_EVENT_BY_NAME)
                + "\n\t" + CounterAspect.GET_TICKET_PRICE + ":" + counterAspect.getCounterNumber(CounterAspect.GET_TICKET_PRICE));

        print("Number of received discounts:" + DiscountAspect.getUsersDiscountCount());
    }

    private static void print(String s) {
        System.out.print("\n=========================================================================================\n");
        System.out.print(s);
    }
}
