package com.dbalota.show.controller;

import com.dbalota.show.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportsController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/reports")
    public ModelAndView reportsPage() {
        return new ModelAndView("reports", "users", userService.getAll());
    }

    @RequestMapping(value = "/reports/usersBookedTickets",  produces="application/pdf")
    public ModelAndView usersTicketsPdf(@RequestParam long userId) {
        return new ModelAndView("usersTicketPdf", "usersBookedTickets", userService.getBookedTickets(userService.getById(userId)));
    }

}
