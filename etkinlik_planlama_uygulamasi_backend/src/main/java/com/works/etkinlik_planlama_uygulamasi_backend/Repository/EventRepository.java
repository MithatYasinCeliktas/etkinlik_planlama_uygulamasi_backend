package com.works.etkinlik_planlama_uygulamasi_backend.Repository;

import com.works.etkinlik_planlama_uygulamasi_backend.Entities.Event;
import com.works.etkinlik_planlama_uygulamasi_backend.util.eventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByStatus(eventStatus status, Pageable pageable);
    List<Event> findByOwnerId(Long userId);
}