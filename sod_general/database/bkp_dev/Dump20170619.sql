-- -----------------------------------------------------
-- Schema sod_db
-- -----------------------------------------------------
-- This is the initial_scheema for the service on demand application
--
DROP SCHEMA IF EXISTS `sod_db`;

-- -----------------------------------------------------
-- Schema sod_db
--
-- This is the initial_scheema for the service on demand application
--
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `sod_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sod_db`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: sod_db
-- ------------------------------------------------------
-- Server version	5.7.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `AccessKey`
--

DROP TABLE IF EXISTS `AccessKey`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccessKey` (
  `idAccessKey`      INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient`         INT(10) UNSIGNED NOT NULL,
  `idSocialNetworks` INT(10) UNSIGNED NOT NULL,
  `token`            VARCHAR(100)              DEFAULT NULL,
  `tokenSecre`       VARCHAR(250)              DEFAULT NULL,
  PRIMARY KEY (`idAccessKey`),
  KEY `fk_AccessKey_Clients1_idx` (`idClient`),
  KEY `fk_AccessKey_SocialNetworks1_idx` (`idSocialNetworks`),
  CONSTRAINT `fk_AccessKey_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AccessKey_SocialNetworks1` FOREIGN KEY (`idSocialNetworks`) REFERENCES `SocialNetworks` (`idSocialNetworks`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccessKey`
--

LOCK TABLES `AccessKey` WRITE;
/*!40000 ALTER TABLE `AccessKey`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `AccessKey`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address` (
  `idAddress` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient`  INT(10) UNSIGNED NOT NULL,
  `country`   VARCHAR(45)               DEFAULT NULL,
  `state`     VARCHAR(45)               DEFAULT NULL,
  `zipcode`   VARCHAR(45)               DEFAULT NULL,
  `city`      VARCHAR(45)               DEFAULT NULL,
  `address`   VARCHAR(250)              DEFAULT NULL,
  `address2`  VARCHAR(250)              DEFAULT NULL,
  `comments`  VARCHAR(255)              DEFAULT NULL,
  `lat`       DECIMAL(10, 8)            DEFAULT NULL,
  `lng`       DECIMAL(11, 8)            DEFAULT NULL,
  `prefered`  TINYINT(1)                DEFAULT '0',
  `factura`   TINYINT(1)                DEFAULT '0',
  PRIMARY KEY (`idAddress`),
  KEY `fk_Address_Clients1_idx` (`idClient`),
  CONSTRAINT `fk_Address_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 301
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address`
  DISABLE KEYS */;
INSERT INTO `Address` VALUES
  (1, 1, 'Mexico', 'Jalisco', '44540', 'Guadalajara', 'Calle Peninsula', 'Bosques de La Victoria',
      'No hay timbre tocar claxon', 20.64548516, -103.39241982, 1, 1),
  (2, 1, 'Mexico', 'Jalisco', '44540', 'Guadalajara', 'Calle Barlovento', 'Rinconada del Bosque', 'departamento sin timbre', 20.64997290, -103.38900805, 0, 0),
  (3, 2, NULL, NULL, NULL, NULL, 'Direccion', NULL, NULL, NULL, NULL, 0, 0),
  (4, 3, NULL, NULL, NULL, NULL, 'Colegio Becquer', NULL, NULL, NULL, NULL, 0, 0),
  (5, 4, NULL, NULL, NULL, NULL, 'Sochicalco 5955', NULL, NULL, NULL, NULL, 0, 0),
  (6, 5, NULL, NULL, NULL, NULL, 'Restaurantes Atemai', NULL, NULL, NULL, NULL, 0, 0),
  (7, 6, NULL, NULL, NULL, NULL, 'av.del pinar', NULL, NULL, NULL, NULL, 0, 0),
  (8, 7, NULL, NULL, NULL, NULL, '\"Av', NULL, NULL, NULL, NULL, 0, 0),
  (9, 8, NULL, NULL, NULL, NULL, 'Ciber', NULL, NULL, NULL, NULL, 0, 0),
  (10, 9, NULL, NULL, NULL, NULL, 'penisnula2749 int 12', NULL, NULL, NULL, NULL, 0, 0),
  (11, 10, NULL, NULL, NULL, NULL, 'Av. del pinar 3498', NULL, NULL, NULL, NULL, 0, 0),
  (12, 11, NULL, NULL, NULL, NULL, '18 de marzo 1875-B', NULL, NULL, NULL, NULL, 0, 0),
  (13, 12, NULL, NULL, NULL, NULL, 'Peten5484', NULL, NULL, NULL, NULL, 0, 0),
  (14, 13, NULL, NULL, NULL, NULL, 'Bahia Acapulco 2988-9', NULL, NULL, NULL, NULL, 0, 0),
  (15, 14, NULL, NULL, NULL, NULL, 'Tomas Balcazar', NULL, NULL, NULL, NULL, 0, 0),
  (16, 15, NULL, NULL, NULL, NULL, 'av. del pinar 3447', NULL, NULL, NULL, NULL, 0, 0),
  (17, 16, NULL, NULL, NULL, NULL, 'Cancun 5908', NULL, NULL, NULL, NULL, 0, 0),
  (18, 17, NULL, NULL, NULL, NULL, 'Tula 5736', NULL, NULL, NULL, NULL, 0, 0),
  (19, 18, NULL, NULL, NULL, NULL, 'Av. Del pinar 3340', NULL, NULL, NULL, NULL, 0, 0),
  (20, 19, NULL, NULL, NULL, NULL, 'Avenida del pinar 3240', NULL, NULL, NULL, NULL, 0, 0),
  (21, 20, NULL, NULL, NULL, NULL, 'Rio mezquitic 1154', NULL, NULL, NULL, NULL, 0, 0),
  (22, 21, NULL, NULL, NULL, NULL, 'Av. del  Pinar 3353', NULL, NULL, NULL, NULL, 0, 0),
  (23, 22, NULL, NULL, NULL, NULL, 'av. Del pinar3340 int.334', NULL, NULL, NULL, NULL, 0, 0),
  (24, 23, NULL, NULL, NULL, NULL, 'Copilco1501', NULL, NULL, NULL, NULL, 0, 0),
  (25, 24, 'Mexico', 'Jalisco', '45085', 'Zapopan', 'Río Tuxcacuesco 658', 'Loma Bonita Ejidal', NULL, 20.62484187, -103.40460777, 0, 0),
  (26, 25, NULL, NULL, NULL, NULL, 'Corona Boreal 4223', NULL, NULL, NULL, NULL, 0, 0),
  (27, 26, NULL, NULL, NULL, NULL, 'av. del pinar 3340-int.334', NULL, NULL, NULL, NULL, 0, 0),
  (28, 27, NULL, NULL, NULL, NULL, 'Lopez Mateos Sur 5871', NULL, NULL, NULL, NULL, 0, 0),
  (29, 28, NULL, NULL, NULL, NULL, 'Bonampak 3190', NULL, NULL, NULL, NULL, 0, 0),
  (30, 29, NULL, NULL, NULL, NULL, 'Isac newton 4105', NULL, NULL, NULL, NULL, 0, 0),
  (31, 30, NULL, NULL, NULL, NULL, 'Santa Ana Norte ', NULL, NULL, NULL, NULL, 0, 0),
  (32, 31, NULL, NULL, NULL, NULL, 'av. del pinar3366', NULL, NULL, NULL, NULL, 0, 0),
  (33, 32, NULL, NULL, NULL, NULL, 'PLaya Bucerias 5601', NULL, NULL, NULL, NULL, 0, 0),
  (34, 33, NULL, NULL, NULL, NULL, ' ', NULL, NULL, NULL, NULL, 0, 0),
  (35, 34, NULL, NULL, NULL, NULL, 'Copilco 5946', NULL, NULL, NULL, NULL, 0, 0),
  (36, 35, NULL, NULL, NULL, NULL, 'av.del pinar 3256', NULL, NULL, NULL, NULL, 0, 0),
  (37, 36, NULL, NULL, NULL, NULL, 'Lopez Mateos Sur 5871', NULL, NULL, NULL, NULL, 0, 0),
  (38, 37, NULL, NULL, NULL, NULL, 'Av. del pinar 3340 interior 314', NULL, NULL, NULL, NULL, 0, 0),
  (39, 38, NULL, NULL, NULL, NULL, 'Bonampack 3318', NULL, NULL, NULL, NULL, 0, 0),
  (40, 39, NULL, NULL, NULL, NULL, 'Rio Cuitzle5668', NULL, NULL, NULL, NULL, 0, 0),
  (41, 40, NULL, NULL, NULL, NULL, 'Cancun 1806', NULL, NULL, NULL, NULL, 0, 0),
  (42, 41, NULL, NULL, NULL, NULL, 'Corona Boreal 4223', NULL, NULL, NULL, NULL, 0, 0),
  (43, 42, NULL, NULL, NULL, NULL, 'cancun 5912', NULL, NULL, NULL, NULL, 0, 0),
  (44, 43, NULL, NULL, NULL, NULL, 'Aztlan 3335', NULL, NULL, NULL, NULL, 0, 0),
  (45, 44, NULL, NULL, NULL, NULL, 'Aztlan 1975', NULL, NULL, NULL, NULL, 0, 0),
  (46, 45, NULL, NULL, NULL, NULL, 'aztlan 3335', NULL, NULL, NULL, NULL, 0, 0),
  (47, 46, NULL, NULL, NULL, NULL, ' Aztlan Torre335 INt231', NULL, NULL, NULL, NULL, 0, 0),
  (48, 47, NULL, NULL, NULL, NULL, 'Cancun 1907', NULL, NULL, NULL, NULL, 0, 0),
  (49, 48, NULL, NULL, NULL, NULL, 'Cancun1907', NULL, NULL, NULL, NULL, 0, 0),
  (50, 49, NULL, NULL, NULL, NULL, 'Camino al iteso 8900', NULL, NULL, NULL, NULL, 0, 0),
  (51, 50, NULL, NULL, NULL, NULL, 'Playa Brucelas5589', NULL, NULL, NULL, NULL, 0, 0),
  (52, 51, NULL, NULL, NULL, NULL, 'Teotihuacan 2808', NULL, NULL, NULL, NULL, 0, 0),
  (53, 52, NULL, NULL, NULL, NULL, 'Cancun B', NULL, NULL, NULL, NULL, 0, 0),
  (54, 53, 'Mexico', 'Jalisco', '45080', 'Guadalajara', 'avenida del pinar 1822', 'Pinar de la Calma', 'llevarle la ropa cuando este lista', NULL, NULL, 0, 0),
  (55, 54, NULL, NULL, NULL, NULL, 'Colegio Becker', NULL, NULL, NULL, NULL, 0, 0),
  (56, 55, NULL, NULL, NULL, NULL, 'peninsula 2749 15', NULL, NULL, NULL, NULL, 0, 0),
  (57, 56, NULL, NULL, NULL, NULL, 'Av. del pinar 2889', NULL, NULL, NULL, NULL, 0, 0),
  (58, 57, NULL, NULL, NULL, NULL, 'Rio Nilo 7922', NULL, NULL, NULL, NULL, 0, 0),
  (59, 58, NULL, NULL, NULL, NULL, 'Loma Linda8043', NULL, NULL, NULL, NULL, 0, 0),
  (60, 59, NULL, NULL, NULL, NULL, 'Rio santiago 5738', NULL, NULL, NULL, NULL, 0, 0),
  (61, 60, NULL, NULL, NULL, NULL, 'hotel hoLI day', NULL, NULL, NULL, NULL, 0, 0),
  (62, 61, NULL, NULL, NULL, NULL, 'AV. PINAR 3340 int 313', NULL, NULL, NULL, NULL, 0, 0),
  (63, 62, NULL, NULL, NULL, NULL, '\"Araceli Zouza 5140 A', NULL, NULL, NULL, NULL, 0, 0),
  (64, 63, NULL, NULL, NULL, NULL, 'uxmal 1409', NULL, NULL, NULL, NULL, 0, 0),
  (65, 64, NULL, NULL, NULL, NULL, 'mazamitla 1922', NULL, NULL, NULL, NULL, 0, 0),
  (66, 65, NULL, NULL, NULL, NULL, 'Av. del tesoro', NULL, NULL, NULL, NULL, 0, 0),
  (67, 66, NULL, NULL, NULL, NULL, 'rio lerma 1898', NULL, NULL, NULL, NULL, 0, 0),
  (68, 67, NULL, NULL, NULL, NULL, 'teotihuacan2966', NULL, NULL, NULL, NULL, 0, 0),
  (69, 68, NULL, NULL, NULL, NULL, 'Mariano Otero 1354', NULL, NULL, NULL, NULL, 0, 0),
  (70, 69, NULL, NULL, NULL, NULL, 'acuatix', NULL, NULL, NULL, NULL, 0, 0),
  (71, 70, NULL, NULL, NULL, NULL, 'Av. del  Pinar 3353', NULL, NULL, NULL, NULL, 0, 0),
  (72, 71, 'Mexico', 'Jalisco', '45090', 'Zapopan', 'Calle Uxmal 1410', 'Pinar de la Calma', NULL, 20.61897771, -103.41274559, 0, 0),
  (73, 72, NULL, NULL, NULL, NULL, 'Av. del pinar 3310', NULL, NULL, NULL, NULL, 0, 0),
  (74, 73, NULL, NULL, NULL, NULL, 'obsidiana 2887', NULL, NULL, NULL, NULL, 0, 0),
  (75, 74, NULL, NULL, NULL, NULL, 'Aztlan 3335', NULL, NULL, NULL, NULL, 0, 0),
  (76, 75, NULL, NULL, NULL, NULL, 'Cancun  1905', NULL, NULL, NULL, NULL, 0, 0),
  (77, 76, NULL, NULL, NULL, NULL, '\"Morelos 2262', NULL, NULL, NULL, NULL, 0, 0),
  (78, 77, NULL, NULL, NULL, NULL, 'Aztlan 3335', NULL, NULL, NULL, NULL, 0, 0),
  (79, 78, NULL, NULL, NULL, NULL, 'Avenida del pinar 3316', NULL, NULL, NULL, NULL, 0, 0),
  (80, 79, NULL, NULL, NULL, NULL, 'Sierra Mazamitla 5581', NULL, NULL, NULL, NULL, 0, 0),
  (81, 80, NULL, NULL, NULL, NULL, 'av. del pinar 1908', NULL, NULL, NULL, NULL, 0, 0),
  (82, 81, NULL, NULL, NULL, NULL, 'Av. del Tesoro 981 int. 202', NULL, NULL, NULL, NULL, 0, 0),
  (83, 82, NULL, NULL, NULL, NULL, 'teotihuacan 1906', NULL, NULL, NULL, NULL, 0, 0),
  (84, 83, NULL, NULL, NULL, NULL, 'Valle de Atejamac  2118', NULL, NULL, NULL, NULL, 0, 0),
  (85, 84, NULL, NULL, NULL, NULL, 'parroquia santo niño de atocho', NULL, NULL, NULL, NULL, 0, 0),
  (86, 85, NULL, NULL, NULL, NULL, 'Andador Xochicalco 5966', NULL, NULL, NULL, NULL, 0, 0),
  (87, 86, NULL, NULL, NULL, NULL, 'Peten 5912', NULL, NULL, NULL, NULL, 0, 0),
  (88, 87, NULL, NULL, NULL, NULL, 'Avenida del pinar 3329', NULL, NULL, NULL, NULL, 0, 0),
  (89, 88, NULL, NULL, NULL, NULL, 'Avenida del pinar 3340 int 332', NULL, NULL, NULL, NULL, 0, 0),
  (90, 89, NULL, NULL, NULL, NULL, '\"Rio Colotlan 2084', NULL, NULL, NULL, NULL, 0, 0),
  (91, 90, NULL, NULL, NULL, NULL, '\"Tlatelolco 3344', NULL, NULL, NULL, NULL, 0, 0),
  (92, 91, NULL, NULL, NULL, NULL, 'volcan cofre de perote 5057. Casa 2', NULL, NULL, NULL, NULL, 0, 0),
  (93, 92, NULL, NULL, NULL, NULL, '\"jaime Rivera 3081', NULL, NULL, NULL, NULL, 0, 0),
  (94, 93, NULL, NULL, NULL, NULL, 'Rio Mezquitic 1154', NULL, NULL, NULL, NULL, 0, 0),
  (95, 94, NULL, NULL, NULL, NULL, 'Av. del pinar 3498', NULL, NULL, NULL, NULL, 0, 0),
  (96, 95, NULL, NULL, NULL, NULL, 'Cancun 1890', NULL, NULL, NULL, NULL, 0, 0),
  (97, 96, NULL, NULL, NULL, NULL, 'Mariano Otero 1354', NULL, NULL, NULL, NULL, 0, 0),
  (98, 97, NULL, NULL, NULL, NULL, 'Quick Plaza del sol', NULL, NULL, NULL, NULL, 0, 0),
  (99, 98, NULL, NULL, NULL, NULL, 'corona boreal ', NULL, NULL, NULL, NULL, 0, 0),
  (100, 99, NULL, NULL, NULL, NULL, 'Av. del pinar 3340', NULL, NULL, NULL, NULL, 0, 0),
  (101, 100, NULL, NULL, NULL, NULL, 'Volcan Paricutin  5526 interior 21', NULL, NULL, NULL, NULL, 0, 0),
  (102, 101, NULL, NULL, NULL, NULL, 'Tuxtla 5914', NULL, NULL, NULL, NULL, 0, 0),
  (103, 102, NULL, NULL, NULL, NULL, 'Chicomostock 3044', NULL, NULL, NULL, NULL, 0, 0),
  (104, 103, NULL, NULL, NULL, NULL, 'Bonampack3318', NULL, NULL, NULL, NULL, 0, 0),
  (105, 104, NULL, NULL, NULL, NULL, 'Av. del Pinar 1974', NULL, NULL, NULL, NULL, 0, 0),
  (106, 105, NULL, NULL, NULL, NULL, 'Cuicuilco 1795', NULL, NULL, NULL, NULL, 0, 0),
  (107, 106, NULL, NULL, NULL, NULL, 'av.del pinar 3223', NULL, NULL, NULL, NULL, 0, 0),
  (108, 107, NULL, NULL, NULL, NULL, 'aztlan 1805', NULL, NULL, NULL, NULL, 0, 0),
  (109, 108, NULL, NULL, NULL, NULL, '\"aldama 83105', NULL, NULL, NULL, NULL, 0, 0),
  (110, 109, NULL, NULL, NULL, NULL, 'AZTLAN 1950', NULL, NULL, NULL, NULL, 0, 0),
  (111, 110, NULL, NULL, NULL, NULL, 'Cuicuilco 1813', NULL, NULL, NULL, NULL, 0, 0),
  (112, 111, NULL, NULL, NULL, NULL, 'Rio Santiago 5814', NULL, NULL, NULL, NULL, 0, 0),
  (113, 112, NULL, NULL, NULL, NULL, 'cuicuilco 1936', NULL, NULL, NULL, NULL, 0, 0),
  (114, 113, NULL, NULL, NULL, NULL, 'playa bucerias 5589', NULL, NULL, NULL, NULL, 0, 0),
  (115, 114, NULL, NULL, NULL, NULL, 'Aurora Boreal 3937 int 3', NULL, NULL, NULL, NULL, 0, 0),
  (116, 115, NULL, NULL, NULL, NULL, 'av. del pinar 3044 dep3', NULL, NULL, NULL, NULL, 0, 0),
  (117, 116, NULL, NULL, NULL, NULL, 'cacaco 1726', NULL, NULL, NULL, NULL, 0, 0),
  (118, 117, NULL, NULL, NULL, NULL, 'Av. del Pinar 3050 int 8', NULL, NULL, NULL, NULL, 0, 0),
  (119, 118, NULL, NULL, NULL, NULL, 'bonanpack 5955', NULL, NULL, NULL, NULL, 0, 0),
  (120, 119, NULL, NULL, NULL, NULL, 'Primo Feliciano Velazquez 761', NULL, NULL, NULL, NULL, 0, 0),
  (121, 120, NULL, NULL, NULL, NULL, 'Peten 5918', NULL, NULL, NULL, NULL, 0, 0),
  (122, 121, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (123, 122, NULL, NULL, NULL, NULL, 'Bonampack 3424 int 13', NULL, NULL, NULL, NULL, 0, 0),
  (124, 123, NULL, NULL, NULL, NULL, 'Colegio Becker', NULL, NULL, NULL, NULL, 0, 0),
  (125, 124, NULL, NULL, NULL, NULL, 'Av. del pinar 2857', NULL, NULL, NULL, NULL, 0, 0),
  (126, 125, NULL, NULL, NULL, NULL, 'Echeverria', NULL, NULL, NULL, NULL, 0, 0),
  (127, 126, NULL, NULL, NULL, NULL, 'esturion 3071', NULL, NULL, NULL, NULL, 0, 0),
  (128, 127, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (129, 128, NULL, NULL, NULL, NULL, 'Tuxtla5892', NULL, NULL, NULL, NULL, 0, 0),
  (130, 129, NULL, NULL, NULL, NULL, 'ixtepete 3492 int 4', NULL, NULL, NULL, NULL, 0, 0),
  (131, 130, NULL, NULL, NULL, NULL, 'Ixtepete 3285', NULL, NULL, NULL, NULL, 0, 0),
  (132, 131, NULL, NULL, NULL, NULL, 'Av. del Pinar 3230', NULL, NULL, NULL, NULL, 0, 0),
  (133, 132, NULL, NULL, NULL, NULL, 'rio mexquitic 1147', NULL, NULL, NULL, NULL, 0, 0),
  (134, 133, NULL, NULL, NULL, NULL, 'Avenida del Pinar 3498', NULL, NULL, NULL, NULL, 0, 0),
  (135, 134, NULL, NULL, NULL, NULL, 'Cancun 1788', NULL, NULL, NULL, NULL, 0, 0),
  (136, 135, NULL, NULL, NULL, NULL, 'cisne 1249 colonia morelos entre cardenal y dos de abril', NULL, NULL, NULL, NULL, 0, 0),
  (137, 136, NULL, NULL, NULL, NULL, 'la burreteria', NULL, NULL, NULL, NULL, 0, 0),
  (138, 137, NULL, NULL, NULL, NULL, 'Cuicuilco1974', NULL, NULL, NULL, NULL, 0, 0),
  (139, 138, NULL, NULL, NULL, NULL, 'rio tuxpan 894', NULL, NULL, NULL, NULL, 0, 0),
  (140, 139, NULL, NULL, NULL, NULL, 'av del pinar 3340', NULL, NULL, NULL, NULL, 0, 0),
  (141, 140, NULL, NULL, NULL, NULL, '\"Av', NULL, NULL, NULL, NULL, 0, 0),
  (142, 141, NULL, NULL, NULL, NULL, 'peninsula 2749 int 10', NULL, NULL, NULL, NULL, 0, 0),
  (143, 142, NULL, NULL, NULL, NULL, 'av del pinar 3340 1', NULL, NULL, NULL, NULL, 0, 0),
  (144, 143, NULL, NULL, NULL, NULL, 'copan 5755', NULL, NULL, NULL, NULL, 0, 0),
  (145, 144, NULL, NULL, NULL, NULL, 'Av. del PInar 2805-b8', NULL, NULL, NULL, NULL, 0, 0),
  (146, 145, NULL, NULL, NULL, NULL, 'Tula 5715', NULL, NULL, NULL, NULL, 0, 0),
  (147, 146, NULL, NULL, NULL, NULL, 'cancun 5906', NULL, NULL, NULL, NULL, 0, 0),
  (148, 147, NULL, NULL, NULL, NULL, 'Tuxtla 5914 int 1', NULL, NULL, NULL, NULL, 0, 0),
  (149, 148, NULL, NULL, NULL, NULL, 'av del pinar 3340', NULL, NULL, NULL, NULL, 0, 0),
  (150, 149, NULL, NULL, NULL, NULL, 'laguna 15fraccionamiento loreto', NULL, NULL, NULL, NULL, 0, 0),
  (151, 150, NULL, NULL, NULL, NULL, 'Aztlan 3335', NULL, NULL, NULL, NULL, 0, 0),
  (152, 151, NULL, NULL, NULL, NULL, 'Aztlan 3335 int213', NULL, NULL, NULL, NULL, 0, 0),
  (153, 152, NULL, NULL, NULL, NULL, 'Tical 1615', NULL, NULL, NULL, NULL, 0, 0),
  (154, 153, NULL, NULL, NULL, NULL, 'cancun 5908', NULL, NULL, NULL, NULL, 0, 0),
  (155, 154, NULL, NULL, NULL, NULL, 'Andador Mayapan 3362 in 31', NULL, NULL, NULL, NULL, 0, 0),
  (156, 155, NULL, NULL, NULL, NULL, '\"Martillo 2139', NULL, NULL, NULL, NULL, 0, 0),
  (157, 156, NULL, NULL, NULL, NULL, 'isla mexcala4921 int23', NULL, NULL, NULL, NULL, 0, 0),
  (158, 157, NULL, NULL, NULL, NULL, 'galileo galiley3608', NULL, NULL, NULL, NULL, 0, 0),
  (159, 158, NULL, NULL, NULL, NULL, 'cuicuilco3396', NULL, NULL, NULL, NULL, 0, 0),
  (160, 159, NULL, NULL, NULL, NULL, 'Cancun 1745', NULL, NULL, NULL, NULL, 0, 0),
  (161, 160, NULL, NULL, NULL, NULL, 'Bonampack 1948', NULL, NULL, NULL, NULL, 0, 0),
  (162, 161, NULL, NULL, NULL, NULL, 'turqueza 2531', NULL, NULL, NULL, NULL, 0, 0),
  (163, 162, NULL, NULL, NULL, NULL, 'Av. del pinar 2805', NULL, NULL, NULL, NULL, 0, 0),
  (164, 163, NULL, NULL, NULL, NULL, 'Aztlan 3335 inte 133', NULL, NULL, NULL, NULL, 0, 0),
  (165, 164, NULL, NULL, NULL, NULL, 'MORELOS 2368', NULL, NULL, NULL, NULL, 0, 0),
  (166, 165, NULL, NULL, NULL, NULL, 'aztlan 3335 int 222', NULL, NULL, NULL, NULL, 0, 0),
  (167, 166, NULL, NULL, NULL, NULL, 'av padre javier scheifler 719 int 9', NULL, NULL, NULL, NULL, 0, 0),
  (168, 167, NULL, NULL, NULL, NULL, 'Mariano Otero  5654', NULL, NULL, NULL, NULL, 0, 0),
  (169, 168, NULL, NULL, NULL, NULL, 'Avenida del Tesoro 5781', NULL, NULL, NULL, NULL, 0, 0),
  (170, 169, NULL, NULL, NULL, NULL, 'Quebrada 3282 A int 12', NULL, NULL, NULL, NULL, 0, 0),
  (171, 170, NULL, NULL, NULL, NULL, 'Quebrada 3282 A int 12', NULL, NULL, NULL, NULL, 0, 0),
  (172, 171, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (173, 172, NULL, NULL, NULL, NULL, 'Rio Colotlan 1651', NULL, NULL, NULL, NULL, 0, 0),
  (174, 173, NULL, NULL, NULL, NULL, '\"Litoral 2510', NULL, NULL, NULL, NULL, 0, 0),
  (175, 174, NULL, NULL, NULL, NULL, 'Av. del Pinar 1776', NULL, NULL, NULL, NULL, 0, 0),
  (176, 175, NULL, NULL, NULL, NULL, 'Galerias', NULL, NULL, NULL, NULL, 0, 0),
  (177, 176, NULL, NULL, NULL, NULL, 'Plaza del Sol', NULL, NULL, NULL, NULL, 0, 0),
  (178, 177, NULL, NULL, NULL, NULL, 'molina 2876', NULL, NULL, NULL, NULL, 0, 0),
  (179, 178, NULL, NULL, NULL, NULL, 'Aztlan 3335 132', NULL, NULL, NULL, NULL, 0, 0),
  (180, 179, NULL, NULL, NULL, NULL, 'Peten 5918', NULL, NULL, NULL, NULL, 0, 0),
  (181, 180, NULL, NULL, NULL, NULL, 'Claudio Tolomeo', NULL, NULL, NULL, NULL, 0, 0),
  (182, 181, NULL, NULL, NULL, NULL, 'prolongacion las Fuentes 3012', NULL, NULL, NULL, NULL, 0, 0),
  (183, 182, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (184, 183, NULL, NULL, NULL, NULL, 'Cuicuilco 1795', NULL, NULL, NULL, NULL, 0, 0),
  (185, 184, NULL, NULL, NULL, NULL, 'rio atengo 1839', NULL, NULL, NULL, NULL, 0, 0),
  (186, 185, NULL, NULL, NULL, NULL, 'av faro 2778', NULL, NULL, NULL, NULL, 0, 0),
  (187, 186, NULL, NULL, NULL, NULL, 'av del pinar 3447', NULL, NULL, NULL, NULL, 0, 0),
  (188, 187, NULL, NULL, NULL, NULL, 'peninsula 2727 interior 1', NULL, NULL, NULL, NULL, 0, 0),
  (189, 188, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (190, 189, NULL, NULL, NULL, NULL, 'chicomostok 1615', NULL, NULL, NULL, NULL, 0, 0),
  (191, 190, NULL, NULL, NULL, NULL, 'michoacan 144 colonia el mante', NULL, NULL, NULL, NULL, 0, 0),
  (192, 191, NULL, NULL, NULL, NULL, 'Rio Santiago 5932', NULL, NULL, NULL, NULL, 0, 0),
  (193, 192, NULL, NULL, NULL, NULL, 'peten 5903', NULL, NULL, NULL, NULL, 0, 0),
  (194, 193, NULL, NULL, NULL, NULL, 'Teotihuacan 1922', NULL, NULL, NULL, NULL, 0, 0),
  (195, 194, NULL, NULL, NULL, NULL, 'Av. del pinar 1766', NULL, NULL, NULL, NULL, 0, 0),
  (196, 195, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (197, 196, NULL, NULL, NULL, NULL, 'sierra de mazamitla 5895int 1', NULL, NULL, NULL, NULL, 0, 0),
  (198, 197, NULL, NULL, NULL, NULL, 'cuicuilco1285', NULL, NULL, NULL, NULL, 0, 0),
  (199, 198, NULL, NULL, NULL, NULL, 'cancun 1788', NULL, NULL, NULL, NULL, 0, 0),
  (200, 199, NULL, NULL, NULL, NULL, 'Aztlan', NULL, NULL, NULL, NULL, 0, 0),
  (201, 200, NULL, NULL, NULL, NULL, 'cancun 5977', NULL, NULL, NULL, NULL, 0, 0),
  (202, 201, NULL, NULL, NULL, NULL, 'av las fuentes 99', NULL, NULL, NULL, NULL, 0, 0),
  (203, 202, NULL, NULL, NULL, NULL, 'Av. Cruz sur 2447', NULL, NULL, NULL, NULL, 0, 0),
  (204, 203, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (205, 204, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (206, 205, NULL, NULL, NULL, NULL, 'av del pinar 1776', NULL, NULL, NULL, NULL, 0, 0),
  (207, 206, NULL, NULL, NULL, NULL, 'Aztlan ', NULL, NULL, NULL, NULL, 0, 0),
  (208, 207, NULL, NULL, NULL, NULL, 'aztlan 3282', NULL, NULL, NULL, NULL, 0, 0),
  (209, 208, NULL, NULL, NULL, NULL, 'Vistas a la catedral 3041', NULL, NULL, NULL, NULL, 0, 0),
  (210, 209, NULL, NULL, NULL, NULL, 'Faro 2768', NULL, NULL, NULL, NULL, 0, 0),
  (211, 210, NULL, NULL, NULL, NULL, 'Cancun 1440', NULL, NULL, NULL, NULL, 0, 0),
  (212, 211, NULL, NULL, NULL, NULL, 'Av. del pinar 3230', NULL, NULL, NULL, NULL, 0, 0),
  (213, 212, NULL, NULL, NULL, NULL, 'Quebrada 2714 2', NULL, NULL, NULL, NULL, 0, 0),
  (214, 213, NULL, NULL, NULL, NULL, '\"Av. del pinar ', NULL, NULL, NULL, NULL, 0, 0),
  (215, 214, 'Mexico', 'Jalisco', '45070', 'Zapopan', 'Calle Sagitario 5598', 'Arboledas', 'Marcar antes de acudir', 20.62637817, -103.42320085, 0, 0),
  (216, 215, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (217, 216, NULL, NULL, NULL, NULL, 'Andador coba 3333 int 12', NULL, NULL, NULL, NULL, 0, 0),
  (218, 217, NULL, NULL, NULL, NULL, 'Teotihuacan 1939', NULL, NULL, NULL, NULL, 0, 0),
  (219, 218, NULL, NULL, NULL, NULL, 'Av. del Pinar  3427', NULL, NULL, NULL, NULL, 0, 0),
  (220, 219, NULL, NULL, NULL, NULL, 'Sierra Mazamitla 5895 int 1', NULL, NULL, NULL, NULL, 0, 0),
  (221, 220, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (222, 221, NULL, NULL, NULL, NULL, 'aztlan 1889', NULL, NULL, NULL, NULL, 0, 0),
  (223, 222, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (224, 223, NULL, NULL, NULL, NULL, 'Av. del Pinar 3447', NULL, NULL, NULL, NULL, 0, 0),
  (225, 224, NULL, NULL, NULL, NULL, 'Bonampack 3307', NULL, NULL, NULL, NULL, 0, 0),
  (226, 225, NULL, NULL, NULL, NULL, 'privada de las gardenias sur 11 el moral', NULL, NULL, NULL, NULL, 0, 0),
  (227, 226, NULL, NULL, NULL, NULL, 'Rio Santiago 5923', NULL, NULL, NULL, NULL, 0, 0),
  (228, 227, NULL, NULL, NULL, NULL, 'peten 5918', NULL, NULL, NULL, NULL, 0, 0),
  (229, 228, NULL, NULL, NULL, NULL, 'av del pinar 3032', NULL, NULL, NULL, NULL, 0, 0),
  (230, 229, NULL, NULL, NULL, NULL, 'aztlan 1822', NULL, NULL, NULL, NULL, 0, 0),
  (231, 230, NULL, NULL, NULL, NULL, 'av del pinar 1908', NULL, NULL, NULL, NULL, 0, 0),
  (232, 231, NULL, NULL, NULL, NULL, 'Bonampack 3307', NULL, NULL, NULL, NULL, 0, 0),
  (233, 232, NULL, NULL, NULL, NULL, 'Tortas Alex', NULL, NULL, NULL, NULL, 0, 0),
  (234, 233, NULL, NULL, NULL, NULL, 'av del pinar esquina con tajin', NULL, NULL, NULL, NULL, 0, 0),
  (235, 234, NULL, NULL, NULL, NULL, 'av del pinar esquina con tajin', NULL, NULL, NULL, NULL, 0, 0),
  (236, 235, NULL, NULL, NULL, NULL, 'av del pinar 3350', NULL, NULL, NULL, NULL, 0, 0),
  (237, 236, NULL, NULL, NULL, NULL, 'Peten 5921', NULL, NULL, NULL, NULL, 0, 0),
  (238, 237, NULL, NULL, NULL, NULL, 'av del pinar 3340', NULL, NULL, NULL, NULL, 0, 0),
  (239, 238, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (240, 239, NULL, NULL, NULL, NULL, 'Tajin 5898 dep 6 3 piso', NULL, NULL, NULL, NULL, 0, 0),
  (241, 240, NULL, NULL, NULL, NULL, 'bonanpak 3424 int 3d', NULL, NULL, NULL, NULL, 0, 0),
  (242, 241, NULL, NULL, NULL, NULL, 'av del pinar', NULL, NULL, NULL, NULL, 0, 0),
  (243, 242, NULL, NULL, NULL, NULL, 'Aztlan 3335-113', NULL, NULL, NULL, NULL, 0, 0),
  (244, 243, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (245, 244, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (246, 245, NULL, NULL, NULL, NULL, 'Av. del Pinar 3223', NULL, NULL, NULL, NULL, 0, 0),
  (247, 246, NULL, NULL, NULL, NULL, '\"can mayor 3522', NULL, NULL, NULL, NULL, 0, 0),
  (248, 247, NULL, NULL, NULL, NULL, 'aztlan 1799', NULL, NULL, NULL, NULL, 0, 0),
  (249, 248, NULL, NULL, NULL, NULL, 'cancum 1835', NULL, NULL, NULL, NULL, 0, 0),
  (250, 249, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (251, 250, NULL, NULL, NULL, NULL, 'av del pinar 24', NULL, NULL, NULL, NULL, 0, 0),
  (252, 251, NULL, NULL, NULL, NULL, 'Av. dle PInar 3340', NULL, NULL, NULL, NULL, 0, 0),
  (253, 252, NULL, NULL, NULL, NULL, 'sierra tapalpa 176-', NULL, NULL, NULL, NULL, 0, 0),
  (254, 253, NULL, NULL, NULL, NULL, 'Teotihuacan 3313', NULL, NULL, NULL, NULL, 0, 0),
  (255, 254, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (256, 255, NULL, NULL, NULL, NULL, 'av valle de atemajac 2028', NULL, NULL, NULL, NULL, 0, 0),
  (257, 256, NULL, NULL, NULL, NULL, 'av del pinar 3340', NULL, NULL, NULL, NULL, 0, 0),
  (258, 257, NULL, NULL, NULL, NULL, 'ixtepete 1834', NULL, NULL, NULL, NULL, 0, 0),
  (259, 258, NULL, NULL, NULL, NULL, 'av del pinar 1621', NULL, NULL, NULL, NULL, 0, 0),
  (260, 259, NULL, NULL, NULL, NULL, 'rio lerma 1898', NULL, NULL, NULL, NULL, 0, 0),
  (261, 260, NULL, NULL, NULL, NULL, 'aztlan 3335', NULL, NULL, NULL, NULL, 0, 0),
  (262, 261, NULL, NULL, NULL, NULL, ' alejandro\"', NULL, NULL, NULL, NULL, 0, 0),
  (263, 262, NULL, NULL, NULL, NULL, 'Teotihuacan 1894', NULL, NULL, NULL, NULL, 0, 0),
  (264, 263, NULL, NULL, NULL, NULL, 'lapiz laszuli 3037', NULL, NULL, NULL, NULL, 0, 0),
  (265, 264, NULL, NULL, NULL, NULL, 'av del pinar 3381', NULL, NULL, NULL, NULL, 0, 0),
  (266, 265, NULL, NULL, NULL, NULL, 'ixtepete 3285 int a', NULL, NULL, NULL, NULL, 0, 0),
  (267, 266, NULL, NULL, NULL, NULL, 'sierra de mazamitla 5521', NULL, NULL, NULL, NULL, 0, 0),
  (268, 267, NULL, NULL, NULL, NULL, 'av del pinar 3447', NULL, NULL, NULL, NULL, 0, 0),
  (269, 268, NULL, NULL, NULL, NULL, 'Teotihuacan 3329', NULL, NULL, NULL, NULL, 0, 0),
  (270, 269, NULL, NULL, NULL, NULL, 'pinar 3032 int 1', NULL, NULL, NULL, NULL, 0, 0),
  (271, 270, NULL, NULL, NULL, NULL, 'rio atengo 2085', NULL, NULL, NULL, NULL, 0, 0),
  (272, 271, NULL, NULL, NULL, NULL, 'Tical 1458', NULL, NULL, NULL, NULL, 0, 0),
  (273, 272, NULL, NULL, NULL, NULL, 'Teotihuacan', NULL, NULL, NULL, NULL, 0, 0),
  (274, 273, NULL, NULL, NULL, NULL, '\"Obsidiana2747', NULL, NULL, NULL, NULL, 0, 0),
  (275, 274, NULL, NULL, NULL, NULL, 'Bonampack 1972', NULL, NULL, NULL, NULL, 0, 0),
  (276, 275, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (277, 276, NULL, NULL, NULL, NULL, 'sochicalco 5961', NULL, NULL, NULL, NULL, 0, 0),
  (278, 277, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (279, 278, NULL, NULL, NULL, NULL, 'ixtepete 3479', NULL, NULL, NULL, NULL, 0, 0),
  (280, 279, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (281, 280, NULL, NULL, NULL, NULL, 'peten 5840', NULL, NULL, NULL, NULL, 0, 0),
  (282, 281, NULL, NULL, NULL, NULL, 'peten 5840', NULL, NULL, NULL, NULL, 0, 0),
  (283, 282, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (284, 283, NULL, NULL, NULL, NULL, 'Teotihuacan 1666', NULL, NULL, NULL, NULL, 0, 0),
  (285, 284, NULL, NULL, NULL, NULL, 'cancun 3224', NULL, NULL, NULL, NULL, 0, 0),
  (286, 285, NULL, NULL, NULL, NULL, 'credomatik', NULL, NULL, NULL, NULL, 0, 0),
  (287, 286, NULL, NULL, NULL, NULL, 'teotiuhacan 1922', NULL, NULL, NULL, NULL, 0, 0),
  (288, 287, NULL, NULL, NULL, NULL, 'Sierra Mazamitla 5883', NULL, NULL, NULL, NULL, 0, 0),
  (289, 288, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, 0, 0),
  (290, 289, NULL, NULL, NULL, NULL, 'Colegio Becker', NULL, NULL, NULL, NULL, 0, 0),
  (291, 290, NULL, NULL, NULL, NULL, 'Auhetes 1740', NULL, NULL, NULL, NULL, 0, 0),
  (292, 291, NULL, NULL, NULL, NULL, 'Teotihuacan 1806', NULL, NULL, NULL, NULL, 0, 0),
  (293, 292, NULL, NULL, NULL, NULL, 'Av. del Pinar ', NULL, NULL, NULL, NULL, 0, 0),
  (294, 293, NULL, NULL, NULL, NULL, 'Senderos de monteverde 181', NULL, NULL, NULL, NULL, 0, 0),
  (295, 294, NULL, NULL, NULL, NULL, 'Av. dle PInar 3498 int. 8', NULL, NULL, NULL, NULL, 0, 0),
  (296, 295, NULL, NULL, NULL, NULL, 'Cancun Tienda', NULL, NULL, NULL, NULL, 0, 0),
  (297, 150, 'Mexico', 'Jalisco', '45080', 'Zapopan', 'Aztlán', 'Pinar de la Calma', 'El pasa por la ropa', 20.62132700,
        -103.41805600, 0, 0),
  (298, 296, 'Mexico', 'Jalisco', '44500', 'Guadalajara', 'Calzada Lázaro Cárdenas', 'Chapalita',
        'MARCARLE CUANDO LLEGUEN AL DOMICILIO', 20.62132700, -103.41805600, 1, 0),
  (299, 297, 'Mexico', 'Jalisco', '44630', 'Guadalajara', 'Avenida de las Américas', 'Providencia',
        'remojar sabanas lunes martes y miercoles', 20.62132700, -103.41805600, 1, 0),
  (300, 298, 'Mexico', 'Jalisco', '45030', 'Zapopan', 'Avenida Rafael Sanzio', 'Camichines Vallarta', NULL, 20.67970709,
        -103.43177319, 1, 0);
/*!40000 ALTER TABLE `Address`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Asset`
--

DROP TABLE IF EXISTS `Asset`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Asset` (
  `idAsset`     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idAssetType` INT(10) UNSIGNED NOT NULL,
  `status`      INT(11)          NOT NULL DEFAULT '0',
  `name`        VARCHAR(45)               DEFAULT NULL,
  `description` VARCHAR(250)              DEFAULT NULL,
  `deleted`     INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idAsset`),
  KEY `fk_Asset_AssetType1_idx` (`idAssetType`),
  CONSTRAINT `fk_Asset_AssetType1` FOREIGN KEY (`idAssetType`) REFERENCES `AssetType` (`idAssetType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Asset`
--

LOCK TABLES `Asset` WRITE;
/*!40000 ALTER TABLE `Asset`
  DISABLE KEYS */;
INSERT INTO `Asset`
VALUES (1, 1, 1, 'lavadora 1', 'lavadora roja', 0), (2, 1, 1, 'lavadora 2', 'lavadora roja rota', 0),
  (3, 3, 1, 'moto1', 'moto roja placas aaabbb92', 0);
/*!40000 ALTER TABLE `Asset`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AssetTaskOrder`
--

DROP TABLE IF EXISTS `AssetTaskOrder`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AssetTaskOrder` (
  `idAssetTaskOrder` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderTask`      INT(10) UNSIGNED NOT NULL,
  `Asset_idAsset`    INT(10) UNSIGNED NOT NULL,
  `comments`         VARCHAR(250)              DEFAULT NULL,
  PRIMARY KEY (`idAssetTaskOrder`),
  KEY `fk_AssetTaskOrder_Asset1_idx` (`Asset_idAsset`),
  KEY `fk_AssetTaskOrder_OrderTask1_idx` (`idOrderTask`),
  CONSTRAINT `fk_AssetTaskOrder_Asset1` FOREIGN KEY (`Asset_idAsset`) REFERENCES `Asset` (`idAsset`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AssetTaskOrder_OrderTask1` FOREIGN KEY (`idOrderTask`) REFERENCES `OrderTask` (`idOrderTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssetTaskOrder`
--

LOCK TABLES `AssetTaskOrder` WRITE;
/*!40000 ALTER TABLE `AssetTaskOrder`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `AssetTaskOrder`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AssetTaskService`
--

DROP TABLE IF EXISTS `AssetTaskService`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AssetTaskService` (
  `idAssetTaskService` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idAsset`            INT(10) UNSIGNED NOT NULL,
  `idServiceTask`      INT(10) UNSIGNED NOT NULL,
  `comments`           VARCHAR(250)              DEFAULT NULL,
  PRIMARY KEY (`idAssetTaskService`),
  KEY `fk_AssetTaskService_Asset1_idx` (`idAsset`),
  KEY `fk_AssetTaskService_ServiceTask1_idx` (`idServiceTask`),
  CONSTRAINT `fk_AssetTaskService_Asset1` FOREIGN KEY (`idAsset`) REFERENCES `Asset` (`idAsset`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AssetTaskService_ServiceTask1` FOREIGN KEY (`idServiceTask`) REFERENCES `ServiceTask` (`idServiceTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssetTaskService`
--

LOCK TABLES `AssetTaskService` WRITE;
/*!40000 ALTER TABLE `AssetTaskService`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `AssetTaskService`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AssetType`
--

DROP TABLE IF EXISTS `AssetType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AssetType` (
  `idAssetType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45)               DEFAULT NULL,
  `description` VARCHAR(255)              DEFAULT NULL,
  `deleted`     INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idAssetType`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AssetType`
--

LOCK TABLES `AssetType` WRITE;
/*!40000 ALTER TABLE `AssetType`
  DISABLE KEYS */;
INSERT INTO `AssetType` VALUES (1, 'lavado', NULL, 0), (2, 'planchado', NULL, 0), (3, 'transporte', NULL, 0);
/*!40000 ALTER TABLE `AssetType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BagType`
--

DROP TABLE IF EXISTS `BagType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BagType` (
  `idBagType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`      VARCHAR(45)               DEFAULT NULL,
  `size`      INT(11)                   DEFAULT NULL,
  PRIMARY KEY (`idBagType`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BagType`
--

LOCK TABLES `BagType` WRITE;
/*!40000 ALTER TABLE `BagType`
  DISABLE KEYS */;
INSERT INTO `BagType` VALUES (1, 'Chica', 5), (2, 'Grande', 10);
/*!40000 ALTER TABLE `BagType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CashOut`
--

DROP TABLE IF EXISTS `CashOut`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CashOut` (
  `idCashOut` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `created`   DATETIME                  DEFAULT NULL,
  `user`      INT(11)                   DEFAULT NULL,
  `subtotal`  DOUBLE                    DEFAULT '0',
  `pending`   DOUBLE                    DEFAULT '0',
  `discount`  DOUBLE                    DEFAULT '0',
  `deleted`   INT(11)                   DEFAULT '0',
  `total`     DOUBLE                    DEFAULT '0',
  PRIMARY KEY (`idCashOut`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CashOut`
--

LOCK TABLES `CashOut` WRITE;
/*!40000 ALTER TABLE `CashOut`
  DISABLE KEYS */;
INSERT INTO `CashOut`
VALUES (1, '2017-06-10 17:33:45', 1, 123, 123, 0, 0, 0), (2, '2017-06-10 17:34:38', 1, 123, 123, 0, 0, 0),
  (3, '2017-06-10 17:36:46', 1, 123, 123, 0, 0, 0), (4, '2017-06-10 17:41:07', 1, 123, 123, 0, 0, 0),
  (5, '2017-06-10 17:41:48', 1, 123, 123, 0, 0, 0), (6, '2017-06-10 17:45:05', 1, 123, 123, 0, 0, 0),
  (7, '2017-06-10 18:43:34', 1, 123, 123, 0, 0, 0), (8, '2017-06-10 18:44:51', 1, 123, 123, 0, 0, 0),
  (9, '2017-06-11 11:26:18', 1, 123, 123, 0, 0, 0), (10, '2017-06-11 11:59:15', 1, 123, 123, 0, 0, 0),
  (11, '2017-06-11 15:10:14', 1, 123, 123, 0, 0, 0), (12, '2017-06-12 19:47:47', 1, 88, 0, 0, 0, 88),
  (13, '2017-06-19 12:57:21', 1, 264, 0, 0, 0, 264), (14, '2017-06-19 13:26:38', 1, 563, 299, 0, 0, 563);
/*!40000 ALTER TABLE `CashOut`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8 */;
/*!50003 SET character_set_results = utf8 */;
/*!50003 SET collation_connection = utf8_general_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER = CURRENT_USER */ /*!50003 TRIGGER `sod_db`.`date`
BEFORE INSERT ON `sod_db`.`CashOut`
FOR EACH ROW
  BEGIN
    SET NEW.created = NOW();
  END */;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;

--
-- Table structure for table `ClientBags`
--

DROP TABLE IF EXISTS `ClientBags`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClientBags` (
  `idClientBags` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `number`       VARCHAR(45)               DEFAULT NULL,
  `inOrder`      TINYINT(1)                DEFAULT NULL,
  `idBagType`    INT(10) UNSIGNED NOT NULL,
  `idClient`     INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idClientBags`),
  KEY `fk_ClientBags_BagSize1_idx` (`idBagType`),
  KEY `fk_ClientBags_Clients1_idx` (`idClient`),
  CONSTRAINT `fk_ClientBags_BagSize1` FOREIGN KEY (`idBagType`) REFERENCES `BagType` (`idBagType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ClientBags_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientBags`
--

LOCK TABLES `ClientBags` WRITE;
/*!40000 ALTER TABLE `ClientBags`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `ClientBags`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClientPaymentInfo`
--

DROP TABLE IF EXISTS `ClientPaymentInfo`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClientPaymentInfo` (
  `idClientPaymentInfo` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type`                INT(11)                   DEFAULT '0'
  COMMENT 'CASH = 0; \nCC = 1; // credit card \nPAYPAL = 2;  \nOTHER = 4; // NOT DEFINED \nSTRIPE = 3; ',
  `token`               VARCHAR(250)              DEFAULT NULL,
  `idClient`            INT(10) UNSIGNED NOT NULL,
  `prefered`            TINYINT(1)                DEFAULT '0',
  PRIMARY KEY (`idClientPaymentInfo`),
  KEY `fk_ClientPaymentInfo_Clients1_idx` (`idClient`),
  CONSTRAINT `fk_ClientPaymentInfo_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientPaymentInfo`
--

LOCK TABLES `ClientPaymentInfo` WRITE;
/*!40000 ALTER TABLE `ClientPaymentInfo`
  DISABLE KEYS */;
INSERT INTO `ClientPaymentInfo` VALUES (1, 1, '01234', 1, 1);
/*!40000 ALTER TABLE `ClientPaymentInfo`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ClientType`
--

DROP TABLE IF EXISTS `ClientType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ClientType` (
  `idClientType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(45)               DEFAULT NULL,
  `description`  VARCHAR(45)               DEFAULT NULL,
  PRIMARY KEY (`idClientType`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ClientType`
--

LOCK TABLES `ClientType` WRITE;
/*!40000 ALTER TABLE `ClientType`
  DISABLE KEYS */;
INSERT INTO `ClientType` VALUES (1, 'Normal', 'Clientes normal'), (2, 'Mayoreo', 'Clientes de Mayoreo'),
  (3, 'Cliente Regular 1', 'Clientes Regular 1'), (4, 'Cliente Regular 2', 'Clientes Regular 2');
/*!40000 ALTER TABLE `ClientType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Clients`
--

DROP TABLE IF EXISTS `Clients`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Clients` (
  `idClient`     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClientType` INT(10) UNSIGNED NOT NULL,
  `email`        VARCHAR(100)              DEFAULT NULL,
  `password`     CHAR(128)                 DEFAULT NULL,
  `name`         VARCHAR(250)              DEFAULT NULL,
  `lastName`     VARCHAR(250)              DEFAULT NULL,
  `twitter`      VARCHAR(250)              DEFAULT NULL,
  `created`      DATETIME                  DEFAULT NULL,
  `updated`      DATETIME                  DEFAULT NULL,
  `loginID`      VARCHAR(50)               DEFAULT NULL,
  `rfc`          VARCHAR(45)               DEFAULT NULL,
  `razonSocial`  VARCHAR(250)              DEFAULT NULL,
  `deleted`      INT(11)                   DEFAULT '0',
  `mobilePhone`  VARCHAR(45)               DEFAULT NULL,
  `homePhone`    VARCHAR(45)               DEFAULT NULL,
  `otherPhone`   VARCHAR(45)               DEFAULT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_Clients_ClientCategory1_idx` (`idClientType`),
  CONSTRAINT `fk_Clients_ClientCategory1` FOREIGN KEY (`idClientType`) REFERENCES `ClientType` (`idClientType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 299
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Clients`
--

LOCK TABLES `Clients` WRITE;
/*!40000 ALTER TABLE `Clients`
  DISABLE KEYS */;
INSERT INTO `Clients` VALUES
  (1, 1, 'email@domain.com', 'notused', 'Cliente', 'Apellido', 'twitter', '2017-02-16 15:18:15', '2017-02-16 09:19:07',
      '123', NULL, NULL, 0, '1111222333', NULL, NULL),
  (2, 1, NULL, NULL, 'Nombre', '', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, 'Telefono', NULL, NULL),
  (3, 1, NULL, NULL, 'Aida', 'Ruiz', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '3313612762', NULL, NULL),
  (4, 1, NULL, NULL, 'Aide', 'Elena', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '36313494', NULL, NULL),
  (5, 1, NULL, NULL, 'Anahi', '', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (6, 1, NULL, NULL, 'Angel', 'Gutierrez', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '31204063', NULL, NULL),
  (7, 1, NULL, NULL, 'Angelica', 'huerta', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, ' del pinar 3447\"', NULL, NULL),
  (8, 1, NULL, NULL, 'Antonio', 'topete', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '36320337', NULL, NULL),
  (9, 1, NULL, NULL, 'Arnulfo', '', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '3312859808', NULL, NULL),
  (10, 1, NULL, NULL, 'Avig', '', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '3312227469', NULL, NULL),
  (11, 1, NULL, NULL, 'Barben', 'shop', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '3317628487', NULL, NULL),
  (12, 1, NULL, NULL, 'Benjamin', '', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '3418863925', NULL, NULL),
  (13, 1, NULL, NULL, 'Benjamin', 'Bravo', NULL, '2017-02-24 18:17:30', NULL, NULL, NULL, NULL, 0, '31884038', NULL, NULL),
  (14, 1, NULL, NULL, 'Berenice', 'Ramirez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3312482014', NULL, NULL),
  (15, 1, NULL, NULL, 'Carlos', 'Huerta', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3631101', NULL, NULL),
  (16, 1, NULL, NULL, 'Carmen', 'Chavez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36323262', NULL, NULL),
  (17, 1, NULL, NULL, 'Daniela', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3331159620', NULL, NULL),
  (18, 1, NULL, NULL, 'Delia', 'Nuno', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '19840612', NULL, NULL),
  (19, 1, NULL, NULL, 'Dulce', 'Maria', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3338443360', NULL, NULL),
  (20, 1, NULL, NULL, 'Elsa', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3312160856', NULL, NULL),
  (21, 1, NULL, NULL, 'Elvia', 'Limon', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36312992', NULL, NULL),
  (22, 1, NULL, NULL, 'Emilio', 'perez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3312310818', NULL, NULL),
  (23, 1, NULL, NULL, 'Estela', 'Caballero', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3317193667', NULL, NULL),
  (24, 1, NULL, NULL, 'Fernando', 'Avalos', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '35636380', NULL, NULL),
  (25, 1, NULL, NULL, 'Gabi', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '311101020', NULL, NULL),
  (26, 1, NULL, NULL, 'Hector', 'Rivera', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3313118811', NULL, NULL),
  (27, 1, NULL, NULL, 'Silva', 'Ep94', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '19834905', NULL, NULL),
  (28, 1, NULL, NULL, 'Laura', 'Navarete', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3315028487', NULL, NULL),
  (29, 1, NULL, NULL, 'Laura', 'Pulido', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36341686', NULL, NULL),
  (30, 1, NULL, NULL, 'Leticia', 'Sanchez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3331966018', NULL, NULL),
  (31, 1, NULL, NULL, 'leticia', 'gonzalez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36320419', NULL, NULL),
  (32, 1, NULL, NULL, 'Lizbeth', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3335075904', NULL, NULL),
  (33, 1, NULL, NULL, 'Lourdes', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36468333', NULL, NULL),
  (34, 1, NULL, NULL, 'Lourdes', 'Mora', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '331089347', NULL, NULL),
  (35, 1, NULL, NULL, 'Lupita', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36328675', NULL, NULL),
  (36, 1, NULL, NULL, 'ManuelChavez', 'Perez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3315293032', NULL, NULL),
  (37, 1, NULL, NULL, 'Margarita', 'Gonzalez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3311949300', NULL, NULL),
  (38, 1, NULL, NULL, 'Maria', 'Elena', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36323236', NULL, NULL),
  (39, 1, NULL, NULL, 'Maria', 'Guadalupe', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3331047686', NULL, NULL),
  (40, 1, NULL, NULL, 'Maria', 'Elena', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36315665', NULL, NULL),
  (41, 1, NULL, NULL, 'Martha', 'Chavez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3311101020', NULL, NULL),
  (42, 1, NULL, NULL, 'Nicolas', 'Bonneal', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36319792', NULL, NULL),
  (43, 1, NULL, NULL, 'Pablo', 'Lopez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3315115680', NULL, NULL),
  (44, 1, NULL, NULL, 'Pedro', 'Martinez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36311024', NULL, NULL),
  (45, 1, NULL, NULL, 'Rita', 'Arreola', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36320406', NULL, NULL),
  (46, 1, NULL, NULL, 'Roberto', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3315282254', NULL, NULL),
  (47, 1, NULL, NULL, 'Rocio', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3310655042', NULL, NULL),
  (48, 1, NULL, NULL, 'Rocio', 'Guerrero', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '2066870329', NULL, NULL),
  (49, 1, NULL, NULL, 'Rodolfo', 'Gallegos', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '13805356', NULL, NULL),
  (50, 1, NULL, NULL, 'Rosa', 'Hernandez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '20143088', NULL, NULL),
  (51, 1, NULL, NULL, 'Rosario', 'Jaramillo', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36341137', NULL, NULL),
  (52, 1, NULL, NULL, 'Sandra', 'Mejia', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (53, 1, NULL, NULL, 'Teresa', 'Quezada', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, ' del Pinar 1822\"', NULL, NULL),
  (54, 1, NULL, NULL, 'Vania', 'Barrera', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3334812587', NULL, NULL),
  (55, 1, NULL, NULL, 'Ronal', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3321735971', NULL, NULL),
  (56, 1, NULL, NULL, 'Rosario', 'Garibay', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36318931', NULL, NULL),
  (57, 1, NULL, NULL, 'maricela', 'Placencia', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36813770', NULL, NULL),
  (58, 1, NULL, NULL, 'Claudia', 'ramirez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3322265741', NULL, NULL),
  (59, 1, NULL, NULL, 'Martha', 'Santana', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '31331308', NULL, NULL),
  (60, 1, NULL, NULL, 'cecilio', 'lechuga', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (61, 1, NULL, NULL, 'MARIA', 'DEL', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36321706', NULL, NULL),
  (62, 1, NULL, NULL, 'Estela', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, ' Paseos del Sol\"', NULL, NULL),
  (63, 1, NULL, NULL, 'Carmen', 'murillo', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36325200', NULL, NULL),
  (64, 1, NULL, NULL, 'maria', 'teresa', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '15811773', NULL, NULL),
  (65, 1, NULL, NULL, 'Lic', 'Lorena', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '31351092', NULL, NULL),
  (66, 1, NULL, NULL, 'Abel', 'castro', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3318079364', NULL, NULL),
  (67, 1, NULL, NULL, 'Jorge', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3318931865', NULL, NULL),
  (68, 1, NULL, NULL, 'herandy', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3318957534', NULL, NULL),
  (69, 1, NULL, NULL, 'robert', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (70, 1, NULL, NULL, 'Elvia', 'Limon', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36312992', NULL, NULL),
  (71, 1, NULL, NULL, 'martha', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36322275', NULL, NULL),
  (72, 1, NULL, NULL, 'Silvia', 'Flores', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3313264077', NULL, NULL),
  (73, 1, NULL, NULL, 'silvia', 'alyve', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '96271672', NULL, NULL),
  (74, 1, NULL, NULL, 'Rodolfo', 'Rodriguez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3312705060', NULL, NULL),
  (75, 1, NULL, NULL, 'Adriana', 'Gallardo', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3339016115', NULL, NULL),
  (76, 1, NULL, NULL, 'Alejandra', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, ' entre duque de Rivas y av. Vallarta\"', NULL, NULL),
  (77, 1, NULL, NULL, 'Alejandra', 'Cardona', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3312705060', NULL, NULL),
  (78, 1, NULL, NULL, 'Rosalba', 'Camarena', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36312813', NULL, NULL),
  (79, 1, NULL, NULL, 'Angelica', 'Chacht', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '4421240242', NULL, NULL),
  (80, 1, NULL, NULL, 'Jose', 'Luis', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (81, 1, 'temporal1', NULL, 'Fernando', 'Flores', NULL, '2017-02-24 18:17:31', '2017-02-25 17:36:57', NULL, NULL, NULL, 0, '8182063145', NULL, NULL),
  (82, 1, NULL, NULL, 'Norma', 'medina', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36312722', NULL, NULL),
  (83, 1, NULL, NULL, 'Jose', 'Morales', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3311932000', NULL, NULL),
  (84, 1, NULL, NULL, 'mari', 'paz', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36312741', NULL, NULL),
  (85, 1, NULL, NULL, 'Gladis', 'palafox', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3314799837', NULL, NULL),
  (86, 1, NULL, NULL, 'Lupita', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '39446921', NULL, NULL),
  (87, 1, NULL, NULL, 'Maria', 'Guadalupe', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '39446921', NULL, NULL),
  (88, 1, NULL, NULL, 'Erick', 'Gonzalez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '7222478789', NULL, NULL),
  (89, 1, NULL, NULL, 'Yolanda', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, ' entre cuitzmala y santiago\"', NULL, NULL),
  (90, 1, NULL, NULL, 'Dolores', 'Gomez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, ' int 12', NULL, NULL),
  (91, 1, NULL, NULL, 'Elizabeth', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3319560199', NULL, NULL),
  (92, 1, NULL, NULL, 'Carlos', 'cossio', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, ' paseos del sol\"', NULL, NULL),
  (93, 1, NULL, NULL, 'Elsa', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3312160856', NULL, NULL),
  (94, 1, NULL, NULL, 'Sravan', 'Varanasi', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3339646504', NULL, NULL),
  (95, 1, NULL, NULL, 'Ana', 'Gonzalez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '15813543', NULL, NULL),
  (96, 1, NULL, NULL, 'Joel', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3314380020', NULL, NULL),
  (97, 1, NULL, NULL, 'Joe', 'Gutierrez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3315548493', NULL, NULL),
  (98, 1, NULL, NULL, 'Martha', 'Chavez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3311101020', NULL, NULL),
  (99, 1, NULL, NULL, 'Marco', 'Zanudo', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '19840612', NULL, NULL),
  (100, 1, NULL, NULL, 'Blanca', 'Pelayo', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (101, 1, NULL, NULL, 'Ana', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '24007030', NULL, NULL),
  (102, 1, NULL, NULL, 'Maria', 'asuncion', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36312262', NULL, NULL),
  (103, 1, NULL, NULL, 'Jorge', 'Alvirde', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36323236', NULL, NULL),
  (104, 1, NULL, NULL, 'Noe', 'Aquino', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36312144', NULL, NULL),
  (105, 1, NULL, NULL, 'Cecilia', 'Becerra', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3310976924', NULL, NULL),
  (106, 1, NULL, NULL, 'coisa', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36325121', NULL, NULL),
  (107, 1, NULL, NULL, 'martha', 'gallegos', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36311790', NULL, NULL),
  (108, 1, NULL, NULL, 'carolina', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, ' Gavilanes\"', NULL, NULL),
  (109, 1, NULL, NULL, 'MARIA', 'ELENA', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (110, 1, NULL, NULL, 'catalina', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36340007', NULL, NULL),
  (111, 1, NULL, NULL, 'Miguel', 'Pantoja', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36314818', NULL, NULL),
  (112, 1, NULL, NULL, 'mariana', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3331494855', NULL, NULL),
  (113, 1, NULL, NULL, 'rosa', 'hernandez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '20143088', NULL, NULL),
  (114, 1, NULL, NULL, 'Rosa', 'maria', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '31331209', NULL, NULL),
  (115, 1, NULL, NULL, 'Pamela', 'Paz', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3317434183', NULL, NULL),
  (116, 1, NULL, NULL, 'Bety', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3310750525', NULL, NULL),
  (117, 1, NULL, NULL, 'Julieta', 'Sanchez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3314860833', NULL, NULL),
  (118, 1, NULL, NULL, 'marco', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '2293313394', NULL, NULL),
  (119, 1, NULL, NULL, 'Ivan', 'Lopez', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3315992618', NULL, NULL),
  (120, 1, NULL, NULL, 'Alvaro', 'Chimal', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36312226', NULL, NULL),
  (121, 1, NULL, NULL, 'Lili', 'Alvarado', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '4521010515', NULL, NULL),
  (122, 1, NULL, NULL, 'paola', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '4371034642', NULL, NULL),
  (123, 1, NULL, NULL, 'Lupita', 'Barragan', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (124, 1, NULL, NULL, 'Javier', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3322209516', NULL, NULL),
  (125, 1, NULL, NULL, 'Sergio', 'Quintero', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3322612839', NULL, NULL),
  (126, 1, NULL, NULL, 'cecilia', 'wuilian', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3334400938', NULL, NULL),
  (127, 1, NULL, NULL, 'Eduardo', 'Alain', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3921088569', NULL, NULL),
  (128, 1, NULL, NULL, 'Angel', '', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '36317003', NULL, NULL),
  (129, 1, NULL, NULL, 'Rosario', 'Giles', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '3315111331', NULL, NULL),
  (130, 1, NULL, NULL, 'Luz', 'Maria', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '6441346257', NULL, NULL),
  (131, 1, NULL, NULL, 'pablo', 'Arrieta', NULL, '2017-02-24 18:17:31', NULL, NULL, NULL, NULL, 0, '11178137', NULL, NULL),
  (132, 1, NULL, NULL, 'guillermina', 'rosales', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '15811016', NULL, NULL),
  (133, 1, NULL, NULL, 'Raj', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '33221927', NULL, NULL),
  (134, 1, NULL, NULL, 'Vianey', 'Navarrete', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3331662530', NULL, NULL),
  (135, 1, NULL, NULL, 'carolina', 'aguilar', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3335963470', NULL, NULL),
  (136, 1, NULL, NULL, 'Axel', 'Nuniez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '5531194748', NULL, NULL),
  (137, 1, NULL, NULL, 'Elizabeth', 'Rios', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3334530414', NULL, NULL),
  (138, 1, NULL, NULL, 'ana', 'valles', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '31350022', NULL, NULL),
  (139, 1, NULL, NULL, 'rafael', 'roman', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '39446923', NULL, NULL),
  (140, 1, NULL, NULL, 'Coni', 'Sanchez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, ' del pinar 3253\"', NULL, NULL),
  (141, 1, NULL, NULL, 'Guillermo', 'Vecino', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (142, 1, NULL, NULL, 'alfonso', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '5585508289', NULL, NULL),
  (143, 1, NULL, NULL, 'jorge', 'Rodriguez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3335762203', NULL, NULL),
  (144, 1, NULL, NULL, 'Hugo', 'Quirarte', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3331566757', NULL, NULL),
  (145, 1, NULL, NULL, 'Rosario', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36314496', NULL, NULL),
  (146, 1, NULL, NULL, 'Maria', 'Luisa', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36346799', NULL, NULL),
  (147, 1, NULL, NULL, 'Ana', 'Aceves', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3310954693', NULL, NULL),
  (148, 1, NULL, NULL, 'victor', 'montanez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3314413919', NULL, NULL),
  (149, 1, NULL, NULL, 'roberto', 'chacon', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3314359420', NULL, NULL),
  (150, 1, NULL, NULL, 'Samuel', 'Leyva', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '16666926', NULL, NULL),
  (151, 1, NULL, NULL, 'Susana', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36327658', NULL, NULL),
  (152, 1, NULL, NULL, 'Lourdes', 'Lechuga', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (153, 1, NULL, NULL, 'jorge', 'luis', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36323262', NULL, NULL),
  (154, 1, NULL, NULL, 'Cecilia', 'a', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3317740706', NULL, NULL),
  (155, 1, NULL, NULL, 'Diego', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, ' Alamo Industrial\"', NULL, NULL),
  (156, 1, NULL, NULL, 'charly', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3321061418', NULL, NULL),
  (157, 1, NULL, NULL, 'Rodney', 'Anthony', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3312120018', NULL, NULL),
  (158, 1, NULL, NULL, 'guillermo', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3313111016', NULL, NULL),
  (159, 1, NULL, NULL, 'Alejandra', 'Perez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3317814240', NULL, NULL),
  (160, 1, NULL, NULL, 'Sara', 'Sabido', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3312416956', NULL, NULL),
  (161, 1, NULL, NULL, 'B-Box', 'Securyti', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3322613050', NULL, NULL),
  (162, 1, NULL, NULL, 'Maribel', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3313183601', NULL, NULL),
  (163, 1, NULL, NULL, 'Nidia', 'Chavez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3331379811', NULL, NULL),
  (164, 1, NULL, NULL, 'SECURITY', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36155301', NULL, NULL),
  (165, 1, NULL, NULL, 'juan', 'carlos', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3310384254', NULL, NULL),
  (166, 1, NULL, NULL, 'Brenda', 'Salas', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3311083137', NULL, NULL),
  (167, 1, NULL, NULL, 'Pinal', 'y', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '31336090', NULL, NULL),
  (168, 1, NULL, NULL, 'Asociacion', 'del', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '(33)31-35-10-92', NULL, NULL),
  (169, 1, NULL, NULL, 'Isela', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '6242247499', NULL, NULL),
  (170, 1, NULL, NULL, 'Diana', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '6242247499', NULL, NULL),
  (171, 1, NULL, NULL, 'Samuel', 'Nava', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3221175698', NULL, NULL),
  (172, 1, NULL, NULL, 'Martha', 'Gonzalez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36310556', NULL, NULL),
  (173, 1, NULL, NULL, 'Gloria', 'Villasenor', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, ' entre diamante y barlovento\"', NULL, NULL),
  (174, 1, NULL, NULL, 'Cristina', 'Nuño', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36322915', NULL, NULL),
  (175, 1, NULL, NULL, 'Pinal', 'y', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (176, 1, NULL, NULL, 'qute', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '31336090', NULL, NULL),
  (177, 1, NULL, NULL, 'Carola', 'Madero', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '6441267979', NULL, NULL),
  (178, 1, NULL, NULL, 'Concepcion', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3334975640', NULL, NULL),
  (179, 1, NULL, NULL, 'Cluadia', 'Ramirez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36312226', NULL, NULL),
  (180, 1, NULL, NULL, 'Emmanuel', 'Ramirez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3411620055', NULL, NULL),
  (181, 1, NULL, NULL, 'Hilda', 'Guzman', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36319301', NULL, NULL),
  (182, 1, NULL, NULL, 'Eduardo', 'Dominguez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3921088569', NULL, NULL),
  (183, 1, NULL, NULL, 'Cristina', 'Becerra', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '31335170', NULL, NULL),
  (184, 1, NULL, NULL, 'Walter', 'Pinal', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '8117184635', NULL, NULL),
  (185, 1, NULL, NULL, 'cesar', 'Rivera', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3336625357', NULL, NULL),
  (186, 1, NULL, NULL, 'Leo', 'Preciado', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36311011', NULL, NULL),
  (187, 1, NULL, NULL, 'ivan', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3334685288', NULL, NULL),
  (188, 1, NULL, NULL, 'Carlos', 'Ruiz', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '8331409194', NULL, NULL),
  (189, 1, NULL, NULL, 'elia', 'contreras', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36311719', NULL, NULL),
  (190, 1, NULL, NULL, 'nancy', 'arias', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3310201592', NULL, NULL),
  (191, 1, NULL, NULL, 'Angeles', 'Vazquez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '33446862', NULL, NULL),
  (192, 1, NULL, NULL, 'Lorena', 'vazquez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3315195865', NULL, NULL),
  (193, 1, NULL, NULL, 'Rauf', 'Hamden', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '15811773', NULL, NULL),
  (194, 1, NULL, NULL, 'Alejandro', 'Aguilera', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3311937312', NULL, NULL),
  (195, 1, NULL, NULL, 'enrique', 'moncayo', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '31338415', NULL, NULL),
  (196, 1, NULL, NULL, 'juan', 'moreno', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36322992', NULL, NULL),
  (197, 1, NULL, NULL, 'yolanda', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '15420620', NULL, NULL),
  (198, 1, NULL, NULL, 'Isabela', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3318014067', NULL, NULL),
  (199, 1, NULL, NULL, 'Sandra', 'Moncayo', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (200, 1, NULL, NULL, 'sonia', 'sandobal', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36314532', NULL, NULL),
  (201, 1, NULL, NULL, 'margarita', 'vega', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '23020824', NULL, NULL),
  (202, 1, NULL, NULL, 'lizeth', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3338095321', NULL, NULL),
  (203, 1, NULL, NULL, 'luceli', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (204, 1, NULL, NULL, 'manish', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3316310237', NULL, NULL),
  (205, 1, NULL, NULL, 'alfredo', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3312691510', NULL, NULL),
  (206, 1, NULL, NULL, 'Supercito', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (207, 1, NULL, NULL, 'gabriela', 'tejeda', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3311937203', NULL, NULL),
  (208, 1, NULL, NULL, 'eusebio', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3339558631', NULL, NULL),
  (209, 1, NULL, NULL, 'Silvia', 'Rosales', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '18118566', NULL, NULL),
  (210, 1, NULL, NULL, 'Olga', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (211, 1, NULL, NULL, 'Alejandra', 'Arrieta', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3318208235', NULL, NULL),
  (212, 1, NULL, NULL, 'Laura', 'Gomez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3338094645', NULL, NULL),
  (213, 1, NULL, NULL, 'Aldo', 'Caltelum', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, ' tajin\"', NULL, NULL),
  (214, 1, NULL, NULL, 'Uma', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (215, 1, NULL, NULL, 'Gonzalo', 'Diaz', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (216, 1, NULL, NULL, 'Berny', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36318116', NULL, NULL),
  (217, 1, NULL, NULL, 'Eduardo', 'perez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3313598761', NULL, NULL),
  (218, 1, NULL, NULL, 'Monica', 'Barrigo', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3333590091', NULL, NULL),
  (219, 1, NULL, NULL, 'Karla', 'Flores', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36322992', NULL, NULL),
  (220, 1, NULL, NULL, 'Irlanda', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3310204453', NULL, NULL),
  (221, 1, NULL, NULL, 'mario', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36323161', NULL, NULL),
  (222, 1, NULL, NULL, 'Jorge', 'Rodriguez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3335762203', NULL, NULL),
  (223, 1, NULL, NULL, 'Alberto', 'Preciado', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36311011', NULL, NULL),
  (224, 1, NULL, NULL, 'Jordi', 'Acosto', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '33 2134 7127', NULL, NULL),
  (225, 1, NULL, NULL, 'julio', 'silva', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3332012719', NULL, NULL),
  (226, 1, NULL, NULL, 'Adriana', 'Rodriguez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36322790', NULL, NULL),
  (227, 1, NULL, NULL, 'claudia', 'Parra', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36312226', NULL, NULL),
  (228, 1, NULL, NULL, 'felipe', 'romero', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '5539272921', NULL, NULL),
  (229, 1, NULL, NULL, 'Senora', 'Villa', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36311830', NULL, NULL),
  (230, 1, NULL, NULL, 'susana', 'palacios', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3313994878', NULL, NULL),
  (231, 1, NULL, NULL, 'Ismael', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3310802839', NULL, NULL),
  (232, 1, NULL, NULL, 'Juan', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36328085', NULL, NULL),
  (233, 1, NULL, NULL, 'manuel', 'verdugo', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3315479629', NULL, NULL),
  (234, 1, NULL, NULL, 'juan', 'iglesias', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3334861684', NULL, NULL),
  (235, 1, NULL, NULL, 'norma', 'adame', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3331063907', NULL, NULL),
  (236, 1, NULL, NULL, 'Pina', 'Navarro', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (237, 1, NULL, NULL, 'rodrigo', 'viramontes', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36311846', NULL, NULL),
  (238, 1, NULL, NULL, 'Buena', 'Mesa', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (239, 1, NULL, NULL, 'Ivan', 'mariscal', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '6441175280', NULL, NULL),
  (240, 1, NULL, NULL, 'paulina', 'alcala', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '4371031030', NULL, NULL),
  (241, 1, NULL, NULL, 'Rodolfo', 'Gonzalez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '6441010893', NULL, NULL),
  (242, 1, NULL, NULL, 'Maria', 'Macias', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3314242134', NULL, NULL),
  (243, 1, NULL, NULL, 'efren', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3321562516', NULL, NULL),
  (244, 1, NULL, NULL, 'Tomas', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (245, 1, NULL, NULL, 'Jaime', 'Rodriguez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36325121', NULL, NULL),
  (246, 1, NULL, NULL, 'Sandy', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, ' entre carnero y pedro simon laplace\"', NULL, NULL),
  (247, 1, NULL, NULL, 'lupita', 'ruvalcava', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3311080695', NULL, NULL),
  (248, 1, NULL, NULL, 'lore', 'buen', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36346446', NULL, NULL),
  (249, 1, NULL, NULL, 'habram', 'aguirre', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '6691370274', NULL, NULL),
  (250, 1, NULL, NULL, 'pablo', 'cameruta', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3331007611', NULL, NULL),
  (251, 1, NULL, NULL, 'Andrea', 'Escobar', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '4473949978', NULL, NULL),
  (252, 1, NULL, NULL, 'julio', 'cesar', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3310151562', NULL, NULL),
  (253, 1, NULL, NULL, 'Rosa', 'Maria', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36348461', NULL, NULL),
  (254, 1, NULL, NULL, 'Daniela', 'Gutierrez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (255, 1, NULL, NULL, 'Esteban', 'Barbosa', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36316561', NULL, NULL),
  (256, 1, NULL, NULL, 'sara', 'gomez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3322021521', NULL, NULL),
  (257, 1, NULL, NULL, 'anabel', 'mora', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3318898012', NULL, NULL),
  (258, 1, NULL, NULL, 'alma', 'delia', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (259, 1, NULL, NULL, 'luciano', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '8332389287', NULL, NULL),
  (260, 1, NULL, NULL, 'valeria', 'perez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3310384254', NULL, NULL),
  (261, 1, NULL, NULL, '\"Barbento', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, 'Cruz del sur 4508 \'B', NULL, NULL),
  (262, 1, NULL, NULL, 'Guadalupe', 'Fernandez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36311888', NULL, NULL),
  (263, 1, NULL, NULL, 'yamile', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3321160625', NULL, NULL),
  (264, 1, NULL, NULL, 'esthela', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3317444777', NULL, NULL),
  (265, 1, NULL, NULL, 'nicol', 'madera', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '6441389606', NULL, NULL),
  (266, 1, NULL, NULL, 'jesus', 'diaz', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3334538156', NULL, NULL),
  (267, 1, NULL, NULL, 'señor', 'alberto', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36311011', NULL, NULL),
  (268, 1, NULL, NULL, 'Jose', 'Rodriguez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (269, 1, NULL, NULL, 'Norma', 'lamadrid', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36327222', NULL, NULL),
  (270, 1, NULL, NULL, 'german', 'marquez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36312381', NULL, NULL),
  (271, 1, NULL, NULL, 'Samanta', 'Castelo', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3322627190', NULL, NULL),
  (272, 1, NULL, NULL, 'Nataly', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3335997760', NULL, NULL),
  (273, 1, NULL, NULL, 'Araceli', 'mercado', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, ' entre Onix y Turqueza\"', NULL, NULL),
  (274, 1, NULL, NULL, 'Anabel', 'Nolasco', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3338141561', NULL, NULL),
  (275, 1, NULL, NULL, 'gabriela', 'ruiz', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '4561124347', NULL, NULL),
  (276, 1, NULL, NULL, 'gonzalo', 'diaz', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36341337', NULL, NULL),
  (277, 1, NULL, NULL, 'maria', 'elena', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36310727', NULL, NULL),
  (278, 1, NULL, NULL, 'marisol', 'rivera', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3312912323', NULL, NULL),
  (279, 1, NULL, NULL, 'miguel', 'gonzalez', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (280, 1, NULL, NULL, 'emanuel', 'salgado', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3332006230', NULL, NULL),
  (281, 1, NULL, NULL, 'carlos', 'contreras', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3314142050', NULL, NULL),
  (282, 1, NULL, NULL, 'Gerardo', '', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (283, 1, NULL, NULL, 'Alicia', 'Espinoza', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '36327367', NULL, NULL),
  (284, 1, NULL, NULL, 'leopoldo', 'silva', NULL, '2017-02-24 18:17:32', NULL, NULL, NULL, NULL, 0, '3310627216', NULL, NULL),
  (285, 1, NULL, NULL, 'laura', 'placencia', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (286, 1, NULL, NULL, 'maria', 'teresa', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '15811773', NULL, NULL),
  (287, 1, NULL, NULL, 'Miguel', 'Angel', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '3318273896', NULL, NULL),
  (288, 1, NULL, NULL, 'Omar', '', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (289, 1, NULL, NULL, '', 'Maestra', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (290, 1, NULL, NULL, 'Diego', 'Rodriguez', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '3339562034', NULL, NULL),
  (291, 1, NULL, NULL, 'Paty', '', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '3314764177', NULL, NULL),
  (292, 1, NULL, NULL, 'Viswa', '', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '3321107046', NULL, NULL),
  (293, 1, NULL, NULL, 'Eduardo', 'Duran', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '3331050314', NULL,
   NULL),
  (294, 1, NULL, NULL, 'Manoj', '', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '3339500734', NULL, NULL),
  (295, 1, NULL, NULL, 'Sarai', '', NULL, '2017-02-24 18:17:33', NULL, NULL, NULL, NULL, 0, '', NULL, NULL),
  (296, 1, 'myaneli.ramirez@gmail.com', NULL, 'ALEJANDRO', 'CREDOMATIC', NULL, '2017-03-23 22:13:59', NULL, NULL, NULL,
   NULL, 0, '3312866315', '', NULL),
  (297, 2, '', NULL, 'ocho 16 spa', 'spa', NULL, '2017-03-23 22:27:20', NULL, NULL, NULL, NULL, 0, '3316003132', NULL,
   NULL),
  (298, 1, 'fernando.jarero@gmail.com', NULL, 'Fernando', 'Jarero', NULL, '2017-06-19 13:11:06', NULL, NULL, NULL, NULL,
   0, '333566565665', '54545454', NULL);
/*!40000 ALTER TABLE `Clients`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8 */;
/*!50003 SET character_set_results = utf8 */;
/*!50003 SET collation_connection = utf8_general_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER = CURRENT_USER */ /*!50003 TRIGGER `sod_db`.`Clients_BEFORE_INSERT`
BEFORE INSERT ON `sod_db`.`Clients`
FOR EACH ROW
  BEGIN
    SET NEW.created = NOW();
  END */;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8 */;
/*!50003 SET character_set_results = utf8 */;
/*!50003 SET collation_connection = utf8_general_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER = CURRENT_USER */ /*!50003 TRIGGER `sod_db`.`Clients_BEFORE_UPDATE`
BEFORE UPDATE ON `sod_db`.`Clients`
FOR EACH ROW
  BEGIN
    SET NEW.updated = NOW();
  END */;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;

--
-- Table structure for table `DistanceInfo`
--

DROP TABLE IF EXISTS `DistanceInfo`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DistanceInfo` (
  `idDistanceInfo` INT(11)          NOT NULL AUTO_INCREMENT,
  `distance`       INT(11)                   DEFAULT NULL,
  `price`          DOUBLE                    DEFAULT NULL,
  `source`         INT(11)                   DEFAULT '0'
  COMMENT '0 = local\n1 = empresa \n2 = ruta',
  `idStore`        INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idDistanceInfo`),
  KEY `fk_DistanceInfo_Stores1_idx` (`idStore`),
  CONSTRAINT `fk_DistanceInfo_Stores1` FOREIGN KEY (`idStore`) REFERENCES `Stores` (`idStore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DistanceInfo`
--

LOCK TABLES `DistanceInfo` WRITE;
/*!40000 ALTER TABLE `DistanceInfo`
  DISABLE KEYS */;
INSERT INTO `DistanceInfo` VALUES (1, 3, 0, 1, 1), (2, 6, 0, 1, 1), (3, 12, 0, 1, 1), (4, 24, 0, 1, 1);
/*!40000 ALTER TABLE `DistanceInfo`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `idEmployee`     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEmployeeType` INT(10) UNSIGNED NOT NULL,
  `status`         INT(11)          NOT NULL DEFAULT '0',
  `name`           VARCHAR(45)               DEFAULT NULL,
  `lastname`       VARCHAR(45)               DEFAULT NULL,
  `password`       LONGTEXT,
  `username`       VARCHAR(45)               DEFAULT NULL,
  `created`        DATETIME                  DEFAULT NULL,
  `updated`        DATETIME                  DEFAULT NULL,
  `email`          VARCHAR(100)              DEFAULT NULL,
  `deleted`        INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEmployee`),
  KEY `fk_Employee_EmployeeType1_idx` (`idEmployeeType`),
  CONSTRAINT `fk_Employee_EmployeeType1` FOREIGN KEY (`idEmployeeType`) REFERENCES `EmployeeType` (`idEmployeeType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee`
  DISABLE KEYS */;
INSERT INTO `Employee`
VALUES (1, 1, 1, 'user', 'user', NULL, 'user', '2016-12-30 13:09:05', NULL, 'email@domain.com', 0);
/*!40000 ALTER TABLE `Employee`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8 */;
/*!50003 SET character_set_results = utf8 */;
/*!50003 SET collation_connection = utf8_general_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER = CURRENT_USER */ /*!50003 TRIGGER `sod_db`.`Employee_BEFORE_INSERT`
BEFORE INSERT ON `sod_db`.`Employee`
FOR EACH ROW
  BEGIN
    SET NEW.created = NOW();
  END */;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8 */;
/*!50003 SET character_set_results = utf8 */;
/*!50003 SET collation_connection = utf8_general_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER = CURRENT_USER */ /*!50003 TRIGGER `sod_db`.`Employee_BEFORE_UPDATE`
BEFORE UPDATE ON `sod_db`.`Employee`
FOR EACH ROW
  BEGIN
    SET NEW.updated = NOW();
  END */;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;

--
-- Table structure for table `EmployeeTaskOrder`
--

DROP TABLE IF EXISTS `EmployeeTaskOrder`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmployeeTaskOrder` (
  `idEmployeeTaskOrder` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderTask`         INT(10) UNSIGNED NOT NULL,
  `idEmployee`          INT(10) UNSIGNED NOT NULL,
  `comments`            VARCHAR(250)              DEFAULT NULL,
  PRIMARY KEY (`idEmployeeTaskOrder`),
  KEY `fk_EmployeeTaskOrder_OrderTask1_idx` (`idOrderTask`),
  KEY `fk_EmployeeTaskOrder_Employee1_idx` (`idEmployee`),
  CONSTRAINT `fk_EmployeeTaskOrder_Employee1` FOREIGN KEY (`idEmployee`) REFERENCES `Employee` (`idEmployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EmployeeTaskOrder_OrderTask1` FOREIGN KEY (`idOrderTask`) REFERENCES `OrderTask` (`idOrderTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmployeeTaskOrder`
--

LOCK TABLES `EmployeeTaskOrder` WRITE;
/*!40000 ALTER TABLE `EmployeeTaskOrder`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `EmployeeTaskOrder`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmployeeTaskService`
--

DROP TABLE IF EXISTS `EmployeeTaskService`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmployeeTaskService` (
  `idEmployeeTaskService` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEmployee`            INT(10) UNSIGNED NOT NULL,
  `idServiceTask`         INT(10) UNSIGNED NOT NULL,
  `comments`              VARCHAR(250)              DEFAULT NULL,
  PRIMARY KEY (`idEmployeeTaskService`),
  KEY `fk_EmployeeTaskService_Employee1_idx` (`idEmployee`),
  KEY `fk_EmployeeTaskService_ServiceTask1_idx` (`idServiceTask`),
  CONSTRAINT `fk_EmployeeTaskService_Employee1` FOREIGN KEY (`idEmployee`) REFERENCES `Employee` (`idEmployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EmployeeTaskService_ServiceTask1` FOREIGN KEY (`idServiceTask`) REFERENCES `ServiceTask` (`idServiceTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmployeeTaskService`
--

LOCK TABLES `EmployeeTaskService` WRITE;
/*!40000 ALTER TABLE `EmployeeTaskService`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `EmployeeTaskService`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EmployeeType`
--

DROP TABLE IF EXISTS `EmployeeType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EmployeeType` (
  `idEmployeeType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`           VARCHAR(45)               DEFAULT NULL,
  `description`    VARCHAR(255)              DEFAULT NULL,
  `deleted`        INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEmployeeType`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EmployeeType`
--

LOCK TABLES `EmployeeType` WRITE;
/*!40000 ALTER TABLE `EmployeeType`
  DISABLE KEYS */;
INSERT INTO `EmployeeType` VALUES (1, 'admin', 'Administradores', 0), (2, 'general', 'Empleados Nivel 2', 0);
/*!40000 ALTER TABLE `EmployeeType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Menu`
--

DROP TABLE IF EXISTS `Menu`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Menu` (
  `idMenu`      INT(11) NOT NULL AUTO_INCREMENT,
  `state`       VARCHAR(45)      DEFAULT NULL,
  `name`        VARCHAR(45)      DEFAULT NULL,
  `accessLevel` INT(11)          DEFAULT '1'
  COMMENT '0 = none\n1 = admin \n2 = not so admin\n3 = less admin than 2\n',
  `order`       INT(11)          DEFAULT NULL,
  PRIMARY KEY (`idMenu`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Menu`
--

LOCK TABLES `Menu` WRITE;
/*!40000 ALTER TABLE `Menu`
  DISABLE KEYS */;
INSERT INTO `Menu`
VALUES (1, 'client.all', 'Clientes', 2, 1), (2, 'routes.all', 'Rutas', 0, 2), (3, 'tasks.taskMenu', 'Tareas', 1, 5),
  (4, 'specs.specMenu', 'Specs', 1, 6), (5, 'employees.employeeMenu', 'Empleados', 1, 4),
  (6, 'assets.assetMenu', 'Activos', 0, 3), (7, 'supplies.supplyMenu', 'Consumibles', 1, 7),
  (8, 'services.serviceMenu', 'Servicios', 1, 8), (9, 'orders.orderMenu', 'Orders', 1, 9),
  (10, 'products.productMenu', 'Productos', 1, 10);
/*!40000 ALTER TABLE `Menu`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderPriceAdjustment`
--

DROP TABLE IF EXISTS `OrderPriceAdjustment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderPriceAdjustment` (
  `idOrderPriceAdjustment` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrder`                INT(10) UNSIGNED NOT NULL,
  `idPriceAdjustment`      INT(10) UNSIGNED NOT NULL,
  `cantidad`               DOUBLE           NOT NULL DEFAULT '0',
  PRIMARY KEY (`idOrderPriceAdjustment`),
  KEY `fk_OrderPromotion_Orders1_idx` (`idOrder`),
  KEY `fk_OrderPromotion_Promotion1_idx` (`idPriceAdjustment`),
  CONSTRAINT `fk_OrderPromotion_Orders1` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderPromotion_Promotion1` FOREIGN KEY (`idPriceAdjustment`) REFERENCES `PriceAdjustment` (`idPriceAdjustment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderPriceAdjustment`
--

LOCK TABLES `OrderPriceAdjustment` WRITE;
/*!40000 ALTER TABLE `OrderPriceAdjustment`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderPriceAdjustment`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderTask`
--

DROP TABLE IF EXISTS `OrderTask`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderTask` (
  `idOrderTask`  INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrder`      INT(10) UNSIGNED NOT NULL,
  `idTask`       INT(10) UNSIGNED NOT NULL,
  `time`         INT(11)                   DEFAULT NULL,
  `comments`     VARCHAR(255)              DEFAULT NULL,
  `status`       INT(11)                   DEFAULT '0'
  COMMENT '0 = NEW\n1 = COMPLETED',
  `sortingOrder` INT(11)                   DEFAULT NULL,
  `started`      DATETIME                  DEFAULT NULL,
  `ended`        DATETIME                  DEFAULT NULL,
  PRIMARY KEY (`idOrderTask`),
  KEY `fk_OrderTask_Task1_idx` (`idTask`),
  KEY `fk_OrderTask_Order1_idx` (`idOrder`),
  CONSTRAINT `fk_OrderTask_Order1` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTask_Task1` FOREIGN KEY (`idTask`) REFERENCES `Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderTask`
--

LOCK TABLES `OrderTask` WRITE;
/*!40000 ALTER TABLE `OrderTask`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderTask`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderType`
--

DROP TABLE IF EXISTS `OrderType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderType` (
  `idOrderType`   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR(45)               DEFAULT NULL,
  `description`   VARCHAR(255)              DEFAULT NULL,
  `transportInfo` INT(11)                   DEFAULT '0'
  COMMENT '0 = none; \n1 = show pick up\n2 = show deliver \n3 = show both.',
  `deleted`       INT(11)                   DEFAULT '0',
  PRIMARY KEY (`idOrderType`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderType`
--

LOCK TABLES `OrderType` WRITE;
/*!40000 ALTER TABLE `OrderType`
  DISABLE KEYS */;
INSERT INTO `OrderType`
VALUES (1, 'Recoleccion + Entrega', 'Pickup + service + deliver', 3, 0), (2, 'Recoleccion', 'Pickup + service', 1, 0),
  (3, 'Entrega', 'Service + deliver', 2, 0), (4, 'Encargo', 'Service', 0, 0);
/*!40000 ALTER TABLE `OrderType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderTypeTask`
--

DROP TABLE IF EXISTS `OrderTypeTask`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrderTypeTask` (
  `idOrderTypeTask` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderType`     INT(10) UNSIGNED NOT NULL,
  `idTask`          INT(10) UNSIGNED NOT NULL,
  `sortingOrder`    INT(11)                   DEFAULT NULL,
  PRIMARY KEY (`idOrderTypeTask`),
  KEY `fk_OrderTemplateTasks_Task1_idx` (`idTask`),
  KEY `fk_OrderTemplateTasks_OrderTemplate1_idx` (`idOrderType`),
  CONSTRAINT `fk_OrderTemplateTasks_OrderTemplate1` FOREIGN KEY (`idOrderType`) REFERENCES `OrderType` (`idOrderType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTemplateTasks_Task1` FOREIGN KEY (`idTask`) REFERENCES `Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderTypeTask`
--

LOCK TABLES `OrderTypeTask` WRITE;
/*!40000 ALTER TABLE `OrderTypeTask`
  DISABLE KEYS */;
INSERT INTO `OrderTypeTask` VALUES (1, 1, 3, 1), (2, 1, 1, 2), (3, 1, 4, 3), (5, 2, 3, 0), (6, 4, 1, 0);
/*!40000 ALTER TABLE `OrderTypeTask`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `idOrder`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient`         INT(10) UNSIGNED NOT NULL,
  `idOrderType`      INT(10) UNSIGNED NOT NULL,
  `idAddressPickup`  INT(11)                   DEFAULT NULL
  COMMENT 'Not froreing key ',
  `pickUpDate`       DATETIME                  DEFAULT NULL,
  `pickUpPrice`      DOUBLE                    DEFAULT NULL,
  `idAddressDeliver` INT(11)                   DEFAULT NULL,
  `deliverDate`      DATETIME                  DEFAULT NULL,
  `deliverPrice`     DOUBLE                    DEFAULT NULL,
  `time`             INT(11)                   DEFAULT NULL,
  `status`           INT(11)                   DEFAULT '0'
  COMMENT '0 = active\n1 = finished',
  `comments`         VARCHAR(250)              DEFAULT NULL,
  `createdBy`        INT(11)                   DEFAULT '0',
  `created`          DATETIME                  DEFAULT NULL,
  `updated`          DATETIME                  DEFAULT NULL,
  `deleted`          INT(11)                   DEFAULT '0',
  `totalServices`    DOUBLE                    DEFAULT NULL,
  `total`            DOUBLE                    DEFAULT NULL,
  `discount`         DOUBLE                    DEFAULT NULL,
  `paymentStatus`    TINYINT(4)                DEFAULT '0'
  COMMENT 'flag to know if the order is already payed. ',
  `idCashOut`        INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idOrder`),
  KEY `fk_Order_OrderTemplate1_idx` (`idOrderType`),
  KEY `fk_Order_Clients1_idx` (`idClient`),
  CONSTRAINT `fk_Order_Clients1` FOREIGN KEY (`idClient`) REFERENCES `Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_OrderTemplate1` FOREIGN KEY (`idOrderType`) REFERENCES `OrderType` (`idOrderType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `Orders`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8 */;
/*!50003 SET character_set_results = utf8 */;
/*!50003 SET collation_connection = utf8_general_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER = CURRENT_USER */ /*!50003 TRIGGER `sod_db`.`Orders_BEFORE_INSERT`
BEFORE INSERT ON `sod_db`.`Orders`
FOR EACH ROW
  BEGIN
    SET NEW.created = NOW();
  END */;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8 */;
/*!50003 SET character_set_results = utf8 */;
/*!50003 SET collation_connection = utf8_general_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER = CURRENT_USER */ /*!50003 TRIGGER `sod_db`.`Orders_BEFORE_UPDATE`
BEFORE UPDATE ON `sod_db`.`Orders`
FOR EACH ROW
  BEGIN
    SET NEW.updated = NOW();
  END */;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;

--
-- Table structure for table `PaymentInfo`
--

DROP TABLE IF EXISTS `PaymentInfo`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PaymentInfo` (
  `idPaymentInfo`   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrder`         INT(10) UNSIGNED NOT NULL,
  `type`            INT(11)          NOT NULL
  COMMENT '0=cash\n1=stripe\n2=square\n',
  `transactionInfo` VARCHAR(250)              DEFAULT NULL,
  PRIMARY KEY (`idPaymentInfo`),
  KEY `fk_PaymentInfo_Orders1_idx` (`idOrder`),
  CONSTRAINT `fk_PaymentInfo_Orders1` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PaymentInfo`
--

LOCK TABLES `PaymentInfo` WRITE;
/*!40000 ALTER TABLE `PaymentInfo`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `PaymentInfo`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PriceAdjustment`
--

DROP TABLE IF EXISTS `PriceAdjustment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PriceAdjustment` (
  `idPriceAdjustment`     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idPriceAdjustmentType` INT(10) UNSIGNED NOT NULL,
  `name`                  VARCHAR(60)               DEFAULT NULL,
  `description`           VARCHAR(45)               DEFAULT NULL,
  `startDate`             DATETIME                  DEFAULT NULL,
  `endDate`               DATETIME                  DEFAULT NULL,
  `maxUses`               INT(11)                   DEFAULT '0'
  COMMENT 'max uses by order',
  `amount`                DOUBLE                    DEFAULT '0',
  `promoCode`             VARCHAR(45)               DEFAULT NULL,
  `orderLimit`            INT(11)                   DEFAULT '0',
  `dateLimit`             DATETIME                  DEFAULT NULL
  COMMENT 'limit date that the promotion will be enabled. ',
  `minimumAmount`         INT(11)                   DEFAULT '0',
  `discountType`          INT(11)                   DEFAULT '1'
  COMMENT '1 = $\n2 = %\n',
  `deleted`               INT(11)                   DEFAULT '0',
  PRIMARY KEY (`idPriceAdjustment`),
  KEY `fk_Promotion_PromotionType1_idx` (`idPriceAdjustmentType`),
  CONSTRAINT `fk_Promotion_PromotionType1` FOREIGN KEY (`idPriceAdjustmentType`) REFERENCES `PriceAdjustmentType` (`idPriceAdjustmentType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PriceAdjustment`
--

LOCK TABLES `PriceAdjustment` WRITE;
/*!40000 ALTER TABLE `PriceAdjustment`
  DISABLE KEYS */;
INSERT INTO `PriceAdjustment` VALUES
  (1, 1, 'Buen Fin', 'Promocion por el buen fin', '2016-01-01 00:00:00', '2017-01-01 00:00:00', 0, 10, NULL, 1, NULL, 0,
   2, 0), (2, 1, 'Compra de $120', 'Compra de $120', NULL, NULL, 0, 20, NULL, 1, NULL, 0, 1, 0),
  (3, 1, 'Compra No. #', 'Compra no #. ', NULL, NULL, 0, 60, NULL, 1, NULL, 0, 2, 0),
  (4, 1, 'Cliente Frecuente', 'Descuento por cliente frecuente', NULL, NULL, 0, 10, NULL, 1, NULL, 0, 1, 0),
  (5, 1, 'Pronto Pago', 'Descuento por pronto pago', NULL, NULL, 0, 8, NULL, 0, NULL, 0, 1, 0);
/*!40000 ALTER TABLE `PriceAdjustment`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PriceAdjustmentType`
--

DROP TABLE IF EXISTS `PriceAdjustmentType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PriceAdjustmentType` (
  `idPriceAdjustmentType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`                  VARCHAR(50)      NOT NULL,
  `description`           VARCHAR(255)              DEFAULT NULL,
  `deleted`               INT(11)                   DEFAULT '0',
  PRIMARY KEY (`idPriceAdjustmentType`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PriceAdjustmentType`
--

LOCK TABLES `PriceAdjustmentType` WRITE;
/*!40000 ALTER TABLE `PriceAdjustmentType`
  DISABLE KEYS */;
INSERT INTO `PriceAdjustmentType` VALUES (1, 'Descuento', 'Descuentos entre fechas', 0),
  (2, 'Cargo', 'Al agregar uno o alguna combinacio de producto o servicio', 0);
/*!40000 ALTER TABLE `PriceAdjustmentType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `idProduct`     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idProductType` INT(10) UNSIGNED NOT NULL,
  `name`          VARCHAR(45)      NOT NULL,
  `price`         DOUBLE           NOT NULL,
  `maxQty`        INT(11)          NOT NULL DEFAULT '0',
  `deleted`       INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idProduct`),
  KEY `fk_Subproduct_SubproductType1_idx` (`idProductType`),
  CONSTRAINT `fk_Subproduct_SubproductType1` FOREIGN KEY (`idProductType`) REFERENCES `ProductType` (`idProductType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product`
  DISABLE KEYS */;
INSERT INTO `Product`
VALUES (1, 1, 'Sabanas', 2.7, 0, 0), (2, 1, 'Toalla Chica', 0.9, 0, 0), (3, 1, 'Toalla Grande', 2.34, 0, 0),
  (4, 1, 'Toalla Mediana', 1.08, 0, 0), (5, 4, 'Kilogramo', 13.6, 0, 0), (6, 4, '1-5 Kg', 68, 0, 0),
  (7, 4, '5.1-10 Kg', 130, 0, 0), (8, 6, 'Por Lavadora', 50, 0, 0), (9, 6, 'Cobija', 65, 0, 0),
  (10, 6, 'Edredon', 65, 0, 0), (11, 7, 'Pantalon', 45, 0, 0), (12, 7, 'Traje', 88, 0, 0),
  (13, 4, 'Kilogramo Mayoreo', 13, 0, 0),
  (17, 4, 'Prenda', 7, 0, 0), (18, 6, 'Colcha', 55, 0, 0), (19, 1, 'Trapo', 0.9, 0, 0),
  (20, 1, 'Funda', 0.9, 0, 0), (21, 1, 'Bata Banio', 2.88, 0, 0), (22, 1, 'Cobertor', 9.54, 0, 0),
  (23, 1, 'Bandas', 0.9, 0, 0), (24, 1, 'Tapete', 2.7, 0, 0), (25, 1, 'Cubre Colchon', 2.7, 0, 0),
  (26, 1, 'Uniformes', 2.88, 0, 0), (27, 1, 'Capas', 0.9, 0, 0), (28, 1, 'Careta', 1.08, 0, 0),
  (29, 1, 'Vendas', 1.08, 0, 0), (30, 3, 'Toalla Mediana', 3, 0, 0), (31, 3, 'Capas', 2, 0, 0),
  (32, 3, 'Bandas', 1, 0, 0), (33, 3, 'Batas', 3.5, 0, 0), (34, 3, 'Mandiles', 3.5, 0, 0), (35, 5, 'Docena', 60, 0, 0),
  (36, 5, 'Media docena', 35, 0, 0), (37, 5, 'Pieza', 7, 0, 0);
/*!40000 ALTER TABLE `Product`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProductType`
--

DROP TABLE IF EXISTS `ProductType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProductType` (
  `idProductType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR(45)               DEFAULT NULL,
  `description`   VARCHAR(250)              DEFAULT NULL,
  `deleted`       INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idProductType`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProductType`
--

LOCK TABLES `ProductType` WRITE;
/*!40000 ALTER TABLE `ProductType`
  DISABLE KEYS */;
INSERT INTO `ProductType`
VALUES (1, 'Ocho16 Spa', 'Productos para el ocho16 spa', 0), (2, 'Esteticas', 'Varias Esteticas', 1),
  (3, 'Estetica', 'Estetica varias', 0), (4, 'Lavado', 'Productos de Lavado general', 0),
  (5, 'Planchado', 'Productos de Planchado general', 0), (6, 'Lavado de Blancos', 'Productos de lavado de blancos', 0),
  (7, 'Tintoreria', 'productos de tintoreria', 0);
/*!40000 ALTER TABLE `ProductType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Service`
--

DROP TABLE IF EXISTS `Service`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Service` (
  `idService`     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idServiceType` INT(10) UNSIGNED NOT NULL,
  `name`          VARCHAR(45)               DEFAULT NULL,
  `description`   VARCHAR(250)              DEFAULT NULL,
  `time`          INT(11)                   DEFAULT NULL,
  `created`       DATETIME                  DEFAULT NULL,
  `updated`       DATETIME                  DEFAULT NULL,
  `status`        INT(11)                   DEFAULT NULL,
  `idOrder`       INT(10) UNSIGNED NOT NULL,
  `currentTask`   INT(11)                   DEFAULT NULL,
  `deleted`       INT(11)                   DEFAULT '0',
  `price`         DOUBLE                    DEFAULT '0',
  `specsPrice`    DOUBLE                    DEFAULT '0',
  `productsPrice` DOUBLE                    DEFAULT '0',
  `totalPrice`    DOUBLE                    DEFAULT '0',
  PRIMARY KEY (`idService`),
  KEY `fk_Service_ServiceType1_idx` (`idServiceType`),
  KEY `fk_Service_Orders1_idx` (`idOrder`),
  CONSTRAINT `fk_Service_Orders1` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Service_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Service`
--

LOCK TABLES `Service` WRITE;
/*!40000 ALTER TABLE `Service`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `Service`
  ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8 */;
/*!50003 SET character_set_results = utf8 */;
/*!50003 SET collation_connection = utf8_general_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER = CURRENT_USER */ /*!50003 TRIGGER `sod_db`.`Service_BEFORE_INSERT`
BEFORE INSERT ON `sod_db`.`Service`
FOR EACH ROW
  BEGIN
    SET NEW.created = NOW();
  END */;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8 */;
/*!50003 SET character_set_results = utf8 */;
/*!50003 SET collation_connection = utf8_general_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ALLOW_INVALID_DATES,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
/*!50003 CREATE */ /*!50017 DEFINER = CURRENT_USER */ /*!50003 TRIGGER `sod_db`.`Service_BEFORE_UPDATE`
BEFORE UPDATE ON `sod_db`.`Service`
FOR EACH ROW
  BEGIN
    SET NEW.updated = NOW();
  END */;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;

--
-- Table structure for table `ServiceCategory`
--

DROP TABLE IF EXISTS `ServiceCategory`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceCategory` (
  `idServiceCategory` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`              VARCHAR(45)               DEFAULT NULL,
  `description`       VARCHAR(250)              DEFAULT NULL,
  PRIMARY KEY (`idServiceCategory`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceCategory`
--

LOCK TABLES `ServiceCategory` WRITE;
/*!40000 ALTER TABLE `ServiceCategory`
  DISABLE KEYS */;
INSERT INTO `ServiceCategory` VALUES (1, 'Lavado', 'Servicios de Lavado Clientes'), (2, 'Planchado', 'Planchado'),
  (3, 'Tintoreria', 'Tintoreria varios'), (4, 'Costura', 'Costura'),
  (5, 'Mayoreao Spa', 'Servicio de mayoreo para SPAs'), (6, 'Clientes Mayoreo', 'Clientes Mayoreo');
/*!40000 ALTER TABLE `ServiceCategory`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceComments`
--

DROP TABLE IF EXISTS `ServiceComments`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceComments` (
  `idServiceComments` INT(11)          NOT NULL,
  `idService`         INT(10) UNSIGNED NOT NULL,
  `comment`           VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`idServiceComments`),
  KEY `fk_ServiceComments_Service1_idx` (`idService`),
  CONSTRAINT `fk_ServiceComments_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceComments`
--

LOCK TABLES `ServiceComments` WRITE;
/*!40000 ALTER TABLE `ServiceComments`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceComments`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceProducts`
--

DROP TABLE IF EXISTS `ServiceProducts`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceProducts` (
  `idServiceProducts` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idService`         INT(10) UNSIGNED NOT NULL,
  `idProduct`         INT(10) UNSIGNED NOT NULL,
  `quantity`          INT(11)                   DEFAULT '0',
  `price`             DOUBLE                    DEFAULT '0',
  PRIMARY KEY (`idServiceProducts`),
  KEY `fk_ServiceSubproducts_Service1_idx` (`idService`),
  KEY `fk_ServiceSubproducts_Subproduct1_idx` (`idProduct`),
  CONSTRAINT `fk_ServiceSubproducts_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceSubproducts_Subproduct1` FOREIGN KEY (`idProduct`) REFERENCES `Product` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 99
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceProducts`
--

LOCK TABLES `ServiceProducts` WRITE;
/*!40000 ALTER TABLE `ServiceProducts`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceProducts`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceSpecs`
--

DROP TABLE IF EXISTS `ServiceSpecs`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceSpecs` (
  `idServiceSpecs`   INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idService`        INT(10) UNSIGNED NOT NULL,
  `idSpecs`          INT(10) UNSIGNED NOT NULL,
  `comments`         VARCHAR(250)              DEFAULT NULL,
  `quantity`         INT(11)                   DEFAULT '0',
  `specPrice`        DOUBLE                    DEFAULT '0',
  `selectedValue`    VARCHAR(250)              DEFAULT NULL,
  `serviceIncrement` DOUBLE                    DEFAULT '0',
  PRIMARY KEY (`idServiceSpecs`),
  KEY `fk_ServiceSpecs_Specs1_idx` (`idSpecs`),
  KEY `fk_ServiceSpecs_Service1_idx` (`idService`),
  CONSTRAINT `fk_ServiceSpecs_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceSpecs_Specs1` FOREIGN KEY (`idSpecs`) REFERENCES `Specs` (`idSpecs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceSpecs`
--

LOCK TABLES `ServiceSpecs` WRITE;
/*!40000 ALTER TABLE `ServiceSpecs`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceSpecs`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceTask`
--

DROP TABLE IF EXISTS `ServiceTask`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceTask` (
  `idServiceTask` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idService`     INT(10) UNSIGNED NOT NULL,
  `idTask`        INT(10) UNSIGNED NOT NULL,
  `comments`      VARCHAR(250)              DEFAULT NULL,
  `status`        INT(11)                   DEFAULT NULL,
  `started`       DATETIME                  DEFAULT NULL,
  `ended`         DATETIME                  DEFAULT NULL,
  `sortingOrder`  INT(11)                   DEFAULT NULL,
  `time`          INT(11)                   DEFAULT '10',
  PRIMARY KEY (`idServiceTask`),
  KEY `fk_ServiceTask_Task1_idx` (`idTask`),
  KEY `fk_ServiceTask_Service1_idx` (`idService`),
  CONSTRAINT `fk_ServiceTask_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTask_Task1` FOREIGN KEY (`idTask`) REFERENCES `Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 30
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceTask`
--

LOCK TABLES `ServiceTask` WRITE;
/*!40000 ALTER TABLE `ServiceTask`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceTask`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceType`
--

DROP TABLE IF EXISTS `ServiceType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceType` (
  `idServiceType`     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`              VARCHAR(250)              DEFAULT NULL,
  `description`       VARCHAR(250)              DEFAULT NULL,
  `price`             DOUBLE                    DEFAULT NULL,
  `time`              INT(11)                   DEFAULT NULL,
  `idServiceCategory` INT(10) UNSIGNED NOT NULL,
  `calculator`        TINYINT(1)                DEFAULT '0',
  PRIMARY KEY (`idServiceType`),
  KEY `fk_ServiceType_ServiceCategory1_idx` (`idServiceCategory`),
  CONSTRAINT `fk_ServiceType_ServiceCategory1` FOREIGN KEY (`idServiceCategory`) REFERENCES `ServiceCategory` (`idServiceCategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 9
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceType`
--

LOCK TABLES `ServiceType` WRITE;
/*!40000 ALTER TABLE `ServiceType`
  DISABLE KEYS */;
INSERT INTO `ServiceType`
VALUES (1, 'Lavado General', 'Ropa general', 0, 45, 1, 1), (2, 'Lavado Blancos', 'Roba de cama', 0, 120, 1, 1),
  (3, 'Planchado', 'Planchado', 0, 60, 2, 0), (4, 'Costura', 'Servicio de costura', 0, 20, 2, 0),
  (5, 'Ocho16 Spa', 'Ocho16 Spa', 0, 180, 5, 0), (6, 'Mayoreo esteticas', 'Mayoreo de esteticas', 0, 120, 6, 0);
/*!40000 ALTER TABLE `ServiceType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceTypeProductType`
--

DROP TABLE IF EXISTS `ServiceTypeProductType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceTypeProductType` (
  `idServiceType` INT(10) UNSIGNED NOT NULL,
  `idProductType` INT(10) UNSIGNED NOT NULL,
  KEY `fk_ServiceTypeSubproductType_ServiceType1_idx` (`idServiceType`),
  KEY `fk_ServiceTypeSubproductType_SubproductType1_idx` (`idProductType`),
  CONSTRAINT `fk_ServiceTypeSubproductType_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeSubproductType_SubproductType1` FOREIGN KEY (`idProductType`) REFERENCES `ProductType` (`idProductType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceTypeProductType`
--

LOCK TABLES `ServiceTypeProductType` WRITE;
/*!40000 ALTER TABLE `ServiceTypeProductType`
  DISABLE KEYS */;
INSERT INTO `ServiceTypeProductType` VALUES (4, 5), (3, 5), (5, 1), (1, 4), (2, 6), (6, 3), (7, 7);
/*!40000 ALTER TABLE `ServiceTypeProductType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceTypeSpecs`
--

DROP TABLE IF EXISTS `ServiceTypeSpecs`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceTypeSpecs` (
  `idServiceType` INT(10) UNSIGNED NOT NULL,
  `idSpecs`       INT(10) UNSIGNED NOT NULL,
  KEY `fk_ServiceTypeSpecs_Specs1_idx` (`idSpecs`),
  KEY `fk_ServiceTypeSpecs_ServiceType1_idx` (`idServiceType`),
  CONSTRAINT `fk_ServiceTypeSpecs_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeSpecs_Specs1` FOREIGN KEY (`idSpecs`) REFERENCES `Specs` (`idSpecs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceTypeSpecs`
--

LOCK TABLES `ServiceTypeSpecs` WRITE;
/*!40000 ALTER TABLE `ServiceTypeSpecs`
  DISABLE KEYS */;
INSERT INTO `ServiceTypeSpecs`
VALUES (1, 1), (1, 2), (2, 1), (2, 2), (3, 5), (5, 2), (5, 3), (5, 4), (1, 4), (7, 6), (2, 7), (2, 4), (2, 8), (2, 9),
  (2, 3), (1, 7), (1, 3), (1, 8), (1, 9), (5, 7), (5, 9), (3, 8), (4, 8), (6, 7), (6, 1), (6, 2), (6, 3), (6, 4),
  (6, 8), (6, 9);
/*!40000 ALTER TABLE `ServiceTypeSpecs`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ServiceTypeTask`
--

DROP TABLE IF EXISTS `ServiceTypeTask`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceTypeTask` (
  `idServiceTypeTask` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idServiceType`     INT(10) UNSIGNED NOT NULL,
  `idTask`            INT(10) UNSIGNED NOT NULL,
  `sortingOrder`      INT(11)                   DEFAULT NULL,
  `time`              INT(11)                   DEFAULT '0',
  PRIMARY KEY (`idServiceTypeTask`),
  KEY `fk_ServiceTypeTask_Task1_idx` (`idTask`),
  KEY `fk_ServiceTypeTask_ServiceType1_idx` (`idServiceType`),
  CONSTRAINT `fk_ServiceTypeTask_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeTask_Task1` FOREIGN KEY (`idTask`) REFERENCES `Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceTypeTask`
--

LOCK TABLES `ServiceTypeTask` WRITE;
/*!40000 ALTER TABLE `ServiceTypeTask`
  DISABLE KEYS */;
INSERT INTO `ServiceTypeTask`
VALUES (1, 1, 2, 1, 10), (2, 1, 5, 2, 10), (3, 2, 2, 1, 10), (4, 2, 5, 2, 10), (12, 7, 8, 0, 0), (13, 7, 9, 1, 0),
  (14, 3, 6, 0, 0), (15, 4, 11, 1, 0), (16, 4, 10, 0, 0), (17, 5, 5, 2, 0), (18, 5, 2, 0, 0), (19, 5, 7, 1, 0),
  (20, 6, 5, 2, 0), (21, 6, 7, 1, 0), (22, 6, 2, 0, 0);
/*!40000 ALTER TABLE `ServiceTypeTask`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SocialNetworkData`
--

DROP TABLE IF EXISTS `SocialNetworkData`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SocialNetworkData` (
  `idSocialNetworkData` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data`                LONGTEXT,
  `idAccessKey`         INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idSocialNetworkData`),
  KEY `fk_SocialNetworkData_AccessKey1_idx` (`idAccessKey`),
  CONSTRAINT `fk_SocialNetworkData_AccessKey1` FOREIGN KEY (`idAccessKey`) REFERENCES `AccessKey` (`idAccessKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SocialNetworkData`
--

LOCK TABLES `SocialNetworkData` WRITE;
/*!40000 ALTER TABLE `SocialNetworkData`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `SocialNetworkData`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SocialNetworks`
--

DROP TABLE IF EXISTS `SocialNetworks`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SocialNetworks` (
  `idSocialNetworks` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`             VARCHAR(45)               DEFAULT NULL,
  `domain`           VARCHAR(45)               DEFAULT NULL,
  PRIMARY KEY (`idSocialNetworks`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SocialNetworks`
--

LOCK TABLES `SocialNetworks` WRITE;
/*!40000 ALTER TABLE `SocialNetworks`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `SocialNetworks`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Specs`
--

DROP TABLE IF EXISTS `Specs`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Specs` (
  `idSpecs`     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT 'This table is for information about a service\nsuch as \njavon \nsuavisante\ntypo lavador\n',
  `name`        VARCHAR(45)               DEFAULT NULL,
  `description` VARCHAR(45)               DEFAULT NULL,
  `optional`    TINYINT(1)                DEFAULT '1',
  `max_qty`     INT(11)                   DEFAULT '0',
  `deleted`     INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSpecs`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Specs`
--

LOCK TABLES `Specs` WRITE;
/*!40000 ALTER TABLE `Specs`
  DISABLE KEYS */;
INSERT INTO `Specs`
VALUES (1, 'Suavizante', 'Tipo de suavizante', 1, 1, 0), (2, 'Detergente', 'Jabon / detergente a utilizarse', 1, 1, 0),
  (3, 'Tipo de secado', 'Tipo de secado', 1, 0, 0), (4, 'Toallas de secado', 'toalla aromatizante de secado', 1, 1, 0),
  (5, 'Ganchos', 'Ganchos para prendas', 1, 0, 0), (6, 'Tintoreria', 'general', 0, 0, 0),
  (7, 'Blanqueador', 'Blanqueador de ropa', 1, 0, 0), (8, 'Bolsas', 'Bolsas varias', 1, 0, 0),
  (9, 'Aroma', 'Reforzador de aroma', 1, 0, 0);
/*!40000 ALTER TABLE `Specs`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SpecsValues`
--

DROP TABLE IF EXISTS `SpecsValues`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SpecsValues` (
  `idSpecsValues`    INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idSpecs`          INT(10) UNSIGNED NOT NULL,
  `type`             INT(11)                   DEFAULT '1'
  COMMENT '1 = value\n2 = product',
  `value`            VARCHAR(45)               DEFAULT NULL,
  `idSupplyType`     INT(11)                   DEFAULT '0',
  `serviceIncrement` DOUBLE                    DEFAULT '0',
  `prefered`         INT(11)                   DEFAULT '0',
  `specPrice`        DOUBLE                    DEFAULT '0',
  `costType`         INT(11)                   DEFAULT '1'
  COMMENT '1 = increment\n2 = specPrice',
  PRIMARY KEY (`idSpecsValues`),
  KEY `fk_SpecsValues_Specs1_idx` (`idSpecs`),
  CONSTRAINT `fk_SpecsValues_Specs1` FOREIGN KEY (`idSpecs`) REFERENCES `Specs` (`idSpecs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SpecsValues`
--

LOCK TABLES `SpecsValues` WRITE;
/*!40000 ALTER TABLE `SpecsValues`
  DISABLE KEYS */;
INSERT INTO `SpecsValues`
VALUES (2, 1, 2, '', 2, 0, 0, 0, 0), (3, 2, 2, NULL, 1, 0, 0, 0, 0), (4, 3, 1, 'Secadora', 0, 0, 0, 0, 0),
  (5, 3, 1, 'Al Sol', 0, 0, 0, 0, 1), (6, 4, 2, NULL, 4, 0, 0, 0, 0), (7, 5, 2, NULL, 5, 0, 0, 0, 0),
  (8, 7, 2, NULL, 3, 0, 0, 0, 0), (9, 8, 2, NULL, 7, 0, 0, 0, 0), (10, 9, 2, NULL, 8, 0, 0, 0, 0);
/*!40000 ALTER TABLE `SpecsValues`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Stores`
--

DROP TABLE IF EXISTS `Stores`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Stores` (
  `idStore`    INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`       VARCHAR(45)      NOT NULL,
  `lat`        DECIMAL(10, 8)   NOT NULL,
  `lng`        DECIMAL(11, 8)   NOT NULL,
  `idEmployee` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idStore`),
  KEY `fk_Stores_Employee1_idx` (`idEmployee`),
  CONSTRAINT `fk_Stores_Employee1` FOREIGN KEY (`idEmployee`) REFERENCES `Employee` (`idEmployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Stores`
--

LOCK TABLES `Stores` WRITE;
/*!40000 ALTER TABLE `Stores`
  DISABLE KEYS */;
INSERT INTO `Stores` VALUES (1, 'unica', 20.62132700, -103.41805600, 1);
/*!40000 ALTER TABLE `Stores`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Supply`
--

DROP TABLE IF EXISTS `Supply`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Supply` (
  `idSupply`         INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idSupplyType`     INT(10) UNSIGNED NOT NULL,
  `status`           INT(11)          NOT NULL DEFAULT '0',
  `name`             VARCHAR(45)               DEFAULT NULL,
  `description`      VARCHAR(250)              DEFAULT NULL,
  `price`            DOUBLE                    DEFAULT '0',
  `serviceIncrement` DOUBLE                    DEFAULT '0',
  `deleted`          INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSupply`),
  KEY `fk_Product_ProductType1_idx` (`idSupplyType`),
  CONSTRAINT `fk_Product_ProductType1` FOREIGN KEY (`idSupplyType`) REFERENCES `SupplyType` (`idSupplyType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Supply`
--

LOCK TABLES `Supply` WRITE;
/*!40000 ALTER TABLE `Supply`
  DISABLE KEYS */;
INSERT INTO `Supply`
VALUES (1, 1, 1, 'Mas color', 'Jabon liquido', 320, 0, 0), (2, 1, 1, 'Ace', 'Jabon liquido', 145, 0, 0),
  (3, 2, 1, 'En suenio', 'Suavisante de prendas', 109, 0, 0),
  (4, 4, 1, 'Toallas Naranja', 'Toallas de secado (pendiente nombre)', 199, 5, 0),
  (5, 3, 1, 'Cloro', 'Blanqueador cloro', 60, 0, 0), (6, 5, 1, 'Ganchos sencillo', 'Caja con 500 u', 475, 2, 0),
  (7, 1, 1, 'Roma', 'Jabon en polvo para mayoreo', 228, 0, 0),
  (8, 1, 1, 'Suavitel', 'Suavisante de prendas', 120, 0, 0), (9, 1, 1, 'Dawny', 'Suavisante de prendas', 180, 0, 0),
  (10, 3, 1, 'Pinon', 'Pinol', 139, 0, 0), (11, 3, 1, 'Vanish', 'Vanish', 170, 0, 0),
  (12, 3, 1, 'Vinagre', 'Vinagre', 45, 0, 0), (13, 7, 1, 'Bolsas', 'Bolsas plastico 5K', 259, 0, 0),
  (14, 8, 1, 'DW', 'Reforzador de aroma', 38, 0, 0), (15, 9, 1, 'Agua destilada', 'Plancha, 3 garrafones', 87, 0, 0),
  (16, 10, 1, 'Almidon', '3K', 100, 0, 0), (17, 1, 1, 'Dreft', 'Detergente bebes', 250, 2, 0),
  (18, 2, 1, 'Dreft Suavisante', 'Suavisante para bebes', 250, 2, 0);
/*!40000 ALTER TABLE `Supply`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SupplyType`
--

DROP TABLE IF EXISTS `SupplyType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SupplyType` (
  `idSupplyType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(45)               DEFAULT NULL,
  `description`  VARCHAR(255)              DEFAULT NULL,
  `deleted`      INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSupplyType`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SupplyType`
--

LOCK TABLES `SupplyType` WRITE;
/*!40000 ALTER TABLE `SupplyType`
  DISABLE KEYS */;
INSERT INTO `SupplyType` VALUES (1, 'Detergente', 'Jabones/Detergentes', 0), (2, 'Suavizante', 'Suavisantes', 0),
  (3, 'Blanqueador', 'Blanqueadores de prendas', 0), (4, 'Toallas de secado', 'Toallas para secado', 0),
  (5, 'Ganchos', 'ganchos para ropa', 0), (6, 'Blanqueador', 'Blanqueadores', 1), (7, 'Bolsas', 'Bolsas varias', 0),
  (8, 'Reforzador de aroma', 'aroma al doblar', 0), (9, 'Agua destilada', 'Agua destilada Plancha', 0),
  (10, 'Almidon', 'Almidon Plancha', 0), (11, 'Detergente Bebes', 'Detergente para bebes', 1);
/*!40000 ALTER TABLE `SupplyType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Task`
--

DROP TABLE IF EXISTS `Task`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Task` (
  `idTask`      INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idTaskType`  INT(10) UNSIGNED NOT NULL,
  `name`        VARCHAR(45)               DEFAULT NULL,
  `description` VARCHAR(45)               DEFAULT NULL,
  `deleted`     INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTask`),
  KEY `fk_Task_TaskType1_idx` (`idTaskType`),
  CONSTRAINT `fk_Task_TaskType1` FOREIGN KEY (`idTaskType`) REFERENCES `TaskType` (`idTaskType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task`
  DISABLE KEYS */;
INSERT INTO `Task` VALUES (1, 1, 'Servicio para ordenes', 'Servicio para ordenes.', 0),
  (2, 2, 'Lavado lavadora', 'lavado de ropa general', 0), (3, 4, 'Recojer', 'recojer pedido', 0),
  (4, 4, 'Entregar', 'entregar pedido', 0), (5, 2, 'Doblar ropa', 'doblado', 0), (6, 3, 'Planchar', 'planchar', 0),
  (7, 2, 'Lavado a mano', 'Lavado a mano', 0), (8, 5, 'Solicitar Tintoreria', 'Solicitar Tintoreria', 0),
  (9, 5, 'Recibir Tintoreria', 'Resepcion de tintoreria', 0), (10, 6, 'Solicitar Costura', 'Costura', 0),
  (11, 6, 'Recibir Costura', 'Resepcion de costura', 0);
/*!40000 ALTER TABLE `Task`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TaskType`
--

DROP TABLE IF EXISTS `TaskType`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TaskType` (
  `idTaskType`  INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(45)               DEFAULT NULL,
  `description` VARCHAR(255)              DEFAULT NULL,
  `ordersOnly`  TINYINT(1)                DEFAULT '0',
  `deleted`     INT(11)          NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTaskType`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TaskType`
--

LOCK TABLES `TaskType` WRITE;
/*!40000 ALTER TABLE `TaskType`
  DISABLE KEYS */;
INSERT INTO `TaskType`
VALUES (1, 'Tareas de Ordenes', 'Trabajo para servicio', 1, 0), (2, 'Lavado', 'todo lo relevante a lavar ropa', 0, 0),
  (3, 'Planchado', 'todo lo relevante a planchado', 0, 0), (4, 'Transporte', 'recojer o entregar pedidos', 0, 0),
  (5, 'Tintoreria', 'Seguimiento de tintoreria', 0, 0), (6, 'Costura', 'Costura', 0, 0);
/*!40000 ALTER TABLE `TaskType`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sod_db'
--

--
-- Dumping routines for database 'sod_db'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2017-06-19 13:40:04
