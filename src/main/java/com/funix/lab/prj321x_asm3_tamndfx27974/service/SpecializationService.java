package com.funix.lab.prj321x_asm3_tamndfx27974.service;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.HttpResponse;
import com.funix.lab.prj321x_asm3_tamndfx27974.dto.SpecializationRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Specialization;

import java.util.List;

public interface SpecializationService {
    HttpResponse<List<?>> getAllSpecializations();

    HttpResponse<Specialization> createSpecialization(SpecializationRequest specializationRequest);
}
