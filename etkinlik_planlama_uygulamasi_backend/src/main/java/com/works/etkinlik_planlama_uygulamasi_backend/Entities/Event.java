package com.works.etkinlik_planlama_uygulamasi_backend.Entities;

import com.works.etkinlik_planlama_uygulamasi_backend.util.eventStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.sql.Time;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Etkinlik adı boş bırakılamaz")
    private String title;

    @NotNull(message = "Tarih boş bırakılamaz")
    @Future(message = "Etkinlik tarihi bugünden ileri bir tarih olmalıdır") // Requires date > current date
    @Temporal(TemporalType.DATE) // Maps java.util.Date to SQL DATE
    private Date date;

    @NotNull(message = "Saat boş bırakılamaz")
    @Temporal(TemporalType.TIME) // Maps java.sql.Time to SQL TIME
    private Time time;

    @NotBlank(message = "Yer bilgisi boş bırakılamaz")
    private String location;

    @Column(length = 1000)
    private String description;

    @NotBlank(message = "Kategori boş bırakılamaz")
    private String category;

    @Enumerated(EnumType.STRING)
    private eventStatus status = eventStatus.YAYINDA;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
}