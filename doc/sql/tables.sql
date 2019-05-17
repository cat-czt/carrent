/*
Navicat MySQL Data Transfer

Source Server         : 47.112.24.164
Source Server Version : 50725
Source Host           : 47.112.24.164:3306
Source Database       : information

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-03-28 13:51:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) COLLATE utf8_bin NOT NULL,
  `account` varchar(200) COLLATE utf8_bin NOT NULL,
  `password` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `mobile` varchar(11) COLLATE utf8_bin NOT NULL,
  `open_id` varchar(200) DEFAULT NULL COMMENT '微信openId',
  `create_date` datetime DEFAULT NULL,
  `create_user` int(20) DEFAULT NULL,
  `update_user` int(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(200) COLLATE utf8_bin NOT NULL,
  `remark` varchar(600) COLLATE utf8_bin DEFAULT NULL,
  `create_user` int(20) DEFAULT NULL,
  `update_user` int(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `sys_param`
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(200) COLLATE utf8_bin NOT NULL,
  `param_value` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(600) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_user` int(20) DEFAULT NULL,
  `update_user` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `information`
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `summary` varchar(900) COLLATE utf8_bin NOT NULL COMMENT '内容简介',
  `charger` varchar(120) COLLATE utf8_bin NOT NULL COMMENT '负责人',
  `mobile` varchar(11) COLLATE utf8_bin NOT NULL COMMENT '联系电话',
  `province` varchar(120) COLLATE utf8_bin NOT NULL COMMENT '省',
  `city` varchar(120) COLLATE utf8_bin NOT NULL COMMENT '市/直辖区',
  `district` varchar(120) COLLATE utf8_bin NOT NULL COMMENT '区',
  `address` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '标题',
  `longitude` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '经度',
  `latitude` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '纬度',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_user` int(20) DEFAULT NULL,
  `update_user` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `attachment`
-- ----------------------------
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `information_id` int(20) NOT NULL COMMENT '关联信息id',
  `file_name` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '附件名称',
  `path` varchar(600) COLLATE utf8_bin NOT NULL COMMENT '附件存储路径',
  `extension` varchar(12) COLLATE utf8_bin NOT NULL COMMENT '文件后缀',
  `size` int(20) COLLATE utf8_bin NOT NULL COMMENT '文件大小',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `create_user` int(20) DEFAULT NULL,
  `update_user` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

