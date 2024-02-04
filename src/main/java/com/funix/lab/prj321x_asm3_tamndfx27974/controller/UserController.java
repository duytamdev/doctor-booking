package com.funix.lab.prj321x_asm3_tamndfx27974.controller;

import com.funix.lab.prj321x_asm3_tamndfx27974.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @RequestMapping("/info")
    public ResponseEntity<?> getInfoUser() {
        return ResponseEntity.ok(userService.getInfoUser());
    }

}
