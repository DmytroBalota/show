-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.23 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for show
DROP DATABASE IF EXISTS `show`;
CREATE DATABASE IF NOT EXISTS `show` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `show`;


-- Dumping structure for table show.auditoriums
DROP TABLE IF EXISTS `auditoriums`;
CREATE TABLE IF NOT EXISTS `auditoriums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `seatsNumber` int(11) DEFAULT NULL,
  `vipSeats` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

-- Dumping data for table show.auditoriums: ~3 rows (approximately)
/*!40000 ALTER TABLE `auditoriums` DISABLE KEYS */;
INSERT INTO `auditoriums` (`id`, `name`, `seatsNumber`, `vipSeats`) VALUES
	(18, 'Red Room', 50, '48,49,50,45,46,47'),
	(19, 'Blue Room', 40, '38,39,40'),
	(20, 'White Room', 30, '28,29,30');
/*!40000 ALTER TABLE `auditoriums` ENABLE KEYS */;


-- Dumping structure for table show.counters
DROP TABLE IF EXISTS `counters`;
CREATE TABLE IF NOT EXISTS `counters` (
  `name` varchar(50) NOT NULL,
  `number` int(11) NOT NULL DEFAULT '0',
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table show.counters: ~3 rows (approximately)
/*!40000 ALTER TABLE `counters` DISABLE KEYS */;
INSERT INTO `counters` (`name`, `number`) VALUES
	('bookTicket', 4),
	('getEventByName', 45),
	('getTicketPrice', 4);
/*!40000 ALTER TABLE `counters` ENABLE KEYS */;


-- Dumping structure for table show.events
DROP TABLE IF EXISTS `events`;
CREATE TABLE IF NOT EXISTS `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `rating` varchar(50) DEFAULT NULL,
  UNIQUE KEY `name` (`name`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table show.events: ~1 rows (approximately)
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` (`id`, `name`, `price`, `duration`, `rating`) VALUES
	(5, 'Saw', 100, 100, 'HIGH');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;


-- Dumping structure for table show.event_date_location
DROP TABLE IF EXISTS `event_date_location`;
CREATE TABLE IF NOT EXISTS `event_date_location` (
  `event_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `auditoriumName` varchar(50) DEFAULT NULL,
  KEY `FK_event_date_location_events` (`event_id`),
  CONSTRAINT `FK_event_date_location_events` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table show.event_date_location: ~2 rows (approximately)
/*!40000 ALTER TABLE `event_date_location` DISABLE KEYS */;
INSERT INTO `event_date_location` (`event_id`, `date`, `auditoriumName`) VALUES
	(5, '2016-03-31 12:00:00', 'Red Room'),
	(5, '2016-04-01 13:00:00', 'White Room');
/*!40000 ALTER TABLE `event_date_location` ENABLE KEYS */;


-- Dumping structure for table show.tickets
DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0',
  `event_id` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `auditoriumName` varchar(50) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tickets_events` (`event_id`),
  KEY `FK_tickets_users` (`user_id`),
  CONSTRAINT `FK_tickets_events` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tickets_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table show.tickets: ~4 rows (approximately)
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` (`id`, `user_id`, `event_id`, `date`, `auditoriumName`, `seat`, `price`) VALUES
	(1, 2, 5, '2016-03-31 12:00:00', 'Red Room', 1, 120),
	(2, 2, 5, '2016-03-31 12:00:00', 'Red Room', 50, 240),
	(3, 2, 5, '2016-04-01 13:00:00', 'White Room', 29, 240),
	(4, 2, 5, '2016-04-01 13:00:00', 'White Room', 30, 240);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;


-- Dumping structure for table show.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table show.users: ~1 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `firstName`, `lastName`, `email`, `birthday`) VALUES
	(2, 'Dmytro', 'Balota', 'dmytro_balota@epam.com', '1985-01-01 00:00:00');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
