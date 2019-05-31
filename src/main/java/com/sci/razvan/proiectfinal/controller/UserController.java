package com.sci.razvan.proiectfinal.controller;
import com.sci.razvan.proiectfinal.model.Users;
import com.sci.razvan.proiectfinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Iterator;

/**
 *
 * @author Rzv Bineata
 * 22/05/2019
 */

@RestController
public class UserController  {
    @Autowired
    UserService userService;

    /**
     *this endpoint is called when the add new user page is loaded
     * a new object user will be sent to the add-user page
     *
     * @param model
     * @return ModelAndView
     */
    @GetMapping(path = "user/add")
    public ModelAndView showAddAuthorPage(Model model){
        ModelAndView mv = new ModelAndView("add-user");
        mv.addObject("user", new Users());
        return mv;
    }

    /**
     *this endpoint is called when the show user profile page is loaded
     * an object with the current user information is sent to the user-profil page
     *
     * @param model
     * @return ModelAndView
     */
    @GetMapping(path = "user/profile")
    public ModelAndView showUserDetails(Model model){
        ModelAndView mv = new ModelAndView("user-profil");
        mv.addObject("user", getCurrentUser());
        return mv;
    }

    /**
     * this endpoint is called when the user press save button from user profile page
     * the updated user information will se saved in DB
     *
     * @param user
     * @param bindingResult
     * @return ModelAndView
     */
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

    /**
    * this endpoint is called when the user press add new user button from new user page
    * the user will se saved in DB
     *
     * @param user
     * @param bindingResult
     * @return ModelAndView
     */
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

    /**
     * this method returns the current user that is logged in the application
     * @return Users
     */
    private Users getCurrentUser(){
        //returns authenticated user with his details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return userService.searchIfUserExist(currentUserName);
        }
        return null;
    }

    /**
     * this endpoint is called when user acces /user url
     * on the page will be displayed a list with all existing users with their details
     *
     * @return ModelAndView
     */
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
}
