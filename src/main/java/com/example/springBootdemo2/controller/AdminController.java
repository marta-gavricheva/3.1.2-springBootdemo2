package com.example.springBootdemo2.controller;

import com.example.springBootdemo2.model.Role;
import com.example.springBootdemo2.model.User;
import com.example.springBootdemo2.service.RoleService;
import com.example.springBootdemo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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


    @GetMapping("/new")
    public String addUser(User user) {
        return "create";
    }

    @PostMapping("/new")
    public String add(@RequestParam("userName") String userName,
                      @RequestParam("email") String email,
                      @RequestParam("password") String password,
                      @RequestParam(required = false, name = "ROLE_ADMIN") String roleAdmin,
                      @RequestParam(required = false, name = "ROLE_USER") String roleUser) {

        Set<Role> roles = new HashSet<>();

        if (roleAdmin != null) {
            roles.add(roleService.getByName("ROLE_ADMIN"));
        }
        if (roleUser != null) {
            roles.add(roleService.getByName("ROLE_USER"));
        }
        if (roleAdmin == null && roleUser == null) {
            roles.add(roleService.getByName("ROLE_USER"));
        }

        User user = new User(userName, email, password, roles);
        user.setRoles(roles);

        try {
            userService.addUser(user);
        } catch (Exception ignored) {

        }
        return "redirect:/admin";
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
    public String editUser(@ModelAttribute("user") User user, @PathVariable("id") int id,
                           @RequestParam(required = false, name = "ROLE_ADMIN") String roleAdmin,
                           @RequestParam(required = false, name = "ROLE_USER") String roleUser) {

        Set<Role> roles = new HashSet<>();

        if (roleAdmin != null) {
            roles.add(roleService.getByName("ROLE_ADMIN"));
        }
        if (roleUser != null) {
            roles.add(roleService.getByName("ROLE_USER"));
        }
        if (roleAdmin == null && roleUser == null) {
            roles.add(roleService.getByName("ROLE_USER"));
        }

        user.setRoles(roles);

        userService.updateUser(user);

        return "redirect:/admin";
    }
}