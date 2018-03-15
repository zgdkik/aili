
CREATE TABLE t_sds_dict (
dict_code varchar(50)  ,
dict_name varchar(255)  ,
remark varchar(255)  ,
create_user varchar(50)  ,
update_user varchar(255)  ,
update_time timestamp(6),
status int4,
id varchar(50)   NOT NULL,
create_time timestamp(6)
);

INSERT INTO t_sds_dict VALUES ('function_type', '功能类型', null, null, 'admin', '2015-12-07 17:03:47.987', '1', '1', null);
INSERT INTO t_sds_dict VALUES ('auth_type', '角色类型', null, 'admin', 'admin', '2015-12-14 15:39:39.014', '0', '6426aa96-3eab-4d37-96bc-49a046d9d189', '2015-12-14 11:50:42.629');
INSERT INTO t_sds_dict VALUES ('role_type', '角色类型', null, 'admin', null, null, '1', '973f548c-c674-40b3-8f24-480d81d0e53e', '2015-12-14 15:40:29.78');

