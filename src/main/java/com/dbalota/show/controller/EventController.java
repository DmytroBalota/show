package com.dbalota.show.controller;

import com.dbalota.show.models.Event;
import com.dbalota.show.services.AuditoriumService;
import com.dbalota.show.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dima on 19.03.2016.
 */
@Controller
public class EventController {
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private EventService eventService;

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm";
    private static final String CLIENT_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static DateFormat df = new SimpleDateFormat(DATE_FORMAT);
    private static DateFormat cdf = new SimpleDateFormat(CLIENT_DATE_FORMAT);


    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView eventPage() {
        ModelAndView mv = new ModelAndView("events", "auditoriumList", auditoriumService.getAuditoriums());
        mv.addObject("eventList", eventService.getAll());
        return mv;
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ModelAndView addEvent(@RequestParam String name,
                                 @RequestParam int duration,
                                 @RequestParam double price,
                                 @RequestParam String rating
    ) {
        Event e = new Event(name);
        e.setDuration(duration);
        e.setPrice(price);
        e.setRating(Event.Rating.valueOf(rating));
        eventService.create(e);

        ModelAndView mv = new ModelAndView("events", "auditoriumList", auditoriumService.getAuditoriums());
        mv.addObject("eventList", eventService.getAll());
        return mv;
    }

    @RequestMapping(value = "/events/assignAuditorium", method = RequestMethod.POST)
    public ModelAndView assignAuditorium(@RequestParam String event,
                                         @RequestParam String auditorium,
                                         @RequestParam Date date

    ) {
        eventService.assignAuditorium(eventService.getByName(event),
                auditoriumService.getAuditorium(auditorium), date);
        return new ModelAndView("redirect:/show/events");
    }

    @RequestMapping(value = "/events/delete/{nameEventTodele}")
    public ModelAndView removeAuditorium(@PathVariable String nameEventTodele) {
        eventService.remove(eventService.getByName(nameEventTodele));
        return new ModelAndView("redirect:/show/events");
    }

    @RequestMapping(value = "events/delete/assignAuditorium/{eventId}/{date}")
    public ModelAndView removeAuditorium(@PathVariable long eventId, @PathVariable String date) {
        try {
            eventService.deleteAssignment(eventId, cdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/show/events");
    }


    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true); // second argument 'allowEmpty' is set to true to allow  null/empty values.
        binder.registerCustomEditor(Date.class, editor);
    }
}
