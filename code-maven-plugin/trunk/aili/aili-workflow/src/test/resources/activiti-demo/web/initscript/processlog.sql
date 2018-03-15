# --------------------------------------------------------
# Host:                         127.0.0.1
# Server version:               5.1.47-community
# Server OS:                    Win32
# HeidiSQL version:             6.0.0.3603
# Date/time:                    2013-03-26 16:17:13
# --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

# Dumping database structure for activiti-demo
CREATE DATABASE IF NOT EXISTS `activiti-demo` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `activiti-demo`;


# Dumping structure for table activiti-demo.t_flow_log
CREATE TABLE IF NOT EXISTS `t_flow_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FORMID` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `USERID` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `USERTYPE` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `ACTION` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `LOGTIME` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `OPINION` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `TASKID` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `TASKNAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `FLOWID` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `RECORDID` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

# Dumping data for table activiti-demo.t_flow_log: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_flow_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_flow_log` ENABLE KEYS */;


# Dumping structure for table activiti-demo.t_leave
CREATE TABLE IF NOT EXISTS `t_leave` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `leavePerson` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `superior` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `startTime` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `endTime` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `leaveReasons` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `createTime` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `userID` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `domStatus` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

# Dumping data for table activiti-demo.t_leave: ~0 rows (approximately)
/*!40000 ALTER TABLE `t_leave` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_leave` ENABLE KEYS */;


# Dumping structure for table activiti-demo.t_user
CREATE TABLE IF NOT EXISTS `t_user` (
  `ID` int(11) DEFAULT NULL,
  `userName` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

# Dumping data for table activiti-demo.t_user: ~3 rows (approximately)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`ID`, `userName`, `password`) VALUES
	(1, 'zhangsan', '123'),
	(2, 'sa', '123'),
	(3, 'admin', '123');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
