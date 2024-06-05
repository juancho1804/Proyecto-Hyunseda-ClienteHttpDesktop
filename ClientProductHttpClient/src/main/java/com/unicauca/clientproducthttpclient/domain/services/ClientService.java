package com.unicauca.clientproducthttpclient.domain.services;

import com.unicauca.clientproducthttpclient.access.ClientRestRepository;
import com.unicauca.clientproducthttpclient.access.IClientRestRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Client;

public class ClientService implements IClientService{
    IClientRestRepository clientRestRepository=new ClientRestRepository();

    @Override
    public Client guardarCliente(Client client) {
        return clientRestRepository.crearCliente(client);
    }
}
