package com.sci.razvan.proiectfinal.controller;

import com.sci.razvan.proiectfinal.model.User;
import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Iterator;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(path = "")
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

    @GetMapping(path = "/add")
    public String showAddAuthorPage(Model model){
        model.addAttribute("user", new User());
        System.out.println("Go to page add-user");
        return "add-user";
    }

    @PostMapping(path="/add")
    public String saveNewUser(@Valid User user, BindingResult bindingResult){
        System.out.println("User: " + user);//cand afisam un string + un obiect stie automat sa sa apeleze toString pentru obiect
        if(bindingResult.hasErrors()){
            System.out.println("Error when trying to insert");
            return "add-user";
        }
        userService.saveUser(user);
            System.out.println("New user was saved!");
        return "redirect:/user/login";
    }
}
