package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClinicRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Phone is mandatory")
    private String phone;
    private String introductionHTML;
    private String introductionMarkdown;
    private String description;
    private String image;

}
