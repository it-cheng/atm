DROP DATABASE IF EXISTS `atm`;
CREATE DATABASE `atm`;

USE `atm`;

SET FOREIGN_KEY_CHECKS=0;


DROP TABLE IF EXISTS `card_info`;
CREATE TABLE `card_info` (
  `card_id` varchar(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `balance` int(11) NOT NULL,
  `pass` varchar(32) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`card_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `card_info_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `user_info` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `card_info` VALUES ('1001', '0', '123456', '1');
INSERT INTO `card_info` VALUES ('1002', '200', '123456', '2');
INSERT INTO `card_info` VALUES ('1003', '0', '123456', '1');


DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `user_info` VALUES ('1', '张三', '412345678945612345', '18845678912', '河南省新乡市');
INSERT INTO `user_info` VALUES ('2', '李四', '413658974562356987', '15912365789', '河南省郑州市');
