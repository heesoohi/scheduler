package com.example.scheduler.dto;

import com.example.scheduler.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class EventResponseDto {

    // password 는 반환하지 않음
    private long id;
    private String task;
    private String authorName;
    private LocalDateTime updatedAt;

    public EventResponseDto(Event event) {
       this.id = event.getId();
       this.task = event.getTask();
       this.authorName = event.getAuthorName();
       this.updatedAt = event.getUpdatedAt();
    }

    public EventResponseDto(long id, String task, String authorName) {
        this.id = id;
        this.task = task;
        this.authorName = authorName;
    }
}
