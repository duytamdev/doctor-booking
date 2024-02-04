package com.funix.lab.prj321x_asm3_tamndfx27974.controller;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.DoctorCreateRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;


    @PostMapping("/admin/doctor/create")
    public ResponseEntity<?> createDoctor(@Valid @RequestBody DoctorCreateRequest req) {
        return ResponseEntity.ok(doctorService.createDoctor(req));
    }


}
