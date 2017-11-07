/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-08-30 14:20:36
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id`   INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20)
         COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('1', 'iPad');
INSERT INTO `device` VALUES ('2', 'iPod');
INSERT INTO `device` VALUES ('3', 'iMac');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT,
  `name`     VARCHAR(10)
           COLLATE utf8_bin DEFAULT NULL,
  `username` VARCHAR(10)
             COLLATE utf8_bin DEFAULT NULL,
  `password` VARCHAR(10)
             COLLATE utf8_bin DEFAULT NULL,
  `gender`   VARCHAR(6)
           COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'Jack', 'jack', 'jack', 'male');
INSERT INTO `user` VALUES ('2', 'Jone', 'jone', 'jone', 'female');

-- ----------------------------
-- Table structure for user_device
-- ----------------------------
DROP TABLE IF EXISTS `user_device`;
CREATE TABLE `user_device` (
  `id`        INT(11) NOT NULL AUTO_INCREMENT,
  `user_id`   INT(11) NOT NULL,
  `device_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;

-- ----------------------------
-- Records of user_device
-- ----------------------------
INSERT INTO `user_device` VALUES ('11', '1', '3');
INSERT INTO `user_device` VALUES ('12', '1', '2');
INSERT INTO `user_device` VALUES ('13', '2', '3');
INSERT INTO `user_device` VALUES ('14', '2', '1');
