package com.meditrack.patient.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AddPatientRequest {
    private String name;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private Gender gender;
    private String comments;
    private String email;

}
