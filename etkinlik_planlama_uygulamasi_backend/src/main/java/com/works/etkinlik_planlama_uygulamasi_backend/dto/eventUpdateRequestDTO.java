package com.works.etkinlik_planlama_uygulamasi_backend.dto;

import com.works.etkinlik_planlama_uygulamasi_backend.util.eventStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class eventUpdateRequestDTO {

    @NotNull(message = "Güncellenecek etkinlik ID'si gereklidir")
    private Long id;

    @NotBlank(message = "Etkinlik adı boş bırakılamaz")
    private String title;

    @NotNull(message = "Tarih boş bırakılamaz")
    @FutureOrPresent(message = "Tarih geçmiş bir zaman olamaz")
    private Date date;

    @NotNull(message = "Saat boş bırakılamaz")
    private Time time;

    @NotBlank(message = "Yer bilgisi boş bırakılamaz")
    private String location;

    private String description;

    @NotBlank(message = "Kategori boş bırakılamaz")
    private String category;

    private eventStatus status;
}