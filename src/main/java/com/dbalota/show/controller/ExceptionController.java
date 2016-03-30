package com.dbalota.show.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dmytro_Balota on 3/21/2016.
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView onServerException(Exception e) {
        return new ModelAndView("error", "errorMessage", e.getMessage());
    }
}
