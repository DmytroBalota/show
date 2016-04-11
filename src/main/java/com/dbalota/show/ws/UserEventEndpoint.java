package com.dbalota.show.ws;

import com.dbalota.show.gs_producing_web_service.GetEventRequest;
import com.dbalota.show.gs_producing_web_service.GetEventResponse;
import com.dbalota.show.gs_producing_web_service.GetUserRequest;
import com.dbalota.show.gs_producing_web_service.GetUserResponse;
import com.dbalota.show.models.Event;
import com.dbalota.show.models.User;
import com.dbalota.show.services.EventService;
import com.dbalota.show.services.UserService;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Dmytro_Balota on 4/5/2016.
 */
@Endpoint
public class UserEventEndpoint {

    private static final String NAMESPACE_URI = "http://dbalota.com/show/gs-producing-web-service";
    private UserService userService;
    private EventService eventService;

    @Autowired
    public UserEventEndpoint(UserService userService, EventService eventService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventRequest")
    @ResponsePayload
    public GetEventResponse getEvent(@RequestPayload GetEventRequest request) {
        GetEventResponse response = new GetEventResponse();
        Event e = eventService.getByName(request.getName());
        com.dbalota.show.gs_producing_web_service.Event el = new com.dbalota.show.gs_producing_web_service.Event();
        el.setDuration(e.getDuration());
        el.setId(e.getId());
        el.setName(e.getName());
        el.setPrice(e.getPrice());
        response.setEvent(el);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        User u = userService.getUserByEmail(request.getEmail());
        com.dbalota.show.gs_producing_web_service.User u1 = new com.dbalota.show.gs_producing_web_service.User();

        u1.setId(u.getId());
        u1.setFirstName(u.getFirstName());
        u1.setLastName(u.getLastName());
        u1.setEmail(u.getEmail());
        u1.setRoles(u.getRoles());
        Calendar c = GregorianCalendar.getInstance();
        c.setTime(u.getBirthday());
        u1.setBirthday(new XMLGregorianCalendarImpl((GregorianCalendar)c));

        response.setUser(u1);
        return response;
    }

}
