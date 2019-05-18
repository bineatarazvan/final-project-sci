package com.sci.razvan.proiectfinal.repository;

import com.sci.razvan.proiectfinal.model.Trip;
import com.sci.razvan.proiectfinal.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface TripRepositiry extends CrudRepository<Trip, Id> {

}
