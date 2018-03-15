/*
Navicat MySQL Data Transfer

Source Server         : hbhk
Source Server Version : 50173
Source Host           : 139.196.180.16:3306
Source Database       : hbhk1

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-06-02 18:08:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_auth_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_privilege`;
CREATE TABLE `t_auth_privilege` (
  `id` varchar(50) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `uri` varchar(255) DEFAULT NULL,
  `parent_code` varchar(50) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `checkable` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `leaf` varchar(255) DEFAULT NULL,
  `icon_cls` varchar(255) DEFAULT NULL,
  `cls` varchar(255) DEFAULT NULL,
  `descp` varchar(2000) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_privilege
-- ----------------------------
INSERT INTO `t_auth_privilege` VALUES ('10a30026-e558-483f-a522-1bfb47baba65', '1022', '用户新增', '/user/toadduser', '8', '0', null, 'F', null, '', '', '', 'admin', '2015-12-16 16:19:57', 'admin', '2015-12-22 17:09:19', '1');
INSERT INTO `t_auth_privilege` VALUES ('41312950-2765-43d0-9478-9576b15df9a7', '12', '菜单管理', '/auth/showMenuIndex', '1', '2', null, 'N', null, '', '', '菜单管理', null, '2016-03-08 18:04:57', 'admin', '2015-12-04 15:01:53', '1');
INSERT INTO `t_auth_privilege` VALUES ('4249ffd3-a537-4046-a96f-8ff3781ebdd4', '0', '应用系统', '', '', '1', '', 'Y', '', '', '', '', null, '2016-03-08 18:04:57', null, '2016-03-08 18:04:57', '1');
INSERT INTO `t_auth_privilege` VALUES ('556b94cd-2083-4246-bdf2-6ebc7c354ce5', '21', '数据字典', '/common/showDictIndex', '2', '1', null, 'N', null, '', '', '', null, '2016-03-21 11:44:13', 'admin', '2015-12-09 18:44:16', '1');
INSERT INTO `t_auth_privilege` VALUES ('5a3c36ae-8f8b-4db3-abdc-391aa6cfb27c', '22', '系统参数', '/common/systemParameterList', '2', '2', null, 'N', null, '', '', '', null, '2016-03-08 18:04:57', 'admin', '2015-12-09 18:44:31', '1');
INSERT INTO `t_auth_privilege` VALUES ('74f39a33-d346-461d-9293-786745df26b9', '2', '系统配置', '/sysconfig', '0', '7', null, 'Y', null, '', '', '', null, '2016-03-08 18:04:57', 'admin', '2016-01-04 15:19:39', '1');
INSERT INTO `t_auth_privilege` VALUES ('78c6d367-637c-4222-914a-c99d551d5477', '14', '用户管理', '/user/userlist', '1', '4', null, 'N', null, '', '', '用户管理', null, '2016-03-08 18:04:57', 'admin', '2015-12-04 11:06:01', '1');
INSERT INTO `t_auth_privilege` VALUES ('84fc3d48-5e67-4b1c-9d1c-1c283dd6649b', '1', '权限管理', '/auth', '0', '8', null, 'Y', null, '', '', '', null, '2016-03-08 18:04:57', 'admin', '2016-01-04 15:19:47', '1');
INSERT INTO `t_auth_privilege` VALUES ('a8e6d452-16a3-4d86-8396-0bccb13ae35a', '80001', 'admin权限', '/80001', '8', '1', null, 'F', null, '', '', '', 'admin', '2015-12-22 17:10:18', null, '2016-03-08 18:04:57', '1');
INSERT INTO `t_auth_privilege` VALUES ('d8b70859-7576-4103-9093-3dbe0537f6a7', '11', '角色管理', '/role/roleList', '1', '1', null, 'N', null, '', '', '', 'admin', '2015-12-04 11:02:49', null, '2016-03-08 18:04:57', '1');
INSERT INTO `t_auth_privilege` VALUES ('dcfd2cd2-b5c4-4fc5-abdd-5d4887194189', '80002', '总账号权限', '/80002', '8', '2', null, 'F', null, '', '', '', 'admin', '2015-12-22 17:10:39', null, '2016-03-08 18:04:57', '1');
INSERT INTO `t_auth_privilege` VALUES ('e3ef75cd-19dd-49ca-9222-f875d5ca55b1', '8', '功能权限', '/auth/auth', '0', '9', null, 'Y', null, '', '', '', 'admin', '2015-12-22 17:07:37', 'admin', '2016-01-04 15:20:07', '1');

-- ----------------------------
-- Table structure for t_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role`;
CREATE TABLE `t_auth_role` (
  `id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `role_code` varchar(50) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `dept_code` varchar(50) DEFAULT NULL,
  `role_type` varchar(50) DEFAULT NULL,
  `status` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_role
-- ----------------------------
INSERT INTO `t_auth_role` VALUES ('d5ce1fc7-37f9-436e-9838-e6e144d69a4c', '2015-12-04 14:30:05', 'admin', 'admin', '2016-01-21 10:07:58', 'admin', '超级管理员', 'admin', null, 'common', '1');

-- ----------------------------
-- Table structure for t_auth_role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role_privilege`;
CREATE TABLE `t_auth_role_privilege` (
  `role_code` varchar(50) DEFAULT NULL,
  `privilege_code` varchar(50) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT NULL,
  `id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_role_privilege
-- ----------------------------
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '41', null, null, '2016-03-08 18:05:39', '1', '083c2d16-0d43-4a13-a7eb-20fe6c327f26', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '6', null, null, '2016-03-08 18:05:39', '1', '1392707f-1cb7-49fc-b138-9453226e7f30', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '14', null, null, '2016-03-08 18:05:39', '1', '33c92b33-8f32-4d5d-b79e-e708234e625c', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '31', null, null, '2016-03-08 18:05:39', '1', '3d1cb80b-1c09-4d7c-8577-b4578ac2d485', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '3', null, null, '2016-03-08 18:05:39', '1', '3e35d350-2b22-4894-a5d0-7cbe5905620d', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '12', null, null, '2016-03-08 18:05:39', '1', '47768bae-e733-4c14-aff4-9ef434eb22c2', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '1', null, null, '2016-03-08 18:05:39', '1', '675dc9d8-bb93-4242-bb6b-0c3c89387ed9', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '22', null, null, '2016-03-08 18:05:39', '1', '7a6076f4-b34c-4417-b422-d85a9692c5aa', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '2', null, null, '2016-03-08 18:05:39', '1', '7f12c4d4-7e14-46de-b039-4703c96a9386', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '80001', null, null, '2016-03-08 18:05:39', '1', '7f6f5047-8c6b-405e-823e-448154bc6823', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '6003', null, null, '2016-03-08 18:05:39', '1', '84dd56fc-6533-4f1f-874c-c6e691de3936', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '1022', null, null, '2016-03-08 18:05:40', '1', 'a1d700a0-793c-41b1-b4a8-70b8291d8b50', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '8', null, null, '2016-03-08 18:05:40', '1', 'b844df55-c341-4c3a-bdcd-c0861da39289', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '0', null, null, '2016-03-08 18:05:40', '1', 'bf7249eb-2f04-41b2-ba8e-b6f08e3fe415', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '53', null, null, '2016-03-08 18:05:40', '1', 'c918cf07-b068-42b0-8812-defd9f255025', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '51', null, null, '2016-03-08 18:05:40', '1', 'cd432167-a801-4d96-8c67-b6d248e8a1db', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '21', null, null, '2016-03-08 18:05:40', '1', 'e28c5b05-b547-4c1b-9d7a-681d184a1274', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '62', null, null, '2016-03-08 18:05:40', '1', 'e8818219-b33f-46bb-9128-a64310038cad', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '52', null, null, '2016-03-08 18:05:40', '1', 'e89c0695-3381-4100-b7ee-45fb2123c809', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '5', null, null, '2016-03-08 18:05:40', '1', 'e95aed79-bad1-48f6-9ec3-b0a1ef969ba0', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '4', null, null, '2016-03-08 18:05:40', '1', 'ef24f3cd-68aa-4671-a0a7-77e6c8a2cc13', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '11', null, null, '2016-03-08 18:05:40', '1', 'f15a4818-3826-4e0b-8750-f1ba1968f55e', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '21', '', '', '2016-03-08 18:05:40', '1', 'ffab0c69-a4bf-467b-a3f9-4b625210d1f6', '2016-01-21 10:07:58');
INSERT INTO `t_auth_role_privilege` VALUES ('admin', '61', null, null, '2016-03-08 18:05:40', '1', 'ffab0c69-a4bf-467b-a3f9-4b625210d1f6', '2016-01-21 10:07:58');

-- ----------------------------
-- Table structure for t_auth_user
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_user`;
CREATE TABLE `t_auth_user` (
  `id` varchar(50) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_login` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `type` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `frozen_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_user
-- ----------------------------
INSERT INTO `t_auth_user` VALUES ('1', 'admin', '343b1c4a3ea721b2d640fc8700db0f36', '2016-04-16 10:17:30', '2016-03-08 18:06:41', '2016-03-08 18:06:41', '343b1c4a3ea721b2d640fc8700db0f36', 'admin', '2016-03-08 18:41:23', '1', '2016-03-08 18:06:41', '1', '15821999948', '0');

-- ----------------------------
-- Table structure for t_auth_user_copy
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_user_copy`;
CREATE TABLE `t_auth_user_copy` (
  `id` varchar(50) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `begin_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `last_login` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(11) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `type` int(11) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `frozen_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_user_copy
-- ----------------------------
INSERT INTO `t_auth_user_copy` VALUES ('1', 'admin', '343b1c4a3ea721b2d640fc8700db0f36', '2016-04-15 09:03:26', '2016-03-08 18:06:41', '2016-03-08 18:06:41', null, 'admin', '2016-03-08 18:41:23', '1', '2016-03-08 18:06:41', '1', '15821999948', '0');

-- ----------------------------
-- Table structure for t_auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_user_role`;
CREATE TABLE `t_auth_user_role` (
  `id` varchar(40) NOT NULL,
  `user_code` varchar(40) DEFAULT NULL,
  `role_code` varchar(40) DEFAULT NULL,
  `create_user` varchar(40) DEFAULT NULL,
  `update_user` varchar(40) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth_user_role
-- ----------------------------
INSERT INTO `t_auth_user_role` VALUES ('75e76a29-4caf-470f-9aa6-9a1bb46fa41c', 'admin', 'admin', 'admin', null, '2016-03-08 18:50:01', '2016-01-20 18:14:41', '1');

-- ----------------------------
-- Table structure for t_common_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_common_dict`;
CREATE TABLE `t_common_dict` (
  `dict_code` varchar(50) DEFAULT NULL,
  `dict_name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT NULL,
  `id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_common_dict
-- ----------------------------
INSERT INTO `t_common_dict` VALUES ('function_type', '功能类型', null, null, 'admin', '2016-03-21 11:41:39', '1', '1', '2016-03-08 18:25:19');
INSERT INTO `t_common_dict` VALUES ('auth_type', '角色类型', null, 'admin', 'admin', '2015-12-14 15:39:39', '0', '6426aa96-3eab-4d37-96bc-49a046d9d189', '2015-12-14 11:50:42');
INSERT INTO `t_common_dict` VALUES ('role_type', '角色类型', null, 'admin', null, '2016-03-16 16:27:51', '0', '973f548c-c674-40b3-8f24-480d81d0e53e', '2015-12-14 15:40:29');

-- ----------------------------
-- Table structure for t_common_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `t_common_dict_value`;
CREATE TABLE `t_common_dict_value` (
  `dict_key` varchar(50) DEFAULT NULL,
  `dict_value` varchar(50) DEFAULT NULL,
  `dict_code` varchar(50) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT NULL,
  `id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_common_dict_value
-- ----------------------------
INSERT INTO `t_common_dict_value` VALUES ('common', '通用', 'role_type', '1', '通用角色', 'admin', null, '2016-03-08 18:23:24', '1', '0f49893e-ade3-442a-a876-0f14681cca4e', '2015-12-14 15:41:13');
INSERT INTO `t_common_dict_value` VALUES ('Y', '菜单', 'function_type', '2', null, null, 'admin', '2015-12-14 11:37:18', '0', '1', '2016-03-08 18:23:24');
INSERT INTO `t_common_dict_value` VALUES ('2', '否', 'tax_point', '2', '', 'admin', null, '2016-03-08 18:23:24', '1', 'cda60ed0-282e-4ff5-ae93-74e61652bfb9', '2015-12-24 20:05:44');
INSERT INTO `t_common_dict_value` VALUES ('1', '是', 'tax_point', '1', '', 'admin', null, '2016-03-08 18:23:24', '1', 'ee6810d6-8474-4f84-bfb8-4eda6f18a5c3', '2015-12-24 20:05:31');

-- ----------------------------
-- Table structure for t_send_log
-- ----------------------------
DROP TABLE IF EXISTS `t_send_log`;
CREATE TABLE `t_send_log` (
  `biz_no` varchar(255) DEFAULT NULL,
  `body` text,
  `biz_type` varchar(255) DEFAULT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_send_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_system_parameter
-- ----------------------------
DROP TABLE IF EXISTS `t_system_parameter`;
CREATE TABLE `t_system_parameter` (
  `id` varchar(50) NOT NULL,
  `sys_key` varchar(50) DEFAULT NULL,
  `sys_value` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` smallint(6) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_parameter
-- ----------------------------
INSERT INTO `t_system_parameter` VALUES ('78d1ea85-4ab3-49a7-b236-29c6b4b46b6d', '122', '1111', '2016-03-09 10:23:59', 'admin', null, '2016-03-09 10:23:58', '1');
INSERT INTO `t_system_parameter` VALUES ('4704a7f3-b895-4548-83cd-33c756df42cd', '12342', '234', '2016-03-14 13:09:48', 'admin', 'admin', '2016-03-14 13:09:44', '1');
INSERT INTO `t_system_parameter` VALUES ('b0d68cd8-2fe6-4897-a82a-5a6d28e675e9', '123123', '1231233123', '2016-03-14 13:09:52', 'admin', null, '2016-03-14 13:09:56', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` double NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_user_login_log`;
CREATE TABLE `t_user_login_log` (
  `user_code` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `login_method` varchar(50) DEFAULT NULL,
  `id` varchar(50) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_login_log
-- ----------------------------
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'f303613f-d0db-413f-bd2b-cd8c82be1dad', '2016-03-08 18:08:02');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'c9529314-6c5f-44ee-a33e-45b092893e93', '2016-03-08 18:14:17');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '3c262030-a3bf-4ede-a7c3-dba3a477ce62', '2016-03-08 18:16:47');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'df89dc57-f9f0-4709-92d5-326303c2d35c', '2016-03-08 18:42:28');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '890f0106-1f00-45bb-b82f-2475e81870d3', '2016-03-08 18:43:48');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '7edd3ffe-bc8c-4531-9837-53f98031e3f3', '2016-03-08 18:46:23');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'b5e2560b-a036-4cef-af29-2fbbb4f89f82', '2016-03-08 18:49:06');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'ffd2809d-843a-4e9d-ac4f-9df6fc717448', '2016-03-08 18:49:15');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '22c35ed9-53b4-431b-b872-030f13f3cfef', '2016-03-08 19:02:21');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '043cd416-630e-4f42-bad5-6bebf9e1040d', '2016-03-08 19:34:25');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'd4372982-5eae-425a-b757-07c5dd6d9509', '2016-03-09 08:52:58');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'f822b1a2-50d5-42a3-976e-6760c26d3c24', '2016-03-09 09:01:01');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'bc0b2599-1864-4a27-8e56-b42926370e2f', '2016-03-09 09:04:24');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'ed3d8577-02d6-4a04-8eaf-cfd5c747edf9', '2016-03-09 09:07:29');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'adc5ec21-acc1-483a-b059-69487e8f0457', '2016-03-09 09:09:59');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'f054cb5b-0c49-4ef0-8bf5-8186978a81d8', '2016-03-09 09:10:40');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'e4f356c0-b249-4b9d-98b8-d3c92a1622cd', '2016-03-09 09:15:31');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '15cdad5f-89a5-4d40-8b84-80440f84bf6e', '2016-03-09 09:19:07');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '2f760a7f-7ef8-4a3c-90c2-dadacb2215d9', '2016-03-09 09:19:22');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '77422591-f62b-4c86-b1f4-4dbf001f0354', '2016-03-09 09:23:53');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '8d4da0d6-e124-41bd-844f-6b9c3a2c77ab', '2016-03-09 09:24:01');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '5485d101-21e5-4f0c-a9e8-631e01cdae57', '2016-03-09 09:25:36');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '4fbe9c94-a425-4e2c-8dd6-cfcf24bc30c1', '2016-03-09 09:40:24');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '6a3b1269-3d3e-48f6-b869-dc35c5d315d9', '2016-03-09 09:43:24');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '0f12d1e4-fab7-497c-99b4-bbcf0c186732', '2016-03-09 09:47:40');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '532e4aa2-1f3f-4757-8ad2-d53c2d5cca95', '2016-03-09 09:49:33');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '4e654eb4-df22-4963-802c-bee467a06e03', '2016-03-09 10:02:29');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'f6c8cc68-c0f0-4c75-a4b2-e031c7a90ad9', '2016-03-09 10:05:19');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '212b32b8-9b57-416e-b3dc-37f569961762', '2016-03-09 10:11:33');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '6490d868-1551-4b89-aae8-06d3e8f70e0f', '2016-03-09 10:21:33');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '9c9fefc8-fa33-4db7-8dc9-9df683afbc88', '2016-03-09 10:51:47');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'c1c19760-c016-4743-bc0f-039b34143d7f', '2016-03-09 11:57:49');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'd11ccdc2-e5ff-4cec-8f6f-f761d58f89e3', '2016-03-09 12:39:14');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'd5ca5063-56c4-4663-828d-ba9be7899564', '2016-03-09 14:58:28');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '22149cda-4e02-41bf-a0a5-5f56543c5975', '2016-03-09 15:33:17');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '4c23b66f-123e-4bb7-a374-d908e89cd8a0', '2016-03-09 16:04:07');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'f95d7d2f-7223-4720-b2f8-e0914140c0c3', '2016-03-11 14:40:30');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '58b0b4a4-c36b-4723-835f-1435950f256a', '2016-03-12 15:01:42');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '2bcd3356-59da-4747-8981-079e30cf2027', '2016-03-12 15:52:07');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '620d6473-3608-466a-a69d-1c01387b04a7', '2016-03-12 15:52:07');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'c6d233ea-5598-4e70-8683-2d682aa32eff', '2016-03-14 11:53:57');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '05ace48f-6100-455a-9fb1-44a02848cc56', '2016-03-14 11:54:08');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '16bfd59b-f68c-4392-965e-addf5279f2d6', '2016-03-14 13:09:38');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '63c93544-9cf0-4484-9a10-d718035646e6', '2016-03-15 13:24:11');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'bc06f8c5-9085-43d6-babb-41c0d2eec688', '2016-03-15 17:56:13');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '6200fb34-d1ec-48eb-a098-e7ff929987ea', '2016-03-15 18:18:28');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '7a6a4955-6300-4551-a02e-c4e4201ced4f', '2016-03-15 18:22:49');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '8498578b-895a-450d-9137-8feb9dabf3da', '2016-03-15 18:22:55');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '79b226b5-699f-4804-81ca-dd306b7578b9', '2016-03-16 10:07:00');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'eb643012-e3cc-49c3-b2be-2e8a656526c6', '2016-03-16 15:44:15');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'ce6e5e9e-a813-457f-9a60-3261832d7e44', '2016-03-17 09:37:11');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'c75a7eb4-5404-46e3-ade7-c79384587c7b', '2016-03-17 11:45:03');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '2137f97d-d91d-4f8a-a817-6fefc8745104', '2016-03-17 11:54:00');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '27893905-d5da-469f-8c99-154e6e56eda4', '2016-03-17 13:28:47');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '16fa4a85-0bee-44d5-965c-8bbd9183df31', '2016-03-17 13:34:58');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '41c58da8-99af-4b82-bff0-17639d72b74c', '2016-03-17 13:35:56');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'ef00305a-1faf-4171-9837-bbada635b80b', '2016-03-17 13:36:18');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'd33d6ac2-a4fe-43c0-8784-442282c27495', '2016-03-17 13:41:31');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '430bb7ba-9c15-4b45-9d9c-a196346c962f', '2016-03-17 13:45:09');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '3d04e457-fc20-4b18-85d4-46a31fdb4024', '2016-03-17 13:54:21');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'd5f90bbb-892d-4f71-8e0c-13ae0b2cc8d5', '2016-03-17 14:01:56');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '99e37282-cc48-4396-b64b-c0021cff5841', '2016-03-17 14:05:19');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '77213a6d-d7ec-4b21-b2f2-a7ee2b53f536', '2016-03-17 14:08:05');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '13eaa946-3fd1-4239-968b-3f71ce09cc7d', '2016-03-17 14:13:15');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '5f4bbc75-636c-44f5-8653-6c9c41ab72f9', '2016-03-17 14:26:24');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'd989b6a7-362b-453e-b572-1ad95284401a', '2016-03-17 16:17:46');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '232c922d-b4cd-4c6b-b8ca-cc9b10479928', '2016-03-17 18:44:28');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '01c5ad74-9f36-4dcf-88c4-d6f62a9672a6', '2016-03-17 19:02:50');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '2b078b63-4402-4e52-aa66-33992ecde29a', '2016-03-17 19:30:56');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'c078620c-5f09-48c3-ae3a-f9c08bf49013', '2016-03-17 19:32:54');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '7cb6a613-bd8e-48ec-b6b2-cac334b9aad7', '2016-03-18 09:08:50');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.36', 'pc', 'e0d0b1c8-ae62-48cc-8e2f-d74ddadd2c4a', '2016-03-18 09:29:30');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.36', 'pc', 'cca9d579-83ef-4cbb-87d7-d6c23aaef6cc', '2016-03-18 10:08:35');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '15445180-b026-447d-97d3-51d0ea599bfd', '2016-03-18 10:20:46');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '53b0ba68-ce7f-4b08-9426-63d87cbe4dd7', '2016-03-18 10:51:08');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '146e445d-74a7-409f-b0aa-99f38150bebc', '2016-03-18 10:54:57');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'ada5f514-0830-4ea3-b889-3183b8f1e9be', '2016-03-18 12:58:10');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '959c89fe-eb5f-4031-a788-6218f6d8ecdd', '2016-03-18 14:46:36');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'f0347ef7-1fc6-46b1-a4b5-50cd84bc1fa7', '2016-03-18 15:16:38');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '788b87b0-bfd7-49c4-9675-41850ee7a121', '2016-03-18 16:18:18');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '8bf48ceb-c445-46e1-bb43-7aba7dfe9957', '2016-03-18 16:28:22');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.36', 'pc', 'a7426a34-695d-412e-a747-fc404aaa608d', '2016-03-18 16:49:04');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'e3448155-9a8a-46fd-afed-e2c52243534c', '2016-03-18 16:50:46');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '34c82019-0fb3-405e-9bed-548268b41329', '2016-03-18 17:20:22');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '9d5bdd4b-9d36-4f8c-9c18-9607fd134e32', '2016-03-18 17:53:50');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '1f8ca039-3987-443d-9c80-648135153753', '2016-03-18 17:53:50');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'a727b9b5-b1e7-4fdc-a566-2ed503aef924', '2016-03-21 08:20:57');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '1547c646-a0a5-4d6f-9dab-7936a925838a', '2016-03-21 08:23:23');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'c71cb7ac-a111-4b7f-a1e5-0300148bf6bd', '2016-03-21 08:30:50');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '377865dc-8abe-4846-b94e-9ffa2d4e9d05', '2016-03-21 09:19:48');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '4b04b70c-5714-4059-ba7a-ff3f76054d26', '2016-03-21 09:22:52');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '2f35daf9-d0c8-470c-8e06-c75eaea1609b', '2016-03-21 10:08:44');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.56.1', 'pc', '319385aa-dc40-471c-8fd7-475700a8633f', '2016-03-21 11:21:29');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.56.1', 'pc', '94f30b29-7a39-4f14-82a9-241594308bd9', '2016-03-21 11:24:53');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'e3581860-85c7-415c-9d2c-41bab006d35c', '2016-03-21 11:29:15');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '804f8696-36d4-4aa2-bf9b-40dc98ab217b', '2016-03-21 11:37:54');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.56.1', 'pc', 'a46b1b92-170f-4445-9881-b5e3738bd3df', '2016-03-21 11:39:01');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'b1440a6e-68af-4180-98a5-e7f8d20e66e6', '2016-03-21 11:51:00');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '1eb263de-9d68-4954-8e6b-69bc2951568c', '2016-03-21 11:52:26');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '7dedb006-57e0-4f3f-8123-1fa519e89af8', '2016-03-21 11:54:43');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '010fae74-aca3-47f9-8ced-f29da899545f', '2016-03-21 12:30:27');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '7c960c84-be4f-4c3f-b96d-b6119096c26d', '2016-03-21 12:32:20');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '77ff9c5b-83db-4208-9d4f-cacc9c0aacbd', '2016-03-21 13:30:19');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', '608679bd-cac4-49a9-b394-f9032887aa02', '2016-03-21 13:31:53');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'bfadcfdc-3c5e-42b4-ab56-aecc91d6d75f', '2016-03-21 13:35:27');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.30.24', 'pc', 'd4863f22-86e1-4fd5-bb6b-7430dab28a8b', '2016-03-21 13:49:17');
INSERT INTO `t_user_login_log` VALUES ('admin', '192.168.56.1', 'pc', 'cdf00490-b041-4be0-8c0d-bc5af15d7f33', '2016-03-21 14:01:53');
