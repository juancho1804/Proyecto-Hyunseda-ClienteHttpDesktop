package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.designpatterns.observerItem.ItemObserver;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.services.IShoppingCartService;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class ItemController implements ItemObserver {
    @Setter
    private IShoppingCartService shoppingCartService;
    @Getter
    @FXML
    private AnchorPane pnlParaShopping;
    @Getter
    @FXML
    private AnchorPane pnlVerCarrito;
    @FXML
    private ImageView imgItem;
    @FXML
    private Label lblNombreItem;
    @FXML
    private Label lblDesc1Item;
    @FXML
    private Label lblPrecioItem;
    @FXML
    private Button btnAgregarItem;
    @Getter
    @FXML
    private Button btnEliminarItem;
    @Getter
    @FXML
    private Label lblItem;
    @FXML
    private Spinner<Integer> spnCantidad;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblNombreItem1;
    @FXML
    private Label lblPrecioItem1;
    @FXML
    private AnchorPane pnlImagen;

    private Item item;

    public void setData(Item item) {
    this.item = item;
    item.addObserver(this);
    updateItemState();
    if (item.getProduct() != null) {
        if(pnlParaShopping.isVisible()) {
            lblNombreItem.setText(item.getProduct().getName());
            lblDesc1Item.setText(item.getProduct().getDescription());
            lblPrecioItem.setText(String.valueOf(item.getProduct().getPrice()));
        }else{
            lblSubtotal.setText(String.valueOf(item.getSubtotal()));
            lblNombreItem1.setText(item.getProduct().getName());
            lblPrecioItem1.setText(String.valueOf(item.getProduct().getPrice()));
            inicializarSpinner(item.getCantidad());
            spnCantidad.valueProperty().addListener((obs, oldValue, newValue) -> {
                // Actualizar el subtotal cuando cambie el valor del Spinner
                if(oldValue>newValue){
                    shoppingCartService.eliminarItem(item);
                    if (newValue == 0) {
                        // Si la cantidad llega a cero, eliminar el elemento de la interfaz
                        shoppingCartService.eliminarItem(item);
                        pnlVerCarrito.getChildren().clear();
                        pnlImagen.getChildren().clear();
                    }
                } else if (oldValue<newValue) {
                    shoppingCartService.agregarItem(item);

                }else{

                }
                //item.setCantidad(newValue);
                lblSubtotal.setText(String.valueOf(item.getSubtotal()));
            });


        }


        /*
        String imagePath = item.getProduct().getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            try {
                String ruta= Utilities.convertirARutaValidaImagen(imagePath);
                Image image = new Image(getClass().getResourceAsStream(ruta));
                imgItem.setImage(image);
            } catch (NullPointerException e) {
                System.out.println("Error loading image: " + imagePath);
            }
        }

         */
        String imageUrl = item.getProduct().getImage(); // Obtener la URL de la imagen desde el producto seleccionado
        Image image = null;
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                image = new Image(imageUrl, true); // Intentar cargar la imagen desde la URL
            } catch (Exception e) {
                // Si hay un error al cargar la imagen desde la URL, manejarlo y buscar localmente
            }
        }
        if (image == null) {
            // Si la imagen no se cargó desde la URL, buscar localmente
            File localImageFile = new File(item.getProduct().getImage());
            if (localImageFile.exists()) {
                image = new Image(localImageFile.toURI().toString(), true); // Cargar la imagen localmente
            }
        }
        imgItem.setImage(image);
    }

    }

    public void agregarItem(ActionEvent actionEvent) {
        shoppingCartService.agregarItem(item);
        lblItem.setText(item.getCantidad()+" en el carrito");
        btnEliminarItem.setVisible(true);
    }
    public void eliminarItem(ActionEvent actionEvent) {
        shoppingCartService.eliminarItem(item);
    }

    private void updateItemState() {
        if (item.getCantidad() == 0) {
            btnEliminarItem.setVisible(false);
            lblItem.setText("");
        } else {
            lblItem.setText(item.getCantidad() + " en el carrito");
            btnEliminarItem.setVisible(true);
        }
    }

    public void inicializarSpinner(int valor){
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE, valor);
        spnCantidad.setValueFactory(valueFactory);
    }

    @Override
    public void onItemStateChanged(Item item) {
        updateItemState();
    }
}
