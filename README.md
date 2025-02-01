# 일정 관리 앱

## API 목록

| 기능 | HTTP Method | URL | Request                                                                                                                | Response                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
|------|------------|-----|------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 일정 생성 | `POST` | `/api/events` | <br>{<br>&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"password": "long"<br>} | 성공 시: `201 Created`<br>{<br>&nbsp;&nbsp;"id": long, <br>&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;"updatedAt": "localDateTime"<br>}                                                                                                                                                                                                                                                                                                                                                                                                            |
| 전체 일정 조회 | `GET` | `/api/events` | 없음                                                                                                                     | 성공 시: `200 OK`<br>[<br>&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;"id": "long", <br>&nbsp;&nbsp;&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;&nbsp;&nbsp;"updatedAt": "localDateTime"<br>&nbsp;&nbsp;},<br>&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;"id": "long", <br>&nbsp;&nbsp;&nbsp;&nbsp;"task": "String", <br>&nbsp;&nbsp;&nbsp;&nbsp;"authorName": "String", <br>&nbsp;&nbsp;&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;&nbsp;&nbsp;"updatedAt": "localDateTime"<br>&nbsp;&nbsp;}<br>]<br>없을 경우: `[]` |
| 선택 일정 조회 | `GET` | `/api/events/{id}` | 없음                                                                                                                     | 성공 시: `200 OK`<br>{<br>&nbsp;&nbsp;"id": "long", <br>&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;"updatedAt": "localDateTime"<br>}<br>실패 시: `404 Not Found`                                                                                                                                                                                                                                                                                                                                                                                      |
| 선택 일정 수정 | `PUT` | `/api/events/{id}` | <br>{<br>&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"password": "long"<br>} | 성공 시: `200 OK`<br>{<br>&nbsp;&nbsp;"id": "long", <br>&nbsp;&nbsp;"task": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;"updatedAt": "localDateTime"<br>}<br>실패 시: `404 Not Found`, `400 Bad Request`                                                                                                                                                                                                                                                                                                                                                       |
| 선택 일정 삭제 | `DELETE` | `/api/events/{id}` | 없음                                                                                                                     | 성공 시: `200 OK`<br>실패 시: `404 Not Found`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  |
