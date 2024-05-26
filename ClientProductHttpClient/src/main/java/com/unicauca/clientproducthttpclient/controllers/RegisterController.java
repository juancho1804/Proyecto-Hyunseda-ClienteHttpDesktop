package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.domain.services.IUserService;
import com.unicauca.clientproducthttpclient.domain.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        if(txtNombre.getText().isBlank()||txtApellidos.getText().isBlank()||txtUsuario.getText().isBlank()||txtContrasena.getText().isBlank()){
            lblMessage.setText("Verifique que todos los campos esten llenos.");
        }else{
            User user = new User();
            user.setFirstName(txtNombre.getText());
            user.setLastName(txtApellidos.getText());
            user.setUsername(txtUsuario.getText());
            user.setPassword(txtContrasena.getText());
            userService.registerUser(user);
        }

    }

}
