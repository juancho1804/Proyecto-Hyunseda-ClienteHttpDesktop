package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.designpatterns.observer.Observer;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.services.ShoppingCartService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VerCarritoController extends Window implements Initializable, Observer {
    @Getter
    @Setter
    private HomeUserController homeUserController;
    @FXML
    private Button btnVolver;
    @FXML
    private GridPane grid;
    @FXML
    private Label lblTotal;

    private DoubleProperty totalProperty = new SimpleDoubleProperty(0.0);

    private ShoppingCartService shoppingCartService;
    private List<Item>items=new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!items.isEmpty()) {
            updateGridWithResults(items);
            System.out.println(items.get(0));
            lblTotal.textProperty().bind(Bindings.createStringBinding(
                    () -> String.format("%.2f", totalProperty.get()),
                    totalProperty
            ));
        }

        btnClose.setOnAction(this::btnOnActionClose);
        btnMinimize.setOnAction(this::btnOnActionMinimize);
        this.btnVolver.setOnAction(this::btnOnActionVolver);
    }

    @FXML
    public void btnOnActionVolver(ActionEvent event)  {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
        homeUserController.getStage().show();

    }

    private void updateGridWithResults(List<Item> searchResults) {
        // Limpiar el grid antes de agregar los nuevos resultados
        grid.getChildren().clear();

        int column = 0;
        int row = 1;

        try {
            for(int i = 0; i < searchResults.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.getPnlParaShopping().setVisible(false);
                itemController.getPnlVerCarrito().setVisible(true);
                itemController.setData(searchResults.get(i));
                itemController.setShoppingCartService(shoppingCartService);
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


    @Override
    public void update(Object o) {
        this.shoppingCartService=(ShoppingCartService)o;
        if(shoppingCartService.getShoppingCart().getItems().isEmpty()){
            this.items=new ArrayList<>();
            System.out.println("La lista esta vacia");
            totalProperty.set(0);
        }else {
            this.items=shoppingCartService.getShoppingCart().getItems();
            //items.addAll(this.getData());
            System.out.println("--ACTUALIZACION DE OBSERVER-------------------");

            for (Item item : this.items) {
                System.out.println(item.getProduct().getName());
                System.out.println(item.getCantidad());
            }
            System.out.println("Total =" + shoppingCartService.obtenerTotal());

            System.out.println("--finalizacion OBSEVRER-------------------------");
            double newTotal=shoppingCartService.obtenerTotal();
            totalProperty.set(newTotal);
        }

    }
}
