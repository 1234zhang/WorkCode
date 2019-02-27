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

 Date: 13/02/2019 13:11:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for song_table
-- ----------------------------
DROP TABLE IF EXISTS `song_table`;
CREATE TABLE `song_table`  (
  `song_id` int(11) NOT NULL AUTO_INCREMENT,
  `song_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `song_publishtime` timestamp(0) NOT NULL,
  `sing_id` int(10) NOT NULL,
  `sing_picid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sing_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sing_en` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `album_id` int(10) NOT NULL,
  `album_picid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `album_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `album_publishtime` timestamp(0) NOT NULL,
  `album_company` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`song_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song_table
-- ----------------------------
INSERT INTO `song_table` VALUES (1, 'sdf', '2019-02-12 23:00:17', 123, 'mouseBrother', '123', 'ewr', 1, 'mouseBrother', 'sdf', '2019-02-12 23:21:28', 'xcv');
INSERT INTO `song_table` VALUES (2, 'sdfcx', '2019-02-12 23:00:48', 123, 'mouseBrother', '132', 'ewr', 1, 'mouseBrother', 'sdf', '2019-02-13 23:21:28', 'xcv');

SET FOREIGN_KEY_CHECKS = 1;
