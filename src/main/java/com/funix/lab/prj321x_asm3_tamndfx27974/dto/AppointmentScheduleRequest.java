package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import lombok.Data;

@Data
public class AppointmentScheduleRequest {

    private int doctorId;
    private String hours;
    private int fee;
    private String reasonForVisit;
}
