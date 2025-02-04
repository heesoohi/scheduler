package com.example.scheduler.repository;

import com.example.scheduler.entity.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public TaskRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Task saveTask(Task task) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO task (content, writer, password, created_at, updated_at) VALUES (?, ?, ?, NOW(), NOW())",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, task.getContent());
            ps.setString(2, task.getWriter());
            ps.setLong(3, task.getPassword());
            return ps;
        }, keyHolder);

        task.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return task;
    }

    @Override
    public Optional<Task> findTaskById(Long id) {
        List<Task> tasks = jdbcTemplate.query(
                "SELECT * FROM task WHERE id = ?",
                (rs, rowNum) -> new Task(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getString("writer"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                ),
                id
        );
        return tasks.stream().findAny();
    }

    @Override
    public List<Task> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM task ORDER BY updated_at DESC",  // 수정일 기준 내림차순 정렬 추가
                (rs, rowNum) -> new Task(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getString("writer"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                )
        );
    }

    @Override
    public List<Task> findAllByCondition(String writer, LocalDateTime updatedAt) {
        String sql = "SELECT * FROM task WHERE 1=1";
        List<Object> params = new ArrayList<>();

        if (writer != null && !writer.isEmpty()) {
            sql += " AND writer = ?";
            params.add(writer);
        }

        if (updatedAt != null) {
            sql += " AND DATE(updated_at) = ?";
            params.add(updatedAt.toLocalDate());
        }

        sql += " ORDER BY updated_at DESC";

        return jdbcTemplate.query(
                sql,
                params.toArray(),
                (rs, rowNum) -> new Task(
                        rs.getLong("id"),
                        rs.getString("content"),
                        rs.getString("writer"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                )
        );
    }

    @Override
    public Task updateContent(Long id, String content, String writer, LocalDateTime updatedAt) {
        jdbcTemplate.update(
                "UPDATE task SET content = ?, writer = ?, updated_at = ? WHERE id = ?",
                content, writer, updatedAt, id
        );

        return findTaskById(id).orElseThrow(
                () -> new IllegalArgumentException("업데이트된 일정을 찾을 수 없습니다.")
        );
    }

    @Override
    public void deleteTaskById(Long id) {

        int affectedRows = jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
        if (affectedRows == 0) {
            throw new IllegalArgumentException("삭제할 일정이 존재하지 않습니다.");
        }
    }
}


