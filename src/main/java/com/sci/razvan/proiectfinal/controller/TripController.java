package com.sci.razvan.proiectfinal.controller;

import com.sci.razvan.proiectfinal.model.Trips;
import com.sci.razvan.proiectfinal.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/trip")
public class TripController {


    /**
     *     o metoda care sa ia userul indentificat si sa il trimita mai departe la pagina de add trip pentru a il
     *     pastra mereu in memorie
     *     la actionarea butonului ne va trimite la pagina add trip
     */

    @RequestMapping(value = "/add",params = "add-button", method = RequestMethod.GET)
    public ModelAndView addNewTrip (@Valid User user,HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("add-trip");
        mav.addObject("userLogin", user);
        System.out.println("-------------------->"+user.getUsername());
        mav.addObject("trip", new Trips());

        return mav;
    }
}
