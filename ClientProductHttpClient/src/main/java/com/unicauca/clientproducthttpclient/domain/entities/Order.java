/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.unicauca.clientproducthttpclient.designpatterns.state.State;
import com.unicauca.clientproducthttpclient.designpatterns.state.StateEntregado;
import com.unicauca.clientproducthttpclient.designpatterns.state.StateEnviado;
import com.unicauca.clientproducthttpclient.designpatterns.state.StateSolicitado;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Juan
 */
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Order {

    @JsonProperty("id")
    Long id;
    @JsonProperty("date")
    String date;
    @JsonProperty("state")
    State state;
    @JsonProperty("items")
    String items;
    @JsonProperty("idClient")
    Integer idClient;


    public Order(Integer idClient, List<Item>items) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.date=fechaHoraActual.format(formato);
        this.idClient=idClient;
        this.items=generateReceipt(items);
        solicitado();
    }
    public void solicitado(){
        this.state=new StateSolicitado();
    }
    public void enviar(){
        this.state= new StateEnviado();
    }
    public void entregar(){
        this.state=new StateEntregado();
    }

    public String generateReceipt(List<Item> items) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Producto\t\t\t\tCantidad\t\tSubtotal\n"); // Ajuste de las tabulaciones
        //receipt.append("-------------------------------------------------\n"); // Se ajusta el número de guiones

        // Encontrar la longitud máxima del nombre del producto
        int maxLength = 0;
        for (Item item : items) {
            int length = item.getProduct().getName().length();
            if (length > maxLength) {
                maxLength = length;
            }
        }

        // Construir el recibo
        for (Item item : items) {
            String productName = item.getProduct().getName();
            // Calcular el número de tabulaciones necesarias para mantener el formato
            int tabs = (int)Math.ceil((double)(maxLength - productName.length()) / 8); // 8 es el ancho de una tabulación en caracteres
            receipt.append(productName);
            // Añadir las tabulaciones necesarias para alinear la columna de cantidad
            for (int i = 0; i < tabs + 2; i++) { // Se añaden dos tabulaciones adicionales para ajustar la apariencia
                receipt.append("\t");
            }
            receipt.append(item.getCantidad()).append("\t\t");
            receipt.append("$").append(item.getSubtotal()).append(" COP\n");
        }

        return receipt.toString();
    }


    
    
}
