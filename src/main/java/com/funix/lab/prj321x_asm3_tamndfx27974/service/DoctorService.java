package com.funix.lab.prj321x_asm3_tamndfx27974.service;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.DoctorCreateRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.Doctor;

public interface DoctorService {
    Doctor createDoctor(DoctorCreateRequest req);
}
