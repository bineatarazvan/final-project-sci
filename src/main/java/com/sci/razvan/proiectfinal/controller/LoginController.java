
package com.sci.razvan.proiectfinal.controller;
import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Rzv Bineata
 * 22/05/2019
 */

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * this endpoint is called when user trays to access the application
     * the login.html page will be displayed
     *
     * @param model
     * @param error
     * @param logout
     * @return string
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        model.addAttribute("userLogin", new Users());
        return "login";
    }
}

