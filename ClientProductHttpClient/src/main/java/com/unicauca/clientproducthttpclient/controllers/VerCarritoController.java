package com.unicauca.clientproducthttpclient.controllers;

import co.unicauca.microkernel.common.payhuynseda.Payment;
import com.unicauca.clientproducthttpclient.access.ClientRestRepository;
import com.unicauca.clientproducthttpclient.designpatterns.observer.Observer;
import com.unicauca.clientproducthttpclient.domain.entities.Client;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.domain.entities.User;
import com.unicauca.clientproducthttpclient.domain.services.*;
import com.unicauca.clientproducthttpclient.util.Messages;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import unicauca.microkernel.plugin.manager.DeliveryPluginManager;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerCarritoController extends Window implements Initializable, Observer {
    @Getter
    @Setter
    private HomeUserController homeUserController;

    @FXML
    private Button btnVolver;
    @FXML
    private Button btnPagar;
    @FXML
    private Button btnConfirmarPago;
    @FXML
    private GridPane grid;
    @FXML
    private Label lblTotal;
    @FXML
    private ScrollPane scrollVerCarrito;
    @FXML
    private AnchorPane pnlPago;
    @FXML
    private Label lblProducto;
    @FXML
    private Label lblCantidad;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblAdvertenciaCampos;

    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtCiudad;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtBarrio;
    @FXML
    private ComboBox<String>cboMetodoPago;


    @FXML
    TableView<Item> tblItems;
    @FXML
    TableColumn<Item,String> colProd;
    @FXML
    TableColumn<Item,Integer> colCantidad;
    @FXML
    TableColumn<Item,Double> colSubtotal;

    private double xOffset;
    private double yOffset;

    @FXML
    @Getter
    @Setter
    private Stage stage;

    private DoubleProperty totalProperty = new SimpleDoubleProperty(0.0);

    private ShoppingCartService shoppingCartService;
    private List<Item>items=new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.pnlPago.setVisible(false);
        this.scrollVerCarrito.setVisible(true);
        if(!items.isEmpty()) {
            updateGridWithResults(items);
            System.out.println(items.get(0));
            lblTotal.textProperty().bind(Bindings.createStringBinding(
                    () -> String.format("%.2f", totalProperty.get()),
                    totalProperty
            ));
        }

        ObservableList<String> metodosPago = FXCollections.observableArrayList(
                "PSE",
                "Efecty",
                "Efectivo"
                // Agrega más métodos de pago si es necesario
        );

        // Asignar la lista de métodos de pago al ComboBox
        cboMetodoPago.setItems(metodosPago);

        this.btnConfirmarPago.setOnAction(this::btnOnActionConfirmarPago);



        btnClose.setOnAction(this::btnOnActionClose);
        btnMinimize.setOnAction(this::btnOnActionMinimize);
        this.btnVolver.setOnAction(this::btnOnActionVolver);
        this.btnPagar.setOnAction(this::btnOnActionPagar);


    }

    @FXML
    public void btnOnActionPagar(ActionEvent event){
        if(shoppingCartService.getShoppingCart().getItems().isEmpty()){
            Utilities.mostrarAlerta("Información","El carrito esta vacío");
        }else{
            this.scrollVerCarrito.setVisible(false);
            this.pnlPago.setVisible(true);
            this.btnVolver.setOnAction(this::btnOnActionVolver2);
            this.btnPagar.setVisible(false);
            this.btnConfirmarPago.setVisible(true);
            updateLblItems();
        }

    }

    public void btnOnActionConfirmarPago(ActionEvent event) {
        String basePath = getBaseFilePath();
        if(validarCampos(txtNombres,txtApellidos,txtDireccion,txtBarrio,txtCiudad)&&cboMetodoPago.getValue()!=null){
            lblAdvertenciaCampos.setVisible(false);
            String method= cboMetodoPago.getValue();
            try {
                ClientRestRepository clientRestRepository=new ClientRestRepository();
                Client client=new Client();
                client.setFirstName("Primer PRUEBAjsjsjs");
                client.setLastName("Seguno nombre");
                client.setAddress("Mi email");


                DeliveryPluginManager.init(basePath);
                //Console presentationObject = new Console();
                //presentationObject.start();

                Payment pay = new Payment (method, 12345, 1000000);
                PayServices payServices = new PayServices();
                boolean paymentVerified = payServices.verifyPayment(pay);

                if (paymentVerified) {
                    Utilities.mostrarAlerta("Información","Ha realizado su pago con éxito, puede revisar su pedido en Ordenes");// Mostrar mensaje de éxito
                    //Messages.sendPaymentConfirmationMessage("jua" , email, phoneNumber);

                } else {
                    Utilities.mostrarAlerta("Error","Error al procesar el pago"); // Mostrar mensaje de error
                }

            } catch (Exception ex) {
                Logger.getLogger("GUIPago").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
            }
            //Utilities.mostrarAlerta("Información", "Ha realizado su pago con éxito, puede revisar su pedido en Ordenes");
            cargarHomeUser();
        }

        else{
            lblAdvertenciaCampos.setVisible(true);
        }

    }


    public void cargarHomeUser(){
        try {

            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.close();
            HomeUserController homeUserController = new HomeUserController();
            FXMLLoader fxmlLoader = new FXMLLoader(VerCarritoController.class.getResource("/views/homeUser.fxml"));
            fxmlLoader.setController(homeUserController);
            Scene scene = new Scene(fxmlLoader.load(), 1300, 801);
            scene.setOnMousePressed(event1 -> {
                xOffset = event1.getSceneX();
                yOffset = event1.getSceneY();
            });
            scene.setOnMouseDragged(event1 -> {
                Stage stage1 = (Stage) scene.getWindow();
                stage1.setX(event1.getScreenX() - xOffset);
                stage1.setY(event1.getScreenY() - yOffset);
            });
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.initStyle(StageStyle.UNDECORATED);
            homeUserController.setStage(stage1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción de acuerdo a tus necesidades, por ejemplo, mostrar un mensaje de error al usuario
            Utilities.mostrarAlerta("Error", "No se pudo cargar la vista de inicio de usuario. Por favor, inténtelo de nuevo más tarde.");
        }
    }


    public void btnOnActionVolver2(ActionEvent event){
        this.pnlPago.setVisible(false);
        this.scrollVerCarrito.setVisible(true);
        this.btnPagar.setVisible(true);
        this.btnConfirmarPago.setVisible(false);
        this.btnVolver.setOnAction(this::btnOnActionVolver);
        updateLblItems();
    }


    @FXML
    public void btnOnActionVolver(ActionEvent event)  {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
        homeUserController.getStage().show();

    }




    private void updateGridWithResults(List<Item> searchResults) {
        // Limpiar el grid antes de agregar los nuevos resultados
        grid.getChildren().clear();

        int column = 0;
        int row = 1;

        try {
            for(int i = 0; i < searchResults.size(); ++i) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.getPnlParaShopping().setVisible(false);
                itemController.getPnlVerCarrito().setVisible(true);
                itemController.setData(searchResults.get(i));
                itemController.setShoppingCartService(shoppingCartService);
                if (column == 1) {
                    column = 0;
                    ++row;
                }

                grid.add(anchorPane, column++, row);
                grid.setMinWidth(-1.0);
                grid.setPrefWidth(-1.0);
                grid.setMaxWidth(Double.NEGATIVE_INFINITY);
                grid.setMinHeight(-1.0);
                grid.setPrefHeight(-1.0);
                grid.setMaxHeight(Double.NEGATIVE_INFINITY);
                GridPane.setMargin(anchorPane, new Insets(10.0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Object o) {
        this.shoppingCartService=(ShoppingCartService)o;
        if(shoppingCartService.getShoppingCart().getItems().isEmpty()){
            this.items=new ArrayList<>();
            System.out.println("La lista esta vacia");
            totalProperty.set(0);
        }else {
            this.items=shoppingCartService.getShoppingCart().getItems();
            //items.addAll(this.getData());
            System.out.println("--ACTUALIZACION DE OBSERVER-------------------");

            for (Item item : this.items) {
                System.out.println(item.getProduct().getName());
                System.out.println(item.getCantidad());
            }
            System.out.println("Total =" + shoppingCartService.obtenerTotal());

            System.out.println("--finalizacion OBSEVRER-------------------------");
            double newTotal=shoppingCartService.obtenerTotal();
            totalProperty.set(newTotal);
            //initializeTablaItems();

        }

    }

    private boolean validarCampos(TextField... campos) {
        for (TextField campo : campos) {
            if (campo.getText().isEmpty()) {
                campo.setStyle("-fx-border-color: red;");
                return false;
            } else {
                campo.setStyle("");
            }
        }
        return true;
    }


    private void updateLblItems() {
        StringBuilder productos = new StringBuilder();
        StringBuilder cantidades = new StringBuilder();
        StringBuilder subtotales = new StringBuilder();

        for (Item item : items) {
            productos.append(item.getProduct().getName()).append("\n");
            cantidades.append(item.getCantidad()).append("\n");
            subtotales.append("$"+item.getSubtotal()+" COP").append("\n");
        }

        lblProducto.setText(productos.toString());
        lblCantidad.setText(cantidades.toString());
        lblSubtotal.setText(subtotales.toString());
    }


    private static String getBaseFilePath() {
        try {
            String path = VerCarritoController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = URLDecoder.decode(path, "UTF-8"); //This should solve the problem with spaces and special characters.
            File pathFile = new File(path);
            if (pathFile.isFile()) {
                path = pathFile.getParent();
                if (!path.endsWith(File.separator)) {
                    path += File.separator;
                }
            }
            return path;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(VerCarritoController.class.getName()).log(Level.SEVERE, "Error al eliminar espacios en la ruta del archivo", ex);
            return null;
        }
    }


}
