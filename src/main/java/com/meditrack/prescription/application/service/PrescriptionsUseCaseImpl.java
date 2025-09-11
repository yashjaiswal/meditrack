package com.meditrack.prescription.application.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.meditrack.patient.application.ports.AppointmentPersistenceUseCase;
import com.meditrack.patient.domain.AppointmentRequest;
import com.meditrack.prescription.application.ports.PrescriptionsPersistenceUseCase;
import com.meditrack.prescription.application.ports.PrescriptionsUseCase;
import com.meditrack.prescription.domain.PrescriptionCreationRequest;
import com.meditrack.prescription.domain.UpdatePrescriptionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrescriptionsUseCaseImpl implements PrescriptionsUseCase {

    private final PrescriptionsPersistenceUseCase prescriptionsPersistenceUseCase;
    private final AppointmentPersistenceUseCase appointmentPersistenceUseCase;

    @Override
    public Long createPrescription(Long doctorId, PrescriptionCreationRequest prescriptionCreationRequest) {
        Long prescriptionId = prescriptionsPersistenceUseCase.createPrescription(prescriptionCreationRequest.getPatientId(), prescriptionCreationRequest.getAppointmentId(), doctorId);
        return prescriptionId;
    }

    @Override
    public void updatePrescription(UpdatePrescriptionRequest updatePrescriptionRequest) {
        prescriptionsPersistenceUseCase.updatePrescription(updatePrescriptionRequest);

        // Set an appointment for future date for this patient
        appointmentPersistenceUseCase.storeAppointment(formAppointmentRequest(updatePrescriptionRequest));

    }

    @Override
    public ResponseEntity<byte[]> generatePrescriptionPDF(Long prescriptionId) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);

        document.open();
        document.add(new Paragraph("Prescription"));
        document.add(new Paragraph("-----------------------"));
        document.add(new Paragraph("Patient: John Doe"));
        document.add(new Paragraph("Medicine: Paracetamol 500mg"));
        document.close();

        // Return as ResponseEntity
        byte[] pdfBytes = out.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "prescription.pdf");
        headers.setContentLength(pdfBytes.length);

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    private AppointmentRequest formAppointmentRequest(UpdatePrescriptionRequest updatePrescriptionRequest) {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setAppointmentDate(updatePrescriptionRequest.getNextAppointmentDate());
        appointmentRequest.setPatientId(updatePrescriptionRequest.getPatientId());
        if (updatePrescriptionRequest.getCommentsForNextAppointment() != null) {
            appointmentRequest.setComments(updatePrescriptionRequest.getCommentsForNextAppointment());
        }

        return appointmentRequest;
    }
}
