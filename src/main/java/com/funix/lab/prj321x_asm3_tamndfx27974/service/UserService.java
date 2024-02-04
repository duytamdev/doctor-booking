package com.funix.lab.prj321x_asm3_tamndfx27974.service;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.GetInfoUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    GetInfoUserResponse getInfoUser();
}
