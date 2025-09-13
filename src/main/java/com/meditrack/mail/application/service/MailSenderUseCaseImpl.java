package com.meditrack.mail.application.service;

import com.meditrack.mail.application.ports.MailSenderUseCase;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSenderUseCaseImpl implements MailSenderUseCase {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendPrescriptionToEmail(byte[] prescriptionPdfBytes, String patientEmail, String pdfFileName) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
try {
    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
    helper.setFrom("doctor@meditrack.com");
    helper.setTo(patientEmail);
    helper.setSubject("Prescription PDF");
    helper.setText("Dear Patient, please find your prescription attached");
    helper.addAttachment(
            pdfFileName,
            () -> new ByteArrayInputStream(prescriptionPdfBytes),
            "application/pdf"
    );

    mailSender.send(mimeMessage);
} catch (Exception e) {
    e.printStackTrace();
    throw new RuntimeException(e);
}

    }
}
