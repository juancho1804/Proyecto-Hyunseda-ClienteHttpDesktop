package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.domain.services.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomeAdminController  extends Window implements Initializable{

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
    private Label lblCantidadProductos;
    @FXML
    private Label lblCantidadUsuarios;
    @FXML
    private Label lblCantidadOrdenes;
    @FXML
    private AnchorPane pnlInicio;
    @FXML
    private AnchorPane pnlProductos;
    @FXML
    private AnchorPane pnlCategorias;

    @FXML
    private BarChart<String, Integer> bchProductosPorCategoria;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.pnlCategorias.setVisible(false);
        this.pnlProductos.setVisible(false);
        this.pnlInicio.setVisible(true);
        this.btnProductos.setOnAction(this::btnOnActionProductos);
        this.btnCategorias.setOnAction(this::btnOnActionCategorias);
        this.btnInicio.setOnAction(this::btnOnActionInicio);
        this.btnCerrarSesion.setOnAction(this::btnOnActionCerrarSesion);
        this.btnMinimize.setOnAction(this::btnOnActionMinimize);
        this.btnClose.setOnAction(this::btnOnActionClose);
        this.lblCantidadProductos.setText(""+lblOnActionCantidadProductos());
        this.lblCantidadUsuarios.setText(""+lblOnActionCantidadUsuarios());
        this.lblCantidadOrdenes.setText(""+lblOnActionCantidadOrdenes());
        initializelblUsuario();
        inicializarGraficaProductosPorCategoria();



    }

    public void btnOnActionInicio(ActionEvent event){
        pnlCategorias.setVisible(false);
        pnlProductos.setVisible(false);
        pnlInicio.setVisible(true);
    }



    public int lblOnActionCantidadProductos(){
        IProductService productService = new ProductService();
        return productService.findAll().size();
    }
    public int lblOnActionCantidadUsuarios(){
        IUserService userService = new UserService();
        return userService.findAll().size();
    }
    public int lblOnActionCantidadOrdenes(){
        IOrderService orderService = new OrderService();
        return orderService.findAll().size();
    }


    public void inicializarGraficaProductosPorCategoria() {
        //bchProductosPorCategoria.getData().clear();
        IProductService productService=new ProductService();
        Map<String, Integer> productosPorCategoria = productService.contarProductosPorCategoria();

        XYChart.Series chart = new XYChart.Series();

        for (Map.Entry<String, Integer> entry : productosPorCategoria.entrySet()) {
            // Agregamos cada categoría y su cantidad de productos a la serie del gráfico
            chart.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        bchProductosPorCategoria.getXAxis().setLabel("Categorías");
        bchProductosPorCategoria.getYAxis().setLabel("Productos");
        bchProductosPorCategoria.getData().add(chart);


    }




}
