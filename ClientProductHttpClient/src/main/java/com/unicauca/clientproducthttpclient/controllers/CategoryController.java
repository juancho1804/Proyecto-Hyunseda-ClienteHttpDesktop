/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.entities.Category;
import com.unicauca.clientproducthttpclient.domain.services.ICategoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Juan
 */
public class CategoryController extends Window implements Initializable {
    private ICategoryService categoryService;

    @FXML
    private AnchorPane pnlCategorias;
    @FXML
    private AnchorPane pnlInicio;
    @FXML
    private AnchorPane pnlProductos;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnCategorias;
    @FXML
    public Button btnMinimize;
    @FXML
    public Button btnClose;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.pnlProductos.setVisible(false);
        this.pnlInicio.setVisible(false);
        this.pnlCategorias.setVisible(true);
        initializelblUsuario();
        this.btnProductos.setOnAction(this::btnOnActionProductos);
        this.btnInicio.setOnAction(this::btnOnActionInicio);
        this.btnCategorias.setOnAction(this::btnOnActionCategorias);
        this.btnCerrarSesion.setOnAction(this::btnOnActionCerrarSesion);
        this.btnClose.setOnAction(this::btnOnActionClose);
        this.btnMinimize.setOnAction(this::btnOnActionMinimize);

    }


    public void btnOnActionCategorias(ActionEvent event) {
        pnlInicio.setVisible(false);
        pnlProductos.setVisible(false);
        pnlCategorias.setVisible(true);
    }
}
