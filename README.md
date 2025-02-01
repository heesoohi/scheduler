# 일정 관리 앱

## API 목록

| 기능 | HTTP Method | URL | Request | Response |
|------|------------|-----|---------|----------|
| 일정 생성 | `POST` | `/api/events` | <br>{<br>&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"password": 123456<br>} | 성공 시: `201 Created`<br>{<br>&nbsp;&nbsp;"id": 1, <br>&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"createdAt": "2025-02-01T12:00:00", <br>&nbsp;&nbsp;"updatedAt": "2025-02-01T12:00:00"<br>} |
| 전체 일정 조회 | `GET` | `/api/events` | 없음 | 성공 시: `200 OK`<br>[<br>&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;"id": 1, <br>&nbsp;&nbsp;&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;&nbsp;&nbsp;"createdAt": "2025-02-01T12:00:00", <br>&nbsp;&nbsp;&nbsp;&nbsp;"updatedAt": "2025-02-01T12:00:00"<br>&nbsp;&nbsp;},<br>&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;"id": 2, <br>&nbsp;&nbsp;&nbsp;&nbsp;"task": "another task", <br>&nbsp;&nbsp;&nbsp;&nbsp;"authorName": "another author", <br>&nbsp;&nbsp;&nbsp;&nbsp;"createdAt": "2025-02-01T13:00:00", <br>&nbsp;&nbsp;&nbsp;&nbsp;"updatedAt": "2025-02-01T13:00:00"<br>&nbsp;&nbsp;}<br>]<br>없을 경우: `[]` |
| 선택 일정 조회 | `GET` | `/api/events/{id}` | 없음 | 성공 시: `200 OK`<br>{<br>&nbsp;&nbsp;"id": 1, <br>&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"createdAt": "2025-02-01T12:00:00", <br>&nbsp;&nbsp;"updatedAt": "2025-02-01T12:00:00"<br>}<br>실패 시: `404 Not Found` |
| 선택 일정 수정 | `PUT` | `/api/events/{id}` | <br>{<br>&nbsp;&nbsp;"task": "updated task", <br>&nbsp;&nbsp;"authorName": "updated author", <br>&nbsp;&nbsp;"password": 123456<br>} | 성공 시: `200 OK`<br>{<br>&nbsp;&nbsp;"id": 1, <br>&nbsp;&nbsp;"task": "updated task", <br>&nbsp;&nbsp;"authorName": "updated author", <br>&nbsp;&nbsp;"createdAt": "2025-02-01T12:00:00", <br>&nbsp;&nbsp;"updatedAt": "2025-02-01T14:00:00"<br>}<br>실패 시: `404 Not Found`, `400 Bad Request` |
| 선택 일정 삭제 | `DELETE` | `/api/events/{id}` | 없음 | 성공 시: `200 OK`<br>실패 시: `404 Not Found` |
