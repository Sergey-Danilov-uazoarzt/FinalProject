package com.example.restapi_skillfactory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorDTO {
    private final String objectName;
    private final String field;
    private final String message;
}
