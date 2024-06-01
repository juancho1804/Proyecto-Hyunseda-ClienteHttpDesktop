package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenFacebookPageStrategy;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenInstagramProfile;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.OpenWhatsAppConversationStrategy;
import com.unicauca.clientproducthttpclient.designpatterns.strategy.SendEmailStrategy;
import com.unicauca.clientproducthttpclient.domain.entities.Category;
import com.unicauca.clientproducthttpclient.domain.services.CategoryService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnClose.setOnAction(this::btnOnActionClose);
        btnMinimize.setOnAction(this::btnOnActionMinimize);
        btnCerrarSesion.setOnAction(this::btnOnActionCerrarSesion);
        btnWhatsapp.setOnAction(new OpenWhatsAppConversationStrategy()::execute);
        btnFacebook.setOnAction(new OpenFacebookPageStrategy()::execute);
        btnInstagram.setOnAction(new OpenInstagramProfile()::execute);
        btnEmail.setOnAction(new SendEmailStrategy()::execute);
        initializeCboCategorias();


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


}
