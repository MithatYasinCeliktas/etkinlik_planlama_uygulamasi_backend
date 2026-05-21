package com.works.etkinlik_planlama_uygulamasi_backend.Controller;

import com.works.etkinlik_planlama_uygulamasi_backend.dto.userLoginRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.dto.userRegisterRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.dto.userResponseDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.Entities.User;
import com.works.etkinlik_planlama_uygulamasi_backend.Services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; // Constructor yardımıyla enjekte edilen servis bean'i

    @PostMapping("/register")
    public ResponseEntity<userResponseDTO> register(@Valid @RequestBody userRegisterRequestDTO registrationDto) {
        User saved = userService.registerUser(registrationDto);
        userResponseDTO dto = new userResponseDTO();
        dto.setId(saved.getId());
        dto.setFullName(saved.getFullName());
        dto.setEmail(saved.getEmail());
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody userLoginRequestDTO loginDto) {
        if (userService.login(loginDto)) {
            return ResponseEntity.ok("Giriş başarılı.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email veya şifre hatalı.");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        userService.logout();
        return ResponseEntity.ok("Oturum kapatıldı.");
    }

    @GetMapping("/me")
    public ResponseEntity<userResponseDTO> getCurrentUser() {
        User user = userService.getCurrentUser();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        userResponseDTO dto = new userResponseDTO();
        dto.setId(user.getId());
        dto.setFullName(user.getFullName());
        dto.setEmail(user.getEmail());
        return ResponseEntity.ok(dto);
    }
}