
DROP TABLE IF EXISTS t_esb_route;
CREATE TABLE t_esb_route (
  FID varchar(40) NOT NULL,
  ESBSVCCODE varchar(80) NOT NULL,
  TARGETSVCCODE varchar(80) NOT NULL,
  PRIMARY KEY (FID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO t_esb_route VALUES ('1', 'shbhk1', 'hbhk1');
INSERT INTO t_esb_route VALUES ('2', 'shbhk2', 'hbhk2');
