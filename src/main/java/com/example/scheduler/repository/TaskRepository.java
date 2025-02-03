package com.example.scheduler.repository;

import com.example.scheduler.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task saveTask(Task task);

    Optional<Task> findTaskById(Long id);

    List<Task> findAll();

    List<Task> findAllByCondition(String writer, LocalDateTime updatedAt);

    Task updateContent(Long id, String content, String writer, LocalDateTime now);

    void deleteTaskById(Long id);
}
