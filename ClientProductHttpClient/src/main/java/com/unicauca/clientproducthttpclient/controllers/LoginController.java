package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.Main;
import com.unicauca.clientproducthttpclient.domain.entities.Role;
import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.domain.services.IUserService;
import com.unicauca.clientproducthttpclient.domain.services.UserService;
import com.unicauca.clientproducthttpclient.util.Resultado;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {

    private final IUserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    @FXML
    private Button btnLogin;
    @FXML
    private Button btnRegister;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblMessageResponse;


    public void loginButtonOnAction(ActionEvent event) throws IOException {
        User user=new User();
        user.setUsername(txtUsername.getText());
        user.setPassword(txtPassword.getText());

        Resultado resultado=userService.validateUser(user);
        if (resultado!=null){
            if(resultado.getMsg().equals("ADMIN")){

                // Carga el FXML del HomeAdminController
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/homeAdmin.fxml"));
                Parent root = loader.load();
                HomeAdminController controller = loader.getController();
                controller.setLblBienvenido("Bienvenido, " + txtUsername.getText());
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Hyunseda Administrador");
                stage.show();

                cerrarVentana();
            }else{
                lblMessageResponse.setText("Ha iniciado sesión correctamente como user");
                lblMessageResponse.setTextFill(Color.GREEN);
            }

        }else{
            lblMessageResponse.setText(" ¡ Usuario o contraseña incorrecta/s !");
        }
    }

    public String validarRol(User user){
        if(user.getRoleModel()== Role.ADMIN){
            return "ADMIN";
        }
        return "USER";
    }

    public void registerButtonOnAction(ActionEvent event){
        Utilities.cargarFXML("/views/register.fxml", "Registro");
        cerrarVentana();
    }

    public void cerrarVentana(){
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }







}
