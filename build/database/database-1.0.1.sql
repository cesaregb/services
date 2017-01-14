-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sod_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sod_db` ;

-- -----------------------------------------------------
-- Schema sod_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sod_db` DEFAULT CHARACTER SET utf8 ;
USE `sod_db` ;

-- -----------------------------------------------------
-- Table `sod_db`.`ClientType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ClientType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ClientType` (
  `idClientType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idClientType`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Clients` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Clients` (
  `idClient` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClientType` INT(10) UNSIGNED NOT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `password` CHAR(128) NULL DEFAULT NULL,
  `name` VARCHAR(250) NULL DEFAULT NULL,
  `lastName` VARCHAR(250) NULL DEFAULT NULL,
  `twitter` VARCHAR(250) NULL DEFAULT NULL,
  `created` DATETIME NULL DEFAULT NULL,
  `updated` DATETIME NULL DEFAULT NULL,
  `loginID` VARCHAR(50) NULL DEFAULT NULL,
  `rfc` VARCHAR(45) NULL DEFAULT NULL,
  `razonSocial` VARCHAR(250) NULL DEFAULT NULL,
  `deleted` INT(11) NULL DEFAULT '0',
  `mobilePhone` VARCHAR(45) NULL DEFAULT NULL,
  `homePhone` VARCHAR(45) NULL DEFAULT NULL,
  `otherPhone` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idClient`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_Clients_ClientCategory1_idx` (`idClientType` ASC),
  CONSTRAINT `fk_Clients_ClientCategory1`
    FOREIGN KEY (`idClientType`)
    REFERENCES `sod_db`.`ClientType` (`idClientType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Address` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Address` (
  `idAddress` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT(10) UNSIGNED NOT NULL,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(45) NULL DEFAULT NULL,
  `zipcode` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(250) NULL DEFAULT NULL,
  `address2` VARCHAR(250) NULL DEFAULT NULL,
  `comments` VARCHAR(255) NULL DEFAULT NULL,
  `lat` DECIMAL(10,8) NULL DEFAULT NULL,
  `lng` DECIMAL(11,8) NULL DEFAULT NULL,
  `prefered` TINYINT(1) NULL DEFAULT '0',
  `factura` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`idAddress`),
  INDEX `fk_Address_Clients1_idx` (`idClient` ASC),
  CONSTRAINT `fk_Address_Clients1`
    FOREIGN KEY (`idClient`)
    REFERENCES `sod_db`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`AddressRoutes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`AddressRoutes` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`AddressRoutes` (
  `idAddressRoute` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(45) NULL DEFAULT NULL,
  `zipcode` VARCHAR(45) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(250) NULL DEFAULT NULL,
  `address2` VARCHAR(250) NULL DEFAULT NULL,
  `comments` VARCHAR(255) NULL DEFAULT NULL,
  `lat` DECIMAL(10,8) NULL DEFAULT NULL,
  `lng` DECIMAL(11,8) NULL DEFAULT NULL,
  PRIMARY KEY (`idAddressRoute`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`AssetType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`AssetType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`AssetType` (
  `idAssetType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idAssetType`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Asset`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Asset` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Asset` (
  `idAsset` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idAssetType` INT(10) UNSIGNED NOT NULL,
  `status` INT(11) NOT NULL DEFAULT '0',
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(250) NULL DEFAULT NULL,
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idAsset`),
  INDEX `fk_Asset_AssetType1_idx` (`idAssetType` ASC),
  CONSTRAINT `fk_Asset_AssetType1`
    FOREIGN KEY (`idAssetType`)
    REFERENCES `sod_db`.`AssetType` (`idAssetType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`OrderType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`OrderType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`OrderType` (
  `idOrderType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `transportInfo` INT(11) NULL DEFAULT '0' COMMENT '0 = none; \n1 = show pick up\n2 = show deliver \n3 = show both.',
  `deleted` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`idOrderType`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Orders` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Orders` (
  `idOrder` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT(10) UNSIGNED NOT NULL,
  `idOrderType` INT(10) UNSIGNED NOT NULL,
  `idAddressPickup` INT(11) NULL DEFAULT NULL COMMENT 'Not froreing key ',
  `pickUpDate` DATETIME NULL DEFAULT NULL,
  `idAddressDeliver` INT(11) NULL DEFAULT NULL,
  `deliverDate` DATETIME NULL DEFAULT NULL,
  `time` INT(11) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT '0' COMMENT '0 = active\n1 = finished',
  `comments` VARCHAR(250) NULL DEFAULT NULL,
  `createdBy` INT(11) NULL DEFAULT '0',
  `created` DATETIME NULL DEFAULT NULL,
  `updated` DATETIME NULL DEFAULT NULL,
  `deleted` INT(11) NULL DEFAULT '0',
  `totalServices` DOUBLE NULL DEFAULT NULL,
  `total` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`idOrder`),
  INDEX `fk_Order_OrderTemplate1_idx` (`idOrderType` ASC),
  INDEX `fk_Order_Clients1_idx` (`idClient` ASC),
  CONSTRAINT `fk_Order_Clients1`
    FOREIGN KEY (`idClient`)
    REFERENCES `sod_db`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_OrderTemplate1`
    FOREIGN KEY (`idOrderType`)
    REFERENCES `sod_db`.`OrderType` (`idOrderType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`TaskType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`TaskType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`TaskType` (
  `idTaskType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `ordersOnly` TINYINT(1) NULL DEFAULT '0',
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTaskType`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Task` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Task` (
  `idTask` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idTaskType` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTask`),
  INDEX `fk_Task_TaskType1_idx` (`idTaskType` ASC),
  CONSTRAINT `fk_Task_TaskType1`
    FOREIGN KEY (`idTaskType`)
    REFERENCES `sod_db`.`TaskType` (`idTaskType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`OrderTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`OrderTask` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`OrderTask` (
  `idOrderTask` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrder` INT(10) UNSIGNED NOT NULL,
  `idTask` INT(10) UNSIGNED NOT NULL,
  `time` INT(11) NULL DEFAULT NULL,
  `comments` VARCHAR(255) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT '0' COMMENT '0 = NEW\n1 = COMPLETED',
  `sortingOrder` INT(11) NULL DEFAULT NULL,
  `started` DATETIME NULL DEFAULT NULL,
  `ended` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`idOrderTask`),
  INDEX `fk_OrderTask_Task1_idx` (`idTask` ASC),
  INDEX `fk_OrderTask_Order1_idx` (`idOrder` ASC),
  CONSTRAINT `fk_OrderTask_Order1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `sod_db`.`Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTask_Task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `sod_db`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`AssetTaskOrder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`AssetTaskOrder` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`AssetTaskOrder` (
  `idAssetTaskOrder` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderTask` INT(10) UNSIGNED NOT NULL,
  `Asset_idAsset` INT(10) UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`idAssetTaskOrder`),
  INDEX `fk_AssetTaskOrder_Asset1_idx` (`Asset_idAsset` ASC),
  INDEX `fk_AssetTaskOrder_OrderTask1_idx` (`idOrderTask` ASC),
  CONSTRAINT `fk_AssetTaskOrder_Asset1`
    FOREIGN KEY (`Asset_idAsset`)
    REFERENCES `sod_db`.`Asset` (`idAsset`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AssetTaskOrder_OrderTask1`
    FOREIGN KEY (`idOrderTask`)
    REFERENCES `sod_db`.`OrderTask` (`idOrderTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceCategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceCategory` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceCategory` (
  `idServiceCategory` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`idServiceCategory`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceType` (
  `idServiceType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NULL DEFAULT NULL,
  `description` VARCHAR(250) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `time` INT(11) NULL DEFAULT NULL,
  `idServiceCategory` INT(10) UNSIGNED NOT NULL,
  `calculator` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`idServiceType`),
  INDEX `fk_ServiceType_ServiceCategory1_idx` (`idServiceCategory` ASC),
  CONSTRAINT `fk_ServiceType_ServiceCategory1`
    FOREIGN KEY (`idServiceCategory`)
    REFERENCES `sod_db`.`ServiceCategory` (`idServiceCategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Service` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Service` (
  `idService` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idServiceType` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(250) NULL DEFAULT NULL,
  `time` INT(11) NULL DEFAULT NULL,
  `created` DATETIME NULL DEFAULT NULL,
  `updated` DATETIME NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `idOrder` INT(10) UNSIGNED NOT NULL,
  `nTasks` INT(11) NULL DEFAULT '0' COMMENT '# numero de tasks o pasos',
  `currentTask` INT(11) NULL DEFAULT NULL,
  `deleted` INT(11) NULL DEFAULT '0',
  `price` DOUBLE NULL DEFAULT '0',
  `specsPrice` DOUBLE NULL DEFAULT '0',
  `productsPrice` DOUBLE NULL DEFAULT '0',
  `totalPrice` DOUBLE NULL DEFAULT '0',
  PRIMARY KEY (`idService`),
  INDEX `fk_Service_ServiceType1_idx` (`idServiceType` ASC),
  INDEX `fk_Service_Orders1_idx` (`idOrder` ASC),
  CONSTRAINT `fk_Service_Orders1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `sod_db`.`Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Service_ServiceType1`
    FOREIGN KEY (`idServiceType`)
    REFERENCES `sod_db`.`ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceTask` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceTask` (
  `idServiceTask` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idService` INT(10) UNSIGNED NOT NULL,
  `idTask` INT(10) UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL DEFAULT NULL,
  `status` INT(11) NULL DEFAULT NULL,
  `started` DATETIME NULL DEFAULT NULL,
  `ended` DATETIME NULL DEFAULT NULL,
  `sortingOrder` INT(11) NULL DEFAULT NULL,
  `time` INT(11) NULL DEFAULT '10',
  PRIMARY KEY (`idServiceTask`),
  INDEX `fk_ServiceTask_Task1_idx` (`idTask` ASC),
  INDEX `fk_ServiceTask_Service1_idx` (`idService` ASC),
  CONSTRAINT `fk_ServiceTask_Service1`
    FOREIGN KEY (`idService`)
    REFERENCES `sod_db`.`Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTask_Task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `sod_db`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`AssetTaskService`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`AssetTaskService` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`AssetTaskService` (
  `idAssetTaskService` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idAsset` INT(10) UNSIGNED NOT NULL,
  `idServiceTask` INT(10) UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`idAssetTaskService`),
  INDEX `fk_AssetTaskService_Asset1_idx` (`idAsset` ASC),
  INDEX `fk_AssetTaskService_ServiceTask1_idx` (`idServiceTask` ASC),
  CONSTRAINT `fk_AssetTaskService_Asset1`
    FOREIGN KEY (`idAsset`)
    REFERENCES `sod_db`.`Asset` (`idAsset`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AssetTaskService_ServiceTask1`
    FOREIGN KEY (`idServiceTask`)
    REFERENCES `sod_db`.`ServiceTask` (`idServiceTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`BagType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`BagType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`BagType` (
  `idBagType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `size` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idBagType`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Routes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Routes` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Routes` (
  `idRoutes` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(105) NULL DEFAULT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `category` INT(11) NULL DEFAULT '1' COMMENT '1 = departamentos\n2 = offices\n3 = cliente\n',
  PRIMARY KEY (`idRoutes`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`CalendarRoute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`CalendarRoute` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`CalendarRoute` (
  `idCalendarRoute` INT(11) NOT NULL AUTO_INCREMENT,
  `day` INT(11) NULL DEFAULT '1',
  `time` VARCHAR(50) NULL DEFAULT '8:00',
  `idRoutes` INT(11) NOT NULL,
  `action` VARCHAR(45) NULL DEFAULT '1' COMMENT '1 = pickup\n2 = deliver',
  PRIMARY KEY (`idCalendarRoute`),
  INDEX `fk_Calendar_Routes1_idx` (`idRoutes` ASC),
  CONSTRAINT `fk_Calendar_Routes1`
    FOREIGN KEY (`idRoutes`)
    REFERENCES `sod_db`.`Routes` (`idRoutes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ClientBags`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ClientBags` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ClientBags` (
  `idClientBags` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(45) NULL DEFAULT NULL,
  `inOrder` TINYINT(1) NULL DEFAULT NULL,
  `idBagType` INT(10) UNSIGNED NOT NULL,
  `idClient` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idClientBags`),
  INDEX `fk_ClientBags_BagSize1_idx` (`idBagType` ASC),
  INDEX `fk_ClientBags_Clients1_idx` (`idClient` ASC),
  CONSTRAINT `fk_ClientBags_BagSize1`
    FOREIGN KEY (`idBagType`)
    REFERENCES `sod_db`.`BagType` (`idBagType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ClientBags_Clients1`
    FOREIGN KEY (`idClient`)
    REFERENCES `sod_db`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ClientPaymentInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ClientPaymentInfo` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ClientPaymentInfo` (
  `idClientPaymentInfo` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` INT(11) NULL DEFAULT '0' COMMENT 'CASH = 0; \nCC = 1; // credit card \nPAYPAL = 2;  \nOTHER = 4; // NOT DEFINED \nSTRIPE = 3; ',
  `token` VARCHAR(250) NULL DEFAULT NULL,
  `idClient` INT(10) UNSIGNED NOT NULL,
  `prefered` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`idClientPaymentInfo`),
  INDEX `fk_ClientPaymentInfo_Clients1_idx` (`idClient` ASC),
  CONSTRAINT `fk_ClientPaymentInfo_Clients1`
    FOREIGN KEY (`idClient`)
    REFERENCES `sod_db`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`EmployeeType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`EmployeeType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`EmployeeType` (
  `idEmployeeType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEmployeeType`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Employee` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Employee` (
  `idEmployee` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEmployeeType` INT(10) UNSIGNED NOT NULL,
  `status` INT(11) NOT NULL DEFAULT '0',
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `lastname` VARCHAR(45) NULL DEFAULT NULL,
  `password` LONGTEXT NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `created` DATETIME NULL DEFAULT NULL,
  `updated` DATETIME NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idEmployee`),
  INDEX `fk_Employee_EmployeeType1_idx` (`idEmployeeType` ASC),
  CONSTRAINT `fk_Employee_EmployeeType1`
    FOREIGN KEY (`idEmployeeType`)
    REFERENCES `sod_db`.`EmployeeType` (`idEmployeeType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Stores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Stores` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Stores` (
  `idStore` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `lat` DECIMAL(10,8) NOT NULL,
  `lng` DECIMAL(11,8) NOT NULL,
  `idEmployee` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idStore`),
  INDEX `fk_Stores_Employee1_idx` (`idEmployee` ASC),
  CONSTRAINT `fk_Stores_Employee1`
    FOREIGN KEY (`idEmployee`)
    REFERENCES `sod_db`.`Employee` (`idEmployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`DistanceInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`DistanceInfo` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`DistanceInfo` (
  `idDistanceInfo` INT(11) NOT NULL AUTO_INCREMENT,
  `distance` INT(11) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `source` INT(11) NULL DEFAULT '0' COMMENT '0 = local\n1 = empresa \n2 = ruta',
  `idStore` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idDistanceInfo`),
  INDEX `fk_DistanceInfo_Stores1_idx` (`idStore` ASC),
  CONSTRAINT `fk_DistanceInfo_Stores1`
    FOREIGN KEY (`idStore`)
    REFERENCES `sod_db`.`Stores` (`idStore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`EmployeeTaskOrder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`EmployeeTaskOrder` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`EmployeeTaskOrder` (
  `idEmployeeTaskOrder` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderTask` INT(10) UNSIGNED NOT NULL,
  `idEmployee` INT(10) UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`idEmployeeTaskOrder`),
  INDEX `fk_EmployeeTaskOrder_OrderTask1_idx` (`idOrderTask` ASC),
  INDEX `fk_EmployeeTaskOrder_Employee1_idx` (`idEmployee` ASC),
  CONSTRAINT `fk_EmployeeTaskOrder_Employee1`
    FOREIGN KEY (`idEmployee`)
    REFERENCES `sod_db`.`Employee` (`idEmployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EmployeeTaskOrder_OrderTask1`
    FOREIGN KEY (`idOrderTask`)
    REFERENCES `sod_db`.`OrderTask` (`idOrderTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`EmployeeTaskService`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`EmployeeTaskService` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`EmployeeTaskService` (
  `idEmployeeTaskService` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEmployee` INT(10) UNSIGNED NOT NULL,
  `idServiceTask` INT(10) UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`idEmployeeTaskService`),
  INDEX `fk_EmployeeTaskService_Employee1_idx` (`idEmployee` ASC),
  INDEX `fk_EmployeeTaskService_ServiceTask1_idx` (`idServiceTask` ASC),
  CONSTRAINT `fk_EmployeeTaskService_Employee1`
    FOREIGN KEY (`idEmployee`)
    REFERENCES `sod_db`.`Employee` (`idEmployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EmployeeTaskService_ServiceTask1`
    FOREIGN KEY (`idServiceTask`)
    REFERENCES `sod_db`.`ServiceTask` (`idServiceTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Menu` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Menu` (
  `idMenu` INT(11) NOT NULL AUTO_INCREMENT,
  `state` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `accessLevel` INT(11) NULL DEFAULT '1' COMMENT '0 = none\n1 = admin \n2 = not so admin\n3 = less admin than 2\n',
  `order` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idMenu`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`PromotionType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`PromotionType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`PromotionType` (
  `idPromotionType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `deleted` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`idPromotionType`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Promotion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Promotion` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Promotion` (
  `idPromotion` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idPromotionType` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(60) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `startDate` DATETIME NULL DEFAULT NULL,
  `endDate` DATETIME NULL DEFAULT NULL,
  `maxUses` INT(11) NULL DEFAULT '0' COMMENT 'max uses by order',
  `amount` DOUBLE NULL DEFAULT '0',
  `promoCode` VARCHAR(45) NULL DEFAULT NULL,
  `orderLimit` INT(11) NULL DEFAULT '0',
  `dateLimit` DATETIME NULL DEFAULT NULL COMMENT 'limit date that the promotion will be enabled. ',
  `minimumAmount` INT(11) NULL DEFAULT '0',
  `discountType` INT(11) NULL DEFAULT '1' COMMENT '1 = $\n2 = %\n',
  `deleted` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`idPromotion`),
  INDEX `fk_Promotion_PromotionType1_idx` (`idPromotionType` ASC),
  CONSTRAINT `fk_Promotion_PromotionType1`
    FOREIGN KEY (`idPromotionType`)
    REFERENCES `sod_db`.`PromotionType` (`idPromotionType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`OrderPromotion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`OrderPromotion` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`OrderPromotion` (
  `idOrderPromotion` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrder` INT(10) UNSIGNED NOT NULL,
  `idPromotion` INT(10) UNSIGNED NOT NULL,
  `cantidad` DOUBLE NOT NULL DEFAULT '0',
  PRIMARY KEY (`idOrderPromotion`),
  INDEX `fk_OrderPromotion_Orders1_idx` (`idOrder` ASC),
  INDEX `fk_OrderPromotion_Promotion1_idx` (`idPromotion` ASC),
  CONSTRAINT `fk_OrderPromotion_Orders1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `sod_db`.`Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderPromotion_Promotion1`
    FOREIGN KEY (`idPromotion`)
    REFERENCES `sod_db`.`Promotion` (`idPromotion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`OrderTypeTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`OrderTypeTask` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`OrderTypeTask` (
  `idOrderTypeTask` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderType` INT(10) UNSIGNED NOT NULL,
  `idTask` INT(10) UNSIGNED NOT NULL,
  `sortingOrder` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idOrderTypeTask`),
  INDEX `fk_OrderTemplateTasks_Task1_idx` (`idTask` ASC),
  INDEX `fk_OrderTemplateTasks_OrderTemplate1_idx` (`idOrderType` ASC),
  CONSTRAINT `fk_OrderTemplateTasks_OrderTemplate1`
    FOREIGN KEY (`idOrderType`)
    REFERENCES `sod_db`.`OrderType` (`idOrderType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTemplateTasks_Task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `sod_db`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`PaymentInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`PaymentInfo` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`PaymentInfo` (
  `idPaymentInfo` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrder` INT(10) UNSIGNED NOT NULL,
  `type` INT(11) NOT NULL COMMENT '0=cash\n1=stripe\n2=square\n',
  `transactionInfo` VARCHAR(250) NULL DEFAULT NULL,
  PRIMARY KEY (`idPaymentInfo`),
  INDEX `fk_PaymentInfo_Orders1_idx` (`idOrder` ASC),
  CONSTRAINT `fk_PaymentInfo_Orders1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `sod_db`.`Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ProductType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ProductType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ProductType` (
  `idProductType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(250) NULL DEFAULT NULL,
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idProductType`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Product` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Product` (
  `idProduct` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idProductType` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `price` DOUBLE NOT NULL,
  `maxQty` INT(11) NOT NULL DEFAULT '0',
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idProduct`),
  INDEX `fk_Subproduct_SubproductType1_idx` (`idProductType` ASC),
  CONSTRAINT `fk_Subproduct_SubproductType1`
    FOREIGN KEY (`idProductType`)
    REFERENCES `sod_db`.`ProductType` (`idProductType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceComments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceComments` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceComments` (
  `idServiceComments` INT(11) NOT NULL,
  `idService` INT(10) UNSIGNED NOT NULL,
  `comment` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idServiceComments`),
  INDEX `fk_ServiceComments_Service1_idx` (`idService` ASC),
  CONSTRAINT `fk_ServiceComments_Service1`
    FOREIGN KEY (`idService`)
    REFERENCES `sod_db`.`Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceProducts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceProducts` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceProducts` (
  `idServiceProducts` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idService` INT(10) UNSIGNED NOT NULL,
  `idProduct` INT(10) UNSIGNED NOT NULL,
  `quantity` INT(11) NULL DEFAULT '0',
  `price` DOUBLE NULL DEFAULT '0',
  PRIMARY KEY (`idServiceProducts`),
  INDEX `fk_ServiceSubproducts_Service1_idx` (`idService` ASC),
  INDEX `fk_ServiceSubproducts_Subproduct1_idx` (`idProduct` ASC),
  CONSTRAINT `fk_ServiceSubproducts_Service1`
    FOREIGN KEY (`idService`)
    REFERENCES `sod_db`.`Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceSubproducts_Subproduct1`
    FOREIGN KEY (`idProduct`)
    REFERENCES `sod_db`.`Product` (`idProduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Specs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Specs` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Specs` (
  `idSpecs` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'This table is for information about a service\nsuch as \njavon \nsuavisante\ntypo lavador\n',
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `optional` TINYINT(1) NULL DEFAULT '1',
  `max_qty` INT(11) NULL DEFAULT '0',
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSpecs`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceSpecs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceSpecs` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceSpecs` (
  `idServiceSpecs` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idService` INT(10) UNSIGNED NOT NULL,
  `idSpecs` INT(10) UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL DEFAULT NULL,
  `quantity` INT(11) NULL DEFAULT '0',
  `specPrice` DOUBLE NULL DEFAULT '0',
  `selectedValue` VARCHAR(250) NULL DEFAULT NULL,
  `serviceIncrement` DOUBLE NULL DEFAULT '0',
  PRIMARY KEY (`idServiceSpecs`),
  INDEX `fk_ServiceSpecs_Specs1_idx` (`idSpecs` ASC),
  INDEX `fk_ServiceSpecs_Service1_idx` (`idService` ASC),
  CONSTRAINT `fk_ServiceSpecs_Service1`
    FOREIGN KEY (`idService`)
    REFERENCES `sod_db`.`Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceSpecs_Specs1`
    FOREIGN KEY (`idSpecs`)
    REFERENCES `sod_db`.`Specs` (`idSpecs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceTypeProductType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceTypeProductType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceTypeProductType` (
  `idServiceType` INT(10) UNSIGNED NOT NULL,
  `idProductType` INT(10) UNSIGNED NOT NULL,
  INDEX `fk_ServiceTypeSubproductType_ServiceType1_idx` (`idServiceType` ASC),
  INDEX `fk_ServiceTypeSubproductType_SubproductType1_idx` (`idProductType` ASC),
  CONSTRAINT `fk_ServiceTypeSubproductType_ServiceType1`
    FOREIGN KEY (`idServiceType`)
    REFERENCES `sod_db`.`ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeSubproductType_SubproductType1`
    FOREIGN KEY (`idProductType`)
    REFERENCES `sod_db`.`ProductType` (`idProductType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceTypeSpecs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceTypeSpecs` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceTypeSpecs` (
  `idServiceTypeSpecs` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idServiceType` INT(10) UNSIGNED NOT NULL,
  `idSpecs` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`idServiceTypeSpecs`),
  INDEX `fk_ServiceTypeSpecs_Specs1_idx` (`idSpecs` ASC),
  INDEX `fk_ServiceTypeSpecs_ServiceType1_idx` (`idServiceType` ASC),
  CONSTRAINT `fk_ServiceTypeSpecs_ServiceType1`
    FOREIGN KEY (`idServiceType`)
    REFERENCES `sod_db`.`ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeSpecs_Specs1`
    FOREIGN KEY (`idSpecs`)
    REFERENCES `sod_db`.`Specs` (`idSpecs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceTypeTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceTypeTask` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceTypeTask` (
  `idServiceTypeTask` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idServiceType` INT(10) UNSIGNED NOT NULL,
  `idTask` INT(10) UNSIGNED NOT NULL,
  `sortingOrder` INT(11) NULL DEFAULT NULL,
  `time` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`idServiceTypeTask`),
  INDEX `fk_ServiceTypeTask_Task1_idx` (`idTask` ASC),
  INDEX `fk_ServiceTypeTask_ServiceType1_idx` (`idServiceType` ASC),
  CONSTRAINT `fk_ServiceTypeTask_ServiceType1`
    FOREIGN KEY (`idServiceType`)
    REFERENCES `sod_db`.`ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeTask_Task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `sod_db`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`SpecsValues`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`SpecsValues` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`SpecsValues` (
  `idSpecsValues` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idSpecs` INT(10) UNSIGNED NOT NULL,
  `type` INT(11) NULL DEFAULT '1' COMMENT '1 = value\n2 = product',
  `value` VARCHAR(45) NULL DEFAULT NULL,
  `idSupplyType` INT(11) NULL DEFAULT '0',
  `serviceIncrement` DOUBLE NULL DEFAULT '0',
  `prefered` INT(11) NULL DEFAULT '0',
  `specPrice` DOUBLE NULL DEFAULT '0',
  `costType` INT(11) NULL DEFAULT '1' COMMENT '1 = increment\n2 = specPrice',
  PRIMARY KEY (`idSpecsValues`),
  INDEX `fk_SpecsValues_Specs1_idx` (`idSpecs` ASC),
  CONSTRAINT `fk_SpecsValues_Specs1`
    FOREIGN KEY (`idSpecs`)
    REFERENCES `sod_db`.`Specs` (`idSpecs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Stops`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Stops` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Stops` (
  `idStops` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `time` INT(11) NULL DEFAULT '10',
  `arriveAt` VARCHAR(50) NULL DEFAULT '7',
  `idRoutes` INT(11) NOT NULL,
  `type` INT(11) NULL DEFAULT '0' COMMENT '0 = address\n1 = client',
  `idAddress` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idStops`),
  INDEX `fk_Stops_Routes1_idx` (`idRoutes` ASC),
  CONSTRAINT `fk_Stops_Routes1`
    FOREIGN KEY (`idRoutes`)
    REFERENCES `sod_db`.`Routes` (`idRoutes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`SupplyType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`SupplyType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`SupplyType` (
  `idSupplyType` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSupplyType`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sod_db`.`Supply`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Supply` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Supply` (
  `idSupply` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `idSupplyType` INT(10) UNSIGNED NOT NULL,
  `status` INT(11) NOT NULL DEFAULT '0',
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(250) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT '0',
  `serviceIncrement` DOUBLE NULL DEFAULT '0',
  `deleted` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idSupply`),
  INDEX `fk_Product_ProductType1_idx` (`idSupplyType` ASC),
  CONSTRAINT `fk_Product_ProductType1`
    FOREIGN KEY (`idSupplyType`)
    REFERENCES `sod_db`.`SupplyType` (`idSupplyType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

USE `sod_db`;

DELIMITER $$

USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Clients_BEFORE_INSERT` $$
USE `sod_db`$$
CREATE
DEFINER=CURRENT_USER
TRIGGER `sod_db`.`Clients_BEFORE_INSERT`
BEFORE INSERT ON `sod_db`.`Clients`
FOR EACH ROW
BEGIN
SET NEW.created = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Clients_BEFORE_UPDATE` $$
USE `sod_db`$$
CREATE
DEFINER=CURRENT_USER
TRIGGER `sod_db`.`Clients_BEFORE_UPDATE`
BEFORE UPDATE ON `sod_db`.`Clients`
FOR EACH ROW
BEGIN
SET NEW.updated = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Orders_BEFORE_INSERT` $$
USE `sod_db`$$
CREATE
DEFINER=CURRENT_USER
TRIGGER `sod_db`.`Orders_BEFORE_INSERT`
BEFORE INSERT ON `sod_db`.`Orders`
FOR EACH ROW
BEGIN
SET NEW.created = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Orders_BEFORE_UPDATE` $$
USE `sod_db`$$
CREATE
DEFINER=CURRENT_USER
TRIGGER `sod_db`.`Orders_BEFORE_UPDATE`
BEFORE UPDATE ON `sod_db`.`Orders`
FOR EACH ROW
BEGIN
SET NEW.updated = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Service_BEFORE_INSERT` $$
USE `sod_db`$$
CREATE
DEFINER=CURRENT_USER
TRIGGER `sod_db`.`Service_BEFORE_INSERT`
BEFORE INSERT ON `sod_db`.`Service`
FOR EACH ROW
BEGIN
SET NEW.created = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Service_BEFORE_UPDATE` $$
USE `sod_db`$$
CREATE
DEFINER=CURRENT_USER
TRIGGER `sod_db`.`Service_BEFORE_UPDATE`
BEFORE UPDATE ON `sod_db`.`Service`
FOR EACH ROW
BEGIN
SET NEW.updated = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Employee_BEFORE_INSERT` $$
USE `sod_db`$$
CREATE
DEFINER=CURRENT_USER
TRIGGER `sod_db`.`Employee_BEFORE_INSERT`
BEFORE INSERT ON `sod_db`.`Employee`
FOR EACH ROW
BEGIN
SET NEW.created = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Employee_BEFORE_UPDATE` $$
USE `sod_db`$$
CREATE
DEFINER=CURRENT_USER
TRIGGER `sod_db`.`Employee_BEFORE_UPDATE`
BEFORE UPDATE ON `sod_db`.`Employee`
FOR EACH ROW
BEGIN
SET NEW.updated = NOW();
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
