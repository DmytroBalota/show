package com.dbalota.show.ws_client;

import com.dbalota.show.gs_producing_web_service.GetEventRequest;
import com.dbalota.show.gs_producing_web_service.GetEventResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Dmytro_Balota on 4/6/2016.
 */
@Controller
public class WebServiceClient {

    private final WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

    // send to an explicit URI
    @RequestMapping(path = "/wstest")
    public ModelAndView customSendAndReceive(@RequestParam String eventName) throws Exception {

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setContextPath("com.dbalota.show.gs_producing_web_service");
        marshaller.afterPropertiesSet();

        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.afterPropertiesSet();

        webServiceTemplate.setUnmarshaller(marshaller);

        webServiceTemplate.setMarshaller(marshaller);
        final SoapActionCallback soapActionCallback = new SoapActionCallback("getEventRequest");
        GetEventRequest request = new GetEventRequest();
        request.setName(eventName);
        GetEventResponse response = (GetEventResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8080/ws", request,

                soapActionCallback);


        return new ModelAndView("wstest", "wsresponse", response.getEvent());
    }


}
