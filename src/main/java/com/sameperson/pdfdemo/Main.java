package com.sameperson.pdfdemo;

import com.sameperson.pdfdemo.util.PDFGenerator;

public class Main {

    public static void main(String[] args) {

        PDFGenerator generator = new PDFGenerator();
        generator.createFromTemplate("0100100000011", "John", "Smith");

    }
}
