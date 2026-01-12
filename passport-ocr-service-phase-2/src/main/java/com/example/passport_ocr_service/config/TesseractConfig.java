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
        File tessData = new File("/usr/share/tesseract-ocr/4.00/tessdata");
        System.out.println("Tessdata exists? " + tessData.exists());
        if (!tessData.exists()) {
            System.out.println("Listing /usr/share/tesseract-ocr:");
            File[] files = new File("/usr/share/tesseract-ocr").listFiles();
            for (File f : files) System.out.println(" - " + f.getName());
        }
        t.setDatapath(tessData.exists() ? tessData.getAbsolutePath() : null);
        t.setLanguage("eng");
        t.setPageSegMode(6);

        return t;
    }
}

