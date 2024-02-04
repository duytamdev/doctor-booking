package com.funix.lab.prj321x_asm3_tamndfx27974.service;


import com.funix.lab.prj321x_asm3_tamndfx27974.dto.*;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.User;

public interface AuthenticationService {
    User signup(SignupRequest signupRequest);

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    HttpResponse<ForgotPasswordResponse> forgotPassword(SendEmailRequest email);

    User resetPassword(ResetPasswordRequest resetPasswordRequest);

    User getCurrentUser();

    HttpResponse<String> lockUser(String email);

    HttpResponse<String> unlockUser(String email);
}
