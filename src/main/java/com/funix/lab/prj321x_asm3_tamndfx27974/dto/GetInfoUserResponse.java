package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import com.funix.lab.prj321x_asm3_tamndfx27974.entity.AppointmentSchedule;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class GetInfoUserResponse {
    User userInfo;
    List<AppointmentSchedule> appointmentSchedules;
}
