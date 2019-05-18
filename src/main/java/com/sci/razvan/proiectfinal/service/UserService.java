package com.sci.razvan.proiectfinal.service;


import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

        users.setPassword(encrytePassword(users.getPassword()));
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
    // Encryte Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
