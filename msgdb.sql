/*
 Navicat Premium Data Transfer

 Source Server         : qwe
 Source Server Type    : MySQL
 Source Server Version : 50130
 Source Host           : localhost:3306
 Source Schema         : msgdb

 Target Server Type    : MySQL
 Target Server Version : 50130
 File Encoding         : 65001

 Date: 30/12/2021 01:46:11
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for msgs
-- ----------------------------
DROP TABLE IF EXISTS `msgs`;
CREATE TABLE `msgs`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '留言id，主键，自增长',
  `msgtype` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言类别(一般、重要)',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言标题',
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  `pubdate` date NOT NULL COMMENT '留言发表日期',
  `reply` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言回复',
  `userid` bigint(20) NOT NULL COMMENT '留言用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of msgs
-- ----------------------------
INSERT INTO `msgs` VALUES (1, '一般', '图书馆闭馆时间', '请问2016年国庆期间，图书馆的开闭馆时间？', '2017-09-01', NULL, 2);
INSERT INTO `msgs` VALUES (2, '重要', '图书借阅时长', '计算机类图书可以借阅多少天？', '2017-10-01', NULL, 2);
INSERT INTO `msgs` VALUES (3, '一般', '计算类图书借阅数量', '一次最多可借阅计算类图书多少本？', '2017-11-01', NULL, 3);
INSERT INTO `msgs` VALUES (4, '重要', '图书馆建立时间', '请问图书馆什么时候建立的？', '2017-09-03', NULL, 3);
INSERT INTO `msgs` VALUES (5, '一般', '图书馆大约有多少本书', '图书馆内有多少书？', '2017-10-04', NULL, 4);
INSERT INTO `msgs` VALUES (6, '重要', '图书馆假期开放吗', '图书馆的具体开闭馆时间', '2017-11-01', NULL, 4);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id，主键，自增长',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `userpwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `userxm` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `birthday` date NULL DEFAULT NULL COMMENT '用户出生日期',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电子邮箱',
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户性别',
  `headpic` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像文件名',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户角色(权限)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', 'admin', NULL, NULL, NULL, 'head1.gif', 'admin');
INSERT INTO `users` VALUES (2, 'user11', 'user11', 'user11', '1997-01-01', 'user11@126.com', '男', 'head1.gif', 'user');
INSERT INTO `users` VALUES (3, 'user22', 'user22', 'user22', '1997-02-01', 'user22@126.com', '女', 'head2.gif', 'user');
INSERT INTO `users` VALUES (4, 'user33', 'user33', 'user33', '1998-01-01', 'user33@126.com', '男', 'head3.gif', 'user');
INSERT INTO `users` VALUES (5, 'user44', 'user44', 'user44', '1998-02-01', 'user44@126.com', '女', 'head4.gif', 'user');

SET FOREIGN_KEY_CHECKS = 1;
