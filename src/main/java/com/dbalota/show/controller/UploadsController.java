package com.dbalota.show.controller;

import com.dbalota.show.models.Event;
import com.dbalota.show.models.User;
import com.dbalota.show.services.EventService;
import com.dbalota.show.services.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadsController {

    private Gson gson = new Gson();

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/uploads")
    public ModelAndView uploadsPage() {
        return new ModelAndView("uploads");
    }

    @RequestMapping(value = "/uploads/users", method = RequestMethod.POST)
    public String uploadUsers(@RequestParam MultipartFile usersDump) throws IOException {

        Type listType = new TypeToken<ArrayList<User>>() {
        }.getType();

        List<User> users = gson.fromJson(new String(usersDump.getBytes()), listType);

        for (User u : users) {
            userService.register(u);
        }

        return "redirect:/users";
    }

    @RequestMapping(value = "/uploads/events", method = RequestMethod.POST)
    public String uploadEvents(@RequestParam MultipartFile eventsDump) throws IOException {

        Type listType = new TypeToken<ArrayList<Event>>() {
        }.getType();

        List<Event> events = gson.fromJson(new String(eventsDump.getBytes()), listType);

        for (Event e : events) {
            eventService.create(e);
        }

        return "redirect:/events";
    }


}
