


ALTER TABLE `pacientes` ADD `paci_vinculacion_eps` VARCHAR(100) NULL DEFAULT NULL COMMENT 'tipo de vinculacion a la eps' AFTER `paci_eps`;
CREATE TABLE `javaphc`.`responsables_paciente` ( `repa_id` INT(30) NOT NULL AUTO_INCREMENT , `repa_nombre` VARCHAR(100) NULL , `repa_documento` VARCHAR(20) NULL , `repa_parentesco` VARCHAR(20) NULL , `repa_direccion` VARCHAR(50) NULL , `repa_telefono` VARCHAR(50) NULL , `repa_tipo` VARCHAR(20) NULL , `tick_id` INT(30) NOT NULL , PRIMARY KEY (`repa_id`)) ENGINE = InnoDB;
ALTER TABLE `responsables_paciente` ADD `paci_id` INT(30) NULL AFTER `repa_id`;
CREATE TABLE `javaphc`.`tipo_vinculacion_eps` ( `tvep_id` INT(30) NOT NULL AUTO_INCREMENT , `tvep_descripcion` VARCHAR(50) NULL , `tvep_observacion` VARCHAR(100) NULL , PRIMARY KEY (`tvep_id`)) ENGINE = InnoDB;