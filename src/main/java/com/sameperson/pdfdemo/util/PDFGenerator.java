package com.sameperson.pdfdemo.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class PDFGenerator {

    private static final float POINTS_PER_MM = 1 / (10 * 2.54f) * 72;
    private PDFont font;

    public PDFGenerator() {
        font = PDType1Font.HELVETICA;
    }

    public void createFromTemplate(String barcode, String firstName, String lastName) {
        try {
            PDDocument template = PDDocument.load(new File("template/demo-template.pdf"));
            PDPage templatePage = template.getPage(0);
            PDPageContentStream content = new PDPageContentStream(template, templatePage,
                    PDPageContentStream.AppendMode.APPEND, true, true);

            content.beginText();
            content.setFont(font, 10);
            content.newLineAtOffset(10 * POINTS_PER_MM, 130f * POINTS_PER_MM);
            content.showText(barcode + " (Barcode will be here)");
            content.endText();

            content.beginText();
            content.setFont(font, 14);
            content.newLineAtOffset(50 * POINTS_PER_MM, 115.8f * POINTS_PER_MM);
            content.showText(firstName);
            content.endText();

            content.beginText();
            content.newLineAtOffset(50 * POINTS_PER_MM, 106.9f * POINTS_PER_MM);
            content.showText(lastName);
            content.endText();

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

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 10);
            content.newLineAtOffset(10 * POINTS_PER_MM, 90 * POINTS_PER_MM);
            content.showText(barcode + " (Barcode will be here)");
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 14);
            content.newLineAtOffset(15 * POINTS_PER_MM, 75 * POINTS_PER_MM);
            content.showText("First name: " + firstName);
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 14);
            content.newLineAtOffset(15 * POINTS_PER_MM, 70 * POINTS_PER_MM);
            content.showText("Last name: " + lastName);
            content.endText();

            content.close();

            document.save(barcode + ".pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFont(PDFont font) {
        this.font = font;
    }

}
