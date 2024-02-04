package com.funix.lab.prj321x_asm3_tamndfx27974.controller;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.AppointmentScheduleRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.AppointmentScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AppointmentScheduleController {

    private final AppointmentScheduleService appointmentScheduleService;

    @PostMapping("/appointment-schedule/create")
    public ResponseEntity<?> createAppointmentSchedule(@RequestBody AppointmentScheduleRequest req) {
        return ResponseEntity.ok(appointmentScheduleService.createAppointmentSchedule(req));
    }

    @GetMapping("/appointment-schedule/doctor/{doctorId}")
    public ResponseEntity<?> getAllAppointmentScheduleByDoctor(@PathVariable int doctorId) {
        return ResponseEntity.ok(appointmentScheduleService.getAllAppointmentScheduleByDoctor(doctorId));
    }

    @GetMapping("/appointment-schedule/user/{userId}")
    public ResponseEntity<?> getAllAppointmentScheduleByUser(@PathVariable int userId) {
        return ResponseEntity.ok(appointmentScheduleService.getAllAppointmentScheduleByUser(userId));
    }
}
