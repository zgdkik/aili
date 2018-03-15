--收集异常统计数据
CREATE OR REPLACE PROCEDURE PRO_COLLECTEXPDATA is
  mintime timestamp;
  maxtime timestamp;
  successCount NUMBER(8);
begin
  INSERT INTO T_ESB_EXCEPTION_TEMP (
    FID,
    MSGID,
    SVCCODE,
    CREATETIME,
    EXCPTIONCODE,
    BIZ1,
    BIZ2,
    RETRYCOUNT,
    STATSTCFLG,
    FROMENDPOINTURI,
    ISAUTORESEND,
    REDOTYPE,
    REQUESTID,
    ISSUCCESS
  )
    SELECT
      FID,
      MSGID,
      SVCCODE,
      CREATETIME,
      EXCPTIONCODE,
      BIZ1,
      BIZ2,
      RETRYCOUNT,
      STATSTCFLG,
      FROMENDPOINTURI,
      ISAUTORESEND,
      REDOTYPE,
      REQUESTID,
      ISSUCCESS
    FROM T_ESB_EXCEPTION e where e.STATSTCFLG = 0;

  select  min(CREATETIME),max(CREATETIME) into mintime,maxtime from T_ESB_EXCEPTION_TEMP;

	FOR ACC_CUR IN (
	  SELECT COUNT(T.SVCCODE) failCount, SVCCODE FROM T_ESB_EXCEPTION_TEMP T
	  GROUP BY T.SVCCODE
	)
	LOOP
    SELECT COUNT(1) INTO successCount
    FROM T_ESB_PERFORMANCE P
    WHERE P.CREATETIME > mintime
      AND P.CREATETIME < maxtime
      AND P.SVCCODE=ACC_CUR.SVCCODE;

    INSERT INTO T_ESB_EXCPTNSTATISTICS (
        FID,
        SVCCODE,
        CALLEDCOUNT,
        EXCPTNCOUNT,
        CREATETIME,
        STARTTIME,
        ENDTIME
      )
      SELECT SYS_GUID() as FID,
           ACC_CUR.SVCCODE as SVCCODE,
           (successCount + ACC_CUR.failCount) as CALLEDCOUNT,
           ACC_CUR.failCount as EXCPTNCOUNT,
           sysdate as CREATETIME,
           min(T.CREATETIME) as STARTTIME,
           max(T.CREATETIME) as ENDTIME
      from T_ESB_EXCEPTION_TEMP T
      WHERE T.SVCCODE=ACC_CUR.SVCCODE
    ;
  END LOOP;

  UPDATE T_ESB_EXCEPTION e set e.STATSTCFLG = 1
  where e.STATSTCFLG = 0
  and exists (select 1 from T_ESB_EXCEPTION_TEMP et where et.FID = e.FID);

  COMMIT;
EXCEPTION
  WHEN OTHERS
  THEN
    dbms_output.put_line(sqlcode|| ': '||sqlerrm);
    ROLLBACK;
end pro_collectExpData;
/



--收集性能统计数据
CREATE OR REPLACE PROCEDURE pro_collectPfmData is
begin
  INSERT INTO T_ESB_PERFORMANCE_TEMP (
    FID,
    CAMELCONTEXTID,
    ROUTEID,
    SVCCODE,
    FROMENDPOINT,
    TIMECOSTS,
    CREATETIME,
    TOENDPOINT,
    STATISTICSFLG
  )
  SELECT
    FID,
    CAMELCONTEXTID,
    ROUTEID,
    SVCCODE,
    FROMENDPOINT,
    TIMECOSTS,
    CREATETIME,
    TOENDPOINT,
    STATISTICSFLG
  FROM T_ESB_PERFORMANCE p where p.STATISTICSFLG = 0;

  insert into T_ESB_PFMCSTATISTICS (
    FID,
    SVCCODE,
    CALLEDCOUNT,
    MINRSPTIME,
    MAXRSPTIME,
    AVGRSPTIME,
    CREATETIME,
    STARTTIME,
    ENDTIME
  )
  select sys_guid()      as FID,
         SVCCODE         as SVCCODE,
         count(SVCCODE)  as CALLEDCOUNT,
         min(TIMECOSTS)  as MINRSPTIME,
         max(TIMECOSTS)  as MAXRSPTIME,
         CEIL(avg(TIMECOSTS))  as AVGRSPTIME,
         sysdate         as CREATETIME,
         min(CREATETIME) as STARTTIME,
         max(CREATETIME) as ENDTIME
  from T_ESB_PERFORMANCE_TEMP p
  where p.STATISTICSFLG = 0
  group by p.SVCCODE;

  update T_ESB_PERFORMANCE p set p.STATISTICSFLG = 1
  where p.STATISTICSFLG = 0
  and exists (select 1 from T_ESB_PERFORMANCE_TEMP pt where pt.FID = p.FID);

  COMMIT;
EXCEPTION
  WHEN OTHERS
  THEN
    dbms_output.put_line(sqlcode|| ': '||sqlerrm);
    ROLLBACK;
end pro_collectPfmData;





--这里的所有scripts都要在sqlplus中执行
--oracle定时任务：收集性能统计数据
BEGIN
sys.dbms_scheduler.create_job(
job_name => 'JOB_COLLECT_PERF_DATA',
job_type => 'STORED_PROCEDURE',
job_action => 'PRO_COLLECTPFMDATA',
repeat_interval => 'FREQ=MINUTELY;INTERVAL=5',
start_date => systimestamp at time zone '+8:00',
job_class => 'DEFAULT_JOB_CLASS',
auto_drop => FALSE,
enabled => TRUE);
END;
/

--oracle定时任务：收集异常统计数据
BEGIN
sys.dbms_scheduler.create_job(
job_name => 'JOB_COLLECT_EXPN_DATA',
job_type => 'STORED_PROCEDURE',
job_action => 'PRO_COLLECTEXPDATA',
repeat_interval => 'FREQ=MINUTELY;INTERVAL=5',
start_date => systimestamp at time zone '+8:00',
job_class => 'DEFAULT_JOB_CLASS',
auto_drop => FALSE,
enabled => TRUE);
END;
/


