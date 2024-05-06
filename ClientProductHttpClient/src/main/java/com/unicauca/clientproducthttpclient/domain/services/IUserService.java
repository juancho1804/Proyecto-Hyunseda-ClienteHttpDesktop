package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.domain.entities.User;

import java.util.ArrayList;

public interface IUserService {
    public ArrayList<User> getUsers();
    public User save(User newUser);
    public User findById(Long id);
    public ArrayList<User>findByName(String name);
    public User updateById(User newUser, long id);
    public boolean deleteById(Long id);
    public boolean validateUser(String username, String password);
}
