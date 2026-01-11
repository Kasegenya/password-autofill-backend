package com.example.passport_ocr_service.model;


import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassportData {
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String nationality;
    private String dateOfBirth;
    private String gender;
    private String expiryDate;   // Optional: from MRZ

    // Optional helper to update fullName
//    public void updateFullName() {
//        this.fullName = (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
//    }
}

