package com.example.knowledgehub_library_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(ResourceNotFoundException ex)
    {
        Map<String,Object> response = new HashMap<>();
        response.put("status",404);
        response.put("message",ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleGeneric(Exception ex)
    {
        Map<String,Object> response = new HashMap<>();
        response.put("status",500);
        response.put("message","Something went wrong");

        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<Map<String,Object>>handleAccessDenied(Exception ex)
    {
        Map<String,Object> response = new HashMap<>();
        response.put("status",403);
        response.put("message","Access Denied");

        return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
    }

}
