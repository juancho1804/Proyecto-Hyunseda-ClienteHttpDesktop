package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.access.IUserRepository;
import com.unicauca.clientproducthttpclient.access.UserRestRepository;
import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.util.Resultado;


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
    public Resultado validateUser(User user) {
        if(user.getUsername().isEmpty() ||user.getPassword()==null){
            return null;
        }
        return this.userRepository.validateUser(user);
    }
}
