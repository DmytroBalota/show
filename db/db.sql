-- --------------------------------------------------------
-- Сервер:                       127.0.0.1
-- Версія сервера:               5.7.11-log - MySQL Community Server (GPL)
-- ОС сервера:                   Win64
-- HeidiSQL Версія:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for show
CREATE DATABASE IF NOT EXISTS `show` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `show`;


-- Dumping structure for таблиця show.auditoriums
CREATE TABLE IF NOT EXISTS `auditoriums` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `seatsNumber` int(11) DEFAULT NULL,
  `vipSeats` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table show.auditoriums: ~2 rows (приблизно)
/*!40000 ALTER TABLE `auditoriums` DISABLE KEYS */;
INSERT INTO `auditoriums` (`id`, `name`, `seatsNumber`, `vipSeats`) VALUES
	(18, 'Red Room', 50, '48,49,50,45,46,47'),
	(19, 'Blue Room', 40, '38,39,40'),
	(20, 'White Room', 30, '28,29,30');
/*!40000 ALTER TABLE `auditoriums` ENABLE KEYS */;


-- Dumping structure for таблиця show.counters
CREATE TABLE IF NOT EXISTS `counters` (
  `name` varchar(50) NOT NULL,
  `number` int(11) NOT NULL DEFAULT '0',
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table show.counters: ~1 rows (приблизно)
/*!40000 ALTER TABLE `counters` DISABLE KEYS */;
INSERT INTO `counters` (`name`, `number`) VALUES
	('getEventByName', 30);
/*!40000 ALTER TABLE `counters` ENABLE KEYS */;


-- Dumping structure for таблиця show.events
CREATE TABLE IF NOT EXISTS `events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `rating` varchar(50) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Dumping data for table show.events: ~2 rows (приблизно)
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` (`id`, `name`, `price`, `duration`, `rating`) VALUES
	(5, 'Saw', 100, 100, 'HIGH');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;


-- Dumping structure for таблиця show.event_date_location
CREATE TABLE IF NOT EXISTS `event_date_location` (
  `event_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `auditoriumName` varchar(50) DEFAULT NULL,
  KEY `FK_event_date_location_events` (`event_id`),
  CONSTRAINT `FK_event_date_location_events` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table show.event_date_location: ~2 rows (приблизно)
/*!40000 ALTER TABLE `event_date_location` DISABLE KEYS */;
INSERT INTO `event_date_location` (`event_id`, `date`, `auditoriumName`) VALUES
	(5, '2016-04-01 13:00:00', 'Red Room'),
	(5, '2016-04-01 13:00:00', 'White Room');
/*!40000 ALTER TABLE `event_date_location` ENABLE KEYS */;


-- Dumping structure for таблиця show.tickets
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table show.tickets: ~0 rows (приблизно)
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;


-- Dumping structure for таблиця show.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `birthday` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table show.users: ~0 rows (приблизно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `firstName`, `lastName`, `email`, `birthday`) VALUES
	(2, 'Dmytro', 'Balota', 'dmytro_balota@epam.com', '1985-01-01 00:00:00');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
