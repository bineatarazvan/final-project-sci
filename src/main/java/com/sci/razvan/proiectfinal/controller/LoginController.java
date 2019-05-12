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
@RequestMapping(path = "")
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        model.addAttribute("userLogin", new User());
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");
        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/login",params = "login-button", method = RequestMethod.POST)
    public String userLogin(@Valid User user, BindingResult bindingResult){
/*
        if(userService.searchIfUserExist(user.getUsername(), user.getPassword())){
            return "redirect:/user";

        }else{
            return "redirect:/login";

        }
        */
        return "redirect:/user";
    }

    @RequestMapping(value = "/login",params = "signup-button", method = RequestMethod.POST)
    public String createNewUser(Model model){
        return "redirect:/user/add";
    }
}

