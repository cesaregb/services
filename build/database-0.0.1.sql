-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sod_db
-- -----------------------------------------------------
-- This is the initial_scheema for the service on demand application
-- 

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
CREATE TABLE IF NOT EXISTS `sod_db`.`Clients` (
  `idClient` INT UNSIGNED NOT NULL,
  `email` VARCHAR(100) NULL,
  `password` CHAR(128) NULL,
  `name` VARCHAR(250) NULL,
  `lasName` VARCHAR(250) NULL,
  `phoneNumber` VARCHAR(250) NULL,
  `twitter` VARCHAR(250) NULL,
  PRIMARY KEY (`idClient`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`SocialNetworks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`SocialNetworks` (
  `idSocialNetworks` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `domain` VARCHAR(45) NULL,
  PRIMARY KEY (`idSocialNetworks`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`AccessKey`
-- -----------------------------------------------------
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
CREATE TABLE IF NOT EXISTS `sod_db`.`PhoneNumber` (
  `idPhoneNumber` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT UNSIGNED NOT NULL,
  `number` VARCHAR(45) NULL,
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
CREATE TABLE IF NOT EXISTS `sod_db`.`Address` (
  `idAddress` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT UNSIGNED NOT NULL,
  `country` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `address` VARCHAR(250) NULL,
  `address2` VARCHAR(250) NULL,
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
CREATE TABLE IF NOT EXISTS `sod_db`.`TaskType` (
  `idTaskType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idTaskType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`EmployeeType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`EmployeeType` (
  `idEmployeeType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idEmployeeType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`AssetType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`AssetType` (
  `idAssetType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idAssetType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ProductType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`ProductType` (
  `idProductType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idProductType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Task`
-- -----------------------------------------------------
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
CREATE TABLE IF NOT EXISTS `sod_db`.`Employee` (
  `idEmployee` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idEmployeeType` INT UNSIGNED NOT NULL,
  `status` INT NOT NULL DEFAULT 0,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `password` LONGTEXT NULL,
  `username` VARCHAR(45) NULL,
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
-- Table `sod_db`.`OrderTemplate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`OrderTemplate` (
  `idOrderTemplate` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`idOrderTemplate`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`OrderTemplateTasks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`OrderTemplateTasks` (
  `idOrderTemplateTasks` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idOrderTemplate` INT UNSIGNED NOT NULL,
  `idTask` INT UNSIGNED NOT NULL,
  `description` VARCHAR(45) NULL,
  `time` INT NULL,
  PRIMARY KEY (`idOrderTemplateTasks`),
  INDEX `fk_OrderTemplateTasks_Task1_idx` (`idTask` ASC),
  INDEX `fk_OrderTemplateTasks_OrderTemplate1_idx` (`idOrderTemplate` ASC),
  CONSTRAINT `fk_OrderTemplateTasks_Task1`
    FOREIGN KEY (`idTask`)
    REFERENCES `sod_db`.`Task` (`idTask`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrderTemplateTasks_OrderTemplate1`
    FOREIGN KEY (`idOrderTemplate`)
    REFERENCES `sod_db`.`OrderTemplate` (`idOrderTemplate`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`Order` (
  `idOrder` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idClient` INT UNSIGNED NOT NULL,
  `idOrderTemplate` INT UNSIGNED NOT NULL,
  `idAddressPickup` INT NULL COMMENT 'Not froreing key ',
  `idAddressDeliver` INT NULL,
  `date` DATETIME NULL,
  `price` DOUBLE NULL,
  `status` INT NULL,
  `comments` VARCHAR(250) NULL,
  PRIMARY KEY (`idOrder`),
  INDEX `fk_Order_OrderTemplate1_idx` (`idOrderTemplate` ASC),
  INDEX `fk_Order_Clients1_idx` (`idClient` ASC),
  CONSTRAINT `fk_Order_OrderTemplate1`
    FOREIGN KEY (`idOrderTemplate`)
    REFERENCES `sod_db`.`OrderTemplate` (`idOrderTemplate`)
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
    REFERENCES `sod_db`.`Order` (`idOrder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`AssetTaskOrder`
-- -----------------------------------------------------
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
CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceType` (
  `idServiceType` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NULL,
  `description` VARCHAR(250) NULL,
  `price` DOUBLE NULL,
  `time` DATETIME NULL,
  PRIMARY KEY (`idServiceType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`Specs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`Specs` (
  `idSpecs` INT UNSIGNED NOT NULL,
  `idProductType` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`idSpecs`),
  INDEX `fk_Specs_ProductType1_idx` (`idProductType` ASC),
  CONSTRAINT `fk_Specs_ProductType1`
    FOREIGN KEY (`idProductType`)
    REFERENCES `sod_db`.`ProductType` (`idProductType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sod_db`.`ServiceTypeSpecs`
-- -----------------------------------------------------
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
CREATE TABLE IF NOT EXISTS `sod_db`.`Service` (
  `idService` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idServiceType` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(250) NULL,
  `price` DOUBLE NULL,
  `time` DATETIME NULL,
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
CREATE TABLE IF NOT EXISTS `sod_db`.`ServiceSpecs` (
  `idServiceTypeSpecs` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `idService` INT UNSIGNED NOT NULL,
  `idSpecs` INT UNSIGNED NOT NULL,
  `comments` VARCHAR(250) NULL,
  PRIMARY KEY (`idServiceTypeSpecs`),
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

USE `sod_db` ;

-- -----------------------------------------------------
-- Placeholder table for view `sod_db`.`view1`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sod_db`.`view1` (`id` INT);

-- -----------------------------------------------------
-- View `sod_db`.`view1`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sod_db`.`view1`;
USE `sod_db`;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
