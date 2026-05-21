package com.works.etkinlik_planlama_uygulamasi_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
public class errorResponseDTO {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> details; // For validation errors (field -> message)
}