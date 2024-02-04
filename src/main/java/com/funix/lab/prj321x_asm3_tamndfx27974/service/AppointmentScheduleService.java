package com.funix.lab.prj321x_asm3_tamndfx27974.service;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.AppointmentScheduleRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.dto.HttpResponse;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.AppointmentSchedule;

import java.util.List;

public interface AppointmentScheduleService {
    HttpResponse<AppointmentSchedule> createAppointmentSchedule(AppointmentScheduleRequest req);

    HttpResponse<List<AppointmentSchedule>> getAllAppointmentScheduleByDoctor(int doctorId);

    HttpResponse<List<AppointmentSchedule>> getAllAppointmentScheduleByUser(int userId);
}
