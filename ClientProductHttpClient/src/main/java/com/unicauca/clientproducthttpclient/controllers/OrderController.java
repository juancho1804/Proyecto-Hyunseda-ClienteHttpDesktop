package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.access.IOrderRepository;
import com.unicauca.clientproducthttpclient.access.OrderRestRepository;
import com.unicauca.clientproducthttpclient.designpatterns.observerOrder.OrderObserver;
import com.unicauca.clientproducthttpclient.domain.entities.Order;
import com.unicauca.clientproducthttpclient.domain.services.IOrderService;
import com.unicauca.clientproducthttpclient.domain.services.OrderService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.Data;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Data
public class OrderController implements OrderObserver {
    private IOrderService orderService;


    @FXML
    private Button btnDescargarRecibo;

    @FXML
    private Label lblEstado;

    @FXML
    private Label lblFechaOrden;

    @FXML
    private Label lblIdOrden;

    private Order order;



    public void setData(Order order){
        this.order = order;
        order.solicitado();
        order.addObserver(this);
        lblIdOrden.setText(order.getId().toString());
        lblEstado.setText(order.getState().estadoPedido());
        lblFechaOrden.setText(order.getDate());
        this.btnDescargarRecibo.setOnAction(this::btnOnActionDescargarRecibo);


    }

    public void btnOnActionDescargarRecibo(ActionEvent actionEvent) {
        try {
            URI uri = new URI(order.getItems());
            java.awt.Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOrderStateChanged(Order order) {

    }
}
