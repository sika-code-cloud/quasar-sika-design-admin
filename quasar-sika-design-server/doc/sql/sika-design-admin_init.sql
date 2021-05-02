/*
Navicat MySQL Data Transfer

Source Server         : SikaDesignAamin
Source Server Version : 80017
Source Host           : 121.89.202.68:3306
Source Database       : sika-design-admin

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2021-01-13 10:54:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sika_config
-- ----------------------------
DROP TABLE IF EXISTS `sika_config`;
CREATE TABLE `sika_config` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';

-- ----------------------------
-- Records of sika_config
-- ----------------------------

-- ----------------------------
-- Table structure for sika_demo
-- ----------------------------
DROP TABLE IF EXISTS `sika_demo`;
CREATE TABLE `sika_demo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `name` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '名字',
  `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',
  `nickname` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户昵称',
  `priority` int(11) NOT NULL DEFAULT '1' COMMENT '优先级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='示例表';

-- ----------------------------
-- Records of sika_demo
-- ----------------------------
INSERT INTO `sika_demo` VALUES ('3', '', '', '2020-12-25 13:12:26', '2020-12-25 13:13:38', '0', '1', '0', '', '张三2222', '20', '', '1');
INSERT INTO `sika_demo` VALUES ('4', '', '', '2020-12-25 13:12:44', '2020-12-25 13:12:44', '0', '1', '0', '', '张三', '20', '', '1');
INSERT INTO `sika_demo` VALUES ('5', '', '', '2020-12-25 13:12:50', '2020-12-25 13:12:50', '0', '1', '0', '', '张三', '20', '', '1');

-- ----------------------------
-- Table structure for sika_dept
-- ----------------------------
DROP TABLE IF EXISTS `sika_dept`;
CREATE TABLE `sika_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_update_date` (`update_date`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='部门表';

-- ----------------------------
-- Records of sika_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sika_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sika_dict_data`;
CREATE TABLE `sika_dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of sika_dict_data
-- ----------------------------

-- ----------------------------
-- Table structure for sika_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sika_dict_type`;
CREATE TABLE `sika_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';

-- ----------------------------
-- Records of sika_dict_type
-- ----------------------------

-- ----------------------------
-- Table structure for sika_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sika_logininfor`;
CREATE TABLE `sika_logininfor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';

-- ----------------------------
-- Records of sika_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sika_mail_template
-- ----------------------------
DROP TABLE IF EXISTS `sika_mail_template`;
CREATE TABLE `sika_mail_template` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '模板类型',
  `code` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '模板编号',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '模板主题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '模板内容',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_code` (`code`) USING BTREE,
  KEY `title` (`title`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='邮件模板表';

-- ----------------------------
-- Records of sika_mail_template
-- ----------------------------
INSERT INTO `sika_mail_template` VALUES ('1', '', '', '2021-01-02 21:15:30', '2021-01-09 02:52:46', '0', '1', '0', '', '10', 'BIND_OAUTH_USER_00001', '验证码模板', '<head>\r\n    <base target=\"_blank\"/>\r\n    <style type=\"text/css\">::-webkit-scrollbar {\r\n            display: none;\r\n        }</style>\r\n    <style id=\"cloudAttachStyle\" type=\"text/css\">#divNeteaseBigAttach, #divNeteaseBigAttach_bak {\r\n            display: none;\r\n        }</style>\r\n    <style id=\"blockquoteStyle\" type=\"text/css\">blockquote {\r\n            display: none;\r\n        }</style>\r\n    <style type=\"text/css\">\r\n        body {\r\n            font-size: 14px;\r\n            font-family: arial, verdana, sans-serif;\r\n            line-height: 1.666;\r\n            padding: 0;\r\n            margin: 0;\r\n            overflow: auto;\r\n            white-space: normal;\r\n            word-wrap: break-word;\r\n            min-height: 100px\r\n        }\r\n\r\n        td, input, button, select, body {\r\n            font-family: Helvetica, \'Microsoft Yahei\', verdana\r\n        }\r\n\r\n        pre {\r\n            white-space: pre-wrap;\r\n            white-space: -moz-pre-wrap;\r\n            white-space: -pre-wrap;\r\n            white-space: -o-pre-wrap;\r\n            word-wrap: break-word;\r\n            width: 95%\r\n        }\r\n\r\n        th, td {\r\n            font-family: arial, verdana, sans-serif;\r\n            line-height: 1.666\r\n        }\r\n\r\n        img {\r\n            border: 0\r\n        }\r\n\r\n        header, footer, section, aside, article, nav, hgroup, figure, figcaption {\r\n            display: block\r\n        }\r\n\r\n        blockquote {\r\n            margin-right: 0px\r\n        }\r\n    </style>\r\n</head>\r\n<body tabindex=\"0\" role=\"listitem\">\r\n<table width=\"700\" border=\"0\" align=\"center\" cellspacing=\"0\" style=\"width:700px;\">\r\n    <tbody>\r\n    <tr>\r\n        <td>\r\n            <div style=\"width:700px;margin:0 auto;border-bottom:1px solid #ccc;margin-bottom:30px;\">\r\n                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"700\" height=\"39\"\r\n                       style=\"font:12px Tahoma, Arial, 宋体;\">\r\n                    <tbody>\r\n                    <tr>\r\n                        <td width=\"210\"></td>\r\n                    </tr>\r\n                    </tbody>\r\n                </table>\r\n            </div>\r\n            <div style=\"width:680px;padding:0 10px;margin:0 auto;\">\r\n                <div style=\"line-height:1.5;font-size:14px;margin-bottom:25px;color:#4d4d4d;\">\r\n                    <strong style=\"display:block;margin-bottom:15px;\">尊敬的用户：<span\r\n                                style=\"color:#f60;font-size: 16px;\"></span>您好！</strong>\r\n                    <strong style=\"display:block;margin-bottom:15px;\">\r\n                        您正在进行<span style=\"color: red\">【${model.operate }</span>】操作，请在验证码输入框中输入：<span\r\n                                style=\"color:#f60;font-size: 24px\">${model.content }</span>，以完成操作。\r\n                    </strong>\r\n                </div>\r\n                <div style=\"margin-bottom:30px;\">\r\n                    <small style=\"display:block;margin-bottom:20px;font-size:12px;\">\r\n                        <p style=\"color:#747474;\">\r\n                            注意：此操作可能会修改您的密码、登录邮箱或绑定手机。如非本人操作，请及时登录并修改密码以保证帐户安全\r\n                            <br>（工作人员不会向你索取此验证码，请勿泄漏！)\r\n                        </p>\r\n                    </small>\r\n                </div>\r\n            </div>\r\n            <div style=\"width:700px;margin:0 auto;\">\r\n                <div style=\"padding:10px 10px 0;border-top:1px solid #ccc;color:#747474;margin-bottom:20px;line-height:1.3em;font-size:12px;\">\r\n                    <p>此为系统邮件，请勿回复<br>\r\n                        请保管好您的邮箱，避免账号被他人盗用\r\n                    </p>\r\n                    <p>院主网络科技团队</p>\r\n                </div>\r\n            </div>\r\n        </td>\r\n    </tr>\r\n    </tbody>\r\n</table>\r\n</body>\r\n');

-- ----------------------------
-- Table structure for sika_menu
-- ----------------------------
DROP TABLE IF EXISTS `sika_menu`;
CREATE TABLE `sika_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `url` varchar(200) DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_update_date` (`update_date`)
) ENGINE=InnoDB AUTO_INCREMENT=2000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of sika_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sika_notice
-- ----------------------------
DROP TABLE IF EXISTS `sika_notice`;
CREATE TABLE `sika_notice` (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) DEFAULT NULL COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';

-- ----------------------------
-- Records of sika_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sika_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sika_oper_log`;
CREATE TABLE `sika_oper_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志记录';

-- ----------------------------
-- Records of sika_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sika_post
-- ----------------------------
DROP TABLE IF EXISTS `sika_post`;
CREATE TABLE `sika_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_update_date` (`update_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='岗位信息表';

-- ----------------------------
-- Records of sika_post
-- ----------------------------

-- ----------------------------
-- Table structure for sika_role
-- ----------------------------
DROP TABLE IF EXISTS `sika_role`;
CREATE TABLE `sika_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_update_date` (`update_date`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- ----------------------------
-- Records of sika_role
-- ----------------------------

-- ----------------------------
-- Table structure for sika_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sika_role_dept`;
CREATE TABLE `sika_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和部门关联表';

-- ----------------------------
-- Records of sika_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sika_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sika_role_menu`;
CREATE TABLE `sika_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sika_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sika_third_oauth_user
-- ----------------------------
DROP TABLE IF EXISTS `sika_third_oauth_user`;
CREATE TABLE `sika_third_oauth_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '第三方uuid',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '第三方用户名称',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '第三方昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '头像链接',
  `blog` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '博客',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '公司或者组织',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '所在地点',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '邮箱',
  `gender` varchar(7) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '性别',
  `source` varchar(31) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '来源【gitee|gethub】等等',
  `token` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'token',
  `raw_user_info` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT 'rawUserInfo',
  `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联用户id【为0表示没有关联】',
  `state` varchar(127) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '第三方授权登录的state',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_uuid_source` (`uuid`,`source`) USING BTREE,
  UNIQUE KEY `uniq_username_source` (`username`,`email`) USING BTREE,
  UNIQUE KEY `uniq_state_source` (`source`,`state`) USING BTREE,
  KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='第三方授权用户表';

-- ----------------------------
-- Records of sika_third_oauth_user
-- ----------------------------
INSERT INTO `sika_third_oauth_user` VALUES ('1', '', '', '2021-01-01 23:55:07', '2021-01-13 01:25:00', '0', '1', '0', '开源改变世界，让编码更优雅', '17608739', 'dq-open-cloud', 'sika-code', 'https://avatars3.githubusercontent.com/u/17608739?v=4', '', '', '', '466608943@qq.com', 'UNKNOWN', 'GITHUB', 0x6D652E7A6879642E6F617574682E6D6F64656C2E41757468546F6B656E403362333436336537, 0x7B2267697374735F75726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F75642F67697374737B2F676973745F69647D222C227265706F735F75726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F75642F7265706F73222C22666F6C6C6F77696E675F75726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F75642F666F6C6C6F77696E677B2F6F746865725F757365727D222C2262696F223A22E5BC80E6BA90E694B9E58F98E4B896E7958CEFBC8CE8AEA9E7BC96E7A081E69BB4E4BC98E99B85222C22637265617465645F6174223A22323031362D30332D30335430343A35393A33335A222C226C6F67696E223A2264712D6F70656E2D636C6F7564222C2274797065223A2255736572222C22626C6F67223A22222C22737562736372697074696F6E735F75726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F75642F737562736372697074696F6E73222C22757064617465645F6174223A22323032312D30312D31325431333A30313A35375A222C22736974655F61646D696E223A66616C73652C226964223A31373630383733392C227075626C69635F7265706F73223A31312C2267726176617461725F6964223A22222C22656D61696C223A223436363630383934334071712E636F6D222C226F7267616E697A6174696F6E735F75726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F75642F6F726773222C22737461727265645F75726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F75642F737461727265647B2F6F776E65727D7B2F7265706F7D222C22666F6C6C6F776572735F75726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F75642F666F6C6C6F77657273222C227075626C69635F6769737473223A302C2275726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F7564222C2272656365697665645F6576656E74735F75726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F75642F72656365697665645F6576656E7473222C22666F6C6C6F77657273223A31332C226176617461725F75726C223A2268747470733A2F2F61766174617273332E67697468756275736572636F6E74656E742E636F6D2F752F31373630383733393F763D34222C226576656E74735F75726C223A2268747470733A2F2F6170692E6769746875622E636F6D2F75736572732F64712D6F70656E2D636C6F75642F6576656E74737B2F707269766163797D222C2268746D6C5F75726C223A2268747470733A2F2F6769746875622E636F6D2F64712D6F70656E2D636C6F7564222C22666F6C6C6F77696E67223A312C226E616D65223A2273696B612D636F6465222C226E6F64655F6964223A224D44513656584E6C636A45334E6A41344E7A4D35227D, '8', 'E91E7500B02F57C47F14D93A2832D9B6');
INSERT INTO `sika_third_oauth_user` VALUES ('4', '', '', '2021-01-02 00:02:08', '2021-01-13 01:11:11', '0', '1', '0', '', '6534365', 'sikadai', 'sikadai', 'https://portrait.gitee.com/uploads/avatars/user/2178/6534365_sikadai_1598190791.png', '', '', '', '', 'UNKNOWN', 'GITEE', 0x6D652E7A6879642E6F617574682E6D6F64656C2E41757468546F6B656E403166356165636366, 0x7B2267697374735F75726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B616461692F67697374737B2F676973745F69647D222C227265706F735F75726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B616461692F7265706F73222C22666F6C6C6F77696E675F75726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B616461692F666F6C6C6F77696E675F75726C7B2F6F746865725F757365727D222C22637265617465645F6174223A22323032302D30332D30385432303A30383A32362B30383A3030222C226C6F67696E223A2273696B61646169222C2274797065223A2255736572222C22737562736372697074696F6E735F75726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B616461692F737562736372697074696F6E73222C22757064617465645F6174223A22323032312D30312D31325432323A34303A35342B30383A3030222C226964223A363533343336352C227075626C69635F7265706F73223A382C226F7267616E697A6174696F6E735F75726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B616461692F6F726773222C22737461727265645F75726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B616461692F737461727265647B2F6F776E65727D7B2F7265706F7D222C22666F6C6C6F776572735F75726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B616461692F666F6C6C6F77657273222C227075626C69635F6769737473223A302C2275726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B61646169222C2272656365697665645F6576656E74735F75726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B616461692F72656365697665645F6576656E7473222C2277617463686564223A31372C22666F6C6C6F77657273223A312C226176617461725F75726C223A2268747470733A2F2F706F7274726169742E67697465652E636F6D2F75706C6F6164732F617661746172732F757365722F323137382F363533343336355F73696B616461695F313539383139303739312E706E67222C226576656E74735F75726C223A2268747470733A2F2F67697465652E636F6D2F6170692F76352F75736572732F73696B616461692F6576656E74737B2F707269766163797D222C2268746D6C5F75726C223A2268747470733A2F2F67697465652E636F6D2F73696B61646169222C22666F6C6C6F77696E67223A302C226E616D65223A2273696B61646169222C22737461726564223A33347D, '0', '15F3A4B60F1F520FB199E33D2E37564F');

-- ----------------------------
-- Table structure for sika_user
-- ----------------------------
DROP TABLE IF EXISTS `sika_user`;
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
  `password` varchar(127) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '密码',
  `oauth_pwd` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '授权密码',
  `nickname` varchar(63) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '用户昵称',
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of sika_user
-- ----------------------------
INSERT INTO `sika_user` VALUES ('19', '', '', '2021-01-10 01:12:13', '2021-01-13 01:08:05', '0', '1', '0', '', 'sikadai', 'f9af12b16ffe5ecc16e7fbac3827a326c17c85c723f9fbe45b154214adb7ff11', '', '', '0', '18938182678', '466608943@qq.com', '', 'TOKEN:a704444f-70f2-4272-896a-2f77694eae35', '1');
INSERT INTO `sika_user` VALUES ('20', '', '', '2021-01-10 01:19:29', '2021-01-10 01:41:34', '0', '1', '0', '', 'sika', 'b69329634a2eae57030bee379462868c7afc77f370d51eed26a0a2e2ec938ad1', '', '', '0', '18938267677', '4555@qq.com', '', 'TOKEN:1315f310-734a-416a-9501-e7c698c47478', '1');
INSERT INTO `sika_user` VALUES ('21', '', '', '2021-01-10 21:35:59', '2021-01-10 21:36:00', '0', '1', '0', '', 'sikaq', '9d89bbb61a528e76a7cd398eb50ffe3f5adaf2ce4c8fc5642ebe9bd055fc207f', '', '', '0', '18938286877', '45550@132.com', '', 'TOKEN:279a460e-1683-4a2e-9e3e-ec07225e290c', '1');
INSERT INTO `sika_user` VALUES ('22', '', '', '2021-01-10 21:37:59', '2021-01-10 21:38:00', '0', '1', '0', '', 'zhangshu', 'f3fc16d078faee26fab35195c67e48e98a1beca03a403ef7fe7a6ee4acf69e5b', '', '', '0', '18938182676', '466608943@12.com', '', 'TOKEN:279a460e-1683-4a2e-9e3e-ec07225e290c', '1');
INSERT INTO `sika_user` VALUES ('23', '', '', '2021-01-10 21:46:01', '2021-01-10 21:46:01', '0', '1', '0', '', 'sika123', '8a98947f3aa48361082ebde84b8fd88497f7d261becc5e92688bf5f3be6c67fd', '', '', '0', '18938286727', '466608943@ww.com', '', 'TOKEN:279a460e-1683-4a2e-9e3e-ec07225e290c', '1');

-- ----------------------------
-- Table structure for sika_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sika_user_online`;
CREATE TABLE `sika_user_online` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `sessionId` varchar(50) DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int(5) DEFAULT '0' COMMENT '超时时间，单位为分钟',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sessionId` (`sessionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='在线用户记录';

-- ----------------------------
-- Records of sika_user_online
-- ----------------------------

-- ----------------------------
-- Table structure for sika_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sika_user_post`;
CREATE TABLE `sika_user_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户与岗位关联表';

-- ----------------------------
-- Records of sika_user_post
-- ----------------------------

-- ----------------------------
-- Table structure for sika_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sika_user_role`;
CREATE TABLE `sika_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '创建人标识',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '最后更新人标识',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本管理标志',
  `available` int(11) NOT NULL DEFAULT '1' COMMENT '可用标志 [1:可用,0:不可用]',
  `is_deleted` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标志[1:删除,0:未删]',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sika_user_role
-- ----------------------------
