package com.dbalota.show.controller;

import com.dbalota.show.models.Auditorium;
import com.dbalota.show.services.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Dima on 19.03.2016.
 */
@Controller
public class AuditoriumController {
    @Autowired
    private AuditoriumService auditoriumService;

    @RequestMapping(value = "/auditoriums", method = RequestMethod.GET)
    public ModelAndView auditoriumPage() {
        return new ModelAndView("auditoriums", "auditoriumList", auditoriumService.getAuditoriums());
    }

    @RequestMapping(value = "/auditoriums", method = RequestMethod.POST)
    public ModelAndView addAuditorium(@RequestParam String name, @RequestParam int seatsNumber, @RequestParam String vipSeats) {
        Auditorium a = new Auditorium();
        a.setName(name);
        a.setSeatsNumber(seatsNumber);
        String[] vipS = vipSeats.trim().split(",");
        a.setVipSeats(Arrays.asList(vipS).stream().map(Integer::valueOf).collect(Collectors.toSet()));
        auditoriumService.addAuditorium(a);
        return new ModelAndView("auditoriums", "auditoriumList", auditoriumService.getAuditoriums());
    }

    @RequestMapping(value = "auditoriums/delete/{nameAuditoriumTodele}")
    public ModelAndView removeAuditorium(@PathVariable String nameAuditoriumTodele) {
        Auditorium a = auditoriumService.getAuditorium(nameAuditoriumTodele);
        auditoriumService.delete(a);
        return new ModelAndView("redirect:/auditoriums");
    }
}
