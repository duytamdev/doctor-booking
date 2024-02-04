package com.funix.lab.prj321x_asm3_tamndfx27974.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "appointment_schedule")
@Data
public class AppointmentSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    // khung gio kham
    private String hours;
    private int fee;
    @Column(name = "reason_for_visit")
    private String reasonForVisit;
}
