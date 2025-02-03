package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class Event {

    private long id; // database auto increment
    private String task;
    private String authorName;
    private long password;
    private LocalDateTime UpdatedAt;

    public Event(String task, String authorName, long password){
        this.task = task;
        this.authorName = authorName;
        this.password = password;
    }

    public Event(long id, String task, String authorName) {
        this.id = id;
        this.task = task;
        this.authorName = authorName;
    }
}
