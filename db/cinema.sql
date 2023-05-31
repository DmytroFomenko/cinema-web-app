-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bill_to_pay`
--

DROP TABLE IF EXISTS `bill_to_pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill_to_pay` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `bill_to_pay` varchar(29) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `billToPay_UNIQUE` (`bill_to_pay`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill_to_pay`
--

LOCK TABLES `bill_to_pay` WRITE;
/*!40000 ALTER TABLE `bill_to_pay` DISABLE KEYS */;
INSERT INTO `bill_to_pay` VALUES (3,'UA213223130000026007233566001'),(4,'UA213223130000026007233566002'),(5,'UA213223130000026007233566004'),(7,'UA213223130000026007233566005');
/*!40000 ALTER TABLE `bill_to_pay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `image_name` char(16) DEFAULT NULL,
  `duration` time NOT NULL,
  `description` text,
  `year` smallint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (1,'Аватар','1645896325478961','02:42:00','Джейк Саллі — колишній морський піхотинець, прикутий до інвалідного крісла. Незважаючи на немічне тіло, Джейк в душі, як і раніше залишається воїном. Він отримує завдання здійснити подорож у кілька світлових років до бази землян на планеті Пандора, де корпорації видобувають рідкісний мінерал, що має величезне значення для виходу Землі з енергетичної кризи.',2009),(2,'Джуманджі','1645896325478962','01:40:00','Симпатичне та дотепне пригодницьке фентезі про оживший світ магічної настільної гри, який перетворив невеличке провінційне містечко не непрохідні джунглі, повні диких та небезпечних тварин. В центрі сюжету – історія Алана Перріша, який в дитинстві знайшов загадкову настільну гру під назвою «Джуманджі». Не знаючи про таємничі особливості гри, він переноситься в джунглі. Після 26 років проживання в диких лісах, його звільняють двоє ні про що не підозрюючих підлітків. І тепер їм належить перемогти могутню силу гри, яка випускає всю живність лісів Джуманджі в місто… Фільм «Джуманджі» або ж «Джуманджи» (1995) буде цікаво дивитись українською усім шанувальникам якісного пригодницького кіно.',1995),(3,'Подорож до центру Землі','1645896325478963','01:33:00','Три шукача пригод потрапляють у підземний світ. Вони спускаються туди через вулканічний кратер. Те, що постає перед ними, вражає головних героїв. Тут живуть створіння, які вимерли на поверхні землі тисячі років тому. Мандрівники прямують углиб, де на них чекають інші знахідки.',2008),(4,'Test film','1645896325478964','01:10:00','Test description 1',2022),(5,'Test project','1645896325478965','01:30:00','Description of project',2023);
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','<< Flyway Baseline >>','BASELINE','<< Flyway Baseline >>',NULL,'root','2023-04-14 23:57:35',0,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_data`
--

DROP TABLE IF EXISTS `login_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_data` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) NOT NULL,
  `password` varchar(44) NOT NULL,
  `role_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nickName_UNIQUE` (`nickname`),
  KEY `fk_logindata_usertypes1_idx` (`role_id`),
  CONSTRAINT `fk_login_data_roles1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_data`
--

LOCK TABLES `login_data` WRITE;
/*!40000 ALTER TABLE `login_data` DISABLE KEYS */;
INSERT INTO `login_data` VALUES (1,'Taras','222',2),(3,'Dmytro','111',2),(4,'Andriy','555',2),(5,'Anatoliy','777',1),(6,'Stepan','888',2),(8,'TestAdmin','testAdminPassword',1),(9,'TestClient','testClientPassword',2),(12,'Semen','Taras_Password',2),(13,'Глядач1','1',2),(16,'Глядач3','3',2),(17,'Адмін1','1',1);
/*!40000 ALTER TABLE `login_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `place` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `placement_id` bigint unsigned NOT NULL,
  `place_kind_id` bigint unsigned NOT NULL,
  `number` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_place_place_kind1_idx` (`place_kind_id`),
  KEY `fk_place_placement1_idx` (`placement_id`),
  CONSTRAINT `fk_place_place_kind1` FOREIGN KEY (`place_kind_id`) REFERENCES `place_kind` (`id`),
  CONSTRAINT `fk_place_placement1` FOREIGN KEY (`placement_id`) REFERENCES `placement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,2,2,'row1num1'),(2,2,4,'row1num2'),(3,2,3,'row2num1'),(5,2,2,'row2num2'),(6,2,3,'row2num3'),(7,1,3,'row1num1');
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place_kind`
--

DROP TABLE IF EXISTS `place_kind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `place_kind` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `add_price` decimal(7,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `seatKind_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place_kind`
--

LOCK TABLES `place_kind` WRITE;
/*!40000 ALTER TABLE `place_kind` DISABLE KEYS */;
INSERT INTO `place_kind` VALUES (2,'М\'який стілець',0.00),(3,'test place kind 2',2.00),(4,'Диван',4.00),(5,'test place kind 1',2.75);
/*!40000 ALTER TABLE `place_kind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `placement`
--

DROP TABLE IF EXISTS `placement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `placement` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `image_name` char(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `placementName_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `placement`
--

LOCK TABLES `placement` WRITE;
/*!40000 ALTER TABLE `placement` DISABLE KEYS */;
INSERT INTO `placement` VALUES (1,'Велика поляна','1231231231231231'),(2,'Мала поляна','2342342342342342'),(3,'Test placement 1','3333333333333334');
/*!40000 ALTER TABLE `placement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userTypeKind_UNIQUE` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin'),(2,'client');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seance`
--

DROP TABLE IF EXISTS `seance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seance` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `film_id` bigint unsigned NOT NULL,
  `begin_time` timestamp NOT NULL,
  `base_price` decimal(7,2) unsigned NOT NULL,
  `bill_to_pay_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seance_film1_idx` (`film_id`),
  KEY `fk_seance_billtopay1_idx` (`bill_to_pay_id`),
  CONSTRAINT `fk_seance_bill_to_pay1` FOREIGN KEY (`bill_to_pay_id`) REFERENCES `bill_to_pay` (`id`),
  CONSTRAINT `fk_seance_film1` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seance`
--

LOCK TABLES `seance` WRITE;
/*!40000 ALTER TABLE `seance` DISABLE KEYS */;
INSERT INTO `seance` VALUES (1,1,'2022-01-01 10:00:00',150.00,3),(2,3,'2023-04-14 16:30:00',170.00,4),(4,2,'2023-04-15 08:15:00',150.00,3),(5,1,'2023-04-15 14:05:00',120.00,3),(6,5,'2020-04-15 14:45:00',10.00,7);
/*!40000 ALTER TABLE `seance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `price` decimal(7,2) NOT NULL,
  `available` tinyint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `addServiceName_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'coffee 250g',1.00,1),(2,'bun with apples',2.50,1),(3,'bun with blueberries',2.75,1),(4,'chips 100g',2.00,0),(5,'cake with raisins',2.25,1),(6,'test add service 1',1.75,1),(7,'bread',2.50,1);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `seance_id` bigint unsigned NOT NULL,
  `place_id` bigint unsigned NOT NULL,
  `place_status` enum('FREE','RESERVED','TAKEN') NOT NULL,
  `client_id` bigint unsigned DEFAULT NULL,
  `code` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticketCode_UNIQUE` (`code`),
  KEY `fk_seat_seance_idx` (`seance_id`),
  KEY `fk_seat_logindata_idx` (`client_id`),
  KEY `fk_ticket_place1_idx` (`place_id`),
  CONSTRAINT `fk_ticket_login_data` FOREIGN KEY (`client_id`) REFERENCES `login_data` (`id`),
  CONSTRAINT `fk_ticket_place1` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`),
  CONSTRAINT `fk_ticket_seance` FOREIGN KEY (`seance_id`) REFERENCES `seance` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (6,1,1,'FREE',NULL,NULL),(7,1,2,'FREE',NULL,NULL),(8,1,3,'RESERVED',6,NULL),(9,1,5,'FREE',NULL,NULL),(10,1,6,'TAKEN',3,'Dmytro1645896325471'),(13,2,7,'FREE',NULL,NULL);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_service`
--

DROP TABLE IF EXISTS `ticket_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_service` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `service_id` bigint unsigned NOT NULL,
  `ticket_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_selectedaddservices_seat1_idx` (`ticket_id`),
  KEY `fk_selectedaddservices_addService1_idx` (`service_id`),
  CONSTRAINT `fk_selected_services_service_ticket1` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `fk_selected_services_ticket1` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_service`
--

LOCK TABLES `ticket_service` WRITE;
/*!40000 ALTER TABLE `ticket_service` DISABLE KEYS */;
INSERT INTO `ticket_service` VALUES (1,2,0),(2,5,0),(3,1,0),(4,6,10);
/*!40000 ALTER TABLE `ticket_service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-26  3:54:41
