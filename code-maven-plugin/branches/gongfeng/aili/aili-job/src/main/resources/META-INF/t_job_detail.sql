DROP TABLE IF EXISTS t_job_detail;
CREATE TABLE t_job_detail (
  id varchar(255) NOT NULL,
  Job_name varchar(255) NOT NULL,
  job_cls varchar(255) NOT NULL,
  group_name varchar(255) NOT NULL,
  topic_key varchar(255),
  descp varchar(255) NOT NULL,
  status varchar(255) NOT NULL,
  cron varchar(255) NOT NULL,
  PRIMARY KEY (id)
);
