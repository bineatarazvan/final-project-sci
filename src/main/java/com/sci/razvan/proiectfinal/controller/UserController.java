package com.sci.razvan.proiectfinal.controller;

import com.sci.razvan.proiectfinal.model.Trip;
import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Iterator;

@RestController
public class UserController  {
    @Autowired
    UserService userService;

    @GetMapping(path = "/user")
    public ModelAndView getAllUser(){
        //pe pagina user-page se va putea accesa atributul userList cu valoarea pe care o stocheaza el
        ModelAndView mv = new ModelAndView("user-page");
        mv.addObject("userList", userService.getAllUsers());
        int counter=0;
        Iterator i = userService.getAllUsers().iterator();
        while(i.hasNext()){
            counter++;
            i.next();
        }
        System.out.println("No. of users from DB:" + counter);
        return mv;
    }

    @GetMapping(path = "user/add")
    public ModelAndView showAddAuthorPage(Model model){
        ModelAndView mv = new ModelAndView("add-user");
        mv.addObject("user", new Users());
        return mv;
    }

    @GetMapping(path = "user/profile")
    public ModelAndView showUserDetails(Model model){
        ModelAndView mv = new ModelAndView("user-profil");
        mv.addObject("user", getCurrentUser());
        return mv;
    }

    @PostMapping(path="/user/profile")
    public ModelAndView updateUser(@ModelAttribute("user") Users user, BindingResult bindingResult){
        ModelAndView mv;
        if(bindingResult.hasErrors()){
            System.out.println("Error when trying to insert" + bindingResult.getFieldError().toString());
            mv = new ModelAndView("user-profil");
            mv.addObject("user", user);
            return mv;
        }
        userService.updateUser(user);
        mv = new ModelAndView("user-profil");
        mv.addObject("user",user);
        mv.addObject("message","User details updated!");
        return mv;
    }

    @PostMapping(path="/user/add")
    public ModelAndView saveNewUser(@ModelAttribute("user") Users user, BindingResult bindingResult){
        System.out.println("Users: " + user);//cand afisam un string + un obiect stie automat sa sa apeleze toString pentru obiect
        ModelAndView mv;
        if(bindingResult.hasErrors()){
            System.out.println("Error when trying to insert" + bindingResult.getFieldError().toString());
            return new ModelAndView("add-user");
        }
        user.setRole("user");
        userService.saveUser(user);
        System.out.println("New users was saved!");
        mv = new ModelAndView("login");
        mv.addObject("userLogin",new Users());
        mv.addObject("message","New user " + user.getUsername() + " was created!");
        return mv;
    }

    private Users getCurrentUser(){
        //returns authenticated user with his details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.searchIfUserExist(currentUserName);
        }
        return null;
    }
}
