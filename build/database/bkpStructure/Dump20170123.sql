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
-- Dumping data for table `AccessKey`
--

LOCK TABLES `AccessKey` WRITE;
/*!40000 ALTER TABLE `AccessKey` DISABLE KEYS */;
/*!40000 ALTER TABLE `AccessKey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
INSERT INTO `Address` VALUES (1,1,'Mexico','Jalisco','44540','Guadalajara','Peninsula',NULL,NULL,20.64551528,-103.39244127,1,1),(2,1,'Mexico','Jalisco','44540','Guadalajara','Calle Barlovento','Rinconada del Bosque','departamento sin timbre',20.64997290,-103.38900805,0,0);
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AddressRoutes`
--

LOCK TABLES `AddressRoutes` WRITE;
/*!40000 ALTER TABLE `AddressRoutes` DISABLE KEYS */;
INSERT INTO `AddressRoutes` VALUES (1,'Mexico','Jalisco','44540','Guadalajara','Calle','Colonia','no tiene timbre... ',NULL,NULL);
/*!40000 ALTER TABLE `AddressRoutes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Asset`
--

LOCK TABLES `Asset` WRITE;
/*!40000 ALTER TABLE `Asset` DISABLE KEYS */;
INSERT INTO `Asset` VALUES (1,1,1,'lavadora 1','lavadora roja',0),(2,1,1,'lavadora 2','lavadora roja rota',0),(3,3,1,'moto1','moto roja placas aaabbb92',0);
/*!40000 ALTER TABLE `Asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AssetTaskOrder`
--

LOCK TABLES `AssetTaskOrder` WRITE;
/*!40000 ALTER TABLE `AssetTaskOrder` DISABLE KEYS */;
/*!40000 ALTER TABLE `AssetTaskOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AssetTaskService`
--

LOCK TABLES `AssetTaskService` WRITE;
/*!40000 ALTER TABLE `AssetTaskService` DISABLE KEYS */;
/*!40000 ALTER TABLE `AssetTaskService` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `AssetType`
--

LOCK TABLES `AssetType` WRITE;
/*!40000 ALTER TABLE `AssetType` DISABLE KEYS */;
INSERT INTO `AssetType` VALUES (1,'lavado',NULL,0),(2,'planchado',NULL,0),(3,'transporte',NULL,0);
/*!40000 ALTER TABLE `AssetType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `BagType`
--

LOCK TABLES `BagType` WRITE;
/*!40000 ALTER TABLE `BagType` DISABLE KEYS */;
INSERT INTO `BagType` VALUES (1,'Chica',5),(2,'Grande',10);
/*!40000 ALTER TABLE `BagType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `CalendarRoute`
--

LOCK TABLES `CalendarRoute` WRITE;
/*!40000 ALTER TABLE `CalendarRoute` DISABLE KEYS */;
INSERT INTO `CalendarRoute` VALUES (1,1,'9:30',1,'1');
/*!40000 ALTER TABLE `CalendarRoute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ClientBags`
--

LOCK TABLES `ClientBags` WRITE;
/*!40000 ALTER TABLE `ClientBags` DISABLE KEYS */;
/*!40000 ALTER TABLE `ClientBags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ClientPaymentInfo`
--

LOCK TABLES `ClientPaymentInfo` WRITE;
/*!40000 ALTER TABLE `ClientPaymentInfo` DISABLE KEYS */;
INSERT INTO `ClientPaymentInfo` VALUES (1,1,'01234',1,1);
/*!40000 ALTER TABLE `ClientPaymentInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ClientType`
--

LOCK TABLES `ClientType` WRITE;
/*!40000 ALTER TABLE `ClientType` DISABLE KEYS */;
INSERT INTO `ClientType` VALUES (1,'Normal','Clientes normal'),(2,'Mayoreo','Clientes de Mayoreo'),(3,'Cliente Regular 1','Clientes Regular 1'),(4,'Cliente Regular 2','Clientes Regular 2');
/*!40000 ALTER TABLE `ClientType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Clients`
--

LOCK TABLES `Clients` WRITE;
/*!40000 ALTER TABLE `Clients` DISABLE KEYS */;
INSERT INTO `Clients` VALUES (1,1,'email@domain.com','notused','Brad','Pit','twitter','2016-12-30 13:09:05',NULL,'123',NULL,NULL,0,'333010101',NULL,NULL);
/*!40000 ALTER TABLE `Clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `DistanceInfo`
--

LOCK TABLES `DistanceInfo` WRITE;
/*!40000 ALTER TABLE `DistanceInfo` DISABLE KEYS */;
INSERT INTO `DistanceInfo` VALUES (1,3,0,1,1),(2,6,20,1,1),(3,12,35,1,1),(4,24,50,1,1);
/*!40000 ALTER TABLE `DistanceInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,1,1,'user','user',NULL,'user','2016-12-30 13:09:05',NULL,'email@domain.com',0);
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `EmployeeTaskOrder`
--

LOCK TABLES `EmployeeTaskOrder` WRITE;
/*!40000 ALTER TABLE `EmployeeTaskOrder` DISABLE KEYS */;
/*!40000 ALTER TABLE `EmployeeTaskOrder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `EmployeeTaskService`
--

LOCK TABLES `EmployeeTaskService` WRITE;
/*!40000 ALTER TABLE `EmployeeTaskService` DISABLE KEYS */;
/*!40000 ALTER TABLE `EmployeeTaskService` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `EmployeeType`
--

LOCK TABLES `EmployeeType` WRITE;
/*!40000 ALTER TABLE `EmployeeType` DISABLE KEYS */;
INSERT INTO `EmployeeType` VALUES (1,'admin','Administradores',0),(2,'general','Empleados Nivel 2',0);
/*!40000 ALTER TABLE `EmployeeType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Menu`
--

LOCK TABLES `Menu` WRITE;
/*!40000 ALTER TABLE `Menu` DISABLE KEYS */;
INSERT INTO `Menu` VALUES (1,'client.all','Clientes',2,1),(2,'routes.all','Rutas',1,2),(3,'tasks.taskMenu','Tareas',1,5),(4,'specs.specMenu','Specs',1,6),(5,'employees.employeeMenu','Empleados',1,4),(6,'assets.assetMenu','Activos',0,3),(7,'supplies.supplyMenu','Consumibles',1,7),(8,'services.serviceMenu','Servicios',1,8),(9,'orders.orderMenu','Orders',1,9),(10,'products.productMenu','Productos',1,10);
/*!40000 ALTER TABLE `Menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `OrderPromotion`
--

LOCK TABLES `OrderPromotion` WRITE;
/*!40000 ALTER TABLE `OrderPromotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderPromotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `OrderTask`
--

LOCK TABLES `OrderTask` WRITE;
/*!40000 ALTER TABLE `OrderTask` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrderTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `OrderType`
--

LOCK TABLES `OrderType` WRITE;
/*!40000 ALTER TABLE `OrderType` DISABLE KEYS */;
INSERT INTO `OrderType` VALUES (1,'Completa','Pickup + service + deliver',3,0),(2,'Recoleccion','Pickup + service',1,0),(3,'Entrega','Service + deliver',2,0),(4,'Encargo','Service',0,0);
/*!40000 ALTER TABLE `OrderType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `OrderTypeTask`
--

LOCK TABLES `OrderTypeTask` WRITE;
/*!40000 ALTER TABLE `OrderTypeTask` DISABLE KEYS */;
INSERT INTO `OrderTypeTask` VALUES (1,1,3,1),(2,1,1,2),(3,1,4,3);
/*!40000 ALTER TABLE `OrderTypeTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `PaymentInfo`
--

LOCK TABLES `PaymentInfo` WRITE;
/*!40000 ALTER TABLE `PaymentInfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `PaymentInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,1,'Sabanas',0.2,200,0),(2,1,'Toalla Chica',0.2,300,0),(3,1,'Toalla Grande',20,0,0),(4,1,'Toalla Mediana',20,0,0),(5,4,'Kg',14,0,0),(6,4,'Tersus Bolsa Chica',65,0,0),(7,4,'Tersus Bolsa Grande',70,0,0),(8,6,'Sabana',20,0,0),(9,6,'Cobija',30,0,0),(10,6,'Edredon',50,0,0);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ProductType`
--

LOCK TABLES `ProductType` WRITE;
/*!40000 ALTER TABLE `ProductType` DISABLE KEYS */;
INSERT INTO `ProductType` VALUES (1,'Ocho16 Spa','Productos para el ocho16 spa',0),(2,'Spa General','Productos para spa general',0),(3,'Seguridad 1','Productos para casa de seguridad',0),(4,'Lavado','Productos de Lavado general',0),(5,'Planchado','Productos de Planchado general',0),(6,'Lavado de Blancos','Productos de lavado de blancos',0);
/*!40000 ALTER TABLE `ProductType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Promotion`
--

LOCK TABLES `Promotion` WRITE;
/*!40000 ALTER TABLE `Promotion` DISABLE KEYS */;
INSERT INTO `Promotion` VALUES (1,1,'Buen Fin','Promocion por el buen fin','2016-01-01 00:00:00','2017-01-01 00:00:00',1,10,NULL,1,NULL,0,2,0),(2,3,'Compra de $120','Compra de $120',NULL,NULL,1,20,NULL,1,NULL,0,1,0),(3,4,'Compra No. #','Compra no #. ',NULL,NULL,1,60,NULL,1,NULL,0,2,0),(4,5,'Cliente Frecuente','Descuento por cliente frecuente',NULL,NULL,1,10,NULL,1,NULL,0,1,0);
/*!40000 ALTER TABLE `Promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `PromotionType`
--

LOCK TABLES `PromotionType` WRITE;
/*!40000 ALTER TABLE `PromotionType` DISABLE KEYS */;
INSERT INTO `PromotionType` VALUES (1,'Periodo de tiempo','Descuentos entre fechas',0),(2,'Productos o Servicios [NA]','Al agregar uno o alguna combinacio de producto o servicio',0),(3,'Cantidad','Al llegar a una cantidad economica en una orden',0),(4,'Cantidad acumulativa en periodo','Al llegar a una cantidad acumulativa por periodo',0),(5,'Tipo de Client','Por tipo de clientes',0),(6,'Numero de ordenes por cliente','Al llegar a una cantidad de ordenes por cliente',0);
/*!40000 ALTER TABLE `PromotionType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Routes`
--

LOCK TABLES `Routes` WRITE;
/*!40000 ALTER TABLE `Routes` DISABLE KEYS */;
INSERT INTO `Routes` VALUES (1,'route1','route 1 ',1);
/*!40000 ALTER TABLE `Routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Service`
--

LOCK TABLES `Service` WRITE;
/*!40000 ALTER TABLE `Service` DISABLE KEYS */;
/*!40000 ALTER TABLE `Service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ServiceCategory`
--

LOCK TABLES `ServiceCategory` WRITE;
/*!40000 ALTER TABLE `ServiceCategory` DISABLE KEYS */;
INSERT INTO `ServiceCategory` VALUES (1,'Lavado','Servicios de Lavado Clientes'),(2,'Planchado','Planchado'),(3,'Tintoreria','Tintoreria varios'),(4,'Costura','Costura'),(5,'Mayoreao Spa','Servicio de mayoreo para SPAs'),(6,'Mayoreo Seguridad','Casas de seguridad');
/*!40000 ALTER TABLE `ServiceCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ServiceComments`
--

LOCK TABLES `ServiceComments` WRITE;
/*!40000 ALTER TABLE `ServiceComments` DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceComments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ServiceProducts`
--

LOCK TABLES `ServiceProducts` WRITE;
/*!40000 ALTER TABLE `ServiceProducts` DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceProducts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ServiceSpecs`
--

LOCK TABLES `ServiceSpecs` WRITE;
/*!40000 ALTER TABLE `ServiceSpecs` DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceSpecs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ServiceTask`
--

LOCK TABLES `ServiceTask` WRITE;
/*!40000 ALTER TABLE `ServiceTask` DISABLE KEYS */;
/*!40000 ALTER TABLE `ServiceTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ServiceType`
--

LOCK TABLES `ServiceType` WRITE;
/*!40000 ALTER TABLE `ServiceType` DISABLE KEYS */;
INSERT INTO `ServiceType` VALUES (1,'Lavado General','Ropa general',20,45,1,1),(2,'Lavado Delicado','Ropa intima',20,45,1,1),(3,'Planchado Docena','Planchado docena de piezas',60,30,2,0),(4,'Planchado pieza','planchado pieza',10,5,2,0),(5,'Ocho16 Spa','Ocho16 Spa',0,90,5,0),(6,'Mayoreo Seguridad Casa X','Casa de seguridad 1',0,40,6,0);
/*!40000 ALTER TABLE `ServiceType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ServiceTypeProductType`
--

LOCK TABLES `ServiceTypeProductType` WRITE;
/*!40000 ALTER TABLE `ServiceTypeProductType` DISABLE KEYS */;
INSERT INTO `ServiceTypeProductType` VALUES (4,5),(3,5),(5,1);
/*!40000 ALTER TABLE `ServiceTypeProductType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ServiceTypeSpecs`
--

LOCK TABLES `ServiceTypeSpecs` WRITE;
/*!40000 ALTER TABLE `ServiceTypeSpecs` DISABLE KEYS */;
INSERT INTO `ServiceTypeSpecs` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,2),(5,3,5),(6,4,5),(7,5,2),(8,5,3),(9,5,4),(10,5,1);
/*!40000 ALTER TABLE `ServiceTypeSpecs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ServiceTypeTask`
--

LOCK TABLES `ServiceTypeTask` WRITE;
/*!40000 ALTER TABLE `ServiceTypeTask` DISABLE KEYS */;
INSERT INTO `ServiceTypeTask` VALUES (1,1,2,1,10),(2,1,5,2,10),(3,2,2,1,10),(4,2,5,2,10),(5,3,6,0,0),(6,4,6,0,0),(7,5,5,1,0),(8,5,2,0,0),(9,5,7,1,0),(10,5,2,0,0),(11,5,5,2,0);
/*!40000 ALTER TABLE `ServiceTypeTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `SocialNetworkData`
--

LOCK TABLES `SocialNetworkData` WRITE;
/*!40000 ALTER TABLE `SocialNetworkData` DISABLE KEYS */;
/*!40000 ALTER TABLE `SocialNetworkData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `SocialNetworks`
--

LOCK TABLES `SocialNetworks` WRITE;
/*!40000 ALTER TABLE `SocialNetworks` DISABLE KEYS */;
/*!40000 ALTER TABLE `SocialNetworks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Specs`
--

LOCK TABLES `Specs` WRITE;
/*!40000 ALTER TABLE `Specs` DISABLE KEYS */;
INSERT INTO `Specs` VALUES (1,'Suavizante','Tipo de suavizante',1,1,0),(2,'Detergente','Jabon / detergente a utilizarse',1,1,0),(3,'Secado','Tipo de secado',1,0,0),(4,'Toallas de secado','toalla aromatizante de secado',1,1,0),(5,'Ganchos','Ganchos para prendas',1,0,0);
/*!40000 ALTER TABLE `Specs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `SpecsValues`
--

LOCK TABLES `SpecsValues` WRITE;
/*!40000 ALTER TABLE `SpecsValues` DISABLE KEYS */;
INSERT INTO `SpecsValues` VALUES (2,1,2,'',2,0,0,0,0),(3,2,2,NULL,1,0,0,0,0),(4,3,1,'Secadora',0,0,0,0,0),(5,3,1,'Al sol',0,0,0,20,1),(6,4,2,NULL,4,0,0,0,0),(7,5,2,NULL,5,0,0,0,0);
/*!40000 ALTER TABLE `SpecsValues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Stops`
--

LOCK TABLES `Stops` WRITE;
/*!40000 ALTER TABLE `Stops` DISABLE KEYS */;
INSERT INTO `Stops` VALUES (1,'stop 1','stop 1 description',20,'9:30',1,1,1);
/*!40000 ALTER TABLE `Stops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Stores`
--

LOCK TABLES `Stores` WRITE;
/*!40000 ALTER TABLE `Stores` DISABLE KEYS */;
INSERT INTO `Stores` VALUES (1,'unica',20.62132700,-103.41805600,1);
/*!40000 ALTER TABLE `Stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Supply`
--

LOCK TABLES `Supply` WRITE;
/*!40000 ALTER TABLE `Supply` DISABLE KEYS */;
INSERT INTO `Supply` VALUES (1,1,1,'Ariel','Jabon liquido',100,0,0),(2,1,1,'Membersmarc','Jabon liquido',120,15,0),(3,2,1,'Suavitel','Suavisante de prendas',120,3,0),(4,4,1,'Toallas Bla','Toallas de secado',120,5,0),(5,3,1,'Cloro','Blanqueador cloro',100,0,0),(6,5,1,'Ganchos sencillo','Gancho de fierro sencillo',300,2,0);
/*!40000 ALTER TABLE `Supply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `SupplyType`
--

LOCK TABLES `SupplyType` WRITE;
/*!40000 ALTER TABLE `SupplyType` DISABLE KEYS */;
INSERT INTO `SupplyType` VALUES (1,'Detergente','Jabones/Detergentes',0),(2,'Suavizante','Suavisantes',0),(3,'Blanqueador','Blanqueadores de prendas',0),(4,'Toallas de secado','Toallas para secado',0),(5,'Ganchos','ganchos para ropa',0);
/*!40000 ALTER TABLE `SupplyType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;
INSERT INTO `Task` VALUES (1,1,'Servicio para ordenes','Servicio para ordenes.',0),(2,2,'Lavado lavadora','lavado de ropa general',0),(3,4,'Recojer','recojer pedido',0),(4,4,'Entregar','entregar pedido',0),(5,2,'Doblar ropa','doblado',0),(6,3,'Planchar','planchar',0),(7,2,'Lavado a mano','Lavado a mano',0),(8,5,'Solicitar Tintoreria','Solicitar Tintoreria',0),(9,5,'Recibir Tintoreria','Resepcion de tintoreria',0),(10,6,'Solicitar Costura','Costura',0),(11,6,'Recibir Costura','Resepcion de costura',0);
/*!40000 ALTER TABLE `Task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `TaskType`
--

LOCK TABLES `TaskType` WRITE;
/*!40000 ALTER TABLE `TaskType` DISABLE KEYS */;
INSERT INTO `TaskType` VALUES (1,'Tareas de Ordenes','Trabajo para servicio',1,0),(2,'Lavado','todo lo relevante a lavar ropa',0,0),(3,'Planchado','todo lo relevante a planchado',0,0),(4,'Transporte','recojer o entregar pedidos',0,0),(5,'Tintoreria','Seguimiento de tintoreria',0,0),(6,'Costura','Costura',0,0);
/*!40000 ALTER TABLE `TaskType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sod_db'
--

--
-- Dumping routines for database 'sod_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-23 17:46:56
