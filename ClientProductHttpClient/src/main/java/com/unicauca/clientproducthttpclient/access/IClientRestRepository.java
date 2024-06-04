package com.unicauca.clientproducthttpclient.access;

import com.unicauca.clientproducthttpclient.domain.entities.Client;
import com.unicauca.clientproducthttpclient.domain.entities.User;

public interface IClientRestRepository {
    public User findByUsername();
    public Client crearCliente(Client client);
    public Client findClient();
    public Client updateClient(Client client);
}
