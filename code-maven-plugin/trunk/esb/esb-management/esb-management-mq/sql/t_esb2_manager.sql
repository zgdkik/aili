create table T_ESB2_MANAGER(
	FID varchar(36),
	IP varchar(36) not null,
	PORT number(6) not null,
	CHANNEL varchar(100) not null,
	QUEUENAMEREG varchar(100) not null,
	QMGR varchar(50) not null
);

alter table T_ESB2_MANAGER
  add primary key (FID);
comment on column T_ESB2_MANAGER.FID
  is '主键id';
comment on column T_ESB2_MANAGER.IP
  is 'IP地址';
comment on column T_ESB2_MANAGER.PORT
  is '端口号';
comment on column T_ESB2_MANAGER.CHANNEL
  is '通道';
comment on column T_ESB2_MANAGER.QUEUENAMEREG
  is '队列名称的通配符';
comment on column T_ESB2_MANAGER.QMGR
  is '队列管理器名称';  
