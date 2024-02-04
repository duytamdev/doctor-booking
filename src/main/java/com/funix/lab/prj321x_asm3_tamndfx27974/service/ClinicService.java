package com.funix.lab.prj321x_asm3_tamndfx27974.service;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.ClinicRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.dto.HttpResponse;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Clinic;

import java.util.List;

public interface ClinicService {

    HttpResponse<List<Clinic>> getAllClinics();

    HttpResponse<Clinic> createClinic(ClinicRequest clinic);
}
