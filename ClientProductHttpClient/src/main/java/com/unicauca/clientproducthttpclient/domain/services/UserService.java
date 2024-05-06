package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.access.IUserRepository;
import com.unicauca.clientproducthttpclient.access.UserRestRepository;
import com.unicauca.clientproducthttpclient.domain.entities.User;

import java.util.ArrayList;

public class UserService implements IUserService {
    private IUserRepository userRepository;

    public UserService() {
        this.userRepository=new UserRestRepository();
    }
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public ArrayList<User> getUsers() {
        return null;
    }

    @Override
    public User save(User newUser) {
        return this.userRepository.createUser(newUser);
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public ArrayList<User> findByName(String name) {
        return null;
    }

    @Override
    public User updateById(User newUser, long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean validateUser(String username, String password) {
        return this.userRepository.validateUser(username,password);
    }
}
