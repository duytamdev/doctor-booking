package com.funix.lab.prj321x_asm3_tamndfx27974.service.imp;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.HttpResponse;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Clinic;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Specialization;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.ClinicRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.SpecializationRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchServiceImp implements SearchService {

    private final ClinicRepository clinicRepository;
    private final SpecializationRepository specializationRepository;

    public HttpResponse<Map<String, List<?>>> searchCommon(String keyword) {
        List<Clinic> clinics = clinicRepository.searchClinic(keyword);
        List<Specialization> specializations = specializationRepository.searchSpecialization(keyword);

        Map<String, List<?>> combined = new HashMap<>();
        combined.put("clinics", clinics);
        combined.put("specializations", specializations);

        HttpResponse<Map<String, List<?>>> response = new HttpResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Search results retrieved successfully");
        response.setData(combined);
        return response;

    }

    @Override
    public HttpResponse<?> searchSpecialization(String keyword) {
        List<Specialization> specializations = specializationRepository.searchSpecialization(keyword);
        HttpResponse<List<Specialization>> response = new HttpResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("Search results retrieved successfully");
        response.setData(specializations);
        return response;
    }
}
