package com.meditrack.patient.domain;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Patient {
    private String name;
    private Timestamp dateOfBirth;
    private String phoneNumber;
    private Gender gender;
}

enum Gender {
    male, female, others;
}
