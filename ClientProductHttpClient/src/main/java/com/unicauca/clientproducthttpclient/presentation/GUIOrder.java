package com.unicauca.clientproducthttpclient.presentation;

import com.unicauca.clientproducthttpclient.controllers.OrderController;
import com.unicauca.clientproducthttpclient.controllers.ShoppingCartController;
import com.unicauca.clientproducthttpclient.designpatterns.observer.Observer;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.domain.entities.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class GUIOrder extends javax.swing.JFrame implements Observer {
    Order order;
    OrderController orderController;

    public GUIOrder() {
        this.order = new Order();
        List<Item> items = new ArrayList<>();
        order.setItems(items);
        this.orderController = new OrderController();
        this.orderController.createOrder(order);
        initComponents();
        loadData(order.getItems());
    }

    private void InitializeTable() {
        tblItems.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Item", "Cantidad", "Subtotal"}));
    }

    private void loadData(List<Item> items) {
        InitializeTable();
        DefaultTableModel model = (DefaultTableModel) tblItems.getModel();
        Object rowData[] = new Object[3]; // Número de columnas

        for (Item item : items) {
            rowData[0] = item.getProduct().getName();
            rowData[1] = item.getCantidad();
            rowData[2] = "" + item.getSubtotal();
            model.addRow(rowData);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        pnlNorte = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlCenter = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18));
        jLabel1.setText("¡ Su orden ha sido procesada exitosamente !");

        javax.swing.GroupLayout pnlNorteLayout = new javax.swing.GroupLayout(pnlNorte);
        pnlNorte.setLayout(pnlNorteLayout);
        pnlNorteLayout.setHorizontalGroup(
                pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlNorteLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlNorteLayout.setVerticalGroup(
                pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlNorteLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel1)
                                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new java.awt.GridLayout(2, 2));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14));
        jLabel2.setText("Fecha:");
        jPanel2.add(jLabel2);

        lblFecha.setFont(new java.awt.Font("SansSerif", 0, 12));
        jPanel2.add(lblFecha);

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14));
        jLabel5.setText("Estado:");
        jPanel2.add(jLabel5);

        lblEstado.setFont(new java.awt.Font("SansSerif", 0, 12));
        jPanel2.add(lblEstado);

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Title 1", "Title 2", "Title 3"
                }
        ));
        jScrollPane2.setViewportView(tblItems);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
                pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        pnlCenterLayout.setVerticalGroup(
                pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pnlNorte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnlNorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }
    // </editor-fold>

    @Override
    public void update(Object o) {
        List<Item>items = new ArrayList<>();
        ShoppingCartController shoppingCartController = (ShoppingCartController) o;
        items = shoppingCartController.getShopService().getShoppingCart().getItems();
        loadData(items);

        lblEstado.setText(this.order.getState().estadoPedido());
        lblFecha.setText(this.order.getDate());
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlNorte;
    private javax.swing.JTable tblItems;
    // End of variables declaration
}
