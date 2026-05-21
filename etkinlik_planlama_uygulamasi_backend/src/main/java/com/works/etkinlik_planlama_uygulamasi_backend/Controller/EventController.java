package com.works.etkinlik_planlama_uygulamasi_backend.Controller;

import com.works.etkinlik_planlama_uygulamasi_backend.dto.eventCreateRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.dto.eventResponseRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.dto.eventUpdateRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.Services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<eventResponseRequestDTO> createEvent(@Valid @RequestBody eventCreateRequestDTO createDto) {
        return new ResponseEntity<>(eventService.createEvent(createDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<eventResponseRequestDTO>> getAllEvents(Pageable pageable) {
        return ResponseEntity.ok(eventService.getAllPublishedEvents(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<eventResponseRequestDTO> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @PutMapping
    public ResponseEntity<eventResponseRequestDTO> updateEvent(@Valid @RequestBody eventUpdateRequestDTO updateDto) {
        return ResponseEntity.ok(eventService.updateEvent(updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<eventResponseRequestDTO>> getMyEvents() {
        return ResponseEntity.ok(eventService.getMyEvents());
    }
}