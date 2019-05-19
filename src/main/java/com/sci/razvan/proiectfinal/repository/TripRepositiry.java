package com.sci.razvan.proiectfinal.repository;

import com.sci.razvan.proiectfinal.model.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepositiry extends CrudRepository<Trip, Integer> {

    @Query(value = "SELECT * FROM trip t WHERE t.id = :id ", nativeQuery = true)
    public Trip findTripById(@Param("id") int id);

    @Query(value = "SELECT * FROM trip t WHERE t.userId = :userId ", nativeQuery = true)
    public List<Trip> findTripsByUserId(@Param("userId") int userId);

}