package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.User;

public interface IUserRepository {
    public User createUser(User user);
    public boolean validateUser(String  username, String password);

}
