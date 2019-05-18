package com.sci.razvan.proiectfinal.controller;

import com.sci.razvan.proiectfinal.model.Trip;
import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.service.TripService;

import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/trip")
public class TripController {

    /**
     *     o metoda care sa ia userul indentificat si sa il trimita mai departe la pagina de add trip pentru a il
     *     pastra mereu in memorie
     *     la actionarea butonului ne va trimite la pagina add trip
     */

    @Autowired
    private TripService tripService;
    @Autowired
    private UserService userService;


    @ModelAttribute("trip")
    public Trip exam() {
        return new Trip();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTrip(@Valid Trip trip, BindingResult bindingResult ){

        ModelAndView mv;

        if(trip != null){
            Users user = getCurrentUser();
            trip.setUsers(user);
            tripService.saveNewTrip(trip);
            mv = new ModelAndView("user-trip");
            System.out.println("Go to trip page for user !!!");
            List<Trip> trips = tripService.findAllTripsForUser(getCurrentUser());
            mv.addObject("trips", trips);
            return mv;

        } else{
            System.out.println("trip object is null!");
            return new ModelAndView("add-trip");
        }
    }

    @RequestMapping(value = "/add",params = "add-button", method = RequestMethod.GET)
    public ModelAndView goToNewTripPage (HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView("add-trip");
        mv.addObject("trip", new Trip());
        return mv;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView goToTripPage(Model model) {
        ModelAndView mv = new ModelAndView("user-trip");
        System.out.println("Go to trip page for user !!!");
        List<Trip> trips = tripService.findAllTripsForUser(getCurrentUser());
        mv.addObject("trips", trips);
        mv.addObject("trip", new Trip());

        return mv;
    }

    @RequestMapping(value = "/details", method = RequestMethod.POST)
    public String showSelectedTripDetails(@ModelAttribute("trip") Trip trip, Model model) {

        System.out.println("Trip with id " + trip.getId() + " was selected!!");
        if(trip.getId() != 0) {
            Trip t = tripService.getTrip(trip);
            model.addAttribute("trip", t);
        }
        List<Trip> trips = tripService.findAllTripsForUser(getCurrentUser());
        model.addAttribute("trips", trips);

        return "user-trip";
    }

    private Users getCurrentUser(){
        //returns authenticated user with his details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.searchIfUserExist(currentUserName);
        }
        return null;
    }
}
