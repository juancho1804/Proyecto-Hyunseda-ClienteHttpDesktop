package com.unicauca.clientproducthttpclient.util;

public class Resultado {
    private boolean exito;
    private String msg;
    private String token;
    private String username;

    public Resultado(){
    }
    public Resultado(boolean exito, String msg) {
        this.exito = exito;
        this.msg = msg;
    }

    public Resultado(boolean exito, String msg,String token) {
        this.exito = exito;
        this.msg = msg;
        this.token = token;
    }

    public Resultado(boolean exito, String msg,String token,String username) {
        this.exito = exito;
        this.msg = msg;
        this.token = token;
        this.username = username;
    }

    public String getToken() {
        return token;
    }


    public boolean isExito() {
        return exito;
    }

    public String getMsg() {
        return msg;
    }
    public String getUsername(){
        return username;
    }
}
