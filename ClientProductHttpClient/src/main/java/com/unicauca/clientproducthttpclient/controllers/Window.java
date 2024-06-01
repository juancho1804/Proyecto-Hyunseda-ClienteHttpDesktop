package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void btnOnActionInicio(ActionEvent event){
        try {
            // Cargar el archivo FXML con el nuevo controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/homeAdmin.fxml"));
            loader.setController(new HomeAdminController()); // Establecer el nuevo controlador
            Parent root = loader.load();

            // Configurar la nueva escena
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnMinimize.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnOnActionProductos(ActionEvent event) {

        try {
            // Cargar el archivo FXML con el nuevo controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/homeAdmin.fxml"));
            loader.setController(new ProductController()); // Establecer el nuevo controlador
            Parent root = loader.load();

            // Configurar la nueva escena
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnMinimize.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnOnActionCategorias(ActionEvent event) {

        try {
            // Cargar el archivo FXML con el nuevo controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/homeAdmin.fxml"));
            loader.setController(new CategoryController()); // Establecer el nuevo controlador
            Parent root = loader.load();

            // Configurar la nueva escena
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnMinimize.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initializelblUsuario(){
        this.lblUsuario.setText(usuarioIngresado.getUsername());
    }


/*
    public  void openInstagramProfile(String username) {
        try {
            URI uri = new URI("https://www.instagram.com/" + username);
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

    public  void openFacebookPage(String pageName) {
        try {
            URI uri = new URI("https://www.facebook.com/" + pageName);
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

    public  void openEmail(String emailAddress) {
        try {
            URI uri = new URI("mailto:" + emailAddress);
            Desktop.getDesktop().browse(uri);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

 */
}
