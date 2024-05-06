package com.unicauca.clientproducthttpclient.designpatterns;

public class StateEnviado extends State{
    @Override
    public String estadoPedido() {
        return ("El pedido ha sido enviado");
    }
}
