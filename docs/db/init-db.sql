CREATE DATABASE  IF NOT EXISTS `portal` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `portal`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: portal
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_sm_dict`
--

DROP TABLE IF EXISTS `t_sm_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_dict` (
  `ID` int(12) NOT NULL,
  `PARENT_ID` int(12) DEFAULT NULL,
  `TYPE_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_CODE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `CODE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `SORT` int(10) DEFAULT NULL,
  `CREATE_BY` int(12) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` int(12) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` char(1) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_dict`
--

LOCK TABLES `t_sm_dict` WRITE;
/*!40000 ALTER TABLE `t_sm_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sm_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_parameter`
--

DROP TABLE IF EXISTS `t_sm_parameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_parameter` (
  `ID` int(12) NOT NULL,
  `PARAM_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PARAM_KEY` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PARAM_VALUE` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `REMARKS` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_BY` int(12) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` int(12) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` char(1) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_parameter`
--

LOCK TABLES `t_sm_parameter` WRITE;
/*!40000 ALTER TABLE `t_sm_parameter` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sm_parameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_permission`
--

DROP TABLE IF EXISTS `t_sm_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_permission` (
  `ID` int(12) NOT NULL,
  `PARENT_ID` int(12) DEFAULT NULL,
  `TYPE` char(1) COLLATE utf8_bin DEFAULT NULL,
  `NAEM` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `SORT` int(10) DEFAULT NULL,
  `HREF` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `ICON` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PERMISSION` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `IS_SHOW` char(1) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_BY` int(12) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` int(12) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` char(1) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_permission`
--

LOCK TABLES `t_sm_permission` WRITE;
/*!40000 ALTER TABLE `t_sm_permission` DISABLE KEYS */;
INSERT INTO `t_sm_permission` VALUES (1,NULL,'0','系统管理',10,NULL,NULL,NULL,'1',1,NULL,1,NULL,'0',0),(2,1,'1','权限管理',1010,'/sys/permission',NULL,NULL,'1',1,NULL,1,NULL,'0',0),(3,1,'1','角色管理',1020,'/sys/role',NULL,NULL,'1',1,NULL,1,NULL,'0',0),(4,1,'1','用户管理',1030,'/sys/user',NULL,NULL,'1',1,NULL,1,NULL,'0',0),(5,NULL,'0','内容管理',20,NULL,NULL,NULL,'1',1,NULL,1,NULL,'0',0),(6,5,'1','文章管理',2010,'/cms/article',NULL,NULL,'1',1,NULL,1,NULL,'0',0);
/*!40000 ALTER TABLE `t_sm_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_role`
--

DROP TABLE IF EXISTS `t_sm_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_role` (
  `ID` int(12) NOT NULL,
  `NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `CODE` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `DATA_SCOPE` char(1) COLLATE utf8_bin DEFAULT NULL,
  `IS_DISABLE` char(1) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_BY` int(12) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` int(12) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` char(1) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_role`
--

LOCK TABLES `t_sm_role` WRITE;
/*!40000 ALTER TABLE `t_sm_role` DISABLE KEYS */;
INSERT INTO `t_sm_role` VALUES (1,'管理员','admin','3','1',1,NULL,1,NULL,'0',0);
/*!40000 ALTER TABLE `t_sm_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_role_permission`
--

DROP TABLE IF EXISTS `t_sm_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_role_permission` (
  `ROLE_ID` int(12) DEFAULT NULL,
  `PERMISSION_ID` int(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_role_permission`
--

LOCK TABLES `t_sm_role_permission` WRITE;
/*!40000 ALTER TABLE `t_sm_role_permission` DISABLE KEYS */;
INSERT INTO `t_sm_role_permission` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6);
/*!40000 ALTER TABLE `t_sm_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_user`
--

DROP TABLE IF EXISTS `t_sm_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_user` (
  `ID` int(12) NOT NULL,
  `LOGIN_NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `NO` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `MOBILE` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `IS_DISABLE` char(1) COLLATE utf8_bin DEFAULT NULL,
  `LOGIN_IP` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `LOGIN_TIME` datetime DEFAULT NULL,
  `CREATE_BY` int(12) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_BY` int(12) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `IS_DELETED` char(1) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` int(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_user`
--

LOCK TABLES `t_sm_user` WRITE;
/*!40000 ALTER TABLE `t_sm_user` DISABLE KEYS */;
INSERT INTO `t_sm_user` VALUES (1,'admin','33d1ed7d456b434d96ce7401204cd917','admin','管理员','silencily@126.com','13501220454','1','127.0.0.1','2015-09-03 00:00:00',1,'2015-09-03 00:00:00',1,'2015-09-03 00:00:00','0',0);
/*!40000 ALTER TABLE `t_sm_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_user_role`
--

DROP TABLE IF EXISTS `t_sm_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_user_role` (
  `USER_ID` int(12) DEFAULT NULL,
  `ROLE_ID` int(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_user_role`
--

LOCK TABLES `t_sm_user_role` WRITE;
/*!40000 ALTER TABLE `t_sm_user_role` DISABLE KEYS */;
INSERT INTO `t_sm_user_role` VALUES (1,1);
/*!40000 ALTER TABLE `t_sm_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-04 23:36:16
