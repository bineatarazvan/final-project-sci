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

    @Query(value = "update Users u set u.firstName =:firstname, u.lastName =:lastname, u.city=:city, u.phone=:phone, u.address=:address where u.id=:id", nativeQuery = true)
    public void updareUser(@Param("firstname") String firstname, @Param("lastname") String lastname,
                           @Param("city") String city, @Param("phone") String phone, @Param("address") String address,
                           @Param("id") int id);
}
