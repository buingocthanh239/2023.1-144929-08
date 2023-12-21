-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.2.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for timekeeper
DROP DATABASE IF EXISTS `timekeeper`;
CREATE DATABASE IF NOT EXISTS `timekeeper` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `timekeeper`;

-- Dumping structure for table timekeeper.room
DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
                                      `room_id` int NOT NULL AUTO_INCREMENT,
                                      `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                                      PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table timekeeper.room: ~0 rows (approximately)

-- Dumping structure for table timekeeper.timekeeping_action
DROP TABLE IF EXISTS `timekeeping_action`;
CREATE TABLE IF NOT EXISTS `timekeeping_action` (
                                                    `action_id` int NOT NULL AUTO_INCREMENT,
                                                    `user_id` int NOT NULL,
                                                    `action_time` timestamp NOT NULL,
                                                    `type` tinyint(1) NOT NULL COMMENT '0: checkin, 1:checkout',
                                                    PRIMARY KEY (`action_id`),
                                                    KEY `FK__user` (`user_id`),
                                                    CONSTRAINT `FK__user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table timekeeper.timekeeping_action: ~6 rows (approximately)
INSERT INTO `timekeeping_action` (`action_id`, `user_id`, `action_time`, `type`) VALUES
                                                                                     (3, 1, '2023-01-20 00:00:00', 0),
                                                                                     (4, 1, '2023-12-19 02:00:00', 0),
                                                                                     (5, 1, '2023-12-19 09:30:00', 0),
                                                                                     (6, 1, '2023-12-20 07:36:00', 0),
                                                                                     (7, 1, '2023-12-20 07:44:29', 0),
                                                                                     (8, 1, '2023-12-20 07:52:18', 0);

-- Dumping structure for table timekeeper.timekeeping_request
DROP TABLE IF EXISTS `timekeeping_request`;
CREATE TABLE IF NOT EXISTS `timekeeping_request` (
                                                     `request_id` int NOT NULL AUTO_INCREMENT,
                                                     `user_id` int DEFAULT NULL,
                                                     `request_time` timestamp NULL DEFAULT NULL,
                                                     `content` text,
                                                     PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table timekeeper.timekeeping_request: ~3 rows (approximately)
INSERT INTO `timekeeping_request` (`request_id`, `user_id`, `request_time`, `content`) VALUES
                                                                                           (1, 1, '2023-12-20 15:17:02', '2123wqda'),
                                                                                           (2, 2, '2023-12-20 15:17:19', '321d2'),
                                                                                           (3, 33, '2023-12-20 15:17:35', 'qwdq');

-- Dumping structure for table timekeeper.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
                                      `user_id` int NOT NULL AUTO_INCREMENT,
                                      `username` varchar(50) NOT NULL,
                                      `fullname` varchar(255) NOT NULL,
                                      `password` varchar(50) NOT NULL,
                                      `role` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0: staff, 1: worker, 2: room_manager, 3:admin',
                                      `room_id` int NOT NULL DEFAULT '0',
                                      PRIMARY KEY (`user_id`) USING BTREE,
                                      UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table timekeeper.user: ~3 rows (approximately)
INSERT INTO `user` (`user_id`, `username`, `fullname`, `password`, `role`, `room_id`) VALUES
                                                                                          (1, 'aaaa', 'Nguyễn Mạnh Cường', 'aaaa', 3, 0),
                                                                                          (2, '2', '111', '111', 0, 0),
                                                                                          (3, 'aaaaa', 'cascadasd', 'aaaaa', 0, 0);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
