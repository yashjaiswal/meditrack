package com.meditrack.prescription.domain;

import lombok.Data;

import java.util.List;

@Data
public class PrescriptionDetails {

    private List<Dosage> dosageList;
}
