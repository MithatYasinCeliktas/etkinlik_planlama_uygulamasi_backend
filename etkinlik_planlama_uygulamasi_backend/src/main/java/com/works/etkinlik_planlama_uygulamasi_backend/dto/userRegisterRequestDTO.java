package com.works.etkinlik_planlama_uygulamasi_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class userRegisterRequestDTO {


    @NotBlank(message = "Ad Soyad boş bırakılamaz") // Requirement: Cannot be empty [cite: 111]
    private String fullName;

    @Email(message = "Geçerli bir email adresi giriniz") // Requirement: Email format check [cite: 111]
    @NotBlank(message = "Email boş bırakılamaz")
    private String email;

    @NotBlank(message = "Şifre boş bırakılamaz")
    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır") // Requirement: Min password length [cite: 112]
    private String password;
}