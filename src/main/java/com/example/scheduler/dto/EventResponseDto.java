package com.example.scheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventResponseDto {

    // password 는 반환하지 않음
    private long id;
    private String task;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public EventResponseDto(Event event) {
       this.id = event.getId();
       this.task = event.getTask();
       this.authorName = event.getAuthorName();
       this.createdAt = event.getCreatedAt();
       this.updatedAt = event.getUpdatedAt();
    }

}
