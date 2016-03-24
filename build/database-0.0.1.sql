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
  `prefered` INT NULL DEFAULT 0,
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
  `lat` FLOAT NULL,
  `long` FLOAT NULL,
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
  `price` FLOAT NULL DEFAULT 0,
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
-- Table `sod_db`.`ServiceType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`ServiceType` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceType` (
  `idServiceType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NULL,
  `description` VARCHAR(250) NULL,
  `price` DOUBLE NULL,
  `time` INT NULL,
  PRIMARY KEY (`idServiceType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Specs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`Specs` ;

CREATE TABLE IF NOT EXISTS `sod_db`.`Specs` (
  `idSpecs` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'This table is for information about a service\nsuch as \njavon \nsuavisante\ntypo lavador\n',
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
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
  PRIMARY KEY (`idService`),
  INDEX `fk_Service_ServiceType1_idx` (`idServiceType` ASC),
  CONSTRAINT `fk_Service_ServiceType1`
    FOREIGN KEY (`idServiceType`)
    REFERENCES `sod_db`.`ServiceType` (`idServiceType`)
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
  `type` INT NULL DEFAULT 0 COMMENT '0=stripe',
  `token` VARCHAR(250) NULL,
  `idClient` INT UNSIGNED NOT NULL,
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
  `type` INT NULL DEFAULT '1',
  `value` VARCHAR(45) NULL,
  `idProduct` INT NULL DEFAULT 0,
  PRIMARY KEY (`idSpecsValues`),
  INDEX `fk_SpecsValues_Specs1_idx` (`idSpecs` ASC),
  CONSTRAINT `fk_SpecsValues_Specs1`
    FOREIGN KEY (`idSpecs`)
    REFERENCES `sod_db`.`Specs` (`idSpecs`)
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `sod_db`.`TaskType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`TaskType` (`idTaskType`, `name`, `description`) VALUES (DEFAULT, 'lavado', 'todo lo relevante a lavar ropa');
INSERT INTO `sod_db`.`TaskType` (`idTaskType`, `name`, `description`) VALUES (DEFAULT, 'planchado', 'todo lo relevante a planchado');
INSERT INTO `sod_db`.`TaskType` (`idTaskType`, `name`, `description`) VALUES (DEFAULT, 'transporte', 'recojer o entregar pedidos');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`EmployeeType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`EmployeeType` (`idEmployeeType`, `name`, `description`) VALUES (DEFAULT, 'admin', NULL);
INSERT INTO `sod_db`.`EmployeeType` (`idEmployeeType`, `name`, `description`) VALUES (DEFAULT, 'general', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`AssetType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`AssetType` (`idAssetType`, `name`, `description`) VALUES (DEFAULT, 'lavado', NULL);
INSERT INTO `sod_db`.`AssetType` (`idAssetType`, `name`, `description`) VALUES (DEFAULT, 'planchado', NULL);
INSERT INTO `sod_db`.`AssetType` (`idAssetType`, `name`, `description`) VALUES (DEFAULT, 'transporte', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`ProductType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`ProductType` (`idProductType`, `name`, `description`) VALUES (DEFAULT, 'none', NULL);
INSERT INTO `sod_db`.`ProductType` (`idProductType`, `name`, `description`) VALUES (DEFAULT, 'detergente', NULL);
INSERT INTO `sod_db`.`ProductType` (`idProductType`, `name`, `description`) VALUES (DEFAULT, 'blanqueador', NULL);
INSERT INTO `sod_db`.`ProductType` (`idProductType`, `name`, `description`) VALUES (DEFAULT, 'suavisante', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`OrderType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`OrderType` (`idOrderType`, `name`, `description`) VALUES (DEFAULT, 'Order 1', 'Pickup + service + deliver');
INSERT INTO `sod_db`.`OrderType` (`idOrderType`, `name`, `description`) VALUES (DEFAULT, 'Order 2', 'Pickup + service');
INSERT INTO `sod_db`.`OrderType` (`idOrderType`, `name`, `description`) VALUES (DEFAULT, 'Order 3', 'Service + deliver');
INSERT INTO `sod_db`.`OrderType` (`idOrderType`, `name`, `description`) VALUES (DEFAULT, 'Order 4', 'Service');

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`ServiceType`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`) VALUES (DEFAULT, 'lavado', NULL, 100, 2);
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`) VALUES (DEFAULT, 'planchado', NULL, 100, 2);
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`) VALUES (DEFAULT, 'tintoreria', NULL, 100, 2);
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`) VALUES (DEFAULT, 'costura', NULL, 100, 2);
INSERT INTO `sod_db`.`ServiceType` (`idServiceType`, `name`, `description`, `price`, `time`) VALUES (DEFAULT, 'lavado delicado', 'ropa interior', 100, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `sod_db`.`Specs`
-- -----------------------------------------------------
START TRANSACTION;
USE `sod_db`;
INSERT INTO `sod_db`.`Specs` (`idSpecs`, `name`, `description`) VALUES (DEFAULT, 'size', NULL);

COMMIT;

