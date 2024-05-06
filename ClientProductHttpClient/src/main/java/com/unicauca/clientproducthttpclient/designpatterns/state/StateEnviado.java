package com.unicauca.clientproducthttpclient.designpatterns.state;

public class StateEnviado extends State{
    @Override
    public String estadoPedido() {
        return ("El pedido ha sido enviado");
    }
}
