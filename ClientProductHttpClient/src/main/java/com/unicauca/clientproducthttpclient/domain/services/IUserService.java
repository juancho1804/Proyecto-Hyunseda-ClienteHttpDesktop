package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.User;

import java.util.ArrayList;

public interface IUserService {
    public User registerUser(User newUser);
    public User findByUsername(String username);
    public boolean validateUser(User user);
}
