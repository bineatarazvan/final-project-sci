package com.sci.razvan.proiectfinal.repository;

import com.sci.razvan.proiectfinal.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository <User, Id>{

    @Query(value = "SELECT * FROM user u WHERE u.username = :user ", nativeQuery = true)
    public List<User> searchUser(@Param("user") String user);
}
