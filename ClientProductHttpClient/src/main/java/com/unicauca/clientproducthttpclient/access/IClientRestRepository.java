package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Client;
import com.unicauca.clientproducthttpclient.domain.entities.User;

public interface IClientRestRepository {
    User findByUsername();
    Client crearCliente(Client client);
}
