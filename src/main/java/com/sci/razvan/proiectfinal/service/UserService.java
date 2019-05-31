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

    /**
     * method will take a user object and will save it in the DB
     *
     * @param users
     */
    public void saveUser (Users users) {
        users.setPassword(encrytePassword(users.getPassword()));
        userRepository.save(users);
    }

    /**
     * method returns a user object only if the user recived as a parameter exist in the DB
     *
     * @param user
     * @return
     */
    public Users searchIfUserExist(String user){
        List<Users> usersList = userRepository.searchUser(user);

        if (usersList != null && !usersList.isEmpty()){
            return usersList.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * this method will return the Encryte Password with BCryptPasswordEncoder
     *
     * @param password
     * @return
     */
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    /**
     * methid update an existing user from the DB
     *
     * @param users
     */
    public void updateUser (Users users) {
        users.setRole("user");
        userRepository.save(users);
    }
}
