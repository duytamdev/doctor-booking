package com.funix.lab.prj321x_asm3_tamndfx27974.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {

    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    String generateRefreshToken(Map<String, Object> map, UserDetails userDetails);

    Claims extractAllClaims(String token);
}
