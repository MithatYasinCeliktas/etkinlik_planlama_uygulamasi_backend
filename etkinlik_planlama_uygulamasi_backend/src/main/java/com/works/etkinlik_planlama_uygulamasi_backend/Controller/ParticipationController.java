package com.works.etkinlik_planlama_uygulamasi_backend.Controller;

import com.works.etkinlik_planlama_uygulamasi_backend.Services.ParticipationService;
import com.works.etkinlik_planlama_uygulamasi_backend.dto.eventResponseRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/participations")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @GetMapping("/my")
    public ResponseEntity<List<eventResponseRequestDTO>> getMyJoinedEvents() {
        return ResponseEntity.ok(participationService.getMyJoinedEvents());
    }

    @GetMapping("/{eventId}/joined")
    public ResponseEntity<Map<String, Boolean>> isJoined(@PathVariable Long eventId) {
        return ResponseEntity.ok(Map.of("joined", participationService.isUserJoined(eventId)));
    }

    @PostMapping("/{eventId}/join")
    public ResponseEntity<String> joinEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(participationService.joinEvent(eventId));
    }

    @DeleteMapping("/{eventId}/leave")
    public ResponseEntity<String> leaveEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(participationService.leaveEvent(eventId));
    }

    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<String>> getParticipants(@PathVariable Long eventId) {
        return ResponseEntity.ok(participationService.getParticipants(eventId));
    }
}
