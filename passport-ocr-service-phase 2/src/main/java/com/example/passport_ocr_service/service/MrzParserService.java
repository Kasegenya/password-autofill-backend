package com.example.passport_ocr_service.service;

import com.example.passport_ocr_service.model.PassportData;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import com.example.passport_ocr_service.model.PassportData;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Arrays;


@Service
public class MrzParserService {

    public PassportData parse(String ocrText) {

        // 1️⃣ Extract MRZ lines safely
        String[] mrzLines = extractMrzLines(ocrText);

        if (mrzLines.length < 2) {
            throw new IllegalArgumentException("MRZ lines not found in OCR text");
        }

        // MRZ is always last two valid MRZ-looking lines
        String line1 = normalizeMrz(mrzLines[mrzLines.length - 2]);
        String line2 = normalizeMrz(mrzLines[mrzLines.length - 1]);

        if (line1.length() < 44 || line2.length() < 44) {
            throw new IllegalArgumentException("Invalid MRZ line length");
        }

        PassportData data = new PassportData();

        // 2️⃣ Parse Line 1 (Names)
        parseNames(line1, data);

        // 3️⃣ Parse Line 2 (Document details)
        parseDocumentData(line2, data);

        return data;
    }

    // --------------------------------------------------
    // 🔹 MRZ LINE EXTRACTION
    // --------------------------------------------------
    private String[] extractMrzLines(String text) {
        return Arrays.stream(text.split("\n"))
                .map(String::trim)
                .filter(line ->
                        line.startsWith("P<") ||
                                (line.length() >= 30 && line.contains("<"))
                )
                .toArray(String[]::new);
    }

    // --------------------------------------------------
    // 🔹 OCR NORMALIZATION (CRITICAL)
    // --------------------------------------------------
    private String normalizeMrz(String line) {
        return line
                .toUpperCase()
                .replace('O', '0')
                .replace('D', '0')
                .replace('I', '1')
                .replace('L', '1')
                .replace('S', '5')
                .replace('B', '8')
                .replace('G', '6')
                .replaceAll("[^A-Z0-9<]", "");
    }

    // --------------------------------------------------
    // 🔹 LINE 1 — NAMES
    // --------------------------------------------------
    private void parseNames(String line1, PassportData data) {

        // Skip "P<TZA"
        String nameBlock = line1.substring(5);

        String[] parts = nameBlock.split("<<");

        String lastName = parts[0].replace("<", "");

        String firstNames = parts.length > 1
                ? parts[1].replace("<", " ").trim()
                : "";

        data.setLastName(lastName);
        data.setFirstName(firstNames);
    }

    // --------------------------------------------------
    // 🔹 LINE 2 — DOCUMENT DATA
    // --------------------------------------------------
    private void parseDocumentData(String line2, PassportData data) {

        // Passport number
        data.setPassportNumber(line2.substring(0, 9));

        // Nationality
        data.setNationality(line2.substring(10, 13));

        // Date of birth (YYMMDD)
        data.setDateOfBirth(line2.substring(13, 19));

        // Gender
        String gender = line2.substring(20, 21);
        data.setGender(gender.equals("<") ? "X" : gender);

        // Expiry date (YYMMDD)
        data.setExpiryDate(line2.substring(21, 27));
    }
}
