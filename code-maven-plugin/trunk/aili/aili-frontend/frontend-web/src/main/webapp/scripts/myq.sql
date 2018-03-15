/*
Navicat PGSQL Data Transfer

Source Server         : sds-prd
Source Server Version : 90401
Source Host           : rds9q864jg8omgvp916co.pg.rds.aliyuncs.com:3433
Source Database       : sds
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90401
File Encoding         : 65001

Date: 2016-01-15 17:37:23
*/


-- ----------------------------
-- Sequence structure for seq_id_sys_field
-- ----------------------------
DROP SEQUENCE "public"."seq_id_sys_field";
CREATE SEQUENCE "public"."seq_id_sys_field"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 999999999
 START 439
 CACHE 1;
SELECT setval('"public"."seq_id_sys_field"', 439, true);

-- ----------------------------
-- Sequence structure for seq_id_sys_field_employeeuser
-- ----------------------------
DROP SEQUENCE "public"."seq_id_sys_field_employeeuser";
CREATE SEQUENCE "public"."seq_id_sys_field_employeeuser"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9999999999
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for seq_id_sys_menu
-- ----------------------------
DROP SEQUENCE "public"."seq_id_sys_menu";
CREATE SEQUENCE "public"."seq_id_sys_menu"
 INCREMENT 1
 MINVALUE 0
 MAXVALUE 99999
 START 16
 CACHE 1;
SELECT setval('"public"."seq_id_sys_menu"', 16, true);

-- ----------------------------
-- Sequence structure for seq_id_sys_menu_employeeuser
-- ----------------------------
DROP SEQUENCE "public"."seq_id_sys_menu_employeeuser";
CREATE SEQUENCE "public"."seq_id_sys_menu_employeeuser"
 INCREMENT 1
 MINVALUE 0
 MAXVALUE 999999
 START 635
 CACHE 1;
SELECT setval('"public"."seq_id_sys_menu_employeeuser"', 635, true);

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_blob_triggers";
CREATE TABLE "public"."qrtz_blob_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"blob_data" bytea
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_calendars";
CREATE TABLE "public"."qrtz_calendars" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"calendar_name" varchar(200) COLLATE "default" NOT NULL,
"calendar" bytea NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_cron_triggers";
CREATE TABLE "public"."qrtz_cron_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"cron_expression" varchar(120) COLLATE "default" NOT NULL,
"time_zone_id" varchar(80) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_fired_triggers";
CREATE TABLE "public"."qrtz_fired_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"entry_id" varchar(95) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"instance_name" varchar(200) COLLATE "default" NOT NULL,
"fired_time" int8 NOT NULL,
"priority" int4 NOT NULL,
"state" varchar(16) COLLATE "default" NOT NULL,
"job_name" varchar(200) COLLATE "default",
"job_group" varchar(200) COLLATE "default",
"is_nonconcurrent" bool,
"requests_recovery" bool,
"sched_time" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_job_details";
CREATE TABLE "public"."qrtz_job_details" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"job_name" varchar(200) COLLATE "default" NOT NULL,
"job_group" varchar(200) COLLATE "default" NOT NULL,
"description" varchar(250) COLLATE "default",
"job_class_name" varchar(250) COLLATE "default" NOT NULL,
"is_durable" bool NOT NULL,
"is_nonconcurrent" bool NOT NULL,
"is_update_data" bool NOT NULL,
"requests_recovery" bool NOT NULL,
"job_data" bytea
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_locks";
CREATE TABLE "public"."qrtz_locks" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"lock_name" varchar(40) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_paused_trigger_grps";
CREATE TABLE "public"."qrtz_paused_trigger_grps" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_scheduler_state";
CREATE TABLE "public"."qrtz_scheduler_state" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"instance_name" varchar(200) COLLATE "default" NOT NULL,
"last_checkin_time" int8 NOT NULL,
"checkin_interval" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_simple_triggers";
CREATE TABLE "public"."qrtz_simple_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"repeat_count" int8 NOT NULL,
"repeat_interval" int8 NOT NULL,
"times_triggered" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_simprop_triggers";
CREATE TABLE "public"."qrtz_simprop_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"str_prop_1" varchar(512) COLLATE "default",
"str_prop_2" varchar(512) COLLATE "default",
"str_prop_3" varchar(512) COLLATE "default",
"int_prop_1" int4,
"int_prop_2" int4,
"long_prop_1" int8,
"long_prop_2" int8,
"dec_prop_1" numeric(13,4),
"dec_prop_2" numeric(13,4),
"bool_prop_1" bool,
"bool_prop_2" bool
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_triggers";
CREATE TABLE "public"."qrtz_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"job_name" varchar(200) COLLATE "default" NOT NULL,
"job_group" varchar(200) COLLATE "default" NOT NULL,
"description" varchar(250) COLLATE "default",
"next_fire_time" int8,
"prev_fire_time" int8,
"priority" int4,
"trigger_state" varchar(16) COLLATE "default" NOT NULL,
"trigger_type" varchar(8) COLLATE "default" NOT NULL,
"start_time" int8 NOT NULL,
"end_time" int8,
"calendar_name" varchar(200) COLLATE "default",
"misfire_instr" int2,
"job_data" bytea
)
WITH (OIDS=FALSE)

;

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
-- Table structure for t_sds_billingsolution
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_billingsolution";
CREATE TABLE "public"."t_sds_billingsolution" (
"id" varchar(40) COLLATE "default" NOT NULL,
"solution_title" varchar(30) COLLATE "default",
"customer_desc" text COLLATE "default",
"trucker_desc" text COLLATE "default",
"enterprise_id" varchar(40) COLLATE "default",
"enterprise_account" varchar(30) COLLATE "default",
"status" int4,
"create_user" varchar(20) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(20) COLLATE "default",
"update_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_billingsolution"."id" IS '主键';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."solution_title" IS '方案名称';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."customer_desc" IS '客户描述';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."trucker_desc" IS '司机描述';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."enterprise_id" IS '主账号ID';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."enterprise_account" IS '主账号';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."status" IS '1:正常2:停用';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."t_sds_billingsolution"."update_time" IS '修改时间';

-- ----------------------------
-- Table structure for t_sds_carplan
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_carplan";
CREATE TABLE "public"."t_sds_carplan" (
"id" varchar(50) COLLATE "default" NOT NULL,
"lineid" varchar(50) COLLATE "default",
"usecartime1" varchar(50) COLLATE "default",
"truckeruserid" varchar(50) COLLATE "default",
"create_user" varchar(50) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"carplanstatus" int4,
"usecartime2" varchar(50) COLLATE "default",
"subaccount" varchar(50) COLLATE "default",
"repertory_id" varchar(50) COLLATE "default",
"delivery_area" varchar(255) COLLATE "default",
"carplan_date" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_carplan"."lineid" IS '线路id';
COMMENT ON COLUMN "public"."t_sds_carplan"."usecartime1" IS '用车时间';
COMMENT ON COLUMN "public"."t_sds_carplan"."truckeruserid" IS '司机id';
COMMENT ON COLUMN "public"."t_sds_carplan"."status" IS '1正常生成   0补单';
COMMENT ON COLUMN "public"."t_sds_carplan"."carplanstatus" IS '用车计划状态  已派单:1   已签到:2   已完成:3   已取消:4   空跑:5   缺司机(请假):6     修改线路信息改已生成没跑的计划:7      停用线路取消未跑的计划:8   超过签到时间司机未签到,未服务:9';

-- ----------------------------
-- Table structure for t_sds_carplanrecord
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_carplanrecord";
CREATE TABLE "public"."t_sds_carplanrecord" (
"id" varchar(50) COLLATE "default" NOT NULL,
"enterprise_id" varchar(50) COLLATE "default",
"enterprise_account" varchar(50) COLLATE "default",
"subenterprise_id" varchar(50) COLLATE "default",
"subenterprise_account" varchar(50) COLLATE "default",
"trucker_id" varchar(50) COLLATE "default",
"line_id" varchar(50) COLLATE "default",
"carplan_id" varchar(50) COLLATE "default",
"city" varchar(20) COLLATE "default",
"customer_cost" float8,
"trucker_cost" float8,
"status" int4,
"create_user" varchar(20) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(20) COLLATE "default",
"update_time" timestamp(6),
"remark" varchar(50) COLLATE "default",
"repertory_id" varchar(50) COLLATE "default",
"demand_id" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."t_sds_carplanrecord" IS '用车记录表';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."id" IS '主键';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."subenterprise_account" IS '子账户账号';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."trucker_id" IS '司机ID';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."carplan_id" IS '用车计划ID';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."city" IS '城市';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."customer_cost" IS '客户费用';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."trucker_cost" IS '司机费用';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."status" IS '1:正常
3:删除

';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."remark" IS '备注';
COMMENT ON COLUMN "public"."t_sds_carplanrecord"."repertory_id" IS '仓库ID';

-- ----------------------------
-- Table structure for t_sds_chargingplan
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_chargingplan";
CREATE TABLE "public"."t_sds_chargingplan" (
"id" varchar(50) COLLATE "default",
"account_id" varchar(50) COLLATE "default",
"truck_type" varchar(15) COLLATE "default",
"custmomer_rule" varchar(40) COLLATE "default",
"truckuser_rule" varchar(40) COLLATE "default",
"status" int4,
"create_user" varchar(30) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(30) COLLATE "default",
"update_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_city
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_city";
CREATE TABLE "public"."t_sds_city" (
"id" int2 NOT NULL,
"name" varchar(50) COLLATE "default" NOT NULL,
"parent_id" int2,
"parent_name" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"type" int2
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_cityrelationship
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_cityrelationship";
CREATE TABLE "public"."t_sds_cityrelationship" (
"id" int2 NOT NULL,
"name" varchar(255) COLLATE "default" NOT NULL,
"partent_id" int2,
"partent_name" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"type" int2,
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_cs
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_cs";
CREATE TABLE "public"."t_sds_cs" (
"city" varchar(255) COLLATE "default",
"work_code" varchar(255) COLLATE "default",
"phone" varchar(255) COLLATE "default",
"descp" varchar(255) COLLATE "default",
"create_user" varchar(255) COLLATE "default",
"update_user" varchar(255) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"id" varchar(255) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_cs"."city" IS '城市';
COMMENT ON COLUMN "public"."t_sds_cs"."work_code" IS '工号';
COMMENT ON COLUMN "public"."t_sds_cs"."phone" IS '电话';
COMMENT ON COLUMN "public"."t_sds_cs"."descp" IS '备注';

-- ----------------------------
-- Table structure for t_sds_customer_bill
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_customer_bill";
CREATE TABLE "public"."t_sds_customer_bill" (
"id" varchar(50) COLLATE "default" NOT NULL,
"customer_id" varchar(50) COLLATE "default",
"car_record_id" varchar(50) COLLATE "default",
"freight" money,
"create_user" varchar(50) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"status" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_customer_bill"."id" IS '主键ID';
COMMENT ON COLUMN "public"."t_sds_customer_bill"."customer_id" IS '客户ID';
COMMENT ON COLUMN "public"."t_sds_customer_bill"."car_record_id" IS '用车记录ID';
COMMENT ON COLUMN "public"."t_sds_customer_bill"."freight" IS '运费';
COMMENT ON COLUMN "public"."t_sds_customer_bill"."status" IS '状态(1-未支付；0-已支付)';

-- ----------------------------
-- Table structure for t_sds_demand
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_demand";
CREATE TABLE "public"."t_sds_demand" (
"id" varchar(50) COLLATE "default" NOT NULL,
"subenterprise_accouont" varchar(255) COLLATE "default",
"route_type" int4,
"create_user" varchar(255) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(255) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"repertory_id" varchar(50) COLLATE "default",
"district" text COLLATE "default",
"distribution1" int4,
"distribution2" int4,
"distance" float8,
"start_date" timestamp(6),
"dispatch_date1" varchar(255) COLLATE "default",
"dispatch_date2" varchar(255) COLLATE "default",
"car_type" varchar(255) COLLATE "default",
"car_number" int4,
"rest_require" varchar(255) COLLATE "default",
"rest_explain" text COLLATE "default",
"billing_criterion_id" varchar(50) COLLATE "default",
"hear_status" int4,
"recruit_status" int4,
"refuse_reason" text COLLATE "default",
"if_release" int4 DEFAULT 0,
"release_time" timestamp(6),
"predict_cost" varchar(255) COLLATE "default",
"weekday" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_demand"."id" IS '主键ID';
COMMENT ON COLUMN "public"."t_sds_demand"."subenterprise_accouont" IS '客户账号';
COMMENT ON COLUMN "public"."t_sds_demand"."route_type" IS '路线类型(1-长期;2-短期)';
COMMENT ON COLUMN "public"."t_sds_demand"."repertory_id" IS '仓库ID';
COMMENT ON COLUMN "public"."t_sds_demand"."district" IS '配送区域';
COMMENT ON COLUMN "public"."t_sds_demand"."distribution1" IS '配送点个数';
COMMENT ON COLUMN "public"."t_sds_demand"."distribution2" IS '配送点个数';
COMMENT ON COLUMN "public"."t_sds_demand"."distance" IS '配送里程';
COMMENT ON COLUMN "public"."t_sds_demand"."start_date" IS '开始时间';
COMMENT ON COLUMN "public"."t_sds_demand"."dispatch_date1" IS '配送时间1';
COMMENT ON COLUMN "public"."t_sds_demand"."dispatch_date2" IS '配送时间2';
COMMENT ON COLUMN "public"."t_sds_demand"."car_type" IS '车型';
COMMENT ON COLUMN "public"."t_sds_demand"."car_number" IS '用车数量';
COMMENT ON COLUMN "public"."t_sds_demand"."rest_require" IS '其他要求';
COMMENT ON COLUMN "public"."t_sds_demand"."rest_explain" IS '其他说明';
COMMENT ON COLUMN "public"."t_sds_demand"."billing_criterion_id" IS '计费标准ID';
COMMENT ON COLUMN "public"."t_sds_demand"."hear_status" IS '受理状态（0-待审核；1-已审核；2-已拒绝）';
COMMENT ON COLUMN "public"."t_sds_demand"."recruit_status" IS '招募状态（0-无;1-招募中；2-已招满；3-已停止）';
COMMENT ON COLUMN "public"."t_sds_demand"."refuse_reason" IS '拒绝理由';
COMMENT ON COLUMN "public"."t_sds_demand"."if_release" IS '是否点击发布(1-是；0-否)';
COMMENT ON COLUMN "public"."t_sds_demand"."release_time" IS '发布时间';
COMMENT ON COLUMN "public"."t_sds_demand"."predict_cost" IS '预计费用';
COMMENT ON COLUMN "public"."t_sds_demand"."weekday" IS '每周配送时间,1,2,3,4,5,6,7 对应一周7天';

-- ----------------------------
-- Table structure for t_sds_demand_driver
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_demand_driver";
CREATE TABLE "public"."t_sds_demand_driver" (
"demand_id" varchar(255) COLLATE "default",
"driver_id" varchar(255) COLLATE "default",
"id" varchar(255) COLLATE "default" NOT NULL,
"create_time" timestamp(6),
"read" int2
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_demand_driver"."demand_id" IS '需求id';
COMMENT ON COLUMN "public"."t_sds_demand_driver"."driver_id" IS '司机id';
COMMENT ON COLUMN "public"."t_sds_demand_driver"."id" IS '唯一标识';
COMMENT ON COLUMN "public"."t_sds_demand_driver"."create_time" IS '创建时间';

-- ----------------------------
-- Table structure for t_sds_dict
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_dict";
CREATE TABLE "public"."t_sds_dict" (
"dict_code" varchar(50) COLLATE "default",
"dict_name" varchar(255) COLLATE "default",
"remark" varchar(255) COLLATE "default",
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(255) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"id" varchar(50) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_dict_value
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_dict_value";
CREATE TABLE "public"."t_sds_dict_value" (
"key" varchar(50) COLLATE "default",
"value" varchar(50) COLLATE "default",
"dict_code" varchar(50) COLLATE "default",
"order_no" int4,
"remark" varchar(255) COLLATE "default",
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"id" varchar(50) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_driver
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_driver";
CREATE TABLE "public"."t_sds_driver" (
"direct_driver_id" varchar(100) COLLATE "default",
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"id" varchar(255) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_driver_apply
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_driver_apply";
CREATE TABLE "public"."t_sds_driver_apply" (
"id" varchar(50) COLLATE "default" NOT NULL,
"demand_id" varchar(50) COLLATE "default",
"truckeruser_id" varchar(50) COLLATE "default",
"distance" float8,
"vehicle_age" varchar(255) COLLATE "default",
"driving_years" varchar(255) COLLATE "default",
"reply_content" text COLLATE "default",
"apply_status" int4,
"refuse_reason" text COLLATE "default",
"create_user" varchar(255) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(255) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"subenterprise_account" varchar(255) COLLATE "default",
"often_address" text COLLATE "default",
"lng" varchar(255) COLLATE "default",
"lat" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_driver_apply"."demand_id" IS '需求ID';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."truckeruser_id" IS '司机ID';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."distance" IS '距离';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."vehicle_age" IS '车龄';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."driving_years" IS '驾龄';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."reply_content" IS '回复内容(司机优势)';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."apply_status" IS '报名状态(1-报名中；2-已录用；3-已储备；4-不通过)';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."refuse_reason" IS '拒绝原因';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."subenterprise_account" IS '子账号';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."often_address" IS '常用地址';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."lng" IS '经度';
COMMENT ON COLUMN "public"."t_sds_driver_apply"."lat" IS '纬度';

-- ----------------------------
-- Table structure for t_sds_driver_bill
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_driver_bill";
CREATE TABLE "public"."t_sds_driver_bill" (
"id" varchar(50) COLLATE "default" NOT NULL,
"driver_id" varchar(50) COLLATE "default",
"car_record_id" varchar(50) COLLATE "default",
"freight" money,
"create_user" varchar(50) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"status" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_driver_bill"."id" IS '主键ID';
COMMENT ON COLUMN "public"."t_sds_driver_bill"."driver_id" IS '司机ID';
COMMENT ON COLUMN "public"."t_sds_driver_bill"."car_record_id" IS '用车记录ID';
COMMENT ON COLUMN "public"."t_sds_driver_bill"."freight" IS '运费';
COMMENT ON COLUMN "public"."t_sds_driver_bill"."status" IS '状态(1-未收款；0-已收款)';

-- ----------------------------
-- Table structure for t_sds_driver_reserve
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_driver_reserve";
CREATE TABLE "public"."t_sds_driver_reserve" (
"id" varchar(50) COLLATE "default" NOT NULL,
"truckeruser_id" varchar(50) COLLATE "default",
"subenterprise_account" varchar(255) COLLATE "default",
"create_user" varchar(255) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(255) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"reserve_status" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_driver_reserve"."truckeruser_id" IS '直达司机ID';
COMMENT ON COLUMN "public"."t_sds_driver_reserve"."subenterprise_account" IS '客户账号';
COMMENT ON COLUMN "public"."t_sds_driver_reserve"."reserve_status" IS '储备状态（1-服务中；2-未服务；3-储备中）';

-- ----------------------------
-- Table structure for t_sds_enterprise
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_enterprise";
CREATE TABLE "public"."t_sds_enterprise" (
"id" varchar(50) COLLATE "default" NOT NULL,
"enterprise_name" varchar(255) COLLATE "default",
"enterprise_account" varchar(255) COLLATE "default",
"city" varchar(50) COLLATE "default",
"goods_type" varchar(50) COLLATE "default",
"contact_name" varchar(255) COLLATE "default",
"contact_phone" varchar(50) COLLATE "default",
"email" varchar(255) COLLATE "default",
"emp_id" varchar(50) COLLATE "default",
"emp_name" varchar(50) COLLATE "default",
"bank" varchar(50) COLLATE "default",
"bank_name" varchar(255) COLLATE "default",
"bank_number" varchar(255) COLLATE "default",
"ali_account" varchar(50) COLLATE "default",
"emp_phone" varchar(50) COLLATE "default",
"img_business" varchar(100) COLLATE "default",
"img_tax" varchar(100) COLLATE "default",
"img_service" varchar(100) COLLATE "default",
"status" int4,
"create_user" varchar(50) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(20) COLLATE "default",
"update_time" timestamp(6),
"frozen_status" int4,
"customer_industry" varchar(255) COLLATE "default",
"receipt_type" varchar(30) COLLATE "default",
"cooperate_begin" timestamp(6),
"cooperate_end" timestamp(6),
"balance_period" varchar(2) COLLATE "default",
"tax_point" int4,
"company" varchar(255) COLLATE "default",
"company_address" varchar(255) COLLATE "default",
"img_bargain" varchar(100) COLLATE "default",
"img_taxpayer" varchar(100) COLLATE "default",
"img_confirmation" varchar(100) COLLATE "default",
"img_account" varchar(100) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_enterprise"."id" IS '主键';
COMMENT ON COLUMN "public"."t_sds_enterprise"."enterprise_name" IS '企业名称';
COMMENT ON COLUMN "public"."t_sds_enterprise"."enterprise_account" IS '账号';
COMMENT ON COLUMN "public"."t_sds_enterprise"."city" IS '城市';
COMMENT ON COLUMN "public"."t_sds_enterprise"."goods_type" IS '运输类型';
COMMENT ON COLUMN "public"."t_sds_enterprise"."contact_name" IS '联系人';
COMMENT ON COLUMN "public"."t_sds_enterprise"."contact_phone" IS '联系电话';
COMMENT ON COLUMN "public"."t_sds_enterprise"."email" IS '邮箱';
COMMENT ON COLUMN "public"."t_sds_enterprise"."emp_id" IS '客户经理工号';
COMMENT ON COLUMN "public"."t_sds_enterprise"."emp_name" IS '客户经理姓名';
COMMENT ON COLUMN "public"."t_sds_enterprise"."bank" IS '银行';
COMMENT ON COLUMN "public"."t_sds_enterprise"."bank_name" IS '开户名';
COMMENT ON COLUMN "public"."t_sds_enterprise"."bank_number" IS '银行卡号';
COMMENT ON COLUMN "public"."t_sds_enterprise"."ali_account" IS '支付宝账号';
COMMENT ON COLUMN "public"."t_sds_enterprise"."emp_phone" IS '客户经理联系电话';
COMMENT ON COLUMN "public"."t_sds_enterprise"."img_business" IS '工商证';
COMMENT ON COLUMN "public"."t_sds_enterprise"."img_tax" IS '税务证';
COMMENT ON COLUMN "public"."t_sds_enterprise"."img_service" IS '营业证';
COMMENT ON COLUMN "public"."t_sds_enterprise"."status" IS '1:正常3:删除4:冻结';
COMMENT ON COLUMN "public"."t_sds_enterprise"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."t_sds_enterprise"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sds_enterprise"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."t_sds_enterprise"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."t_sds_enterprise"."frozen_status" IS '1正常4冻结';
COMMENT ON COLUMN "public"."t_sds_enterprise"."customer_industry" IS '客户行业';
COMMENT ON COLUMN "public"."t_sds_enterprise"."receipt_type" IS '发票类型';
COMMENT ON COLUMN "public"."t_sds_enterprise"."cooperate_begin" IS '合作开始时间';
COMMENT ON COLUMN "public"."t_sds_enterprise"."cooperate_end" IS '合作结束时间';
COMMENT ON COLUMN "public"."t_sds_enterprise"."balance_period" IS '结算周期';
COMMENT ON COLUMN "public"."t_sds_enterprise"."tax_point" IS '是否承担税点;1 是；2 否 ';
COMMENT ON COLUMN "public"."t_sds_enterprise"."company" IS '公司全称';
COMMENT ON COLUMN "public"."t_sds_enterprise"."company_address" IS '公司地址';
COMMENT ON COLUMN "public"."t_sds_enterprise"."img_bargain" IS '合同';
COMMENT ON COLUMN "public"."t_sds_enterprise"."img_taxpayer" IS '一般纳税人';
COMMENT ON COLUMN "public"."t_sds_enterprise"."img_confirmation" IS '开票确认函';
COMMENT ON COLUMN "public"."t_sds_enterprise"."img_account" IS '开户许可证';

-- ----------------------------
-- Table structure for t_sds_line
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_line";
CREATE TABLE "public"."t_sds_line" (
"id" varchar(255) COLLATE "default" NOT NULL,
"lineid" varchar(255) COLLATE "default",
"linename" varchar(255) COLLATE "default",
"linetype" int4,
"subaccount" varchar(255) COLLATE "default",
"repertory_address" varchar(255) COLLATE "default",
"delivery_area" varchar(255) COLLATE "default",
"delivery_point_1" int4,
"delivery_point_2" int4,
"delivery_km" float8,
"delivery_date_start" timestamp(6),
"delivery_time_everyday" varchar(255) COLLATE "default",
"cartype" varchar(255) COLLATE "default",
"otherrequire" varchar(255) COLLATE "default",
"other" text COLLATE "default",
"linestatus" int4,
"status" int4,
"create_user" varchar(255) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(255) COLLATE "default",
"update_time" timestamp(6),
"truckeruserid" varchar(255) COLLATE "default",
"demandid" varchar(255) COLLATE "default",
"delivery_time_everyday2" varchar(255) COLLATE "default",
"if_on_line" int4,
"repertory_id" varchar(255) COLLATE "default",
"weekday" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_line"."lineid" IS '线路id';
COMMENT ON COLUMN "public"."t_sds_line"."linename" IS '线路名称';
COMMENT ON COLUMN "public"."t_sds_line"."linetype" IS '线路类型     长期:1   临时:2';
COMMENT ON COLUMN "public"."t_sds_line"."subaccount" IS '子账号';
COMMENT ON COLUMN "public"."t_sds_line"."repertory_address" IS '仓库地址 ';
COMMENT ON COLUMN "public"."t_sds_line"."delivery_area" IS '配送区域';
COMMENT ON COLUMN "public"."t_sds_line"."delivery_point_1" IS '配送点';
COMMENT ON COLUMN "public"."t_sds_line"."delivery_point_2" IS '配送点';
COMMENT ON COLUMN "public"."t_sds_line"."delivery_km" IS '配送里程';
COMMENT ON COLUMN "public"."t_sds_line"."delivery_date_start" IS '开线时间';
COMMENT ON COLUMN "public"."t_sds_line"."delivery_time_everyday" IS '每日配送时间';
COMMENT ON COLUMN "public"."t_sds_line"."cartype" IS '需要车型';
COMMENT ON COLUMN "public"."t_sds_line"."otherrequire" IS '其它要求';
COMMENT ON COLUMN "public"."t_sds_line"."other" IS '其它说明';
COMMENT ON COLUMN "public"."t_sds_line"."linestatus" IS '线路状态  1 运行中   0 已停用';
COMMENT ON COLUMN "public"."t_sds_line"."truckeruserid" IS '司机id';
COMMENT ON COLUMN "public"."t_sds_line"."demandid" IS '用车需求id';
COMMENT ON COLUMN "public"."t_sds_line"."delivery_time_everyday2" IS '每日配送时间';
COMMENT ON COLUMN "public"."t_sds_line"."if_on_line" IS '是否线上招募(1-是；0-否)';
COMMENT ON COLUMN "public"."t_sds_line"."repertory_id" IS '仓库地址 ';
COMMENT ON COLUMN "public"."t_sds_line"."weekday" IS '每周配送时间,1,2,3,4,5,6,7 对应一周7天';

-- ----------------------------
-- Table structure for t_sds_login_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_login_log";
CREATE TABLE "public"."t_sds_login_log" (
"user_code" varchar(50) COLLATE "default",
"ip" varchar(50) COLLATE "default",
"login_method" varchar(50) COLLATE "default",
"id" varchar(50) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_repertory
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_repertory";
CREATE TABLE "public"."t_sds_repertory" (
"id" varchar(50) COLLATE "default" NOT NULL,
"repertory_name" varchar(50) COLLATE "default",
"repertory_add" varchar(255) COLLATE "default",
"repertory_longitude" varchar(50) COLLATE "default",
"repertory_contact" varchar(50) COLLATE "default",
"repertory_mobile" varchar(50) COLLATE "default",
"employee_number" varchar(50) COLLATE "default",
"employee_contact" varchar(50) COLLATE "default",
"employee_mobile" varchar(50) COLLATE "default",
"create_user" varchar(50) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"repertory_latitude" varchar(50) COLLATE "default",
"status" int8,
"subenterprise_id" varchar(50) COLLATE "default",
"subenterprise_account" varchar(30) COLLATE "default",
"enterprise_id" varchar(50) COLLATE "default",
"enterprise_account" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_repertory"."status" IS '0:当子账号被禁用时;1:正常，2：删除; ';

-- ----------------------------
-- Table structure for t_sds_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_role";
CREATE TABLE "public"."t_sds_role" (
"id" varchar(50) COLLATE "default" NOT NULL,
"create_time" timestamp(6),
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"role_code" varchar(50) COLLATE "default",
"role_name" varchar(50) COLLATE "default",
"role_desc" varchar(255) COLLATE "default",
"dept_code" varchar(50) COLLATE "default",
"role_type" varchar(50) COLLATE "default",
"status" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_role_function
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_role_function";
CREATE TABLE "public"."t_sds_role_function" (
"role_code" varchar(50) COLLATE "default",
"function_code" varchar(50) COLLATE "default",
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(20) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"id" varchar(50) COLLATE "default" NOT NULL,
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_send_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_send_log";
CREATE TABLE "public"."t_sds_send_log" (
"biz_no" varchar(255) COLLATE "default",
"body" text COLLATE "default",
"biz_type" varchar(255) COLLATE "default",
"descp" varchar(255) COLLATE "default",
"target" varchar(255) COLLATE "default",
"create_user" varchar(255) COLLATE "default",
"update_user" varchar(255) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"id" varchar(255) COLLATE "default",
"create_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_subenterprise
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_subenterprise";
CREATE TABLE "public"."t_sds_subenterprise" (
"id" varchar(40) COLLATE "default" NOT NULL,
"subenterprise_account" varchar(255) COLLATE "default",
"subenterprise_name" varchar(255) COLLATE "default",
"enterprise_id" varchar(40) COLLATE "default",
"enterprise_account" varchar(255) COLLATE "default",
"enterprise_name" varchar(255) COLLATE "default",
"city" varchar(10) COLLATE "default",
"contact_name" varchar(30) COLLATE "default",
"contact_phone" varchar(15) COLLATE "default",
"status" int4,
"create_user" varchar(20) COLLATE "default",
"create_time" timestamp(6),
"update_user" varchar(20) COLLATE "default",
"update_time" timestamp(6),
"frozen_status" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_subenterprise"."id" IS '主键';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."subenterprise_account" IS '子账号';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."subenterprise_name" IS '企业子账号名称';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."enterprise_id" IS '主账号ID';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."enterprise_account" IS '主账号';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."enterprise_name" IS '企业名称';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."city" IS '城市';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."contact_name" IS '联系人';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."contact_phone" IS '联系电话';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."status" IS '1:正常2:停用4:冻结';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."create_user" IS '创建人';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."update_user" IS '修改人';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."t_sds_subenterprise"."frozen_status" IS '1正常4冻结';

-- ----------------------------
-- Table structure for t_sds_syndata_cartype
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_cartype";
CREATE TABLE "public"."t_sds_syndata_cartype" (
"id" varchar(50) COLLATE "default",
"carname" varchar(50) COLLATE "default",
"canload" varchar(50) COLLATE "default",
"lwh" varchar(50) COLLATE "default",
"createtime" varchar(50) COLLATE "default",
"createuser" varchar(50) COLLATE "default",
"remark" varchar(50) COLLATE "default",
"cartypeimage" varchar(50) COLLATE "default",
"lastupdatetime" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_syndata_city
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_city";
CREATE TABLE "public"."t_sds_syndata_city" (
"id" varchar(50) COLLATE "default",
"name" varchar(50) COLLATE "default",
"zipcode" varchar(50) COLLATE "default",
"provinceid" varchar(50) COLLATE "default",
"createdate" varchar(50) COLLATE "default",
"updatedate" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_syndata_department
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_department";
CREATE TABLE "public"."t_sds_syndata_department" (
"id" varchar(255) COLLATE "default",
"departmentcode" varchar(255) COLLATE "default",
"departmentmark" varchar(255) COLLATE "default",
"departmentname" varchar(255) COLLATE "default",
"supdeptcode" varchar(255) COLLATE "default",
"coadjutant" varchar(32) COLLATE "default",
"showorder" varchar(32) COLLATE "default",
"subcompanyid1" varchar(32) COLLATE "default",
"supdepid" varchar(32) COLLATE "default",
"lastupdate" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_syndata_emp
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_emp";
CREATE TABLE "public"."t_sds_syndata_emp" (
"id" varchar(50) COLLATE "default" NOT NULL,
"business_unit_id" varchar(50) COLLATE "default",
"butype" int4,
"childcityname" text COLLATE "default",
"cityname" text COLLATE "default",
"createdate" varchar(32) COLLATE "default",
"department" text COLLATE "default",
"departmentid" varchar(32) COLLATE "default",
"departmentname" text COLLATE "default",
"hiredate" varchar(32) COLLATE "default",
"introduce" text COLLATE "default",
"isenabled" int2,
"jobtitle" text COLLATE "default",
"jobtype" int4,
"lastupdate" varchar(32) COLLATE "default",
"name" text COLLATE "default",
"password" text COLLATE "default",
"phonenumber" text COLLATE "default",
"powertype" int4,
"title" text COLLATE "default",
"username" text COLLATE "default",
"workecode" text COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_syndata_jobplanlog
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_jobplanlog";
CREATE TABLE "public"."t_sds_syndata_jobplanlog" (
"syn_count" varchar(255) COLLATE "default",
"job_name" varchar(255) COLLATE "default",
"syn_time_end" varchar(32) COLLATE "default",
"syn_message" varchar(255) COLLATE "default",
"table_name" varchar(255) COLLATE "default",
"syn_content" varchar(255) COLLATE "default",
"syn_success" varchar(255) COLLATE "default",
"syn_time" varchar(32) COLLATE "default",
"syn_status" int4,
"syn_time_star" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_syndata_jobplansite
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_jobplansite";
CREATE TABLE "public"."t_sds_syndata_jobplansite" (
"id" int4 NOT NULL,
"job_name" varchar COLLATE "default",
"cron" varchar COLLATE "default",
"enable" int2,
"content" varchar COLLATE "default",
"table_name" varchar(255) COLLATE "default",
"mongo_name" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_syndata_jobplansite"."job_name" IS '执行任务的包名+名称';
COMMENT ON COLUMN "public"."t_sds_syndata_jobplansite"."cron" IS 'quarz时间配置';
COMMENT ON COLUMN "public"."t_sds_syndata_jobplansite"."enable" IS '0：关闭 1：启动';
COMMENT ON COLUMN "public"."t_sds_syndata_jobplansite"."content" IS '计划任务描述';

-- ----------------------------
-- Table structure for t_sds_syndata_province
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_province";
CREATE TABLE "public"."t_sds_syndata_province" (
"id" varchar(50) COLLATE "default",
"provincename" varchar(50) COLLATE "default",
"createat" varchar(50) COLLATE "default",
"lastupdate" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_syndata_truckeruser
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_truckeruser";
CREATE TABLE "public"."t_sds_syndata_truckeruser" (
"id" varchar(50) COLLATE "default" NOT NULL,
"name" varchar(50) COLLATE "default",
"password" varchar(50) COLLATE "default",
"usertype" int4,
"verified" int4,
"trucktype" varchar(50) COLLATE "default",
"truckname" varchar(50) COLLATE "default",
"truckcapacity" varchar(50) COLLATE "default",
"trucklength" float8,
"truckno" varchar(50) COLLATE "default",
"minfee" char(32) COLLATE "default",
"feeperkm" varchar(50) COLLATE "default",
"totaldeal" int4,
"wisdomdeliverexperience" int4,
"usercontract" int4,
"phonenumber" varchar(50) COLLATE "default",
"location_country" varchar(50) COLLATE "default",
"location_province" varchar(50) COLLATE "default",
"location_city" varchar(50) COLLATE "default",
"location_area" varchar(50) COLLATE "default",
"location_latitude" varchar(50) COLLATE "default",
"location_longitude" varchar(50) COLLATE "default",
"location_altitude" int4,
"location_addressname" varchar(50) COLLATE "default",
"location_address" varchar(50) COLLATE "default",
"updatedate" varchar(50) COLLATE "default",
"agentid" varchar(50) COLLATE "default",
"createdate" varchar(50) COLLATE "default",
"userid" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_syndata_truckuser_track
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_truckuser_track";
CREATE TABLE "public"."t_sds_syndata_truckuser_track" (
"carplanid" varchar(255) COLLATE "default",
"truckuserid" varchar(255) COLLATE "default",
"location_latitude" float8,
"location_longitude" float8,
"addressname" varchar(255) COLLATE "default",
"address" varchar(255) COLLATE "default",
"optype" int4,
"id" varchar(255) COLLATE "default" NOT NULL,
"optime" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_syndata_truckuser_track"."carplanid" IS '用车计划id';
COMMENT ON COLUMN "public"."t_sds_syndata_truckuser_track"."truckuserid" IS '司机id';
COMMENT ON COLUMN "public"."t_sds_syndata_truckuser_track"."location_latitude" IS '纬度';
COMMENT ON COLUMN "public"."t_sds_syndata_truckuser_track"."location_longitude" IS '经度';
COMMENT ON COLUMN "public"."t_sds_syndata_truckuser_track"."addressname" IS '地址名(例如:E通世界北区)';
COMMENT ON COLUMN "public"."t_sds_syndata_truckuser_track"."address" IS '真实地址(xx路xx号)';
COMMENT ON COLUMN "public"."t_sds_syndata_truckuser_track"."optype" IS ' //操作类型  0签到；1开始送货， 2途径标记；3.完成接获';
COMMENT ON COLUMN "public"."t_sds_syndata_truckuser_track"."id" IS '主键id';

-- ----------------------------
-- Table structure for t_sds_syndata_truckuserlastseen
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_syndata_truckuserlastseen";
CREATE TABLE "public"."t_sds_syndata_truckuserlastseen" (
"truckuserid" varchar(50) COLLATE "default" NOT NULL,
"gps_longitude" float8,
"gps_latitude" float8,
"location_loc_country" varchar COLLATE "default",
"location_loc_province" varchar COLLATE "default",
"location_loc_city" varchar COLLATE "default",
"location_loc_area" varchar COLLATE "default",
"location_loc_latitude" float8,
"location_loc_longitude" float8,
"location_loc_addressname" varchar COLLATE "default",
"location_loc_address" varchar COLLATE "default",
"location_speed" float8,
"location_timetick" int8,
"needupdate" int2,
"online" int2,
"lastseenat" varchar(32) COLLATE "default",
"lastupdatetime" varchar(32) COLLATE "default",
"nextpushat" varchar(32) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_system_parameter
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_system_parameter";
CREATE TABLE "public"."t_sds_system_parameter" (
"id" varchar(50) COLLATE "default" NOT NULL,
"sys_key" varchar(50) COLLATE "default",
"sys_value" varchar(50) COLLATE "default",
"create_time" timestamp(6),
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"status" int2
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_sds_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_user";
CREATE TABLE "public"."t_sds_user" (
"id" varchar(50) COLLATE "default" NOT NULL,
"user_name" varchar(50) COLLATE "default",
"password" varchar(50) COLLATE "default",
"begin_time" timestamp(6),
"end_time" timestamp(6),
"last_login" timestamp(6),
"create_user" varchar(50) COLLATE "default",
"update_user" varchar(50) COLLATE "default",
"update_time" timestamp(6),
"status" int4,
"create_time" timestamp(6),
"type" int4,
"phone" varchar(20) COLLATE "default",
"frozen_status" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."t_sds_user"."status" IS '1:正常2:禁用3:删除4:冻结';
COMMENT ON COLUMN "public"."t_sds_user"."type" IS '1:系统用户2:企业主账号3:企业子账号';
COMMENT ON COLUMN "public"."t_sds_user"."frozen_status" IS '1:正常4冻结';

-- ----------------------------
-- Table structure for t_sds_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_sds_user_role";
CREATE TABLE "public"."t_sds_user_role" (
"id" varchar(40) COLLATE "default" NOT NULL,
"user_code" varchar(40) COLLATE "default",
"role_code" varchar(40) COLLATE "default",
"create_user" varchar(40) COLLATE "default",
"update_user" varchar(40) COLLATE "default",
"update_time" timestamp(6),
"create_time" timestamp(6),
"status" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table qrtz_blob_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_blob_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Primary Key structure for table qrtz_calendars
-- ----------------------------
ALTER TABLE "public"."qrtz_calendars" ADD PRIMARY KEY ("sched_name", "calendar_name");

-- ----------------------------
-- Primary Key structure for table qrtz_cron_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_cron_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Indexes structure for table qrtz_fired_triggers
-- ----------------------------
CREATE INDEX "idx_qrtz_ft_inst_job_req_rcvry" ON "public"."qrtz_fired_triggers" USING btree (sched_name, instance_name, requests_recovery);
CREATE INDEX "idx_qrtz_ft_j_g" ON "public"."qrtz_fired_triggers" USING btree (sched_name, job_name, job_group);
CREATE INDEX "idx_qrtz_ft_jg" ON "public"."qrtz_fired_triggers" USING btree (sched_name, job_group);
CREATE INDEX "idx_qrtz_ft_t_g" ON "public"."qrtz_fired_triggers" USING btree (sched_name, trigger_name, trigger_group);
CREATE INDEX "idx_qrtz_ft_tg" ON "public"."qrtz_fired_triggers" USING btree (sched_name, trigger_group);
CREATE INDEX "idx_qrtz_ft_trig_inst_name" ON "public"."qrtz_fired_triggers" USING btree (sched_name, instance_name);

-- ----------------------------
-- Primary Key structure for table qrtz_fired_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_fired_triggers" ADD PRIMARY KEY ("sched_name", "entry_id");

-- ----------------------------
-- Indexes structure for table qrtz_job_details
-- ----------------------------
CREATE INDEX "idx_qrtz_j_grp" ON "public"."qrtz_job_details" USING btree (sched_name, job_group);
CREATE INDEX "idx_qrtz_j_req_recovery" ON "public"."qrtz_job_details" USING btree (sched_name, requests_recovery);

-- ----------------------------
-- Primary Key structure for table qrtz_job_details
-- ----------------------------
ALTER TABLE "public"."qrtz_job_details" ADD PRIMARY KEY ("sched_name", "job_name", "job_group");

-- ----------------------------
-- Primary Key structure for table qrtz_locks
-- ----------------------------
ALTER TABLE "public"."qrtz_locks" ADD PRIMARY KEY ("sched_name", "lock_name");

-- ----------------------------
-- Primary Key structure for table qrtz_paused_trigger_grps
-- ----------------------------
ALTER TABLE "public"."qrtz_paused_trigger_grps" ADD PRIMARY KEY ("sched_name", "trigger_group");

-- ----------------------------
-- Primary Key structure for table qrtz_scheduler_state
-- ----------------------------
ALTER TABLE "public"."qrtz_scheduler_state" ADD PRIMARY KEY ("sched_name", "instance_name");

-- ----------------------------
-- Primary Key structure for table qrtz_simple_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_simple_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Primary Key structure for table qrtz_simprop_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_simprop_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Indexes structure for table qrtz_triggers
-- ----------------------------
CREATE INDEX "idx_qrtz_t_c" ON "public"."qrtz_triggers" USING btree (sched_name, calendar_name);
CREATE INDEX "idx_qrtz_t_g" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_group);
CREATE INDEX "idx_qrtz_t_j" ON "public"."qrtz_triggers" USING btree (sched_name, job_name, job_group);
CREATE INDEX "idx_qrtz_t_jg" ON "public"."qrtz_triggers" USING btree (sched_name, job_group);
CREATE INDEX "idx_qrtz_t_n_g_state" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_group, trigger_state);
CREATE INDEX "idx_qrtz_t_n_state" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_name, trigger_group, trigger_state);
CREATE INDEX "idx_qrtz_t_next_fire_time" ON "public"."qrtz_triggers" USING btree (sched_name, next_fire_time);
CREATE INDEX "idx_qrtz_t_nft_misfire" ON "public"."qrtz_triggers" USING btree (sched_name, misfire_instr, next_fire_time);
CREATE INDEX "idx_qrtz_t_nft_st" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_state, next_fire_time);
CREATE INDEX "idx_qrtz_t_nft_st_misfire" ON "public"."qrtz_triggers" USING btree (sched_name, misfire_instr, next_fire_time, trigger_state);
CREATE INDEX "idx_qrtz_t_nft_st_misfire_grp" ON "public"."qrtz_triggers" USING btree (sched_name, misfire_instr, next_fire_time, trigger_group, trigger_state);
CREATE INDEX "idx_qrtz_t_state" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_state);

-- ----------------------------
-- Primary Key structure for table qrtz_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Primary Key structure for table t_sds_auth
-- ----------------------------
ALTER TABLE "public"."t_sds_auth" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_billingsolution
-- ----------------------------
ALTER TABLE "public"."t_sds_billingsolution" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_carplan
-- ----------------------------
CREATE INDEX "t_sds_carplan_index_carplan_date" ON "public"."t_sds_carplan" USING btree (carplan_date);
CREATE INDEX "t_sds_carplan_index_lineid" ON "public"."t_sds_carplan" USING btree (lineid);
CREATE INDEX "t_sds_carplan_index_truckuserid" ON "public"."t_sds_carplan" USING btree (truckeruserid);

-- ----------------------------
-- Primary Key structure for table t_sds_carplan
-- ----------------------------
ALTER TABLE "public"."t_sds_carplan" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_carplanrecord
-- ----------------------------
CREATE INDEX "t_sds_carplanrecord_index_customer_cost" ON "public"."t_sds_carplanrecord" USING btree (customer_cost);
CREATE INDEX "t_sds_carplanrecord_index_trucker_cost" ON "public"."t_sds_carplanrecord" USING btree (trucker_cost);

-- ----------------------------
-- Primary Key structure for table t_sds_carplanrecord
-- ----------------------------
ALTER TABLE "public"."t_sds_carplanrecord" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_cityrelationship
-- ----------------------------
ALTER TABLE "public"."t_sds_cityrelationship" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_cs
-- ----------------------------
ALTER TABLE "public"."t_sds_cs" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_customer_bill
-- ----------------------------
ALTER TABLE "public"."t_sds_customer_bill" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_demand
-- ----------------------------
CREATE INDEX "t_sds_demand_index_hear_status" ON "public"."t_sds_demand" USING btree (hear_status);
CREATE INDEX "t_sds_demand_index_recruitstatus" ON "public"."t_sds_demand" USING btree (recruit_status);
CREATE INDEX "t_sds_demand_index_subaccount" ON "public"."t_sds_demand" USING btree (subenterprise_accouont);

-- ----------------------------
-- Primary Key structure for table t_sds_demand
-- ----------------------------
ALTER TABLE "public"."t_sds_demand" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_demand_driver
-- ----------------------------
CREATE INDEX "t_sds_demand_driver_index_demandid" ON "public"."t_sds_demand_driver" USING btree (demand_id);
CREATE INDEX "t_sds_demand_driver_index_truckerid" ON "public"."t_sds_demand_driver" USING btree (driver_id);

-- ----------------------------
-- Primary Key structure for table t_sds_demand_driver
-- ----------------------------
ALTER TABLE "public"."t_sds_demand_driver" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_dict
-- ----------------------------
ALTER TABLE "public"."t_sds_dict" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_dict_value
-- ----------------------------
ALTER TABLE "public"."t_sds_dict_value" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_driver
-- ----------------------------
CREATE INDEX "t_sds_driver_index_truckerid" ON "public"."t_sds_driver" USING btree (direct_driver_id);

-- ----------------------------
-- Primary Key structure for table t_sds_driver
-- ----------------------------
ALTER TABLE "public"."t_sds_driver" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_driver_apply
-- ----------------------------
CREATE INDEX "t_sds_driver_apply_index_demandid" ON "public"."t_sds_driver_apply" USING btree (demand_id);
CREATE INDEX "t_sds_driver_apply_index_subaccount" ON "public"."t_sds_driver_apply" USING btree (subenterprise_account);
CREATE INDEX "t_sds_driver_apply_index_truckerid" ON "public"."t_sds_driver_apply" USING btree (truckeruser_id);

-- ----------------------------
-- Primary Key structure for table t_sds_driver_apply
-- ----------------------------
ALTER TABLE "public"."t_sds_driver_apply" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_driver_bill
-- ----------------------------
ALTER TABLE "public"."t_sds_driver_bill" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_driver_reserve
-- ----------------------------
CREATE INDEX "t_sds_driver_reserve_index_subaccount" ON "public"."t_sds_driver_reserve" USING btree (subenterprise_account);
CREATE INDEX "t_sds_driver_reserve_index_truckerid" ON "public"."t_sds_driver_reserve" USING btree (truckeruser_id);

-- ----------------------------
-- Primary Key structure for table t_sds_driver_reserve
-- ----------------------------
ALTER TABLE "public"."t_sds_driver_reserve" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_enterprise
-- ----------------------------
CREATE INDEX "t_sds_enterprise_index_account" ON "public"."t_sds_enterprise" USING btree (enterprise_account);
CREATE INDEX "t_sds_enterprise_index_empid" ON "public"."t_sds_enterprise" USING btree (emp_id);
CREATE INDEX "t_sds_enterprise_index_name" ON "public"."t_sds_enterprise" USING btree (enterprise_name);

-- ----------------------------
-- Primary Key structure for table t_sds_enterprise
-- ----------------------------
ALTER TABLE "public"."t_sds_enterprise" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_line
-- ----------------------------
CREATE INDEX "t_sds_line_index_lineid" ON "public"."t_sds_line" USING btree (lineid);
CREATE INDEX "t_sds_line_index_linestatus" ON "public"."t_sds_line" USING btree (linestatus);
CREATE INDEX "t_sds_line_index_linetype" ON "public"."t_sds_line" USING btree (linetype);
CREATE INDEX "t_sds_line_index_subaccount" ON "public"."t_sds_line" USING btree (subaccount);

-- ----------------------------
-- Primary Key structure for table t_sds_line
-- ----------------------------
ALTER TABLE "public"."t_sds_line" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_login_log
-- ----------------------------
ALTER TABLE "public"."t_sds_login_log" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_repertory
-- ----------------------------
CREATE INDEX "t_sds_repertory_enpaccount" ON "public"."t_sds_repertory" USING btree (enterprise_account);
CREATE INDEX "t_sds_repertory_subaccount" ON "public"."t_sds_repertory" USING btree (subenterprise_account);

-- ----------------------------
-- Primary Key structure for table t_sds_repertory
-- ----------------------------
ALTER TABLE "public"."t_sds_repertory" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_role
-- ----------------------------
ALTER TABLE "public"."t_sds_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_sds_role_function
-- ----------------------------
ALTER TABLE "public"."t_sds_role_function" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_subenterprise
-- ----------------------------
CREATE INDEX "t_sds_subenterprise_account" ON "public"."t_sds_subenterprise" USING btree (subenterprise_account);
CREATE INDEX "t_sds_subenterprise_enpaccount" ON "public"."t_sds_subenterprise" USING btree (enterprise_account);
CREATE INDEX "t_sds_subenterprise_name" ON "public"."t_sds_subenterprise" USING btree (subenterprise_name);

-- ----------------------------
-- Primary Key structure for table t_sds_subenterprise
-- ----------------------------
ALTER TABLE "public"."t_sds_subenterprise" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_syndata_emp
-- ----------------------------
CREATE INDEX "t_sds_syndata_emp_name" ON "public"."t_sds_syndata_emp" USING btree (name);
CREATE INDEX "t_sds_syndata_emp_username" ON "public"."t_sds_syndata_emp" USING btree (username);

-- ----------------------------
-- Primary Key structure for table t_sds_syndata_emp
-- ----------------------------
ALTER TABLE "public"."t_sds_syndata_emp" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_syndata_truckeruser
-- ----------------------------
CREATE INDEX "t_sds_syndata_trucker_name" ON "public"."t_sds_syndata_truckeruser" USING btree (name);
CREATE INDEX "t_sds_syndata_trucker_phonenumber" ON "public"."t_sds_syndata_truckeruser" USING btree (phonenumber);
CREATE INDEX "t_sds_syndata_trucker_trucktype" ON "public"."t_sds_syndata_truckeruser" USING btree (trucktype);
CREATE INDEX "t_sds_syndata_trucker_userid" ON "public"."t_sds_syndata_truckeruser" USING btree (userid);

-- ----------------------------
-- Primary Key structure for table t_sds_syndata_truckeruser
-- ----------------------------
ALTER TABLE "public"."t_sds_syndata_truckeruser" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_syndata_truckuser_track
-- ----------------------------
CREATE INDEX "t_sds_syndata_truckuser_track_index_carplanid" ON "public"."t_sds_syndata_truckuser_track" USING btree (carplanid);
CREATE INDEX "t_sds_syndata_truckuser_track_index_optime" ON "public"."t_sds_syndata_truckuser_track" USING btree (optime);
CREATE INDEX "t_sds_syndata_truckuser_track_index_truckerid" ON "public"."t_sds_syndata_truckuser_track" USING btree (truckuserid);

-- ----------------------------
-- Primary Key structure for table t_sds_syndata_truckuserlastseen
-- ----------------------------
ALTER TABLE "public"."t_sds_syndata_truckuserlastseen" ADD PRIMARY KEY ("truckuserid");

-- ----------------------------
-- Indexes structure for table t_sds_user
-- ----------------------------
CREATE INDEX "t_sds_user_username" ON "public"."t_sds_user" USING btree (user_name);

-- ----------------------------
-- Primary Key structure for table t_sds_user
-- ----------------------------
ALTER TABLE "public"."t_sds_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table t_sds_user_role
-- ----------------------------
CREATE INDEX "t_sds_user_role_rolecode" ON "public"."t_sds_user_role" USING btree (role_code);
CREATE INDEX "t_sds_user_role_usercode" ON "public"."t_sds_user_role" USING btree (user_code);

-- ----------------------------
-- Primary Key structure for table t_sds_user_role
-- ----------------------------
ALTER TABLE "public"."t_sds_user_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_blob_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_blob_triggers" ADD FOREIGN KEY ("sched_name", "trigger_name", "trigger_group") REFERENCES "public"."qrtz_triggers" ("sched_name", "trigger_name", "trigger_group") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_cron_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_cron_triggers" ADD FOREIGN KEY ("sched_name", "trigger_name", "trigger_group") REFERENCES "public"."qrtz_triggers" ("sched_name", "trigger_name", "trigger_group") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_simple_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_simple_triggers" ADD FOREIGN KEY ("sched_name", "trigger_name", "trigger_group") REFERENCES "public"."qrtz_triggers" ("sched_name", "trigger_name", "trigger_group") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_simprop_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_simprop_triggers" ADD FOREIGN KEY ("sched_name", "trigger_name", "trigger_group") REFERENCES "public"."qrtz_triggers" ("sched_name", "trigger_name", "trigger_group") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_triggers" ADD FOREIGN KEY ("sched_name", "job_name", "job_group") REFERENCES "public"."qrtz_job_details" ("sched_name", "job_name", "job_group") ON DELETE NO ACTION ON UPDATE NO ACTION;
