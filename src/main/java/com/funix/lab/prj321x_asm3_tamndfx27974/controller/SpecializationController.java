package com.funix.lab.prj321x_asm3_tamndfx27974.controller;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.SpecializationRequest;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.SpecializationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationService specializationService;


    @GetMapping("/public/specializations")
    public ResponseEntity<?> getAllSpecializations() {
        return ResponseEntity.ok(specializationService.getAllSpecializations());
    }

    @PostMapping("/admin/specializations/create")
    public ResponseEntity<?> createSpecialization(@Valid @RequestBody SpecializationRequest specializationRequest) {
        return ResponseEntity.ok(specializationService.createSpecialization(specializationRequest));
    }

}
