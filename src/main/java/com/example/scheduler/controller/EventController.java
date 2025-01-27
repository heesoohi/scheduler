package com.example.scheduler.controller;

import com.example.scheduler.dto.EventRequestDto;
import com.example.scheduler.dto.EventResponseDto;
import com.example.scheduler.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController   // JSON 형태로 데이터 통신  // @Controller + @ResponseBody
@RequestMapping("/events") // Prefix
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // 일정 생성 API
    @PostMapping
    public ResponseEntity<EventResponseDto> createMemo(@RequestBody EventRequestDto dto) {
        // ServiceLayer 호출 및 응답
        return new ResponseEntity<>(eventService.saveEvent(dto), HttpStatus.CREATED);
    }

    // 전체 일정 조회 API
    @GetMapping
    public ResponseEntity<EventResponseDto> findAllEvents() {
        return eventService.findAllEvents();
    }

    // 선택 일정 조회 API
    @GetMapping ("/{id}")
    public ResponseEntity<EventResponseDto> findEventById(@PathVariable Long id) {

        return new ResponseEntity<>(eventService.findEventById(id), HttpStatus.OK);
    }

    // 선택 일정 수정 API
    @PutMapping ("{/id}")
    public ResponseEntity<EventResponseDto> updateEvent(
            @PathVariable Long id,
            @RequestBody EventRequestDto dto
    ) {
        return new ResponseEntity<>(eventService.updateEvent(id, dto.getTask(), dto.getAuthorName(), HttpStatus.OK));

    }

    // 선택 일정 삭제 API
    @DeleteMapping("{/id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
