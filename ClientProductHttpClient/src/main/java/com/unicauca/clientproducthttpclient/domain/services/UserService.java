package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.access.IUserRepository;
import com.unicauca.clientproducthttpclient.access.UserRestRepository;
import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.util.Resultado;

import java.util.List;


public class UserService implements IUserService {
    private IUserRepository userRepository;

    public UserService() {
        this.userRepository=new UserRestRepository();
    }
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Resultado registerUser(User newUser) {
        return this.userRepository.registerUser(newUser);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }


    @Override
    public Resultado validateUser(User user) {
        return userRepository.validateUser(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
