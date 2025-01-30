package com.example.scheduler.repository;

import com.example.scheduler.dto.EventResponseDto;
import com.example.scheduler.entity.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {

    EventResponseDto saveEvent(Event event); // id 없는 상태로 Repository Layer 에 전달됨. EventServiceImpl.saveEvent 에서 id 없는 event 객체로 만들기 때문!

    List<EventResponseDto> findAllEvents();

    Optional<Event> findEventById(Long id);

    Event findEventByIdOrElseThrow(Long id);

    int updateEvent(Long id, String task);

    int deleteEvent(Long id);


}
