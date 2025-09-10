package com.meditrack.prescription.domain.errors;

public class PrescriptionAlreadyExistsException extends RuntimeException{
    public PrescriptionAlreadyExistsException(String message) {
        super(message);
    }
}
