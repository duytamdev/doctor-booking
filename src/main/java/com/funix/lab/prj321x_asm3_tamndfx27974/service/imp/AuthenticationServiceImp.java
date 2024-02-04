package com.funix.lab.prj321x_asm3_tamndfx27974.service.imp;


import com.funix.lab.prj321x_asm3_tamndfx27974.dto.*;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.PasswordResetToken;
import com.funix.lab.prj321x_asm3_tamndfx27974.entity.User;
import com.funix.lab.prj321x_asm3_tamndfx27974.errorException.EmailExistsException;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.PasswordResetTokenRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.RoleRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.repository.UserRepository;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.AuthenticationService;
import com.funix.lab.prj321x_asm3_tamndfx27974.service.JWTService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImp implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    public User signup(SignupRequest signupRequest) {
        if (emailExists(signupRequest.getEmail())) {
            throw new EmailExistsException("Email already exists in the system");
        }

        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPhone(signupRequest.getPhone());
        user.setAddress(signupRequest.getAddress());
        user.setRole(roleRepository.findByName("USER"));
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        System.out.println("signinRequest = " + signinRequest);
        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        String jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        if (jwtService.validateToken(refreshTokenRequest.getToken(), user)) {
            String jwt = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshToken);
            return jwtAuthenticationResponse;
        }
        return null;
    }

    @Override
    public HttpResponse<ForgotPasswordResponse> forgotPassword(SendEmailRequest body) {
        System.out.println("email = " + body.getEmail());
        User user = userRepository.findByEmail(body.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
        String token = UUID.randomUUID().toString().substring(0, 6);
        //send email
        emailService.sendPasswordResetEmail(user.getEmail(), token);
        //save token
        // expiredTime = now + 1 minute
        Date expiredTime = new Date(System.currentTimeMillis() + 1000 * 60);
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(expiredTime);
        passwordResetTokenRepository.save(passwordResetToken);
        //response
        ForgotPasswordResponse forgotPasswordResponse = new ForgotPasswordResponse();
        forgotPasswordResponse.setExpiredTime(expiredTime.toString());
        forgotPasswordResponse.setToken(token);
        HttpResponse<ForgotPasswordResponse> response = new HttpResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setData(forgotPasswordResponse);
        response.setMessage("Email sent");
        return response;
    }

    @Override
    public User resetPassword(ResetPasswordRequest resetPasswordRequest) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByUserEmail(resetPasswordRequest.getEmail()).orElseThrow(() -> new RuntimeException("Token not found"));
        if (!passwordResetToken.getToken().equals(resetPasswordRequest.getToken())) {
            throw new RuntimeException("Token not match");
        }
        if (passwordResetToken.getExpiryDate().before(new Date())) {
            passwordResetTokenRepository.delete(passwordResetToken);
            throw new RuntimeException("Token expired");
        }

        // reset password
        User user = passwordResetToken.getUser();
        user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
        userRepository.save(user);
        //delete token
        passwordResetTokenRepository.delete(passwordResetToken);
        return user;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }

        return null;
    }

    @Override
    @Transactional
    public HttpResponse<String> lockUser(String email) {
        if (!emailExists(email)) {
            throw new EmailExistsException("Email not exists in the system");
        }
        userRepository.lockByEmail(email);
        HttpResponse<String> response = new HttpResponse<>();
        response.setCode(HttpStatus.LOCKED.value());
        response.setMessage("User " + email + " locked");
        return response;

    }

    @Override
    @Transactional
    public HttpResponse<String> unlockUser(String email) {
        if (!emailExists(email)) {
            throw new EmailExistsException("Email not exists in the system");
        }
        userRepository.unlockByEmail(email);
        HttpResponse<String> response = new HttpResponse<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("User " + email + " unlocked");
        return response;
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
