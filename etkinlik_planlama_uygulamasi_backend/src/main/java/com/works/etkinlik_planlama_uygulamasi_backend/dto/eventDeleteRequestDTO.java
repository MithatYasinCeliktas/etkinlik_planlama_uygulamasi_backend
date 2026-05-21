package com.works.etkinlik_planlama_uygulamasi_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class eventDeleteRequestDTO {

    @NotNull(message = "Silinecek etkinlik ID'si boş olamaz")
    private Long id;

    // Optional: useful for logging why the event was removed
    private String deletionReason;
}