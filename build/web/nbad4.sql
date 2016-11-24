CREATE DATABASE  IF NOT EXISTS `nbad4` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `nbad4`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: nbad4
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `answerID` int(11) NOT NULL AUTO_INCREMENT,
  `StudyID` varchar(40) NOT NULL DEFAULT '',
  `QuestionID` varchar(40) NOT NULL DEFAULT '',
  `UserName` varchar(40) NOT NULL DEFAULT '',
  `Choice` varchar(40) DEFAULT NULL,
  `DateSubmitted` datetime DEFAULT NULL,
  PRIMARY KEY (`answerID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (4,'101','101demotest','harsh@uncc.edu','3','2016-04-04 16:00:27'),(5,'102','102demotest1','harsh@uncc.edu','e','2016-04-04 16:00:38'),(6,'101','101demotest','harsh@uncc.edu','4','2016-04-05 14:56:41'),(7,'101','101demotest','harsh@uncc.edu','3','2016-04-05 15:16:01'),(8,'X70','X70ZVMXWX','harsh@uncc.edu','CC','2016-04-06 00:15:05');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `QuestionID` varchar(40) NOT NULL DEFAULT '',
  `StudyID` varchar(40) DEFAULT NULL,
  `Question` varchar(50) DEFAULT NULL,
  `AnswerType` varchar(10) DEFAULT NULL,
  `Option1` varchar(40) DEFAULT NULL,
  `Option2` varchar(40) DEFAULT NULL,
  `Option3` varchar(40) DEFAULT NULL,
  `Option4` varchar(40) DEFAULT NULL,
  `Option5` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`QuestionID`),
  KEY `StudyID` (`StudyID`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`StudyID`) REFERENCES `study` (`StudyID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('101demotest','101','select an option','MCQ','1','2','3','4','5'),('102demotest1','102','question2','MCQ','a','b','c','d','e'),('X36dg','X36','fb','MCQ','gr','jjo','mk',NULL,NULL),('X40wefw','X40','gre','MCQ','wetw','iugi','bkb',NULL,NULL),('X4uncc','X4','nbad','MCQ','1','2','3',NULL,NULL),('X64timezone','X64','what is the time','MCQ','IST','EST','PST',NULL,NULL),('X70ZVMXWX','X70','XW','MCQ','DD','CC','E',NULL,NULL),('X93PanamaPapers','X93','what is it','MCQ','20k','30k','40k',NULL,NULL),('XY76Harsh Akotiya','XY76','what is the time','MCQ','5','2','5',NULL,NULL);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recuser`
--

DROP TABLE IF EXISTS `recuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recuser` (
  `Email` varchar(50) DEFAULT NULL,
  `Token` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recuser`
--

LOCK TABLES `recuser` WRITE;
/*!40000 ALTER TABLE `recuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `recuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reported`
--

DROP TABLE IF EXISTS `reported`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reported` (
  `reportID` int(11) NOT NULL AUTO_INCREMENT,
  `QuestionID` varchar(255) NOT NULL,
  `StudyID` varchar(40) NOT NULL DEFAULT '',
  `Date` datetime DEFAULT NULL,
  `Question` varchar(50) DEFAULT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `NumParticipants` int(15) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`reportID`),
  KEY `QuestionID` (`QuestionID`),
  KEY `reported_ibfk_2` (`StudyID`),
  CONSTRAINT `reported_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reported_ibfk_2` FOREIGN KEY (`StudyID`) REFERENCES `study` (`StudyID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reported`
--

LOCK TABLES `reported` WRITE;
/*!40000 ALTER TABLE `reported` DISABLE KEYS */;
INSERT INTO `reported` VALUES (4,'101demotest','101','2016-04-04 15:51:19','test','harsh@uncc.edu',0,'approved'),(5,'X70ZVMXWX','X70','2016-04-06 00:15:07','XW','harsh@uncc.edu',0,'approved'),(6,'101demotest','101','2016-04-06 12:52:05','select an option','kumar@uncc.edu',0,'Pending'),(7,'101demotest','101','2016-04-06 13:07:08','select an option','tarang1@gmail.com',0,'Pending');
/*!40000 ALTER TABLE `reported` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reset`
--

DROP TABLE IF EXISTS `reset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reset` (
  `Email` varchar(50) DEFAULT NULL,
  `Token` varchar(50) DEFAULT NULL,
  `Expiration` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reset`
--

LOCK TABLES `reset` WRITE;
/*!40000 ALTER TABLE `reset` DISABLE KEYS */;
INSERT INTO `reset` VALUES ('harsh@uncc.edu','5f8a6f8f-24c2-4a94-9625-3fc2b45ae693','2016-04-14 00:00:00'),('hakotiya@uncc.edu','276a6ec4-6393-4b4e-8413-16336aac14a5','2016-04-14 00:00:00');
/*!40000 ALTER TABLE `reset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `study` (
  `StudyID` varchar(40) NOT NULL DEFAULT '',
  `StudyName` varchar(40) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `DateCreated` datetime DEFAULT NULL,
  `IMAGEURL` varchar(200) DEFAULT NULL,
  `ReqParticipants` int(15) DEFAULT NULL,
  `ActParticipants` int(15) DEFAULT NULL,
  `SStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`StudyID`),
  KEY `Username` (`Username`),
  CONSTRAINT `study_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES ('101','demotest','nbad','harsh@uncc.edu','2016-03-31 19:14:57','images/mind.jpg',5,7,'start'),('102','demotest1','nbad1','harsh@uncc.edu','2016-04-01 15:41:14','images/mind.jpg',6,3,'start'),('X36','dg','mk','harsh@uncc.edu','2016-04-06 01:59:11','C:\\Users\\Harsh Akotiya\\Documents\\NetBeansProjects\\Harsh_Assignment3\\build\\web\\\\images\\7.jpg',5,0,'stop'),('X4','uncc','nbad','harsh@uncc.edu','2016-04-14 17:28:10','C:\\Users\\Harsh Akotiya\\Documents\\NetBeansProjects\\Harsh_Assignment4\\build\\web\\\\images\\7.jpg',6,0,'start'),('X40','wefw','kn','harsh@uncc.edu','2016-04-06 10:47:44','C:\\Users\\Harsh Akotiya\\Documents\\NetBeansProjects\\Harsh_Assignment3\\build\\web\\/images/8.jpg',5,0,'stop'),('X64','timezone','time','harsh@uncc.edu','2016-04-06 01:11:38','C:\\Users\\Harsh Akotiya\\Documents\\NetBeansProjects\\Harsh_Assignment3\\build\\web\\\\images\\7.jpg',5,0,'stop'),('X70','ZVMXWX','SHJ','harsh@uncc.edu','2016-04-05 21:10:22','C:\\Users\\Harsh Akotiya\\Documents\\NetBeansProjects\\Harsh_Assignment3\\build\\web\\\\images\\10.jpg',5,1,'stop'),('X93','PanamaPapers','money laundering','harsh@uncc.edu','2016-04-06 00:16:38','C:\\Users\\Harsh Akotiya\\Documents\\NetBeansProjects\\Harsh_Assignment3\\build\\web\\\\images\\11.jpg',5,0,'stop'),('XY76','Harsh Akotiya','a','harsh@uncc.edu','2016-04-06 11:59:23','C:\\Users\\Harsh Akotiya\\Documents\\NetBeansProjects\\Harsh_Assignment3\\build\\web\\\\images\\9.jpg',6,0,'stop');
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tempuser`
--

DROP TABLE IF EXISTS `tempuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tempuser` (
  `UName` varchar(40) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Password` varchar(200) DEFAULT NULL,
  `IssueDate` datetime DEFAULT NULL,
  `Token` varchar(50) DEFAULT NULL,
  `Salt` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tempuser`
--

LOCK TABLES `tempuser` WRITE;
/*!40000 ALTER TABLE `tempuser` DISABLE KEYS */;
INSERT INTO `tempuser` VALUES ('neeraj','dembla.neeraj212@gmail.com','04b20f94bc9d9c19023a6e5d88a10563da0f8fece24c0e4d5295d17588fbb9d5','2016-04-17 16:05:11','70967235-0a24-4d24-8bd8-7ca025507a46','jzl1KkGmkccgolBpnrUEyIZ8tpek69sIO8iLMagczt0='),('Harsh Akotiya','Harshakotiya.229@gmail.com','3e825d90a7d8726056317a3b87494abed0c7038f0f14f70a414a7488ccf50245','2016-04-17 16:08:39','9e170330-60c3-450f-830a-f1f9f1f1d23f','LByhiOBu5OBh9yBCboEq6m40IOfAfxdckaY7Tv+Mtfc='),('Harsh Akotiya','harshakotiya.229@uncc.edu','f8299343470aaae663644cd1c619401a300fa760fd8afa237c99007362da3f40','2016-04-17 16:30:45','a2c2b652-7965-4d1d-b4fe-1f2117c61f20','KfXS1b1xjcgyCcIn1lKJmSfd3n1Tkcn4Y/ssX5UCjyM='),('Harsh Akotiya','harshakotiya.229@gmail.com','2ee3ac4510d116c3da0fb0ce9bfcfde1a17d7a1e81c6fc2303f61a35ccda4ad2','2016-04-17 16:32:36','550f56d2-bdd6-4b16-a28d-c75d89a6a340','Sj2Iwf3tBQeNmuu5f3niQR8PsAKxA0wOjeYJyi/Er3w='),('Harsh Akotiya','harshakotiya.229@gmail.com','559e2be5a7b2d543a6f868c30f8f9848f8f6be4d7182473b4f980afac5d5f6c2','2016-04-17 16:40:48','241b4922-c61c-40c6-9e35-80992bde5af7','eQp9AN8Guisc8maZFC91JVUZOQ9AREkq+dAOovqk1K8='),('Harsh Akotiya','harshakotiya.229@gmail.com','7d75b96e027b0257084e1d3958934f7f17d6462e52d8e21861b882114dda968c','2016-04-17 16:58:01','f5b41d60-d391-4495-9a17-2b3f968df139','uaql94+GyHYD6xetGq0P+adpg/OBiXABt11jkbjrIHU=');
/*!40000 ALTER TABLE `tempuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Username` varchar(50) NOT NULL DEFAULT '',
  `Password` varchar(255) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Studies` int(15) DEFAULT NULL,
  `Participation` int(15) DEFAULT NULL,
  `Coins` int(15) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `Salt` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('hakotiya@uncc.edu','7eb21802ea36f4c3108ef56abefdedef80ac14bcb227ce0b716d02ef762a29a0','Participant',0,0,0,'Harsh Akotiya','g4M/RQRTipTFyKJpZNq3yDD+RnmElnsvQoKjdcjhFXk='),('harish@uncc.edu','7eb21802ea36f4c3108ef56abefdedef80ac14bcb227ce0b716d02ef762a29a0','Admin',0,0,0,'admin','g4M/RQRTipTFyKJpZNq3yDD+RnmElnsvQoKjdcjhFXk='),('harsh@uncc.edu','7eb21802ea36f4c3108ef56abefdedef80ac14bcb227ce0b716d02ef762a29a0','Participant',0,7,7,'Harsh Akotiya','g4M/RQRTipTFyKJpZNq3yDD+RnmElnsvQoKjdcjhFXk=');
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

-- Dump completed on 2016-04-17 17:07:37
