package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SendEmailRequest {
    @Email(message = "Email is invalid")
    String email;
}
