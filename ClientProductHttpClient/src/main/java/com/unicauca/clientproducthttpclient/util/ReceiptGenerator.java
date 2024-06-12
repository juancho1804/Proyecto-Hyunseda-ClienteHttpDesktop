package com.unicauca.clientproducthttpclient.util;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.RequestedVisibility;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.SharedLinkSettings;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.unicauca.clientproducthttpclient.domain.entities.Item;

import java.io.*;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.unicauca.clientproducthttpclient.domain.entities.Order;

public class ReceiptGenerator {

    private static final String tokendrop = "t0k3n"; //aqui va

    public String generateReceiptPDF(List<Item> items, String filePath, Order order) {
        Document document = new Document(PageSize.A4);



        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Paragraph clientParagraph = new Paragraph("Información del cliente: ");
            clientParagraph.add(new Paragraph("Nombre completo : " + order.getClient().getFirstName()+" "+order.getClient().getLastName()));
            clientParagraph.add(new Paragraph("Dirección : "+order.getClient().getAddress()));
            clientParagraph.setAlignment(Element.ALIGN_LEFT);
            document.add(clientParagraph);
            document.add(new Paragraph("Fecha de la orden :"+order.getDate()));
            document.add(new Paragraph("\n"));



            PdfPTable table = new PdfPTable(3); // 3 columnas para Producto, Cantidad y Subtotal
            table.setWidthPercentage(100); // Ancho de la tabla es 100% del ancho de la página

            // Añadir encabezados
            addTableHeader(table);

            // Añadir filas de datos
            for (Item item : items) {
                addRows(table, item);
            }

            document.add(table);
            document.close();
            System.out.println("PDF generado exitosamente en: " + filePath);

           // uploadToDropbox(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadToDropbox(filePath);
    }

private static String uploadToDropbox(String filePath) {
    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/Hyunseda").build();
    DbxClientV2 client = new DbxClientV2(config, tokendrop);
    String enlaceWeb = null;

    try {
        java.io.File file = new java.io.File(filePath);
        InputStream inputStream = new FileInputStream(file);

        FileMetadata metadata = client.files().uploadBuilder("/" + file.getName())
                .withMode(WriteMode.ADD)
                .uploadAndFinish(inputStream);

        // Configurar los permisos de lectura para el enlace compartido
        SharedLinkSettings sharedLinkSettings = SharedLinkSettings.newBuilder()
                .withRequestedVisibility(RequestedVisibility.PUBLIC)
                .build();


        SharedLinkMetadata sharedLinkMetadata = client.sharing().createSharedLinkWithSettings(metadata.getPathLower());

       // client.sharing().modifySharedLinkSettings(sharedLinkMetadata.getUrl(), sharedLinkSettings);
        enlaceWeb=sharedLinkMetadata.getUrl().replace("www.dropbox.com", "dl.dropboxusercontent.com");
        System.out.println("PDF subido exitosamente a Dropbox.");
    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println("enlace: " + enlaceWeb);

    return enlaceWeb;
}

    private void addTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell(new Paragraph("Producto"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Cantidad"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Subtotal"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private void addRows(PdfPTable table, Item item) {
        table.addCell(item.getProduct().getName());
        table.addCell(Integer.toString(item.getCantidad()));
        table.addCell("$" + Double.toString(item.getSubtotal()) + " COP");
    }
}
