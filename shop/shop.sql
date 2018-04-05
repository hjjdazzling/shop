-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` char(11) NOT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'hjj','haojunjie','18700860366',100210,'2018-04-02 08:08:58');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliver`
--

DROP TABLE IF EXISTS `deliver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deliver` (
  `d_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` char(11) NOT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`d_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliver`
--

LOCK TABLES `deliver` WRITE;
/*!40000 ALTER TABLE `deliver` DISABLE KEYS */;
INSERT INTO `deliver` VALUES (1,'中国邮政','dazzling.++','18700860366',100000,'2018-04-02 08:11:24'),(3,'EMS','dazzling.++','18700860366',100000,'2018-03-29 09:35:11'),(4,'hjj','hjj','18700860366',10010,'2018-04-03 07:31:34'),(6,'hjjj','hjj','1275',32,'2018-04-02 08:08:58');
/*!40000 ALTER TABLE `deliver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliver_record`
--

DROP TABLE IF EXISTS `deliver_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `deliver_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `d_id` int(11) DEFAULT NULL,
  `p_id` int(11) DEFAULT NULL,
  `type` enum('送货','等待确认收货','确认收货','退货','等待确认退货','确认退货') DEFAULT NULL,
  `address` varchar(200) NOT NULL,
  `target_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `p_id` (`p_id`),
  KEY `d_id` (`d_id`),
  CONSTRAINT `deliver_record_ibfk_1` FOREIGN KEY (`p_id`) REFERENCES `purchase_record` (`p_id`),
  CONSTRAINT `deliver_record_ibfk_2` FOREIGN KEY (`d_id`) REFERENCES `deliver` (`d_id`),
  CONSTRAINT `deliver_record_ibfk_3` FOREIGN KEY (`d_id`) REFERENCES `deliver` (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliver_record`
--

LOCK TABLES `deliver_record` WRITE;
/*!40000 ALTER TABLE `deliver_record` DISABLE KEYS */;
INSERT INTO `deliver_record` VALUES (2,1,3,'送货','发货中','陕西省渭南市蒲城县龙池镇'),(6,1,8,'送货','发货中','陕西省渭南市蒲城县龙池镇'),(9,4,15,'等待确认收货','陕西省渭南市蒲城县龙池镇','陕西省渭南市蒲城县龙池镇'),(11,4,4,'退货','发货中','陕西省');
/*!40000 ALTER TABLE `deliver_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `good`
--

DROP TABLE IF EXISTS `good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `good` (
  `g_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `function` varchar(200) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`g_id`),
  KEY `FK6ooay4v720wt7axa7vbm1aaeg` (`s_id`),
  CONSTRAINT `FK6ooay4v720wt7axa7vbm1aaeg` FOREIGN KEY (`s_id`) REFERENCES `shop` (`s_id`),
  CONSTRAINT `good_ibfk_1` FOREIGN KEY (`s_id`) REFERENCES `shop` (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good`
--

LOCK TABLES `good` WRITE;
/*!40000 ALTER TABLE `good` DISABLE KEYS */;
INSERT INTO `good` VALUES (1,4,'水杯','生活用品',80),(2,5,'水杯','生活用品',80),(4,4,'薯片','食物',5),(5,5,'可乐','食物',5),(6,4,'橡皮','文具',1);
/*!40000 ALTER TABLE `good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_record`
--

DROP TABLE IF EXISTS `purchase_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_record` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `g_id` int(11) DEFAULT NULL,
  `number` int(10) unsigned NOT NULL,
  `situation` enum('送货','退货','确认收货','退货成功') DEFAULT NULL,
  PRIMARY KEY (`p_id`),
  KEY `u_id` (`u_id`),
  KEY `g_id` (`g_id`),
  CONSTRAINT `purchase_record_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`),
  CONSTRAINT `purchase_record_ibfk_2` FOREIGN KEY (`g_id`) REFERENCES `good` (`g_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_record`
--

LOCK TABLES `purchase_record` WRITE;
/*!40000 ALTER TABLE `purchase_record` DISABLE KEYS */;
INSERT INTO `purchase_record` VALUES (1,1,4,10,'送货'),(2,2,4,1,'确认收货'),(3,2,2,1,'送货'),(4,2,2,1,'退货'),(5,2,2,4,'确认收货'),(8,2,1,1,'送货'),(13,2,5,10,'确认收货'),(15,2,4,20,'送货');
/*!40000 ALTER TABLE `purchase_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` char(11) NOT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`s_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'hjj','hjj','18700860366',10000,'陕西省渭南市蒲城县龙池镇','2018-04-03 08:46:21'),(4,'haojunjie','haojunjie','18700860366',5,'陕西省','2018-04-03 07:31:34'),(5,'dazzling','1275119618','18700860366',50,'陕西省','2018-04-03 07:31:34'),(7,'ntgyy','ntgyy','18700860366',10000,'陕西省渭南市蒲城县','2018-04-03 08:57:59'),(8,'123','123','123',123,'123','2018-04-03 08:59:43');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_car_record`
--

DROP TABLE IF EXISTS `shop_car_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shop_car_record` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `g_id` int(11) DEFAULT NULL,
  `number` int(10) unsigned NOT NULL,
  PRIMARY KEY (`r_id`),
  KEY `u_id` (`u_id`),
  KEY `g_id` (`g_id`),
  CONSTRAINT `shop_car_record_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `user` (`u_id`),
  CONSTRAINT `shop_car_record_ibfk_2` FOREIGN KEY (`g_id`) REFERENCES `good` (`g_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_car_record`
--

LOCK TABLES `shop_car_record` WRITE;
/*!40000 ALTER TABLE `shop_car_record` DISABLE KEYS */;
INSERT INTO `shop_car_record` VALUES (1,1,4,10),(2,2,1,1),(3,2,2,10);
/*!40000 ALTER TABLE `shop_car_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` char(11) NOT NULL,
  `money` decimal(10,0) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1275119618','dazzling.++','18700860366',10000,'2018-08-17 16:20:30','陕西省渭南市蒲城县'),(2,'hjj','hjj','1275119618',99690,'2018-04-03 07:31:34','陕西省渭南市蒲城县龙池镇'),(3,'ntgyy','ntgyy','1275119618',0,'2018-03-23 06:14:27',''),(4,'ntgyy1','123','18700860366',0,'2018-03-23 06:40:16','陕西省'),(5,'haojunjie','haojunjie','13636779800',100000,'2018-03-23 07:23:49','陕西省渭南市蒲城县龙池镇'),(6,'j','j','1',1,'2018-03-23 07:50:28','1'),(10,'wwe','wwe','123',123,'2018-03-24 11:47:46','美国');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-05 15:50:15
