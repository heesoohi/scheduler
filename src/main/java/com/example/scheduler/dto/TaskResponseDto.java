package com.example.scheduler.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TaskResponseDto { // 비밀번호는 반환되면 안 됨.
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
