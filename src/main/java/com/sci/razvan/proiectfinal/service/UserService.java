package com.sci.razvan.proiectfinal.service;


import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//e un layer pt a separa logica
@Service
public class UserService {
    @Autowired //pentru a instantia singur
    UserRepository userRepository;

    public Iterable<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser (Users users) {
        userRepository.save(users);
    }

    public Users searchIfUserExist(String user){
        List<Users> usersList = userRepository.searchUser(user);

        if (usersList != null && !usersList.isEmpty()){
            System.out.println("usersList.get(0)--------"+usersList.get(0).getUsername());
            return usersList.get(0);
        }

        else {
            System.out.println("usersList.get(0)------nullll--");

            return null;
        }
    }
}
