package com.sameperson.pdfdemo;

import com.sameperson.pdfdemo.util.PDFGenerator;

public class Main {

    public static void main(String[] args) {

        PDFGenerator generator = new PDFGenerator();
        generator.createFromTemplateWithFields("0100100000011", "John Smith", "+380965921355", "Kyiv, Pravdi ave. 20, flat 14");
//        generator.createFromTemplateWithCoordinates("0100100000011", "John", "Smith");
//        generator.create("9999999999999", "From", "Scratch");

    }
}
