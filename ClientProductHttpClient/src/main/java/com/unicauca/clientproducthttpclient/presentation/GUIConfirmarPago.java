/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.unicauca.clientproducthttpclient.presentation;

import com.unicauca.clientproducthttpclient.controllers.ShoppingCartController;
import com.unicauca.clientproducthttpclient.designpatterns.observer.Observer;
import com.unicauca.clientproducthttpclient.domain.entities.Item;
import com.unicauca.clientproducthttpclient.presentation.GUIPago;
import com.unicauca.clientproducthttpclient.domain.services.IShoppingCartService;
import java.util.List;

/**
 *
 * @author Juan
 */
public class GUIConfirmarPago extends javax.swing.JFrame implements Observer {
    private GUIPago gUIPago;

    /**
     * Creates new form GUIOtherview
     */
    public GUIConfirmarPago() {
        initComponents();
        this.gUIPago = new GUIPago();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCenter = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblCantidadProductos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        pnlNorte = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlCenter.setLayout(new java.awt.GridLayout(2, 2));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Productos:");
        pnlCenter.add(jLabel2);

        lblCantidadProductos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCantidadProductos.setText("0");
        pnlCenter.add(lblCantidadProductos);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Total:");
        pnlCenter.add(jLabel3);

        lblTotal.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblTotal.setText("$0 COP");
        pnlCenter.add(lblTotal);

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setText("Confirmar compra");

        javax.swing.GroupLayout pnlNorteLayout = new javax.swing.GroupLayout(pnlNorte);
        pnlNorte.setLayout(pnlNorteLayout);
        pnlNorteLayout.setHorizontalGroup(
            pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNorteLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );
        pnlNorteLayout.setVerticalGroup(
            pnlNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlNorteLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        getContentPane().add(pnlNorte, java.awt.BorderLayout.PAGE_START);

        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/confrmar.png"))); // NOI18N
        jButton1.setText("Confirmar");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras.png"))); // NOI18N
        jButton2.setText("Atras");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.add(jButton2);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (this.gUIPago != null) {
            this.gUIPago.setVisible(true);
        } else {
            System.out.println("gUIPago is not initialized");
        }
    }//GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCantidadProductos;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlNorte;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object o) {
        int cantidad=0;
        ShoppingCartController shoppingCartController = (ShoppingCartController) o;
        List<Item>items= shoppingCartController.getShopService().getShoppingCart().getItems();
        for(Item item:items){
            cantidad+=item.getCantidad();
        }
        lblCantidadProductos.setText("" +cantidad);
        lblTotal.setText("$ "+shoppingCartController.getShopService().obtenerTotal()+" COP");

        
    }
}
