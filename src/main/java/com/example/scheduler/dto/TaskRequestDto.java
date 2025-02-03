package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class TaskRequestDto {
    private String content;
    private String writer;
    private long password;
}
