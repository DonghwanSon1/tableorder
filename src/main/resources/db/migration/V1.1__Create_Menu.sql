CREATE TABLE IF NOT EXISTS menu (
    `sn` BIGINT AUTO_INCREMENT PRIMARY KEY,                   -- 메뉴 고유 식별자 (자동 증가)
    `category_sn` BIGINT NOT NULL,                              -- 카테고리 (FK)
    `name` VARCHAR(255) NOT NULL,                              -- 메뉴명
    `order` SMALLINT NOT NULL,                                 -- 순서
    `image_url` VARCHAR(255),                                   -- 이미지 URL
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,          -- 생성일
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정일
    CONSTRAINT fk_category_sn FOREIGN KEY (`category_sn`) REFERENCES category(`sn`) ON DELETE CASCADE ON UPDATE CASCADE  -- FK 제약조건
);
