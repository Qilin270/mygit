/*
 Navicat Premium Data Transfer

 Source Server         : mydb
 Source Server Type    : MySQL
 Source Server Version : 50623
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 50623
 File Encoding         : 65001

 Date: 03/03/2023 22:24:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for walletlog
-- ----------------------------
DROP TABLE IF EXISTS `walletlog`;
CREATE TABLE `walletlog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '钱包记录id',
  `wallet_id` int(11) NOT NULL COMMENT '钱包id',
  `balance_old` decimal(10, 2) NOT NULL COMMENT '原先余额',
  `balance_new` decimal(10, 2) NOT NULL COMMENT '最新余额',
  `balance_change` decimal(10, 2) NOT NULL COMMENT '变动余额',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of walletlog
-- ----------------------------
INSERT INTO `walletlog` VALUES (4, 2, 182.33, 82.33, -100.00, '2023-03-03 16:07:39');
INSERT INTO `walletlog` VALUES (5, 3, 17.23, 27.23, 10.00, '2023-03-03 17:41:46');
INSERT INTO `walletlog` VALUES (6, 3, 27.23, 47.23, 20.00, '2023-03-03 18:11:21');
INSERT INTO `walletlog` VALUES (7, 2, 202.33, 222.33, 20.00, '2023-03-03 19:37:05');
INSERT INTO `walletlog` VALUES (8, 2, 222.33, 122.33, -100.00, '2023-03-03 19:38:12');
INSERT INTO `walletlog` VALUES (9, 2, 122.33, 22.33, -100.00, '2023-03-03 19:38:14');

SET FOREIGN_KEY_CHECKS = 1;
