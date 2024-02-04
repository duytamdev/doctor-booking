package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import lombok.Data;

@Data
public class HttpResponse<T> {

    private int code; // 200, 201, 400, 500, etc
    private T data; // User, List<User>, String, etc
    private String message; // Success, Fail, etc
}
