package com.dbalota.show.controller;

import com.dbalota.show.services.UserAccountService;
import com.dbalota.show.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dmytro_Balota on 3/30/2016.
 */
@Controller
public class UserAccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/users/{userId}/accounts", method = RequestMethod.GET)
    public ModelAndView userAccountsPage(@PathVariable long userId) {
        ModelAndView mv = new ModelAndView("user-account");
        mv.addObject("user", userService.getById(userId));
        mv.addObject("accounts", userAccountService.getUserAccounts(userId));
        return mv;
    }

    @RequestMapping(value = "/users/{userId}/accounts/{accountNumber}/refill", method = RequestMethod.GET)
    public ModelAndView userAccountRefillPage(@PathVariable long userId, @PathVariable long accountNumber) {
        ModelAndView mv = new ModelAndView("user-account-refill");
        mv.addObject("user", userService.getById(userId));
        mv.addObject("balance", userAccountService.checkBalance(accountNumber));
        mv.addObject("accountNumber", accountNumber);
        return mv;
    }

    @RequestMapping(value = "/users/{userId}/accounts/create", method = RequestMethod.GET)
    public ModelAndView userAccountCreate(@PathVariable long userId) {
        userAccountService.createAccount(userId);
        return new ModelAndView("redirect:/show/users/" + userId + "/accounts/");
    }

    @RequestMapping(value = "/users/{userId}/accounts/{accountNumber}/delete", method = RequestMethod.GET)
    public ModelAndView userAccountDelete(@PathVariable long userId, @PathVariable long accountNumber) {
        userAccountService.deleteAccount(accountNumber);
        return new ModelAndView("redirect:/show/users/" + userId + "/accounts/");
    }

    @RequestMapping(value = "/users/{userId}/accounts/{accountNumber}/refill", method = RequestMethod.POST)
    public ModelAndView userAccountRefill(@PathVariable long userId, @PathVariable long accountNumber,
                                          @RequestParam Double amount) {
        userAccountService.refill(accountNumber, amount);
        return new ModelAndView("redirect:/show/users/" + userId + "/accounts/" + accountNumber + "/refill");
    }
}
