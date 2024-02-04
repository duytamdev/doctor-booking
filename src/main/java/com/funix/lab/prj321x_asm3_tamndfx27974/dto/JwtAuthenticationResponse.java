package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

    private String token;
    private String refreshToken;
}
