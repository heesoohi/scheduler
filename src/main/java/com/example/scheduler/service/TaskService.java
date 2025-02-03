package com.example.scheduler.service;

import com.example.scheduler.dto.TaskRequestDto;
import com.example.scheduler.dto.TaskResponseDto;
import com.example.scheduler.entity.Task;
import com.example.scheduler.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional
    public TaskResponseDto saveTask(TaskRequestDto dto) {
        Task task = new Task(dto.getContent(), dto.getWriter(), dto.getPassword());
        Task savedTask = taskRepository.saveTask(task);
        return new TaskResponseDto(
                savedTask.getId(),
                savedTask.getContent(),
                savedTask.getWriter(),
                savedTask.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> getTasks() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskResponseDto> dtoList = new ArrayList<>();

        for (Task task : taskList) {
            TaskResponseDto taskResponseDto = new TaskResponseDto(
                    task.getId(),
                    task.getContent(),
                    task.getWriter(),
                    task.getUpdatedAt()
            );
            dtoList.add(taskResponseDto);
        }

        return dtoList;
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDto> getTasks(String writer, LocalDateTime updatedAt) {
        List<Task> taskList = taskRepository.findAllByCondition(writer, updatedAt);
        List<TaskResponseDto> dtoList = new ArrayList<>();

        for (Task task : taskList) {
            dtoList.add(new TaskResponseDto(
                    task.getId(),
                    task.getContent(),
                    task.getWriter(),
                    task.getUpdatedAt()
            ));
        }

        return dtoList;
    }

    @Transactional(readOnly = true)
    public TaskResponseDto getTask(Long id) {
        Task task = taskRepository.findTaskById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 할 일이 존재하지 않습니다.")
        );

        return new TaskResponseDto(
                task.getId(),
                task.getContent(),
                task.getWriter(),
                task.getUpdatedAt()
        );
    }

    @Transactional
    public TaskResponseDto updateTask(Long id, TaskRequestDto dto) {
        // 1. 해당 id의 일정 조회
        Task task = taskRepository.findTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 2. 비밀번호 검증
        if (!Objects.equals(task.getPassword(), dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. 일정 내용 및 작성자명 업데이트 (수정된 시간 반영)
        LocalDateTime now = LocalDateTime.now();
        Task updatedTask = taskRepository.updateContent(id, dto.getContent(), dto.getWriter(), now);

        // 4. 수정된 일정 정보를 반환
        return new TaskResponseDto(
                updatedTask.getId(),
                updatedTask.getContent(),
                updatedTask.getWriter(),
                updatedTask.getUpdatedAt()
        );
    }

    @Transactional
    public void deleteTaskById(Long id, long password) {
        Task task = taskRepository.findTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 비밀번호 검증
        if (task.getPassword() != password) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        taskRepository.deleteTaskById(id);
    }
}
