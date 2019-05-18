package com.sci.razvan.proiectfinal.service;

import com.sci.razvan.proiectfinal.controller.Test;
import com.sci.razvan.proiectfinal.model.Trip;
import com.sci.razvan.proiectfinal.repository.TripRepositiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {

    @Autowired
    TripRepositiry tripRepositiry;

    public void saveNewTrip(Trip trip) {
        System.out.println("save trip");
        tripRepositiry.save(trip);
    }

    public List<Trip> findAllTripsForUser() {
        System.out.println("Find all trips!!!");
        List<Trip> list = new ArrayList<>();
        tripRepositiry.findAll().forEach(list::add);
        System.out.println("Found " + list.size() + " trips!!!");
        return list;
    }
}
