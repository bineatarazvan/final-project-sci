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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

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

    /**
     *
     * this endpoint send the user to the add new trip page
     * an empty object trip is sent to this page this object will contain all the details added by the user fot that trip
     *
     * @param request
     * @param response
     * @return ModelAndView
     */

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView goToNewTripPage (HttpServletRequest request, HttpServletResponse response){
        System.out.println("Add new trip");
        ModelAndView mv = new ModelAndView("add-trip");
        mv.addObject("trip", new Trip());
        return mv;
    }

    /**
     *
     * this endpoint will get the object with the new trip details from the html page, and will insert it in DB
     * after insertion the same page will ne displayed
     * user cann upload 0,1,or 2 photo for the trip
     *
     * @param trip
     * @param bindingResult
     * @param file1
     * @param file2
     * @return ModelAndView
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addTrip(@Valid Trip trip, BindingResult bindingResult, @RequestParam("fileToUpload1") MultipartFile file1, @RequestParam("fileToUpload2") MultipartFile file2 ){

        ModelAndView mv =  new ModelAndView("add-trip");
        if(bindingResult.hasErrors()){
            mv.addObject("message", bindingResult.getAllErrors());
            mv.addObject("trip", trip);
            System.out.println("binding has errors !!!!!!");
            return mv;
        }
        byte[] photoPath1 =  null;
        byte[] photoPath2 = null;

        if(trip != null){
            Users user = getCurrentUser();
            trip.setUsers(user);
            try{
                if(file1 != null){
                    photoPath1 = file1.getBytes();
                }
                if(file2 != null) {
                    photoPath2 = file2.getBytes();
                }
                tripService.saveNewTrip(trip,photoPath1,photoPath2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mv.addObject("trip", new Trip());
            mv.addObject("message", "New trip was saved successfully!");
        }
        return mv;
    }

    /**
     * this endpoint it is called when the main interface will be showed
     * this includes a list will all the trip for loged user
     *
     * @param model
     * @return ModelAndView
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView goToTripPage(Model model) {
        ModelAndView mv = new ModelAndView("user-trip");
        System.out.println("Go to trip page for user !!!");
        List<Trip> trips = tripService.findAllTripsForUser(getCurrentUser());
        mv.addObject("trips", trips);
        mv.addObject("trip", new Trip());

        return mv;
    }

    /**
     * this endpoint it is called when show information button is presed for a selected trip
     * the method will retrice all the information for that trip
     *
     * @param trip
     * @param model
     * @return String
     */
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

    /**
     * this endpoint is called when the delet button is presed from the main interface
     * this will delete the selected trip from the DB
     *
     * @param trip
     * @param model
     * @return String
     */
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

    /**
     * this endpoint is called when the edit button is presed for a selected trip
     * this method will take the id for the trip and will load all the information for that trip in the edit trip page
     *
     * @param trip
     * @param model
     * @return ModelAndView
     */
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

    /**
     * this endpoint will be called when the edit trip page wil be displayed
     *
     * @param model
     * @return Model
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public Model goToEditTrip(Model model) {
        return model;
    }

    /**
     * this endpoint is called when the user wants to save the modification made for a trip in edit trip page
     * the new information will be saved in the DB
     *
     * @param trip
     * @param bindingResult
     * @param file1
     * @param file2
     * @return ModelAndView
     */
    @PostMapping(path="/edit")
    public ModelAndView updateUser(@Valid Trip trip, BindingResult bindingResult, @RequestParam("fileToUpload1") MultipartFile file1, @RequestParam("fileToUpload2") MultipartFile file2){
        System.out.println("Trip: " + trip);
        ModelAndView mv = new ModelAndView("edit-trip");
        if(bindingResult.hasErrors()){
            System.out.println("Error when trying to modify trip: " + bindingResult.getFieldError().toString());
            mv.addObject("message","Error when trying to modify trip!");
            return mv;
        }
        byte[] photoPath1 =  null;
        byte[] photoPath2 = null;
        trip.setUsers(getCurrentUser());

        try{
            if(file1 != null){
                photoPath1 = file1.getBytes();
            }
            if(file2 != null) {
                photoPath2 = file2.getBytes();
            }

            tripService.saveNewTrip(trip,photoPath1,photoPath2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mv.addObject("trip",trip);
        mv.addObject("message","Trip details updated!");
        return mv;
    }

    /**
     * this method returns the current user that is logged in the application
     * @return Users
     */
    private Users getCurrentUser(){
        //returns authenticated user with his details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.searchIfUserExist(currentUserName);
        }
        return null;
    }

    @ModelAttribute("trip")
    public Trip exam() {
        return new Trip();
    }
}
