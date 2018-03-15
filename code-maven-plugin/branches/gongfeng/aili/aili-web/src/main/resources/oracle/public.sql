create table T_AUTH_PRIVILEGE
(
  ID            VARCHAR2(50) not null,
  CODE          VARCHAR2(255),
  NAME          VARCHAR2(1000),
  URL           VARCHAR2(255),
  PARENT_CODE   VARCHAR2(50),
  DISPLAY_ORDER VARCHAR2(255),
  CHECKABLE     VARCHAR2(255),
  TYPE          VARCHAR2(255),
  LEAF          VARCHAR2(255),
  ICON_CLS      VARCHAR2(255),
  CLS           VARCHAR2(255),
  DESCP         VARCHAR2(2000),
  CREATE_USER   VARCHAR2(255),
  CREATE_TIME   TIMESTAMP(6) not null,
  UPDATE_USER   VARCHAR2(255),
  UPDATE_TIME   TIMESTAMP(6),
  STATUS        INTEGER,
  SHOUTCUT_KEYS VARCHAR2(50),
  MODULE_TYPE   VARCHAR2(50),
  APP_TYPE      VARCHAR2(50)
)
-- 初始化用户权限
insert  into t_auth_user_role(id,user_code,role_code,status)  select u.user_id , u.username,'common',1 from ts_user u  where u.status in ('enable','system');