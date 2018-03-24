--短信模板
create table T_SMS_TEMPLATE
(
  id                NUMBER(20) PRIMARY KEY,
  SMS_CODE          VARCHAR2(50),
  SMS_NAME          VARCHAR2(50),
  sms_content       VARCHAR2(600),
  sms_params        VARCHAR2(300),
  sms_type          VARCHAR2(2),
  service_type_CODE      VARCHAR2(2),
  sms_mode          VARCHAR2(1),
  comp_code         VARCHAR2(30),
  created_emp_code  VARCHAR2(255 CHAR),
  created_tm        DATE,
  modified_emp_code VARCHAR2(255 CHAR),
  modified_tm       DATE,
  BEGIN_TM       DATE,
  END_TM       DATE,
  VALID_FLAG   CHAR(1)
);
--特定人群短信模板发送
CREATE TABLE T_SPE_SEND_MSG_CONFIG(
  CONFIG_ID NUMBER(20) PRIMARY KEY,
  EMP_MOBILE VARCHAR2(20),
  IS_SEND CHAR(1),
  MSG_TYPE VARCHAR2(50),
  EMP_CODE VARCHAR2(50),
  EMP_NAME VARCHAR2(50),
  DEPT_CODE VARCHAR2(50),
  COMP_CODE VARCHAR2(50),
  CREATED_EMP_CODE VARCHAR2(50),
  created_tm        DATE,
  modified_emp_code VARCHAR2(255 CHAR),
  modified_tm       DATE,
  BEGIN_TM       DATE,
  END_TM       DATE,
  VALID_FLAG   CHAR(1)
);