package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import lombok.Data;

@Data
public class AppointmentScheduleResponse {
    private int id;
    private String hours;
    private int fee;
    private String reasonForVisit;
    private String nameDoctor;
    private String doctorId;
    private String nameUser;
    private String emailUser;
    private String phoneUser;
    private String addressUser;
}
