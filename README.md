# 스케줄링 애플리케이션 API

이 프로젝트는 Spring Boot를 사용하여 일정(Task)을 관리하는 RESTful API 서버입니다. 사용자는 일정을 생성, 조회, 수정, 삭제할 수 있으며, 비밀번호를 설정하여 일정 보호 기능을 제공합니다.

## 기능

- **일정 생성**: 작성자(writer)와 비밀번호(password)를 입력하여 새로운 일정을 생성합니다.
- **일정 조회**: 전체 일정 조회 및 특정 ID 또는 조건(작성자, 수정일)으로 검색이 가능합니다.
- **일정 수정**: 비밀번호 검증 후 일정의 내용을 변경할 수 있습니다.
- **일정 삭제**: 비밀번호 확인 후 일정을 삭제할 수 있습니다.

## 주요 기술 스택

- **Java 17**
- **Spring Boot 3.x**
- **Spring JDBC (JdbcTemplate)**
- **MySQL**
- **Lombok**

## ERD
![img_1.png](img_1.png)

## API 목록

| 기능 | HTTP Method | URL               | Request                                                                                                                   | Response                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
|------|------------|-------------------|---------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 일정 생성 | `POST` | `/api/tasks`      | <br>{<br>&nbsp;&nbsp;"content": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"password": "long"<br>} | 성공 시: `201 Created`<br>{<br>&nbsp;&nbsp;"id": long, <br>&nbsp;&nbsp;"content": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;"updatedAt": "localDateTime"<br>}                                                                                                                                                                                                                                                                                                                                                                                                               |
| 전체 일정 조회 | `GET` | `/api/tasks`      | 없음                                                                                                                        | 성공 시: `200 OK`<br>[<br>&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;"id": "long", <br>&nbsp;&nbsp;&nbsp;&nbsp;"content": "string", <br>&nbsp;&nbsp;&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;&nbsp;&nbsp;"updatedAt": "localDateTime"<br>&nbsp;&nbsp;},<br>&nbsp;&nbsp;{<br>&nbsp;&nbsp;&nbsp;&nbsp;"id": "long", <br>&nbsp;&nbsp;&nbsp;&nbsp;"content": "String", <br>&nbsp;&nbsp;&nbsp;&nbsp;"authorName": "String", <br>&nbsp;&nbsp;&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;&nbsp;&nbsp;"updatedAt": "localDateTime"<br>&nbsp;&nbsp;}<br>]<br>없을 경우: `[]` |
| 선택 일정 조회 | `GET` | `/api/tasks/{id}` | 없음                                                                                                                        | 성공 시: `200 OK`<br>{<br>&nbsp;&nbsp;"id": "long", <br>&nbsp;&nbsp;"content": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;"updatedAt": "localDateTime"<br>}<br>실패 시: `404 Not Found`                                                                                                                                                                                                                                                                                                                                                                                         |
| 선택 일정 수정 | `PUT` | `/api/tasks/{id}` | <br>{<br>&nbsp;&nbsp;"content": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"password": "long"<br>} | 성공 시: `200 OK`<br>{<br>&nbsp;&nbsp;"id": "long", <br>&nbsp;&nbsp;"content": "string", <br>&nbsp;&nbsp;"authorName": "string", <br>&nbsp;&nbsp;"createdAt": "localDateTime", <br>&nbsp;&nbsp;"updatedAt": "localDateTime"<br>}<br>실패 시: `404 Not Found`, `400 Bad Request`                                                                                                                                                                                                                                                                                                                                                                      |
| 선택 일정 삭제 | `DELETE` | `/api/tasks/{id}` | 없음                                                                                                                        | 성공 시: `200 OK`<br>실패 시: `404 Not Found`                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
