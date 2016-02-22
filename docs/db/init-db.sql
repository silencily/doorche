CREATE DATABASE  IF NOT EXISTS `doorche` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `doorche`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: doorche
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
  `ID` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PARENT_ID` int(12) DEFAULT NULL COMMENT '父级ID',
  `TYPE_NAME` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '类型名称',
  `TYPE_CODE` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '类型编码',
  `NAME` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '显示名',
  `CODE` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '编码',
  `SORT` int(10) DEFAULT NULL COMMENT '排序',
  `CREATE_BY` int(12) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` int(12) DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETED` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `VERSION` int(20) NOT NULL COMMENT '乐观锁',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统字典编码表';
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
  `ID` int(12) NOT NULL AUTO_INCREMENT,
  `PARAM_TYPE` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '参数类型',
  `PARAM_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `PARAM_KEY` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '参数标识',
  `PARAM_VALUE` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '参数值',
  `REMARKS` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '说明',
  `CREATE_BY` int(12) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` int(12) DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETED` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `VERSION` int(20) NOT NULL COMMENT '乐观锁',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统参数表';
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
  `ID` int(12) NOT NULL COMMENT '主键',
  `PARENT_ID` int(12) DEFAULT NULL COMMENT '父级ID',
  `TYPE` char(1) COLLATE utf8_bin NOT NULL COMMENT '权限类型：目录--0；菜单--1；操作--2',
  `NAEM` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `SORT` int(10) DEFAULT NULL COMMENT '排序',
  `HREF` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '链接',
  `ICON` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `PERMISSION` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '权限标识',
  `IS_SHOW` char(1) COLLATE utf8_bin DEFAULT '1' COMMENT '是否显示：0-不显示；1-显示',
  `CREATE_BY` int(12) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` int(12) DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETED` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `VERSION` int(20) NOT NULL COMMENT '乐观锁',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_permission`
--

LOCK TABLES `t_sm_permission` WRITE;
/*!40000 ALTER TABLE `t_sm_permission` DISABLE KEYS */;
INSERT INTO `t_sm_permission` VALUES (1,NULL,'0','系统管理',10,NULL,NULL,NULL,'1',1,NULL,1,NULL,'0',1),(2,1,'1','权限管理',1010,'/sm/permission',NULL,NULL,'1',1,NULL,1,NULL,'0',1),(3,1,'1','角色管理',1020,'/sm/role',NULL,NULL,'1',1,NULL,1,NULL,'0',1),(4,1,'1','用户管理',1030,'/sm/user',NULL,NULL,'1',1,NULL,1,NULL,'0',1);
/*!40000 ALTER TABLE `t_sm_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_role`
--

DROP TABLE IF EXISTS `t_sm_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_role` (
  `ID` int(12) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `CODE` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '角色编码',
  `DATA_SCOPE` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '数据范围',
  `IS_DISABLE` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `CREATE_BY` int(12) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` int(12) DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETED` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `VERSION` int(20) NOT NULL COMMENT '乐观锁',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_role`
--

LOCK TABLES `t_sm_role` WRITE;
/*!40000 ALTER TABLE `t_sm_role` DISABLE KEYS */;
INSERT INTO `t_sm_role` VALUES (1,'管理员','admin','0','0',1,NULL,1,NULL,'0',1);
/*!40000 ALTER TABLE `t_sm_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_role_permission`
--

DROP TABLE IF EXISTS `t_sm_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_role_permission` (
  `ROLE_ID` int(12) NOT NULL COMMENT '角色ID',
  `PERMISSION_ID` int(12) NOT NULL COMMENT '权限ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_role_permission`
--

LOCK TABLES `t_sm_role_permission` WRITE;
/*!40000 ALTER TABLE `t_sm_role_permission` DISABLE KEYS */;
INSERT INTO `t_sm_role_permission` VALUES (1,1),(1,2),(1,3),(1,4);
/*!40000 ALTER TABLE `t_sm_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_user`
--

DROP TABLE IF EXISTS `t_sm_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `LOGIN_NAME` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '登录名',
  `PASSWORD` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '登录密码',
  `NO` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '用户编号',
  `NAME` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '用户姓名',
  `EMAIL` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `IS_DISABLE` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '是否禁用：1-禁用；0-启用',
  `LOGIN_IP` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '最后登录IP',
  `LOGIN_TIME` datetime DEFAULT NULL COMMENT '最后登录时间',
  `CREATE_BY` int(12) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` int(12) DEFAULT NULL COMMENT '更新人',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  `IS_DELETED` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  `VERSION` int(20) NOT NULL COMMENT '乐观锁',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `LOGIN_NAME_UNIQUE` (`LOGIN_NAME`),
  UNIQUE KEY `PASSWORD_UNIQUE` (`PASSWORD`),
  UNIQUE KEY `NO_UNIQUE` (`NO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统管理-用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sm_user`
--

LOCK TABLES `t_sm_user` WRITE;
/*!40000 ALTER TABLE `t_sm_user` DISABLE KEYS */;
INSERT INTO `t_sm_user` VALUES (1,'admin','fa759a1429fb49016b2bd286fb1f3063','1','管理员','silencily@126.com','13501220454','0',NULL,NULL,1,NULL,1,NULL,'0',1);
/*!40000 ALTER TABLE `t_sm_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sm_user_role`
--

DROP TABLE IF EXISTS `t_sm_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sm_user_role` (
  `USER_ID` int(12) NOT NULL COMMENT '用户ID',
  `ROLE_ID` int(12) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系表';
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

-- Dump completed on 2016-02-22 11:25:56
