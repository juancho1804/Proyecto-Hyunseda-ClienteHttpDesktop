package com.unicauca.clientproducthttpclient.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VerCarritoController extends Window implements Initializable {
    @Getter
    @Setter
    private HomeUserController homeUserController;
    @FXML
    private Button btnVolver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnClose.setOnAction(this::btnOnActionClose);
        btnMinimize.setOnAction(this::btnOnActionMinimize);
        this.btnVolver.setOnAction(this::btnOnActionVolver);

    }

    @FXML
    public void btnOnActionVolver(ActionEvent event)  {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
        homeUserController.getStage().show();
    }


}
