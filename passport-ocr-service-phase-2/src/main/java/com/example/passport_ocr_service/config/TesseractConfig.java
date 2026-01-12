package com.example.passport_ocr_service.config;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class TesseractConfig {

    @Bean
    public Tesseract tesseract() {
        Tesseract t = new Tesseract();
        t.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");
        t.setLanguage("eng");
        t.setPageSegMode(6); // MRZ friendly
        return t;
    }
}

