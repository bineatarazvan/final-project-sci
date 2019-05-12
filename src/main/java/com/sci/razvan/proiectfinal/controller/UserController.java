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
        //pe pagina user-page se va putea accesa atributul userList cu valoarea pe care o stocheaza el
    }

    @GetMapping(path = "/user/add")
    public String showAddAuthorPage(Model model){
        model.addAttribute("user", new Users());
        System.out.println("Go to page add-user");
        return "add-user.html";
    }

    @PostMapping(path="/user/add")
    public String saveNewUser(@Valid Users users, BindingResult bindingResult){
        System.out.println("Users: " + users);//cand afisam un string + un obiect stie automat sa sa apeleze toString pentru obiect
        if(bindingResult.hasErrors()){
            System.out.println("Error when trying to insert");
            return "add-user";
        }
        users.setRole("user");
        userService.saveUser(users);
            System.out.println("New users was saved!");
        return "redirect:/login";
    }
}
