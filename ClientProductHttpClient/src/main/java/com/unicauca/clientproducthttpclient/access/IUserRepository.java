package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.util.Resultado;

public interface IUserRepository {
    public User registerUser(User user);

    public Resultado validateUser(User user);

}
