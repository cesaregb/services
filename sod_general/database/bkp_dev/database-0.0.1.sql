-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sod_db
-- -----------------------------------------------------
-- This is the initial_scheema for the service on demand application
--
DROP SCHEMA IF EXISTS `sod_db` ;

-- -----------------------------------------------------
-- Schema sod_db
--
-- This is the initial_scheema for the service on demand application
--
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sod_db` DEFAULT CHARACTER SET utf8 ;
USE `sod_db` ;

-- -----------------------------------------------------
-- Table `sod_db`.`Clients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Clients` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Clients` (
  `idClient` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NULL,
  `password` CHAR(128) NULL,
  `name` VARCHAR(250) NULL,
  `lastName` VARCHAR(250) NULL,
  `twitter` VARCHAR(250) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  PRIMARY KEY (`idClient`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`SocialNetworks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`SocialNetworks` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`SocialNetworks` (
  `idSocialNetworks` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `domain` VARCHAR(45) NULL,
  PRIMARY KEY (`idSocialNetworks`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`AccessKey`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`AccessKey` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`AccessKey` (
  `idAccessKey` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT UNSIGNED NOT NULL,
  `idSocialNetworks` INT UNSIGNED NOT NULL,
  `token` VARCHAR(100) NULL,
  `tokenSecre` VARCHAR(250) NULL,
  PRIMARY KEY (`idAccessKey`),
  INDEX `fk_AccessKey_Clients1_idx` (`idClient` ASC),
  INDEX `fk_AccessKey_SocialNetworks1_idx` (`idSocialNetworks` ASC),
  CONSTRAINT `fk_AccessKey_Clients1`
    FOREIGN KEY (`idClient`)
    REFERENCES `sod_db`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AccessKey_SocialNetworks1`
    FOREIGN KEY (`idSocialNetworks`)
    REFERENCES `sod_db`.`SocialNetworks` (`idSocialNetworks`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`SocialNetworkData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`SocialNetworkData` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`SocialNetworkData` (
  `idSocialNetworkData` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `data` LONGTEXT NULL,
  `idAccessKey` INT UNSIGNED NOT NULL,
  INDEX `fk_SocialNetworkData_AccessKey1_idx` (`idAccessKey` ASC),
  PRIMARY KEY (`idSocialNetworkData`),
  CONSTRAINT `fk_SocialNetworkData_AccessKey1`
    FOREIGN KEY (`idAccessKey`)
    REFERENCES `sod_db`.`AccessKey` (`idAccessKey`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`PhoneNumber`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`PhoneNumber` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`PhoneNumber` (
  `idPhoneNumber` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT UNSIGNED NOT NULL,
  `number` VARCHAR(45) NULL,
  `prefered` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`idPhoneNumber`),
  INDEX `fk_PhoneNumber_Clients1_idx` (`idClient` ASC),
  CONSTRAINT `fk_PhoneNumber_Clients1`
    FOREIGN KEY (`idClient`)
    REFERENCES `sod_db`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Address` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Address` (
  `idAddress` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT UNSIGNED NOT NULL,
  `country` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zipcode` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `address` VARCHAR(250) NULL,
  `address2` VARCHAR(250) NULL,
  `comments` VARCHAR(255) NULL,
  `lat` DECIMAL(10,8) NULL,
  `lng` DECIMAL(11,8) NULL,
  `prefered` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`idAddress`),
  INDEX `fk_Address_Clients1_idx` (`idClient` ASC),
  CONSTRAINT `fk_Address_Clients1`
    FOREIGN KEY (`idClient`)
    REFERENCES `sod_db`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`TaskType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`TaskType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`TaskType` (
  `idTaskType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idTaskType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`EmployeeType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`EmployeeType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`EmployeeType` (
  `idEmployeeType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idEmployeeType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`AssetType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`AssetType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`AssetType` (
  `idAssetType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idAssetType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ProductType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ProductType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ProductType` (
  `idProductType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idProductType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Task` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Task` (
  `idTask` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idTaskType` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idTask`),
  INDEX `fk_Task_TaskType1_idx` (`idTaskType` ASC),
  CONSTRAINT `fk_Task_TaskType1`
    FOREIGN KEY (`idTaskType`)
    REFERENCES `sod_db`.`TaskType` (`idTaskType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Employee` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Employee` (
  `idEmployee` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEmployeeType` INT UNSIGNED NOT NULL,
  `status` INT NOT NULL DEFAULT 0,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `password` LONGTEXT NULL,
  `username` VARCHAR(45) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`idEmployee`),
  INDEX `fk_Employee_EmployeeType1_idx` (`idEmployeeType` ASC),
  CONSTRAINT `fk_Employee_EmployeeType1`
    FOREIGN KEY (`idEmployeeType`)
    REFERENCES `sod_db`.`EmployeeType` (`idEmployeeType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Asset`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Asset` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Asset` (
  `idAsset` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idAssetType` INT UNSIGNED NOT NULL,
  `status` INT NOT NULL DEFAULT 0,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(250) NULL,
  PRIMARY KEY (`idAsset`),
  INDEX `fk_Asset_AssetType1_idx` (`idAssetType` ASC),
  CONSTRAINT `fk_Asset_AssetType1`
    FOREIGN KEY (`idAssetType`)
    REFERENCES `sod_db`.`AssetType` (`idAssetType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Product` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Product` (
  `idProduct` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idProductType` INT UNSIGNED NOT NULL,
  `status` INT NOT NULL DEFAULT 0,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(250) NULL,
  `price` DOUBLE NULL DEFAULT 0,
  `serviceIncrement` DOUBLE NULL DEFAULT 0,
  PRIMARY KEY (`idProduct`),
  INDEX `fk_Product_ProductType1_idx` (`idProductType` ASC),
  CONSTRAINT `fk_Product_ProductType1`
    FOREIGN KEY (`idProductType`)
    REFERENCES `sod_db`.`ProductType` (`idProductType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`OrderType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`OrderType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`OrderType` (
  `idOrderType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idOrderType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`OrderTypeTasks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`OrderTypeTasks` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`OrderTypeTasks` (
  `idOrderTypeTasks` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderType` INT UNSIGNED NOT NULL,
  `idTask` INT UNSIGNED NOT NULL,
  `description` VARCHAR(45) NULL,
  `time` INT NULL,
  `sortingOrder` INT NULL,
  PRIMARY KEY (`idOrderTypeTasks`),
  INDEX `fk_OrderTemplateTasks_Task1_idx` (`idTask` ASC),
  INDEX `fk_OrderTemplateTasks_OrderTemplate1_idx` (`idOrderType` ASC),
  CONSTRAINT `fk_OrderTemplateTasks_Task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `sod_db`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTemplateTasks_OrderTemplate1`
    FOREIGN KEY (`idOrderType`)
    REFERENCES `sod_db`.`OrderType` (`idOrderType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Orders` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Orders` (
  `idOrder` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT UNSIGNED NOT NULL,
  `idOrderType` INT UNSIGNED NOT NULL,
  `idAddressPickup` INT NULL COMMENT 'Not froreing key ',
  `idAddressDeliver` INT NULL,
  `time` INT NULL,
  `price` DOUBLE NULL,
  `status` INT NULL,
  `comments` VARCHAR(250) NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `pickUpDate` DATETIME NULL,
  `deliverDate` DATETIME NULL,
  PRIMARY KEY (`idOrder`),
  INDEX `fk_Order_OrderTemplate1_idx` (`idOrderType` ASC),
  INDEX `fk_Order_Clients1_idx` (`idClient` ASC),
  CONSTRAINT `fk_Order_OrderTemplate1`
    FOREIGN KEY (`idOrderType`)
    REFERENCES `sod_db`.`OrderType` (`idOrderType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Clients1`
    FOREIGN KEY (`idClient`)
    REFERENCES `sod_db`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`OrderTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`OrderTask` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`OrderTask` (
  `idOrderTask` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrder` INT UNSIGNED NOT NULL,
  `idTask` INT UNSIGNED NOT NULL,
  `time` DATETIME NULL,
  `comments` VARCHAR(255) NULL,
  PRIMARY KEY (`idOrderTask`),
  INDEX `fk_OrderTask_Task1_idx` (`idTask` ASC),
  INDEX `fk_OrderTask_Order1_idx` (`idOrder` ASC),
  CONSTRAINT `fk_OrderTask_Task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `sod_db`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTask_Order1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `sod_db`.`Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`AssetTaskOrder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`AssetTaskOrder` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`AssetTaskOrder` (
  `idAssetTaskOrder` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderTask` INT UNSIGNED NOT NULL,
  `Asset_idAsset` INT UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`EmployeeTaskOrder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`EmployeeTaskOrder` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`EmployeeTaskOrder` (
  `idEmployeeTaskOrder` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderTask` INT UNSIGNED NOT NULL,
  `idEmployee` INT UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL,
  PRIMARY KEY (`idEmployeeTaskOrder`),
  INDEX `fk_EmployeeTaskOrder_OrderTask1_idx` (`idOrderTask` ASC),
  INDEX `fk_EmployeeTaskOrder_Employee1_idx` (`idEmployee` ASC),
  CONSTRAINT `fk_EmployeeTaskOrder_OrderTask1`
    FOREIGN KEY (`idOrderTask`)
    REFERENCES `sod_db`.`OrderTask` (`idOrderTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EmployeeTaskOrder_Employee1`
    FOREIGN KEY (`idEmployee`)
    REFERENCES `sod_db`.`Employee` (`idEmployee`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceCategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceCategory` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceCategory` (
  `idServiceCategory` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(250) NULL,
  PRIMARY KEY (`idServiceCategory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceType` (
  `idServiceType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NULL,
  `description` VARCHAR(250) NULL,
  `price` DOUBLE NULL,
  `time` INT NULL,
  `idServiceCategory` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idServiceType`),
  INDEX `fk_ServiceType_ServiceCategory1_idx` (`idServiceCategory` ASC),
  CONSTRAINT `fk_ServiceType_ServiceCategory1`
    FOREIGN KEY (`idServiceCategory`)
    REFERENCES `sod_db`.`ServiceCategory` (`idServiceCategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Specs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Specs` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Specs` (
  `idSpecs` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'This table is for information about a service\nsuch as \njavon \nsuavisante\ntypo lavador\n',
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `optional` INT NULL DEFAULT 0,
  `max_qty` INT NULL DEFAULT 1,
  PRIMARY KEY (`idSpecs`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceTypeSpecs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceTypeSpecs` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceTypeSpecs` (
  `idServiceTypeSpecs` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idServiceType` INT UNSIGNED NOT NULL,
  `idSpecs` INT UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL,
  PRIMARY KEY (`idServiceTypeSpecs`),
  INDEX `fk_ServiceTypeSpecs_Specs1_idx` (`idSpecs` ASC),
  INDEX `fk_ServiceTypeSpecs_ServiceType1_idx` (`idServiceType` ASC),
  CONSTRAINT `fk_ServiceTypeSpecs_Specs1`
    FOREIGN KEY (`idSpecs`)
    REFERENCES `sod_db`.`Specs` (`idSpecs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeSpecs_ServiceType1`
    FOREIGN KEY (`idServiceType`)
    REFERENCES `sod_db`.`ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceTypeTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceTypeTask` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceTypeTask` (
  `idServiceTypeTask` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idServiceType` INT UNSIGNED NOT NULL,
  `idTask` INT UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL,
  `sortingOrder` INT NULL,
  PRIMARY KEY (`idServiceTypeTask`),
  INDEX `fk_ServiceTypeTask_Task1_idx` (`idTask` ASC),
  INDEX `fk_ServiceTypeTask_ServiceType1_idx` (`idServiceType` ASC),
  CONSTRAINT `fk_ServiceTypeTask_Task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `sod_db`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTypeTask_ServiceType1`
    FOREIGN KEY (`idServiceType`)
    REFERENCES `sod_db`.`ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Service` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Service` (
  `idService` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idServiceType` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(250) NULL,
  `price` DOUBLE NULL,
  `time` INT NULL,
  `created` DATETIME NULL,
  `updated` DATETIME NULL,
  `status` INT NULL,
  `idOrder` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idService`),
  INDEX `fk_Service_ServiceType1_idx` (`idServiceType` ASC),
  INDEX `fk_Service_Orders1_idx` (`idOrder` ASC),
  CONSTRAINT `fk_Service_ServiceType1`
    FOREIGN KEY (`idServiceType`)
    REFERENCES `sod_db`.`ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Service_Orders1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `sod_db`.`Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceTask`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceTask` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceTask` (
  `idServiceTask` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idService` INT UNSIGNED NOT NULL,
  `idTask` INT UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL,
  `started` DATETIME NULL,
  `ended` DATETIME NULL,
  PRIMARY KEY (`idServiceTask`),
  INDEX `fk_ServiceTask_Task1_idx` (`idTask` ASC),
  INDEX `fk_ServiceTask_Service1_idx` (`idService` ASC),
  CONSTRAINT `fk_ServiceTask_Task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `sod_db`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceTask_Service1`
    FOREIGN KEY (`idService`)
    REFERENCES `sod_db`.`Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceSpecs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceSpecs` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceSpecs` (
  `idServiceSpecs` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idService` INT UNSIGNED NOT NULL,
  `idSpecs` INT UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL,
  `quantity` INT NULL DEFAULT 0,
  `price` FLOAT NULL,
  `selectedValue` VARCHAR(250) NULL,
  PRIMARY KEY (`idServiceSpecs`),
  INDEX `fk_ServiceSpecs_Specs1_idx` (`idSpecs` ASC),
  INDEX `fk_ServiceSpecs_Service1_idx` (`idService` ASC),
  CONSTRAINT `fk_ServiceSpecs_Specs1`
    FOREIGN KEY (`idSpecs`)
    REFERENCES `sod_db`.`Specs` (`idSpecs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceSpecs_Service1`
    FOREIGN KEY (`idService`)
    REFERENCES `sod_db`.`Service` (`idService`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`EmployeeTaskService`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`EmployeeTaskService` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`EmployeeTaskService` (
  `idEmployeeTaskService` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEmployee` INT UNSIGNED NOT NULL,
  `idServiceTask` INT UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`AssetTaskService`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`AssetTaskService` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`AssetTaskService` (
  `idAssetTaskService` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idAsset` INT UNSIGNED NOT NULL,
  `idServiceTask` INT UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL,
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`OrderPickNDeliver`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`OrderPickNDeliver` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`OrderPickNDeliver` (
  `idOrderPickNDeliver` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `time` DATETIME NULL,
  `comments` VARCHAR(255) NULL,
  `typeAction` INT NULL DEFAULT 1 COMMENT '1 = pickup\n2 = delivery ',
  `idAddress` INT UNSIGNED NOT NULL,
  `idOrder` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idOrderPickNDeliver`),
  INDEX `fk_OrderPickNDeliver_Address1_idx` (`idAddress` ASC),
  INDEX `fk_OrderPickNDeliver_Orders1_idx` (`idOrder` ASC),
  CONSTRAINT `fk_OrderPickNDeliver_Address1`
    FOREIGN KEY (`idAddress`)
    REFERENCES `sod_db`.`Address` (`idAddress`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderPickNDeliver_Orders1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `sod_db`.`Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`PaymentInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`PaymentInfo` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`PaymentInfo` (
  `idPaymentInfo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrder` INT UNSIGNED NOT NULL,
  `type` INT NOT NULL COMMENT '0=cash\n1=stripe\n2=square\n',
  `transactionInfo` VARCHAR(250) NULL,
  PRIMARY KEY (`idPaymentInfo`),
  INDEX `fk_PaymentInfo_Orders1_idx` (`idOrder` ASC),
  CONSTRAINT `fk_PaymentInfo_Orders1`
    FOREIGN KEY (`idOrder`)
    REFERENCES `sod_db`.`Orders` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceType_has_OrderType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceType_has_OrderType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceType_has_OrderType` (
  `ServiceType_idServiceType` INT UNSIGNED NOT NULL,
  `OrderType_idOrderType` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`ServiceType_idServiceType`, `OrderType_idOrderType`),
  INDEX `fk_ServiceType_has_OrderType_OrderType1_idx` (`OrderType_idOrderType` ASC),
  INDEX `fk_ServiceType_has_OrderType_ServiceType1_idx` (`ServiceType_idServiceType` ASC),
  CONSTRAINT `fk_ServiceType_has_OrderType_ServiceType1`
    FOREIGN KEY (`ServiceType_idServiceType`)
    REFERENCES `sod_db`.`ServiceType` (`idServiceType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServiceType_has_OrderType_OrderType1`
    FOREIGN KEY (`OrderType_idOrderType`)
    REFERENCES `sod_db`.`OrderType` (`idOrderType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ClientPaymentInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ClientPaymentInfo` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ClientPaymentInfo` (
  `idClientPaymentInfo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` INT NULL DEFAULT 0 COMMENT 'CASH = 0; \nCC = 1; // credit card \nPAYPAL = 2;  \nOTHER = 4; // NOT DEFINED \nSTRIPE = 3; ',
  `token` VARCHAR(250) NULL,
  `idClient` INT UNSIGNED NOT NULL,
  `prefered` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`idClientPaymentInfo`),
  INDEX `fk_ClientPaymentInfo_Clients1_idx` (`idClient` ASC),
  CONSTRAINT `fk_ClientPaymentInfo_Clients1`
    FOREIGN KEY (`idClient`)
    REFERENCES `sod_db`.`Clients` (`idClient`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`SpecsValues`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`SpecsValues` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`SpecsValues` (
  `idSpecsValues` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idSpecs` INT UNSIGNED NOT NULL,
  `type` INT NULL DEFAULT 1,
  `value` VARCHAR(45) NULL,
  `idProductType` INT NULL DEFAULT 0,
  `serviceIncrement` DOUBLE NULL DEFAULT 0,
  `prefered` INT NULL DEFAULT 0,
  PRIMARY KEY (`idSpecsValues`),
  INDEX `fk_SpecsValues_Specs1_idx` (`idSpecs` ASC),
  CONSTRAINT `fk_SpecsValues_Specs1`
    FOREIGN KEY (`idSpecs`)
    REFERENCES `sod_db`.`Specs` (`idSpecs`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Routes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Routes` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Routes` (
  `idRoutes` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(105) NULL,
  `description` VARCHAR(200) NULL,
  `category` INT NULL DEFAULT 1 COMMENT '1 = departamentos\n2 = offices\n3 = cliente\n',
  PRIMARY KEY (`idRoutes`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Stops`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Stops` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Stops` (
  `idStops` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `time` INT NULL DEFAULT 10,
  `arriveAt` VARCHAR(50) NULL DEFAULT 7,
  `stopAction` INT NULL DEFAULT 1 COMMENT '1 = pickup\n2 = deliver',
  `idRoutes` INT NOT NULL,
  `type` INT NULL DEFAULT 0 COMMENT '0 = address\n1 = client',
  `idAddress` INT NULL,
  PRIMARY KEY (`idStops`),
  INDEX `fk_Stops_Routes1_idx` (`idRoutes` ASC),
  CONSTRAINT `fk_Stops_Routes1`
    FOREIGN KEY (`idRoutes`)
    REFERENCES `sod_db`.`Routes` (`idRoutes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`AddressRoutes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`AddressRoutes` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`AddressRoutes` (
  `idAddressRoute` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zipcode` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `address` VARCHAR(250) NULL,
  `address2` VARCHAR(250) NULL,
  `comments` VARCHAR(255) NULL,
  `lat` DECIMAL(10,8) NULL,
  `lng` DECIMAL(11,8) NULL,
  PRIMARY KEY (`idAddressRoute`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`CalendarRoute`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`CalendarRoute` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`CalendarRoute` (
  `idCalendarRoute` INT NOT NULL AUTO_INCREMENT,
  `day` INT NULL DEFAULT 1,
  `time` VARCHAR(50) NULL DEFAULT '8:00',
  `idRoutes` INT NOT NULL,
  PRIMARY KEY (`idCalendarRoute`),
  INDEX `fk_Calendar_Routes1_idx` (`idRoutes` ASC),
  CONSTRAINT `fk_Calendar_Routes1`
    FOREIGN KEY (`idRoutes`)
    REFERENCES `sod_db`.`Routes` (`idRoutes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `sod_db` ;

-- -----------------------------------------------------
-- Placeholder table for view `sod_db`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `sod_db`.`view1`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `sod_db`.`view1` ;
DROP TABLE IF EXISTS `sod_db`.`view1`;
USE `sod_db`;

USE `sod_db`;

DELIMITER $$

USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Clients_BEFORE_INSERT` $$
USE `sod_db`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sod_db`.`Clients_BEFORE_INSERT` BEFORE INSERT ON `Clients` FOR EACH ROW
BEGIN
SET NEW.created = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Clients_BEFORE_UPDATE` $$
USE `sod_db`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sod_db`.`Clients_BEFORE_UPDATE` BEFORE UPDATE ON `Clients` FOR EACH ROW
BEGIN
SET NEW.updated = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Employee_BEFORE_INSERT` $$
USE `sod_db`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sod_db`.`Employee_BEFORE_INSERT` BEFORE INSERT ON `Employee` FOR EACH ROW
BEGIN
SET NEW.created = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Employee_BEFORE_UPDATE` $$
USE `sod_db`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sod_db`.`Employee_BEFORE_UPDATE` BEFORE UPDATE ON `Employee` FOR EACH ROW
BEGIN
SET NEW.updated = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Orders_BEFORE_INSERT` $$
USE `sod_db`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sod_db`.`Orders_BEFORE_INSERT` BEFORE INSERT ON `Orders` FOR EACH ROW
BEGIN
SET NEW.created = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Orders_BEFORE_UPDATE` $$
USE `sod_db`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sod_db`.`Orders_BEFORE_UPDATE` BEFORE UPDATE ON `Orders` FOR EACH ROW
BEGIN
SET NEW.updated = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Service_BEFORE_INSERT` $$
USE `sod_db`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sod_db`.`Service_BEFORE_INSERT` BEFORE INSERT ON `Service` FOR EACH ROW
BEGIN
SET NEW.created = NOW();
END$$


USE `sod_db`$$
DROP TRIGGER IF EXISTS `sod_db`.`Service_BEFORE_UPDATE` $$
USE `sod_db`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sod_db`.`Service_BEFORE_UPDATE` BEFORE UPDATE ON `Service` FOR EACH ROW
BEGIN
SET NEW.updated = NOW();
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `sod_db`.`Clients`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Clients` (`idClient`, `email`, `password`, `name`, `lastName`, `twitter`, `created`, `updated`) VALUES (1, 'email@domain.com', 'aa', 'Name', 'Lastname', 'twitter', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`PhoneNumber`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`PhoneNumber` (`idPhoneNumber`, `idClient`, `number`, `prefered`) VALUES (1, 1, '123456789', 1);
INSERT INTO `sod_db`.`PhoneNumber` (`idPhoneNumber`, `idClient`, `number`, `prefered`) VALUES (2, 1, '987654321', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`Address`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Address` (`idAddress`, `idClient`, `country`, `state`, `zipcode`, `city`, `address`, `address2`, `comments`, `lat`, `lng`, `prefered`) VALUES (1, 1, 'Mexico', 'Jalisco', '44540', 'Guadalajara', 'Peninsula', NULL, NULL, 20.64551528, -103.39244127, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`TaskType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`TaskType` (`idTaskType`, `name`, `description`) VALUES (1, 'Servicios', 'utilizado como placeholder');
INSERT INTO `sod_db`.`TaskType` (`idTaskType`, `name`, `description`) VALUES (3, 'planchado', 'todo lo relevante a planchado');
INSERT INTO `sod_db`.`TaskType` (`idTaskType`, `name`, `description`) VALUES (4, 'transporte', 'recojer o entregar pedidos');
INSERT INTO `sod_db`.`TaskType` (`idTaskType`, `name`, `description`) VALUES (2, 'lavado', 'todo lo relevante a lavar ropa');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`EmployeeType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`EmployeeType` (`idEmployeeType`, `name`, `description`) VALUES (1, 'admin', NULL);
INSERT INTO `sod_db`.`EmployeeType` (`idEmployeeType`, `name`, `description`) VALUES (2, 'general', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`AssetType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`AssetType` (`idAssetType`, `name`, `description`) VALUES (1, 'lavado', NULL);
INSERT INTO `sod_db`.`AssetType` (`idAssetType`, `name`, `description`) VALUES (2, 'planchado', NULL);
INSERT INTO `sod_db`.`AssetType` (`idAssetType`, `name`, `description`) VALUES (3, 'transporte', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`ProductType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`ProductType` (`idProductType`, `name`, `description`) VALUES (1, 'detergente', NULL);
INSERT INTO `sod_db`.`ProductType` (`idProductType`, `name`, `description`) VALUES (2, 'suavisante', NULL);
INSERT INTO `sod_db`.`ProductType` (`idProductType`, `name`, `description`) VALUES (3, 'blanqueador', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`Task`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Task` (`idTask`, `idTaskType`, `name`, `description`) VALUES (1, 1, 'SERVICIOS', 'lavado de ropa general (mezclilla, etc)');
INSERT INTO `sod_db`.`Task` (`idTask`, `idTaskType`, `name`, `description`) VALUES (2, 2, 'lavado general', 'lavado de ropa general');
INSERT INTO `sod_db`.`Task` (`idTask`, `idTaskType`, `name`, `description`) VALUES (3, 4, 'recojer', 'recojer pedido');
INSERT INTO `sod_db`.`Task` (`idTask`, `idTaskType`, `name`, `description`) VALUES (4, 4, 'entregar', 'entregar pedido');
INSERT INTO `sod_db`.`Task` (`idTask`, `idTaskType`, `name`, `description`) VALUES (5, 2, 'doblar', 'doblado');
INSERT INTO `sod_db`.`Task` (`idTask`, `idTaskType`, `name`, `description`) VALUES (6, 3, 'planchar', 'planchar');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`Employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Employee` (`idEmployee`, `idEmployeeType`, `status`, `name`, `lastname`, `password`, `username`, `created`, `updated`, `email`) VALUES (1, 1, 1, 'user', 'user', NULL, 'user', NULL, NULL, 'email@domain.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`Asset`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Asset` (`idAsset`, `idAssetType`, `status`, `name`, `description`) VALUES (1, 1, 1, 'lavadora 1', 'lavadora roja');
INSERT INTO `sod_db`.`Asset` (`idAsset`, `idAssetType`, `status`, `name`, `description`) VALUES (2, 1, 1, 'lavadora 2', 'lavadora roja rota');
INSERT INTO `sod_db`.`Asset` (`idAsset`, `idAssetType`, `status`, `name`, `description`) VALUES (3, 3, 1, 'moto1', 'moto roja placas aaabbb92');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`Product`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Product` (`idProduct`, `idProductType`, `status`, `name`, `description`, `price`, `serviceIncrement`) VALUES (1, 1, 1, 'Ariel', NULL, 100, 0);
INSERT INTO `sod_db`.`Product` (`idProduct`, `idProductType`, `status`, `name`, `description`, `price`, `serviceIncrement`) VALUES (2, 1, 1, 'Otro', NULL, 120, 0.15);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`OrderType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`OrderType` (`idOrderType`, `name`, `description`) VALUES (1, 'Order 1', 'Pickup + service + deliver');
INSERT INTO `sod_db`.`OrderType` (`idOrderType`, `name`, `description`) VALUES (2, 'Order 2', 'Pickup + service');
INSERT INTO `sod_db`.`OrderType` (`idOrderType`, `name`, `description`) VALUES (3, 'Order 3', 'Service + deliver');
INSERT INTO `sod_db`.`OrderType` (`idOrderType`, `name`, `description`) VALUES (4, 'Order 4', 'Service');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`OrderTypeTasks`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`OrderTypeTasks` (`idOrderTypeTasks`, `idOrderType`, `idTask`, `description`, `time`, `sortingOrder`) VALUES (1, 1, 3, 'recojer', 20, 1);
INSERT INTO `sod_db`.`OrderTypeTasks` (`idOrderTypeTasks`, `idOrderType`, `idTask`, `description`, `time`, `sortingOrder`) VALUES (2, 1, 1, 'Servicios', 100, 2);
INSERT INTO `sod_db`.`OrderTypeTasks` (`idOrderTypeTasks`, `idOrderType`, `idTask`, `description`, `time`, `sortingOrder`) VALUES (3, 1, 4, 'entrega', 20, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`ServiceCategory`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`ServiceCategory` (`idServiceCategory`, `name`, `description`) VALUES (1, 'lavado', 'lavado general');
INSERT INTO `sod_db`.`ServiceCategory` (`idServiceCategory`, `name`, `description`) VALUES (2, 'planchado', 'planchado');
INSERT INTO `sod_db`.`ServiceCategory` (`idServiceCategory`, `name`, `description`) VALUES (3, 'otros', 'otros');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`ServiceType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`, `idServiceCategory`) VALUES (1, 'lavado general', NULL, 100, 2, 1);
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`, `idServiceCategory`) VALUES (2, 'planchado 1', NULL, 100, 2, 2);
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`, `idServiceCategory`) VALUES (3, 'tintoreria', NULL, 100, 2, 3);
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`, `idServiceCategory`) VALUES (4, 'costura', NULL, 100, 2, 3);
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`, `idServiceCategory`) VALUES (5, 'lavado delicado', 'ropa interior', 100, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`Specs`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Specs` (`idSpecs`, `name`, `description`, `optional`, `max_qty`) VALUES (1, 'tamanio', 'size of order', 0, 1);
INSERT INTO `sod_db`.`Specs` (`idSpecs`, `name`, `description`, `optional`, `max_qty`) VALUES (2, 'jabon', 'detergente a utilizarse', 0, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`ServiceTypeSpecs`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`ServiceTypeSpecs` (`idServiceTypeSpecs`, `idServiceType`, `idSpecs`, `comments`) VALUES (1, 1, 1, 'tamanio');
INSERT INTO `sod_db`.`ServiceTypeSpecs` (`idServiceTypeSpecs`, `idServiceType`, `idSpecs`, `comments`) VALUES (2, 1, 2, 'jabon');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`ServiceTypeTask`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`ServiceTypeTask` (`idServiceTypeTask`, `idServiceType`, `idTask`, `comments`, `sortingOrder`) VALUES (1, 1, 2, 'lavado general', 1);
INSERT INTO `sod_db`.`ServiceTypeTask` (`idServiceTypeTask`, `idServiceType`, `idTask`, `comments`, `sortingOrder`) VALUES (2, 1, 5, 'doblar', 2);
INSERT INTO `sod_db`.`ServiceTypeTask` (`idServiceTypeTask`, `idServiceType`, `idTask`, `comments`, `sortingOrder`) VALUES (3, 2, 6, 'planchar', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`ServiceType_has_OrderType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (1, 1);
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (2, 1);
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (3, 1);
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (4, 1);
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (5, 1);
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (1, 2);
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (2, 2);
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (3, 2);
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (4, 2);
INSERT INTO `sod_db`.`ServiceType_has_OrderType` (`ServiceType_idServiceType`, `OrderType_idOrderType`) VALUES (5, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`ClientPaymentInfo`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`ClientPaymentInfo` (`idClientPaymentInfo`, `type`, `token`, `idClient`, `prefered`) VALUES (1, 1, '01234', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`SpecsValues`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`SpecsValues` (`idSpecsValues`, `idSpecs`, `type`, `value`, `idProductType`, `serviceIncrement`, `prefered`) VALUES (1, 1, 1, 'C', 0, 0, NULL);
INSERT INTO `sod_db`.`SpecsValues` (`idSpecsValues`, `idSpecs`, `type`, `value`, `idProductType`, `serviceIncrement`, `prefered`) VALUES (2, 1, 1, 'M', 0, 0.20, NULL);
INSERT INTO `sod_db`.`SpecsValues` (`idSpecsValues`, `idSpecs`, `type`, `value`, `idProductType`, `serviceIncrement`, `prefered`) VALUES (3, 1, 1, 'G', 0, 0.30, NULL);
INSERT INTO `sod_db`.`SpecsValues` (`idSpecsValues`, `idSpecs`, `type`, `value`, `idProductType`, `serviceIncrement`, `prefered`) VALUES (4, 2, 2, NULL, 1, 0, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`Routes`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Routes` (`idRoutes`, `name`, `description`, `category`) VALUES (1, 'route1', 'route 1 ', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`Stops`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Stops` (`idStops`, `name`, `description`, `time`, `arriveAt`, `stopAction`, `idRoutes`, `type`, `idAddress`) VALUES (1, 'stop 1', 'stop 1 description', 20, '9:30', 1, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`AddressRoutes`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`AddressRoutes` (`idAddressRoute`, `country`, `state`, `zipcode`, `city`, `address`, `address2`, `comments`, `lat`, `lng`) VALUES (1, 'Mexico', 'Jalisco', '44540', 'Guadalajara', 'Calle', 'Colonia', 'no tiene timbre... ', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`CalendarRoute`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`CalendarRoute` (`idCalendarRoute`, `day`, `time`, `idRoutes`) VALUES (1, 1, '9:30', 1);

COMMIT;
