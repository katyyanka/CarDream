package org.bsuir.coursework.controller;

import org.bsuir.coursework.model.User;
import org.bsuir.coursework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user/user-list.html";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user/user-create.html";
    }


    @PostMapping("/user-create")
    public String createUser(User user){
        try {
            userService.saveUser(user);
        } catch (Exception exception) {
            return "redirect:/400";
        }
        return "redirect:/users";
    }

    @GetMapping("/registration")
    public String createRegistrationForm(User user){  return "registration.html"; }

    @PostMapping("/registration")
    public String registration(User user){
        try {
            userService.saveUser(user);
            return "redirect:/thanks-page";
        } catch (Exception e) {
            return "redirect:/400";
        }
    }

    @GetMapping("/thanks-page")
    public String thank(){
        return "errors/thank.html";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") String id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") String id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/user-update.html";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/users";
    }
}
