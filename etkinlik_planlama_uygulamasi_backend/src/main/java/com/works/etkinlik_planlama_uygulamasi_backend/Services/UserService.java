package com.works.etkinlik_planlama_uygulamasi_backend.Services;

import com.works.etkinlik_planlama_uygulamasi_backend.Configs.AppConfig.PasswordEncoderBean;
import com.works.etkinlik_planlama_uygulamasi_backend.dto.userLoginRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.dto.userRegisterRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.Entities.User;
import com.works.etkinlik_planlama_uygulamasi_backend.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession; // Servlet konteynerinden sağlanan proxy bean
    private final PasswordEncoderBean passwordEncoder; // AppConfig içindeki Bean

    public User registerUser(userRegisterRequestDTO registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new RuntimeException("Bu email adresi zaten kullanımda.");
        }

        User user = new User();
        user.setFullName(registrationDto.getFullName());
        user.setEmail(registrationDto.getEmail());
        // Güvenli hale getirilmiş şifre kaydı Bean yardımıyla yapılır
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        User saved = userRepository.save(user);
        httpSession.setAttribute("user", saved);
        return saved;
    }

    public boolean login(userLoginRequestDTO loginDto) {
        Optional<User> userOpt = userRepository.findByEmail(loginDto.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Şifre doğrulama işlemi optimize edilmiş bean üzerinden yürütülür
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                httpSession.setAttribute("user", user);
                return true;
            }
        }
        return false;
    }

    public void logout() {
        httpSession.invalidate();
    }

    public User getCurrentUser() {
        return (User) httpSession.getAttribute("user");
    }
}