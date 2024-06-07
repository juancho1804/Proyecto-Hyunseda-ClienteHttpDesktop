package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenFacebookPageStrategy;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenInstagramProfile;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenWhatsAppConversationStrategy;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.SendEmailStrategy;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Order;
import com.unicauca.clientproducthttpclient.domain.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.unicauca.clientproducthttpclient.access.UserRestRepository.usuarioIngresado;

@Data
public class OrdersController extends Window implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button btnVolver;

    @FXML
    private Button btnEmail;

    @FXML
    private Button btnFacebook;

    @FXML
    private Button btnInstagram;

    @FXML
    private Button btnWhatsapp;

    @FXML
    private Button btnMinimize;

    @FXML
    private GridPane grid;

    @FXML
    private Stage stage;
    private HomeUserController homeUserController;
    private IOrderService orderService = new OrderService();
    private List<Order> orders=new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.orders.addAll(this.getData());
        btnClose.setOnAction(this::btnOnActionClose);
        btnMinimize.setOnAction(this::btnOnActionMinimize);
        btnWhatsapp.setOnAction(new OpenWhatsAppConversationStrategy()::execute);
        btnFacebook.setOnAction(new OpenFacebookPageStrategy()::execute);
        btnInstagram.setOnAction(new OpenInstagramProfile()::execute);
        btnEmail.setOnAction(new SendEmailStrategy()::execute);
        btnVolver.setOnAction(this::btnOnActionVolver);
        updateGridWithResults(orders);
    }

    public List<Order> getData(){
        List<Order> orders=orderService.getOrdersByUsername(usuarioIngresado.getUsername());
        if(orders.isEmpty()){
            return new ArrayList<>();
        }
        return orders;
    }

    private void updateGridWithResults(List<Order> searchResults) {
        // Limpiar el grid antes de agregar los nuevos resultados
        grid.getChildren().clear();

        int column = 0;
        int row = 1;

        try {
            for(int i = 0; i < searchResults.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/order.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                OrderController orderController = fxmlLoader.getController();
                orderController.setData(searchResults.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(-1.0);
                grid.setPrefWidth(-1.0);
                grid.setMaxWidth(Double.NEGATIVE_INFINITY);
                grid.setMinHeight(-1.0);
                grid.setPrefHeight(-1.0);
                grid.setMaxHeight(Double.NEGATIVE_INFINITY);
                GridPane.setMargin(anchorPane, new Insets(10.0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnOnActionVolver(ActionEvent event)  {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
        homeUserController.getStage().show();

    }
}
