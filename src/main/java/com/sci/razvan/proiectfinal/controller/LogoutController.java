package com.sci.razvan.proiectfinal.controller;

import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LogoutController {
    @Autowired
    UserService userService;

    /**
     * this endpoint is called when user press log out button
     * the login.html page will be displayed
     *
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView showLoginPage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("userLogout", new Users());
        return mav;
    }
}


