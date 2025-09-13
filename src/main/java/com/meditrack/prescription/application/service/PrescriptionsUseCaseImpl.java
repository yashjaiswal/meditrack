package com.meditrack.prescription.application.service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.meditrack.accounts.application.ports.AccountsPersistenceUseCase;
import com.meditrack.medicine.application.ports.MedicinePersistenceUseCase;
import com.meditrack.medicine.domain.Medicine;
import com.meditrack.patient.application.ports.AppointmentPersistenceUseCase;
import com.meditrack.patient.application.ports.PatientPersistenceUseCase;
import com.meditrack.patient.domain.AppointmentRequest;
import com.meditrack.patient.domain.Patient;
import com.meditrack.prescription.application.ports.PrescriptionsPersistenceUseCase;
import com.meditrack.prescription.application.ports.PrescriptionsUseCase;
import com.meditrack.prescription.domain.PrescriptionCreationRequest;
import com.meditrack.prescription.domain.PrescriptionDosage;
import com.meditrack.prescription.domain.PrescriptionMetadataAndDetails;
import com.meditrack.prescription.domain.UpdatePrescriptionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.Period;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrescriptionsUseCaseImpl implements PrescriptionsUseCase {

    private final PrescriptionsPersistenceUseCase prescriptionsPersistenceUseCase;
    private final AppointmentPersistenceUseCase appointmentPersistenceUseCase;
    private final PatientPersistenceUseCase patientPersistenceUseCase;
    private final AccountsPersistenceUseCase accountsPersistenceUseCase;
    private final MedicinePersistenceUseCase medicinePersistenceUseCase;

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

        PrescriptionMetadataAndDetails prescriptionMetadataAndDetails =
                prescriptionsPersistenceUseCase.fetchPrescriptionMetadataAndDetails(prescriptionId);

        Patient patient = patientPersistenceUseCase.getPatientDetails(prescriptionMetadataAndDetails.getPatientId());
        String doctorName = accountsPersistenceUseCase.findDoctorName(prescriptionMetadataAndDetails.getDoctorId());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);

        document.open();
        document.add(new Paragraph("Prescription"));
        document.add(new Paragraph("Date of Prescription: " + prescriptionMetadataAndDetails.getPrescriptionDate()));

        document.add(new Paragraph("-----------------------"));
        document.add(new Paragraph("Doctor: " + doctorName));
        document.add(new Paragraph("Patient: " + patient.getName()));
        document.add(new Paragraph("Gender: " + patient.getGender()));
        document.add(new Paragraph("Age: " + Period.between(patient.getDateOfBirth().toLocalDate(), LocalDate.now()).getYears()));
        document.add(new Paragraph("-----------------------"));

        for (PrescriptionDosage prescriptionDosage : prescriptionMetadataAndDetails.getPrescriptionDetails().getPrescriptionDosageList()) {
            Medicine medicine = medicinePersistenceUseCase.getMedicine(prescriptionDosage.getMedicineId());
            document.add(new Paragraph(medicine.getName() + " " + medicine.getStrengthInMg() + " " + prescriptionDosage.getDosageType().getDescription()));
            document.add(new Paragraph("Duration: " + prescriptionDosage.getDurationInDays()));
            if (StringUtils.isNotBlank(prescriptionDosage.getSpecialInstruction())) {
                document.add(new Paragraph("Special Instruction: " + prescriptionDosage.getSpecialInstruction()));

            }
            document.add(new Paragraph("***********************"));


        }
        document.add(new Paragraph("END OF PRESCRIPTION"));

        document.add(new Paragraph());
        document.add(new Paragraph("Signature"));


        document.close();

        String fileName = "prescription-" + patient.getName() + prescriptionMetadataAndDetails.getPrescriptionDate().toString() + ".pdf";

        // Return as ResponseEntity
        byte[] pdfBytes = out.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", fileName);
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
