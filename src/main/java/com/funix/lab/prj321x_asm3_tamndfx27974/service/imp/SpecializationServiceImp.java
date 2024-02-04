package com.funix.lab.prj321x_asm3_tamndfx27974.service.imp;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.HttpResponse;
import com.funix.lab.prj321x_asm3_tamndfx27974.dto.SpecializationRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Specialization;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.SpecializationRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImp implements SpecializationService {

    private final SpecializationRepository specializationRepository;


    @Override
    public HttpResponse<List<?>> getAllSpecializations() {
        HttpResponse<List<?>> httpResponse = new HttpResponse<>();
        httpResponse.setCode(HttpStatus.OK.value());
        httpResponse.setMessage("Specializations retrieved successfully");
        httpResponse.setData(specializationRepository.findAll());
        return httpResponse;
    }

    @Override
    public HttpResponse<Specialization> createSpecialization(SpecializationRequest specializationRequest) {
        Specialization specialization = new Specialization();
        specialization.setName(specializationRequest.getName());
        specialization.setDescription(specializationRequest.getDescription());
        specialization.setImage(specializationRequest.getImage());
        specializationRepository.save(specialization);

        HttpResponse<Specialization> httpResponse = new HttpResponse<>();
        httpResponse.setCode(HttpStatus.CREATED.value());
        httpResponse.setMessage("Specialization created successfully");
        httpResponse.setData(specialization);
        return httpResponse;
    }

}
