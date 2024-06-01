package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenFacebookPageStrategy;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenInstagramProfile;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenWhatsAppConversationStrategy;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.SendEmailStrategy;
import com.unicauca.clientproducthttpclient.domain.entities.Category;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.services.CategoryService;
import com.unicauca.clientproducthttpclient.domain.services.IItemService;
import com.unicauca.clientproducthttpclient.domain.services.ItemService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

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
    private Button btnProductos;

    @FXML
    private Button btnProductos1;

    @FXML
    private Button btnWhatsapp;

    @FXML
    private ComboBox<String> cboCategorias;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private TextField txtBuscarCatNombre;


    private List<Item>items=new ArrayList<>();


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
        initializeCboCategorias();
        items=getData();

        int column = 0;
        int row = 1;

        try {
            for(int i = 0; i < this.items.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(this.getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = (AnchorPane)fxmlLoader.load();
                ItemController itemController = (ItemController)fxmlLoader.getController();
                itemController.setData(this.items.get(i));
                if (column == 1) {
                    column = 0;
                    ++row;
                }

                this.grid.add(anchorPane, column++, row);
                this.grid.setMinWidth(-1.0);
                this.grid.setPrefWidth(-1.0);
                this.grid.setMaxWidth(Double.NEGATIVE_INFINITY);
                this.grid.setMinHeight(-1.0);
                this.grid.setPrefHeight(-1.0);
                this.grid.setMaxHeight(Double.NEGATIVE_INFINITY);
                GridPane.setMargin(anchorPane, new Insets(10.0));
            }
        } catch (IOException var9) {
            IOException e = var9;
            e.printStackTrace();
        }



    }

    public void initializeCboCategorias() {
        List<Category> categoryList = new CategoryService().findAll();
        ObservableList<String> categorias = FXCollections.observableArrayList();
        categorias.add("Todas");
        for(Category category:categoryList){
            categorias.add(category.getName());
        }
        cboCategorias.setItems(categorias);
    }

    public List<Item> getData(){
        IItemService iItemService=new ItemService();
        List<Item> items=iItemService.obtenerItems();
        return items;
    }




}
