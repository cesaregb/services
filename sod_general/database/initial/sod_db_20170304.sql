/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50716
 Source Host           : localhost
 Source Database       : sod_db

 Target Server Type    : MySQL
 Target Server Version : 50716
 File Encoding         : utf-8

 Date: 03/04/2017 14:46:36 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `AccessKey`
-- ----------------------------
DROP TABLE IF EXISTS `AccessKey`;
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

-- ----------------------------
--  Table structure for `Address`
-- ----------------------------
DROP TABLE IF EXISTS `Address`;
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
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Address`
-- ----------------------------
BEGIN;
INSERT INTO `Address` VALUES ('1', '1', 'Mexico', 'Jalisco', '44540', 'Guadalajara', 'Calle Peninsula', 'Bosques de La Victoria', 'No hay timbre tocar claxon', '20.64548516', '-103.39241982', '1', '1'), ('2', '1', 'Mexico', 'Jalisco', '44540', 'Guadalajara', 'Calle Barlovento', 'Rinconada del Bosque', 'departamento sin timbre', '20.64997290', '-103.38900805', '0', '0'), ('3', '2', null, null, null, null, 'Direccion', null, null, null, null, '0', '0'), ('4', '3', null, null, null, null, 'Colegio Becquer', null, null, null, null, '0', '0'), ('5', '4', null, null, null, null, 'Sochicalco 5955', null, null, null, null, '0', '0'), ('6', '5', null, null, null, null, 'Restaurantes Atemai', null, null, null, null, '0', '0'), ('7', '6', null, null, null, null, 'av.del pinar', null, null, null, null, '0', '0'), ('8', '7', null, null, null, null, '\"Av', null, null, null, null, '0', '0'), ('9', '8', null, null, null, null, 'Ciber', null, null, null, null, '0', '0'), ('10', '9', null, null, null, null, 'penisnula2749 int 12', null, null, null, null, '0', '0'), ('11', '10', null, null, null, null, 'Av. del pinar 3498', null, null, null, null, '0', '0'), ('12', '11', null, null, null, null, '18 de marzo 1875-B', null, null, null, null, '0', '0'), ('13', '12', null, null, null, null, 'Peten5484', null, null, null, null, '0', '0'), ('14', '13', null, null, null, null, 'Bahia Acapulco 2988-9', null, null, null, null, '0', '0'), ('15', '14', null, null, null, null, 'Tomas Balcazar', null, null, null, null, '0', '0'), ('16', '15', null, null, null, null, 'av. del pinar 3447', null, null, null, null, '0', '0'), ('17', '16', null, null, null, null, 'Cancun 5908', null, null, null, null, '0', '0'), ('18', '17', null, null, null, null, 'Tula 5736', null, null, null, null, '0', '0'), ('19', '18', null, null, null, null, 'Av. Del pinar 3340', null, null, null, null, '0', '0'), ('20', '19', null, null, null, null, 'Avenida del pinar 3240', null, null, null, null, '0', '0'), ('21', '20', null, null, null, null, 'Rio mezquitic 1154', null, null, null, null, '0', '0'), ('22', '21', null, null, null, null, 'Av. del  Pinar 3353', null, null, null, null, '0', '0'), ('23', '22', null, null, null, null, 'av. Del pinar3340 int.334', null, null, null, null, '0', '0'), ('24', '23', null, null, null, null, 'Copilco1501', null, null, null, null, '0', '0'), ('25', '24', 'Mexico', 'Jalisco', '45085', 'Zapopan', 'Río Tuxcacuesco 658', 'Loma Bonita Ejidal', null, '20.62484187', '-103.40460777', '0', '0'), ('26', '25', null, null, null, null, 'Corona Boreal 4223', null, null, null, null, '0', '0'), ('27', '26', null, null, null, null, 'av. del pinar 3340-int.334', null, null, null, null, '0', '0'), ('28', '27', null, null, null, null, 'Lopez Mateos Sur 5871', null, null, null, null, '0', '0'), ('29', '28', null, null, null, null, 'Bonampak 3190', null, null, null, null, '0', '0'), ('30', '29', null, null, null, null, 'Isac newton 4105', null, null, null, null, '0', '0'), ('31', '30', null, null, null, null, 'Santa Ana Norte ', null, null, null, null, '0', '0'), ('32', '31', null, null, null, null, 'av. del pinar3366', null, null, null, null, '0', '0'), ('33', '32', null, null, null, null, 'PLaya Bucerias 5601', null, null, null, null, '0', '0'), ('34', '33', null, null, null, null, ' ', null, null, null, null, '0', '0'), ('35', '34', null, null, null, null, 'Copilco 5946', null, null, null, null, '0', '0'), ('36', '35', null, null, null, null, 'av.del pinar 3256', null, null, null, null, '0', '0'), ('37', '36', null, null, null, null, 'Lopez Mateos Sur 5871', null, null, null, null, '0', '0'), ('38', '37', null, null, null, null, 'Av. del pinar 3340 interior 314', null, null, null, null, '0', '0'), ('39', '38', null, null, null, null, 'Bonampack 3318', null, null, null, null, '0', '0'), ('40', '39', null, null, null, null, 'Rio Cuitzle5668', null, null, null, null, '0', '0'), ('41', '40', null, null, null, null, 'Cancun 1806', null, null, null, null, '0', '0'), ('42', '41', null, null, null, null, 'Corona Boreal 4223', null, null, null, null, '0', '0'), ('43', '42', null, null, null, null, 'cancun 5912', null, null, null, null, '0', '0'), ('44', '43', null, null, null, null, 'Aztlan 3335', null, null, null, null, '0', '0'), ('45', '44', null, null, null, null, 'Aztlan 1975', null, null, null, null, '0', '0'), ('46', '45', null, null, null, null, 'aztlan 3335', null, null, null, null, '0', '0'), ('47', '46', null, null, null, null, ' Aztlan Torre335 INt231', null, null, null, null, '0', '0'), ('48', '47', null, null, null, null, 'Cancun 1907', null, null, null, null, '0', '0'), ('49', '48', null, null, null, null, 'Cancun1907', null, null, null, null, '0', '0'), ('50', '49', null, null, null, null, 'Camino al iteso 8900', null, null, null, null, '0', '0'), ('51', '50', null, null, null, null, 'Playa Brucelas5589', null, null, null, null, '0', '0'), ('52', '51', null, null, null, null, 'Teotihuacan 2808', null, null, null, null, '0', '0'), ('53', '52', null, null, null, null, 'Cancun B', null, null, null, null, '0', '0'), ('54', '53', null, null, null, null, '\"Av', null, null, null, null, '0', '0'), ('55', '54', null, null, null, null, 'Colegio Becker', null, null, null, null, '0', '0'), ('56', '55', null, null, null, null, 'peninsula 2749 15', null, null, null, null, '0', '0'), ('57', '56', null, null, null, null, 'Av. del pinar 2889', null, null, null, null, '0', '0'), ('58', '57', null, null, null, null, 'Rio Nilo 7922', null, null, null, null, '0', '0'), ('59', '58', null, null, null, null, 'Loma Linda8043', null, null, null, null, '0', '0'), ('60', '59', null, null, null, null, 'Rio santiago 5738', null, null, null, null, '0', '0'), ('61', '60', null, null, null, null, 'hotel hoLI day', null, null, null, null, '0', '0'), ('62', '61', null, null, null, null, 'AV. PINAR 3340 int 313', null, null, null, null, '0', '0'), ('63', '62', null, null, null, null, '\"Araceli Zouza 5140 A', null, null, null, null, '0', '0'), ('64', '63', null, null, null, null, 'uxmal 1409', null, null, null, null, '0', '0'), ('65', '64', null, null, null, null, 'mazamitla 1922', null, null, null, null, '0', '0'), ('66', '65', null, null, null, null, 'Av. del tesoro', null, null, null, null, '0', '0'), ('67', '66', null, null, null, null, 'rio lerma 1898', null, null, null, null, '0', '0'), ('68', '67', null, null, null, null, 'teotihuacan2966', null, null, null, null, '0', '0'), ('69', '68', null, null, null, null, 'Mariano Otero 1354', null, null, null, null, '0', '0'), ('70', '69', null, null, null, null, 'acuatix', null, null, null, null, '0', '0'), ('71', '70', null, null, null, null, 'Av. del  Pinar 3353', null, null, null, null, '0', '0'), ('72', '71', null, null, null, null, 'uxmal 1410', null, null, null, null, '0', '0'), ('73', '72', null, null, null, null, 'Av. del pinar 3310', null, null, null, null, '0', '0'), ('74', '73', null, null, null, null, 'obsidiana 2887', null, null, null, null, '0', '0'), ('75', '74', null, null, null, null, 'Aztlan 3335', null, null, null, null, '0', '0'), ('76', '75', null, null, null, null, 'Cancun  1905', null, null, null, null, '0', '0'), ('77', '76', null, null, null, null, '\"Morelos 2262', null, null, null, null, '0', '0'), ('78', '77', null, null, null, null, 'Aztlan 3335', null, null, null, null, '0', '0'), ('79', '78', null, null, null, null, 'Avenida del pinar 3316', null, null, null, null, '0', '0'), ('80', '79', null, null, null, null, 'Sierra Mazamitla 5581', null, null, null, null, '0', '0'), ('81', '80', null, null, null, null, 'av. del pinar 1908', null, null, null, null, '0', '0'), ('82', '81', null, null, null, null, 'Av. del Tesoro 981 int. 202', null, null, null, null, '0', '0'), ('83', '82', null, null, null, null, 'teotihuacan 1906', null, null, null, null, '0', '0'), ('84', '83', null, null, null, null, 'Valle de Atejamac  2118', null, null, null, null, '0', '0'), ('85', '84', null, null, null, null, 'parroquia santo niño de atocho', null, null, null, null, '0', '0'), ('86', '85', null, null, null, null, 'Andador Xochicalco 5966', null, null, null, null, '0', '0'), ('87', '86', null, null, null, null, 'Peten 5912', null, null, null, null, '0', '0'), ('88', '87', null, null, null, null, 'Avenida del pinar 3329', null, null, null, null, '0', '0'), ('89', '88', null, null, null, null, 'Avenida del pinar 3340 int 332', null, null, null, null, '0', '0'), ('90', '89', null, null, null, null, '\"Rio Colotlan 2084', null, null, null, null, '0', '0'), ('91', '90', null, null, null, null, '\"Tlatelolco 3344', null, null, null, null, '0', '0'), ('92', '91', null, null, null, null, 'volcan cofre de perote 5057. Casa 2', null, null, null, null, '0', '0'), ('93', '92', null, null, null, null, '\"jaime Rivera 3081', null, null, null, null, '0', '0'), ('94', '93', null, null, null, null, 'Rio Mezquitic 1154', null, null, null, null, '0', '0'), ('95', '94', null, null, null, null, 'Av. del pinar 3498', null, null, null, null, '0', '0'), ('96', '95', null, null, null, null, 'Cancun 1890', null, null, null, null, '0', '0'), ('97', '96', null, null, null, null, 'Mariano Otero 1354', null, null, null, null, '0', '0'), ('98', '97', null, null, null, null, 'Quick Plaza del sol', null, null, null, null, '0', '0'), ('99', '98', null, null, null, null, 'corona boreal ', null, null, null, null, '0', '0'), ('100', '99', null, null, null, null, 'Av. del pinar 3340', null, null, null, null, '0', '0'), ('101', '100', null, null, null, null, 'Volcan Paricutin  5526 interior 21', null, null, null, null, '0', '0'), ('102', '101', null, null, null, null, 'Tuxtla 5914', null, null, null, null, '0', '0'), ('103', '102', null, null, null, null, 'Chicomostock 3044', null, null, null, null, '0', '0'), ('104', '103', null, null, null, null, 'Bonampack3318', null, null, null, null, '0', '0'), ('105', '104', null, null, null, null, 'Av. del Pinar 1974', null, null, null, null, '0', '0'), ('106', '105', null, null, null, null, 'Cuicuilco 1795', null, null, null, null, '0', '0'), ('107', '106', null, null, null, null, 'av.del pinar 3223', null, null, null, null, '0', '0'), ('108', '107', null, null, null, null, 'aztlan 1805', null, null, null, null, '0', '0'), ('109', '108', null, null, null, null, '\"aldama 83105', null, null, null, null, '0', '0'), ('110', '109', null, null, null, null, 'AZTLAN 1950', null, null, null, null, '0', '0'), ('111', '110', null, null, null, null, 'Cuicuilco 1813', null, null, null, null, '0', '0'), ('112', '111', null, null, null, null, 'Rio Santiago 5814', null, null, null, null, '0', '0'), ('113', '112', null, null, null, null, 'cuicuilco 1936', null, null, null, null, '0', '0'), ('114', '113', null, null, null, null, 'playa bucerias 5589', null, null, null, null, '0', '0'), ('115', '114', null, null, null, null, 'Aurora Boreal 3937 int 3', null, null, null, null, '0', '0'), ('116', '115', null, null, null, null, 'av. del pinar 3044 dep3', null, null, null, null, '0', '0'), ('117', '116', null, null, null, null, 'cacaco 1726', null, null, null, null, '0', '0'), ('118', '117', null, null, null, null, 'Av. del Pinar 3050 int\'8', null, null, null, null, '0', '0'), ('119', '118', null, null, null, null, 'bonanpack 5955', null, null, null, null, '0', '0'), ('120', '119', null, null, null, null, 'Primo Feliciano Velazquez 761', null, null, null, null, '0', '0'), ('121', '120', null, null, null, null, 'Peten 5918', null, null, null, null, '0', '0'), ('122', '121', null, null, null, null, '', null, null, null, null, '0', '0'), ('123', '122', null, null, null, null, 'Bonampack 3424 int 13', null, null, null, null, '0', '0'), ('124', '123', null, null, null, null, 'Colegio Becker', null, null, null, null, '0', '0'), ('125', '124', null, null, null, null, 'Av. del pinar 2857', null, null, null, null, '0', '0'), ('126', '125', null, null, null, null, 'Echeverria', null, null, null, null, '0', '0'), ('127', '126', null, null, null, null, 'esturion 3071', null, null, null, null, '0', '0'), ('128', '127', null, null, null, null, '', null, null, null, null, '0', '0'), ('129', '128', null, null, null, null, 'Tuxtla5892', null, null, null, null, '0', '0'), ('130', '129', null, null, null, null, 'ixtepete 3492 int 4', null, null, null, null, '0', '0'), ('131', '130', null, null, null, null, 'Ixtepete 3285', null, null, null, null, '0', '0'), ('132', '131', null, null, null, null, 'Av. del Pinar 3230', null, null, null, null, '0', '0'), ('133', '132', null, null, null, null, 'rio mexquitic 1147', null, null, null, null, '0', '0'), ('134', '133', null, null, null, null, 'Avenida del Pinar 3498', null, null, null, null, '0', '0'), ('135', '134', null, null, null, null, 'Cancun 1788', null, null, null, null, '0', '0'), ('136', '135', null, null, null, null, 'cisne 1249 colonia morelos entre cardenal y dos de abril', null, null, null, null, '0', '0'), ('137', '136', null, null, null, null, 'la burreteria', null, null, null, null, '0', '0'), ('138', '137', null, null, null, null, 'Cuicuilco1974', null, null, null, null, '0', '0'), ('139', '138', null, null, null, null, 'rio tuxpan 894', null, null, null, null, '0', '0'), ('140', '139', null, null, null, null, 'av del pinar 3340', null, null, null, null, '0', '0'), ('141', '140', null, null, null, null, '\"Av', null, null, null, null, '0', '0'), ('142', '141', null, null, null, null, 'peninsula 2749 int 10', null, null, null, null, '0', '0'), ('143', '142', null, null, null, null, 'av del pinar 3340 1', null, null, null, null, '0', '0'), ('144', '143', null, null, null, null, 'copan 5755', null, null, null, null, '0', '0'), ('145', '144', null, null, null, null, 'Av. del PInar 2805-b8', null, null, null, null, '0', '0'), ('146', '145', null, null, null, null, 'Tula 5715', null, null, null, null, '0', '0'), ('147', '146', null, null, null, null, 'cancun 5906', null, null, null, null, '0', '0'), ('148', '147', null, null, null, null, 'Tuxtla 5914 int 1', null, null, null, null, '0', '0'), ('149', '148', null, null, null, null, 'av del pinar 3340', null, null, null, null, '0', '0'), ('150', '149', null, null, null, null, 'laguna 15fraccionamiento loreto', null, null, null, null, '0', '0'), ('151', '150', null, null, null, null, 'Aztlan 3335', null, null, null, null, '0', '0'), ('152', '151', null, null, null, null, 'Aztlan 3335 int213', null, null, null, null, '0', '0'), ('153', '152', null, null, null, null, 'Tical 1615', null, null, null, null, '0', '0'), ('154', '153', null, null, null, null, 'cancun 5908', null, null, null, null, '0', '0'), ('155', '154', null, null, null, null, 'Andador Mayapan 3362 in 31', null, null, null, null, '0', '0'), ('156', '155', null, null, null, null, '\"Martillo 2139', null, null, null, null, '0', '0'), ('157', '156', null, null, null, null, 'isla mexcala4921 int23', null, null, null, null, '0', '0'), ('158', '157', null, null, null, null, 'galileo galiley3608', null, null, null, null, '0', '0'), ('159', '158', null, null, null, null, 'cuicuilco3396', null, null, null, null, '0', '0'), ('160', '159', null, null, null, null, 'Cancun 1745', null, null, null, null, '0', '0'), ('161', '160', null, null, null, null, 'Bonampack 1948', null, null, null, null, '0', '0'), ('162', '161', null, null, null, null, 'turqueza 2531', null, null, null, null, '0', '0'), ('163', '162', null, null, null, null, 'Av. del pinar 2805', null, null, null, null, '0', '0'), ('164', '163', null, null, null, null, 'Aztlan 3335 inte 133', null, null, null, null, '0', '0'), ('165', '164', null, null, null, null, 'MORELOS 2368', null, null, null, null, '0', '0'), ('166', '165', null, null, null, null, 'aztlan 3335 int 222', null, null, null, null, '0', '0'), ('167', '166', null, null, null, null, 'av padre javier scheifler 719 int 9', null, null, null, null, '0', '0'), ('168', '167', null, null, null, null, 'Mariano Otero  5654', null, null, null, null, '0', '0'), ('169', '168', null, null, null, null, 'Avenida del Tesoro 5781', null, null, null, null, '0', '0'), ('170', '169', null, null, null, null, 'Quebrada 3282 A int 12', null, null, null, null, '0', '0'), ('171', '170', null, null, null, null, 'Quebrada 3282 A int 12', null, null, null, null, '0', '0'), ('172', '171', null, null, null, null, '', null, null, null, null, '0', '0'), ('173', '172', null, null, null, null, 'Rio Colotlan 1651', null, null, null, null, '0', '0'), ('174', '173', null, null, null, null, '\"Litoral 2510', null, null, null, null, '0', '0'), ('175', '174', null, null, null, null, 'Av. del Pinar 1776', null, null, null, null, '0', '0'), ('176', '175', null, null, null, null, 'Galerias', null, null, null, null, '0', '0'), ('177', '176', null, null, null, null, 'Plaza del Sol', null, null, null, null, '0', '0'), ('178', '177', null, null, null, null, 'molina 2876', null, null, null, null, '0', '0'), ('179', '178', null, null, null, null, 'Aztlan 3335 132', null, null, null, null, '0', '0'), ('180', '179', null, null, null, null, 'Peten 5918', null, null, null, null, '0', '0'), ('181', '180', null, null, null, null, 'Claudio Tolomeo', null, null, null, null, '0', '0'), ('182', '181', null, null, null, null, 'prolongacion las Fuentes 3012', null, null, null, null, '0', '0'), ('183', '182', null, null, null, null, '', null, null, null, null, '0', '0'), ('184', '183', null, null, null, null, 'Cuicuilco 1795', null, null, null, null, '0', '0'), ('185', '184', null, null, null, null, 'rio atengo 1839', null, null, null, null, '0', '0'), ('186', '185', null, null, null, null, 'av faro 2778', null, null, null, null, '0', '0'), ('187', '186', null, null, null, null, 'av del pinar 3447', null, null, null, null, '0', '0'), ('188', '187', null, null, null, null, 'peninsula 2727 interior 1', null, null, null, null, '0', '0'), ('189', '188', null, null, null, null, '', null, null, null, null, '0', '0'), ('190', '189', null, null, null, null, 'chicomostok 1615', null, null, null, null, '0', '0'), ('191', '190', null, null, null, null, 'michoacan 144 colonia el mante', null, null, null, null, '0', '0'), ('192', '191', null, null, null, null, 'Rio Santiago 5932', null, null, null, null, '0', '0'), ('193', '192', null, null, null, null, 'peten 5903', null, null, null, null, '0', '0'), ('194', '193', null, null, null, null, 'Teotihuacan 1922', null, null, null, null, '0', '0'), ('195', '194', null, null, null, null, 'Av. del pinar 1766', null, null, null, null, '0', '0'), ('196', '195', null, null, null, null, '', null, null, null, null, '0', '0'), ('197', '196', null, null, null, null, 'sierra de mazamitla 5895int 1', null, null, null, null, '0', '0'), ('198', '197', null, null, null, null, 'cuicuilco1285', null, null, null, null, '0', '0'), ('199', '198', null, null, null, null, 'cancun 1788', null, null, null, null, '0', '0'), ('200', '199', null, null, null, null, 'Aztlan', null, null, null, null, '0', '0'), ('201', '200', null, null, null, null, 'cancun 5977', null, null, null, null, '0', '0'), ('202', '201', null, null, null, null, 'av las fuentes 99', null, null, null, null, '0', '0'), ('203', '202', null, null, null, null, 'Av. Cruz sur 2447', null, null, null, null, '0', '0'), ('204', '203', null, null, null, null, '', null, null, null, null, '0', '0'), ('205', '204', null, null, null, null, '', null, null, null, null, '0', '0'), ('206', '205', null, null, null, null, 'av del pinar 1776', null, null, null, null, '0', '0'), ('207', '206', null, null, null, null, 'Aztlan ', null, null, null, null, '0', '0'), ('208', '207', null, null, null, null, 'aztlan 3282', null, null, null, null, '0', '0'), ('209', '208', null, null, null, null, 'Vistas a la catedral 3041', null, null, null, null, '0', '0'), ('210', '209', null, null, null, null, 'Faro 2768', null, null, null, null, '0', '0'), ('211', '210', null, null, null, null, 'Cancun 1440', null, null, null, null, '0', '0'), ('212', '211', null, null, null, null, 'Av. del pinar 3230', null, null, null, null, '0', '0'), ('213', '212', null, null, null, null, 'Quebrada 2714 2', null, null, null, null, '0', '0'), ('214', '213', null, null, null, null, '\"Av. del pinar ', null, null, null, null, '0', '0'), ('215', '214', 'Mexico', 'Jalisco', '45070', 'Zapopan', 'Calle Sagitario 5598', 'Arboledas', 'Marcar antes de acudir', '20.62637817', '-103.42320085', '0', '0'), ('216', '215', null, null, null, null, '', null, null, null, null, '0', '0'), ('217', '216', null, null, null, null, 'Andador coba 3333 int 12', null, null, null, null, '0', '0'), ('218', '217', null, null, null, null, 'Teotihuacan 1939', null, null, null, null, '0', '0'), ('219', '218', null, null, null, null, 'Av. del Pinar  3427', null, null, null, null, '0', '0'), ('220', '219', null, null, null, null, 'Sierra Mazamitla 5895 int 1', null, null, null, null, '0', '0'), ('221', '220', null, null, null, null, '', null, null, null, null, '0', '0'), ('222', '221', null, null, null, null, 'aztlan 1889', null, null, null, null, '0', '0'), ('223', '222', null, null, null, null, '', null, null, null, null, '0', '0'), ('224', '223', null, null, null, null, 'Av. del Pinar 3447', null, null, null, null, '0', '0'), ('225', '224', null, null, null, null, 'Bonampack 3307', null, null, null, null, '0', '0'), ('226', '225', null, null, null, null, 'privada de las gardenias sur 11 el moral', null, null, null, null, '0', '0'), ('227', '226', null, null, null, null, 'Rio Santiago 5923', null, null, null, null, '0', '0'), ('228', '227', null, null, null, null, 'peten 5918', null, null, null, null, '0', '0'), ('229', '228', null, null, null, null, 'av del pinar 3032', null, null, null, null, '0', '0'), ('230', '229', null, null, null, null, 'aztlan 1822', null, null, null, null, '0', '0'), ('231', '230', null, null, null, null, 'av del pinar 1908', null, null, null, null, '0', '0'), ('232', '231', null, null, null, null, 'Bonampack 3307', null, null, null, null, '0', '0'), ('233', '232', null, null, null, null, 'Tortas Alex', null, null, null, null, '0', '0'), ('234', '233', null, null, null, null, 'av del pinar esquina con tajin', null, null, null, null, '0', '0'), ('235', '234', null, null, null, null, 'av del pinar esquina con tajin', null, null, null, null, '0', '0'), ('236', '235', null, null, null, null, 'av del pinar 3350', null, null, null, null, '0', '0'), ('237', '236', null, null, null, null, 'Peten 5921', null, null, null, null, '0', '0'), ('238', '237', null, null, null, null, 'av del pinar 3340', null, null, null, null, '0', '0'), ('239', '238', null, null, null, null, '', null, null, null, null, '0', '0'), ('240', '239', null, null, null, null, 'Tajin 5898 dep 6 3 piso', null, null, null, null, '0', '0'), ('241', '240', null, null, null, null, 'bonanpak 3424 int 3d', null, null, null, null, '0', '0'), ('242', '241', null, null, null, null, 'av del pinar', null, null, null, null, '0', '0'), ('243', '242', null, null, null, null, 'Aztlan 3335-113', null, null, null, null, '0', '0'), ('244', '243', null, null, null, null, '', null, null, null, null, '0', '0'), ('245', '244', null, null, null, null, '', null, null, null, null, '0', '0'), ('246', '245', null, null, null, null, 'Av. del Pinar 3223', null, null, null, null, '0', '0'), ('247', '246', null, null, null, null, '\"can mayor 3522', null, null, null, null, '0', '0'), ('248', '247', null, null, null, null, 'aztlan 1799', null, null, null, null, '0', '0'), ('249', '248', null, null, null, null, 'cancum 1835', null, null, null, null, '0', '0'), ('250', '249', null, null, null, null, '', null, null, null, null, '0', '0'), ('251', '250', null, null, null, null, 'av del pinar 24', null, null, null, null, '0', '0'), ('252', '251', null, null, null, null, 'Av. dle PInar 3340', null, null, null, null, '0', '0'), ('253', '252', null, null, null, null, 'sierra tapalpa 176-', null, null, null, null, '0', '0'), ('254', '253', null, null, null, null, 'Teotihuacan 3313', null, null, null, null, '0', '0'), ('255', '254', null, null, null, null, '', null, null, null, null, '0', '0'), ('256', '255', null, null, null, null, 'av valle de atemajac 2028', null, null, null, null, '0', '0'), ('257', '256', null, null, null, null, 'av del pinar 3340', null, null, null, null, '0', '0'), ('258', '257', null, null, null, null, 'ixtepete 1834', null, null, null, null, '0', '0'), ('259', '258', null, null, null, null, 'av del pinar 1621', null, null, null, null, '0', '0'), ('260', '259', null, null, null, null, 'rio lerma 1898', null, null, null, null, '0', '0'), ('261', '260', null, null, null, null, 'aztlan 3335', null, null, null, null, '0', '0'), ('262', '261', null, null, null, null, ' alejandro\"', null, null, null, null, '0', '0'), ('263', '262', null, null, null, null, 'Teotihuacan 1894', null, null, null, null, '0', '0'), ('264', '263', null, null, null, null, 'lapiz laszuli 3037', null, null, null, null, '0', '0'), ('265', '264', null, null, null, null, 'av del pinar 3381', null, null, null, null, '0', '0'), ('266', '265', null, null, null, null, 'ixtepete 3285 int a', null, null, null, null, '0', '0'), ('267', '266', null, null, null, null, 'sierra de mazamitla 5521', null, null, null, null, '0', '0'), ('268', '267', null, null, null, null, 'av del pinar 3447', null, null, null, null, '0', '0'), ('269', '268', null, null, null, null, 'Teotihuacan 3329', null, null, null, null, '0', '0'), ('270', '269', null, null, null, null, 'pinar 3032 int 1', null, null, null, null, '0', '0'), ('271', '270', null, null, null, null, 'rio atengo 2085', null, null, null, null, '0', '0'), ('272', '271', null, null, null, null, 'Tical 1458', null, null, null, null, '0', '0'), ('273', '272', null, null, null, null, 'Teotihuacan', null, null, null, null, '0', '0'), ('274', '273', null, null, null, null, '\"Obsidiana2747', null, null, null, null, '0', '0'), ('275', '274', null, null, null, null, 'Bonampack 1972', null, null, null, null, '0', '0'), ('276', '275', null, null, null, null, '', null, null, null, null, '0', '0'), ('277', '276', null, null, null, null, 'sochicalco 5961', null, null, null, null, '0', '0'), ('278', '277', null, null, null, null, '', null, null, null, null, '0', '0'), ('279', '278', null, null, null, null, 'ixtepete 3479', null, null, null, null, '0', '0'), ('280', '279', null, null, null, null, '', null, null, null, null, '0', '0'), ('281', '280', null, null, null, null, 'peten 5840', null, null, null, null, '0', '0'), ('282', '281', null, null, null, null, 'peten 5840', null, null, null, null, '0', '0'), ('283', '282', null, null, null, null, '', null, null, null, null, '0', '0'), ('284', '283', null, null, null, null, 'Teotihuacan 1666', null, null, null, null, '0', '0'), ('285', '284', null, null, null, null, 'cancun 3224', null, null, null, null, '0', '0'), ('286', '285', null, null, null, null, 'credomatik', null, null, null, null, '0', '0'), ('287', '286', null, null, null, null, 'teotiuhacan 1922', null, null, null, null, '0', '0'), ('288', '287', null, null, null, null, 'Sierra Mazamitla 5883', null, null, null, null, '0', '0'), ('289', '288', null, null, null, null, '', null, null, null, null, '0', '0'), ('290', '289', null, null, null, null, 'Colegio Becker', null, null, null, null, '0', '0'), ('291', '290', null, null, null, null, 'Auhetes 1740', null, null, null, null, '0', '0'), ('292', '291', null, null, null, null, 'Teotihuacan 1806', null, null, null, null, '0', '0'), ('293', '292', null, null, null, null, 'Av. del Pinar ', null, null, null, null, '0', '0'), ('294', '293', null, null, null, null, 'Senderos de monteverde 181', null, null, null, null, '0', '0'), ('295', '294', null, null, null, null, 'Av. dle PInar 3498 int. 8', null, null, null, null, '0', '0'), ('296', '295', null, null, null, null, 'Cancun Tienda', null, null, null, null, '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `AddressRoutes`
-- ----------------------------
DROP TABLE IF EXISTS `AddressRoutes`;
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

-- ----------------------------
--  Records of `AddressRoutes`
-- ----------------------------
BEGIN;
INSERT INTO `AddressRoutes` VALUES ('1', 'Mexico', 'Jalisco', '44540', 'Guadalajara', 'Calle', 'Colonia', 'no tiene timbre... ', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `Asset`
-- ----------------------------
DROP TABLE IF EXISTS `Asset`;
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

-- ----------------------------
--  Records of `Asset`
-- ----------------------------
BEGIN;
INSERT INTO `Asset` VALUES ('1', '1', '1', 'lavadora 1', 'lavadora roja', '0'), ('2', '1', '1', 'lavadora 2', 'lavadora roja rota', '0'), ('3', '3', '1', 'moto1', 'moto roja placas aaabbb92', '0');
COMMIT;

-- ----------------------------
--  Table structure for `AssetTaskOrder`
-- ----------------------------
DROP TABLE IF EXISTS `AssetTaskOrder`;
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

-- ----------------------------
--  Table structure for `AssetTaskService`
-- ----------------------------
DROP TABLE IF EXISTS `AssetTaskService`;
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

-- ----------------------------
--  Table structure for `AssetType`
-- ----------------------------
DROP TABLE IF EXISTS `AssetType`;
CREATE TABLE `AssetType` (
  `idAssetType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idAssetType`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `AssetType`
-- ----------------------------
BEGIN;
INSERT INTO `AssetType` VALUES ('1', 'lavado', null, '0'), ('2', 'planchado', null, '0'), ('3', 'transporte', null, '0');
COMMIT;

-- ----------------------------
--  Table structure for `BagType`
-- ----------------------------
DROP TABLE IF EXISTS `BagType`;
CREATE TABLE `BagType` (
  `idBagType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBagType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `BagType`
-- ----------------------------
BEGIN;
INSERT INTO `BagType` VALUES ('1', 'Chica', '5'), ('2', 'Grande', '10');
COMMIT;

-- ----------------------------
--  Table structure for `CalendarRoute`
-- ----------------------------
DROP TABLE IF EXISTS `CalendarRoute`;
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

-- ----------------------------
--  Records of `CalendarRoute`
-- ----------------------------
BEGIN;
INSERT INTO `CalendarRoute` VALUES ('1', '1', '9:30', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `ClientBags`
-- ----------------------------
DROP TABLE IF EXISTS `ClientBags`;
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

-- ----------------------------
--  Table structure for `ClientPaymentInfo`
-- ----------------------------
DROP TABLE IF EXISTS `ClientPaymentInfo`;
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

-- ----------------------------
--  Records of `ClientPaymentInfo`
-- ----------------------------
BEGIN;
INSERT INTO `ClientPaymentInfo` VALUES ('1', '1', '01234', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `ClientType`
-- ----------------------------
DROP TABLE IF EXISTS `ClientType`;
CREATE TABLE `ClientType` (
  `idClientType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idClientType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ClientType`
-- ----------------------------
BEGIN;
INSERT INTO `ClientType` VALUES ('1', 'Normal', 'Clientes normal'), ('2', 'Mayoreo', 'Clientes de Mayoreo'), ('3', 'Cliente Regular 1', 'Clientes Regular 1'), ('4', 'Cliente Regular 2', 'Clientes Regular 2');
COMMIT;

-- ----------------------------
--  Table structure for `Clients`
-- ----------------------------
DROP TABLE IF EXISTS `Clients`;
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
) ENGINE=InnoDB AUTO_INCREMENT=296 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Clients`
-- ----------------------------
BEGIN;
INSERT INTO `Clients` VALUES ('1', '1', 'email@domain.com', 'notused', 'Cliente', 'Apellido', 'twitter', '2017-02-16 15:18:15', '2017-02-16 09:19:07', '123', null, null, '0', '1111222333', null, null), ('2', '1', null, null, 'Nombre', '', null, '2017-02-24 18:17:30', null, null, null, null, '0', 'Telefono', null, null), ('3', '1', null, null, 'Aida', 'Ruiz', null, '2017-02-24 18:17:30', null, null, null, null, '0', '3313612762', null, null), ('4', '1', null, null, 'Aide', 'Elena', null, '2017-02-24 18:17:30', null, null, null, null, '0', '36313494', null, null), ('5', '1', null, null, 'Anahi', '', null, '2017-02-24 18:17:30', null, null, null, null, '0', '', null, null), ('6', '1', null, null, 'Angel', 'Gutierrez', null, '2017-02-24 18:17:30', null, null, null, null, '0', '31204063', null, null), ('7', '1', null, null, 'Angelica', 'huerta', null, '2017-02-24 18:17:30', null, null, null, null, '0', ' del pinar 3447\"', null, null), ('8', '1', null, null, 'Antonio', 'topete', null, '2017-02-24 18:17:30', null, null, null, null, '0', '36320337', null, null), ('9', '1', null, null, 'Arnulfo', '', null, '2017-02-24 18:17:30', null, null, null, null, '0', '3312859808', null, null), ('10', '1', null, null, 'Avig', '', null, '2017-02-24 18:17:30', null, null, null, null, '0', '3312227469', null, null), ('11', '1', null, null, 'Barben', 'shop', null, '2017-02-24 18:17:30', null, null, null, null, '0', '3317628487', null, null), ('12', '1', null, null, 'Benjamin', '', null, '2017-02-24 18:17:30', null, null, null, null, '0', '3418863925', null, null), ('13', '1', null, null, 'Benjamin', 'Bravo', null, '2017-02-24 18:17:30', null, null, null, null, '0', '31884038', null, null), ('14', '1', null, null, 'Berenice', 'Ramirez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3312482014', null, null), ('15', '1', null, null, 'Carlos', 'Huerta', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3631101', null, null), ('16', '1', null, null, 'Carmen', 'Chavez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36323262', null, null), ('17', '1', null, null, 'Daniela', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3331159620', null, null), ('18', '1', null, null, 'Delia', 'Nuno', null, '2017-02-24 18:17:31', null, null, null, null, '0', '19840612', null, null), ('19', '1', null, null, 'Dulce', 'Maria', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3338443360', null, null), ('20', '1', null, null, 'Elsa', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3312160856', null, null), ('21', '1', null, null, 'Elvia', 'Limon', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36312992', null, null), ('22', '1', null, null, 'Emilio', 'perez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3312310818', null, null), ('23', '1', null, null, 'Estela', 'Caballero', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3317193667', null, null), ('24', '1', null, null, 'Fernando', 'Avalos', null, '2017-02-24 18:17:31', null, null, null, null, '0', '35636380', null, null), ('25', '1', null, null, 'Gabi', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '311101020', null, null), ('26', '1', null, null, 'Hector', 'Rivera', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3313118811', null, null), ('27', '1', null, null, 'Silva', 'Ep94', null, '2017-02-24 18:17:31', null, null, null, null, '0', '19834905', null, null), ('28', '1', null, null, 'Laura', 'Navarete', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3315028487', null, null), ('29', '1', null, null, 'Laura', 'Pulido', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36341686', null, null), ('30', '1', null, null, 'Leticia', 'Sanchez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3331966018', null, null), ('31', '1', null, null, 'leticia', 'gonzalez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36320419', null, null), ('32', '1', null, null, 'Lizbeth', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3335075904', null, null), ('33', '1', null, null, 'Lourdes', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36468333', null, null), ('34', '1', null, null, 'Lourdes', 'Mora', null, '2017-02-24 18:17:31', null, null, null, null, '0', '331089347', null, null), ('35', '1', null, null, 'Lupita', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36328675', null, null), ('36', '1', null, null, 'ManuelChavez', 'Perez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3315293032', null, null), ('37', '1', null, null, 'Margarita', 'Gonzalez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3311949300', null, null), ('38', '1', null, null, 'Maria', 'Elena', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36323236', null, null), ('39', '1', null, null, 'Maria', 'Guadalupe', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3331047686', null, null), ('40', '1', null, null, 'Maria', 'Elena', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36315665', null, null), ('41', '1', null, null, 'Martha', 'Chavez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3311101020', null, null), ('42', '1', null, null, 'Nicolas', 'Bonneal', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36319792', null, null), ('43', '1', null, null, 'Pablo', 'Lopez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3315115680', null, null), ('44', '1', null, null, 'Pedro', 'Martinez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36311024', null, null), ('45', '1', null, null, 'Rita', 'Arreola', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36320406', null, null), ('46', '1', null, null, 'Roberto', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3315282254', null, null), ('47', '1', null, null, 'Rocio', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3310655042', null, null), ('48', '1', null, null, 'Rocio', 'Guerrero', null, '2017-02-24 18:17:31', null, null, null, null, '0', '2066870329', null, null), ('49', '1', null, null, 'Rodolfo', 'Gallegos', null, '2017-02-24 18:17:31', null, null, null, null, '0', '13805356', null, null), ('50', '1', null, null, 'Rosa', 'Hernandez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '20143088', null, null), ('51', '1', null, null, 'Rosario', 'Jaramillo', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36341137', null, null), ('52', '1', null, null, 'Sandra', 'Mejia', null, '2017-02-24 18:17:31', null, null, null, null, '0', '', null, null), ('53', '1', null, null, 'Teresa', 'Quezada', null, '2017-02-24 18:17:31', null, null, null, null, '0', ' del Pinar 1822\"', null, null), ('54', '1', null, null, 'Vania', 'Barrera', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3334812587', null, null), ('55', '1', null, null, 'Ronal', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3321735971', null, null), ('56', '1', null, null, 'Rosario', 'Garibay', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36318931', null, null), ('57', '1', null, null, 'maricela', 'Placencia', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36813770', null, null), ('58', '1', null, null, 'Claudia', 'ramirez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3322265741', null, null), ('59', '1', null, null, 'Martha', 'Santana', null, '2017-02-24 18:17:31', null, null, null, null, '0', '31331308', null, null), ('60', '1', null, null, 'cecilio', 'lechuga', null, '2017-02-24 18:17:31', null, null, null, null, '0', '', null, null), ('61', '1', null, null, 'MARIA', 'DEL', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36321706', null, null), ('62', '1', null, null, 'Estela', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', ' Paseos del Sol\"', null, null), ('63', '1', null, null, 'Carmen', 'murillo', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36325200', null, null), ('64', '1', null, null, 'maria', 'teresa', null, '2017-02-24 18:17:31', null, null, null, null, '0', '15811773', null, null), ('65', '1', null, null, 'Lic', 'Lorena', null, '2017-02-24 18:17:31', null, null, null, null, '0', '31351092', null, null), ('66', '1', null, null, 'Abel', 'castro', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3318079364', null, null), ('67', '1', null, null, 'Jorge', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3318931865', null, null), ('68', '1', null, null, 'herandy', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3318957534', null, null), ('69', '1', null, null, 'robert', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '', null, null), ('70', '1', null, null, 'Elvia', 'Limon', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36312992', null, null), ('71', '1', null, null, 'martha', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36322275', null, null), ('72', '1', null, null, 'Silvia', 'Flores', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3313264077', null, null), ('73', '1', null, null, 'silvia', 'alyve', null, '2017-02-24 18:17:31', null, null, null, null, '0', '96271672', null, null), ('74', '1', null, null, 'Rodolfo', 'Rodriguez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3312705060', null, null), ('75', '1', null, null, 'Adriana', 'Gallardo', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3339016115', null, null), ('76', '1', null, null, 'Alejandra', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', ' entre duque de Rivas y av. Vallarta\"', null, null), ('77', '1', null, null, 'Alejandra', 'Cardona', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3312705060', null, null), ('78', '1', null, null, 'Rosalba', 'Camarena', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36312813', null, null), ('79', '1', null, null, 'Angelica', 'Chacht', null, '2017-02-24 18:17:31', null, null, null, null, '0', '4421240242', null, null), ('80', '1', null, null, 'Jose', 'Luis', null, '2017-02-24 18:17:31', null, null, null, null, '0', '', null, null), ('81', '1', 'temporal1', null, 'Fernando', 'Flores', null, '2017-02-24 18:17:31', '2017-02-25 17:36:57', null, null, null, '0', '8182063145', null, null), ('82', '1', null, null, 'Norma', 'medina', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36312722', null, null), ('83', '1', null, null, 'Jose', 'Morales', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3311932000', null, null), ('84', '1', null, null, 'mari', 'paz', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36312741', null, null), ('85', '1', null, null, 'Gladis', 'palafox', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3314799837', null, null), ('86', '1', null, null, 'Lupita', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '39446921', null, null), ('87', '1', null, null, 'Maria', 'Guadalupe', null, '2017-02-24 18:17:31', null, null, null, null, '0', '39446921', null, null), ('88', '1', null, null, 'Erick', 'Gonzalez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '7222478789', null, null), ('89', '1', null, null, 'Yolanda', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', ' entre cuitzmala y santiago\"', null, null), ('90', '1', null, null, 'Dolores', 'Gomez', null, '2017-02-24 18:17:31', null, null, null, null, '0', ' int 12', null, null), ('91', '1', null, null, 'Elizabeth', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3319560199', null, null), ('92', '1', null, null, 'Carlos', 'cossio', null, '2017-02-24 18:17:31', null, null, null, null, '0', ' paseos del sol\"', null, null), ('93', '1', null, null, 'Elsa', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3312160856', null, null), ('94', '1', null, null, 'Sravan', 'Varanasi', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3339646504', null, null), ('95', '1', null, null, 'Ana', 'Gonzalez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '15813543', null, null), ('96', '1', null, null, 'Joel', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3314380020', null, null), ('97', '1', null, null, 'Joe', 'Gutierrez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3315548493', null, null), ('98', '1', null, null, 'Martha', 'Chavez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3311101020', null, null), ('99', '1', null, null, 'Marco', 'Zanudo', null, '2017-02-24 18:17:31', null, null, null, null, '0', '19840612', null, null), ('100', '1', null, null, 'Blanca', 'Pelayo', null, '2017-02-24 18:17:31', null, null, null, null, '0', '', null, null), ('101', '1', null, null, 'Ana', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '24007030', null, null), ('102', '1', null, null, 'Maria', 'asuncion', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36312262', null, null), ('103', '1', null, null, 'Jorge', 'Alvirde', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36323236', null, null), ('104', '1', null, null, 'Noe', 'Aquino', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36312144', null, null), ('105', '1', null, null, 'Cecilia', 'Becerra', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3310976924', null, null), ('106', '1', null, null, 'coisa', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36325121', null, null), ('107', '1', null, null, 'martha', 'gallegos', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36311790', null, null), ('108', '1', null, null, 'carolina', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', ' Gavilanes\"', null, null), ('109', '1', null, null, 'MARIA', 'ELENA', null, '2017-02-24 18:17:31', null, null, null, null, '0', '', null, null), ('110', '1', null, null, 'catalina', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36340007', null, null), ('111', '1', null, null, 'Miguel', 'Pantoja', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36314818', null, null), ('112', '1', null, null, 'mariana', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3331494855', null, null), ('113', '1', null, null, 'rosa', 'hernandez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '20143088', null, null), ('114', '1', null, null, 'Rosa', 'maria', null, '2017-02-24 18:17:31', null, null, null, null, '0', '31331209', null, null), ('115', '1', null, null, 'Pamela', 'Paz', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3317434183', null, null), ('116', '1', null, null, 'Bety', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3310750525', null, null), ('117', '1', null, null, 'Julieta', 'Sanchez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3314860833', null, null), ('118', '1', null, null, 'marco', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '2293313394', null, null), ('119', '1', null, null, 'Ivan', 'Lopez', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3315992618', null, null), ('120', '1', null, null, 'Alvaro', 'Chimal', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36312226', null, null), ('121', '1', null, null, 'Lili', 'Alvarado', null, '2017-02-24 18:17:31', null, null, null, null, '0', '4521010515', null, null), ('122', '1', null, null, 'paola', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '4371034642', null, null), ('123', '1', null, null, 'Lupita', 'Barragan', null, '2017-02-24 18:17:31', null, null, null, null, '0', '', null, null), ('124', '1', null, null, 'Javier', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3322209516', null, null), ('125', '1', null, null, 'Sergio', 'Quintero', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3322612839', null, null), ('126', '1', null, null, 'cecilia', 'wuilian', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3334400938', null, null), ('127', '1', null, null, 'Eduardo', 'Alain', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3921088569', null, null), ('128', '1', null, null, 'Angel', '', null, '2017-02-24 18:17:31', null, null, null, null, '0', '36317003', null, null), ('129', '1', null, null, 'Rosario', 'Giles', null, '2017-02-24 18:17:31', null, null, null, null, '0', '3315111331', null, null), ('130', '1', null, null, 'Luz', 'Maria', null, '2017-02-24 18:17:31', null, null, null, null, '0', '6441346257', null, null), ('131', '1', null, null, 'pablo', 'Arrieta', null, '2017-02-24 18:17:31', null, null, null, null, '0', '11178137', null, null), ('132', '1', null, null, 'guillermina', 'rosales', null, '2017-02-24 18:17:32', null, null, null, null, '0', '15811016', null, null), ('133', '1', null, null, 'Raj', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '33221927', null, null), ('134', '1', null, null, 'Vianey', 'Navarrete', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3331662530', null, null), ('135', '1', null, null, 'carolina', 'aguilar', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3335963470', null, null), ('136', '1', null, null, 'Axel', 'Nuniez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '5531194748', null, null), ('137', '1', null, null, 'Elizabeth', 'Rios', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3334530414', null, null), ('138', '1', null, null, 'ana', 'valles', null, '2017-02-24 18:17:32', null, null, null, null, '0', '31350022', null, null), ('139', '1', null, null, 'rafael', 'roman', null, '2017-02-24 18:17:32', null, null, null, null, '0', '39446923', null, null), ('140', '1', null, null, 'Coni', 'Sanchez', null, '2017-02-24 18:17:32', null, null, null, null, '0', ' del pinar 3253\"', null, null), ('141', '1', null, null, 'Guillermo', 'Vecino', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('142', '1', null, null, 'alfonso', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '5585508289', null, null), ('143', '1', null, null, 'jorge', 'Rodriguez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3335762203', null, null), ('144', '1', null, null, 'Hugo', 'Quirarte', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3331566757', null, null), ('145', '1', null, null, 'Rosario', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36314496', null, null), ('146', '1', null, null, 'Maria', 'Luisa', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36346799', null, null), ('147', '1', null, null, 'Ana', 'Aceves', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3310954693', null, null), ('148', '1', null, null, 'victor', 'montanez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3314413919', null, null), ('149', '1', null, null, 'roberto', 'chacon', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3314359420', null, null), ('150', '1', null, null, 'Samuel', 'Leyva', null, '2017-02-24 18:17:32', null, null, null, null, '0', '16666926', null, null), ('151', '1', null, null, 'Susana', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36327658', null, null), ('152', '1', null, null, 'Lourdes', 'Lechuga', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('153', '1', null, null, 'jorge', 'luis', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36323262', null, null), ('154', '1', null, null, 'Cecilia', 'a', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3317740706', null, null), ('155', '1', null, null, 'Diego', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', ' Alamo Industrial\"', null, null), ('156', '1', null, null, 'charly', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3321061418', null, null), ('157', '1', null, null, 'Rodney', 'Anthony', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3312120018', null, null), ('158', '1', null, null, 'guillermo', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3313111016', null, null), ('159', '1', null, null, 'Alejandra', 'Perez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3317814240', null, null), ('160', '1', null, null, 'Sara', 'Sabido', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3312416956', null, null), ('161', '1', null, null, 'B-Box', 'Securyti', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3322613050', null, null), ('162', '1', null, null, 'Maribel', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3313183601', null, null), ('163', '1', null, null, 'Nidia', 'Chavez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3331379811', null, null), ('164', '1', null, null, 'SECURITY', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36155301', null, null), ('165', '1', null, null, 'juan', 'carlos', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3310384254', null, null), ('166', '1', null, null, 'Brenda', 'Salas', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3311083137', null, null), ('167', '1', null, null, 'Pinal', 'y', null, '2017-02-24 18:17:32', null, null, null, null, '0', '31336090', null, null), ('168', '1', null, null, 'Asociacion', 'del', null, '2017-02-24 18:17:32', null, null, null, null, '0', '(33)31-35-10-92', null, null), ('169', '1', null, null, 'Isela', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '6242247499', null, null), ('170', '1', null, null, 'Diana', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '6242247499', null, null), ('171', '1', null, null, 'Samuel', 'Nava', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3221175698', null, null), ('172', '1', null, null, 'Martha', 'Gonzalez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36310556', null, null), ('173', '1', null, null, 'Gloria', 'Villasenor', null, '2017-02-24 18:17:32', null, null, null, null, '0', ' entre diamante y barlovento\"', null, null), ('174', '1', null, null, 'Cristina', 'Nuño', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36322915', null, null), ('175', '1', null, null, 'Pinal', 'y', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('176', '1', null, null, 'qute', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '31336090', null, null), ('177', '1', null, null, 'Carola', 'Madero', null, '2017-02-24 18:17:32', null, null, null, null, '0', '6441267979', null, null), ('178', '1', null, null, 'Concepcion', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3334975640', null, null), ('179', '1', null, null, 'Cluadia', 'Ramirez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36312226', null, null), ('180', '1', null, null, 'Emmanuel', 'Ramirez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3411620055', null, null), ('181', '1', null, null, 'Hilda', 'Guzman', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36319301', null, null), ('182', '1', null, null, 'Eduardo', 'Dominguez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3921088569', null, null), ('183', '1', null, null, 'Cristina', 'Becerra', null, '2017-02-24 18:17:32', null, null, null, null, '0', '31335170', null, null), ('184', '1', null, null, 'Walter', 'Pinal', null, '2017-02-24 18:17:32', null, null, null, null, '0', '8117184635', null, null), ('185', '1', null, null, 'cesar', 'Rivera', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3336625357', null, null), ('186', '1', null, null, 'Leo', 'Preciado', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36311011', null, null), ('187', '1', null, null, 'ivan', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3334685288', null, null), ('188', '1', null, null, 'Carlos', 'Ruiz', null, '2017-02-24 18:17:32', null, null, null, null, '0', '8331409194', null, null), ('189', '1', null, null, 'elia', 'contreras', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36311719', null, null), ('190', '1', null, null, 'nancy', 'arias', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3310201592', null, null), ('191', '1', null, null, 'Angeles', 'Vazquez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '33446862', null, null), ('192', '1', null, null, 'Lorena', 'vazquez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3315195865', null, null), ('193', '1', null, null, 'Rauf', 'Hamden', null, '2017-02-24 18:17:32', null, null, null, null, '0', '15811773', null, null), ('194', '1', null, null, 'Alejandro', 'Aguilera', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3311937312', null, null), ('195', '1', null, null, 'enrique', 'moncayo', null, '2017-02-24 18:17:32', null, null, null, null, '0', '31338415', null, null), ('196', '1', null, null, 'juan', 'moreno', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36322992', null, null), ('197', '1', null, null, 'yolanda', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '15420620', null, null), ('198', '1', null, null, 'Isabela', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3318014067', null, null), ('199', '1', null, null, 'Sandra', 'Moncayo', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('200', '1', null, null, 'sonia', 'sandobal', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36314532', null, null), ('201', '1', null, null, 'margarita', 'vega', null, '2017-02-24 18:17:32', null, null, null, null, '0', '23020824', null, null), ('202', '1', null, null, 'lizeth', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3338095321', null, null), ('203', '1', null, null, 'luceli', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('204', '1', null, null, 'manish', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3316310237', null, null), ('205', '1', null, null, 'alfredo', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3312691510', null, null), ('206', '1', null, null, 'Supercito', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('207', '1', null, null, 'gabriela', 'tejeda', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3311937203', null, null), ('208', '1', null, null, 'eusebio', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3339558631', null, null), ('209', '1', null, null, 'Silvia', 'Rosales', null, '2017-02-24 18:17:32', null, null, null, null, '0', '18118566', null, null), ('210', '1', null, null, 'Olga', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('211', '1', null, null, 'Alejandra', 'Arrieta', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3318208235', null, null), ('212', '1', null, null, 'Laura', 'Gomez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3338094645', null, null), ('213', '1', null, null, 'Aldo', 'Caltelum', null, '2017-02-24 18:17:32', null, null, null, null, '0', ' tajin\"', null, null), ('214', '1', null, null, 'Uma', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('215', '1', null, null, 'Gonzalo', 'Diaz', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('216', '1', null, null, 'Berny', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36318116', null, null), ('217', '1', null, null, 'Eduardo', 'perez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3313598761', null, null), ('218', '1', null, null, 'Monica', 'Barrigo', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3333590091', null, null), ('219', '1', null, null, 'Karla', 'Flores', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36322992', null, null), ('220', '1', null, null, 'Irlanda', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3310204453', null, null), ('221', '1', null, null, 'mario', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36323161', null, null), ('222', '1', null, null, 'Jorge', 'Rodriguez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3335762203', null, null), ('223', '1', null, null, 'Alberto', 'Preciado', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36311011', null, null), ('224', '1', null, null, 'Jordi', 'Acosto', null, '2017-02-24 18:17:32', null, null, null, null, '0', '33 2134 7127', null, null), ('225', '1', null, null, 'julio', 'silva', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3332012719', null, null), ('226', '1', null, null, 'Adriana', 'Rodriguez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36322790', null, null), ('227', '1', null, null, 'claudia', 'Parra', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36312226', null, null), ('228', '1', null, null, 'felipe', 'romero', null, '2017-02-24 18:17:32', null, null, null, null, '0', '5539272921', null, null), ('229', '1', null, null, 'Senora', 'Villa', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36311830', null, null), ('230', '1', null, null, 'susana', 'palacios', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3313994878', null, null), ('231', '1', null, null, 'Ismael', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3310802839', null, null), ('232', '1', null, null, 'Juan', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36328085', null, null), ('233', '1', null, null, 'manuel', 'verdugo', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3315479629', null, null), ('234', '1', null, null, 'juan', 'iglesias', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3334861684', null, null), ('235', '1', null, null, 'norma', 'adame', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3331063907', null, null), ('236', '1', null, null, 'Pina', 'Navarro', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('237', '1', null, null, 'rodrigo', 'viramontes', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36311846', null, null), ('238', '1', null, null, 'Buena', 'Mesa', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('239', '1', null, null, 'Ivan', 'mariscal', null, '2017-02-24 18:17:32', null, null, null, null, '0', '6441175280', null, null), ('240', '1', null, null, 'paulina', 'alcala', null, '2017-02-24 18:17:32', null, null, null, null, '0', '4371031030', null, null), ('241', '1', null, null, 'Rodolfo', 'Gonzalez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '6441010893', null, null), ('242', '1', null, null, 'Maria', 'Macias', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3314242134', null, null), ('243', '1', null, null, 'efren', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3321562516', null, null), ('244', '1', null, null, 'Tomas', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('245', '1', null, null, 'Jaime', 'Rodriguez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36325121', null, null), ('246', '1', null, null, 'Sandy', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', ' entre carnero y pedro simon laplace\"', null, null), ('247', '1', null, null, 'lupita', 'ruvalcava', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3311080695', null, null), ('248', '1', null, null, 'lore', 'buen', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36346446', null, null), ('249', '1', null, null, 'habram', 'aguirre', null, '2017-02-24 18:17:32', null, null, null, null, '0', '6691370274', null, null), ('250', '1', null, null, 'pablo', 'cameruta', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3331007611', null, null), ('251', '1', null, null, 'Andrea', 'Escobar', null, '2017-02-24 18:17:32', null, null, null, null, '0', '4473949978', null, null), ('252', '1', null, null, 'julio', 'cesar', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3310151562', null, null), ('253', '1', null, null, 'Rosa', 'Maria', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36348461', null, null), ('254', '1', null, null, 'Daniela', 'Gutierrez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('255', '1', null, null, 'Esteban', 'Barbosa', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36316561', null, null), ('256', '1', null, null, 'sara', 'gomez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3322021521', null, null), ('257', '1', null, null, 'anabel', 'mora', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3318898012', null, null), ('258', '1', null, null, 'alma', 'delia', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('259', '1', null, null, 'luciano', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '8332389287', null, null), ('260', '1', null, null, 'valeria', 'perez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3310384254', null, null), ('261', '1', null, null, '\"Barbento', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', 'Cruz del sur 4508 \'B', null, null), ('262', '1', null, null, 'Guadalupe', 'Fernandez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36311888', null, null), ('263', '1', null, null, 'yamile', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3321160625', null, null), ('264', '1', null, null, 'esthela', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3317444777', null, null), ('265', '1', null, null, 'nicol', 'madera', null, '2017-02-24 18:17:32', null, null, null, null, '0', '6441389606', null, null), ('266', '1', null, null, 'jesus', 'diaz', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3334538156', null, null), ('267', '1', null, null, 'señor', 'alberto', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36311011', null, null), ('268', '1', null, null, 'Jose', 'Rodriguez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('269', '1', null, null, 'Norma', 'lamadrid', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36327222', null, null), ('270', '1', null, null, 'german', 'marquez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36312381', null, null), ('271', '1', null, null, 'Samanta', 'Castelo', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3322627190', null, null), ('272', '1', null, null, 'Nataly', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3335997760', null, null), ('273', '1', null, null, 'Araceli', 'mercado', null, '2017-02-24 18:17:32', null, null, null, null, '0', ' entre Onix y Turqueza\"', null, null), ('274', '1', null, null, 'Anabel', 'Nolasco', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3338141561', null, null), ('275', '1', null, null, 'gabriela', 'ruiz', null, '2017-02-24 18:17:32', null, null, null, null, '0', '4561124347', null, null), ('276', '1', null, null, 'gonzalo', 'diaz', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36341337', null, null), ('277', '1', null, null, 'maria', 'elena', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36310727', null, null), ('278', '1', null, null, 'marisol', 'rivera', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3312912323', null, null), ('279', '1', null, null, 'miguel', 'gonzalez', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('280', '1', null, null, 'emanuel', 'salgado', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3332006230', null, null), ('281', '1', null, null, 'carlos', 'contreras', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3314142050', null, null), ('282', '1', null, null, 'Gerardo', '', null, '2017-02-24 18:17:32', null, null, null, null, '0', '', null, null), ('283', '1', null, null, 'Alicia', 'Espinoza', null, '2017-02-24 18:17:32', null, null, null, null, '0', '36327367', null, null), ('284', '1', null, null, 'leopoldo', 'silva', null, '2017-02-24 18:17:32', null, null, null, null, '0', '3310627216', null, null), ('285', '1', null, null, 'laura', 'placencia', null, '2017-02-24 18:17:33', null, null, null, null, '0', '', null, null), ('286', '1', null, null, 'maria', 'teresa', null, '2017-02-24 18:17:33', null, null, null, null, '0', '15811773', null, null), ('287', '1', null, null, 'Miguel', 'Angel', null, '2017-02-24 18:17:33', null, null, null, null, '0', '3318273896', null, null), ('288', '1', null, null, 'Omar', '', null, '2017-02-24 18:17:33', null, null, null, null, '0', '', null, null), ('289', '1', null, null, '', 'Maestra', null, '2017-02-24 18:17:33', null, null, null, null, '0', '', null, null), ('290', '1', null, null, 'Diego', 'Rodriguez', null, '2017-02-24 18:17:33', null, null, null, null, '0', '3339562034', null, null), ('291', '1', null, null, 'Paty', '', null, '2017-02-24 18:17:33', null, null, null, null, '0', '3314764177', null, null), ('292', '1', null, null, 'Viswa', '', null, '2017-02-24 18:17:33', null, null, null, null, '0', '3321107046', null, null), ('293', '1', null, null, 'Eduardo', 'Duran', null, '2017-02-24 18:17:33', null, null, null, null, '0', '3331050314', null, null), ('294', '1', null, null, 'Manoj', '', null, '2017-02-24 18:17:33', null, null, null, null, '0', '3339500734', null, null), ('295', '1', null, null, 'Sarai', '', null, '2017-02-24 18:17:33', null, null, null, null, '0', '', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `DistanceInfo`
-- ----------------------------
DROP TABLE IF EXISTS `DistanceInfo`;
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

-- ----------------------------
--  Records of `DistanceInfo`
-- ----------------------------
BEGIN;
INSERT INTO `DistanceInfo` VALUES ('1', '3', '0', '1', '1'), ('2', '6', '0', '1', '1'), ('3', '12', '10', '1', '1'), ('4', '24', '20', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `Employee`
-- ----------------------------
DROP TABLE IF EXISTS `Employee`;
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

-- ----------------------------
--  Records of `Employee`
-- ----------------------------
BEGIN;
INSERT INTO `Employee` VALUES ('1', '1', '1', 'user', 'user', null, 'user', '2016-12-30 13:09:05', null, 'email@domain.com', '0');
COMMIT;

-- ----------------------------
--  Table structure for `EmployeeTaskOrder`
-- ----------------------------
DROP TABLE IF EXISTS `EmployeeTaskOrder`;
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

-- ----------------------------
--  Table structure for `EmployeeTaskService`
-- ----------------------------
DROP TABLE IF EXISTS `EmployeeTaskService`;
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

-- ----------------------------
--  Table structure for `EmployeeType`
-- ----------------------------
DROP TABLE IF EXISTS `EmployeeType`;
CREATE TABLE `EmployeeType` (
  `idEmployeeType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEmployeeType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `EmployeeType`
-- ----------------------------
BEGIN;
INSERT INTO `EmployeeType` VALUES ('1', 'admin', 'Administradores', '0'), ('2', 'general', 'Empleados Nivel 2', '0');
COMMIT;

-- ----------------------------
--  Table structure for `Menu`
-- ----------------------------
DROP TABLE IF EXISTS `Menu`;
CREATE TABLE `Menu` (
  `idMenu` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `accessLevel` int(11) DEFAULT '1' COMMENT '0 = none\n1 = admin \n2 = not so admin\n3 = less admin than 2\n',
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMenu`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Menu`
-- ----------------------------
BEGIN;
INSERT INTO `Menu` VALUES ('1', 'client.all', 'Clientes', '2', '1'), ('2', 'routes.all', 'Rutas', '0', '2'), ('3', 'tasks.taskMenu', 'Tareas', '1', '5'), ('4', 'specs.specMenu', 'Specs', '1', '6'), ('5', 'employees.employeeMenu', 'Empleados', '1', '4'), ('6', 'assets.assetMenu', 'Activos', '0', '3'), ('7', 'supplies.supplyMenu', 'Consumibles', '1', '7'), ('8', 'services.serviceMenu', 'Servicios', '1', '8'), ('9', 'orders.orderMenu', 'Orders', '1', '9'), ('10', 'products.productMenu', 'Productos', '1', '10');
COMMIT;

-- ----------------------------
--  Table structure for `OrderPromotion`
-- ----------------------------
DROP TABLE IF EXISTS `OrderPromotion`;
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

-- ----------------------------
--  Table structure for `OrderTask`
-- ----------------------------
DROP TABLE IF EXISTS `OrderTask`;
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

-- ----------------------------
--  Table structure for `OrderType`
-- ----------------------------
DROP TABLE IF EXISTS `OrderType`;
CREATE TABLE `OrderType` (
  `idOrderType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `transportInfo` int(11) DEFAULT '0' COMMENT '0 = none; \n1 = show pick up\n2 = show deliver \n3 = show both.',
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`idOrderType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `OrderType`
-- ----------------------------
BEGIN;
INSERT INTO `OrderType` VALUES ('1', 'Completa', 'Pickup + service + deliver', '3', '0'), ('2', 'Recoleccion', 'Pickup + service', '1', '0'), ('3', 'Entrega', 'Service + deliver', '2', '0'), ('4', 'Encargo', 'Service', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `OrderTypeTask`
-- ----------------------------
DROP TABLE IF EXISTS `OrderTypeTask`;
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

-- ----------------------------
--  Records of `OrderTypeTask`
-- ----------------------------
BEGIN;
INSERT INTO `OrderTypeTask` VALUES ('1', '1', '3', '1'), ('2', '1', '1', '2'), ('3', '1', '4', '3'), ('5', '2', '3', '0');
COMMIT;

-- ----------------------------
--  Table structure for `Orders`
-- ----------------------------
DROP TABLE IF EXISTS `Orders`;
CREATE TABLE `Orders` (
  `idOrder` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idClient` int(10) unsigned NOT NULL,
  `idOrderType` int(10) unsigned NOT NULL,
  `idAddressPickup` int(11) DEFAULT NULL COMMENT 'Not froreing key ',
  `pickUpDate` datetime DEFAULT NULL,
  `pickUpPrice` double DEFAULT NULL,
  `idAddressDeliver` int(11) DEFAULT NULL,
  `deliverDate` datetime DEFAULT NULL,
  `deliverPrice` double DEFAULT NULL,
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

-- ----------------------------
--  Table structure for `PaymentInfo`
-- ----------------------------
DROP TABLE IF EXISTS `PaymentInfo`;
CREATE TABLE `PaymentInfo` (
  `idPaymentInfo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idOrder` int(10) unsigned NOT NULL,
  `type` int(11) NOT NULL COMMENT '0=cash\n1=stripe\n2=square\n',
  `transactionInfo` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idPaymentInfo`),
  KEY `fk_PaymentInfo_Orders1_idx` (`idOrder`),
  CONSTRAINT `fk_PaymentInfo_Orders1` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`idOrder`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `Product`
-- ----------------------------
DROP TABLE IF EXISTS `Product`;
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Product`
-- ----------------------------
BEGIN;
INSERT INTO `Product` VALUES ('1', '1', 'Sabanas', '2.7', '0', '0'), ('2', '1', 'Toalla Chica', '0.9', '0', '0'), ('3', '1', 'Toalla Grande', '2.34', '0', '0'), ('4', '1', 'Toalla Mediana', '1.08', '0', '0'), ('5', '4', 'Kilogramo', '13.6', '0', '0'), ('6', '4', '1-5 Kg', '68', '0', '0'), ('7', '4', '5.1-10 Kg', '130', '0', '0'), ('8', '6', 'Por Lavadora', '50', '0', '0'), ('9', '6', 'Cobija', '65', '0', '0'), ('10', '6', 'Edredon', '65', '0', '0'), ('11', '7', 'Pantalon', '45', '0', '0'), ('12', '7', 'Traje', '88', '0', '0'), ('13', '4', 'Kilogramo Mayoreo', '13', '0', '0'), ('14', '4', '10.1-15 Kg', '196.5', '0', '1'), ('15', '4', '15.1-20 Kg', '271.5', '0', '1'), ('16', '1', '20.1-25', '346.5', '0', '1'), ('17', '4', 'Prenda', '7', '0', '0'), ('18', '6', 'Colcha', '55', '0', '0'), ('19', '1', 'Trapo', '0.9', '0', '0'), ('20', '1', 'Funda', '0.9', '0', '0'), ('21', '1', 'Bata Banio', '2.88', '0', '0'), ('22', '1', 'Cobertor', '9.54', '0', '0'), ('23', '1', 'Bandas', '0.9', '0', '0'), ('24', '1', 'Tapete', '2.7', '0', '0'), ('25', '1', 'Cubre Colchon', '2.7', '0', '0'), ('26', '1', 'Uniformes', '2.88', '0', '0'), ('27', '1', 'Capas', '0.9', '0', '0'), ('28', '1', 'Careta', '1.08', '0', '0'), ('29', '1', 'Vendas', '1.08', '0', '0'), ('30', '3', 'Toalla Mediana', '3', '0', '0'), ('31', '3', 'Capas', '2', '0', '0'), ('32', '3', 'Bandas', '1', '0', '0'), ('33', '3', 'Batas', '3.5', '0', '0'), ('34', '3', 'Mandiles', '3.5', '0', '0'), ('35', '5', 'Docena', '60', '0', '0'), ('36', '5', 'Media docena', '35', '0', '0'), ('37', '5', 'Pieza', '7', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `ProductType`
-- ----------------------------
DROP TABLE IF EXISTS `ProductType`;
CREATE TABLE `ProductType` (
  `idProductType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idProductType`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ProductType`
-- ----------------------------
BEGIN;
INSERT INTO `ProductType` VALUES ('1', 'Ocho16 Spa', 'Productos para el ocho16 spa', '0'), ('2', 'Esteticas', 'Varias Esteticas', '1'), ('3', 'Estetica', 'Estetica varias', '0'), ('4', 'Lavado', 'Productos de Lavado general', '0'), ('5', 'Planchado', 'Productos de Planchado general', '0'), ('6', 'Lavado de Blancos', 'Productos de lavado de blancos', '0'), ('7', 'Tintoreria', 'productos de tintoreria', '0');
COMMIT;

-- ----------------------------
--  Table structure for `Promotion`
-- ----------------------------
DROP TABLE IF EXISTS `Promotion`;
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

-- ----------------------------
--  Records of `Promotion`
-- ----------------------------
BEGIN;
INSERT INTO `Promotion` VALUES ('1', '1', 'Buen Fin', 'Promocion por el buen fin', '2016-01-01 00:00:00', '2017-01-01 00:00:00', '1', '10', null, '1', null, '0', '2', '0'), ('2', '3', 'Compra de $120', 'Compra de $120', null, null, '1', '20', null, '1', null, '0', '1', '0'), ('3', '4', 'Compra No. #', 'Compra no #. ', null, null, '1', '60', null, '1', null, '0', '2', '0'), ('4', '5', 'Cliente Frecuente', 'Descuento por cliente frecuente', null, null, '1', '10', null, '1', null, '0', '1', '0');
COMMIT;

-- ----------------------------
--  Table structure for `PromotionType`
-- ----------------------------
DROP TABLE IF EXISTS `PromotionType`;
CREATE TABLE `PromotionType` (
  `idPromotionType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT '0',
  PRIMARY KEY (`idPromotionType`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `PromotionType`
-- ----------------------------
BEGIN;
INSERT INTO `PromotionType` VALUES ('1', 'Periodo de tiempo', 'Descuentos entre fechas', '0'), ('2', 'Productos o Servicios [NA]', 'Al agregar uno o alguna combinacio de producto o servicio', '0'), ('3', 'Cantidad', 'Al llegar a una cantidad economica en una orden', '0'), ('4', 'Cantidad acumulativa en periodo', 'Al llegar a una cantidad acumulativa por periodo', '0'), ('5', 'Tipo de Client', 'Por tipo de clientes', '0'), ('6', 'Numero de ordenes por cliente', 'Al llegar a una cantidad de ordenes por cliente', '0');
COMMIT;

-- ----------------------------
--  Table structure for `Routes`
-- ----------------------------
DROP TABLE IF EXISTS `Routes`;
CREATE TABLE `Routes` (
  `idRoutes` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(105) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `category` int(11) DEFAULT '1' COMMENT '1 = departamentos\n2 = offices\n3 = cliente\n',
  PRIMARY KEY (`idRoutes`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Routes`
-- ----------------------------
BEGIN;
INSERT INTO `Routes` VALUES ('1', 'route1', 'route 1 ', '1');
COMMIT;

-- ----------------------------
--  Table structure for `Service`
-- ----------------------------
DROP TABLE IF EXISTS `Service`;
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

-- ----------------------------
--  Table structure for `ServiceCategory`
-- ----------------------------
DROP TABLE IF EXISTS `ServiceCategory`;
CREATE TABLE `ServiceCategory` (
  `idServiceCategory` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idServiceCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ServiceCategory`
-- ----------------------------
BEGIN;
INSERT INTO `ServiceCategory` VALUES ('1', 'Lavado', 'Servicios de Lavado Clientes'), ('2', 'Planchado', 'Planchado'), ('3', 'Tintoreria', 'Tintoreria varios'), ('4', 'Costura', 'Costura'), ('5', 'Mayoreao Spa', 'Servicio de mayoreo para SPAs'), ('6', 'Clientes Mayoreo', 'Clientes Mayoreo');
COMMIT;

-- ----------------------------
--  Table structure for `ServiceComments`
-- ----------------------------
DROP TABLE IF EXISTS `ServiceComments`;
CREATE TABLE `ServiceComments` (
  `idServiceComments` int(11) NOT NULL,
  `idService` int(10) unsigned NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idServiceComments`),
  KEY `fk_ServiceComments_Service1_idx` (`idService`),
  CONSTRAINT `fk_ServiceComments_Service1` FOREIGN KEY (`idService`) REFERENCES `Service` (`idService`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ServiceProducts`
-- ----------------------------
DROP TABLE IF EXISTS `ServiceProducts`;
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

-- ----------------------------
--  Table structure for `ServiceSpecs`
-- ----------------------------
DROP TABLE IF EXISTS `ServiceSpecs`;
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

-- ----------------------------
--  Table structure for `ServiceTask`
-- ----------------------------
DROP TABLE IF EXISTS `ServiceTask`;
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

-- ----------------------------
--  Table structure for `ServiceType`
-- ----------------------------
DROP TABLE IF EXISTS `ServiceType`;
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

-- ----------------------------
--  Records of `ServiceType`
-- ----------------------------
BEGIN;
INSERT INTO `ServiceType` VALUES ('1', 'Lavado General', 'Ropa general', '0', '45', '1', '1'), ('2', 'Lavado Blancos', 'Roba de cama', '0', '120', '1', '1'), ('3', 'Planchado', 'Planchado', '0', '60', '2', '0'), ('4', 'Costura', 'Servicio de costura', '0', '20', '2', '0'), ('5', 'Ocho16 Spa', 'Ocho16 Spa', '0', '180', '5', '0'), ('6', 'Mayoreo esteticas', 'Mayoreo de esteticas', '0', '120', '6', '0'), ('7', 'Tintoreria', 'Tintoreria general', '0', '0', '3', '0');
COMMIT;

-- ----------------------------
--  Table structure for `ServiceTypeProductType`
-- ----------------------------
DROP TABLE IF EXISTS `ServiceTypeProductType`;
CREATE TABLE `ServiceTypeProductType` (
  `idServiceType` int(10) unsigned NOT NULL,
  `idProductType` int(10) unsigned NOT NULL,
  KEY `fk_ServiceTypeSubproductType_ServiceType1_idx` (`idServiceType`),
  KEY `fk_ServiceTypeSubproductType_SubproductType1_idx` (`idProductType`),
  CONSTRAINT `fk_ServiceTypeSubproductType_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeSubproductType_SubproductType1` FOREIGN KEY (`idProductType`) REFERENCES `ProductType` (`idProductType`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ServiceTypeProductType`
-- ----------------------------
BEGIN;
INSERT INTO `ServiceTypeProductType` VALUES ('4', '5'), ('3', '5'), ('5', '1'), ('1', '4'), ('2', '6'), ('6', '3'), ('7', '7');
COMMIT;

-- ----------------------------
--  Table structure for `ServiceTypeSpecs`
-- ----------------------------
DROP TABLE IF EXISTS `ServiceTypeSpecs`;
CREATE TABLE `ServiceTypeSpecs` (
  `idServiceType` int(10) unsigned NOT NULL,
  `idSpecs` int(10) unsigned NOT NULL,
  KEY `fk_ServiceTypeSpecs_Specs1_idx` (`idSpecs`),
  KEY `fk_ServiceTypeSpecs_ServiceType1_idx` (`idServiceType`),
  CONSTRAINT `fk_ServiceTypeSpecs_ServiceType1` FOREIGN KEY (`idServiceType`) REFERENCES `ServiceType` (`idServiceType`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeSpecs_Specs1` FOREIGN KEY (`idSpecs`) REFERENCES `Specs` (`idSpecs`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ServiceTypeSpecs`
-- ----------------------------
BEGIN;
INSERT INTO `ServiceTypeSpecs` VALUES ('1', '1'), ('1', '2'), ('2', '1'), ('2', '2'), ('3', '5'), ('5', '2'), ('5', '3'), ('5', '4'), ('1', '4'), ('7', '6'), ('2', '7'), ('2', '4'), ('2', '8'), ('2', '9'), ('2', '3'), ('1', '7'), ('1', '3'), ('1', '8'), ('1', '9'), ('5', '7'), ('5', '9'), ('3', '8'), ('4', '8'), ('6', '7'), ('6', '1'), ('6', '2'), ('6', '3'), ('6', '4'), ('6', '8'), ('6', '9');
COMMIT;

-- ----------------------------
--  Table structure for `ServiceTypeTask`
-- ----------------------------
DROP TABLE IF EXISTS `ServiceTypeTask`;
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `ServiceTypeTask`
-- ----------------------------
BEGIN;
INSERT INTO `ServiceTypeTask` VALUES ('1', '1', '2', '1', '10'), ('2', '1', '5', '2', '10'), ('3', '2', '2', '1', '10'), ('4', '2', '5', '2', '10'), ('12', '7', '8', '0', '0'), ('13', '7', '9', '1', '0'), ('14', '3', '6', '0', '0'), ('15', '4', '11', '1', '0'), ('16', '4', '10', '0', '0'), ('17', '5', '5', '2', '0'), ('18', '5', '2', '0', '0'), ('19', '5', '7', '1', '0'), ('20', '6', '5', '2', '0'), ('21', '6', '7', '1', '0'), ('22', '6', '2', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `SocialNetworkData`
-- ----------------------------
DROP TABLE IF EXISTS `SocialNetworkData`;
CREATE TABLE `SocialNetworkData` (
  `idSocialNetworkData` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `data` longtext,
  `idAccessKey` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idSocialNetworkData`),
  KEY `fk_SocialNetworkData_AccessKey1_idx` (`idAccessKey`),
  CONSTRAINT `fk_SocialNetworkData_AccessKey1` FOREIGN KEY (`idAccessKey`) REFERENCES `AccessKey` (`idAccessKey`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `SocialNetworks`
-- ----------------------------
DROP TABLE IF EXISTS `SocialNetworks`;
CREATE TABLE `SocialNetworks` (
  `idSocialNetworks` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `domain` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSocialNetworks`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `Specs`
-- ----------------------------
DROP TABLE IF EXISTS `Specs`;
CREATE TABLE `Specs` (
  `idSpecs` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'This table is for information about a service\nsuch as \njavon \nsuavisante\ntypo lavador\n',
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `optional` tinyint(1) DEFAULT '1',
  `max_qty` int(11) DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSpecs`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Specs`
-- ----------------------------
BEGIN;
INSERT INTO `Specs` VALUES ('1', 'Suavizante', 'Tipo de suavizante', '1', '1', '0'), ('2', 'Detergente', 'Jabon / detergente a utilizarse', '1', '1', '0'), ('3', 'Tipo de secado', 'Tipo de secado', '1', '0', '0'), ('4', 'Toallas de secado', 'toalla aromatizante de secado', '1', '1', '0'), ('5', 'Ganchos', 'Ganchos para prendas', '1', '0', '0'), ('6', 'Tintoreria', 'general', '0', '0', '0'), ('7', 'Blanqueador', 'Blanqueador de ropa', '1', '0', '0'), ('8', 'Bolsas', 'Bolsas varias', '1', '0', '0'), ('9', 'Aroma', 'Reforzador de aroma', '1', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `SpecsValues`
-- ----------------------------
DROP TABLE IF EXISTS `SpecsValues`;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `SpecsValues`
-- ----------------------------
BEGIN;
INSERT INTO `SpecsValues` VALUES ('2', '1', '2', '', '2', '0', '0', '0', '0'), ('3', '2', '2', null, '1', '0', '0', '0', '0'), ('4', '3', '1', 'Secadora', '0', '0', '0', '0', '0'), ('5', '3', '1', 'Al Sol', '0', '0', '0', '0', '1'), ('6', '4', '2', null, '4', '0', '0', '0', '0'), ('7', '5', '2', null, '5', '0', '0', '0', '0'), ('8', '7', '2', null, '3', '0', '0', '0', '0'), ('9', '8', '2', null, '7', '0', '0', '0', '0'), ('10', '9', '2', null, '8', '0', '0', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `Stops`
-- ----------------------------
DROP TABLE IF EXISTS `Stops`;
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

-- ----------------------------
--  Records of `Stops`
-- ----------------------------
BEGIN;
INSERT INTO `Stops` VALUES ('1', 'stop 1', 'stop 1 description', '20', '9:30', '1', '1', '1');
COMMIT;

-- ----------------------------
--  Table structure for `Stores`
-- ----------------------------
DROP TABLE IF EXISTS `Stores`;
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

-- ----------------------------
--  Records of `Stores`
-- ----------------------------
BEGIN;
INSERT INTO `Stores` VALUES ('1', 'unica', '20.62132700', '-103.41805600', '1');
COMMIT;

-- ----------------------------
--  Table structure for `Supply`
-- ----------------------------
DROP TABLE IF EXISTS `Supply`;
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `Supply`
-- ----------------------------
BEGIN;
INSERT INTO `Supply` VALUES ('1', '1', '1', 'Mas color', 'Jabon liquido', '320', '0', '0'), ('2', '1', '1', 'Ace', 'Jabon liquido', '145', '0', '0'), ('3', '2', '1', 'En suenio', 'Suavisante de prendas', '109', '0', '0'), ('4', '4', '1', 'Toallas Naranja', 'Toallas de secado (pendiente nombre)', '199', '5', '0'), ('5', '3', '1', 'Cloro', 'Blanqueador cloro', '60', '0', '0'), ('6', '5', '1', 'Ganchos sencillo', 'Caja con 500 u', '475', '2', '0'), ('7', '1', '1', 'Roma', 'Jabon en polvo para mayoreo', '228', '0', '0'), ('8', '1', '1', 'Suavitel', 'Suavisante de prendas', '120', '0', '0'), ('9', '1', '1', 'Dawny', 'Suavisante de prendas', '180', '0', '0'), ('10', '3', '1', 'Pinon', 'Pinol', '139', '0', '0'), ('11', '3', '1', 'Vanish', 'Vanish', '170', '0', '0'), ('12', '3', '1', 'Vinagre', 'Vinagre', '45', '0', '0'), ('13', '7', '1', 'Bolsas', 'Bolsas plastico 5K', '259', '0', '0'), ('14', '8', '1', 'DW', 'Reforzador de aroma', '38', '0', '0'), ('15', '9', '1', 'Agua destilada', 'Plancha, 3 garrafones', '87', '0', '0'), ('16', '10', '1', 'Almidon', '3K', '100', '0', '0'), ('17', '1', '1', 'Dreft', 'Detergente bebes', '250', '2', '0'), ('18', '2', '1', 'Dreft Suavisante', 'Suavisante para bebes', '250', '2', '0');
COMMIT;

-- ----------------------------
--  Table structure for `SupplyType`
-- ----------------------------
DROP TABLE IF EXISTS `SupplyType`;
CREATE TABLE `SupplyType` (
  `idSupplyType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSupplyType`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `SupplyType`
-- ----------------------------
BEGIN;
INSERT INTO `SupplyType` VALUES ('1', 'Detergente', 'Jabones/Detergentes', '0'), ('2', 'Suavizante', 'Suavisantes', '0'), ('3', 'Blanqueador', 'Blanqueadores de prendas', '0'), ('4', 'Toallas de secado', 'Toallas para secado', '0'), ('5', 'Ganchos', 'ganchos para ropa', '0'), ('6', 'Blanqueador', 'Blanqueadores', '1'), ('7', 'Bolsas', 'Bolsas varias', '0'), ('8', 'Reforzador de aroma', 'aroma al doblar', '0'), ('9', 'Agua destilada', 'Agua destilada Plancha', '0'), ('10', 'Almidon', 'Almidon Plancha', '0'), ('11', 'Detergente Bebes', 'Detergente para bebes', '1');
COMMIT;

-- ----------------------------
--  Table structure for `Task`
-- ----------------------------
DROP TABLE IF EXISTS `Task`;
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

-- ----------------------------
--  Records of `Task`
-- ----------------------------
BEGIN;
INSERT INTO `Task` VALUES ('1', '1', 'Servicio para ordenes', 'Servicio para ordenes.', '0'), ('2', '2', 'Lavado lavadora', 'lavado de ropa general', '0'), ('3', '4', 'Recojer', 'recojer pedido', '0'), ('4', '4', 'Entregar', 'entregar pedido', '0'), ('5', '2', 'Doblar ropa', 'doblado', '0'), ('6', '3', 'Planchar', 'planchar', '0'), ('7', '2', 'Lavado a mano', 'Lavado a mano', '0'), ('8', '5', 'Solicitar Tintoreria', 'Solicitar Tintoreria', '0'), ('9', '5', 'Recibir Tintoreria', 'Resepcion de tintoreria', '0'), ('10', '6', 'Solicitar Costura', 'Costura', '0'), ('11', '6', 'Recibir Costura', 'Resepcion de costura', '0');
COMMIT;

-- ----------------------------
--  Table structure for `TaskType`
-- ----------------------------
DROP TABLE IF EXISTS `TaskType`;
CREATE TABLE `TaskType` (
  `idTaskType` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ordersOnly` tinyint(1) DEFAULT '0',
  `deleted` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTaskType`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `TaskType`
-- ----------------------------
BEGIN;
INSERT INTO `TaskType` VALUES ('1', 'Tareas de Ordenes', 'Trabajo para servicio', '1', '0'), ('2', 'Lavado', 'todo lo relevante a lavar ropa', '0', '0'), ('3', 'Planchado', 'todo lo relevante a planchado', '0', '0'), ('4', 'Transporte', 'recojer o entregar pedidos', '0', '0'), ('5', 'Tintoreria', 'Seguimiento de tintoreria', '0', '0'), ('6', 'Costura', 'Costura', '0', '0');
COMMIT;

-- ----------------------------
--  Triggers structure for table Clients
-- ----------------------------
DROP TRIGGER IF EXISTS `Clients_BEFORE_INSERT`;
delimiter ;;
CREATE TRIGGER `Clients_BEFORE_INSERT` BEFORE INSERT ON `Clients` FOR EACH ROW BEGIN
SET NEW.created = NOW();
END
 ;;
delimiter ;
DROP TRIGGER IF EXISTS `Clients_BEFORE_UPDATE`;
delimiter ;;
CREATE TRIGGER `Clients_BEFORE_UPDATE` BEFORE UPDATE ON `Clients` FOR EACH ROW BEGIN
SET NEW.updated = NOW();
END
 ;;
delimiter ;

delimiter ;;
-- ----------------------------
--  Triggers structure for table Employee
-- ----------------------------
 ;;
delimiter ;
DROP TRIGGER IF EXISTS `Employee_BEFORE_INSERT`;
delimiter ;;
CREATE TRIGGER `Employee_BEFORE_INSERT` BEFORE INSERT ON `Employee` FOR EACH ROW BEGIN
SET NEW.created = NOW();
END
 ;;
delimiter ;
DROP TRIGGER IF EXISTS `Employee_BEFORE_UPDATE`;
delimiter ;;
CREATE TRIGGER `Employee_BEFORE_UPDATE` BEFORE UPDATE ON `Employee` FOR EACH ROW BEGIN
SET NEW.updated = NOW();
END
 ;;
delimiter ;

delimiter ;;
-- ----------------------------
--  Triggers structure for table Orders
-- ----------------------------
 ;;
delimiter ;
DROP TRIGGER IF EXISTS `Orders_BEFORE_INSERT`;
delimiter ;;
CREATE TRIGGER `Orders_BEFORE_INSERT` BEFORE INSERT ON `Orders` FOR EACH ROW BEGIN
SET NEW.created = NOW();
END
 ;;
delimiter ;
DROP TRIGGER IF EXISTS `Orders_BEFORE_UPDATE`;
delimiter ;;
CREATE TRIGGER `Orders_BEFORE_UPDATE` BEFORE UPDATE ON `Orders` FOR EACH ROW BEGIN
SET NEW.updated = NOW();
END
 ;;
delimiter ;

delimiter ;;
-- ----------------------------
--  Triggers structure for table Service
-- ----------------------------
 ;;
delimiter ;
DROP TRIGGER IF EXISTS `Service_BEFORE_INSERT`;
delimiter ;;
CREATE TRIGGER `Service_BEFORE_INSERT` BEFORE INSERT ON `Service` FOR EACH ROW BEGIN
SET NEW.created = NOW();
END
 ;;
delimiter ;
DROP TRIGGER IF EXISTS `Service_BEFORE_UPDATE`;
delimiter ;;
CREATE TRIGGER `Service_BEFORE_UPDATE` BEFORE UPDATE ON `Service` FOR EACH ROW BEGIN
SET NEW.updated = NOW();
END
 ;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
