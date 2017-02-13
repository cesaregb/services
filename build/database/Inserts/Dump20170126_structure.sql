-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: localhost    Database: sod_db
-- ------------------------------------------------------
-- Server version	5.7.16

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
-- Table structure for table `AccessKey`
--

DROP TABLE IF EXISTS `AccessKey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccessKey` (
  `idAccessKey` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idClient` int(10) unsigned NOT NULL,
  `idSocialNetworks` int(10) unsigned NOT NULL,
  `token` varchar(100) DEFAULT NULL,
  `tokenSecre` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idAccessKey`),
  KEY `fk_AccessKey_Clients1_idx` (`idClient`),
  KEY `fk_AccessKey_SocialNetworks1_idx` (`idSocialNetworks`),
  CONSTRAINT `fk_AccessKey_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AccessKey_SocialNetworks1` FOREIGN KEY (`idSocialNetworks`) REFERENCES `SocialNetworks` (`idSocialNetworks`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccessKey`
--

LOCK TABLES `AccessKey` WRITE;
/*!40000 ALTER TABLE `AccessKey` DISABLE KEYS */;
/*!40000 ALTER TABLE `AccessKey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address` (
  `idAddress` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idClient` int(10) unsigned NOT NULL,
  `country` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `address2` varchar(250) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `lat` decimal(10,8) DEFAULT NULL,
  `lng` decimal(11,8) DEFAULT NULL,
  `prefered` tinyint(1) DEFAULT '0',
  `factura` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idAddress`),
  KEY `fk_Address_Clients1_idx` (`idClient`),
  CONSTRAINT `fk_Address_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` VALUES (1,1,'Mexico','Jalisco','44540','Guadalajara','Peninsula',NULL,NULL,20.64551528,-103.39244127,1,1),(2,1,'Mexico','Jalisco','44540','Guadalajara','Calle Barlovento','Rinconada del Bosque','departamento sin timbre',20.64997290,-103.38900805,0,0);
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AddressRoutes`
--

DROP TABLE IF EXISTS `AddressRoutes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AddressRoutes` (
  `idAddressRoute` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zipcode` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `address2` varchar(250) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `lat` decimal(10,8) DEFAULT NULL,
  `lng` decimal(11,8) DEFAULT NULL,
  PRIMARY KEY (`idAddressRoute`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AddressRoutes`
--

LOCK TABLES `AddressRoutes` WRITE;
/*!40000 ALTER TABLE `AddressRoutes` DISABLE KEYS */;
INSERT INTO `AddressRoutes` VALUES (1,'Mexico','Jalisco','44540','Guadalajara','Calle','Colonia','no tiene timbre... ',NULL,NULL);
/*!40000 ALTER TABLE `AddressRoutes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Asset`
--

DROP TABLE IF EXISTS `Asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Asset` (
  `idAsset` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idAssetType` int(10) unsigned NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idAsset`),
  KEY `fk_Asset_AssetType1_idx` (`idAssetType`),
  CONSTRAINT `fk_Asset_AssetType1` FOREIGN KEY (`idAssetType`) REFERENCES `AssetType` (`idAssetType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Asset`
--

LOCK TABLES `Asset` WRITE;
/*!40000 ALTER TABLE `Asset` DISABLE KEYS */;
INSERT INTO `Asset` VALUES (1,1,1,'lavadora 1','lavadora roja',0),(2,1,1,'lavadora 2','lavadora roja rota',0),(3,3,1,'moto1','moto roja placas aaabbb92',0);
/*!40000 ALTER TABLE `Asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AssetTaskOrder`
--

DROP TABLE IF EXISTS `AssetTaskOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AssetTaskOrder` (
  `idAssetTaskOrder` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idOrderTask` int(10) unsigned NOT NULL,
  `Asset_idAsset` int(10) unsigned NOT NULL,
  `comments` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idAssetTaskOrder`),
  KEY `fk_AssetTaskOrder_Asset1_idx` (`Asset_idAsset`),
  KEY `fk_AssetTaskOrder_OrderTask1_idx` (`idOrderTask`),
  CONSTRAINT `fk_AssetTaskOrder_Asset1` FOREIGN KEY (`Asset_idAsset`) REFERENCES `Asset` (`idAsset`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AssetTaskOrder_OrderTask1` FOREIGN KEY (`idOrderTask`) REFERENCES `OrderTask` (`idOrderTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssetTaskOrder`
--

LOCK TABLES `AssetTaskOrder` WRITE;
/*!40000 ALTER TABLE `AssetTaskOrder` DISABLE KEYS */;
/*!40000 ALTER TABLE `AssetTaskOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AssetTaskService`
--

DROP TABLE IF EXISTS `AssetTaskService`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AssetTaskService` (
  `idAssetTaskService` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idAsset` int(10) unsigned NOT NULL,
  `idServiceTask` int(10) unsigned NOT NULL,
  `comments` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idAssetTaskService`),
  KEY `fk_AssetTaskService_Asset1_idx` (`idAsset`),
  KEY `fk_AssetTaskService_ServiceTask1_idx` (`idServiceTask`),
  CONSTRAINT `fk_AssetTaskService_Asset1` FOREIGN KEY (`idAsset`) REFERENCES `Asset` (`idAsset`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AssetTaskService_ServiceTask1` FOREIGN KEY (`idServiceTask`) REFERENCES `ServiceTask` (`idServiceTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssetTaskService`
--

LOCK TABLES `AssetTaskService` WRITE;
/*!40000 ALTER TABLE `AssetTaskService` DISABLE KEYS */;
/*!40000 ALTER TABLE `AssetTaskService` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AssetType`
--

DROP TABLE IF EXISTS `AssetType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AssetType` (
  `idAssetType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idAssetType`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssetType`
--

LOCK TABLES `AssetType` WRITE;
/*!40000 ALTER TABLE `AssetType` DISABLE KEYS */;
INSERT INTO `AssetType` VALUES (1,'lavado',NULL,0),(2,'planchado',NULL,0),(3,'transporte',NULL,0);
/*!40000 ALTER TABLE `AssetType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BagType`
--

DROP TABLE IF EXISTS `BagType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BagType` (
  `idBagType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBagType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BagType`
--

LOCK TABLES `BagType` WRITE;
/*!40000 ALTER TABLE `BagType` DISABLE KEYS */;
INSERT INTO `BagType` VALUES (1,'Chica',5),(2,'Grande',10);
/*!40000 ALTER TABLE `BagType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CalendarRoute`
--

DROP TABLE IF EXISTS `CalendarRoute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CalendarRoute` (
  `idCalendarRoute` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) DEFAULT '1',
  `time` varchar(50) DEFAULT '8:00',
  `idRoutes` int(11) NOT NULL,
  `action` varchar(45) DEFAULT '1' COMMENT '1 = pickup\n2 = deliver',
  PRIMARY KEY (`idCalendarRoute`),
  KEY `fk_Calendar_Routes1_idx` (`idRoutes`),
  CONSTRAINT `fk_Calendar_Routes1` FOREIGN KEY (`idRoutes`) REFERENCES `Routes` (`idRoutes`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CalendarRoute`
--

LOCK TABLES `CalendarRoute` WRITE;
/*!40000 ALTER TABLE `CalendarRoute` DISABLE KEYS */;
INSERT INTO `CalendarRoute` VALUES (1,1,'9:30',1,'1');
/*!40000 ALTER TABLE `CalendarRoute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClientBags`
--

DROP TABLE IF EXISTS `ClientBags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClientBags` (
  `idClientBags` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL,
  `inOrder` tinyint(1) DEFAULT NULL,
  `idBagType` int(10) unsigned NOT NULL,
  `idClient` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idClientBags`),
  KEY `fk_ClientBags_BagSize1_idx` (`idBagType`),
  KEY `fk_ClientBags_Clients1_idx` (`idClient`),
  CONSTRAINT `fk_ClientBags_BagSize1` FOREIGN KEY (`idBagType`) REFERENCES `BagType` (`idBagType`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ClientBags_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientBags`
--

LOCK TABLES `ClientBags` WRITE;
/*!40000 ALTER TABLE `ClientBags` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClientBags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClientPaymentInfo`
--

DROP TABLE IF EXISTS `ClientPaymentInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClientPaymentInfo` (
  `idClientPaymentInfo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT '0' COMMENT 'CASH = 0; \nCC = 1; // credit card \nPAYPAL = 2;  \nOTHER = 4; // NOT DEFINED \nSTRIPE = 3; ',
  `token` varchar(250) DEFAULT NULL,
  `idClient` int(10) unsigned NOT NULL,
  `prefered` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idClientPaymentInfo`),
  KEY `fk_ClientPaymentInfo_Clients1_idx` (`idClient`),
  CONSTRAINT `fk_ClientPaymentInfo_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientPaymentInfo`
--

LOCK TABLES `ClientPaymentInfo` WRITE;
/*!40000 ALTER TABLE `ClientPaymentInfo` DISABLE KEYS */;
INSERT INTO `ClientPaymentInfo` VALUES (1,1,'01234',1,1);
/*!40000 ALTER TABLE `ClientPaymentInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClientType`
--

DROP TABLE IF EXISTS `ClientType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClientType` (
  `idClientType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idClientType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientType`
--

LOCK TABLES `ClientType` WRITE;
/*!40000 ALTER TABLE `ClientType` DISABLE KEYS */;
INSERT INTO `ClientType` VALUES (1,'Normal','Clientes normal'),(2,'Mayoreo','Clientes de Mayoreo'),(3,'Cliente Regular 1','Clientes Regular 1'),(4,'Cliente Regular 2','Clientes Regular 2');
/*!40000 ALTER TABLE `ClientType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Clients`
--

DROP TABLE IF EXISTS `Clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Clients` (
  `idClient` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idClientType` int(10) unsigned NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` char(128) DEFAULT NULL,
  `name` varchar(250) DEFAULT NULL,
  `lastName` varchar(250) DEFAULT NULL,
  `twitter` varchar(250) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `loginID` varchar(50) DEFAULT NULL,
  `rfc` varchar(45) DEFAULT NULL,
  `razonSocial` varchar(250) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `mobilePhone` varchar(45) DEFAULT NULL,
  `homePhone` varchar(45) DEFAULT NULL,
  `otherPhone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_Clients_ClientCategory1_idx` (`idClientType`),
  CONSTRAINT `fk_Clients_ClientCategory1` FOREIGN KEY (`idClientType`) REFERENCES `ClientType` (`idClientType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Clients`
--

LOCK TABLES `Clients` WRITE;
/*!40000 ALTER TABLE `Clients` DISABLE KEYS */;
INSERT INTO `Clients` VALUES (1,1,'email@domain.com','notused','Brad','Pit','twitter','2016-12-30 13:09:05',NULL,'123',NULL,NULL,0,'333010101',NULL,NULL);
/*!40000 ALTER TABLE `Clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DistanceInfo`
--

DROP TABLE IF EXISTS `DistanceInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DistanceInfo` (
  `idDistanceInfo` int(11) NOT NULL AUTO_INCREMENT,
  `distance` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `source` int(11) DEFAULT '0' COMMENT '0 = local\n1 = empresa \n2 = ruta',
  `idStore` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idDistanceInfo`),
  KEY `fk_DistanceInfo_Stores1_idx` (`idStore`),
  CONSTRAINT `fk_DistanceInfo_Stores1` FOREIGN KEY (`idStore`) REFERENCES `Stores` (`idStore`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DistanceInfo`
--

LOCK TABLES `DistanceInfo` WRITE;
/*!40000 ALTER TABLE `DistanceInfo` DISABLE KEYS */;
INSERT INTO `DistanceInfo` VALUES (1,3,0,1,1),(2,6,20,1,1),(3,12,35,1,1),(4,24,50,1,1);
/*!40000 ALTER TABLE `DistanceInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `idEmployee` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idEmployeeType` int(10) unsigned NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `name` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `password` longtext,
  `username` varchar(45) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEmployee`),
  KEY `fk_Employee_EmployeeType1_idx` (`idEmployeeType`),
  CONSTRAINT `fk_Employee_EmployeeType1` FOREIGN KEY (`idEmployeeType`) REFERENCES `EmployeeType` (`idEmployeeType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,1,1,'user','user',NULL,'user','2016-12-30 13:09:05',NULL,'email@domain.com',0);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmployeeTaskOrder`
--

DROP TABLE IF EXISTS `EmployeeTaskOrder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmployeeTaskOrder` (
  `idEmployeeTaskOrder` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idOrderTask` int(10) unsigned NOT NULL,
  `idEmployee` int(10) unsigned NOT NULL,
  `comments` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idEmployeeTaskOrder`),
  KEY `fk_EmployeeTaskOrder_OrderTask1_idx` (`idOrderTask`),
  KEY `fk_EmployeeTaskOrder_Employee1_idx` (`idEmployee`),
  CONSTRAINT `fk_EmployeeTaskOrder_Employee1` FOREIGN KEY (`idEmployee`) REFERENCES `Employee` (`idEmployee`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EmployeeTaskOrder_OrderTask1` FOREIGN KEY (`idOrderTask`) REFERENCES `OrderTask` (`idOrderTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmployeeTaskOrder`
--

LOCK TABLES `EmployeeTaskOrder` WRITE;
/*!40000 ALTER TABLE `EmployeeTaskOrder` DISABLE KEYS */;
/*!40000 ALTER TABLE `EmployeeTaskOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmployeeTaskService`
--

DROP TABLE IF EXISTS `EmployeeTaskService`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmployeeTaskService` (
  `idEmployeeTaskService` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idEmployee` int(10) unsigned NOT NULL,
  `idServiceTask` int(10) unsigned NOT NULL,
  `comments` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idEmployeeTaskService`),
  KEY `fk_EmployeeTaskService_Employee1_idx` (`idEmployee`),
  KEY `fk_EmployeeTaskService_ServiceTask1_idx` (`idServiceTask`),
  CONSTRAINT `fk_EmployeeTaskService_Employee1` FOREIGN KEY (`idEmployee`) REFERENCES `Employee` (`idEmployee`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EmployeeTaskService_ServiceTask1` FOREIGN KEY (`idServiceTask`) REFERENCES `ServiceTask` (`idServiceTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmployeeTaskService`
--

LOCK TABLES `EmployeeTaskService` WRITE;
/*!40000 ALTER TABLE `EmployeeTaskService` DISABLE KEYS */;
/*!40000 ALTER TABLE `EmployeeTaskService` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmployeeType`
--

DROP TABLE IF EXISTS `EmployeeType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmployeeType` (
  `idEmployeeType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEmployeeType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmployeeType`
--

LOCK TABLES `EmployeeType` WRITE;
/*!40000 ALTER TABLE `EmployeeType` DISABLE KEYS */;
INSERT INTO `EmployeeType` VALUES (1,'admin','Administradores',0),(2,'general','Empleados Nivel 2',0);
/*!40000 ALTER TABLE `EmployeeType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Menu`
--

DROP TABLE IF EXISTS `Menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Menu` (
  `idMenu` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `accessLevel` int(11) DEFAULT '1' COMMENT '0 = none\n1 = admin \n2 = not so admin\n3 = less admin than 2\n',
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMenu`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Menu`
--

LOCK TABLES `Menu` WRITE;
/*!40000 ALTER TABLE `Menu` DISABLE KEYS */;
INSERT INTO `Menu` VALUES (1,'client.all','Clientes',2,1),(2,'routes.all','Rutas',1,2),(3,'tasks.taskMenu','Tareas',1,5),(4,'specs.specMenu','Specs',1,6),(5,'employees.employeeMenu','Empleados',1,4),(6,'assets.assetMenu','Activos',0,3),(7,'supplies.supplyMenu','Consumibles',1,7),(8,'services.serviceMenu','Servicios',1,8),(9,'orders.orderMenu','Orders',1,9),(10,'products.productMenu','Productos',1,10);
/*!40000 ALTER TABLE `Menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderPromotion`
--

DROP TABLE IF EXISTS `OrderPromotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderPromotion` (
  `idOrderPromotion` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idOrder` int(10) unsigned NOT NULL,
  `idPromotion` int(10) unsigned NOT NULL,
  `cantidad` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`idOrderPromotion`),
  KEY `fk_OrderPromotion_Orders1_idx` (`idOrder`),
  KEY `fk_OrderPromotion_Promotion1_idx` (`idPromotion`),
  CONSTRAINT `fk_OrderPromotion_Orders1` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`idOrder`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderPromotion_Promotion1` FOREIGN KEY (`idPromotion`) REFERENCES `Promotion` (`idPromotion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderPromotion`
--

LOCK TABLES `OrderPromotion` WRITE;
/*!40000 ALTER TABLE `OrderPromotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderPromotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderTask`
--

DROP TABLE IF EXISTS `OrderTask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderTask` (
  `idOrderTask` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idOrder` int(10) unsigned NOT NULL,
  `idTask` int(10) unsigned NOT NULL,
  `time` int(11) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0 = NEW\n1 = COMPLETED',
  `sortingOrder` int(11) DEFAULT NULL,
  `started` datetime DEFAULT NULL,
  `ended` datetime DEFAULT NULL,
  PRIMARY KEY (`idOrderTask`),
  KEY `fk_OrderTask_Task1_idx` (`idTask`),
  KEY `fk_OrderTask_Order1_idx` (`idOrder`),
  CONSTRAINT `fk_OrderTask_Order1` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`idOrder`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTask_Task1` FOREIGN KEY (`idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderTask`
--

LOCK TABLES `OrderTask` WRITE;
/*!40000 ALTER TABLE `OrderTask` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderType`
--

DROP TABLE IF EXISTS `OrderType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderType` (
  `idOrderType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `transportInfo` int(11) DEFAULT '0' COMMENT '0 = none; \n1 = show pick up\n2 = show deliver \n3 = show both.',
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`idOrderType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderType`
--

LOCK TABLES `OrderType` WRITE;
/*!40000 ALTER TABLE `OrderType` DISABLE KEYS */;
INSERT INTO `OrderType` VALUES (1,'Completa','Pickup + service + deliver',3,0),(2,'Recoleccion','Pickup + service',1,0),(3,'Entrega','Service + deliver',2,0),(4,'Encargo','Service',0,0);
/*!40000 ALTER TABLE `OrderType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderTypeTask`
--

DROP TABLE IF EXISTS `OrderTypeTask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderTypeTask` (
  `idOrderTypeTask` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idOrderType` int(10) unsigned NOT NULL,
  `idTask` int(10) unsigned NOT NULL,
  `sortingOrder` int(11) DEFAULT NULL,
  PRIMARY KEY (`idOrderTypeTask`),
  KEY `fk_OrderTemplateTasks_Task1_idx` (`idTask`),
  KEY `fk_OrderTemplateTasks_OrderTemplate1_idx` (`idOrderType`),
  CONSTRAINT `fk_OrderTemplateTasks_OrderTemplate1` FOREIGN KEY (`idOrderType`) REFERENCES `OrderType` (`idOrderType`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTemplateTasks_Task1` FOREIGN KEY (`idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderTypeTask`
--

LOCK TABLES `OrderTypeTask` WRITE;
/*!40000 ALTER TABLE `OrderTypeTask` DISABLE KEYS */;
INSERT INTO `OrderTypeTask` VALUES (1,1,3,1),(2,1,1,2),(3,1,4,3),(5,2,3,0);
/*!40000 ALTER TABLE `OrderTypeTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `idOrder` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idClient` int(10) unsigned NOT NULL,
  `idOrderType` int(10) unsigned NOT NULL,
  `idAddressPickup` int(11) DEFAULT NULL COMMENT 'Not froreing key ',
  `pickUpDate` datetime DEFAULT NULL,
  `idAddressDeliver` int(11) DEFAULT NULL,
  `deliverDate` datetime DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '0 = active\n1 = finished',
  `comments` varchar(250) DEFAULT NULL,
  `createdBy` int(11) DEFAULT '0',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `totalServices` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`idOrder`),
  KEY `fk_Order_OrderTemplate1_idx` (`idOrderType`),
  KEY `fk_Order_Clients1_idx` (`idClient`),
  CONSTRAINT `fk_Order_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_OrderTemplate1` FOREIGN KEY (`idOrderType`) REFERENCES `OrderType` (`idOrderType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PaymentInfo`
--

DROP TABLE IF EXISTS `PaymentInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaymentInfo` (
  `idPaymentInfo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idOrder` int(10) unsigned NOT NULL,
  `type` int(11) NOT NULL COMMENT '0=cash\n1=stripe\n2=square\n',
  `transactionInfo` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idPaymentInfo`),
  KEY `fk_PaymentInfo_Orders1_idx` (`idOrder`),
  CONSTRAINT `fk_PaymentInfo_Orders1` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`idOrder`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaymentInfo`
--

LOCK TABLES `PaymentInfo` WRITE;
/*!40000 ALTER TABLE `PaymentInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `PaymentInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `idProduct` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idProductType` int(10) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `maxQty` int(11) NOT NULL DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idProduct`),
  KEY `fk_Subproduct_SubproductType1_idx` (`idProductType`),
  CONSTRAINT `fk_Subproduct_SubproductType1` FOREIGN KEY (`idProductType`) REFERENCES `ProductType` (`idProductType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,1,'Sabanas',0.2,200,0),(2,1,'Toalla Chica',0.2,300,0),(3,1,'Toalla Grande',20,0,0),(4,1,'Toalla Mediana',20,0,0),(5,4,'Kg',14,0,0),(6,4,'Tersus Bolsa Chica',65,0,0),(7,4,'Tersus Bolsa Grande',70,0,0),(8,6,'Sabana',20,0,0),(9,6,'Cobija',30,0,0),(10,6,'Edredon',50,0,0),(11,7,'Pantalon',45,0,0),(12,7,'Traje',88,0,0);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductType`
--

DROP TABLE IF EXISTS `ProductType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductType` (
  `idProductType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idProductType`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductType`
--

LOCK TABLES `ProductType` WRITE;
/*!40000 ALTER TABLE `ProductType` DISABLE KEYS */;
INSERT INTO `ProductType` VALUES (1,'Ocho16 Spa','Productos para el ocho16 spa',0),(2,'Spa General','Productos para spa general',0),(3,'Seguridad 1','Productos para casa de seguridad',0),(4,'Lavado','Productos de Lavado general',0),(5,'Planchado','Productos de Planchado general',0),(6,'Lavado de Blancos','Productos de lavado de blancos',0),(7,'Tintoreria','productos de tintoreria',0);
/*!40000 ALTER TABLE `ProductType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Promotion`
--

DROP TABLE IF EXISTS `Promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Promotion` (
  `idPromotion` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idPromotionType` int(10) unsigned NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `maxUses` int(11) DEFAULT '0' COMMENT 'max uses by order',
  `amount` double DEFAULT '0',
  `promoCode` varchar(45) DEFAULT NULL,
  `orderLimit` int(11) DEFAULT '0',
  `dateLimit` datetime DEFAULT NULL COMMENT 'limit date that the promotion will be enabled. ',
  `minimumAmount` int(11) DEFAULT '0',
  `discountType` int(11) DEFAULT '1' COMMENT '1 = $\n2 = %\n',
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`idPromotion`),
  KEY `fk_Promotion_PromotionType1_idx` (`idPromotionType`),
  CONSTRAINT `fk_Promotion_PromotionType1` FOREIGN KEY (`idPromotionType`) REFERENCES `PromotionType` (`idPromotionType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Promotion`
--

LOCK TABLES `Promotion` WRITE;
/*!40000 ALTER TABLE `Promotion` DISABLE KEYS */;
INSERT INTO `Promotion` VALUES (1,1,'Buen Fin','Promocion por el buen fin','2016-01-01 00:00:00','2017-01-01 00:00:00',1,10,NULL,1,NULL,0,2,0),(2,3,'Compra de $120','Compra de $120',NULL,NULL,1,20,NULL,1,NULL,0,1,0),(3,4,'Compra No. #','Compra no #. ',NULL,NULL,1,60,NULL,1,NULL,0,2,0),(4,5,'Cliente Frecuente','Descuento por cliente frecuente',NULL,NULL,1,10,NULL,1,NULL,0,1,0);
/*!40000 ALTER TABLE `Promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PromotionType`
--

DROP TABLE IF EXISTS `PromotionType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PromotionType` (
  `idPromotionType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`idPromotionType`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PromotionType`
--

LOCK TABLES `PromotionType` WRITE;
/*!40000 ALTER TABLE `PromotionType` DISABLE KEYS */;
INSERT INTO `PromotionType` VALUES (1,'Periodo de tiempo','Descuentos entre fechas',0),(2,'Productos o Servicios [NA]','Al agregar uno o alguna combinacio de producto o servicio',0),(3,'Cantidad','Al llegar a una cantidad economica en una orden',0),(4,'Cantidad acumulativa en periodo','Al llegar a una cantidad acumulativa por periodo',0),(5,'Tipo de Client','Por tipo de clientes',0),(6,'Numero de ordenes por cliente','Al llegar a una cantidad de ordenes por cliente',0);
/*!40000 ALTER TABLE `PromotionType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Routes`
--

DROP TABLE IF EXISTS `Routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Routes` (
  `idRoutes` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(105) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `category` int(11) DEFAULT '1' COMMENT '1 = departamentos\n2 = offices\n3 = cliente\n',
  PRIMARY KEY (`idRoutes`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Routes`
--

LOCK TABLES `Routes` WRITE;
/*!40000 ALTER TABLE `Routes` DISABLE KEYS */;
INSERT INTO `Routes` VALUES (1,'route1','route 1 ',1);
/*!40000 ALTER TABLE `Routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Service`
--

DROP TABLE IF EXISTS `Service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Service` (
  `idService` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idServiceType` int(10) unsigned NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `idOrder` int(10) unsigned NOT NULL,
  `nTasks` int(11) DEFAULT '0' COMMENT '# numero de tasks o pasos',
  `currentTask` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  `price` double DEFAULT '0',
  `specsPrice` double DEFAULT '0',
  `productsPrice` double DEFAULT '0',
  `totalPrice` double DEFAULT '0',
  PRIMARY KEY (`idService`),
  KEY `fk_Service_ServiceType1_idx` (`idServiceType`),
  KEY `fk_Service_Orders1_idx` (`idOrder`),
  CONSTRAINT `fk_Service_Orders1` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`idOrder`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Service_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Service`
--

LOCK TABLES `Service` WRITE;
/*!40000 ALTER TABLE `Service` DISABLE KEYS */;
/*!40000 ALTER TABLE `Service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceCategory`
--

DROP TABLE IF EXISTS `ServiceCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceCategory` (
  `idServiceCategory` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idServiceCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceCategory`
--

LOCK TABLES `ServiceCategory` WRITE;
/*!40000 ALTER TABLE `ServiceCategory` DISABLE KEYS */;
INSERT INTO `ServiceCategory` VALUES (1,'Lavado','Servicios de Lavado Clientes'),(2,'Planchado','Planchado'),(3,'Tintoreria','Tintoreria varios'),(4,'Costura','Costura'),(5,'Mayoreao Spa','Servicio de mayoreo para SPAs'),(6,'Mayoreo Seguridad','Casas de seguridad');
/*!40000 ALTER TABLE `ServiceCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceComments`
--

DROP TABLE IF EXISTS `ServiceComments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceComments` (
  `idServiceComments` int(11) NOT NULL,
  `idService` int(10) unsigned NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idServiceComments`),
  KEY `fk_ServiceComments_Service1_idx` (`idService`),
  CONSTRAINT `fk_ServiceComments_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceComments`
--

LOCK TABLES `ServiceComments` WRITE;
/*!40000 ALTER TABLE `ServiceComments` DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceComments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceProducts`
--

DROP TABLE IF EXISTS `ServiceProducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceProducts` (
  `idServiceProducts` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idService` int(10) unsigned NOT NULL,
  `idProduct` int(10) unsigned NOT NULL,
  `quantity` int(11) DEFAULT '0',
  `price` double DEFAULT '0',
  PRIMARY KEY (`idServiceProducts`),
  KEY `fk_ServiceSubproducts_Service1_idx` (`idService`),
  KEY `fk_ServiceSubproducts_Subproduct1_idx` (`idProduct`),
  CONSTRAINT `fk_ServiceSubproducts_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceSubproducts_Subproduct1` FOREIGN KEY (`idProduct`) REFERENCES `Product` (`idProduct`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceProducts`
--

LOCK TABLES `ServiceProducts` WRITE;
/*!40000 ALTER TABLE `ServiceProducts` DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceProducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceSpecs`
--

DROP TABLE IF EXISTS `ServiceSpecs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceSpecs` (
  `idServiceSpecs` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idService` int(10) unsigned NOT NULL,
  `idSpecs` int(10) unsigned NOT NULL,
  `comments` varchar(250) DEFAULT NULL,
  `quantity` int(11) DEFAULT '0',
  `specPrice` double DEFAULT '0',
  `selectedValue` varchar(250) DEFAULT NULL,
  `serviceIncrement` double DEFAULT '0',
  PRIMARY KEY (`idServiceSpecs`),
  KEY `fk_ServiceSpecs_Specs1_idx` (`idSpecs`),
  KEY `fk_ServiceSpecs_Service1_idx` (`idService`),
  CONSTRAINT `fk_ServiceSpecs_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceSpecs_Specs1` FOREIGN KEY (`idSpecs`) REFERENCES `Specs` (`idSpecs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceSpecs`
--

LOCK TABLES `ServiceSpecs` WRITE;
/*!40000 ALTER TABLE `ServiceSpecs` DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceSpecs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceTask`
--

DROP TABLE IF EXISTS `ServiceTask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceTask` (
  `idServiceTask` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idService` int(10) unsigned NOT NULL,
  `idTask` int(10) unsigned NOT NULL,
  `comments` varchar(250) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `started` datetime DEFAULT NULL,
  `ended` datetime DEFAULT NULL,
  `sortingOrder` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT '10',
  PRIMARY KEY (`idServiceTask`),
  KEY `fk_ServiceTask_Task1_idx` (`idTask`),
  KEY `fk_ServiceTask_Service1_idx` (`idService`),
  CONSTRAINT `fk_ServiceTask_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTask_Task1` FOREIGN KEY (`idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceTask`
--

LOCK TABLES `ServiceTask` WRITE;
/*!40000 ALTER TABLE `ServiceTask` DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceType`
--

DROP TABLE IF EXISTS `ServiceType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceType` (
  `idServiceType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `idServiceCategory` int(10) unsigned NOT NULL,
  `calculator` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idServiceType`),
  KEY `fk_ServiceType_ServiceCategory1_idx` (`idServiceCategory`),
  CONSTRAINT `fk_ServiceType_ServiceCategory1` FOREIGN KEY (`idServiceCategory`) REFERENCES `ServiceCategory` (`idServiceCategory`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceType`
--

LOCK TABLES `ServiceType` WRITE;
/*!40000 ALTER TABLE `ServiceType` DISABLE KEYS */;
INSERT INTO `ServiceType` VALUES (1,'Lavado General','Ropa general',20,45,1,1),(2,'Lavado Delicado','Ropa intima',20,45,1,1),(3,'Planchado Docena','Planchado docena de piezas',60,30,2,0),(4,'Planchado pieza','planchado pieza',10,5,2,0),(5,'Ocho16 Spa','Ocho16 Spa',0,90,5,0),(6,'Mayoreo Seguridad Casa X','Casa de seguridad 1',0,40,6,0),(7,'Tintoreria','Tintoreria general',0,0,3,0);
/*!40000 ALTER TABLE `ServiceType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceTypeProductType`
--

DROP TABLE IF EXISTS `ServiceTypeProductType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceTypeProductType` (
  `idServiceType` int(10) unsigned NOT NULL,
  `idProductType` int(10) unsigned NOT NULL,
  KEY `fk_ServiceTypeSubproductType_ServiceType1_idx` (`idServiceType`),
  KEY `fk_ServiceTypeSubproductType_SubproductType1_idx` (`idProductType`),
  CONSTRAINT `fk_ServiceTypeSubproductType_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeSubproductType_SubproductType1` FOREIGN KEY (`idProductType`) REFERENCES `ProductType` (`idProductType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceTypeProductType`
--

LOCK TABLES `ServiceTypeProductType` WRITE;
/*!40000 ALTER TABLE `ServiceTypeProductType` DISABLE KEYS */;
INSERT INTO `ServiceTypeProductType` VALUES (4,5),(3,5),(5,1);
/*!40000 ALTER TABLE `ServiceTypeProductType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceTypeSpecs`
--

DROP TABLE IF EXISTS `ServiceTypeSpecs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceTypeSpecs` (
  `idServiceType` int(10) unsigned NOT NULL,
  `idSpecs` int(10) unsigned NOT NULL,
  KEY `fk_ServiceTypeSpecs_Specs1_idx` (`idSpecs`),
  KEY `fk_ServiceTypeSpecs_ServiceType1_idx` (`idServiceType`),
  CONSTRAINT `fk_ServiceTypeSpecs_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeSpecs_Specs1` FOREIGN KEY (`idSpecs`) REFERENCES `Specs` (`idSpecs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceTypeSpecs`
--

LOCK TABLES `ServiceTypeSpecs` WRITE;
/*!40000 ALTER TABLE `ServiceTypeSpecs` DISABLE KEYS */;
INSERT INTO `ServiceTypeSpecs` VALUES (1,1),(1,2),(2,1),(2,2),(3,5),(4,5),(5,2),(5,3),(5,4),(5,1),(1,4),(7,6);
/*!40000 ALTER TABLE `ServiceTypeSpecs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceTypeTask`
--

DROP TABLE IF EXISTS `ServiceTypeTask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceTypeTask` (
  `idServiceTypeTask` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idServiceType` int(10) unsigned NOT NULL,
  `idTask` int(10) unsigned NOT NULL,
  `sortingOrder` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT '0',
  PRIMARY KEY (`idServiceTypeTask`),
  KEY `fk_ServiceTypeTask_Task1_idx` (`idTask`),
  KEY `fk_ServiceTypeTask_ServiceType1_idx` (`idServiceType`),
  CONSTRAINT `fk_ServiceTypeTask_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeTask_Task1` FOREIGN KEY (`idTask`) REFERENCES `Task` (`idTask`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceTypeTask`
--

LOCK TABLES `ServiceTypeTask` WRITE;
/*!40000 ALTER TABLE `ServiceTypeTask` DISABLE KEYS */;
INSERT INTO `ServiceTypeTask` VALUES (1,1,2,1,10),(2,1,5,2,10),(3,2,2,1,10),(4,2,5,2,10),(5,3,6,0,0),(6,4,6,0,0),(7,5,5,1,0),(8,5,2,0,0),(9,5,7,1,0),(10,5,2,0,0),(11,5,5,2,0),(12,7,8,0,0),(13,7,9,1,0);
/*!40000 ALTER TABLE `ServiceTypeTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SocialNetworkData`
--

DROP TABLE IF EXISTS `SocialNetworkData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SocialNetworkData` (
  `idSocialNetworkData` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data` longtext,
  `idAccessKey` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idSocialNetworkData`),
  KEY `fk_SocialNetworkData_AccessKey1_idx` (`idAccessKey`),
  CONSTRAINT `fk_SocialNetworkData_AccessKey1` FOREIGN KEY (`idAccessKey`) REFERENCES `AccessKey` (`idAccessKey`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SocialNetworkData`
--

LOCK TABLES `SocialNetworkData` WRITE;
/*!40000 ALTER TABLE `SocialNetworkData` DISABLE KEYS */;
/*!40000 ALTER TABLE `SocialNetworkData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SocialNetworks`
--

DROP TABLE IF EXISTS `SocialNetworks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SocialNetworks` (
  `idSocialNetworks` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `domain` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSocialNetworks`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SocialNetworks`
--

LOCK TABLES `SocialNetworks` WRITE;
/*!40000 ALTER TABLE `SocialNetworks` DISABLE KEYS */;
/*!40000 ALTER TABLE `SocialNetworks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Specs`
--

DROP TABLE IF EXISTS `Specs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Specs` (
  `idSpecs` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'This table is for information about a service\nsuch as \njavon \nsuavisante\ntypo lavador\n',
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `optional` tinyint(1) DEFAULT '1',
  `max_qty` int(11) DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSpecs`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Specs`
--

LOCK TABLES `Specs` WRITE;
/*!40000 ALTER TABLE `Specs` DISABLE KEYS */;
INSERT INTO `Specs` VALUES (1,'Suavizante','Tipo de suavizante',1,1,0),(2,'Detergente','Jabon / detergente a utilizarse',1,1,0),(3,'Secado','Tipo de secado',1,0,0),(4,'Toallas de secado','toalla aromatizante de secado',1,1,0),(5,'Ganchos','Ganchos para prendas',1,0,0),(6,'Tintoreria','general',0,0,0);
/*!40000 ALTER TABLE `Specs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SpecsValues`
--

DROP TABLE IF EXISTS `SpecsValues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SpecsValues` (
  `idSpecsValues` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idSpecs` int(10) unsigned NOT NULL,
  `type` int(11) DEFAULT '1' COMMENT '1 = value\n2 = product',
  `value` varchar(45) DEFAULT NULL,
  `idSupplyType` int(11) DEFAULT '0',
  `serviceIncrement` double DEFAULT '0',
  `prefered` int(11) DEFAULT '0',
  `specPrice` double DEFAULT '0',
  `costType` int(11) DEFAULT '1' COMMENT '1 = increment\n2 = specPrice',
  PRIMARY KEY (`idSpecsValues`),
  KEY `fk_SpecsValues_Specs1_idx` (`idSpecs`),
  CONSTRAINT `fk_SpecsValues_Specs1` FOREIGN KEY (`idSpecs`) REFERENCES `Specs` (`idSpecs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SpecsValues`
--

LOCK TABLES `SpecsValues` WRITE;
/*!40000 ALTER TABLE `SpecsValues` DISABLE KEYS */;
INSERT INTO `SpecsValues` VALUES (2,1,2,'',2,0,0,0,0),(3,2,2,NULL,1,0,0,0,0),(4,3,1,'Secadora',0,0,0,0,0),(5,3,1,'Al sol',0,0,0,20,1),(6,4,2,NULL,4,0,0,0,0),(7,5,2,NULL,5,0,0,0,0);
/*!40000 ALTER TABLE `SpecsValues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stops`
--

DROP TABLE IF EXISTS `Stops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stops` (
  `idStops` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `time` int(11) DEFAULT '10',
  `arriveAt` varchar(50) DEFAULT '7',
  `idRoutes` int(11) NOT NULL,
  `type` int(11) DEFAULT '0' COMMENT '0 = address\n1 = client',
  `idAddress` int(11) DEFAULT NULL,
  PRIMARY KEY (`idStops`),
  KEY `fk_Stops_Routes1_idx` (`idRoutes`),
  CONSTRAINT `fk_Stops_Routes1` FOREIGN KEY (`idRoutes`) REFERENCES `Routes` (`idRoutes`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stops`
--

LOCK TABLES `Stops` WRITE;
/*!40000 ALTER TABLE `Stops` DISABLE KEYS */;
INSERT INTO `Stops` VALUES (1,'stop 1','stop 1 description',20,'9:30',1,1,1);
/*!40000 ALTER TABLE `Stops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stores`
--

DROP TABLE IF EXISTS `Stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stores` (
  `idStore` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `lat` decimal(10,8) NOT NULL,
  `lng` decimal(11,8) NOT NULL,
  `idEmployee` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idStore`),
  KEY `fk_Stores_Employee1_idx` (`idEmployee`),
  CONSTRAINT `fk_Stores_Employee1` FOREIGN KEY (`idEmployee`) REFERENCES `Employee` (`idEmployee`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stores`
--

LOCK TABLES `Stores` WRITE;
/*!40000 ALTER TABLE `Stores` DISABLE KEYS */;
INSERT INTO `Stores` VALUES (1,'unica',20.62132700,-103.41805600,1);
/*!40000 ALTER TABLE `Stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Supply`
--

DROP TABLE IF EXISTS `Supply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Supply` (
  `idSupply` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idSupplyType` int(10) unsigned NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `price` double DEFAULT '0',
  `serviceIncrement` double DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSupply`),
  KEY `fk_Product_ProductType1_idx` (`idSupplyType`),
  CONSTRAINT `fk_Product_ProductType1` FOREIGN KEY (`idSupplyType`) REFERENCES `SupplyType` (`idSupplyType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Supply`
--

LOCK TABLES `Supply` WRITE;
/*!40000 ALTER TABLE `Supply` DISABLE KEYS */;
INSERT INTO `Supply` VALUES (1,1,1,'Ariel','Jabon liquido',100,0,0),(2,1,1,'Membersmarc','Jabon liquido',120,15,0),(3,2,1,'Suavitel','Suavisante de prendas',120,3,0),(4,4,1,'Toallas Bla','Toallas de secado',120,5,0),(5,3,1,'Cloro','Blanqueador cloro',100,0,0),(6,5,1,'Ganchos sencillo','Gancho de fierro sencillo',300,2,0);
/*!40000 ALTER TABLE `Supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SupplyType`
--

DROP TABLE IF EXISTS `SupplyType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SupplyType` (
  `idSupplyType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSupplyType`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SupplyType`
--

LOCK TABLES `SupplyType` WRITE;
/*!40000 ALTER TABLE `SupplyType` DISABLE KEYS */;
INSERT INTO `SupplyType` VALUES (1,'Detergente','Jabones/Detergentes',0),(2,'Suavizante','Suavisantes',0),(3,'Blanqueador','Blanqueadores de prendas',0),(4,'Toallas de secado','Toallas para secado',0),(5,'Ganchos','ganchos para ropa',0);
/*!40000 ALTER TABLE `SupplyType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Task`
--

DROP TABLE IF EXISTS `Task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Task` (
  `idTask` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idTaskType` int(10) unsigned NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTask`),
  KEY `fk_Task_TaskType1_idx` (`idTaskType`),
  CONSTRAINT `fk_Task_TaskType1` FOREIGN KEY (`idTaskType`) REFERENCES `TaskType` (`idTaskType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;
INSERT INTO `Task` VALUES (1,1,'Servicio para ordenes','Servicio para ordenes.',0),(2,2,'Lavado lavadora','lavado de ropa general',0),(3,4,'Recojer','recojer pedido',0),(4,4,'Entregar','entregar pedido',0),(5,2,'Doblar ropa','doblado',0),(6,3,'Planchar','planchar',0),(7,2,'Lavado a mano','Lavado a mano',0),(8,5,'Solicitar Tintoreria','Solicitar Tintoreria',0),(9,5,'Recibir Tintoreria','Resepcion de tintoreria',0),(10,6,'Solicitar Costura','Costura',0),(11,6,'Recibir Costura','Resepcion de costura',0);
/*!40000 ALTER TABLE `Task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TaskType`
--

DROP TABLE IF EXISTS `TaskType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TaskType` (
  `idTaskType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ordersOnly` tinyint(1) DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTaskType`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TaskType`
--

LOCK TABLES `TaskType` WRITE;
/*!40000 ALTER TABLE `TaskType` DISABLE KEYS */;
INSERT INTO `TaskType` VALUES (1,'Tareas de Ordenes','Trabajo para servicio',1,0),(2,'Lavado','todo lo relevante a lavar ropa',0,0),(3,'Planchado','todo lo relevante a planchado',0,0),(4,'Transporte','recojer o entregar pedidos',0,0),(5,'Tintoreria','Seguimiento de tintoreria',0,0),(6,'Costura','Costura',0,0);
/*!40000 ALTER TABLE `TaskType` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-26 16:11:25
