//package com.example.passport_ocr_service.controllers;
//
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.InputStreamReader;
//
//@RestController
//@RequestMapping("/api/test")
//public class TestController {
//
//    @Autowired
//    private Tesseract tesseract;
//
//    @GetMapping("/tesseract")
//    public String testTesseract() {
//        try {
//            Process p = Runtime.getRuntime().exec("tesseract -v");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            StringBuilder output = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                output.append(line).append("\n");
//            }
//            p.waitFor();
//            return output.toString();
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }
//
//    @GetMapping("/version")
//    public String tesseractVersion() {
//        try {
//            Process p = Runtime.getRuntime().exec("tesseract -v");
//            p.waitFor();
//            return new String(p.getInputStream().readAllBytes());
//        } catch (Exception e) {
//            return "Error: " + e.getMessage();
//        }
//    }
//
//    @PostMapping("/ocr")
//    public String testOcr(@RequestParam("file") MultipartFile file) {
//        try {
//            File temp = File.createTempFile("test-", ".png");
//            file.transferTo(temp);
//
//            // Print datapath being used
//            System.out.println("Tesseract datapath: " + getDatapath(tesseract));
//
//            return tesseract.doOCR(temp);
//        } catch (TesseractException e) {
//            return "Tesseract exception: " + e.getMessage();
//        } catch (Exception e) {
//            return "Other exception: " + e.getMessage();
//        }
//    }
//
//    // Add a getter in your controller or Tesseract wrapper
//    public String getDatapath(Tesseract tesseract) {
//        try {
//            java.lang.reflect.Field field = Tesseract.class.getDeclaredField("datapath");
//            field.setAccessible(true);
//            return (String) field.get(tesseract);
//        } catch (Exception e) {
//            return "unknown";
//        }
//    }
//
//
//}
