package com.ronniegnr.app.controller.client;

import com.ronniegnr.app.entity.form.UserSignupForm;
import com.ronniegnr.app.entity.form.UserSignupValidator;
import com.ronniegnr.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/")
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserSignupValidator userSignupValidator;

    private static final String VIEW_PATH = "client/account/";

    private static final String SIGNUP_PAGE = "/signup";
    private static final String LOGIN_PAGE = "/login";

    @InitBinder(value = "userSignupForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userSignupValidator);
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup() {
        return this.VIEW_PATH + "login";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signupPost(@Valid @ModelAttribute("userSignupForm") UserSignupForm userSignupForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "redirect:/signup";
        }

        userService.createUser(userSignupForm);
        return "redirect:" + LOGIN_PAGE;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(@ModelAttribute("userSignupForm") UserSignupForm userSignupForm) {
        return this.VIEW_PATH + "login";
    }

}
