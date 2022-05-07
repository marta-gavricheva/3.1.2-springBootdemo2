package com.example.springBootdemo2.controller;

import com.example.springBootdemo2.model.User;
import com.example.springBootdemo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String usersALL(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/users";
    }


//    @GetMapping("/{id}")
//    public String usersId(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.getUserId(id));
//        return "userInf";
//    }


//    @GetMapping("/new")
//    public String addUser(User user) {
//        return "create";
//    }
//
//    @PostMapping("/new")
//    public String add(@ModelAttribute("user") User user) {
//        userService.addUser(user);
//        return   "redirect:/admin";
//
//    }

    @GetMapping(value = "/new")
    public String addUserForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", userService.findAllRoles());

        return "create";
    }


     @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }



    @GetMapping("/edit/{id}")
    public String updateUser(Model model, @PathVariable("id") long id) {
        model.addAttribute(userService.getUserId(id));
        return "edit";
    }


    @PostMapping("/edit")
    public String update(@Valid @ModelAttribute("user") User user,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            userService.updateUser(user);
            return "redirect:/admin";
        }
    }
}
