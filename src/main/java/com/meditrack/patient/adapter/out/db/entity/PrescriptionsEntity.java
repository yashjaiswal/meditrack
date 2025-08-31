package com.meditrack.patient.adapter.out.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "prescriptions")
@Data
public class PrescriptionsEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long patientId;

    @Column
    private Long appointmentId;

    @Column
    private Long medicineId;

    @Column
    private String dosage;

    @Column
    private String specialInstruction;

    @Column
    private Integer durationInDays;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @Version
    private Integer version = 0;
}