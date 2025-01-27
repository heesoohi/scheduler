package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class Event {

    private long id;
    private String task;
    private String authorName;
    private long password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
