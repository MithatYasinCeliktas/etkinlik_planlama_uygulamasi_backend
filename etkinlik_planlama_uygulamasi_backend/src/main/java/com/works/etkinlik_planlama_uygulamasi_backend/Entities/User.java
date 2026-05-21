package com.works.etkinlik_planlama_uygulamasi_backend.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ad Soyad boş bırakılamaz")
    private String fullName;

    @Column(unique = true)
    @Email(message = "Geçerli bir email adresi giriniz")
    @NotBlank(message = "Email boş bırakılamaz")
    private String email;

    @NotBlank(message = "Şifre boş bırakılamaz")
    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır")
    @Column(length = 255) // BCrypt şifreleri için fazlasıyla yeterli alan
    private String password;

    // Relationship: One user can create many events
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Event> createdEvents;
}