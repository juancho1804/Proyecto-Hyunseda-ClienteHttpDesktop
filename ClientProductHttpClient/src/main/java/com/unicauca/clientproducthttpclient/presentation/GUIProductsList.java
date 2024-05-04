
package com.unicauca.clientproducthttpclient.presentation;

import com.unicauca.clientproducthttpclient.controllers.ProductController;
import java.util.List;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.unicauca.clientproducthttpclient.util.Utilities;
import javax.swing.ImageIcon;
/**
 *
 * @author libardo
 */
public class GUIProductsList extends javax.swing.JDialog {
    private ProductController productController;
    /**
     * Creates new form GUIProductsList
     */
    public GUIProductsList(java.awt.Frame parent, boolean modal, ProductController productController) {
        super(parent, modal);
        this.productController = productController;
        initComponents();
        InitializeTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGroupSearch = new javax.swing.ButtonGroup();
        pnlNorte = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        rdoId = new javax.swing.JRadioButton();
        rdoName = new javax.swing.JRadioButton();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnBuscarTodos = new javax.swing.JButton();
        pnlCentral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblImagen = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listar productos");

        lblBuscar.setText("Buscar por:");
        pnlNorte.add(lblBuscar);

        btGroupSearch.add(rdoId);
        rdoId.setText("Id");
        rdoId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoIdActionPerformed(evt);
            }
        });
        pnlNorte.add(rdoId);

        btGroupSearch.add(rdoName);
        rdoName.setText("Nombre");
        rdoName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNameActionPerformed(evt);
            }
        });
        pnlNorte.add(rdoName);

        txtBuscar.setPreferredSize(new java.awt.Dimension(62, 32));
        pnlNorte.add(txtBuscar);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        pnlNorte.add(btnBuscar);

        btnBuscarTodos.setText("Buscar todos");
        btnBuscarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTodosActionPerformed(evt);
            }
        });
        pnlNorte.add(btnBuscarTodos);

        getContentPane().add(pnlNorte, java.awt.BorderLayout.PAGE_START);

        pnlCentral.setMinimumSize(new java.awt.Dimension(16, 20));
        pnlCentral.setPreferredSize(new java.awt.Dimension(452, 402));

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(tblProducts);

        lblImagen.setMaximumSize(new java.awt.Dimension(10, 23));
        lblImagen.setMinimumSize(new java.awt.Dimension(10, 23));
        jPanel2.add(lblImagen);

        javax.swing.GroupLayout pnlCentralLayout = new javax.swing.GroupLayout(pnlCentral);
        pnlCentral.setLayout(pnlCentralLayout);
        pnlCentralLayout.setHorizontalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCentralLayout.setVerticalGroup(
            pnlCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentralLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCentralLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(pnlCentral, java.awt.BorderLayout.CENTER);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCerrar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNameActionPerformed
        if (rdoName.isSelected()) {
            txtBuscar.setEnabled(true);
            txtBuscar.setText(""); // Limpiar el texto de búsqueda
            txtBuscar.requestFocus(); // Colocar el foco en la casilla de búsqueda
        } else {
            // Deshabilitar la casilla de búsqueda por nombre si no está seleccionada
            txtBuscar.setEnabled(false);
            txtBuscar.setText(""); // Limpiar el texto de búsqueda
        }
    }//GEN-LAST:event_rdoNameActionPerformed

    private void rdoIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoIdActionPerformed
        // Habilitar la casilla de búsqueda por ID si está seleccionada
        if (rdoId.isSelected()) {
            txtBuscar.setEnabled(true);
            txtBuscar.setText(""); // Limpiar el texto de búsqueda
            txtBuscar.requestFocus(); // Colocar el foco en la casilla de búsqueda
        } else {
            // Deshabilitar la casilla de búsqueda por ID si no está seleccionada
            txtBuscar.setEnabled(false);
            txtBuscar.setText(""); // Limpiar el texto de búsqueda
        }
    }//GEN-LAST:event_rdoIdActionPerformed

    private void btnBuscarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTodosActionPerformed
        loadData();
    }//GEN-LAST:event_btnBuscarTodosActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // Verificar si se seleccionó la búsqueda por ID
        if (rdoId.isSelected()) {
            // Obtener el texto ingresado por el usuario en la casilla de búsqueda
            String searchText = txtBuscar.getText().trim();

            // Verificar si el campo de búsqueda no está vacío
            if (!searchText.isEmpty()) {
                try {
                    // Convertir el texto a un Long (ID)
                    Integer productId = Integer.parseInt(searchText);

                    // Realizar la búsqueda por ID
                    Product foundProduct = productController.findById(productId);
                    // Verificar si se encontró el producto
                    if (foundProduct.getId() != 0) {
                        // Limpiar la tabla y agregar el producto encontrado
                        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
                        model.setRowCount(0); // Limpiar la tabla
                        model.addRow(new Object[]{foundProduct.getId(), foundProduct.getName(),foundProduct.getPrice()});
                    
                         // Cargar la imagen correspondiente al producto
                        ImageIcon productImage = Utilities.loadImageFromCloud(foundProduct.getImage()); // Supongo que hay un atributo 'image' en la clase Product que contiene la URL de la imagen
                        lblImagen.setIcon(productImage);
                    } else {
                        // Mostrar un mensaje indicando que no se encontró el producto
                        JOptionPane.showMessageDialog(this, "No se encontró ningún producto con el ID especificado.", "Producto no encontrado", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    // Mostrar un mensaje de error si el texto ingresado no es un número válido
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mostrar un mensaje indicando que el campo de búsqueda está vacío
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID para buscar.", "Campo vacío", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (rdoName.isSelected()) {
            String searchText = txtBuscar.getText().trim();
            if (!searchText.isEmpty()) {
                try {
                    Product foundProduct = productController.findByName(searchText);

                    if (foundProduct.getId() != 0) {
                        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
                        model.setRowCount(0); // Limpiar la tabla
                        model.addRow(new Object[]{foundProduct.getId(), foundProduct.getName(), foundProduct.getPrice()});
                    
                        // Cargar la imagen correspondiente al producto
                        ImageIcon productImage = Utilities.loadImageFromCloud(foundProduct.getImage()); // Supongo que hay un atributo 'image' en la clase Product que contiene la URL de la imagen
                        lblImagen.setIcon(productImage);
                    } else {
                        // Mostrar un mensaje indicando que no se encontró el producto
                        JOptionPane.showMessageDialog(this, "No se encontró ningún producto con el nombre especificado.", "Producto no encontrado", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    // Mostrar un mensaje de error si el texto ingresado no es un número válido
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {

                JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre para buscar .", "Campo vacio", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed
    
    private void InitializeTable() {
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Nombre", "Precio"
                }
        ));
    }
    /*
    private void InitializeTable() {
    tblProducts.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][]{},
        new String[]{"Id", "Nombre", "Precio", "Imagen"}
    ) {
        Class[] types = new Class[]{
            java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.awt.Image.class
        };

        @Override
        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }
    });

        tblProducts.getColumnModel().getColumn(3).setCellRenderer(new Utilities());
    }*/

    private void loadData() {
        InitializeTable();
        List<Product> result = productController.findAll();
        this.InitializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();

        Object rowData[] = new Object[8];//No columnas
        for (int i = 0; i < result.size(); i++) {
            rowData[0] = result.get(i).getId();
            rowData[1] = result.get(i).getName();
            rowData[2] = "" + result.get(i).getPrice();
            //rowData[3] = result.get(i).getImage(); // Agrega la URL de la imagen

            model.addRow(rowData);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btGroupSearch;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarTodos;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlNorte;
    private javax.swing.JRadioButton rdoId;
    private javax.swing.JRadioButton rdoName;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
