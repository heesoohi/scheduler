package com.example.scheduler.service;

import com.example.scheduler.dto.EventRequestDto;
import com.example.scheduler.dto.EventResponseDto;
import com.example.scheduler.entity.Event;
import com.example.scheduler.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    public EventResponseDto saveEvent(EventRequestDto dto) {

        // 요청받은 데이터로 일정 객체 생성 - id 값은 없음.
        Event event = new Event(dto.getTask(), dto.getAuthorName(), dto.getPassword());

        // DB 저장
        return eventRepository.saveEvent(event);
    }

    @Override
    public ResponseEntity<EventResponseDto> findAllEvents() {

        return (ResponseEntity<EventResponseDto>)eventRepository.findAllEvents();
    }

    @Override
    public EventResponseDto findEventById(Long id) {

        Event event = eventRepository.findEventByIdOrElseThrow(id);

        return new EventResponseDto(event);
    }

    @Transactional
    @Override
    public EventResponseDto updateEvent(Long id, String task, String authorName) {

        if (task == null || authorName == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The task and author name are required values.");
        }

        int updatedRow = eventRepository.updateEvent(id, task);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id: " + id);
        }

        Event event = eventRepository.findEventByIdOrElseThrow(id);

        return new EventResponseDto(event);
    }

    @Override
    public void deleteEvent(Long id) {

        int deletedRow = eventRepository.deleteEvent(id);

        if (deletedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id: " + id);
        }
    }
}
