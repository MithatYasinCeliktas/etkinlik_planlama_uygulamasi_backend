package com.works.etkinlik_planlama_uygulamasi_backend.Services;

import com.works.etkinlik_planlama_uygulamasi_backend.dto.eventResponseRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.Entities.*;
import com.works.etkinlik_planlama_uygulamasi_backend.Repository.EventRepository;
import com.works.etkinlik_planlama_uygulamasi_backend.Repository.ParticipationRepository;
import com.works.etkinlik_planlama_uygulamasi_backend.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final EventRepository eventRepository;
    private final UserService userService;
    private final EventMapper eventMapper;

    public String joinEvent(Long eventId) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("Etkinliğe katılmak için giriş yapmalısınız.");
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Etkinlik bulunamadı."));

        if (participationRepository.existsByUserIdAndEventId(currentUser.getId(), eventId)) {
            throw new RuntimeException("Bu etkinliğe zaten katıldınız.");
        }

        participation participation = new participation(currentUser, event);
        participationRepository.save(participation);
        return "Etkinliğe başarıyla katıldınız.";
    }

    @Transactional
    public String leaveEvent(Long eventId) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("Bu işlem için giriş yapmalısınız.");
        }

        participation record = participationRepository
                .findByUserIdAndEventId(currentUser.getId(), eventId)
                .orElseThrow(() -> new RuntimeException("Bu etkinliğe kayıtlı değilsiniz."));

        participationRepository.delete(record);
        return "Etkinlik kaydınız silindi.";
    }

    public boolean isUserJoined(Long eventId) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return false;
        }
        return participationRepository.existsByUserIdAndEventId(currentUser.getId(), eventId);
    }

    public List<String> getParticipants(Long eventId) {
        return participationRepository.findByEventId(eventId).stream()
                .map(p -> p.getUser().getFullName())
                .collect(Collectors.toList());
    }

    public List<eventResponseRequestDTO> getMyJoinedEvents() {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new RuntimeException("Bu işlem için giriş yapmalısınız.");
        }

        return participationRepository.findByUserId(currentUser.getId()).stream()
                .map(p -> eventMapper.toResponseDto(p.getEvent()))
                .collect(Collectors.toList());
    }
}
