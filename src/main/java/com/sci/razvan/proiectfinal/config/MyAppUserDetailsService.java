package com.sci.razvan.proiectfinal.config;

import java.util.Arrays;
import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyAppUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        System.out.println("---------1-------------");
        Users users=userService.searchIfUserExist(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(users.getRole());
        UserDetails userDetails = (UserDetails)new User(users.getUsername(),
                users.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}