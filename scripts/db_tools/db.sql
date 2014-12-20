-- MySQL dump 10.13  Distrib 5.5.40, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: meanmcq
-- ------------------------------------------------------
-- Server version	5.5.40-0ubuntu0.14.04.1

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mcq_test_id` bigint(20) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6bwpwrl53ggls7hjjhyv28cfh` (`mcq_test_id`),
  KEY `FK_eix9du6u2r4wxwu415wq8yb99` (`question_id`),
  KEY `FK_ilrlwe1trc8dyqaius89vprop` (`user_id`),
  CONSTRAINT `FK_6bwpwrl53ggls7hjjhyv28cfh` FOREIGN KEY (`mcq_test_id`) REFERENCES `mcq_test` (`id`),
  CONSTRAINT `FK_eix9du6u2r4wxwu415wq8yb99` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FK_ilrlwe1trc8dyqaius89vprop` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_choices`
--

DROP TABLE IF EXISTS `answer_choices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer_choices` (
  `answer_id` bigint(20) NOT NULL,
  `choices_id` bigint(20) NOT NULL,
  PRIMARY KEY (`answer_id`,`choices_id`),
  KEY `FK_2uo9y8955y5c5gejrd7ermixp` (`choices_id`),
  CONSTRAINT `FK_2uo9y8955y5c5gejrd7ermixp` FOREIGN KEY (`choices_id`) REFERENCES `choice` (`id`),
  CONSTRAINT `FK_qk8yqpcakxoatkq6pnb4sl5l4` FOREIGN KEY (`answer_id`) REFERENCES `answer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_choices`
--

LOCK TABLES `answer_choices` WRITE;
/*!40000 ALTER TABLE `answer_choices` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer_choices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choice`
--

DROP TABLE IF EXISTS `choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `choice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer` bit(1) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `question_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bahktruw1td7ggbu6db625s28` (`question_id`),
  CONSTRAINT `FK_bahktruw1td7ggbu6db625s28` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choice`
--

LOCK TABLES `choice` WRITE;
/*!40000 ALTER TABLE `choice` DISABLE KEYS */;
INSERT INTO `choice` VALUES (1,'','Choice 3',1),(2,'\0','Choice 2',1),(3,'\0','Choice 1',1),(4,'\0','Choice 4',1),(5,'\0','Choice 4',2),(6,'\0','Choice 1',2),(7,'\0','Choice 2',2),(8,'','Choice 3',2),(9,'\0','Choice 1',3),(10,'','Choice 3',3),(11,'\0','Choice 4',3),(12,'\0','Choice 2',3),(13,'\0','Choice 2',4),(14,'','Choice 3',4),(15,'\0','Choice 1',4),(16,'\0','Choice 4',4),(17,'\0','Choice 2',5),(18,'\0','Choice 1',5),(19,'','Choice 3',5),(20,'\0','Choice 4',5),(21,'\0','Choice 4',6),(22,'\0','Choice 1',6),(23,'','Choice 3',6),(24,'\0','Choice 2',6),(25,'\0','Choice 2',7),(26,'','Choice 3',7),(27,'\0','Choice 4',7),(28,'\0','Choice 1',7),(29,'\0','Choice 1',8),(30,'','Choice 3',8),(31,'\0','Choice 2',8),(32,'\0','Choice 4',8),(33,'\0','Choice 1',9),(34,'\0','Choice 2',9),(35,'\0','Choice 4',9),(36,'','Choice 3',9),(37,'\0','Choice 1',10),(38,'\0','Choice 4',10),(39,'\0','Choice 2',10),(40,'','Choice 3',10);
/*!40000 ALTER TABLE `choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mcq_result`
--

DROP TABLE IF EXISTS `mcq_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mcq_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `marks` double NOT NULL,
  `mcq_test_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_12veo32jj126kf22x9o7snifg` (`mcq_test_id`),
  KEY `FK_2ynhk4tsepucukcc1uaw6tgrh` (`user_id`),
  CONSTRAINT `FK_12veo32jj126kf22x9o7snifg` FOREIGN KEY (`mcq_test_id`) REFERENCES `mcq_test` (`id`),
  CONSTRAINT `FK_2ynhk4tsepucukcc1uaw6tgrh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mcq_result`
--

LOCK TABLES `mcq_result` WRITE;
/*!40000 ALTER TABLE `mcq_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `mcq_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mcq_test`
--

DROP TABLE IF EXISTS `mcq_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mcq_test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `duration` int(11) NOT NULL,
  `schedule` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mcq_test`
--

LOCK TABLES `mcq_test` WRITE;
/*!40000 ALTER TABLE `mcq_test` DISABLE KEYS */;
INSERT INTO `mcq_test` VALUES (1,30,'2014-12-10 01:18:01');
/*!40000 ALTER TABLE `mcq_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mcq_test_questions`
--

DROP TABLE IF EXISTS `mcq_test_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mcq_test_questions` (
  `mcq_test_id` bigint(20) NOT NULL,
  `questions_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mcq_test_id`,`questions_id`),
  KEY `FK_1exgw8rtgk7xo784mnlr9wdvs` (`questions_id`),
  CONSTRAINT `FK_1exgw8rtgk7xo784mnlr9wdvs` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FK_jr1qqdpqqugaj5lefh4nja6qc` FOREIGN KEY (`mcq_test_id`) REFERENCES `mcq_test` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mcq_test_questions`
--

LOCK TABLES `mcq_test_questions` WRITE;
/*!40000 ALTER TABLE `mcq_test_questions` DISABLE KEYS */;
INSERT INTO `mcq_test_questions` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10);
/*!40000 ALTER TABLE `mcq_test_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mcq_test_users`
--

DROP TABLE IF EXISTS `mcq_test_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mcq_test_users` (
  `mcq_test_id` bigint(20) NOT NULL,
  `users_id` bigint(20) NOT NULL,
  PRIMARY KEY (`mcq_test_id`,`users_id`),
  KEY `FK_c1suvlo479knqqiuc5bi11gyu` (`users_id`),
  CONSTRAINT `FK_c1suvlo479knqqiuc5bi11gyu` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_g2c4r20yte3lp86uj2bj8xiv0` FOREIGN KEY (`mcq_test_id`) REFERENCES `mcq_test` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mcq_test_users`
--

LOCK TABLES `mcq_test_users` WRITE;
/*!40000 ALTER TABLE `mcq_test_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `mcq_test_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'This is question 1?'),(2,'This is question 2?'),(3,'This is question 3?'),(4,'This is question 4?'),(5,'This is question 5?'),(6,'This is question 6?'),(7,'This is question 7?'),(8,'This is question 8?'),(9,'This is question 9?'),(10,'This is question 10?');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'pass','EXAMINER','Teacher'),(2,'pass','STUDENT','Student1'),(3,'pass','STUDENT','Student2'),(4,'pass','STUDENT','Student3'),(5,'pass','STUDENT','Student4'),(6,'pass','STUDENT','Student5'),(7,'pass','STUDENT','Student6'),(8,'pass','STUDENT','Student7'),(9,'pass','STUDENT','Student8'),(10,'pass','STUDENT','Student9'),(11,'pass','STUDENT','Student10');
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

-- Dump completed on 2014-12-10 23:01:20
