package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.util.Resultado;

import java.util.List;

public interface IUserService {
    public Resultado registerUser(User newUser);
    public User findByUsername(String username);
    public Resultado validateUser(User user);
    public List<User> findAll();
}
