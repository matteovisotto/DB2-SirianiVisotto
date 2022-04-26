-- phpMyAdmin SQL Dump
-- version 5.2.0-rc1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 26, 2022 at 04:54 PM
-- Server version: 10.5.15-MariaDB-0ubuntu0.21.10.1
-- PHP Version: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `telco`
--

-- --------------------------------------------------------

--
-- Table structure for table `activation`
--

DROP TABLE IF EXISTS `activation`;
CREATE TABLE IF NOT EXISTS `activation` (
  `user_id` int(11) NOT NULL,
  `package_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `package_id` (`package_id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
CREATE TABLE IF NOT EXISTS `administrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `alerts`
--

DROP TABLE IF EXISTS `alerts`;
CREATE TABLE IF NOT EXISTS `alerts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `avarage_purchase_optional_package`
--

DROP TABLE IF EXISTS `avarage_purchase_optional_package`;
CREATE TABLE IF NOT EXISTS `avarage_purchase_optional_package` (
  `package_id` int(11) NOT NULL,
  `package_name` varchar(255) NOT NULL,
  `avg_optional` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`package_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `failed_payment`
--

DROP TABLE IF EXISTS `failed_payment`;
CREATE TABLE IF NOT EXISTS `failed_payment` (
  `user_id` int(11) NOT NULL,
  `last_failure` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `n_failures` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `failed_payment`
--
DROP TRIGGER IF EXISTS `raise_new_alert`;
DELIMITER $$
CREATE TRIGGER `raise_new_alert` AFTER UPDATE ON `failed_payment` FOR EACH ROW BEGIN
DECLARE l_username varchar(255);
DECLARE l_email varchar(255);
DECLARE l_amount double;
SET l_amount = (SELECT SUM(price) FROM orders AS o WHERE o.user_id = new.user_id AND o.id NOT IN (SELECT p.order_id FROM payment_history AS p WHERE p.user_id = new.user_id AND p.payment_status = 1));
SET l_email = (SELECT email FROM user WHERE id = new.user_id);
SET l_username = (SELECT username FROM user WHERE id = new.user_id);
IF new.n_failures = 3 THEN
INSERT INTO alerts (user_id, email, username, amount, date_time) VALUES (new.user_id, l_email, l_username, l_amount, new.last_failure);
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `insolvent_user`
--

DROP TABLE IF EXISTS `insolvent_user`;
CREATE TABLE IF NOT EXISTS `insolvent_user` (
  `id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL,
  `surname` varchar(225) NOT NULL,
  `username` varchar(225) NOT NULL,
  `email` varchar(225) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Stand-in structure for view `number_optional_package`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `number_optional_package`;
CREATE TABLE IF NOT EXISTS `number_optional_package` (
`package_id` int(11)
,`number` bigint(21)
);

-- --------------------------------------------------------

--
-- Table structure for table `optional_product`
--

DROP TABLE IF EXISTS `optional_product`;
CREATE TABLE IF NOT EXISTS `optional_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `optional_product`
--
DROP TRIGGER IF EXISTS `create_purchase_optional`;
DELIMITER $$
CREATE TRIGGER `create_purchase_optional` AFTER INSERT ON `optional_product` FOR EACH ROW BEGIN
INSERT INTO total_purchase_optional (tot_purchase, optional_id, optional_name) VALUES (0, new.id, new.name);
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `delete_purchase_optional`;
DELIMITER $$
CREATE TRIGGER `delete_purchase_optional` AFTER DELETE ON `optional_product` FOR EACH ROW BEGIN
DELETE FROM total_purchase_optional WHERE optional_id = old.id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `optional_product_in_package`
--

DROP TABLE IF EXISTS `optional_product_in_package`;
CREATE TABLE IF NOT EXISTS `optional_product_in_package` (
  `package_id` int(11) NOT NULL,
  `optional_product_id` int(11) NOT NULL,
  PRIMARY KEY (`package_id`,`optional_product_id`),
  KEY `optional_product_id` (`optional_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `optional_product_order`
--

DROP TABLE IF EXISTS `optional_product_order`;
CREATE TABLE IF NOT EXISTS `optional_product_order` (
  `order_id` int(11) NOT NULL,
  `optional_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`optional_id`),
  KEY `optional_id` (`optional_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `optional_product_order`
--
DROP TRIGGER IF EXISTS `optional_in_package_for_order`;
DELIMITER $$
CREATE TRIGGER `optional_in_package_for_order` BEFORE INSERT ON `optional_product_order` FOR EACH ROW BEGIN
DECLARE val INT DEFAULT 0;
SET val = (SELECT COUNT(*) as verify FROM optional_product_in_package AS o JOIN orders AS t ON t.package_id = o.package_id WHERE t.id=new.order_id AND o.optional_product_id = new.optional_id);
IF val = 0 THEN
	SIGNAL SQLSTATE '23000'
	SET MESSAGE_TEXT = 'Optional product not in package';
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_order_price_optional`;
DELIMITER $$
CREATE TRIGGER `update_order_price_optional` AFTER INSERT ON `optional_product_order` FOR EACH ROW BEGIN
DECLARE p DOUBLE;
DECLARE v_period INT;
SET p = (SELECT price FROM optional_product WHERE id = new.optional_id);
SET v_period = (SELECT validity_period FROM orders WHERE id=new.order_id);
UPDATE orders SET price = price + (p*v_period) WHERE id = new.order_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `package_id` int(11) DEFAULT NULL,
  `validity_period` int(11) NOT NULL,
  `order_status` int(11) NOT NULL DEFAULT 0,
  `start_date` date NOT NULL,
  `price` double NOT NULL DEFAULT 0,
  `createdAt` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `orders_ibfk_2` (`package_id`),
  KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `orders`
--
DROP TRIGGER IF EXISTS `check_validity_period_validity`;
DELIMITER $$
CREATE TRIGGER `check_validity_period_validity` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
DECLARE val INT;
SET val = (SELECT COUNT(*) FROM package_price AS p WHERE p.package_id = new.package_id AND p.validity_period = new.validity_period);
IF val = 0 THEN
SIGNAL SQLSTATE '23000' SET MESSAGE_TEXT = 'Invalid validity period';
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `retrieve_suspended_orders`;
DELIMITER $$
CREATE TRIGGER `retrieve_suspended_orders` AFTER UPDATE ON `orders` FOR EACH ROW BEGIN
declare number int;
IF new.order_status = 2 THEN
SET number = (SELECT count(*) FROM suspended_orders WHERE order_id=new.id AND user_id=new.user_id);
IF number = 0 THEN
INSERT INTO suspended_orders (order_id, user_id) VALUES (new.id, new.user_id);
END IF;
ELSEIF new.order_status = 1 or new.order_status = 0 THEN
DELETE FROM suspended_orders WHERE order_id = new.id;
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `set_base_price`;
DELIMITER $$
CREATE TRIGGER `set_base_price` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
DECLARE amount double;
SET amount = (SELECT price FROM package_price WHERE package_id = new.package_id AND validity_period = new.validity_period);
SET new.price = amount*new.validity_period;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_purchase_optional`;
DELIMITER $$
CREATE TRIGGER `update_purchase_optional` BEFORE UPDATE ON `orders` FOR EACH ROW BEGIN
DECLARE oId int;
DECLARE done INT DEFAULT FALSE;
DECLARE cur CURSOR FOR select optional_id from optional_product_order where order_id = new.id;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
IF (old.order_status = 0 or old.order_status = 2) and new.order_status = 1 THEN
OPEN cur;
        ins_loop: LOOP
            FETCH cur INTO oId;
            IF done THEN
                LEAVE ins_loop;
            END IF;
            update total_purchase_optional set tot_purchase = tot_purchase + 1 where optional_id = oId;
        END LOOP;
    CLOSE cur;
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_purchase_package`;
DELIMITER $$
CREATE TRIGGER `update_purchase_package` BEFORE UPDATE ON `orders` FOR EACH ROW BEGIN
IF (old.order_status = 0 or old.order_status=2) and new.order_status = 1 THEN
UPDATE total_purchase_package SET tot_purchase = tot_purchase + 1 WHERE package_id = new.package_id;
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_purchase_package_avg`;
DELIMITER $$
CREATE TRIGGER `update_purchase_package_avg` AFTER UPDATE ON `orders` FOR EACH ROW BEGIN
IF new.order_status = 1 THEN
UPDATE avarage_purchase_optional_package SET avg_optional = IFNULL((SELECT SUM(number) FROM number_optional_package WHERE package_id = new.package_id),0)/(SELECT count(*) FROM orders where package_id=new.package_id) WHERE package_id=new.package_id;
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_purchase_package_optional`;
DELIMITER $$
CREATE TRIGGER `update_purchase_package_optional` BEFORE UPDATE ON `orders` FOR EACH ROW BEGIN
DECLARE counter int;
SET counter = IFNULL((SELECT count(*) FROM optional_product_order WHERE order_id = new.id GROUP BY order_id), 0);
IF (old.order_status = 0 or old.order_status=2) and new.order_status = 1 THEN 
IF counter = 0 THEN
UPDATE total_purchase_package_optional set tot_purchase = tot_purchase + 1 where package_id = new.package_id AND has_optional_product = 0;
ELSE
UPDATE total_purchase_package_optional set tot_purchase = tot_purchase + 1 where package_id = new.package_id AND has_optional_product = 1;
END IF;
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_purchase_package_validity`;
DELIMITER $$
CREATE TRIGGER `update_purchase_package_validity` BEFORE UPDATE ON `orders` FOR EACH ROW BEGIN
IF (old.order_status = 0 or old.order_status=2) and new.order_status = 1 THEN
UPDATE total_purchase_package_validity SET tot_purchase = tot_purchase + 1 WHERE package_id = new.package_id AND validity_period = new.validity_period;
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_tot_value_package`;
DELIMITER $$
CREATE TRIGGER `update_tot_value_package` BEFORE UPDATE ON `orders` FOR EACH ROW BEGIN
DECLARE optional_price double;
DECLARE validity_price double;
SET optional_price = (SELECT COALESCE(SUM(o.price),0) FROM optional_product_order AS x JOIN optional_product AS o ON o.id = x.optional_id WHERE x.order_id = new.id);
SET validity_price = (optional_price * old.validity_period);
IF (old.order_status = 0 or old.order_status=2) and new.order_status = 1 THEN 
UPDATE tot_value_optional_no_optional SET tot_value = tot_value + new.price where package_id = new.package_id AND with_optional = 1;
UPDATE tot_value_optional_no_optional SET tot_value = tot_value + (new.price - validity_price) where package_id = new.package_id AND with_optional = 0;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `package_price`
--

DROP TABLE IF EXISTS `package_price`;
CREATE TABLE IF NOT EXISTS `package_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `package_id` int(11) NOT NULL,
  `validity_period` int(11) NOT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `package_id` (`package_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `payment_history`
--

DROP TABLE IF EXISTS `payment_history`;
CREATE TABLE IF NOT EXISTS `payment_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp(),
  `payment_status` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `payment_history_ibfk_1` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `payment_history`
--
DROP TRIGGER IF EXISTS `create_activation_record`;
DELIMITER $$
CREATE TRIGGER `create_activation_record` AFTER INSERT ON `payment_history` FOR EACH ROW BEGIN
DECLARE l_package_id INT;
DECLARE l_start_date date;
DECLARE l_validity_period INT;
SET l_package_id = (SELECT package_id FROM orders WHERE id = new.order_id);
SET l_start_date = (SELECT start_date FROM orders WHERE id = new.order_id);
SET l_validity_period = (SELECT validity_period FROM orders WHERE id = new.order_id);
IF new.payment_status = 1 THEN
INSERT INTO activation (user_id, package_id, start_date, end_date, order_id) VALUES (new.user_id, l_package_id, l_start_date, DATE_ADD(l_start_date, INTERVAL l_validity_period MONTH), new.order_id);
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `manage_insolvent_user`;
DELIMITER $$
CREATE TRIGGER `manage_insolvent_user` AFTER INSERT ON `payment_history` FOR EACH ROW begin
declare number int;
IF new.payment_status = 0 THEN update user set insolvent = 1 where id = new.user_id;
else SET number = (SELECT COUNT(*) FROM payment_history AS p1 WHERE p1.payment_status = 0 AND p1.user_id = new.user_id AND p1.order_id NOT IN (SELECT p2.order_id FROM payment_history AS p2 WHERE p2.payment_status = 1 AND p2.user_id = new.user_id));
IF number = 0 THEN update user set insolvent = 0 where id = new.user_id;
END IF;
end if;
end
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_failed_payment`;
DELIMITER $$
CREATE TRIGGER `update_failed_payment` AFTER INSERT ON `payment_history` FOR EACH ROW BEGIN
IF new.payment_status = 0 THEN
UPDATE failed_payment SET n_failures = n_failures + 1, last_failure = CURRENT_TIMESTAMP() WHERE user_id = new.user_id;
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `update_order_stauts`;
DELIMITER $$
CREATE TRIGGER `update_order_stauts` AFTER INSERT ON `payment_history` FOR EACH ROW BEGIN
IF new.payment_status = 0 THEN
UPDATE orders SET order_status = 2 WHERE id = new.order_id;
ELSE
UPDATE orders SET order_status = 1 WHERE id = new.order_id;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `min` int(11) DEFAULT NULL,
  `sms` int(11) DEFAULT NULL,
  `internet` int(11) DEFAULT NULL,
  `extra_min` float DEFAULT NULL,
  `extra_sms` float DEFAULT NULL,
  `extra_internet` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `service_in_package`
--

DROP TABLE IF EXISTS `service_in_package`;
CREATE TABLE IF NOT EXISTS `service_in_package` (
  `package_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  PRIMARY KEY (`package_id`,`service_id`),
  KEY `service_id` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `service_package`
--

DROP TABLE IF EXISTS `service_package`;
CREATE TABLE IF NOT EXISTS `service_package` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `service_package`
--
DROP TRIGGER IF EXISTS `create_purchase_optional_avg`;
DELIMITER $$
CREATE TRIGGER `create_purchase_optional_avg` AFTER INSERT ON `service_package` FOR EACH ROW BEGIN
INSERT INTO avarage_purchase_optional_package (package_id, package_name, avg_optional) VALUES (new.id, new.name, 0);
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `create_purchase_package`;
DELIMITER $$
CREATE TRIGGER `create_purchase_package` AFTER INSERT ON `service_package` FOR EACH ROW BEGIN 
INSERT INTO total_purchase_package (package_id, package_name, tot_purchase) VALUES (NEW.id, NEW.name, 0);
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `create_purchase_package_optional`;
DELIMITER $$
CREATE TRIGGER `create_purchase_package_optional` AFTER INSERT ON `service_package` FOR EACH ROW BEGIN INSERT INTO total_purchase_package_optional (tot_purchase, package_id, package_name, has_optional_product) VALUES (0, new.id, NEW.name, 0), (0, new.id, NEW.name, 1); END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `create_purchase_package_validity`;
DELIMITER $$
CREATE TRIGGER `create_purchase_package_validity` AFTER INSERT ON `service_package` FOR EACH ROW BEGIN INSERT INTO total_purchase_package_validity (tot_purchase, package_id, package_name, validity_period) VALUES (0, new.id, NEW.name, 12), (0, new.id, NEW.name, 24), (0, new.id, NEW.name, 36); END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `create_tot_value_package`;
DELIMITER $$
CREATE TRIGGER `create_tot_value_package` AFTER INSERT ON `service_package` FOR EACH ROW BEGIN INSERT INTO tot_value_optional_no_optional (tot_value, package_id, package_name, with_optional) VALUES (0, new.id, NEW.name, 0), (0, new.id, NEW.name, 1); END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `delete_purchase_optional_avg`;
DELIMITER $$
CREATE TRIGGER `delete_purchase_optional_avg` AFTER DELETE ON `service_package` FOR EACH ROW BEGIN
DELETE FROM avarage_purchase_optional_package WHERE package_id = old.id;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `delete_purchase_package`;
DELIMITER $$
CREATE TRIGGER `delete_purchase_package` AFTER DELETE ON `service_package` FOR EACH ROW BEGIN 
DELETE FROM total_purchase_package WHERE package_id = old.id; 
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `delete_purchase_package_optional`;
DELIMITER $$
CREATE TRIGGER `delete_purchase_package_optional` AFTER DELETE ON `service_package` FOR EACH ROW BEGIN
DELETE FROM total_purchase_package_optional WHERE package_id = old.id;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `delete_purchase_package_validity`;
DELIMITER $$
CREATE TRIGGER `delete_purchase_package_validity` AFTER DELETE ON `service_package` FOR EACH ROW BEGIN
DELETE FROM total_purchase_package_validity WHERE package_id = old.id;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `delete_tot_value_package`;
DELIMITER $$
CREATE TRIGGER `delete_tot_value_package` AFTER DELETE ON `service_package` FOR EACH ROW BEGIN
DELETE FROM tot_value_optional_no_optional WHERE package_id = old.id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `suspended_orders`
--

DROP TABLE IF EXISTS `suspended_orders`;
CREATE TABLE IF NOT EXISTS `suspended_orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `total_purchase_optional`
--

DROP TABLE IF EXISTS `total_purchase_optional`;
CREATE TABLE IF NOT EXISTS `total_purchase_optional` (
  `optional_id` int(11) NOT NULL,
  `optional_name` varchar(255) NOT NULL,
  `tot_purchase` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`optional_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `total_purchase_package`
--

DROP TABLE IF EXISTS `total_purchase_package`;
CREATE TABLE IF NOT EXISTS `total_purchase_package` (
  `package_id` int(11) NOT NULL,
  `package_name` varchar(255) NOT NULL,
  `tot_purchase` int(11) NOT NULL,
  PRIMARY KEY (`package_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `total_purchase_package_optional`
--

DROP TABLE IF EXISTS `total_purchase_package_optional`;
CREATE TABLE IF NOT EXISTS `total_purchase_package_optional` (
  `tot_purchase` int(11) NOT NULL DEFAULT 0,
  `package_id` int(11) NOT NULL,
  `package_name` varchar(255) NOT NULL,
  `has_optional_product` int(11) NOT NULL,
  PRIMARY KEY (`package_id`,`has_optional_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `total_purchase_package_validity`
--

DROP TABLE IF EXISTS `total_purchase_package_validity`;
CREATE TABLE IF NOT EXISTS `total_purchase_package_validity` (
  `package_id` int(11) NOT NULL,
  `package_name` varchar(255) NOT NULL,
  `tot_purchase` int(11) NOT NULL,
  `validity_period` int(11) NOT NULL,
  PRIMARY KEY (`package_id`,`validity_period`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tot_value_optional_no_optional`
--

DROP TABLE IF EXISTS `tot_value_optional_no_optional`;
CREATE TABLE IF NOT EXISTS `tot_value_optional_no_optional` (
  `package_id` int(11) NOT NULL,
  `with_optional` int(11) NOT NULL,
  `package_name` varchar(255) NOT NULL,
  `tot_value` double DEFAULT 0,
  PRIMARY KEY (`package_id`,`with_optional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(225) NOT NULL,
  `surname` varchar(225) NOT NULL,
  `username` varchar(225) NOT NULL,
  `email` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `insolvent` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Triggers `user`
--
DROP TRIGGER IF EXISTS `create_failure_user`;
DELIMITER $$
CREATE TRIGGER `create_failure_user` AFTER INSERT ON `user` FOR EACH ROW BEGIN INSERT INTO failed_payment (user_id, last_failure, n_failures) VALUES (new.id, NULL, 0); END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `delete_failure_user`;
DELIMITER $$
CREATE TRIGGER `delete_failure_user` AFTER DELETE ON `user` FOR EACH ROW BEGIN
DELETE FROM failed_payment WHERE user_id = old.id;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `retrieve_insolvent_users`;
DELIMITER $$
CREATE TRIGGER `retrieve_insolvent_users` BEFORE UPDATE ON `user` FOR EACH ROW BEGIN
IF old.insolvent = 0 and new.insolvent = 1 THEN
INSERT INTO insolvent_user (id, name, surname, username, email) VALUES (new.id, new.name, new.surname, new.username, new.email);
ELSEIF old.insolvent = 1 and new.insolvent = 0 THEN
DELETE FROM insolvent_user WHERE id = new.id;
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure for view `number_optional_package`
--
DROP TABLE IF EXISTS `number_optional_package`;

DROP VIEW IF EXISTS `number_optional_package`;
CREATE ALGORITHM=UNDEFINED DEFINER=`mattia`@`%` SQL SECURITY DEFINER VIEW `number_optional_package`  AS SELECT `o1`.`package_id` AS `package_id`, count(`o`.`optional_id`) AS `number` FROM (`optional_product_order` `o` join `orders` `o1` on(`o`.`order_id` = `o1`.`id`)) GROUP BY `o`.`order_id`, `o1`.`user_id``user_id`  ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activation`
--
ALTER TABLE `activation`
  ADD CONSTRAINT `activation_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `activation_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `service_package` (`id`),
  ADD CONSTRAINT `activation_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `alerts`
--
ALTER TABLE `alerts`
  ADD CONSTRAINT `alerts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `optional_product_in_package`
--
ALTER TABLE `optional_product_in_package`
  ADD CONSTRAINT `optional_product_in_package_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `service_package` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `optional_product_in_package_ibfk_2` FOREIGN KEY (`optional_product_id`) REFERENCES `optional_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `optional_product_order`
--
ALTER TABLE `optional_product_order`
  ADD CONSTRAINT `optional_product_order_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `optional_product_order_ibfk_2` FOREIGN KEY (`optional_id`) REFERENCES `optional_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `service_package` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `package_price`
--
ALTER TABLE `package_price`
  ADD CONSTRAINT `package_price_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `service_package` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `payment_history`
--
ALTER TABLE `payment_history`
  ADD CONSTRAINT `payment_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `payment_history_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `service_in_package`
--
ALTER TABLE `service_in_package`
  ADD CONSTRAINT `service_in_package_ibfk_1` FOREIGN KEY (`package_id`) REFERENCES `service_package` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `service_in_package_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
