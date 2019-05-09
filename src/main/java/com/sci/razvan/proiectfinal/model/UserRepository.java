package com.sci.razvan.proiectfinal.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface UserRepository extends CrudRepository <User, Id>{
}
