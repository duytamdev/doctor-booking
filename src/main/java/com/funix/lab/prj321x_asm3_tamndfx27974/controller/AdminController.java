package com.funix.lab.prj321x_asm3_tamndfx27974.controller;

import com.funix.lab.prj321x_asm3_tamndfx27974.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthenticationService authenticationService;

    @PostMapping("/lockAccount")
    public ResponseEntity<?> deleteAccount(@RequestParam String email) {
        return ResponseEntity.ok(authenticationService.lockUser(email));
    }

    @PostMapping("/unlockAccount")
    public ResponseEntity<?> unlockAccount(@RequestParam String email) {
        return ResponseEntity.ok(authenticationService.unlockUser(email));
    }
}
