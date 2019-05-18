package com.sci.razvan.proiectfinal.controller;

import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping(path="/user/add")
    public ModelAndView saveNewUser(@Valid Users users, BindingResult bindingResult){
        System.out.println("Users: " + users);//cand afisam un string + un obiect stie automat sa sa apeleze toString pentru obiect
        ModelAndView mv;
        if(bindingResult.hasErrors()){
            System.out.println("Error when trying to insert" + bindingResult.getFieldError().toString());
            return new ModelAndView("add-user");
        }
        users.setRole("user");
        userService.saveUser(users);
        System.out.println("New users was saved!");
        return new ModelAndView("login");

    }
}
