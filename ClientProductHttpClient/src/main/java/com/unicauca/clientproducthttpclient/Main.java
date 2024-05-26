package com.unicauca.clientproducthttpclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Juan
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {

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

    public static void main(String[] args) {
        launch(args);
    }

}

    