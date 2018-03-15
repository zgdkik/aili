


DROP TABLE IF EXISTS t_sds_auth;
CREATE TABLE t_sds_auth (
id varchar(50)  NOT NULL,
function_code varchar(255) ,
function_name varchar(1000) ,
uri varchar(255) ,
parent_code varchar(50) ,
active varchar(255) ,
display_order varchar(255) ,
checkable varchar(255) ,
function_type varchar(255) ,
leaf varchar(255) ,
icon_cls varchar(255) ,
cls varchar(255) ,
function_desc varchar(2000) ,
create_user varchar(255) ,
create_time timestamp(6),
update_user varchar(255) ,
update_time timestamp(6),
status int4
)
;
COMMENT ON COLUMN t_sds_auth.function_code IS '功能编码';
COMMENT ON COLUMN t_sds_auth.function_name IS '功能名称';
COMMENT ON COLUMN t_sds_auth.uri IS '功能入口URI';
COMMENT ON COLUMN t_sds_auth.parent_code IS '父功能';
COMMENT ON COLUMN t_sds_auth.active IS '是否有效';
COMMENT ON COLUMN t_sds_auth.display_order IS '显示顺序';
COMMENT ON COLUMN t_sds_auth.checkable IS '是否权限检查';
COMMENT ON COLUMN t_sds_auth.function_type IS '功能类型';
COMMENT ON COLUMN t_sds_auth.leaf IS '是否叶子节点';
COMMENT ON COLUMN t_sds_auth.icon_cls IS '图标的CSS样式';
COMMENT ON COLUMN t_sds_auth.cls IS '节点的CSS样式';
COMMENT ON COLUMN t_sds_auth.function_desc IS '功能描述';



DROP TABLE IF EXISTS t_sds_dict_value;
CREATE TABLE t_sds_dict_value (
dict_key varchar(50) ,
dict_value varchar(50) ,
dict_code varchar(50) ,
order_no int4,
remark varchar(255) ,
create_user varchar(50) ,
update_user varchar(50) ,
update_time timestamp(6),
status int4,
id varchar(50)  NOT NULL,
create_time timestamp(6)
)


;

-- ----------------------------
-- Table structure for t_sds_login_log
-- ----------------------------
DROP TABLE IF EXISTS t_sds_login_log;
CREATE TABLE t_sds_login_log (
user_code varchar(50) ,
ip varchar(50) ,
login_method varchar(50) ,
id varchar(50)  NOT NULL,
create_time timestamp(6)
)


;

-- ----------------------------
-- Table structure for t_sds_role
-- ----------------------------
DROP TABLE IF EXISTS t_sds_role;
CREATE TABLE t_sds_role (
id varchar(50)  NOT NULL,
create_time timestamp(6),
create_user varchar(50) ,
update_user varchar(50) ,
update_time timestamp(6),
role_code varchar(50) ,
role_name varchar(50) ,
role_desc varchar(255) ,
dept_code varchar(50) ,
role_type varchar(50) ,
status int8
)


;
-- ----------------------------
-- Table structure for t_sds_role_function
-- ----------------------------
DROP TABLE IF EXISTS t_sds_role_function;
CREATE TABLE t_sds_role_function (
role_code varchar(50) ,
function_code varchar(50) ,
create_user varchar(50) ,
update_user varchar(20) ,
update_time timestamp(6),
status int4,
id varchar(50)  NOT NULL,
create_time timestamp(6)
)

;
-- ----------------------------
-- Table structure for t_sds_send_log
-- ----------------------------
DROP TABLE IF EXISTS t_sds_send_log;
CREATE TABLE t_sds_send_log (
biz_no varchar(255) ,
body text ,
biz_type varchar(255) ,
descp varchar(255) ,
target varchar(255) ,
create_user varchar(255) ,
update_user varchar(255) ,
update_time timestamp(6),
status int4,
id varchar(255) ,
create_time timestamp(6)
)


;

-- ----------------------------
-- Table structure for t_sds_system_parameter
-- ----------------------------
DROP TABLE IF EXISTS t_sds_system_parameter;
CREATE TABLE t_sds_system_parameter (
id varchar(50)  NOT NULL,
sys_key varchar(50) ,
sys_value varchar(50) ,
create_time timestamp(6),
create_user varchar(50) ,
update_user varchar(50) ,
update_time timestamp(6),
status int2
)


;

-- ----------------------------
-- Table structure for t_sds_user
-- ----------------------------
DROP TABLE IF EXISTS t_sds_user;
CREATE TABLE t_sds_user (
id varchar(50)  NOT NULL,
user_name varchar(50) ,
password varchar(50) ,
begin_time timestamp(6),
end_time timestamp(6),
last_login timestamp(6),
create_user varchar(50) ,
update_user varchar(50) ,
update_time timestamp(6),
status int4,
create_time timestamp(6),
type int4,
phone varchar(20) ,
frozen_status int4
)


;
COMMENT ON COLUMN t_sds_user.status IS '1:正常2:禁用3:删除4:冻结';
COMMENT ON COLUMN t_sds_user.type IS '1:系统用户2:企业主账号3:企业子账号';
COMMENT ON COLUMN t_sds_user.frozen_status IS '1:正常4冻结';

-- ----------------------------
-- Table structure for t_sds_user_role
-- ----------------------------
DROP TABLE IF EXISTS t_sds_user_role;
CREATE TABLE t_sds_user_role (
id varchar(40)  NOT NULL,
user_code varchar(40) ,
role_code varchar(40) ,
create_user varchar(40) ,
update_user varchar(40) ,
update_time timestamp(6),
create_time timestamp(6),
status int4
)

;
