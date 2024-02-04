package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecializationRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
    private String image;
}
