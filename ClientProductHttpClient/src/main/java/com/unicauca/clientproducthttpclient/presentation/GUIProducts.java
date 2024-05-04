package com.unicauca.clientproducthttpclient.presentation;


import com.unicauca.clientproducthttpclient.controllers.CategoryController;
import com.unicauca.clientproducthttpclient.controllers.ProductController;
import com.unicauca.clientproducthttpclient.domain.entities.Category;
import com.unicauca.clientproducthttpclient.domain.entities.Product;
import com.unicauca.clientproducthttpclient.util.Messages;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author libardo
 */
public class GUIProducts extends javax.swing.JFrame {
    
    private ProductController productController;
    private CategoryController categoryController;
    List<Category> categories;
    
    boolean addOption;
    boolean deleteOption;
    boolean editOption;

    /**
     * Creates new form GUIProducts
     */
    public GUIProducts(ProductController productController, CategoryController categoryController) {
        this.addOption=false;
        this.deleteOption=false;
        this.editOption=false;
        initComponents();
        this.productController = productController;
        this.categoryController = categoryController;
        this.categories=categoryController.findAll();
        initializeComboBox();
        stateInitial();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlSur = new javax.swing.JPanel();
        btnGrabar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        lblImagen = new javax.swing.JLabel();
        txtImagen = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gestión de Productos");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });
        pnlSur.add(btnGrabar);

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        pnlSur.add(btnAgregar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlSur.add(btnCancelar);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlSur.add(btnEditar);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        pnlSur.add(btnEliminar);

        btnListar.setText("Listar");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        pnlSur.add(btnListar);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        pnlSur.add(btnCerrar);

        getContentPane().add(pnlSur, java.awt.BorderLayout.PAGE_END);

        pnlCentro.setLayout(new java.awt.GridLayout(6, 2));

        jLabel2.setText("Id:");
        pnlCentro.add(jLabel2);
        pnlCentro.add(txtId);

        jLabel3.setText("Nombre:");
        pnlCentro.add(jLabel3);
        pnlCentro.add(txtNombre);

        lblDescripcion.setText("Descripcion:");
        pnlCentro.add(lblDescripcion);

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        pnlCentro.add(txtDescripcion);

        jLabel4.setText("Precio:");
        pnlCentro.add(jLabel4);
        pnlCentro.add(txtPrecio);

        lblCategoria.setText("Categoria");
        pnlCentro.add(lblCategoria);

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCategoriaActionPerformed(evt);
            }
        });
        pnlCentro.add(cboCategoria);

        lblImagen.setText("URL Imagen:");
        pnlCentro.add(lblImagen);
        pnlCentro.add(txtImagen);

        getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stateInitial() {
        btnAgregar.setVisible(true);
        btnEditar.setVisible(true);
        btnEliminar.setVisible(true);
        btnCancelar.setVisible(false);
        btnCerrar.setVisible(true);
        btnGrabar.setVisible(false);
        btnListar.setVisible(true);
        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtPrecio.setEnabled(false);

    }
    
    private void stateCreate() {
        btnAgregar.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(true);
        btnCerrar.setVisible(true);
        btnGrabar.setVisible(true);
        btnListar.setVisible(false);
        txtId.setEnabled(false);
        txtNombre.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtImagen.setEnabled(true);
    }
    private void stateEdit() {
        btnAgregar.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(true);
        btnCerrar.setVisible(true);
        btnGrabar.setVisible(true);
        btnListar.setVisible(false);
        txtId.setEnabled(true);
        txtNombre.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtPrecio.setEnabled(true);
        txtImagen.setEnabled(true);
    }
    private void stateDelete(){
        btnAgregar.setVisible(false);
        btnEditar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(true);
        btnCerrar.setVisible(true);
        btnGrabar.setVisible(true);
        btnListar.setVisible(false);
        txtId.setEnabled(true);
        txtNombre.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtPrecio.setEnabled(false);
        cboCategoria.setVisible(false);
        lblCategoria.setVisible(false);
        txtImagen.setEnabled(false);
        
    }
    
    private void initializeComboBox(){
       DefaultComboBoxModel<String> model=new DefaultComboBoxModel<>();
       for(Category category:categories){
           model.addElement(category.getName());
       }
       cboCategoria.setModel(model);
    }
    
    private void cleanControls() {
        txtId.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtImagen.setText("");
    }
            
    
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        GUIProductsList dialog = new GUIProductsList(new javax.swing.JFrame(), true, this.productController);
        dialog.setSize(500, 200);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnListarActionPerformed

    private void addProduct(){
        Product product=new Product();
        product.setId(0);
        product.setName(txtNombre.getText());
        product.setPrice(Integer.parseInt(txtPrecio.getText()));
        product.setDescription(txtDescripcion.getText());
        String nombreCategoria= cboCategoria.getSelectedItem().toString();
        product.setImage(txtImagen.getText());
        
        product.setCategory(categoryController.findByName(nombreCategoria));
        productController.create(product);
        cleanControls();
        stateInitial();
        
        
    }
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        stateCreate();
        this.addOption=true;
        
    }//GEN-LAST:event_btnAgregarActionPerformed
    
    private void editProduct(){
        Product productUpdated = new Product(txtNombre.getText() , Integer.parseInt(txtPrecio.getText()));
        productUpdated.setDescription(txtDescripcion.getText());
        String nombreCategoria= cboCategoria.getSelectedItem().toString();
        productUpdated.setImage(txtImagen.getText());
        productUpdated.setCategory(categoryController.findByName(nombreCategoria));
        productController.edit(Integer.parseInt(txtId.getText()), productUpdated);
        cleanControls();
        stateInitial();
    }
    
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        stateEdit();
        this.editOption=true;
    }//GEN-LAST:event_btnEditarActionPerformed
    
    private void deleteProduct(){
        productController.delete(Integer.parseInt(txtId.getText()));
        cleanControls();
        stateInitial();
    }
            
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        stateDelete();
        this.deleteOption=true;
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCategoriaActionPerformed
        String nombreCategoria = cboCategoria.getSelectedItem().toString();
        Category categoriaSeleccionado = categories.stream().filter(p -> p.getName().equals(nombreCategoria)).findFirst().orElse(null);
    }//GEN-LAST:event_cboCategoriaActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private boolean validarId() {
        String idText = txtId.getText().trim();
        if (idText.isEmpty()) {
            Messages.showMessageDialog("Debe ingresar el ID del producto", "Atención");
            txtId.requestFocus();
            return false;
        }
        try {
            Integer.parseInt(idText);
            return true;
        } catch (NumberFormatException e) {
            Messages.showMessageDialog("El ID debe ser un número entero válido", "Atención");
            txtId.requestFocus();
            return false;
        }
    }
    private boolean validarNombre() {
        if (txtNombre.getText().trim().isEmpty()) {
            Messages.showMessageDialog("Debe ingresar el nombre del producto", "Atención");
            txtNombre.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarDescripcion() {
        if (txtDescripcion.getText().trim().isEmpty()) {
            Messages.showMessageDialog("Debe ingresar la descripción del producto", "Atención");
            txtDescripcion.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarPrecio() {
        if (txtPrecio.getText().trim().isEmpty()) {
            Messages.showMessageDialog("Debe ingresar el precio del producto", "Atención");
            txtPrecio.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validarCampos() {
        return validarNombre() && validarDescripcion() && validarPrecio();
    }
    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        if (deleteOption) {
            if(!validarId()){
                return;
            }
            deleteProduct();
            deleteOption = false;
        }

        if(addOption){
            if (!validarCampos()) {
                return;
            }
            addProduct();
            addOption=false;
        }
        
        if(editOption){
            if((!validarId())||(!validarCampos())){
                return;
            }
            editProduct();
            editOption= false;
        }
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        stateInitial();
        cleanControls();
    }//GEN-LAST:event_btnCancelarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnListar;
    private javax.swing.JComboBox<String> cboCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlSur;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtImagen;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables

}
