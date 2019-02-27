/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : mynetease

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 16/02/2019 14:49:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for songlist_table
-- ----------------------------
DROP TABLE IF EXISTS `songlist_table`;
CREATE TABLE `songlist_table`  (
  `songlist_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` varchar(35) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `songlist_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `songlist_type` int(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`songlist_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of songlist_table
-- ----------------------------
INSERT INTO `songlist_table` VALUES (1, '1234', '23', 1);
INSERT INTO `songlist_table` VALUES (2, '123', '123454', 1);
INSERT INTO `songlist_table` VALUES (3, 'dsf', '1234', 1);
INSERT INTO `songlist_table` VALUES (4, 'dsf', '1234', 1);
INSERT INTO `songlist_table` VALUES (6, 'dsf', '1234', 1);
INSERT INTO `songlist_table` VALUES (7, 'dsf', '1234', 1);
INSERT INTO `songlist_table` VALUES (8, 'dsf', '1234', 1);

SET FOREIGN_KEY_CHECKS = 1;
