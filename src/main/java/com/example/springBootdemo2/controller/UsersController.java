package com.example.springBootdemo2.controller;

import com.example.springBootdemo2.model.User;
import com.example.springBootdemo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.imageio.spi.ServiceRegistry;
import javax.validation.Valid;
import java.security.Principal;

@Controller

 public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/{id}")
    public String usersId(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserId(id));
        return "userInf";
    }
}