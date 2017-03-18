package com.sameperson.pdfdemo.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import java.io.File;
import java.io.IOException;

public class PDFGenerator {

    private static final float POINTS_PER_MM = 1 / (10 * 2.54f) * 72;
    private PDFont font;

    public PDFGenerator() {
        font = PDType1Font.HELVETICA;
    }

    public void createFromTemplateWithFields(String barcode, String senderName, String senderPhone, String senderAddress) {
        try {
            PDDocument template = PDDocument.load(new File("template/post-template.pdf"));
            PDAcroForm acroForm = template.getDocumentCatalog().getAcroForm();
            if (acroForm != null)
            {
                PDTextField field = (PDTextField) acroForm.getField("senderName");
                field.setValue(senderName);

                field = (PDTextField) acroForm.getField("senderPhone");
                field.setValue(senderPhone);

                field = (PDTextField) acroForm.getField("senderAddress");
                field.setValue(senderAddress);
            }
            template.save("output/" + barcode + ".pdf");
            template.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFromTemplateWithCoordinates(String barcode, String firstName, String lastName) {
        try {
            PDDocument template = PDDocument.load(new File("template/demo-template.pdf"));
            PDPage templatePage = template.getPage(0);
            PDPageContentStream content = new PDPageContentStream(template, templatePage,
                    PDPageContentStream.AppendMode.APPEND, true, true);

            //Barcode
            putText(content, font, 10, 10 * POINTS_PER_MM, 130f * POINTS_PER_MM, barcode + " (Barcode will be here)");
            //First name
            putText(content, font, 14, 50 * POINTS_PER_MM, 115.8f * POINTS_PER_MM, firstName);
            //Last name
            putText(content, font, 14, 50 * POINTS_PER_MM, 106.9f * POINTS_PER_MM, lastName);

            content.close();

            template.save("output/" + barcode + ".pdf");
            template.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create(String barcode, String firstName, String lastName) {
        try {
            new PDPage(new PDRectangle(297 * POINTS_PER_MM, 210 * POINTS_PER_MM));

            PDDocument document = new PDDocument();
            PDRectangle rectangle = new PDRectangle(210 * POINTS_PER_MM, 148 * POINTS_PER_MM);
            PDPage page = new PDPage(rectangle);

            document.addPage(page);
            PDPageContentStream content = new PDPageContentStream(document, page);

            //Barcode
            putText(content, font, 10, 10 * POINTS_PER_MM, 90 * POINTS_PER_MM, barcode + " (Barcode will be here)");
            //First name
            putText(content, font, 14, 15 * POINTS_PER_MM, 75 * POINTS_PER_MM, "First name: " + firstName);
            //Last name
            putText(content, font, 14, 15 * POINTS_PER_MM, 70 * POINTS_PER_MM, "Last name: " + lastName);

            content.close();

            document.save("output/" + barcode + ".pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFont(PDFont font) {
        this.font = font;
    }

    private void putText(PDPageContentStream content, PDFont font, int size, float offsetX, float offsetY, String text) throws IOException {
        content.beginText();
        content.setFont(font, size);
        content.newLineAtOffset(offsetX, offsetY);
        content.showText(text);
        content.endText();
    }

}
