CREATE ROLE logistics2 LOGIN
   VALID UNTIL 'infinity';
COMMENT ON ROLE logistics2
  IS 'logistics2';
  
CREATE TABLESPACE logistics2
  OWNER logistics2
  LOCATION '/opt/PostgreSQL/tblspace/logistics2';
  
 CREATE DATABASE logistics2
  WITH ENCODING='UTF8'
       OWNER=logistics2
       CONNECTION LIMIT=-1
       TABLESPACE=logistics2;
