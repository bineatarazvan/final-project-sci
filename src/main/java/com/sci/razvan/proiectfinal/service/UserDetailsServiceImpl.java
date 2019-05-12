package com.sci.razvan.proiectfinal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.sci.razvan.proiectfinal.model.User u = userService.searchIfUserExist(username);
        System.out.println("---> in metoda UserDetailsServiceImpl");
        Set roles = new HashSet<>();
        User.UserBuilder builder = null;
        roles.add("ADMIN");
        if( u!= null) {
            User user = new User(u.getUsername(), u.getPassword(), roles);
            System.out.println("---> u.getUsername()" + u.getUsername());
            System.out.println("---> u.getPassword()" + u.getPassword());

            if (user != null) {
                System.out.println("---> user != null");
                builder = org.springframework.security.core.userdetails.User.withUsername(username);
                builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
                builder.roles("admin");
            }
        }else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
}