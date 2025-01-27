package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class Event {

    @Setter
    private long id;
    private String task;
    private String authorName;
    private long password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Event(String task, String authorName, long password){
        this.task = task;
        this.authorName = authorName;
        this.password = password;
    }

    public void update(String task, String authorName, long password){
        this.task = task;
        this.authorName = authorName;
        this.password = password;
    }
}
