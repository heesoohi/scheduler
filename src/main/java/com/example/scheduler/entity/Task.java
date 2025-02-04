package com.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Task {
    @Setter
    private long id; // database auto increment
    private String content;
    private String writer;
    private long password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 신규 일정 생성자
    public Task(String content, String writer, long password) {
        this.content = content;
        this.writer = writer;
        this.password = password;
        this.createdAt = LocalDateTime.now(); // 최초 생성일
        this.updatedAt = this.createdAt; // 수정일 = 생성일
    }

    public Task(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Task(long id, String content, String writer, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
