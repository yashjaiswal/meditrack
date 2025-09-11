package com.meditrack.patient.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class Patient {
    private String name;
    private LocalDateTime dateOfBirth;
    private String phoneNumber;
    private Gender gender;
}
