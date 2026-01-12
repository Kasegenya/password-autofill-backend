package com.example.passport_ocr_service.config;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TesseractConfig {

    @Bean
    public Tesseract tesseract() {
        Tesseract t = new Tesseract();
        t.setDatapath("/usr/share/tesseract-ocr/4.00/tessdata");
        t.setLanguage("eng");
        t.setPageSegMode(6); // MRZ friendly
        return t;
    }
}

