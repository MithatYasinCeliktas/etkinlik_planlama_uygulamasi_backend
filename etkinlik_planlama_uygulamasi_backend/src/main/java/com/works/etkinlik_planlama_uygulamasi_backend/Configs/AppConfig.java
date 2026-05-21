package com.works.etkinlik_planlama_uygulamasi_backend.Configs;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoderBean passwordEncoder() {
        return new PasswordEncoderBean();
    }

    public static class PasswordEncoderBean {
        public String encode(String rawPassword) {
            return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
        }
        public boolean matches(String rawPassword, String encodedPassword) {
            try {
                return BCrypt.checkpw(rawPassword, encodedPassword);
            } catch (Exception e) {
                return false;
            }
        }
    }
}