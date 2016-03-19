package com.dbalota.show.controller;

import com.dbalota.show.models.Auditorium;
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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by Dima on 19.03.2016.
 */
@Controller
public class EventController {
    @Autowired
    private AuditoriumService auditoriumService;
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/event", method = RequestMethod.GET)
    public ModelAndView eventPage() {
        ModelAndView mv = new ModelAndView("event", "auditoriumList", auditoriumService.getAuditoriums());
        mv.addObject("eventList", eventService.getAll());
        return mv;
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public ModelAndView addAuditorium(@RequestParam String name,
                                      @RequestParam int duration,
                                      @RequestParam double price,
                                      @RequestParam String raiting,
                                      @RequestParam Date date,
                                      @RequestParam String auditorium
    ) {
        Event e = new Event(name);
        e.setDuration(duration);
        e.setPrice(price);
        e.setRaiting(Event.Raiting.valueOf(raiting));

        eventService.create(e);
        e = eventService.getByName(name);
        eventService.assignAuditorium(e, auditoriumService.getAuditorium(auditorium), date);
        ModelAndView mv = new ModelAndView("event", "auditoriumList", auditoriumService.getAuditoriums());
        mv.addObject("eventList", eventService.getAll());
        return mv;
    }

    @RequestMapping(value = "/event/delete/{nameEventTodele}")
    public ModelAndView removeAuditorium(@PathVariable String nameEventTodele) {
        eventService.remove(eventService.getByName(nameEventTodele));
        return new ModelAndView("redirect:/event");
    }

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(df, true); // second argument 'allowEmpty' is set to true to allow  null/empty values.
        binder.registerCustomEditor(Date.class, editor);
    }
}
