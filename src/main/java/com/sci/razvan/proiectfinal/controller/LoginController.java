package com.sci.razvan.proiectfinal.controller;

import com.sci.razvan.proiectfinal.model.User;
import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("login-page");
        mav.addObject("userLogin", new User());
        return mav;
    }

    @RequestMapping(value = "/login",params = "login-button", method = RequestMethod.POST)
    public ModelAndView userLogin(@Valid User user, BindingResult bindingResult){

        if(userService.searchIfUserExist(user.getUserName(), user.getPassword())){
            ModelAndView mav = new ModelAndView("user-trip");
            mav.addObject("userLogin", user);
            return mav;
        }else{
            ModelAndView mav = new ModelAndView("login-page");
            return mav;
        }
    }

    @RequestMapping(value = "/login",params = "signup-button", method = RequestMethod.POST)
    public String createNewUser(Model model){
        return "redirect:/user/add";
    }
}

