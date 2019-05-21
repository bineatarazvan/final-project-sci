package com.sci.razvan.proiectfinal.repository;

import com.sci.razvan.proiectfinal.model.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepositiry extends CrudRepository<Trip, Integer> {

    /**
     * method will search a for a trip using their id
     *
     * @param id
     * @return
     */
    @Query(value = "SELECT * FROM trip t WHERE t.id = :id ", nativeQuery = true)
    public Trip findTripById(@Param("id") int id);

    /**
     * method will return all the trips for a given user id
     *
     * @param userId
     * @return
     */
    @Query(value = "SELECT * FROM trip t WHERE t.userId = :userId ", nativeQuery = true)
    public List<Trip> findTripsByUserId(@Param("userId") int userId);
}