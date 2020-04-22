CREATE TABLE `ShinShin`.`proveedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `id_marca` INT NULL,
  `active` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_proveedor_marca_idx` (`id_marca` ASC),
  CONSTRAINT `fk_proveedor_marca`
    FOREIGN KEY (`id_marca`)
    REFERENCES `ShinShin`.`catalogo_marca` (`id_catalogo_marca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    
CREATE TABLE `proveedor_authority` (
  `authority_id` int(11) DEFAULT NULL,
  `proveedor_id` int(11) DEFAULT NULL,
  KEY `fk_authority_p_idx` (`authority_id`),
  KEY `fk_proveedor_idx` (`proveedor_id`),
  CONSTRAINT `fk_authority_p` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_proveedor` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `ShinShin`.`contacto` 
ADD COLUMN `nombre` VARCHAR(80) NULL AFTER `id_usuario`,
ADD COLUMN `email` VARCHAR(100) NULL AFTER `nombre`;

-- ALTER TABLE `ShinShin`.`proveedor` 
-- ADD COLUMN `active` TINYINT(1) NULL DEFAULT 1 AFTER `id_marca`;

