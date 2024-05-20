package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.domain.services.IUserService;
import com.unicauca.clientproducthttpclient.domain.services.UserService;

public class UserController {
    private final IUserService userService;
    public UserController() {
        this.userService = new UserService();
    }
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    public User registerUser(User user) {
        return this.userService.registerUser(user);
    }
    public boolean validateUser(User user) {
        return this.userService.validateUser(user);
    }
}
