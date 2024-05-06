package com.unicauca.clientproducthttpclient.designpatterns;

public class StateSolicitado extends State {
    public StateSolicitado() {
    }

    @Override
    public String estadoPedido() {
        return ("Pedido Solicitado");
    }
}
