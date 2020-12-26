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

CREATE TABLE `sika_user` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    `username` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户名',
    `password` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密码',
    `oauthPwd` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '授权密码',
    `nickName` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户昵称',
    `sex` int(11) NOT NULL DEFAULT '0' COMMENT '性别【1：男，2：女，0：未知】',
    `phone` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '手机号',
    `email` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '邮箱',
    `avatar` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '头像',
    `token` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'token',
    `type` int(11) NOT NULL DEFAULT '1' COMMENT '用户类型：1：游客，2：系统用户',
    PRIMARY KEY (`id`),
    KEY `idx_username` (`username`),
    KEY `idx_email` (`email`),
    KEY `idx_phone` (`phone`),
    KEY `idx_token` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

CREATE TABLE `sika_menu` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父菜单id',
    `path` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '菜单路径',
    `title` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '菜单标题',
    `level` int(11) NOT NULL DEFAULT '1' COMMENT '菜单级别',
    `sort_no` int(11) NOT NULL DEFAULT '0' COMMENT '排序编号',
    `icon` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '图标',
    `type` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'menu、button',
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_path` (`path`),
    KEY `idx_sortNo` (`sortNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';

CREATE TABLE `sika_role` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    `code` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '角色编码',
    `name` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '角色名称',
    PRIMARY KEY (`id`),
    UNIQUE KEY `unq_code` (`code`),
    UNIQUE KEY `unq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

CREATE TABLE `sika_user_role` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户id',
    `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色表';

CREATE TABLE `sika_role_menu` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
    `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
    `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
    `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
    `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
    `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
    `menu_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '菜单id',
    `role_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '角色id',
    PRIMARY KEY (`id`),
    KEY `idx_menu_id` (`menu_id`),
    KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色菜单表';


