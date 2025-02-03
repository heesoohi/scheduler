package com.example.scheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskResponseDto {
    private final Long id;
    private final String content;
    private final String writer;
    private final LocalDateTime updatedAt;

    public TaskResponseDto(Long id, String content, String writer, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.updatedAt = updatedAt;
    }
}
