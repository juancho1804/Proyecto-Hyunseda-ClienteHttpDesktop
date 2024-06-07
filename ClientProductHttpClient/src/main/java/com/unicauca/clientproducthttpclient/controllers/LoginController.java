package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.access.ClientRestRepository;
import com.unicauca.clientproducthttpclient.access.IOrderRepository;
import com.unicauca.clientproducthttpclient.access.OrderRestRepository;
import com.unicauca.clientproducthttpclient.access.UserRestRepository;
import com.unicauca.clientproducthttpclient.domain.entities.Client;
import com.unicauca.clientproducthttpclient.domain.entities.Order;
import com.unicauca.clientproducthttpclient.domain.entities.Role;
import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.domain.services.ClientService;
import com.unicauca.clientproducthttpclient.domain.services.IClientService;
import com.unicauca.clientproducthttpclient.domain.services.IUserService;
import com.unicauca.clientproducthttpclient.domain.services.UserService;
import com.unicauca.clientproducthttpclient.util.Resultado;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    private double xOffset;
    private double yOffset;


    public void loginButtonOnAction(ActionEvent event) throws IOException {
        User user=new User();
        user.setUsername(txtUsername.getText());
        user.setPassword(txtPassword.getText());

        Resultado resultado=userService.validateUser(user);
        if (resultado!=null){
            if(resultado.getMsg().equals("ADMIN")){
                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/views/homeAdmin.fxml"));
                fxmlLoader.setController(new HomeAdminController()); // Establecer el controlador
                Scene scene = new Scene(fxmlLoader.load(), 1100, 581);
                scene.setOnMousePressed(event1 -> {
                      xOffset =event1.getSceneX();
                      yOffset= event1.getSceneY();
                });
                scene.setOnMouseDragged(event1 -> {
                    Stage stage = (Stage) scene.getWindow();
                    stage.setX(event1.getScreenX() - xOffset);
                    stage.setY(event1.getScreenY() - yOffset);
                });
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                cerrarVentana();
            }else{
                HomeUserController homeUserController=new HomeUserController();
                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/views/homeUser.fxml"));
                fxmlLoader.setController(homeUserController);
                Scene scene = new Scene(fxmlLoader.load(), 1300, 801);
                scene.setOnMousePressed(event1 -> {
                    xOffset =event1.getSceneX();
                    yOffset= event1.getSceneY();
                });
                scene.setOnMouseDragged(event1 -> {
                    Stage stage = (Stage) scene.getWindow();
                    stage.setX(event1.getScreenX() - xOffset);
                    stage.setY(event1.getScreenY() - yOffset);
                });
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                homeUserController.setStage(stage);
                stage.show();
                cerrarVentana();
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
