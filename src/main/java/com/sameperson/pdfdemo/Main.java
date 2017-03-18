package com.sameperson.pdfdemo;

import com.sameperson.pdfdemo.util.PDFGenerator;

public class Main {

    public static void main(String[] args) {

        PDFGenerator generator = new PDFGenerator();
        generator.createFromTemplateWithFields("0100100000011", "John", "Smith");
//        generator.createFromTemplateWithCoordinates("0100100000011", "John", "Smith");
//        generator.create("9999999999999", "From", "Scratch");

    }
}
