package com.unicauca.clientproducthttpclient.controllers;

import com.unicauca.clientproducthttpclient.designpatterns.observerItem.ItemObserver;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.services.IShoppingCartService;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

public class ItemController implements ItemObserver {
    @Setter
    private IShoppingCartService shoppingCartService;


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

    private Item item;

    public void setData(Item item) {
    this.item = item;
    item.addObserver(this);
    updateItemState();
    if (item.getProduct() != null) {
        lblNombreItem.setText(item.getProduct().getName());
        lblDesc1Item.setText(item.getProduct().getDescription());
        lblPrecioItem.setText(String.valueOf(item.getProduct().getPrice()));

        // Load image from resources
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
    }

    }

    public void agregarItem(ActionEvent actionEvent) {
        shoppingCartService.agregarItem(item);
        lblItem.setText(item.getCantidad()+" en el carrito");
        System.out.println("ACTUALIZACION -----------------");
        for(Item item1:shoppingCartService.getShoppingCart().getItems()) {
            System.out.println(item1.getProduct().getName());
            System.out.println(item1.getCantidad());
            System.out.println(item1.getSubtotal());
        }
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

    @Override
    public void onItemStateChanged(Item item) {
        updateItemState();
    }
}
