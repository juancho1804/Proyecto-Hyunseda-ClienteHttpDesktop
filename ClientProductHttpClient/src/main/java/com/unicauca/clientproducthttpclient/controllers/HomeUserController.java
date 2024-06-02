package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenFacebookPageStrategy;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenInstagramProfile;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenWhatsAppConversationStrategy;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.SendEmailStrategy;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

public class HomeUserController extends Window implements Initializable {
    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnEmail;

    @FXML
    private Button btnFacebook;

    @FXML
    private Button btnInstagram;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnWhatsapp;

    @FXML
    private Button btnVerCarrito;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField txtBuscarItem;

    @FXML
    private Button btnBuscar;
    @FXML
    @Getter
    @Setter
    private Stage stage;

    private double xOffset=0;
    private double yOffset=0;



    private IShoppingCartService shoppingCartService = new ShoppingCartService();
    private List<Item>items=new ArrayList<>();
    VerCarritoController carritoController = new VerCarritoController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.items.addAll(this.getData());
        btnClose.setOnAction(this::btnOnActionClose);
        btnMinimize.setOnAction(this::btnOnActionMinimize);
        btnCerrarSesion.setOnAction(this::btnOnActionCerrarSesion);
        btnWhatsapp.setOnAction(new OpenWhatsAppConversationStrategy()::execute);
        btnFacebook.setOnAction(new OpenFacebookPageStrategy()::execute);
        btnInstagram.setOnAction(new OpenInstagramProfile()::execute);
        btnEmail.setOnAction(new SendEmailStrategy()::execute);
        btnVerCarrito.setOnAction(this::btnOnActionVerCarrito);
        updateGridWithResults(items);
        btnBuscar.setOnAction(this::handleSearch);
        this.shoppingCartService.addObserver(carritoController);


    }


    public List<Item> getData(){
        IItemService iItemService=new ItemService();
        List<Item> items=iItemService.obtenerItems();
        return items;
    }

    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = txtBuscarItem.getText().toLowerCase(); // Convertir a minúsculas para una comparación sin distinción entre mayúsculas y minúsculas
        List<Item> searchResults = buscarPorNombre(searchText);
        updateGridWithResults(searchResults);
    }

    public List<Item> buscarPorNombre(String nombreProducto){
        List<Item>itemsPorNombre=new ArrayList<>();
        for(Item item:this.items){
            if(item.getProduct().getName().toLowerCase().contains(nombreProducto.toLowerCase())){
                itemsPorNombre.add(item);
            }
        }
        return itemsPorNombre;
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
                itemController.setData(searchResults.get(i));
                itemController.getPnlVerCarrito().setVisible(false);
                itemController.getPnlParaShopping().setVisible(true);
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


    public void btnOnActionVerCarrito(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HomeUserController.class.getResource("/views/verCarrito.fxml"));
            //VerCarritoController carritoController = new VerCarritoController();
            fxmlLoader.setController(this.carritoController);
            this.carritoController.setHomeUserController(this);
            Scene scene = new Scene(fxmlLoader.load(), 1216, 714);
            scene.setOnMousePressed(event1 -> {
                xOffset = event1.getSceneX();
                yOffset = event1.getSceneY();
            });
            scene.setOnMouseDragged(event1 -> {
                Stage stage = (Stage) scene.getWindow();
                stage.setX(event1.getScreenX() - xOffset);
                stage.setY(event1.getScreenY() - yOffset);
            });
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            cerrarVentana();

        } catch (IOException var9) {
            IOException e = var9;
            e.printStackTrace();
        }
    }

    public void cerrarVentana(){
        Stage stage = (Stage) btnEmail.getScene().getWindow();
        stage.close();
    }




}
