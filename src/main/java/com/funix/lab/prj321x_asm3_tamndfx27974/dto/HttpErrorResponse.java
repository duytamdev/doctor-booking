package com.funix.lab.prj321x_asm3_tamndfx27974.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
public class HttpErrorResponse {
    private String status;
    private String message;
    @CreationTimestamp
    private Date timestamp;
    private List<String> errors;
    private String path;

    public HttpErrorResponse() {
        this.timestamp = new Date();
    }
}
