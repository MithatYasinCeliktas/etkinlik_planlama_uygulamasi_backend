package com.works.etkinlik_planlama_uygulamasi_backend.Repository;

import com.works.etkinlik_planlama_uygulamasi_backend.Entities.participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<participation, Long> {
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    Optional<participation> findByUserIdAndEventId(Long userId, Long eventId);
    List<participation> findByEventId(Long eventId);
    List<participation> findByUserId(Long userId);
    void deleteByEventId(Long eventId);
}