/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unicauca.clientproducthttpclient.util;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author jvuan
 */
public class Utilities{
    /*
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof String) {
            String imageUrl = (String) value;
            try {
                URL url = new URL(imageUrl);
                Image image = new ImageIcon(url).getImage();
                setIcon(new ImageIcon(image));
            } catch (Exception e) {
                setIcon(null);
            }
        } else {
            setIcon(null);
        }
        
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }
        
        setHorizontalAlignment(JLabel.CENTER);
        
        return this;
    }*/
    /**
     * Carga una imagen a partir de la url
     * @param name url de la imagen
     * @return Un icono de la imagen
     */
    public static ImageIcon loadImageFromCloud(String name) {

        String imageUrl = name;

        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(ImageIO.read(url));
            return icon;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
