package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DoctorCreateRequest extends SignupRequest {
    private String introduction;
    private String trainingProcess;
    private List<String> specializationsId;
}
