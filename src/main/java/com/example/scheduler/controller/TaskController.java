package com.example.scheduler.controller;

import com.example.scheduler.dto.TaskRequestDto;
import com.example.scheduler.dto.TaskResponseDto;
import com.example.scheduler.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponseDto> saveTask(@RequestBody TaskRequestDto dto) {
        return ResponseEntity.ok(taskService.saveTask(dto));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseDto>> getTasks(
            @RequestParam(required = false) String writer,
            @RequestParam(required = false) String updatedAt
    ) {
        LocalDateTime updatedDateTime = (updatedAt != null && !updatedAt.isEmpty())
                ? LocalDateTime.parse(updatedAt + "T00:00:00")
                : null;

        return ResponseEntity.ok(taskService.getTasks(writer, updatedDateTime));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id, @RequestParam long password) {
        taskService.deleteTaskById(id, password);
        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
}
