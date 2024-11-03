CREATE TABLE IF NOT EXISTS member (
    sn BIGINT AUTO_INCREMENT PRIMARY KEY,               -- 고유 식별자 (자동 증가)
    id VARCHAR(255) NOT NULL,                           -- 사용자 ID (고유)
    password VARCHAR(255) NOT NULL,                     -- 비밀번호
    name VARCHAR(255) NOT NULL,                         -- 이름
    birth_date DATE,                                    -- 생년월일
    role VARCHAR(20),                                   -- 역할
    email VARCHAR(255) NOT NULL,                        -- 이메일
    UNIQUE KEY unique_id (id),                          -- id 컬럼에 대한 유니크 인덱스
    INDEX idx_role (role)                               -- role 컬럼에 대한 인덱스 추가
);