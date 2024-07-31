package com.apiweb.redsocial.infrastructure.api.controller;


import com.apiweb.redsocial.application.service.UserService;
import com.apiweb.redsocial.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operaciones relacionadas con el usuario.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user.getName());
    }

    @PostMapping("/{followerName}/follow/{followeeName}")
    public String followUser(@PathVariable String followerName, @PathVariable String followeeName) {
        userService.followUser(followerName, followeeName);
        return followerName + " empezó a seguir a " + followeeName;
    }

    @GetMapping("/{userName}/wall")
    public List<String> getWall(@PathVariable String userName) {
        return userService.getWall(userName);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
