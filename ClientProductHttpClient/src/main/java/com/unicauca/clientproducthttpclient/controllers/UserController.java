package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.domain.services.IUserService;
import com.unicauca.clientproducthttpclient.domain.services.UserService;

public class UserController {
    private IUserService userService;
    public UserController() {
        this.userService = new UserService();
    }
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    public User create(User user) {
        return this.userService.save(user);
    }
    public boolean validateUser(String username, String password) {
        return this.userService.validateUser(username, password);
    }
}
