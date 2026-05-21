package com.works.etkinlik_planlama_uygulamasi_backend.dto;

import com.works.etkinlik_planlama_uygulamasi_backend.util.eventStatus;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class eventResponseRequestDTO {
    private Long id;
    private String title;
    private Date date;
    private Time time;
    private String location;
    private String description;
    private String category;
    private eventStatus status;
    private String ownerFullName; // Only send the name, not the whole User object
}