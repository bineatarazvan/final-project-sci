package com.sci.razvan.proiectfinal.service;

import com.sci.razvan.proiectfinal.model.Trip;
import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.repository.TripRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    TripRepositiry tripRepositiry;

    /**
     * this method will take as a parameter an object trip with 2 photos
     * that will be saved on DB for the curent user, the method is use also for update
     *
     * @param trip
     * @param photoPath1
     * @param photoPath2
     */
    public void saveNewTrip(Trip trip,byte[]photoPath1, byte[]photoPath2) {
        if(photoPath1 != null)
            trip.setPhotoPath1(Base64.getEncoder().encodeToString(photoPath1));
        if(photoPath2 != null)
            trip.setPhotoPath2(Base64.getEncoder().encodeToString(photoPath2));
        System.out.println("save trip");
        tripRepositiry.save(trip);
    }

    /**
     * this method deletes the trip received as a parameter
     *
     * @param trip
     */
    public void deleteTrip(Trip trip) {
        System.out.println("delete trip");
        tripRepositiry.delete(trip);
    }

    /**
     * the method return all existing trips from the DB
     *
     * @return
     */
    public List<Trip> findAllTripsForUser() {
        System.out.println("Find all trips!!!");
        List<Trip> list = new ArrayList<>();
        tripRepositiry.findAll().forEach(list::add);
        System.out.println("Found " + list.size() + " trips!!!");
        return list;
    }

    /**
     * the method returns a list with all existing trips for the received user
     *
     * @param user
     * @return
     */
    public List<Trip> findAllTripsForUser(Users user) {
        List<Trip> list = new ArrayList<>();
        list = tripRepositiry.findTripsByUserId(user.getId());
        System.out.println("Found " + list.size() + " trips!!!");
        return list;
    }

    /**
     * this method returns a specified trip from the DB
     *
     * @param trip
     * @return
     */
    public Trip getTrip(Trip trip){
        return tripRepositiry.findTripById(trip.getId());
    }

    /**
     * this method will return a trip by their id
     *
     * @param trip
     * @return
     */
    public Trip getTripById(Trip trip){
        if(tripRepositiry.findById(trip.getId()).isPresent()){
            return tripRepositiry.findById(trip.getId()).get();
        }
        return null;
    }
}
