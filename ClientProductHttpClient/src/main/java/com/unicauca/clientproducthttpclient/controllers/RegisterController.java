package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.Main;
import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.domain.services.IUserService;
import com.unicauca.clientproducthttpclient.domain.services.UserService;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegisterController {

    private final IUserService userService;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtContrasena;
    @FXML
    private Button btnRegistrarse;
    @FXML
    private Label lblMessage;
    @FXML
    private Button btnCerrar;

    public RegisterController() {
        this.userService = new UserService();
    }

    public void registrarseButtonOnAction(ActionEvent actionEvent) {
        User user = new User();
        user.setFirstName(txtNombre.getText());
        user.setLastName(txtApellidos.getText());
        user.setUsername(txtUsuario.getText());
        user.setPassword(txtContrasena.getText());

        if(validarCampos()){
            if(userService.registerUser(user)!=null){
                lblMessage.setTextFill(Color.GREEN);
                lblMessage.setText("Usuario registrado satisfactoriamente");
            }else{
                Utilities.mostrarAlerta("Error","El usuario ya existe");
            }
        }
    }

    public boolean validarCampos(){
        if(txtNombre.getText().isBlank()||txtApellidos.getText().isBlank()||txtUsuario.getText().isBlank()||txtContrasena.getText().isBlank()){
            lblMessage.setText("Verifique que todos los campos esten llenos.");
            return false;
        }
        return true;
    }

    public void btnCerrarOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/views/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 400);
            stage.setTitle("Hyun Seda");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
