package com.sci.razvan.proiectfinal.repository;

import com.sci.razvan.proiectfinal.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository <Users, Id>{

    @Query(value = "SELECT * FROM users u WHERE u.username = :user ", nativeQuery = true)
    public List<Users> searchUser(@Param("user") String user);
}
