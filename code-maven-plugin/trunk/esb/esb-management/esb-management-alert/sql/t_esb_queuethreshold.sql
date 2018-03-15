-- Add/modify columns 
alter table T_ESB_QUEUETHRESHOLD add COMPARETIME NUMBER(3);

-- Add comments to the columns 
comment on column T_ESB_QUEUETHRESHOLD.COMPARETIME
  is '对比次数';
  
-- Add/modify columns 
alter table T_ESB_QUEUETHRESHOLD add VOLATILITY NUMBER(36);
-- Add comments to the columns 
comment on column T_ESB_QUEUETHRESHOLD.VOLATILITY
  is '浮动值';  
  
-- Add/modify columns 
alter table T_ESB_QUEUETHRESHOLD add pjVersion varchar2(15);
-- Add comments to the columns 
comment on column T_ESB_QUEUETHRESHOLD.pjVersion
  is '项目版本';    
  
-- Add/modify columns 
alter table T_ESB_QUEUETHRESHOLD add maxDepth NUMBER(36);
-- Add comments to the columns 
comment on column T_ESB_QUEUETHRESHOLD.maxDepth
  is '最大队列深度告警阀值';   