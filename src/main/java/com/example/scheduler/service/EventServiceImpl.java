package com.example.scheduler.service;

import com.example.scheduler.dto.EventRequestDto;
import com.example.scheduler.dto.EventResponseDto;
import com.example.scheduler.entity.Event;
import com.example.scheduler.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        Event savedEvent = eventRepository.saveEvent(event);

        return new EventResponseDto(savedEvent);
    }

    @Override
    public ResponseEntity<EventResponseDto> findAllEvents() {

        return eventRepository.findAllEvents();;
    }

    @Override
    public EventResponseDto findEventById(Long id) {

        Event event = eventRepository.findEventById(id);

        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id: " + id);
        }

        return new EventResponseDto(event);
    }

    @Override
    public EventResponseDto updateEvent(Long id, String task, String authorName) {

        Event event = eventRepository.findEventById(id);

        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id: " + id);
        }

        if (task == null || authorName == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The task and author name are required values.");
        }

        event.update(task, authorName, password);

        return new EventResponseDto(event);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findEventById(id);

        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id: " + id);
        }

        eventRepository.deleteEvent(id);
    }
}
