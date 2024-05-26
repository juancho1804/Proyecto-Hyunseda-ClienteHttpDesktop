package com.unicauca.clientproducthttpclient.util;

public class Resultado {
    private boolean exito;
    private String msg;

    public Resultado(){
    }
    public Resultado(boolean exito, String msg) {
        this.exito = exito;
        this.msg = msg;
    }

    public boolean isExito() {
        return exito;
    }

    public String getMsg() {
        return msg;
    }
}
