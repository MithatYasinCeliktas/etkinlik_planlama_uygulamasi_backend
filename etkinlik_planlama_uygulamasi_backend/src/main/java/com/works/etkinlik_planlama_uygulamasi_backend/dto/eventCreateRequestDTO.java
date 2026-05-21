package com.works.etkinlik_planlama_uygulamasi_backend.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.sql.Time;

@Data
public class eventCreateRequestDTO {

    @NotBlank(message = "Etkinlik adı boş bırakılamaz")
    private String title;

    @NotNull(message = "Tarih boş bırakılamaz")
    @Future(message = "Etkinlik tarihi bugünden ileri bir tarih olmalıdır") // Fulfills "bigger than current date" requirement
    private Date date;

    @NotNull(message = "Saat boş bırakılamaz")
    private Time time;

    @NotBlank(message = "Yer bilgisi boş bırakılamaz")
    private String location;

    private String description;

    @NotBlank(message = "Kategori boş bırakılamaz")
    private String category;
}