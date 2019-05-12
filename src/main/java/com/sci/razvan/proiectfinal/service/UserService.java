package com.sci.razvan.proiectfinal.service;


import com.sci.razvan.proiectfinal.model.User;
import com.sci.razvan.proiectfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//e un layer pt a separa logica
@Service
public class UserService {
    @Autowired //pentru a instantia singur
    UserRepository userRepository;

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void saveUser (User user) {
        userRepository.save(user);
    }

    public User searchIfUserExist(String user){
        List<User> userList = userRepository.searchUser(user);

        if (userList != null && !userList.isEmpty())
            return userList.get(0);
        else
            return null;
    }
}
