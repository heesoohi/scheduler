package com.example.scheduler.repository;

import com.example.scheduler.dto.EventResponseDto;
import com.example.scheduler.entity.Event;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateEventRepository implements EventRepository {

    // JDBC 템플릿 사용하기 위해 field 선언 및 생성자
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateEventRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public EventResponseDto saveEvent(Event event) {
        // INSERT Query 직접 작성하지 않아도 됨.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("event").usingGeneratedKeyColumns("id");

        // Map 은 인터페이스이기 때문에, 항상 구현체를 사용하여 초기화 해줘야 함.
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("task", event.getTask());
        parameters.put("authorName", event.getAuthorName());
        parameters.put("updatedAt", event.getUpdatedAt());

        // 저장 후 생성된 key 값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new EventResponseDto(key.longValue(), event.getTask(), event.getAuthorName(), event.getUpdatedAt());
    }

    @Override
    public List<EventResponseDto> findAllEvents() {

        return jdbcTemplate.query("select * from event", eventRowMapper());

    }

    @Override
    public Optional<Event> findEventById(Long id) {
        List<Event> result = jdbcTemplate.query("select * from event where id = ?", eventRowMapperV2(), id);

        return result.stream().findAny();
    }

    @Override
    public Event findEventByIdOrElseThrow(Long id) {
        List<Event> result = jdbcTemplate.query("select * from event where id = ?", eventRowMapperV2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id:" + id));
    }

    @Override
    public int updateEvent(Long id, String task) {
        return jdbcTemplate.update("update event set task = ? where id = ?", task, id);
    }

    @Override
    public int deleteEvent(Long id) {
        return jdbcTemplate.update("delete from event where id = ?", id);
    }

    private RowMapper<EventResponseDto> eventRowMapper() {

        return new RowMapper<EventResponseDto>() {
            @Override
            public EventResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new EventResponseDto(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("authorName")
                );
            }
        };
    }

    private RowMapper<Event> eventRowMapperV2() {
        return new RowMapper<Event>() {
            @Override
            public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Event(
                        rs.getLong("id"),
                        rs.getString("task"),
                        rs.getString("authorName")
                );
            }
        };
    }
}
