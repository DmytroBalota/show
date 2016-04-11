package com.dbalota.show.controller;

import com.dbalota.show.models.User;
import com.dbalota.show.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dima on 19.03.2016.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @Autowired
    PasswordEncoder encoder;

    private static final String CLIENT_DATE_FORMAT = "yyyy-MM-dd";
    private static DateFormat cdf = new SimpleDateFormat(CLIENT_DATE_FORMAT);

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView usersPage() {
        return new ModelAndView("users", "usersList", userService.getAll());
    }



    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user
    ) {
        userService.register(user);
        return new ModelAndView("users", "usersList", userService.getAll());
    }

    @RequestMapping(value = "users/delete/{userId}")
    public ModelAndView removeUser(@PathVariable long userId) {
        userService.remove(userService.getById(userId));
        return new ModelAndView("redirect:/users");
    }

    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        cdf.setLenient(false);
        CustomDateEditor editor = new CustomDateEditor(cdf, true); // second argument 'allowEmpty' is set to true to allow  null/empty values.
        binder.registerCustomEditor(Date.class, editor);
    }
}
