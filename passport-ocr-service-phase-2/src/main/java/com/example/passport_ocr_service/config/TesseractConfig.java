package com.example.passport_ocr_service.config;

import net.sourceforge.tess4j.Tesseract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TesseractConfig {

    @Bean
    public Tesseract tesseract() {
        Tesseract t = new Tesseract();

// Option 1: Try the default path (Tesseract 5+ usually installs here)
        File tessData = new File("/usr/share/tesseract-ocr/5/tessdata");
        if (tessData.exists()) {
            t.setDatapath(tessData.getAbsolutePath());
        } else {
            // fallback: assume system default
            t.setDatapath(null);
        }

        t.setLanguage("eng");
        t.setPageSegMode(6);

//        t.setDatapath("/usr/share/tesseract-ocr/5/tessdata");
//        t.setLanguage("eng");
//        t.setPageSegMode(6); // MRZ friendly
        return t;
    }
}

