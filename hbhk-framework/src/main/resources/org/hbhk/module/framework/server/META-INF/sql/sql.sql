--用户表Users
    CREATE TABLE t_users (
       -- 账号是否有限 1. 是 0.否
       enable int(11) default NULL,
       password varchar(255) default NULL,
       account varchar(255) default NULL,
       id int(11) NOT NULL auto_increment,
       PRIMARY KEY  (id)
    )
 
   --角色表Roles
   CREATE TABLE t_roles (
     enable int(11) default NULL,
     name varchar(255) default NULL,
     id int(11) NOT NULL auto_increment,
     PRIMARY KEY  (id)
   )
 
   3--用户_角色表users_roles
   CREATE TABLE t_users_roles (
     --用户表的外键
     uid int(11) default NULL,
     --角色表的外键
     rid int(11) default NULL,
     urId int(11) NOT NULL auto_increment,
     PRIMARY KEY  (urId),
     KEY rid (rid),
     KEY uid (uid),
    CONSTRAINT users_roles_ibfk_1 FOREIGN KEY (rid) REFERENCES roles (id),
    CONSTRAINT users_roles_ibfk_2 FOREIGN KEY (uid) REFERENCES users (id)
   )
 
   --资源表resources
   CREATE TABLE t_resources (
     memo varchar(255) default NULL,
     -- 权限所对应的url地址
     url varchar(255) default NULL,
     --优先权
     priority int(11) default NULL,
     --类型
     type int(11) default NULL,
     --权限所对应的编码，例201代表发表文章
     name varchar(255) default NULL,
     id int(11) NOT NULL auto_increment,
     PRIMARY KEY  (id)
   )
 
   --角色_资源表roles_resources
    CREATE TABLE t_roles_resources (
      rsid int(11) default NULL,
      rid int(11) default NULL,
      rrId int(11) NOT NULL auto_increment,
      PRIMARY KEY  (rrId),
      KEY rid (rid),
      KEY roles_resources_ibfk_2 (rsid),
      CONSTRAINT roles_resources_ibfk_2 FOREIGN KEY (rsid) REFERENCES resources (id),
      CONSTRAINT roles_resources_ibfk_1 FOREIGN KEY (rid) REFERENCES roles (id)
      )
 