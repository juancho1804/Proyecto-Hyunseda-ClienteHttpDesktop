package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.User;

public interface IUserRepository {
    public User registerUser(User user);

    public boolean validateUser(User user);

}
