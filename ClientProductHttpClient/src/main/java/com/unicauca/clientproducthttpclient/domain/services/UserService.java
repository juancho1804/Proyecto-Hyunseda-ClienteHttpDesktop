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
    public User registerUser(User newUser) {
        return this.userRepository.registerUser(newUser);
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }


    @Override
    public boolean validateUser(User user) {
        return this.userRepository.validateUser(user);
    }
}
