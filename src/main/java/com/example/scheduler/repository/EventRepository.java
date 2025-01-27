package com.example.scheduler.repository;

import com.example.scheduler.dto.EventResponseDto;
import com.example.scheduler.entity.Event;

import java.util.List;

public interface EventRepository {

    Event saveEvent(Event event); // id 없는 상태로 Repository Layer 에 전달됨. EventServiceImpl.saveEvent 에서 id 없는 event 객체로 만들기 때문!

    List<EventResponseDto> findAllEvents();

    Event findEventById(Long id);

    void deleteEvent(Long id);

}
