package com.example.passport_ocr_service.controllers;

import com.example.passport_ocr_service.model.PassportData;
import com.example.passport_ocr_service.service.MrzParserService;
import com.example.passport_ocr_service.service.OcrService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;


@RestController
@RequestMapping("/api/ocr")
@CrossOrigin
public class OcrController {
//  just a comment
    @Autowired
    private OcrService ocrService;

    @Autowired
    private MrzParserService mrzParser;

    @PostMapping("/passport")
    public PassportData upload(@RequestParam("image") MultipartFile file)
            throws Exception {

        String text = ocrService.extractText(file);
        return mrzParser.parse(text);
    }
}
