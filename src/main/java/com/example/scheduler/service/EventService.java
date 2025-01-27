package com.example.scheduler.service;

import com.example.scheduler.dto.EventRequestDto;
import com.example.scheduler.dto.EventResponseDto;
import org.springframework.http.ResponseEntity;

public interface EventService {
    EventResponseDto saveEvent(EventRequestDto dto);

    ResponseEntity<EventResponseDto> findAllEvents();

    EventResponseDto findEventById(Long id);

    EventResponseDto updateEvent(Long id, String task, String authorName);

    void deleteEvent(Long id);
}
