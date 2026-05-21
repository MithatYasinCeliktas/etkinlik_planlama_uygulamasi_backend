package com.works.etkinlik_planlama_uygulamasi_backend.Services;

import com.works.etkinlik_planlama_uygulamasi_backend.dto.eventCreateRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.dto.eventResponseRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.dto.eventUpdateRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.Entities.Event;
import com.works.etkinlik_planlama_uygulamasi_backend.util.eventStatus;
import com.works.etkinlik_planlama_uygulamasi_backend.Entities.User;
import com.works.etkinlik_planlama_uygulamasi_backend.mapper.EventMapper;
import com.works.etkinlik_planlama_uygulamasi_backend.Repository.EventRepository;
import com.works.etkinlik_planlama_uygulamasi_backend.Repository.ParticipationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;
    private final UserService userService;
    private final EventMapper eventMapper;

    public eventResponseRequestDTO createEvent(eventCreateRequestDTO createDto) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("Etkinlik oluşturmak için giriş yapmalısınız.");
        }

        Event event = new Event();
        event.setTitle(createDto.getTitle());
        event.setDate(createDto.getDate()); // java.util.Date
        event.setTime(createDto.getTime()); // java.sql.Time
        event.setLocation(createDto.getLocation());
        event.setDescription(createDto.getDescription());
        event.setCategory(createDto.getCategory());
        event.setStatus(eventStatus.YAYINDA);
        event.setOwner(currentUser);

        return eventMapper.toResponseDto(eventRepository.save(event));
    }

    public Page<eventResponseRequestDTO> getAllPublishedEvents(Pageable pageable) {
        // Dönüşüm mantığı döngüsel olmaktan çıkarılıp Bean'e devredildi, performans artırıldı.
        return eventRepository.findByStatus(eventStatus.YAYINDA, pageable)
                .map(eventMapper::toResponseDto);
    }

    public eventResponseRequestDTO updateEvent(eventUpdateRequestDTO updateDto) {
        Event event = eventRepository.findById(updateDto.getId())
                .orElseThrow(() -> new RuntimeException("Etkinlik bulunamadı."));

        validateOwnership(event);

        event.setTitle(updateDto.getTitle());
        event.setDate(updateDto.getDate());
        event.setTime(updateDto.getTime());
        event.setLocation(updateDto.getLocation());
        event.setDescription(updateDto.getDescription());
        event.setCategory(updateDto.getCategory());

        if (updateDto.getStatus() != null) {
            event.setStatus(updateDto.getStatus());
        }

        return eventMapper.toResponseDto(eventRepository.save(event));
    }

    @Transactional
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etkinlik bulunamadı."));
        validateOwnership(event);
        participationRepository.deleteByEventId(id);
        eventRepository.delete(event);
    }

    public eventResponseRequestDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etkinlik bulunamadı."));
        return eventMapper.toResponseDto(event);
    }

    public List<eventResponseRequestDTO> getMyEvents() {
        User currentUser = userService.getCurrentUser();
        return eventRepository.findByOwnerId(currentUser.getId()).stream()
                .map(eventMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    private void validateOwnership(Event event) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null || !event.getOwner().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Bu işlem için yetkiniz yok.");
        }
    }
}