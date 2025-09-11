package com.meditrack.prescription.adapter.out.db.entity;

import com.meditrack.prescription.domain.PrescriptionDetails;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

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
    private Long doctorId;

    @Column
    private Long appointmentId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private PrescriptionDetails prescriptionDetails;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    @Version
    private Integer version = 0;
}