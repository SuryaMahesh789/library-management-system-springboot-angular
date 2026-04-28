package com.example.knowledgehub_library_management_system.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T>
{
    private int status;
    private String message;
    private T data;
}
