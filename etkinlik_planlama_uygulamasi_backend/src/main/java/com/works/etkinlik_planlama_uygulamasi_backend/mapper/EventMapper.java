package com.works.etkinlik_planlama_uygulamasi_backend.mapper;

import com.works.etkinlik_planlama_uygulamasi_backend.dto.eventResponseRequestDTO;
import com.works.etkinlik_planlama_uygulamasi_backend.Entities.Event;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component // Bu sınıf artık DI (Dependency Injection) ile her yerde çağrılabilecek bir Bean'dir.
@RequiredArgsConstructor
public class EventMapper {

    private final ModelMapper modelMapper; // AppConfig içindeki Bean buraya enjekte edilir.

    public eventResponseRequestDTO toResponseDto(Event event) {
        eventResponseRequestDTO dto = modelMapper.map(event, eventResponseRequestDTO.class);
        if (event.getOwner() != null) {
            dto.setOwnerFullName(event.getOwner().getFullName());
        }
        return dto;
    }
}