package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeAdminController  {


    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnCategorias;
    @FXML
    private Label lblUsuario;
    @FXML
    private AnchorPane pnlInicio;
    @FXML
    private AnchorPane pnlProductos;



    public void setLblUsuario(String text){
        lblUsuario.setText(text);
    }



    public void btnOnActionInicio(ActionEvent event){
        pnlProductos.setVisible(false);
        pnlInicio.setVisible(true);
    }
    public void btnOnActionProductos(ActionEvent event) throws IOException {
        pnlInicio.setVisible(false);
        pnlProductos.setVisible(true);
    }
    public void btnOnActionCerrarSesion(ActionEvent event) {
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        Utilities.cargarFXML("/views/login.fxml","Login");
        stage.close();
    }
}
