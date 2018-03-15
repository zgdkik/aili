INSERT INTO QRTZ_FIRED_TRIGGERS (
	SCHED_NAME,
	ENTRY_ID,
	TRIGGER_NAME,
	TRIGGER_GROUP,
	INSTANCE_NAME,
	FIRED_TIME,
	SCHED_TIME,
	STATE,
	JOB_NAME,
	JOB_GROUP,
	IS_NONCONCURRENT,
	REQUESTS_RECOVERY,
	PRIORITY
)
VALUES
	(
		'quartzScheduler',
		'NON_CLUSTERED1449744470726',
		'UseCarPlanJob_trigger',
		'DEFAULT',
		'NON_CLUSTERED',
		1450845970058,
		1450845980000,
		'ACQUIRED',
		NULL,
		NULL,
		FALSE,
		FALSE,
		5
	);

INSERT INTO QRTZ_JOB_DETAILS (
	SCHED_NAME,
	JOB_NAME,
	JOB_GROUP,
	DESCRIPTION,
	JOB_CLASS_NAME,
	IS_DURABLE,
	IS_NONCONCURRENT,
	IS_UPDATE_DATA,
	REQUESTS_RECOVERY,
	JOB_DATA
)
VALUES
	(
		'quartzScheduler',
		'UseCarPlanJob',
		'DEFAULT',
		'description',
		'com.feisuo.sds.job.usecar.UseCarPlanJob',
		FALSE,
		FALSE,
		FALSE,
		FALSE,
		null
	);

INSERT INTO QRTZ_TRIGGERS (
	SCHED_NAME,
	TRIGGER_NAME,
	TRIGGER_GROUP,
	JOB_NAME,
	JOB_GROUP,
	DESCRIPTION,
	NEXT_FIRE_TIME,
	PREV_FIRE_TIME,
	TRIGGER_STATE,
	TRIGGER_TYPE,
	START_TIME,
	END_TIME,
	CALENDAR_NAME,
	MISFIRE_INSTR,
	JOB_DATA,
	PRIORITY
)
VALUES
	(
		'quartzScheduler',
		'UseCarPlanJob_trigger',
		'DEFAULT',
		'UseCarPlanJob',
		'DEFAULT',
		NULL,
		1449744480000,
		- 1,
		'WAITING',
		'CRON',
		1449744471000,
		0,
		NULL,
		0,
		'',
		5
	);

INSERT INTO QRTZ_CRON_TRIGGERS (
		SCHED_NAME,
		TRIGGER_NAME,
		TRIGGER_GROUP,
		CRON_EXPRESSION,
		TIME_ZONE_ID
	)
VALUES
	(
		'quartzScheduler',
		'UseCarPlanJob_trigger',
		'DEFAULT',
		'0/10 * * * * ?',
		'Asia/Shanghai'
	)