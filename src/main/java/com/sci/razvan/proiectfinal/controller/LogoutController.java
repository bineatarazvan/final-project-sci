package com.sci.razvan.proiectfinal.controller;

import com.sci.razvan.proiectfinal.model.User;
import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping(path = "/user")
public class LogoutController {
    @Autowired
    UserService userService;

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView showLoginPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("logout");
        mav.addObject("userLogout", new User());
        return mav;
    }




}


