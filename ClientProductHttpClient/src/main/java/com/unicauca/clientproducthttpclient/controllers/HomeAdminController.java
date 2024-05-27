package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeAdminController  implements Initializable{

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.pnlProductos.setVisible(false);
        this.pnlInicio.setVisible(true);
        this.btnProductos.setOnAction(this::btnOnActionProductos);
        this.btnInicio.setOnAction(this::btnOnActionInicio);
        this.btnCerrarSesion.setOnAction(this::btnOnActionCerrarSesion);
    }

    public void setLblUsuario(String text){
        lblUsuario.setText(text);
    }



    public void btnOnActionInicio(ActionEvent event){
        pnlProductos.setVisible(false);
        pnlInicio.setVisible(true);
    }
    public void btnOnActionProductos(ActionEvent event) {

        try {
            // Cargar el archivo FXML con el nuevo controlador
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/homeAdmin.fxml"));
            loader.setController(new ProductController()); // Establecer el nuevo controlador
            Parent root = loader.load();
            ProductController controller = loader.getController();
            controller.setLblUsuario(this.lblUsuario.getText());

            // Configurar la nueva escena
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnProductos.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void btnOnActionCerrarSesion(ActionEvent event) {
        Stage stage = (Stage) btnCerrarSesion.getScene().getWindow();
        Utilities.cargarFXML("/views/login.fxml","Login");
        stage.close();
    }


}
