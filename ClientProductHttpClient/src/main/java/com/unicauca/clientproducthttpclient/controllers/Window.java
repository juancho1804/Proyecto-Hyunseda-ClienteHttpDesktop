package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static com.unicauca.clientproducthttpclient.access.UserRestRepository.usuarioIngresado;

public class Window {
    @FXML
    public Button btnMinimize;
    @FXML
    public Button btnClose;
    @FXML
    public Label lblUsuario;
    @FXML
    public Button btnCerrarSesion;

    // Metodos minimizar y cerrar ventana
    public void btnOnActionMinimize(ActionEvent event){
        Stage stage=(Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
    public void btnOnActionClose(ActionEvent event){
        System.exit(0);
    }
    public void btnOnActionCerrarSesion(ActionEvent event) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        Utilities.cargarFXML("/views/login.fxml","Login");
        stage.close();
    }
    public void initializelblUsuario(){
        this.lblUsuario.setText(usuarioIngresado.getUsername());
    }
}
