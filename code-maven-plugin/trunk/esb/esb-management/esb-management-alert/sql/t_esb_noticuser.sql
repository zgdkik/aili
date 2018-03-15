-- Add/modify columns 
alter table T_ESB_NOTICUSER add pjVersion varchar2(15);
-- Add comments to the columns 
comment on column T_ESB_NOTICUSER.pjVersion
  is '项目版本';