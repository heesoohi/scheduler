package com.example.scheduler.controller;

import com.example.scheduler.service.EventService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController   // JSON 형태로 데이터 통신  // @Controller + @ResponseBody
@RequestMapping("/events") // prefix
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
}
