-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema prediction_app
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema prediction_app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `prediction_app` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema prediction_app2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema prediction_app2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `prediction_app2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `prediction_app` ;

-- -----------------------------------------------------
-- Table `prediction_app`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`event` (
  `id` INT NOT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `startDate` TIMESTAMP NULL DEFAULT NULL,
  `endDate` TIMESTAMP NULL DEFAULT NULL,
  `category` TEXT NULL DEFAULT NULL,
  `createdAt` TIMESTAMP NULL DEFAULT NULL,
  `updatedAt` TIMESTAMP NULL DEFAULT NULL,
  `deletedAt` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`event_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`event_category` (
  `id` INT NOT NULL,
  `eventId` INT NULL DEFAULT NULL,
  `category` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `eventId` (`eventId` ASC) VISIBLE,
  CONSTRAINT `event_category_ibfk_1`
    FOREIGN KEY (`eventId`)
    REFERENCES `prediction_app`.`event` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`category_options`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`category_options` (
  `id` INT NOT NULL,
  `categoryId` INT NULL DEFAULT NULL,
  `optionName` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `categoryId` (`categoryId` ASC) VISIBLE,
  CONSTRAINT `category_options_ibfk_1`
    FOREIGN KEY (`categoryId`)
    REFERENCES `prediction_app`.`event_category` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`choiceeventresult`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`choiceeventresult` (
  `id` INT NOT NULL,
  `eventId` INT NULL DEFAULT NULL,
  `categoryId` INT NULL DEFAULT NULL,
  `optionId` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `eventId` (`eventId` ASC) VISIBLE,
  INDEX `categoryId` (`categoryId` ASC) VISIBLE,
  INDEX `optionId` (`optionId` ASC) VISIBLE,
  CONSTRAINT `choiceeventresult_ibfk_1`
    FOREIGN KEY (`eventId`)
    REFERENCES `prediction_app`.`event` (`id`),
  CONSTRAINT `choiceeventresult_ibfk_2`
    FOREIGN KEY (`categoryId`)
    REFERENCES `prediction_app`.`event_category` (`id`),
  CONSTRAINT `choiceeventresult_ibfk_3`
    FOREIGN KEY (`optionId`)
    REFERENCES `prediction_app`.`category_options` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`user` (
  `id` INT NOT NULL,
  `username` TEXT NULL DEFAULT NULL,
  `email` TEXT NULL DEFAULT NULL,
  `password` TEXT NULL DEFAULT NULL,
  `createdAt` TIMESTAMP NULL DEFAULT NULL,
  `updatedAt` TIMESTAMP NULL DEFAULT NULL,
  `deletedAt` TIMESTAMP NULL DEFAULT NULL,
  `isActive` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`predictiongroup`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`predictiongroup` (
  `id` INT NOT NULL,
  `eventId` INT NULL DEFAULT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `isPublic` TINYINT(1) NULL DEFAULT NULL,
  `ownerId` INT NULL DEFAULT NULL,
  `createdAt` TIMESTAMP NULL DEFAULT NULL,
  `updatedAt` TIMESTAMP NULL DEFAULT NULL,
  `deletedAt` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `eventId` (`eventId` ASC) VISIBLE,
  INDEX `ownerId` (`ownerId` ASC) VISIBLE,
  CONSTRAINT `predictiongroup_ibfk_1`
    FOREIGN KEY (`eventId`)
    REFERENCES `prediction_app`.`event` (`id`),
  CONSTRAINT `predictiongroup_ibfk_2`
    FOREIGN KEY (`ownerId`)
    REFERENCES `prediction_app`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`prediction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`prediction` (
  `id` INT NOT NULL,
  `userId` INT NULL DEFAULT NULL,
  `predictiongroupId` INT NULL DEFAULT NULL,
  `categoryId` INT NULL DEFAULT NULL,
  `optionId` INT NULL DEFAULT NULL,
  `prediction` TEXT NULL DEFAULT NULL,
  `createdAt` TIMESTAMP NULL DEFAULT NULL,
  `updatedAt` TIMESTAMP NULL DEFAULT NULL,
  `deletedAt` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `userId` (`userId` ASC) VISIBLE,
  INDEX `predictiongroupId` (`predictiongroupId` ASC) VISIBLE,
  INDEX `categoryId` (`categoryId` ASC) VISIBLE,
  INDEX `optionId` (`optionId` ASC) VISIBLE,
  CONSTRAINT `prediction_ibfk_1`
    FOREIGN KEY (`userId`)
    REFERENCES `prediction_app`.`user` (`id`),
  CONSTRAINT `prediction_ibfk_2`
    FOREIGN KEY (`predictiongroupId`)
    REFERENCES `prediction_app`.`predictiongroup` (`id`),
  CONSTRAINT `prediction_ibfk_3`
    FOREIGN KEY (`categoryId`)
    REFERENCES `prediction_app`.`event_category` (`id`),
  CONSTRAINT `prediction_ibfk_4`
    FOREIGN KEY (`optionId`)
    REFERENCES `prediction_app`.`category_options` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`choiceprediction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`choiceprediction` (
  `id` INT NOT NULL,
  `predictionId` INT NULL DEFAULT NULL,
  `chosenOptionId` INT NULL DEFAULT NULL,
  `points` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `predictionId` (`predictionId` ASC) VISIBLE,
  INDEX `chosenOptionId` (`chosenOptionId` ASC) VISIBLE,
  CONSTRAINT `choiceprediction_ibfk_1`
    FOREIGN KEY (`predictionId`)
    REFERENCES `prediction_app`.`prediction` (`id`),
  CONSTRAINT `choiceprediction_ibfk_2`
    FOREIGN KEY (`chosenOptionId`)
    REFERENCES `prediction_app`.`category_options` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`predictionresult`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`predictionresult` (
  `id` INT NOT NULL,
  `predictionId` INT NULL DEFAULT NULL,
  `points` INT NULL DEFAULT NULL,
  `predictionType` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `predictionId` (`predictionId` ASC) VISIBLE,
  CONSTRAINT `predictionresult_ibfk_1`
    FOREIGN KEY (`predictionId`)
    REFERENCES `prediction_app`.`prediction` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`sportsprediction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`sportsprediction` (
  `id` INT NOT NULL,
  `predictionId` INT NULL DEFAULT NULL,
  `homeScore` INT NULL DEFAULT NULL,
  `awayScore` INT NULL DEFAULT NULL,
  `points` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `predictionId` (`predictionId` ASC) VISIBLE,
  CONSTRAINT `sportsprediction_ibfk_1`
    FOREIGN KEY (`predictionId`)
    REFERENCES `prediction_app`.`prediction` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`team` (
  `id` INT NOT NULL,
  `name` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`sportsresult`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`sportsresult` (
  `id` INT NOT NULL,
  `eventId` INT NULL DEFAULT NULL,
  `homeTeamId` INT NULL DEFAULT NULL,
  `awayTeamId` INT NULL DEFAULT NULL,
  `homeScore` INT NULL DEFAULT NULL,
  `awayScore` INT NULL DEFAULT NULL,
  `resultDate` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `eventId` (`eventId` ASC) VISIBLE,
  INDEX `homeTeamId` (`homeTeamId` ASC) VISIBLE,
  INDEX `awayTeamId` (`awayTeamId` ASC) VISIBLE,
  CONSTRAINT `sportsresult_ibfk_1`
    FOREIGN KEY (`eventId`)
    REFERENCES `prediction_app`.`event` (`id`),
  CONSTRAINT `sportsresult_ibfk_2`
    FOREIGN KEY (`homeTeamId`)
    REFERENCES `prediction_app`.`team` (`id`),
  CONSTRAINT `sportsresult_ibfk_3`
    FOREIGN KEY (`awayTeamId`)
    REFERENCES `prediction_app`.`team` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app`.`usergroupscore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app`.`usergroupscore` (
  `id` INT NOT NULL,
  `userId` INT NULL DEFAULT NULL,
  `groupId` INT NULL DEFAULT NULL,
  `totalPoints` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `userId` (`userId` ASC) VISIBLE,
  INDEX `groupId` (`groupId` ASC) VISIBLE,
  CONSTRAINT `usergroupscore_ibfk_1`
    FOREIGN KEY (`userId`)
    REFERENCES `prediction_app`.`user` (`id`),
  CONSTRAINT `usergroupscore_ibfk_2`
    FOREIGN KEY (`groupId`)
    REFERENCES `prediction_app`.`predictiongroup` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `prediction_app2` ;

-- -----------------------------------------------------
-- Table `prediction_app2`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`event` (
  `id` INT NOT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `start_date` TIMESTAMP NULL DEFAULT NULL,
  `end_date` TIMESTAMP NULL DEFAULT NULL,
  `category` TEXT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `deleted_at` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`event_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`event_category` (
  `id` INT NOT NULL,
  `event_id` INT NULL DEFAULT NULL,
  `category` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `event_id` (`event_id` ASC) VISIBLE,
  CONSTRAINT `event_category_ibfk_1`
    FOREIGN KEY (`event_id`)
    REFERENCES `prediction_app2`.`event` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`category_options`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`category_options` (
  `id` INT NOT NULL,
  `category_id` INT NULL DEFAULT NULL,
  `option_name` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `category_id` (`category_id` ASC) VISIBLE,
  CONSTRAINT `category_options_ibfk_1`
    FOREIGN KEY (`category_id`)
    REFERENCES `prediction_app2`.`event_category` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`choice_event_result`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`choice_event_result` (
  `id` INT NOT NULL,
  `event_id` INT NULL DEFAULT NULL,
  `category_id` INT NULL DEFAULT NULL,
  `option_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `event_id` (`event_id` ASC) VISIBLE,
  INDEX `category_id` (`category_id` ASC) VISIBLE,
  INDEX `option_id` (`option_id` ASC) VISIBLE,
  CONSTRAINT `choice_event_result_ibfk_1`
    FOREIGN KEY (`event_id`)
    REFERENCES `prediction_app2`.`event` (`id`),
  CONSTRAINT `choice_event_result_ibfk_2`
    FOREIGN KEY (`category_id`)
    REFERENCES `prediction_app2`.`event_category` (`id`),
  CONSTRAINT `choice_event_result_ibfk_3`
    FOREIGN KEY (`option_id`)
    REFERENCES `prediction_app2`.`category_options` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`user` (
  `id` INT NOT NULL,
  `username` TEXT NULL DEFAULT NULL,
  `email` TEXT NULL DEFAULT NULL,
  `password` TEXT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `deleted_at` TIMESTAMP NULL DEFAULT NULL,
  `is_active` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`prediction_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`prediction_group` (
  `id` INT NOT NULL,
  `event_id` INT NULL DEFAULT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `is_public` TINYINT(1) NULL DEFAULT NULL,
  `owner_id` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `deleted_at` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `event_id` (`event_id` ASC) VISIBLE,
  INDEX `owner_id` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `prediction_group_ibfk_1`
    FOREIGN KEY (`event_id`)
    REFERENCES `prediction_app2`.`event` (`id`),
  CONSTRAINT `prediction_group_ibfk_2`
    FOREIGN KEY (`owner_id`)
    REFERENCES `prediction_app2`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`prediction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`prediction` (
  `id` INT NOT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `prediction_group_id` INT NULL DEFAULT NULL,
  `category_id` INT NULL DEFAULT NULL,
  `option_id` INT NULL DEFAULT NULL,
  `prediction` TEXT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT NULL,
  `updated_at` TIMESTAMP NULL DEFAULT NULL,
  `deleted_at` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `prediction_group_id` (`prediction_group_id` ASC) VISIBLE,
  INDEX `category_id` (`category_id` ASC) VISIBLE,
  INDEX `option_id` (`option_id` ASC) VISIBLE,
  CONSTRAINT `prediction_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `prediction_app2`.`user` (`id`),
  CONSTRAINT `prediction_ibfk_2`
    FOREIGN KEY (`prediction_group_id`)
    REFERENCES `prediction_app2`.`prediction_group` (`id`),
  CONSTRAINT `prediction_ibfk_3`
    FOREIGN KEY (`category_id`)
    REFERENCES `prediction_app2`.`event_category` (`id`),
  CONSTRAINT `prediction_ibfk_4`
    FOREIGN KEY (`option_id`)
    REFERENCES `prediction_app2`.`category_options` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`choice_prediction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`choice_prediction` (
  `id` INT NOT NULL,
  `prediction_id` INT NULL DEFAULT NULL,
  `chosen_option_id` INT NULL DEFAULT NULL,
  `points` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `prediction_id` (`prediction_id` ASC) VISIBLE,
  INDEX `chosen_option_id` (`chosen_option_id` ASC) VISIBLE,
  CONSTRAINT `choice_prediction_ibfk_1`
    FOREIGN KEY (`prediction_id`)
    REFERENCES `prediction_app2`.`prediction` (`id`),
  CONSTRAINT `choice_prediction_ibfk_2`
    FOREIGN KEY (`chosen_option_id`)
    REFERENCES `prediction_app2`.`category_options` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`prediction_result`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`prediction_result` (
  `id` INT NOT NULL,
  `prediction_id` INT NULL DEFAULT NULL,
  `points` INT NULL DEFAULT NULL,
  `prediction_type` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `prediction_id` (`prediction_id` ASC) VISIBLE,
  CONSTRAINT `prediction_result_ibfk_1`
    FOREIGN KEY (`prediction_id`)
    REFERENCES `prediction_app2`.`prediction` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`team` (
  `id` INT NOT NULL,
  `name` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`sports_prediction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`sports_prediction` (
  `id` INT NOT NULL,
  `prediction_id` INT NULL DEFAULT NULL,
  `home_score` INT NULL DEFAULT NULL,
  `away_score` INT NULL DEFAULT NULL,
  `home_team_id` INT NULL,
  `away_team_id` INT NULL,
  `points` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `prediction_id` (`prediction_id` ASC) VISIBLE,
  INDEX `fk_sports_prediction_team1_idx` (`home_team_id` ASC) VISIBLE,
  INDEX `fk_sports_prediction_team2_idx` (`away_team_id` ASC) VISIBLE,
  CONSTRAINT `sports_prediction_ibfk_1`
    FOREIGN KEY (`prediction_id`)
    REFERENCES `prediction_app2`.`prediction` (`id`),
  CONSTRAINT `fk_sports_prediction_team1`
    FOREIGN KEY (`home_team_id`)
    REFERENCES `prediction_app2`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sports_prediction_team2`
    FOREIGN KEY (`away_team_id`)
    REFERENCES `prediction_app2`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`sports_result`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`sports_result` (
  `id` INT NOT NULL,
  `event_id` INT NULL DEFAULT NULL,
  `home_team_id` INT NULL DEFAULT NULL,
  `away_team_id` INT NULL DEFAULT NULL,
  `home_score` INT NULL DEFAULT NULL,
  `away_score` INT NULL DEFAULT NULL,
  `result_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `event_id` (`event_id` ASC) VISIBLE,
  INDEX `home_team_id` (`home_team_id` ASC) VISIBLE,
  INDEX `away_team_id` (`away_team_id` ASC) VISIBLE,
  CONSTRAINT `sports_result_ibfk_1`
    FOREIGN KEY (`event_id`)
    REFERENCES `prediction_app2`.`event` (`id`),
  CONSTRAINT `sports_result_ibfk_2`
    FOREIGN KEY (`home_team_id`)
    REFERENCES `prediction_app2`.`team` (`id`),
  CONSTRAINT `sports_result_ibfk_3`
    FOREIGN KEY (`away_team_id`)
    REFERENCES `prediction_app2`.`team` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `prediction_app2`.`user_group_score`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `prediction_app2`.`user_group_score` (
  `id` INT NOT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `group_id` INT NULL DEFAULT NULL,
  `total_points` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `group_id` (`group_id` ASC) VISIBLE,
  CONSTRAINT `user_group_score_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `prediction_app2`.`user` (`id`),
  CONSTRAINT `user_group_score_ibfk_2`
    FOREIGN KEY (`group_id`)
    REFERENCES `prediction_app2`.`prediction_group` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
