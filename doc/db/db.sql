-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: aoeai-leet-code-study-record
-- ------------------------------------------------------
-- Server version	5.7.21-enterprise-commercial-advanced

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
-- Table structure for table `lcsr_problem`
--

DROP TABLE IF EXISTS `lcsr_problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lcsr_problem` (
  `id` smallint(6) NOT NULL,
  `problem` varchar(500) NOT NULL COMMENT '问题',
  `problem_en` varchar(500) NOT NULL COMMENT '问题的英文版',
  `difficulty` tinyint(4) NOT NULL DEFAULT '1' COMMENT '难度 1：简单；2：中等；3困难',
  `tag` varchar(200) NOT NULL COMMENT '类型 （类型i的集合，例如 1,2)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='LeetCode问题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lcsr_problem_record`
--

DROP TABLE IF EXISTS `lcsr_problem_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lcsr_problem_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `problem_id` smallint(6) NOT NULL COMMENT '问题id',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 0：为删除； 1：已删除',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`),
  KEY `problem_id_index` (`problem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='刷题记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lcsr_user`
--

DROP TABLE IF EXISTS `lcsr_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lcsr_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` char(64) NOT NULL COMMENT '密码',
  `salt` char(64) NOT NULL COMMENT '密码干扰因子',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_index` (`username`) USING BTREE,
  KEY `salt_index` (`salt`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lcsr_user_problem`
--

DROP TABLE IF EXISTS `lcsr_user_problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lcsr_user_problem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `problem_id` smallint(6) NOT NULL COMMENT '问题id',
  `do_count` smallint(6) NOT NULL DEFAULT '0' COMMENT '刷题次数',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '''状态 1：未解答；2：已解答；3：尝试过；4：略懂；5：掌握；6：精通',
  `last_do_time` datetime NOT NULL COMMENT '最后做题时间',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_problem_id_idnex` (`user_id`,`problem_id`),
  KEY `user_id_index` (`user_id`),
  KEY `problem_id_index` (`problem_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='用户刷题记录概要';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-24 12:52:23
