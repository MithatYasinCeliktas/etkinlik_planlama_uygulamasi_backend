package com.works.etkinlik_planlama_uygulamasi_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class userLoginRequestDTO {

    @Email(message = "Geçerli bir email adresi giriniz")
    @NotBlank(message = "Email boş bırakılamaz")
    private String email;

    @NotBlank(message = "Şifre boş bırakılamaz")
    private String password;
}