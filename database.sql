CREATE DATABASE IF NOT EXISTS KTJ2EE;
USE KTJ2EE;

CREATE TABLE IF NOT EXISTS department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image LONGTEXT,
    specialty VARCHAR(255),
    department_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES department(id)
);

CREATE TABLE IF NOT EXISTS patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS `role` (
    role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS patient_role (
    patient_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (patient_id, role_id),
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (role_id) REFERENCES `role`(role_id)
);

CREATE TABLE IF NOT EXISTS appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    patient_id BIGINT,
    doctor_id BIGINT,
    appointment_date DATETIME NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patient(id),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id)
);

-- Thêm quyền (Role)
INSERT IGNORE INTO `role` (role_id, name) VALUES (1, 'ADMIN'), (2, 'PATIENT');

-- Thêm bệnh nhân và Quản trị viên mẫu
INSERT IGNORE INTO patient (id, username, password, email) VALUES 
(1, 'admin', 'admin123', 'admin@clinic.com'),
(2, 'benhnhan', '123456', 'benhnhan@gmail.com');

INSERT IGNORE INTO patient_role (patient_id, role_id) VALUES 
(1, 1), 
(2, 2);

-- Thêm dữ liệu mẫu để hiển thị trên web
INSERT IGNORE INTO department (name) VALUES ('Tim mạch'), ('Thần kinh'), ('Nhi khoa'), ('Chỉnh hình'), ('Da liễu');

INSERT IGNORE INTO doctor (name, image, specialty, department_id) VALUES 
('BS. Nguyễn Văn Anh', 'https://randomuser.me/api/portraits/men/32.jpg', 'Bác sĩ phẫu thuật', 1),
('BS. Phạm Văn An', 'https://randomuser.me/api/portraits/men/45.jpg', 'Chuyên gia thần kinh', 2),
('BS. Đặng Thị Cúc', 'https://randomuser.me/api/portraits/women/44.jpg', 'Bác sĩ nhi khoa', 3),
('BS. Đăng Thanh', 'https://randomuser.me/api/portraits/men/22.jpg', 'Chỉnh hình', 4),
('BS. Minh Quang', 'https://randomuser.me/api/portraits/men/67.jpg', 'Bác sĩ da liễu', 5),
('BS. Nhật Nam', 'https://randomuser.me/api/portraits/men/82.jpg', 'Bác sĩ tim', 1),
('BS. Ngọc Đạt', 'https://randomuser.me/api/portraits/women/65.jpg', 'Tuổi dậy thì', 3);
