package com.funix.lab.prj321x_asm3_tamndfx27974.controller;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.ClinicRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.ClinicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ClinicController {

    private final ClinicService clinicService;

    @GetMapping("/public/clinics")
    public ResponseEntity<?> getAllClinics() {
        return ResponseEntity.ok(clinicService.getAllClinics());
    }

    @PostMapping("/admin/clinics/create")
    public ResponseEntity<?> createClinic(@Valid @RequestBody ClinicRequest clinicRequest) {
        return ResponseEntity.ok(clinicService.createClinic(clinicRequest));
    }
}
