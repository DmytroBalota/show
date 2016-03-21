package com.dbalota.show.controller;

import com.dbalota.show.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersBookedTicketsController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/{userId}/tickets.pdf"/*,  headers="accept=application/pdf"*/)
    public ModelAndView usersTicketsPdf(@PathVariable long userId) {

        return new ModelAndView("usersTicketPdf", "usersBookedTickets", userService.getBookedTickets(userService.getById(userId)));
    }

}
