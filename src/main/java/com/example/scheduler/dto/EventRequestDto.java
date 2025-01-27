package com.example.scheduler.dto;

import lombok.Getter;

@Getter

public class EventRequestDto {
    // 요청받을 데이터가 task, authorName, password 라는 뜻
    private String task;
    private String authorName;
    private long password;
}
