package com.works.etkinlik_planlama_uygulamasi_backend.Repository;

import com.works.etkinlik_planlama_uygulamasi_backend.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository // Spring bu arayüzü tarar ve bir Veritabanı Yönetim Bean'i üretir.
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}