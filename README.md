# Hands-on-Unit-Testing-with-Modern-Frameworks
TEAM2 NJS1803

## Team Members
Trần Phước Thịnh  - SE171833
Võ Trường Thành Phát - SE171823
Nguyễn Đức Quang - SE160057
Phùng Phước Thiên - SE173156
Nguyễn Huy Hoàng - SE170263

# Lab2 - Spring Boot REST API với CRUD, Unit Test và Test Coverage

## 1. Giới thiệu
Đây là một dự án Spring Boot REST API cung cấp các tính năng CRUD (Create, Read, Update, Delete). Dự án được xây dựng với:
- **Spring Boot** để phát triển API REST.
- **Spring Data JPA** để làm việc với cơ sở dữ liệu.
- **JUnit & Mockito** để viết unit test.
- **JaCoCo** để đo lường mức độ test coverage, đảm bảo tối thiểu **80%**.

## 2. Công nghệ sử dụng
- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Data JPA** (Làm việc với cơ sở dữ liệu)
- **MySQL & H2 Database**
- **Lombok & MapStruct** (Giảm boilerplate code)
- **Thymeleaf** (Giao diện nếu cần)
- **JUnit 5 & Mockito** (Testing)
- **JaCoCo** (Test coverage)

## 3. Cài đặt & Chạy chương trình
### 3.1. Cấu hình cơ sở dữ liệu (MySQL)
Chỉnh sửa file `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/lab2_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3.2. Chạy ứng dụng
```bash
mvn spring-boot:run
```
Ứng dụng sẽ chạy trên `http://localhost:8080`.

## 4. API Endpoints
| HTTP Method | Endpoint | Chức năng             |
|------------|--------|-----------------------|
| GET        | /users | Lấy danh sách user    |
| GET        | /users/{id} | Lấy user theo ID      |
| POST       | /users | Thêm item mới         |
| PUT        | /users/{id} | Cập nhật user theo id |
| DELETE     | /users/{id} | Xóa user theo id      |

## 5. Testing & Coverage
### 5.1. Chạy Unit Test
```bash
mvn test
```
### 5.2. Kiểm tra Test Coverage
```bash
mvn verify
```
Kết quả báo cáo coverage nằm trong `target/site/jacoco/index.html`. Đảm bảo **coverage >= 80%**.

## 6. Kết luận
Dự án này giúp hiểu rõ về cách xây dựng REST API với Spring Boot, kiểm thử bằng JUnit & Mockito, đồng thời đảm bảo chất lượng mã với JaCoCo.

