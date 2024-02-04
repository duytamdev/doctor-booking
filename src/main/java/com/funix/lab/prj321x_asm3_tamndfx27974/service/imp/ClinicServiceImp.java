package com.funix.lab.prj321x_asm3_tamndfx27974.service.imp;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.ClinicRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.dto.HttpResponse;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Clinic;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.ClinicRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicServiceImp implements ClinicService {

    private final ClinicRepository clinicRepository;

    @Override
    public HttpResponse<List<Clinic>> getAllClinics() {
        HttpResponse<List<Clinic>> response = new HttpResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Clinics retrieved successfully");
        response.setData(clinicRepository.findAll());
        return response;
    }

    @Override
    public HttpResponse<Clinic> createClinic(ClinicRequest clinicRequest) {

        Clinic clinic = new Clinic();
        clinic.setName(clinicRequest.getName());
        clinic.setAddress(clinicRequest.getAddress());
        clinic.setPhone(clinicRequest.getPhone());
        clinic.setIntroductionHTML(clinicRequest.getIntroductionHTML());
        clinic.setIntroductionMarkdown(clinicRequest.getIntroductionMarkdown());
        clinic.setDescription(clinicRequest.getDescription());
        clinic.setImage(clinicRequest.getImage());

        clinicRepository.save(clinic);

        HttpResponse<Clinic> response = new HttpResponse<>();
        response.setCode(HttpStatus.CREATED.value());
        response.setMessage("Clinic created successfully");
        response.setData(clinic);
        return response;
    }
}
