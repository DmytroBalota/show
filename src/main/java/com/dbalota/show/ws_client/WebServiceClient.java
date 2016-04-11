package com.dbalota.show.ws_client;

import com.dbalota.show.gs_producing_web_service.GetEventRequest;
import com.dbalota.show.gs_producing_web_service.GetEventResponse;
import com.dbalota.show.gs_producing_web_service.GetUserRequest;
import com.dbalota.show.gs_producing_web_service.GetUserResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

/**
 * Created by Dmytro_Balota on 4/6/2016.
 */
@Controller
public class WebServiceClient {

    public static final String WS_URL = "http://localhost:8080/ws";
    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    // send to an explicit URI
    @RequestMapping(path = "/wstest")
    public ModelAndView customSendAndReceive(@RequestParam String eventName, @RequestParam String email) throws Exception {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setContextPath("com.dbalota.show.gs_producing_web_service");
        marshaller.afterPropertiesSet();

        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.afterPropertiesSet();

        webServiceTemplate.setUnmarshaller(marshaller);

        webServiceTemplate.setMarshaller(marshaller);
        GetEventRequest eventRequest = new GetEventRequest();
        eventRequest.setName(eventName);
        GetEventResponse eventResponse = (GetEventResponse) webServiceTemplate.marshalSendAndReceive(WS_URL, eventRequest,
                new SoapActionCallback("getEventRequest"));

        ModelAndView mv = new ModelAndView("wstest", "event", eventResponse.getEvent());

        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setEmail(email);
        GetUserResponse userResponse = (GetUserResponse) webServiceTemplate.marshalSendAndReceive(WS_URL, getUserRequest,
                new SoapActionCallback("getUserRequest"));

        mv.addObject("user", userResponse.getUser());

        return mv;
    }


}
