package com.meditrack.mail.application.ports;

import jakarta.mail.MessagingException;

public interface MailSenderUseCase {

    void sendPrescriptionToEmail(byte[] prescriptionPdfBytes, String patientEmail, String pdfFileName) throws MessagingException;
}
