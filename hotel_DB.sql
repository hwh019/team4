# DB 삭제(hotel)
DROP DATABASE IF EXISTS hotel;
# DB 생성(hotel) 
CREATE DATABASE IF NOT EXISTS hotel;

CREATE TABLE IF NOT EXISTS  `hotel`.`member` (
  `mb_no` INT NOT NULL AUTO_INCREMENT,
  `mb_id` VARCHAR(20) NOT NULL,
  `mb_name` VARCHAR(20) NOT NULL,
  `mb_password` VARCHAR(255) NOT NULL,
  `mb_email` VARCHAR(255) NOT NULL,
  `mb_is_admin` CHAR(1) NOT NULL DEFAULT 'N',
  UNIQUE INDEX `mb_id_UNIQUE` (`mb_id` ASC) VISIBLE,
  PRIMARY KEY (`mb_no`));

insert into hotel.member set mb_id = 'admin', mb_name = '최고 관리자', mb_password = 'admin1234', mb_email = 'admin@admin.com', mb_is_admin = 'Y';

CREATE TABLE IF NOT EXISTS `hotel`.`room` (
  `ro_id` INT NOT NULL AUTO_INCREMENT,
  `ro_num` INT(11) NOT NULL,
  `ro_price` INT(11) NOT NULL DEFAULT 0,
  `ro_max_person` INT(11) NOT NULL DEFAULT 1,
  `ro_name` VARCHAR(20) NOT NULL,
  UNIQUE INDEX `ro_num_UNIQUE` (`ro_num` ASC) VISIBLE,
  PRIMARY KEY (`ro_id`));

ALTER TABLE `hotel`.`room` 
ADD COLUMN `ro_use` CHAR(1) NOT NULL DEFAULT 'Y' AFTER `ro_name`;
  
  CREATE TABLE IF NOT EXISTS `hotel`.`reservation` (
  `rv_id` BIGINT NOT NULL,
  `rv_date` DATE NOT NULL,
  `rv_room_num` INT NOT NULL,
  `rv_start_date` DATE NOT NULL,
  `rv_end_date` DATE NOT NULL,
  `rv_status` VARCHAR(50) NOT NULL,
  `rv_total_price` INT(11) NOT NULL DEFAULT 0,
  `rv_stay_person` INT(11) NOT NULL DEFAULT 1,
  `mb_id` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`rv_id`),
  INDEX `reservation_member_idx` (`mb_id` ASC) VISIBLE,
  INDEX `reservation_room_idx` (`rv_room_num` ASC) VISIBLE,
  CONSTRAINT `reservation_member`
    FOREIGN KEY (`mb_id`)
    REFERENCES `hotel`.`member` (`mb_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `reservation_room`
    FOREIGN KEY (`rv_room_num`)
    REFERENCES `hotel`.`room` (`ro_num`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
