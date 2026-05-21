package com.works.etkinlik_planlama_uygulamasi_backend.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "participations")
@Data
@NoArgsConstructor
public class participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    private LocalDateTime joinedAt;

    public participation(User user, Event event, LocalDateTime joinedAt) {
        this.user = user;
        this.event = event;
        this.joinedAt = LocalDateTime.now();
    }

    public participation(User currentUser, Event event) {
     this.user = currentUser;
     this.event = event;
     this.joinedAt = LocalDateTime.now();
    }
}