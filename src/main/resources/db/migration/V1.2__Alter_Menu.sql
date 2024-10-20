ALTER TABLE menu
ADD COLUMN `description` TEXT AFTER `name`,
ADD COLUMN `price` INT NOT NULL DEFAULT 0 AFTER `description`;