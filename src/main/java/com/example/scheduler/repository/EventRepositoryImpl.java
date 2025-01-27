package com.example.scheduler.repository;

import com.example.scheduler.dto.EventResponseDto;
import com.example.scheduler.entity.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository {
    @Override
    public Event saveEvent(Event event) {

        // 일정 식별자 자동 생성
        Long eventId =
        event.setId(eventID);

        return event;
    }

    @Override
    public List<EventResponseDto> findAllEvents() {

        // init list
        // List 는 인터페이스 이기 때문에, 구현체인 ArrayList 로 생성
        List<EventResponseDto> allEvents = new ArrayList<>();

        return allEvents;
    }

    @Override
    public Event findEventById(Long id) {
        return eventList.get(id);
    }

    @Override
    public void deleteEvent(Long id) {
        eventList.remove(id);
    }
}
