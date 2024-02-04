package com.funix.lab.prj321x_asm3_tamndfx27974.config;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.HttpErrorResponse;
import com.funix.lab.prj321x_asm3_tamndfx27974.errorException.EmailExistsException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        HttpErrorResponse errorResponse = new HttpErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setErrors(Collections.singletonList(exception.getMessage()));
        errorResponse.setPath(exception.getStackTrace()[0].getClassName());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException ex, WebRequest request) {
        HttpErrorResponse body = new HttpErrorResponse();
        body.setStatus(HttpStatus.FORBIDDEN.toString());
        body.setMessage(ex.getMessage());
        body.setErrors(Collections.singletonList(ex.getMessage()));
        body.setPath(request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        HttpErrorResponse responseBody = new HttpErrorResponse();
        responseBody.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        responseBody.setMessage("Validation Failed");
        responseBody.setErrors(errors);
        responseBody.setPath(request.getDescription(false));

        return new ResponseEntity<>(responseBody, headers, status);
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<Object> handleEmailExistsException(EmailExistsException ex) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("error", "Email Exists");
        responseBody.put("message", ex.getMessage());
        responseBody.put("path", "/api/v1/auth/signup");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("error", "Bad Credentials");
        responseBody.put("message", ex.getMessage());
        responseBody.put("path", "/api/v1/auth/signin");
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}
