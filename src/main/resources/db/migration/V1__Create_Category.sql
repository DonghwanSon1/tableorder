CREATE TABLE  IF NOT EXISTS category (
    `sn` BIGINT AUTO_INCREMENT PRIMARY KEY,                   -- 카테고리 고유 식별자 (자동 증가)
    `name` VARCHAR(255) NOT NULL,                              -- 카테고리명
    `order` SMALLINT NOT NULL,                               -- 순서 (예약어이므로 백틱으로 감싸기)
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,         -- 생성일
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 수정일
);