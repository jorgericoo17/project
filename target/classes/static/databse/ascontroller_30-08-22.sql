-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: ascontroller
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.22.04.1

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
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model` (
  `IDMODEL` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(150) NOT NULL,
  `YEAR` int DEFAULT NULL,
  `FINISH` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IDMODEL`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,'IBIZA',2020,'1.6 TDI STYLE'),(2,'ATECA',2022,'2.0 TDI '),(3,'prueba',69,'dsafas');
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_vehicle`
--

DROP TABLE IF EXISTS `new_vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_vehicle` (
  `IDNEW_VEHICLE` int NOT NULL AUTO_INCREMENT,
  `CHASSIS_NUMBER` varchar(50) NOT NULL,
  `PLATE` varchar(15) NOT NULL,
  `COST` decimal(8,2) DEFAULT NULL,
  `SELL_PRICE` decimal(8,2) DEFAULT NULL,
  `NEW` tinyint(1) DEFAULT NULL,
  `IDMODEL` int NOT NULL,
  `IDSTATUS` int NOT NULL,
  `IDSELLER` int DEFAULT NULL,
  PRIMARY KEY (`IDNEW_VEHICLE`),
  KEY `fk_new_vehicle_idModel` (`IDMODEL`),
  KEY `fk_new_vehicle_idStatus` (`IDSTATUS`),
  KEY `fk_new_vehicle_idSeller` (`IDSELLER`),
  CONSTRAINT `fk_new_vehicle_idModel` FOREIGN KEY (`IDMODEL`) REFERENCES `model` (`IDMODEL`),
  CONSTRAINT `fk_new_vehicle_idSeller` FOREIGN KEY (`IDSELLER`) REFERENCES `user` (`IDUSER`),
  CONSTRAINT `fk_new_vehicle_idStatus` FOREIGN KEY (`IDSTATUS`) REFERENCES `status` (`IDSTATUS`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_vehicle`
--

LOCK TABLES `new_vehicle` WRITE;
/*!40000 ALTER TABLE `new_vehicle` DISABLE KEYS */;
INSERT INTO `new_vehicle` VALUES (1,'5646513135A','1234AAA',15000.00,17000.00,1,2,3,NULL),(2,'96875431Z','9874ZZZ',22000.00,25000.00,0,1,3,NULL),(3,'srdgdsfgsdf','fdsgdfsg',12.00,140.00,1,1,3,NULL),(4,'sdafdsfsd','sdfsd',69.00,169.00,1,3,3,NULL),(5,'5646513135A','1234AAA',789664.00,20.00,1,3,2,NULL);
/*!40000 ALTER TABLE `new_vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `IDROLE` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`IDROLE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MANAGER'),(3,'ROLE_SELLER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sell`
--

DROP TABLE IF EXISTS `sell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sell` (
  `IDSELL` int NOT NULL AUTO_INCREMENT,
  `BUYER_NAME` varchar(150) NOT NULL,
  `BUYER_DNI` varchar(10) NOT NULL,
  `PROFIT` decimal(8,2) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `IDSELLER` int NOT NULL,
  `IDVEHICLE` int NOT NULL,
  PRIMARY KEY (`IDSELL`),
  KEY `fk_sell_idSeller` (`IDSELLER`),
  KEY `fk_sell_idVehicle` (`IDVEHICLE`),
  CONSTRAINT `fk_sell_idSeller` FOREIGN KEY (`IDSELLER`) REFERENCES `user` (`IDUSER`),
  CONSTRAINT `fk_sell_idVehicle` FOREIGN KEY (`IDVEHICLE`) REFERENCES `new_vehicle` (`IDNEW_VEHICLE`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sell`
--

LOCK TABLES `sell` WRITE;
/*!40000 ALTER TABLE `sell` DISABLE KEYS */;
INSERT INTO `sell` VALUES (1,'Prueba venta','7881AAA',NULL,NULL,1,1),(2,'Prueba venta','7881AAA',2000.00,NULL,1,1),(3,'Prueba venta','7881AAA',2000.00,NULL,1,1),(4,'sdfsdf','sdfsdfsd',3000.00,NULL,1,2),(5,'sdafsdf','sadf',128.00,NULL,1,3),(6,'XD','RHLM',100.00,NULL,1,4),(7,'fdgfd','fdgsdfg',2000.00,NULL,1,1);
/*!40000 ALTER TABLE `sell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `IDSTATUS` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(150) NOT NULL,
  PRIMARY KEY (`IDSTATUS`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'DISPONIBLE'),(2,'RESERVADO'),(3,'VENDIDO');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `IDUSER` int NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(10) NOT NULL,
  `PASSWORD` varchar(150) NOT NULL,
  `NAME` varchar(150) DEFAULT NULL,
  `LASTNAME` varchar(150) DEFAULT NULL,
  `IDROLE` int DEFAULT NULL,
  PRIMARY KEY (`IDUSER`),
  KEY `fk_user_idRole` (`IDROLE`),
  CONSTRAINT `fk_user_idRole` FOREIGN KEY (`IDROLE`) REFERENCES `role` (`IDROLE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'as0000','$2a$12$Xr1tEQv78wy7WrIGJ0ObOu3SJkUwGhBaNYjTyIcrOwTfUu07f8Oh6','Jorge','Rico Tobío',1),(2,'as0001','$2a$10$iD0UtRtQCi5k8nwuncy8OeeWg4qoLV4DNkrXiXEg.YrkrjF5DU5rW','vendededor','jorge',3);
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

-- Dump completed on 2022-08-30  7:46:54
