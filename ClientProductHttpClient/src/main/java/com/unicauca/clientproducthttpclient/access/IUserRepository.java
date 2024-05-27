package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.util.Resultado;

import java.util.List;

public interface IUserRepository {
    public Resultado registerUser(User user);

    public Resultado validateUser(User user);

    public List<User> findAll();

}
