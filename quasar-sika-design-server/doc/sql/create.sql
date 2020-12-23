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
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `name` varchar(63) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '名字',
  `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',
  `nickname` varchar(63) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户昵称',
  `priority` int(11) NOT NULL DEFAULT '1' COMMENT '优先级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='示例表';