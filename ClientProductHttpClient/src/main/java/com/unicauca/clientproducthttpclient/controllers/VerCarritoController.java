package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.designpatterns.observer.Observer;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.services.IItemService;
import com.unicauca.clientproducthttpclient.domain.services.ShoppingCartService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

    private ShoppingCartService shoppingCartService;
    private List<Item>items=new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // this.items.addAll(this.getData());
        if(items.isEmpty()){
            System.out.println("vacia");
        }else{
            updateGridWithResults(items);
            System.out.println(items.get(0));
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

    public List<Item>getData(){
        if(items.isEmpty()){
            return null;
        }
        return this.shoppingCartService.getShoppingCart().getItems();

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
        /*
        ShoppingCartService shoppingCartService=(ShoppingCartService)o;
        System.out.println("hola");
        List<Item> items= shoppingCartService.getShoppingCart().getItems();
        for(Item item:items){
            System.out.println(item.getProduct().getName());
            System.out.println(item.getCantidad());
        }
        System.out.println("Total ="+shoppingCartService.obtenerTotal());

         */
        this.shoppingCartService=(ShoppingCartService)o;
        if(shoppingCartService.getShoppingCart().getItems().isEmpty()){
            this.items=new ArrayList<>();
            System.out.println("La lista esta vacia");
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
        }

    }
}
