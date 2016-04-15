package com.dbalota.show.controller;

import com.dbalota.show.models.Ticket;
import com.dbalota.show.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmytro_Balota on 4/15/2016.
 */
@Controller
public class RestTemplateTestController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/resttemplatetest")
    public ModelAndView restTemplateTest() {
        ModelAndView mv = new ModelAndView("resttemplatetest", "users", userService.getAll());

        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/resttemplatetest/usersbookedtickets")
    public List<Ticket> bookedTickets(@RequestParam long userId) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Ticket>> ticketResponse =
                restTemplate.exchange("http://localhost:8080/show/resttemplatetest/usersbookedtickets/" + userId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Ticket>>() {
                        });
        List<Ticket> tickets = ticketResponse.getBody();

        /*String result =
                restTemplate.getForObject("http://localhost:8080/show/resttemplatetest/usersbookedtickets/" + userId,
                        String.class);*/
        return tickets;
    }

    @ResponseBody
    @RequestMapping(value = "/resttemplatetest/usersbookedtickets/{userId}")
    public List<Ticket> restTemplateBookedTickets(@PathVariable long userId) {
        return userService.getBookedTickets(userService.getById(userId));
    }


}
