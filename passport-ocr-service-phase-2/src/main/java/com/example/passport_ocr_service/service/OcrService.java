package com.example.passport_ocr_service.service;

import lombok.extern.log4j.Log4j2;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class OcrService {

    @Autowired
    private Tesseract tesseract;

    @Autowired
    private ImagePreprocessService preprocessService;

    public String extractText(MultipartFile file) throws Exception {
        File temp = File.createTempFile("passport", ".png");
        file.transferTo(temp);

        BufferedImage image = preprocessService.preprocess(temp);
        return tesseract.doOCR(image);
    }
}
