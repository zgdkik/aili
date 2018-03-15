/*
Navicat PGSQL Data Transfer

Source Server         : 192.168.20.195
Source Server Version : 90405
Source Host           : 192.168.20.195:1111
Source Database       : logistics2
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90405
File Encoding         : 65001

Date: 2015-12-09 16:46:25
*/


-- ----------------------------
-- Table structure for t_sds_auth
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_auth";
CREATE TABLE "public"."t_sds_auth" (
"id" varchar(50) COLLATE "default" DEFAULT 1 NOT NULL,
"function_code" varchar(255) COLLATE "default",
"function_name" varchar(1000) COLLATE "default",
"uri" varchar(255) COLLATE "default",
"parent_code" varchar(50) COLLATE "default",
"active" varchar(255) COLLATE "default",
"display_order" varchar(255) COLLATE "default",
"checkable" varchar(255) COLLATE "default",
"function_type" varchar(255) COLLATE "default",
"leaf" varchar(255) COLLATE "default",
"icon_cls" varchar(255) COLLATE "default",
"cls" varchar(255) COLLATE "default",
"function_desc" varchar(2000) COLLATE "default",
"create_user" varchar(255) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(255) COLLATE "default",
"update_time" timestamp(6),
"status" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_auth"."function_code" IS '功能编码';
COMMENT ON COLUMN "public"."t_sds_auth"."function_name" IS '功能名称';
COMMENT ON COLUMN "public"."t_sds_auth"."uri" IS '功能入口URI';
COMMENT ON COLUMN "public"."t_sds_auth"."parent_code" IS '父功能';
COMMENT ON COLUMN "public"."t_sds_auth"."active" IS '是否有效';
COMMENT ON COLUMN "public"."t_sds_auth"."display_order" IS '显示顺序';
COMMENT ON COLUMN "public"."t_sds_auth"."checkable" IS '是否权限检查';
COMMENT ON COLUMN "public"."t_sds_auth"."function_type" IS '功能类型';
COMMENT ON COLUMN "public"."t_sds_auth"."leaf" IS '是否叶子节点';
COMMENT ON COLUMN "public"."t_sds_auth"."icon_cls" IS '图标的CSS样式';
COMMENT ON COLUMN "public"."t_sds_auth"."cls" IS '节点的CSS样式';
COMMENT ON COLUMN "public"."t_sds_auth"."function_desc" IS '功能描述';

-- ----------------------------
-- Records of t_sds_auth
-- ----------------------------

INSERT INTO "public"."t_sds_auth" VALUES ('4249ffd3-a537-4046-a96f-8ff3781ebdd4', '0', '应用系统', '', '', 'Y', '1', '', 'Y', '', '', '', '', null, null, null, null, '1');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
