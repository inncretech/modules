-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: masterdb
-- ------------------------------------------------------
-- Server version	5.5.24-0ubuntu0.12.04.1

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
-- Table structure for table `id_entry`
--
drop database userdb1;
drop database userdb2;
drop database sourcedb1;
drop database sourcedb2;
drop database masterdb;

create database userdb1;
create database userdb2;
create database sourcedb1;
create database sourcedb2;
create database masterdb;

use database masterdb;

DROP TABLE IF EXISTS `id_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_entry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_entry`
--

LOCK TABLES `id_entry` WRITE;
/*!40000 ALTER TABLE `id_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `id_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shard_config`
--

DROP TABLE IF EXISTS `shard_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shard_config` (
  `id` int(11) NOT NULL,
  `allow_new` tinyint(1) DEFAULT NULL,
  `jdbc_url` varchar(255) DEFAULT NULL,
  `shard_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shard_config`
--

LOCK TABLES `shard_config` WRITE;
/*!40000 ALTER TABLE `shard_config` DISABLE KEYS */;
INSERT INTO `shard_config` VALUES (1,1,'jdbc:mysql://localhost:3306/userdb1',0),(2,1,'jdbc:mysql://localhost:3306/userdb2',0),(8001,1,'jdbc:mysql://localhost:3306/sourcedb2',1),(8002,1,'jdbc:mysql://localhost:3306/sourcedb1',1);
/*!40000 ALTER TABLE `shard_config` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-06-09 14:33:33
