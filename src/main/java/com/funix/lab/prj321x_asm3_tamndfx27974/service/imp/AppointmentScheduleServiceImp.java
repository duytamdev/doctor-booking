package com.funix.lab.prj321x_asm3_tamndfx27974.service.imp;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.AppointmentScheduleRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.dto.HttpResponse;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.AppointmentSchedule;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Doctor;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.User;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.AppointmentScheduleRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.DoctorRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.AppointmentScheduleService;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AppointmentScheduleServiceImp implements AppointmentScheduleService {

    private final AppointmentScheduleRepository appointmentScheduleRepository;
    private final AuthenticationService authenticationService;
    private final DoctorRepository doctorRepository;

    @Override
    public HttpResponse<AppointmentSchedule> createAppointmentSchedule(AppointmentScheduleRequest req) {
        User user = authenticationService.getCurrentUser();
        Doctor doctor = doctorRepository.findById(req.getDoctorId()).get();

        AppointmentSchedule appointmentSchedule = new AppointmentSchedule();
        appointmentSchedule.setUser(user);
        appointmentSchedule.setDoctor(doctor);
        appointmentSchedule.setHours(req.getHours());
        appointmentSchedule.setFee(req.getFee());
        appointmentSchedule.setReasonForVisit(req.getReasonForVisit());

        appointmentScheduleRepository.save(appointmentSchedule);

        HttpResponse<AppointmentSchedule> response = new HttpResponse<>();
        response.setCode(HttpStatus.CREATED.value());
        response.setData(appointmentSchedule);
        response.setMessage("Appointment schedule created successfully");

        return response;
    }

    @Override
    public HttpResponse<List<AppointmentSchedule>> getAllAppointmentScheduleByDoctor(int doctorId) {
        HttpResponse<List<AppointmentSchedule>> response = new HttpResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setData(appointmentScheduleRepository.findByDoctorId(doctorId));
        response.setMessage("Get all appointment schedule by doctor successfully");
        return response;
    }

    @Override
    public HttpResponse<List<AppointmentSchedule>> getAllAppointmentScheduleByUser(int userId) {
        HttpResponse<List<AppointmentSchedule>> response = new HttpResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setData(appointmentScheduleRepository.findByUserId(userId));
        response.setMessage("Get all appointment schedule by user successfully");
        return response;
    }
}
