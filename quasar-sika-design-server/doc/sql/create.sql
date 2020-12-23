/*
Navicat MySQL Data Transfer

Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : sika-design-admin

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-12-23 18:08:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sika_demo
-- ----------------------------
DROP TABLE IF EXISTS `sika_demo`;
CREATE TABLE `sika_demo` (
     `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
     `create_by` varchar(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '创建人',
     `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_by` varchar(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' COMMENT '文章封面图片',
     `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     `version` int(11) unsigned NOT NULL DEFAULT '1',
     `is_deleted` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '删除标识',
     `available` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '可用',
     `remark` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
     `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
     `age` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '年龄',
     `nickname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '名称',
     `priority` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '优先级',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
