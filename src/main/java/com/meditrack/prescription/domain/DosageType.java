package com.meditrack.prescription.domain;


public enum DosageType {

    OD("Once daily"),
    BD("Twice daily"),
    TDS("Thrice daily"),
    QID("Four times daily"),
    QAM("Once every morning, before meals");

    private final String description;

    // Constructor for enum
    DosageType(String description) {
        this.description = description;
    }

    // Getter
    public String getDescription() {
        return description;
    }
}
