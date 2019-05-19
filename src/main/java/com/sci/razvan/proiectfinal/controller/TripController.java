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
import org.springframework.web.bind.annotation.*;
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

        ModelAndView mv =  new ModelAndView("add-trip");

        if(trip != null || trip.getTripName() == null || trip.getTripName().isEmpty()){
            Users user = getCurrentUser();
            trip.setUsers(user);
            tripService.saveNewTrip(trip);
            mv.addObject("trip", new Trip());
            mv.addObject("message", "New trip was saved successfully!");
        }
        if(bindingResult.hasErrors()){
            mv.addObject("message", bindingResult.getAllErrors());
            mv.addObject("trip", trip);
        }
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView goToNewTripPage (HttpServletRequest request, HttpServletResponse response){
        System.out.println("Add new trip");
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

    @RequestMapping(value = "/details",params = "show-info", method = RequestMethod.POST)
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

    @RequestMapping(value = "/details",params = "delete-button", method = RequestMethod.POST)
    public String deleteSelectedTrip(@ModelAttribute("trip") Trip trip, Model model) {

        System.out.println("Trip with id " + trip.getId() + " was selected to be deleted!!");
        if(trip.getId() != 0) {
            Trip t = tripService.getTrip(trip);
            tripService.deleteTrip(t);
            model.addAttribute("message", "Trip " + t.getTripName() + " was deleted!");
        }
        List<Trip> trips = tripService.findAllTripsForUser(getCurrentUser());
        model.addAttribute("trips", trips);

        return "user-trip";
    }

    @RequestMapping(value = "/details",params = "edit-button", method = RequestMethod.POST)
    public ModelAndView selectTripToBeModified(@ModelAttribute("trip") Trip trip, Model model) {
        ModelAndView mv ;
        if(trip.getId() != 0) {
            System.out.println("Go to Edit trip page with tripId= " + trip.getId());
            Trip t = tripService.getTrip(trip);
            mv = new ModelAndView("edit-trip");
            mv.addObject("trip", t);
            return mv;
        }
        System.out.println("Id selected trip = 0 !!!");
        return new ModelAndView("user-trip");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public Model goToEditTrip(Model model) {
        return model;
    }

    @PostMapping(path="/edit")
    public ModelAndView updateUser(@Valid Trip trip, BindingResult bindingResult){
        System.out.println("Trip: " + trip);
        ModelAndView mv = new ModelAndView("edit-trip");;
        if(bindingResult.hasErrors()){
            System.out.println("Error when trying to modify trip: " + bindingResult.getFieldError().toString());
            mv.addObject("message","Error when trying to modify trip!");
            return mv;
        }
        trip.setUsers(getCurrentUser());
        tripService.saveNewTrip(trip);

        mv.addObject("trip",trip);
        mv.addObject("message","Trip details updated!");

        return mv;
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
