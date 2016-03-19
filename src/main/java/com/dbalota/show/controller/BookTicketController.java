package com.dbalota.show.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookTicketController {

    @RequestMapping(value = "/bookTicket")
    public ModelAndView bookTicketPage(){
        return new ModelAndView("bookTicketForm");
    }

}
