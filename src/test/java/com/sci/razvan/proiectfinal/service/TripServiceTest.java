package com.sci.razvan.proiectfinal.service;

import com.sci.razvan.proiectfinal.model.Trip;
import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.repository.TripRepositiry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    @InjectMocks
    TripService tripService;
    @Mock
    TripRepositiry tripRepository;

    @Test
    public void testFindAllTripsForUser(){
        List tripList = new ArrayList<Trip>();
        when(tripRepository.findAll()).thenReturn(tripList);

        //test when user has 0 trips
        assertEquals(tripList,tripService.findAllTripsForUser());

        //test when user has 1 trip
        tripList.add(new Trip());
        assertEquals(tripList,tripService.findAllTripsForUser());
        assertEquals(tripList.size(),tripService.findAllTripsForUser().size());
    }

    @Test
    public void testFindAllTripsForUserWithParam(){
        List tripList = new ArrayList<Trip>();
        Users currentUser = new Users();
        currentUser.setId(5);

        //test when user has 0 trips
        when(tripRepository.findTripsByUserId(5)).thenReturn(tripList);
        assertEquals(tripList,tripService.findAllTripsForUser(currentUser));

        //test when user has 1 trip
        tripList.add(new Trip());
        assertEquals(tripList,tripService.findAllTripsForUser(currentUser));
        assertEquals(tripList.size(),tripService.findAllTripsForUser(currentUser).size());
    }

    @Test
    public void testGetTripById(){

        //find the object in db
        Trip trip = new Trip();
        trip.setId(7);
        when(tripRepository.findById(7)).thenReturn(Optional.of(trip));
        assertEquals(trip,tripService.getTripById(trip));
    }
}
