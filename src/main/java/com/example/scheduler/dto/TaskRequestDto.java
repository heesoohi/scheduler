package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class TaskRequestDto { // id, createdAt, updatedAt 들은 database 에서 자동으로 생성
    private String content;
    private String writer;
    private long password;
}
