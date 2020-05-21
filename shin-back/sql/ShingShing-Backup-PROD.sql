# ************************************************************
# Sequel Pro SQL dump
# Versi�n 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: shinshin.cvvjdff1wc6u.us-east-2.rds.amazonaws.com (MySQL 5.7.22-log)
# Base de datos: ShinShin
# Tiempo de Generaci�n: 2020-05-20 23:49:27 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Volcado de tabla authority
# ------------------------------------------------------------

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;

INSERT INTO `authority` (`id`, `name`)
VALUES
	(1,'ROLE_ADMIN'),
	(2,'ROLE_USER');

/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_banco
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_banco`;

CREATE TABLE `catalogo_banco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Volcado de tabla catalogo_diccionario_tiendas
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_diccionario_tiendas`;

CREATE TABLE `catalogo_diccionario_tiendas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clave_diccionario` varchar(45) DEFAULT NULL,
  `valor_diccionario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_diccionario_tiendas` WRITE;
/*!40000 ALTER TABLE `catalogo_diccionario_tiendas` DISABLE KEYS */;

INSERT INTO `catalogo_diccionario_tiendas` (`id`, `clave_diccionario`, `valor_diccionario`)
VALUES
	(1,'xxo','OXXO'),
	(2,'OXXO','OXXO'),
	(3,'xxo','OXXO'),
	(4,'Oxx0','OXXO'),
	(5,'Oxxa','OXXO'),
	(6,'Oxx0','OXXO'),
	(7,'GxD','OXXO'),
	(8,'Oxxo','OXXO'),
	(9,'Oyxo','OXXO'),
	(10,'OvxO','OXXO'),
	(11,'OwxO','OXXO'),
	(12,'Gxxo','OXXO'),
	(13,'xxo','OXXO'),
	(14,'Walmart','WALMART'),
	(15,'Wal mart','WALMART'),
	(16,'Soriona','SORIANA'),
	(17,'Soriana','SORIANA'),
	(18,'7 ELEVEN','7ELEVEN'),
	(19,'7-Eleven','7ELEVEN');

/*!40000 ALTER TABLE `catalogo_diccionario_tiendas` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_marca
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_marca`;

CREATE TABLE `catalogo_marca` (
  `id_catalogo_marca` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_marca` varchar(45) NOT NULL,
  `img_url` varchar(200) DEFAULT NULL,
  `active` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id_catalogo_marca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_marca` WRITE;
/*!40000 ALTER TABLE `catalogo_marca` DISABLE KEYS */;

INSERT INTO `catalogo_marca` (`id_catalogo_marca`, `nombre_marca`, `img_url`, `active`)
VALUES
	(1,'Bonafont','',1),
	(2,'Coca Cola','http://res.cloudinary.com/shingshing/image/upload/v1588108110/shingshing/marcas/Coca%20Cola.png',1),
	(3,'Nestle',NULL,0),
	(4,'Herdez',NULL,0),
	(5,'Sony',NULL,0),
	(6,'Microsoft',NULL,0),
	(7,'Nintendo',NULL,0),
	(8,'Kellogs',NULL,0),
	(9,'AXE',NULL,1),
	(10,'Herdez','http://res.cloudinary.com/shingshing/image/upload/v1581459501/shingshing/marcas/HERDEZ.png',1),
	(11,'Starder','',0),
	(12,'Del Valle',NULL,0),
	(13,'Royal','http://res.cloudinary.com/shingshing/image/upload/v1570290421/shingshing/Royal.png',0),
	(14,'Yakult','',0),
	(15,'Petalo','',1),
	(16,'Santa Clara ','',1),
	(17,'Stefano','',1),
	(18,'MegaChamoy','',1),
	(19,'San Juan','',1),
	(20,'Alpura','',1),
	(21,'Del Fuerte','',1),
	(22,'Danone','',1),
	(23,'Ades','',1),
	(24,'Colgate','',1),
	(25,'Nestlé','',1),
	(26,'Lala','',1),
	(27,'Epura','',1),
	(28,'Yakult','',1),
	(29,'Chips Ahoy','',0),
	(30,'Granvita','',1),
	(31,'Splash','',1),
	(32,'Santa Clara ','',1),
	(33,'Kleenex Cottonelle','',1),
	(34,'Soriana ','',1),
	(35,'Quaker','',1),
	(36,'Fabuloso','',1),
	(37,'Axion','',1),
	(38,'Inova','',1),
	(39,'Tressemé','',1),
	(40,'Milpa Real ','',1),
	(41,'Tía Rosa','',1),
	(42,'Quaker State','',0),
	(43,'Hola, soy un Demo','',0),
	(44,'Nueva marca','',0),
	(45,'Hola, soy un Demooo','',0),
	(46,'Sabritas','http://res.cloudinary.com/shingshing/image/upload/v1588544528/shingshing/marcas/Sabritas.png',1),
	(47,'Mead Johnson','',1),
	(48,'Heineken','',1),
	(49,'Pascual','',1),
	(50,'Rayter','',0),
	(51,'Sigma','',1),
	(52,'Natural GOOD SHAPE ','',1),
	(53,'Marinela','',1),
	(54,'McCORMICK','',1),
	(55,'DEMO 4 MAYO','',0),
	(56,'Demo 5 de mayo','',0),
	(57,'La Morena','',1),
	(58,'Alpura','',0),
	(59,'GAMESA','',1),
	(60,'D\' Gari','',1),
	(61,'Crosse & Blackwell','',1),
	(62,'Valentina','',1),
	(63,'Charras','',1);

/*!40000 ALTER TABLE `catalogo_marca` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_medios_bonificacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_medios_bonificacion`;

CREATE TABLE `catalogo_medios_bonificacion` (
  `id_catalogo_medio_bonificacion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_medio_bonificacion` varchar(45) NOT NULL,
  `active` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id_catalogo_medio_bonificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_medios_bonificacion` WRITE;
/*!40000 ALTER TABLE `catalogo_medios_bonificacion` DISABLE KEYS */;

INSERT INTO `catalogo_medios_bonificacion` (`id_catalogo_medio_bonificacion`, `nombre_medio_bonificacion`, `active`)
VALUES
	(1,'Bancaria',1),
	(2,'Paypal',1),
	(3,'Recarga telefónica',1),
	(4,'Nuevo!!',0);

/*!40000 ALTER TABLE `catalogo_medios_bonificacion` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_redes_sociales
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_redes_sociales`;

CREATE TABLE `catalogo_redes_sociales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_red_social` varchar(45) NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_redes_sociales` WRITE;
/*!40000 ALTER TABLE `catalogo_redes_sociales` DISABLE KEYS */;

INSERT INTO `catalogo_redes_sociales` (`id`, `nombre_red_social`)
VALUES
	(1,'GOOGLE'),
	(2,'FACEBOOK');

/*!40000 ALTER TABLE `catalogo_redes_sociales` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_sexo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_sexo`;

CREATE TABLE `catalogo_sexo` (
  `id_catalogo_sexo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_sexo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_catalogo_sexo`),
  UNIQUE KEY `id_catalogo_sexo_UNIQUE` (`id_catalogo_sexo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_sexo` WRITE;
/*!40000 ALTER TABLE `catalogo_sexo` DISABLE KEYS */;

INSERT INTO `catalogo_sexo` (`id_catalogo_sexo`, `nombre_sexo`)
VALUES
	(1,'HOMBRE'),
	(2,'MUJER'),
	(3,'DESCONOCIDO');

/*!40000 ALTER TABLE `catalogo_sexo` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_tienda
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_tienda`;

CREATE TABLE `catalogo_tienda` (
  `id_catalogo_tienda` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tienda` varchar(45) NOT NULL,
  `imagen_tienda` varchar(200) NOT NULL,
  `active` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id_catalogo_tienda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_tienda` WRITE;
/*!40000 ALTER TABLE `catalogo_tienda` DISABLE KEYS */;

INSERT INTO `catalogo_tienda` (`id_catalogo_tienda`, `nombre_tienda`, `imagen_tienda`, `active`)
VALUES
	(1,'Oxxo','http://res.cloudinary.com/shingshing/image/upload/v1589425111/shingshing/tiendas/Oxxo.png',1),
	(2,'Neto','',0),
	(3,'Walmart','http://res.cloudinary.com/shingshing/image/upload/v1589424953/shingshing/tiendas/Walmart.png',1),
	(4,'7 Eleven','http://res.cloudinary.com/shingshing/image/upload/v1589425038/shingshing/tiendas/7%20Eleven.png',1),
	(5,'K-Mart','',0),
	(6,'Chedraui','http://res.cloudinary.com/shingshing/image/upload/v1586926464/shingshing/tiendas/Chedraui.png',0),
	(7,'Superama','http://res.cloudinary.com/shingshing/image/upload/v1589425131/shingshing/tiendas/Superama.png',1),
	(8,'Mega','http://res.cloudinary.com/shingshing/image/upload/v1586926531/shingshing/tiendas/Mega.png',0),
	(9,'tiendita de Juan','',0),
	(10,'Nueva tienda Demo','',0),
	(11,'demooooojghfc','http://res.cloudinary.com/shingshing/image/upload/v1584825790/shingshing/tiendas/demooooo.png',0),
	(12,'demo','',0),
	(13,'Aurrera','http://res.cloudinary.com/shingshing/image/upload/v1589424938/shingshing/tiendas/Aurrera.png',1),
	(14,'Comercial Mexicana','http://res.cloudinary.com/shingshing/image/upload/v1589425069/shingshing/tiendas/Comercial%20Mexicana.png',1),
	(15,'HEB','',0),
	(16,'Mega Soriana','http://res.cloudinary.com/shingshing/image/upload/v1589425097/shingshing/tiendas/Mega%20Soriana.png',1),
	(17,'Soriana','http://res.cloudinary.com/shingshing/image/upload/v1589425121/shingshing/tiendas/Soriana.png',1),
	(18,'HEB','http://res.cloudinary.com/shingshing/image/upload/v1589425089/shingshing/tiendas/HEB.png',1),
	(19,'don Juan','',0),
	(20,'Costco','http://res.cloudinary.com/shingshing/image/upload/v1589425079/shingshing/tiendas/Costco.png',1),
	(21,'Chedraui','http://res.cloudinary.com/shingshing/image/upload/v1589425059/shingshing/tiendas/Chedraui.png',1),
	(22,'Sam\'s Club','http://res.cloudinary.com/shingshing/image/upload/v1589426539/shingshing/tiendas/Sam%27s%20Club.png',1),
	(23,'GAMESA','',0);

/*!40000 ALTER TABLE `catalogo_tienda` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_tienda_pattern
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_tienda_pattern`;

CREATE TABLE `catalogo_tienda_pattern` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_tienda` varchar(45) DEFAULT NULL,
  `id_pattern` varchar(45) DEFAULT NULL,
  `pattern` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_tienda_pattern` WRITE;
/*!40000 ALTER TABLE `catalogo_tienda_pattern` DISABLE KEYS */;

INSERT INTO `catalogo_tienda_pattern` (`id`, `id_tienda`, `id_pattern`, `pattern`)
VALUES
	(1,'WALMART','ID_WALMART_HORA_PATTERN','(\\d\\d:\\d\\d)'),
	(2,'WALMART','ID_TDA_PATTERN','TDA[\\#|H|M|N][0-9]+\\b'),
	(3,'WALMART','ID_OP_PATTERN','[O|0]P[\\#|H|M|N][A-Z0-9]+\\b'),
	(4,'WALMART','ID_TE_PATTERN','TE[\\#|H|M|N]\\s[0-9]+\\b'),
	(5,'WALMART','ID_TR_PATTERN','TR[\\#|H|M|N|\\s]\\s[0-9]+\\b'),
	(6,'WALMART','ID_WALMART_FECHA_PATTERN','(0[1-9]|[12][0-9]|3[01])[/ .](0[1-9]|1[012])[/ .](19|20)'),
	(8,'SORIANA','ID_SORIANA_FECHA_PATTERN','(0[1-9]|[12][0-9]|3[01])[/ .](0[1-9]|1[012])[/ .](20)\\d\\d'),
	(9,'SORIANA','ID_SORIANA_HORA_PATTERN','(\\d\\d:\\d\\d:\\d\\d)'),
	(10,'SORIANA','ID_SUCURSAL','\\d{3}\\s\\d{3}\\s\\d+\\s\\d+'),
	(11,'SORIANA','ID_SERIAL_NUMBER','[0-9]{5}'),
	(12,'7ELEVEN','ID_FECHA_PATTERN','(0[1-9]|1[012])[/ .](0[1-9]|[12][0-9]|3[01])[/ .](19|20)'),
	(13,'7ELEVEN','ID_HORA_PATTERN','(\\d\\d:\\d\\d:\\d\\d\\s(PM|AM))'),
	(14,'7ELEVEN','ID_SUCURSAL','\\d+\\s\\d+\\s\\d+\\s\\d+'),
	(15,'7ELEVEN','ID_SERIAL_NUMBER','[0-9]{5}'),
	(16,'7ELEVEN','ID_CANTIDAD','\\d+\\s'),
	(17,'OXXO','ID_OXXO_FECHA_PATTERN','(0[1-9]|[12][0-9]|3[01])[/ ).](0[1-9]|1[012])[/ .](19|20)\\d\\d'),
	(18,'OXXO','ID_OXXO_HORA_PATTERN','(\\d\\d:\\d\\d))'),
	(19,'OXXO','ID_OXXO_PRODUCTOS_PATTERN','[a-zA-Z0-9]*\\.?\\d*\\.?\\d*)'),
	(20,'OXXO','ID_OXXO_FOLIO_VENTA','[a-zA-Z]+\\s?[a-zA-Z]+:[0-9]+)'),
	(21,'OXXO','ID_OXXO_ID_VENTA','ID[=|a-zA-Z0-9]+)'),
	(22,'OXXO','ID_OXXO_CIFRAS','^-?\\d*\\.{1,1}\\d+$)'),
	(23,'OXXO','ID_OXXO_CAJA','\\b[0-9]+\\b)');

/*!40000 ALTER TABLE `catalogo_tienda_pattern` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_tipo_bancaria
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_tipo_bancaria`;

CREATE TABLE `catalogo_tipo_bancaria` (
  `id_tipo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion_bancaria` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo`),
  UNIQUE KEY `id_tipo_UNIQUE` (`id_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_tipo_bancaria` WRITE;
/*!40000 ALTER TABLE `catalogo_tipo_bancaria` DISABLE KEYS */;

INSERT INTO `catalogo_tipo_bancaria` (`id_tipo`, `descripcion_bancaria`)
VALUES
	(1,'CLABE'),
	(2,'Cuenta'),
	(3,'Tarjeta');

/*!40000 ALTER TABLE `catalogo_tipo_bancaria` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_tipo_producto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_tipo_producto`;

CREATE TABLE `catalogo_tipo_producto` (
  `id_catalogo_tipo_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tipo_producto` varchar(45) NOT NULL,
  `img_url` varchar(200) DEFAULT NULL,
  `active` tinyint(4) DEFAULT '1' COMMENT '0 = a Dado de baja\\n1 = a Activo',
  PRIMARY KEY (`id_catalogo_tipo_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_tipo_producto` WRITE;
/*!40000 ALTER TABLE `catalogo_tipo_producto` DISABLE KEYS */;

INSERT INTO `catalogo_tipo_producto` (`id_catalogo_tipo_producto`, `nombre_tipo_producto`, `img_url`, `active`)
VALUES
	(1,'Abarrotes','http://res.cloudinary.com/shingshing/image/upload/v1570550786/shingshing/departamentos/Abarrotes.png',1),
	(2,'Electrónica','http://res.cloudinary.com/shingshing/image/upload/v1570550933/shingshing/departamentos/Electr%C3%B3nicos.png',1),
	(3,'Autos','http://res.cloudinary.com/shingshing/image/upload/v1570550960/shingshing/departamentos/Autos.png',1),
	(4,'Bebé','http://res.cloudinary.com/shingshing/image/upload/v1570550983/shingshing/departamentos/Beb%C3%A9.png',1),
	(5,'Belleza','http://res.cloudinary.com/shingshing/image/upload/v1570551753/shingshing/departamentos/Belleza.png',0),
	(6,'Gadgets hola','http://res.cloudinary.com/shingshing/image/upload/v1570551933/shingshing/departamentos/Gadgets.png',0),
	(7,'Ropa','http://res.cloudinary.com/shingshing/image/upload/v1582661295/shingshing/departamentos/Ropa.png',1),
	(8,'Cosméticos','http://res.cloudinary.com/shingshing/image/upload/v1570552046/shingshing/departamentos/Cosm%C3%A9ticos.png',1),
	(9,'Deportes','http://res.cloudinary.com/shingshing/image/upload/v1570551972/shingshing/departamentos/Deportes.png',1),
	(10,'Mascotas','http://res.cloudinary.com/shingshing/image/upload/v1570551954/shingshing/departamentos/Mascotas.png',1),
	(11,'Autos','http://res.cloudinary.com/shingshing/image/upload/v1570550960/shingshing/departamentos/Autos.png',0),
	(12,'Bebidas','http://res.cloudinary.com/shingshing/image/upload/v1570552024/shingshing/departamentos/Bebidas.png',1),
	(13,'Blancos','http://res.cloudinary.com/shingshing/image/upload/v1581434430/shingshing/departamentos/Blancos.png',0),
	(14,'Cremeria','http://res.cloudinary.com/shingshing/image/upload/v1582661239/shingshing/departamentos/Cremeria.png',1),
	(15,'Electrónica','',0),
	(16,'Hogar','http://res.cloudinary.com/shingshing/image/upload/v1582661114/shingshing/departamentos/Hogar.png',1),
	(17,'Higiene','http://res.cloudinary.com/shingshing/image/upload/v1582661096/shingshing/departamentos/Higiene.png',1),
	(18,'Limpieza','http://res.cloudinary.com/shingshing/image/upload/v1582661030/shingshing/departamentos/Limpieza.png',1),
	(19,'Nuevo','',0),
	(20,'Demo','http://res.cloudinary.com/shingshing/image/upload/v1582667097/shingshing/departamentos/Demo.png',0),
	(21,'Hola soy un demo','http://res.cloudinary.com/shingshing/image/upload/v1584824076/shingshing/departamentos/Hola%20soy%20un%20demo.png',0),
	(22,'demo','',0),
	(23,'Jardineria','http://res.cloudinary.com/shingshing/image/upload/v1588724688/shingshing/departamentos/Jardineria.png',1);

/*!40000 ALTER TABLE `catalogo_tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config` (
  `key_name` varchar(45) NOT NULL,
  `key_value` varchar(45) DEFAULT NULL,
  `key_data` longtext,
  PRIMARY KEY (`key_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;

INSERT INTO `config` (`key_name`, `key_value`, `key_data`)
VALUES
	('FCM_KEY','FCM_KEY_FILE','ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAic2hpbmdzaGluZy02OWMxZiIsCiAgInByaXZhdGVfa2V5X2lkIjogImMwYTcwYzJjY2NmZDFjMDQ5NDYwZDI4MzRmNmJiZTBjMjQyNWViZDEiLAogICJwcml2YXRlX2tleSI6ICItLS0tLUJFR0lOIFBSSVZBVEUgS0VZLS0tLS1cbk1JSUV2Z0lCQURBTkJna3Foa2lHOXcwQkFRRUZBQVNDQktnd2dnU2tBZ0VBQW9JQkFRREhBVlhMeG1OaVVXMzlcbjJtUElZQmt2eFV0UlExK2lhdHhWWlRoQ1RVMzhiOGVHN1FQUHVhYVkrNE1Zai80N2l5V3A4eVZjcE43M2d5VC9cbkhuUjNCRTFESDBEQ0QwVGxxbHlRVjJHNzR3OTFoWGFJNUF3WUNqTjg3VG9QSTZaRnVlVUZ2Rzk4SDV5VWNnMXlcbnBsVEhtS3VoZkxhYzlvaXpJeTZNYmpyR2tYTS9WbExxaUgzbGcxWWk1R1hJYXBwN2FLb2N1ZWZTRVpOVDY1ZjlcbmlkanNDY20rTG9sS1ViTXV6cmxwZVJDdlM3N1JoNXR4Y1N6NFZhamV4K3VJL2xHUjdRZGFKYXliMnVEN1dPNTFcbnJ6UHduOFdEaUZrN2FNcC9oTnoyY002TEpGSGc3Wk9UR1R1TnhVa01DMUs2cHBPUXVLSWNickVUalhoYjZTM0Zcbkszak42Z2J6QWdNQkFBRUNnZ0VBQWZwSFJyVkpsR2xEbkNLa2QzKzcxek5EY241TERia21iTk5tUWQ3OThQdVFcbmlWVzU3OVBSeFFUYi9mSVJoeVk5MmtyeFNvbXdWaUVSOFRIMWtnMDM1SHh4YytEU3JwbWJQaXRtL29iYy9jQnJcbjdyWlVVUUtPcUNXWWhXVzFjTitwb1NIMnNSZUVlVU40VXdjMkllZGZONDZldVdSUlBYVmJubG52T1ppRlRJa2FcbnZGUXFhcVZQbm1lWW1WWDE0ekk3MCtvM251MG9oWXZVWU93TUJ4VkNHRUV5dDdTUms3Ry9CbXZzYUhzTk1Za3lcbjJ5ZTZySERDL2VqZHFrczhtZTNSQWRmKy9qc1RyUDkwQ2FLVHcwRzd2Sk9LUXI1SlBQcmI2bmlGTGRYeVdDNWFcbkF3bXFld3o5c3J3WlpWbjIwNUZFUGcrV0dJZGtNNVM1Y0V1ZFFjZTBTUUtCZ1FEbCtNTjRUMnB1bXV0Qis4b3pcbnVvRGtqR29IUDAyc1c5UEE5cTBxTHFyVkVTdk5FbzVPZ3A1TkVjY0I5UmRtMytUNHhhQjZiK1N6OWd2K3FoTitcblF4bldxa3RqaCtXN1BwSlppdXhrSlQrZVJsQmdualhDYWE0OEI5akYyd1FXTXdPclowMkRkZDRkZHhWandTN2RcbnNubldma0h1UkJWdGZxcHhrSW5UbitzQ1R3S0JnUURkaDFmSzdMeFJKMXpBMURZOXdiTTBFZVVJWXAzdmM3Y3NcbnMzbjhaMmF3N0MzNzBoSVFnanA0OTVVWDhpMHIrRlR2UEpKRVMzVHhLZTFXbVg2M2NueDFtNFl0UDlkc1dSd3pcbldGcWF2VUUzTzdUQUVibk9KTHlkWUlaUXBlbXh0OXdoUE5NS01BZ2VST2tVVHEyYWJOWWxPSnNZZEVFQnFvTWZcbkZTcEZyV3Y4SFFLQmdRREZJSXpscHlWemMrWXZaRmE2S3FkcndaVHRhMno0VHFwZjROTWtzbVlMUjdIRkVwL2JcbldvbTVSSURUQTdVd29NRVVJY3RpeUdGQmhhcFlmSTlERHQrcUs4VjlwckxjNDFEdDRuQ3BrMmhLRXJtNWFFUnFcbnhzM3NkVWx5cUQrRGkrMGNVdXVWd1VaSStaZmpMMmd0NzF3UzZaMDRVVElRN3AvSnd2eng0MFcrYndLQmdRQ0Jcblo0MHVPY25yc2cycGlvMUEyQVNobHc1dUxvVkptaFBYWmVRTlFDMnBqZDF5cTR6bjNkcmdUT2ZuL0F6TkVad3pcbnYvTkZON3JSSVlmRnZaWmEvT0tkSk1ObGJWU2VzeE1aSHpTV0RaV3ArUk9sMnZUcDZXWFFuTkQ1RFdJTThYVUNcbmFYQnlGQXE1KzlFWGpybzBhZjFSOHRZT20rSlpZY3lFZksyYjFDa3ZOUUtCZ0FtTVgvaWVxdDludEpmSFB1ZXBcbmtUeERxandHejhIL3ZEK1hLR1lkNi9TQ0FiM1d1NERQWWg3elBlaFBlRVhZSzdOdkdGNEZrQlVVY2RFY2xwZVhcbmhyWkorU2NJYjFtOERqR3pJdEpkYjMza29qS2NUYnAxdVdpcC95VTFMTytmMTdqVkZTY0g3ZzVFZTM3R242bHNcbnVVbVhsTDRweUNRQk4zOHR3NEJTVlpndlxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImZpcmViYXNlLWFkbWluc2RrLXduN2lsQHNoaW5nc2hpbmctNjljMWYuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iLAogICJjbGllbnRfaWQiOiAiMTAyMDAxNzYyNDgyMDI5NTU1OTg4IiwKICAiYXV0aF91cmkiOiAiaHR0cHM6Ly9hY2NvdW50cy5nb29nbGUuY29tL28vb2F1dGgyL2F1dGgiLAogICJ0b2tlbl91cmkiOiAiaHR0cHM6Ly9vYXV0aDIuZ29vZ2xlYXBpcy5jb20vdG9rZW4iLAogICJhdXRoX3Byb3ZpZGVyX3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vb2F1dGgyL3YxL2NlcnRzIiwKICAiY2xpZW50X3g1MDlfY2VydF91cmwiOiAiaHR0cHM6Ly93d3cuZ29vZ2xlYXBpcy5jb20vcm9ib3QvdjEvbWV0YWRhdGEveDUwOS9maXJlYmFzZS1hZG1pbnNkay13bjdpbCU0MHNoaW5nc2hpbmctNjljMWYuaWFtLmdzZXJ2aWNlYWNjb3VudC5jb20iCn0K');

/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla contacto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `contacto`;

CREATE TABLE `contacto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topico` varchar(200) DEFAULT NULL,
  `detalle` varchar(200) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario_contacto_idx` (`id_usuario`),
  CONSTRAINT `id_usuario_contacto` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `contacto` WRITE;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;

INSERT INTO `contacto` (`id`, `topico`, `detalle`, `id_usuario`, `nombre`, `email`)
VALUES
	(1,'no funciona','no funciona',88,NULL,NULL),
	(2,'hola','hola',86,NULL,NULL),
	(3,'Camara','Error al tomar foto',2,NULL,NULL),
	(4,'Problema con la App','Se cierra en la pantalla principal, al scrollear ',2,NULL,NULL),
	(5,'Escaneo de un Ticket','Problema al escanear ticket de Walmart',2,NULL,NULL),
	(6,'Problemas con el APP','jhkjhkjh',107,NULL,NULL),
	(7,'Comentarios','12345',107,NULL,NULL),
	(8,'Problemas con el APP','12345',107,NULL,NULL),
	(9,'Comentarios','sdasdasd',107,NULL,NULL),
	(10,'Problemas con el APP','Hola probando',107,NULL,NULL),
	(11,'Problemas con el APP','Hola',107,NULL,NULL),
	(12,'Problemas con el APP','akjshkjashd',107,NULL,NULL),
	(13,'Comentarios','12345',107,NULL,NULL),
	(14,'Comentarios','Hola ultima prueba',107,NULL,NULL),
	(15,'Problemas con el APP','123456789',107,NULL,NULL),
	(16,'Problemas con el APP','Hola que tal',107,NULL,NULL),
	(17,'Problemas con el APP','asdassdadasd',107,NULL,NULL),
	(18,'Problema con la App','Hola de nuevo !',42,NULL,NULL),
	(19,'Problemas con el APP','hola!¡¿$:¡$;*×;¡#:¿$, v',42,NULL,NULL),
	(20,NULL,'hola !',NULL,'ERICK','8ures92@gmail.com'),
	(21,NULL,'',NULL,'',''),
	(22,NULL,'',NULL,'',''),
	(23,'Problema con la App','Comportamiento extraño al iniciar',117,NULL,NULL);

/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla fotografias_ticket
# ------------------------------------------------------------

DROP TABLE IF EXISTS `fotografias_ticket`;

CREATE TABLE `fotografias_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_ticket` int(11) DEFAULT NULL,
  `url_foto_ticket` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ticket_fotografias` (`id_ticket`),
  CONSTRAINT `fk_ticket_fotografias` FOREIGN KEY (`id_ticket`) REFERENCES `ticket` (`id_ticket`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `fotografias_ticket` WRITE;
/*!40000 ALTER TABLE `fotografias_ticket` DISABLE KEYS */;

INSERT INTO `fotografias_ticket` (`id`, `id_ticket`, `url_foto_ticket`)
VALUES
	(1,86,'http://res.cloudinary.com/shingshing/image/upload/v1588571312/shingshing/tickets/86-0.jpg'),
	(2,87,'http://res.cloudinary.com/shingshing/image/upload/v1588571469/shingshing/tickets/87-0.jpg'),
	(3,88,'http://res.cloudinary.com/shingshing/image/upload/v1588732771/shingshing/tickets/88-0.jpg'),
	(4,92,'http://res.cloudinary.com/shingshing/image/upload/v1589930872/shingshing/tickets/92-0.jpg');

/*!40000 ALTER TABLE `fotografias_ticket` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla historico_bonificaciones
# ------------------------------------------------------------

DROP TABLE IF EXISTS `historico_bonificaciones`;

CREATE TABLE `historico_bonificaciones` (
  `id_ticket` int(11) NOT NULL,
  `producto_id_producto` int(11) NOT NULL,
  KEY `id_ticket_idx` (`id_ticket`),
  KEY `fk_historico_bonificaciones_producto1_idx` (`producto_id_producto`),
  CONSTRAINT `fk_historico_bonificaciones_producto1` FOREIGN KEY (`producto_id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_ticket` FOREIGN KEY (`id_ticket`) REFERENCES `ticket` (`id_ticket`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `historico_bonificaciones` WRITE;
/*!40000 ALTER TABLE `historico_bonificaciones` DISABLE KEYS */;

INSERT INTO `historico_bonificaciones` (`id_ticket`, `producto_id_producto`)
VALUES
	(44,13),
	(45,13),
	(46,13),
	(47,1),
	(48,24),
	(49,24),
	(50,24),
	(51,1),
	(51,1),
	(52,44),
	(53,67),
	(54,67),
	(55,67),
	(56,67),
	(57,67),
	(58,67),
	(59,71),
	(60,71),
	(61,71),
	(62,71),
	(63,71),
	(64,28),
	(65,28),
	(66,28),
	(67,28),
	(68,28),
	(69,27),
	(70,27),
	(71,75),
	(72,75),
	(73,75),
	(74,75),
	(75,75),
	(76,75),
	(77,75),
	(78,75),
	(79,75),
	(80,75),
	(81,28),
	(82,28),
	(83,28),
	(84,28),
	(85,28),
	(86,80),
	(86,81),
	(86,82),
	(86,83),
	(87,28),
	(87,28),
	(87,28),
	(87,29),
	(87,29),
	(87,29),
	(87,30),
	(87,30),
	(87,30),
	(87,19),
	(87,19),
	(87,19),
	(87,9),
	(87,9),
	(87,31),
	(87,31),
	(87,32),
	(87,32),
	(87,33),
	(87,33),
	(87,33),
	(87,33),
	(87,33),
	(87,33),
	(87,33),
	(87,34),
	(87,34),
	(87,34),
	(87,34),
	(87,34),
	(87,34),
	(87,34),
	(87,35),
	(87,35),
	(87,35),
	(87,35),
	(87,35),
	(87,35),
	(87,35),
	(87,2),
	(87,2),
	(87,2),
	(87,2),
	(87,2),
	(87,2),
	(87,2),
	(87,2),
	(87,36),
	(87,36),
	(87,36),
	(87,36),
	(87,36),
	(87,36),
	(87,36),
	(87,37),
	(87,37),
	(87,37),
	(87,37),
	(87,37),
	(87,37),
	(87,37),
	(87,38),
	(87,38),
	(87,38),
	(87,38),
	(87,38),
	(87,38),
	(87,38),
	(87,39),
	(87,39),
	(87,39),
	(87,39),
	(87,39),
	(87,39),
	(87,39),
	(87,40),
	(87,40),
	(87,40),
	(87,40),
	(87,40),
	(87,40),
	(87,40),
	(87,41),
	(87,41),
	(87,41),
	(87,41),
	(87,41),
	(87,41),
	(87,41),
	(87,42),
	(87,42),
	(87,42),
	(87,42),
	(87,42),
	(87,42),
	(87,42),
	(87,43),
	(87,43),
	(87,43),
	(87,43),
	(87,43),
	(87,43),
	(87,43),
	(87,44),
	(87,44),
	(87,44),
	(87,44),
	(87,44),
	(87,44),
	(87,44),
	(87,45),
	(87,45),
	(87,45),
	(87,45),
	(87,45),
	(87,45),
	(87,45),
	(87,46),
	(87,46),
	(87,46),
	(87,46),
	(87,46),
	(87,46),
	(87,46),
	(87,47),
	(87,47),
	(87,47),
	(87,47),
	(87,47),
	(87,47),
	(87,47),
	(87,48),
	(87,48),
	(87,48),
	(87,48),
	(87,48),
	(87,48),
	(87,48),
	(87,49),
	(87,49),
	(87,49),
	(87,49),
	(87,49),
	(87,49),
	(87,49),
	(87,50),
	(87,50),
	(87,50),
	(87,50),
	(87,50),
	(87,50),
	(87,50),
	(87,51),
	(87,51),
	(87,51),
	(87,51),
	(87,51),
	(87,51),
	(87,51),
	(87,52),
	(87,52),
	(87,52),
	(87,52),
	(87,52),
	(87,52),
	(87,52),
	(87,53),
	(87,53),
	(87,53),
	(87,53),
	(87,53),
	(87,53),
	(87,53),
	(87,54),
	(87,54),
	(87,54),
	(87,54),
	(87,54),
	(87,54),
	(87,54),
	(87,55),
	(87,55),
	(87,55),
	(87,55),
	(87,55),
	(87,55),
	(87,55),
	(87,56),
	(87,56),
	(87,56),
	(87,56),
	(87,56),
	(87,56),
	(87,56),
	(87,57),
	(87,57),
	(87,57),
	(87,57),
	(87,57),
	(87,57),
	(87,57),
	(87,58),
	(87,58),
	(87,58),
	(87,58),
	(87,58),
	(87,58),
	(87,58),
	(87,59),
	(87,59),
	(87,59),
	(87,59),
	(87,59),
	(87,59),
	(87,59),
	(87,60),
	(87,60),
	(87,60),
	(87,60),
	(87,60),
	(87,60),
	(87,60),
	(87,61),
	(87,61),
	(87,61),
	(87,61),
	(87,61),
	(87,61),
	(87,61),
	(87,62),
	(87,62),
	(87,62),
	(87,62),
	(87,62),
	(87,62),
	(87,62),
	(87,63),
	(87,63),
	(87,63),
	(87,63),
	(87,63),
	(87,63),
	(87,63),
	(87,64),
	(87,64),
	(87,64),
	(87,64),
	(87,64),
	(87,64),
	(87,64),
	(87,65),
	(87,65),
	(87,65),
	(87,65),
	(87,65),
	(87,65),
	(87,65),
	(87,16),
	(87,16),
	(87,16),
	(87,16),
	(87,16),
	(87,16),
	(87,16),
	(87,16),
	(87,66),
	(87,66),
	(87,66),
	(87,66),
	(87,66),
	(87,66),
	(87,66),
	(87,67),
	(87,67),
	(87,67),
	(87,67),
	(87,67),
	(87,67),
	(87,22),
	(87,22),
	(87,22),
	(87,22),
	(87,22),
	(87,22),
	(87,22),
	(87,68),
	(87,68),
	(87,68),
	(87,68),
	(87,68),
	(87,68),
	(87,68),
	(87,1),
	(87,1),
	(87,1),
	(87,1),
	(87,1),
	(87,1),
	(87,1),
	(87,1),
	(87,5),
	(87,5),
	(87,5),
	(87,5),
	(87,5),
	(87,5),
	(87,5),
	(87,6),
	(87,6),
	(87,6),
	(87,6),
	(87,6),
	(87,6),
	(87,6),
	(87,6),
	(87,7),
	(87,7),
	(87,7),
	(87,7),
	(87,7),
	(87,7),
	(87,7),
	(87,7),
	(87,8),
	(87,8),
	(87,8),
	(87,8),
	(87,8),
	(87,8),
	(87,8),
	(87,8),
	(87,4),
	(87,4),
	(87,4),
	(87,4),
	(87,4),
	(87,4),
	(87,3),
	(87,3),
	(87,3),
	(87,3),
	(87,3),
	(87,3),
	(87,3),
	(87,11),
	(87,11),
	(87,11),
	(87,11),
	(87,11),
	(87,11),
	(87,11),
	(87,12),
	(87,12),
	(87,12),
	(87,12),
	(87,12),
	(87,12),
	(87,12),
	(87,13),
	(87,13),
	(87,13),
	(87,13),
	(87,13),
	(87,13),
	(87,13),
	(87,13),
	(87,14),
	(87,14),
	(87,14),
	(87,14),
	(87,14),
	(87,14),
	(87,14),
	(87,14),
	(87,15),
	(87,15),
	(87,15),
	(87,15),
	(87,15),
	(87,15),
	(87,15),
	(87,18),
	(87,18),
	(87,18),
	(87,18),
	(87,18),
	(87,18),
	(87,18),
	(87,21),
	(87,21),
	(87,21),
	(87,21),
	(87,21),
	(87,21),
	(87,21),
	(87,69),
	(87,69),
	(87,69),
	(87,69),
	(87,69),
	(87,69),
	(87,69),
	(87,69),
	(87,70),
	(87,70),
	(87,70),
	(87,70),
	(87,70),
	(87,70),
	(87,70),
	(87,70),
	(87,71),
	(87,71),
	(87,71),
	(87,71),
	(87,71),
	(87,71),
	(87,71),
	(87,72),
	(87,72),
	(87,72),
	(87,72),
	(87,72),
	(87,72),
	(87,72),
	(87,72),
	(87,73),
	(87,73),
	(87,73),
	(87,73),
	(87,73),
	(87,74),
	(87,74),
	(87,74),
	(87,74),
	(87,74),
	(87,74),
	(87,74),
	(87,75),
	(87,75),
	(87,75),
	(87,75),
	(87,75),
	(87,75),
	(87,75),
	(87,76),
	(87,76),
	(87,76),
	(87,76),
	(87,76),
	(87,76),
	(87,76),
	(87,76),
	(87,77),
	(87,77),
	(87,77),
	(87,77),
	(87,77),
	(87,77),
	(87,77),
	(87,77),
	(87,78),
	(87,78),
	(87,78),
	(87,78),
	(87,78),
	(87,78),
	(87,78),
	(87,78),
	(87,79),
	(87,79),
	(87,79),
	(87,79),
	(87,79),
	(87,79),
	(87,79),
	(87,79),
	(87,80),
	(87,80),
	(87,80),
	(87,80),
	(87,80),
	(87,80),
	(87,80),
	(87,80),
	(87,81),
	(87,81),
	(87,81),
	(87,81),
	(87,81),
	(87,81),
	(87,81),
	(87,81),
	(87,82),
	(87,82),
	(87,82),
	(87,82),
	(87,82),
	(87,82),
	(87,82),
	(87,82),
	(87,83),
	(87,83),
	(87,83),
	(87,83),
	(87,83),
	(87,83),
	(87,83),
	(87,83),
	(87,84),
	(87,84),
	(87,84),
	(87,84),
	(87,84),
	(87,84),
	(87,84),
	(87,84),
	(87,85),
	(87,85),
	(87,85),
	(87,85),
	(87,85),
	(87,85),
	(87,85),
	(87,85),
	(87,86),
	(87,86),
	(87,86),
	(87,86),
	(87,86),
	(87,86),
	(87,86),
	(87,86),
	(87,87),
	(87,87),
	(87,87),
	(87,87),
	(87,87),
	(87,87),
	(87,87),
	(87,87),
	(87,87),
	(87,88),
	(87,88),
	(87,88),
	(87,88),
	(87,88),
	(87,88),
	(87,88),
	(87,88),
	(87,89),
	(87,89),
	(87,89),
	(87,89),
	(87,89),
	(87,89),
	(87,89),
	(87,89),
	(87,89),
	(88,96),
	(89,44),
	(90,97),
	(90,99),
	(90,100),
	(90,103),
	(91,44),
	(92,106);

/*!40000 ALTER TABLE `historico_bonificaciones` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla historico_medios_bonificacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `historico_medios_bonificacion`;

CREATE TABLE `historico_medios_bonificacion` (
  `id_historico_medios_bonificacion` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_bonificacion` date NOT NULL,
  `hora_bonificacion` time DEFAULT NULL,
  `cantidad_bonificacion` double DEFAULT NULL,
  `id_medios_bonificacion` int(11) NOT NULL,
  `usuario_id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_historico_medios_bonificacion`),
  KEY `id_medios_bonificacion_idx` (`id_medios_bonificacion`),
  KEY `fk_historico_bonificaciones_usuario1_idx` (`usuario_id_usuario`),
  CONSTRAINT `fk_historico_bonificaciones_usuario1` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_medios_bonificacion` FOREIGN KEY (`id_medios_bonificacion`) REFERENCES `medios_bonificacion` (`id_medios_bonificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `historico_medios_bonificacion` WRITE;
/*!40000 ALTER TABLE `historico_medios_bonificacion` DISABLE KEYS */;

INSERT INTO `historico_medios_bonificacion` (`id_historico_medios_bonificacion`, `fecha_bonificacion`, `hora_bonificacion`, `cantidad_bonificacion`, `id_medios_bonificacion`, `usuario_id_usuario`)
VALUES
	(1,'2020-01-28','02:28:05',10,127,2),
	(2,'2020-01-28','02:38:05',30,127,2),
	(4,'2020-01-29','02:38:05',30.5,127,2),
	(5,'2020-01-31','02:38:05',20,130,2),
	(6,'2020-02-03','12:38:05',20,127,2),
	(7,'2020-03-30',NULL,100,153,2),
	(8,'2020-03-30',NULL,20,128,2),
	(9,'2020-03-30',NULL,50,161,2),
	(10,'2020-04-27','10:23:05',20,127,2),
	(14,'2020-04-27',NULL,20,128,2),
	(15,'2020-04-27',NULL,20,128,2),
	(16,'2020-04-27',NULL,20,128,2),
	(17,'2020-04-28',NULL,20,128,2),
	(18,'2020-04-28',NULL,10,128,2),
	(19,'2020-04-28',NULL,10,128,2),
	(20,'2020-04-28',NULL,20,128,2),
	(21,'2020-04-29',NULL,100,191,42),
	(22,'2020-04-29',NULL,100,191,42),
	(23,'2020-04-29',NULL,100,191,42),
	(24,'2020-04-29',NULL,100,155,42),
	(25,'2020-04-29',NULL,100,189,42),
	(26,'2020-04-29',NULL,10,189,42),
	(27,'2020-05-01',NULL,10,191,42),
	(28,'2020-05-01',NULL,10,191,42),
	(29,'2020-05-03',NULL,100,191,42),
	(30,'2020-05-03','23:13:53',100,191,42),
	(31,'2020-05-04',NULL,200,191,42),
	(32,'2020-05-04',NULL,100,189,42),
	(33,'2020-05-04',NULL,100,189,42),
	(34,'2020-05-05',NULL,100,191,42),
	(35,'2020-05-05',NULL,100,188,42),
	(36,'2020-05-05',NULL,10,189,42),
	(37,'2020-05-05','13:05:55',100,191,42),
	(38,'2020-05-05',NULL,10,128,2),
	(39,'2020-05-05',NULL,500,191,42),
	(40,'2020-05-05',NULL,10,199,42),
	(41,'2020-05-19','01:49:46',10,151,2),
	(42,'2020-05-19','05:10:20',0,155,42),
	(43,'2020-05-19','05:10:58',100,155,42),
	(44,'2020-05-19','05:11:04',100,189,42),
	(45,'2020-05-19','19:28:00',0,151,2);

/*!40000 ALTER TABLE `historico_medios_bonificacion` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla historico_tickets
# ------------------------------------------------------------

DROP TABLE IF EXISTS `historico_tickets`;

CREATE TABLE `historico_tickets` (
  `usuario_id_usuario` int(11) NOT NULL,
  `ticket_id_ticket` int(11) NOT NULL,
  KEY `fk_historico_tickets_usuario1_idx` (`usuario_id_usuario`),
  KEY `fk_historico_tickets_ticket1_idx` (`ticket_id_ticket`),
  CONSTRAINT `fk_historico_tickets_ticket1` FOREIGN KEY (`ticket_id_ticket`) REFERENCES `ticket` (`id_ticket`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_historico_tickets_usuario1` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `historico_tickets` WRITE;
/*!40000 ALTER TABLE `historico_tickets` DISABLE KEYS */;

INSERT INTO `historico_tickets` (`usuario_id_usuario`, `ticket_id_ticket`)
VALUES
	(2,47),
	(2,48),
	(2,49),
	(2,50),
	(2,51),
	(2,52),
	(42,53),
	(42,54),
	(42,55),
	(42,56),
	(42,57),
	(42,58),
	(42,59),
	(42,60),
	(42,61),
	(42,62),
	(42,63),
	(42,64),
	(42,65),
	(42,66),
	(42,67),
	(42,68),
	(42,69),
	(42,70),
	(42,71),
	(42,72),
	(42,73),
	(42,74),
	(42,75),
	(42,76),
	(42,77),
	(42,78),
	(42,79),
	(42,80),
	(42,81),
	(42,82),
	(42,83),
	(42,84),
	(42,85),
	(42,86),
	(42,87),
	(88,88),
	(2,89),
	(42,90),
	(84,91),
	(2,92);

/*!40000 ALTER TABLE `historico_tickets` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla medios_bonificacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `medios_bonificacion`;

CREATE TABLE `medios_bonificacion` (
  `id_medios_bonificacion` int(11) NOT NULL AUTO_INCREMENT,
  `alias_medio_bonificacion` varchar(45) NOT NULL,
  `cuenta_medio_bonificacion` varchar(100) DEFAULT NULL,
  `compania_medio_bonificacion` varchar(45) DEFAULT NULL,
  `id_catalogo_medio_bonificacion` int(11) NOT NULL,
  `vigencia_medio_bonificacion` varchar(10) DEFAULT NULL,
  `id_cuenta_medio_bonificacion` varchar(45) DEFAULT NULL,
  `usuario_id_usuario` int(11) NOT NULL,
  `id_tipo` int(11) DEFAULT NULL,
  `estatus` int(11) DEFAULT '1',
  `banco` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_medios_bonificacion`),
  KEY `id_catalogo_medio_bonificacion_idx` (`id_catalogo_medio_bonificacion`),
  KEY `fk_medios_bonificacion_usuario1_idx` (`usuario_id_usuario`),
  KEY `fk_if_tipo_idx` (`id_tipo`),
  CONSTRAINT `fk_if_tipo` FOREIGN KEY (`id_tipo`) REFERENCES `catalogo_tipo_bancaria` (`id_tipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_medios_bonificacion_usuario1` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_catalogo_medio_bonificacion` FOREIGN KEY (`id_catalogo_medio_bonificacion`) REFERENCES `catalogo_medios_bonificacion` (`id_catalogo_medio_bonificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `medios_bonificacion` WRITE;
/*!40000 ALTER TABLE `medios_bonificacion` DISABLE KEYS */;

INSERT INTO `medios_bonificacion` (`id_medios_bonificacion`, `alias_medio_bonificacion`, `cuenta_medio_bonificacion`, `compania_medio_bonificacion`, `id_catalogo_medio_bonificacion`, `vigencia_medio_bonificacion`, `id_cuenta_medio_bonificacion`, `usuario_id_usuario`, `id_tipo`, `estatus`, `banco`)
VALUES
	(127,'Personal','5534714616','Telcel',3,NULL,NULL,2,NULL,2,NULL),
	(128,'Esposa','5548998389','Telcel',3,NULL,NULL,2,NULL,1,NULL),
	(129,'Hijo','5534714616','Telcel',3,NULL,NULL,2,NULL,2,NULL),
	(130,'Hija','5534714616','ATT&T',3,NULL,NULL,2,NULL,2,NULL),
	(131,'libreton','4152313364159520','VISA',1,NULL,NULL,88,3,1,'BBVA'),
	(132,'cel','5521891769','TELCEL',3,NULL,NULL,88,NULL,1,NULL),
	(136,'hi','h@h.com',NULL,2,NULL,'1234567890123',90,NULL,1,NULL),
	(137,'2','j@h.com',NULL,2,NULL,'qwertyuiopasd',90,NULL,1,NULL),
	(138,'hola ajuste Nuevo','j@gmail.com',NULL,2,NULL,'yuioplkjhgfds',90,NULL,1,NULL),
	(139,'4','ho@gmail.com',NULL,2,NULL,'hjklajhsgydjk',90,NULL,1,NULL),
	(140,'hola','h@gmail.com',NULL,2,NULL,'jhhklabsjsos ',90,NULL,1,NULL),
	(141,'6','12@gmail.com',NULL,2,NULL,'nnagwhomabaja',90,NULL,1,NULL),
	(142,'7','hello@j.com',NULL,2,NULL,'lljjjjjjjjjjj',90,NULL,1,NULL),
	(143,'8','uiihhh@gmail.com',NULL,2,NULL,'klksbskkskkkk',90,NULL,1,NULL),
	(144,'9','hola@gmail.com',NULL,2,NULL,'kksjbsbkslkvs',90,NULL,1,NULL),
	(145,'10','h@gmail.com',NULL,2,NULL,'langsllahvklj',90,NULL,1,NULL),
	(146,'11','j@gmail.com',NULL,2,NULL,'hjklkjhgfdhjk',90,NULL,1,NULL),
	(147,'12','u@gmail.com',NULL,2,NULL,'hjkjhgffsffff',90,NULL,1,NULL),
	(148,'13','jh@gmail.com',NULL,2,NULL,'jhjhjhjhjhjhj',90,NULL,1,NULL),
	(149,'14','k@gmail.com',NULL,2,NULL,'khhjkjhjjhgff',90,NULL,1,NULL),
	(150,'15','h@gmail.com',NULL,2,NULL,'uyhjiuhjygtrf',90,NULL,1,NULL),
	(151,'PayPay Personal','kissthbw@gmail.com',NULL,2,NULL,'kissthbwpaypa',2,NULL,1,NULL),
	(152,'PayPal esposa','kissthbw@gmail.com',NULL,2,NULL,'kiss-PayPalEs',2,NULL,1,NULL),
	(153,'PayPal esposa','kissthbw@gmail.com',NULL,2,NULL,'kiss-PayPalEs-2',2,NULL,1,NULL),
	(154,'hola','herickov@gmail.com',NULL,2,NULL,'nnkkhhkkjjkkb',90,NULL,1,NULL),
	(155,'mmknnn','h@gmail.com',NULL,2,NULL,'12345678912334',42,NULL,1,NULL),
	(156,'kkk','h@gmail.com',NULL,2,NULL,'mkvvkjjjjjjjjjjjjj',42,NULL,1,NULL),
	(157,'jjkk','7778888988887777','',1,NULL,NULL,42,3,2,'BBVA'),
	(158,'herick','5550679875','TELCEL',3,NULL,NULL,42,NULL,2,NULL),
	(161,'DEBITO','5579 1001 8464 5800',NULL,1,NULL,NULL,2,3,1,'SANTANDER'),
	(162,'Débito 2','5579 1001 8464 5800',NULL,1,NULL,NULL,2,3,1,'SANTANDER'),
	(163,'demo ok cambio','herickov@gmail.com',NULL,2,NULL,'jkjjjjjjkkkkl',42,NULL,2,NULL),
	(164,'cuentaprueba','4000 0012 3456 7899','VISA',1,NULL,NULL,73,3,2,'BBVA'),
	(165,'pruebapaypal','prueba@prueba.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(166,'BBV','4000001234567899','VISA',1,NULL,NULL,73,3,1,'BBVA'),
	(167,'bbv2','4002001234567899','VISA',1,NULL,NULL,73,3,1,'BBVA'),
	(168,'Débito','5579 1001 8464 5800',NULL,1,NULL,NULL,81,3,1,'SANTANDER'),
	(169,'pruebapaypal','prueba@prueba.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(170,'PPP','prueba@prueba.com',NULL,2,NULL,'S-7FNADHDMDHDM',73,NULL,2,NULL),
	(171,'ppp','prueba@prueba.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(172,'ppp','p@g.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(173,'ppp','g@g.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(174,'ggg','g@g.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(175,'ggg','g@g.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(176,'ggg','g@g.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(177,'ppv','g@g.com',NULL,2,NULL,'S-7FNADHDMHHM',73,NULL,2,NULL),
	(178,'ppp','g@g.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(179,'pg','g@g.com',NULL,2,NULL,'S-7FNADHDMHHDM',73,NULL,2,NULL),
	(180,'hg2020','g@h.com',NULL,2,NULL,'S-7FNAHDHDHDHD',73,NULL,1,NULL),
	(181,'test bbva','765556677778888','',1,NULL,NULL,2,3,1,'BBVA'),
	(182,'test bbvas','765556677778888','',1,NULL,NULL,2,3,1,'BBVA'),
	(183,'test bbv','765556677778888','',1,NULL,NULL,2,3,1,'BBVA'),
	(184,'test bbv1','765556677778888','',1,NULL,NULL,2,3,1,'BBVA'),
	(185,'test bbvd','765556677778888','',1,NULL,NULL,2,3,2,'BBVA'),
	(186,'test bbvd','765556677778888','',1,NULL,NULL,2,3,2,'BBVA'),
	(187,'android cambio','7766666','',1,NULL,NULL,42,3,1,'BBVA'),
	(188,'android cambio','ksj@g.com',NULL,2,NULL,'lsnkkkkkkkkkkk',42,NULL,2,NULL),
	(189,'android con cambio','5550679876','TELCEL',3,NULL,NULL,42,NULL,1,NULL),
	(190,'bbv3','667877665556','',1,NULL,NULL,2,3,1,'BBVA'),
	(191,'demo BBVa','5555 5555 5555 5555',NULL,1,NULL,NULL,42,3,1,'BBVA'),
	(193,'Debito Juan','5579 1001 8464 5800',NULL,1,NULL,NULL,117,3,1,'SANTANDER'),
	(194,'Debito Banorte Juan','4915 6664 3817 0692',NULL,1,NULL,NULL,117,3,1,'SCOTIA BANK'),
	(195,'PayPal Personal','kissthbw@gmail.com',NULL,2,NULL,'PayPalPersona',117,NULL,1,NULL),
	(196,'Personal','5534714616','Telcel',3,NULL,NULL,117,NULL,1,NULL),
	(197,'black','4521 6632 0800 6321',NULL,1,NULL,NULL,88,3,2,'SANTANDER'),
	(198,'qs','5549442851','Telcel',3,NULL,NULL,88,NULL,1,NULL),
	(199,'cuenta fake ','5550679875','Virgin Mobile',3,NULL,NULL,42,NULL,1,NULL),
	(200,'Cliente','5579209097631234',NULL,1,NULL,NULL,2,1,1,'Scottiabank'),
	(201,'Cliente','5579209097631234',NULL,1,NULL,NULL,2,1,1,'Scottiabank'),
	(202,'Cliente','5579209097631234',NULL,1,NULL,NULL,2,1,1,'Scottiabank'),
	(203,'Cliente','5579209097631234',NULL,1,NULL,NULL,2,1,1,'Scottiabank'),
	(204,'Cliente','5579209097631234',NULL,1,NULL,NULL,2,1,1,'Scottiabank'),
	(205,'Cliente','5579209097631234',NULL,1,NULL,NULL,2,1,1,'Scottiabank'),
	(206,'Cliente','5579209097631234',NULL,1,NULL,NULL,2,1,1,'Scottiabank'),
	(207,'Cliente','5579209097631234',NULL,1,NULL,NULL,2,1,1,'Scottiabank'),
	(208,'Tarjeta','5579209097634248',NULL,1,NULL,NULL,2,1,1,'Scottiabank'),
	(209,'t','1234567891234567',NULL,1,NULL,NULL,2,1,1,'Afirme'),
	(210,'12345','1234567891234567',NULL,1,NULL,NULL,2,1,1,'Afirme'),
	(211,'tarjeta','12345678912345678',NULL,1,NULL,NULL,2,1,1,'Afirme'),
	(212,'asdasd','1234567891234567',NULL,1,NULL,NULL,2,1,1,'Banbajio'),
	(213,'sjnl','1234567891234567',NULL,1,NULL,NULL,2,1,1,'Afirme'),
	(214,'sjnl','1234567891234567',NULL,1,NULL,NULL,2,1,1,'Afirme'),
	(215,'onlk','1234678912345',NULL,1,NULL,NULL,2,1,1,'Afirme'),
	(216,'edgar','ergar_16@hotmail.com',NULL,2,NULL,'123456789',2,NULL,1,NULL),
	(217,'erick uriel','e',NULL,1,NULL,NULL,42,1,1,'Scottiabank'),
	(218,'erick uriel','e',NULL,1,NULL,NULL,42,1,1,'Scottiabank');

/*!40000 ALTER TABLE `medios_bonificacion` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla problema
# ------------------------------------------------------------

DROP TABLE IF EXISTS `problema`;

CREATE TABLE `problema` (
  `id_problema` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `problema` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_problema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `problema` WRITE;
/*!40000 ALTER TABLE `problema` DISABLE KEYS */;

INSERT INTO `problema` (`id_problema`, `problema`)
VALUES
	(1,'Tengo un problema !!!'),
	(2,'Tengo un problema!'),
	(3,'nop se!'),
	(4,'No encuentro jfvdinfvjenfv'),
	(5,'dgd'),
	(6,'Tickets'),
	(7,'hola cabeza de bola :)'),
	(8,'Problema desde dashboard marca'),
	(9,'problema usuarios'),
	(10,'hellou');

/*!40000 ALTER TABLE `problema` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla producto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `producto`;

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(45) NOT NULL,
  `precio` double NOT NULL,
  `codigo_barras` varchar(45) DEFAULT NULL,
  `presentacion` varchar(200) DEFAULT NULL,
  `contenido` varchar(200) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `aplica_promocion` tinyint(1) DEFAULT NULL,
  `vigencia_promocion` date DEFAULT NULL,
  `url_imagen_producto` varchar(100) DEFAULT NULL,
  `cantidad_bonificacion` double DEFAULT NULL,
  `banner` int(1) DEFAULT NULL,
  `color_banner` varchar(11) DEFAULT NULL,
  `id_catalogo_marca` int(11) NOT NULL,
  `id_catalogo_tipo_producto` int(11) NOT NULL,
  `id_catalogo_tienda` int(11) DEFAULT NULL,
  `img_url` varchar(200) DEFAULT NULL,
  `active` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id_producto`),
  KEY `id_catalogo_marca_idx` (`id_catalogo_marca`),
  KEY `id_catalogo_tipo_producto_idx` (`id_catalogo_tipo_producto`),
  KEY `id_catalogo_tienda_idx` (`id_catalogo_tienda`),
  CONSTRAINT `id_catalogo_marca` FOREIGN KEY (`id_catalogo_marca`) REFERENCES `catalogo_marca` (`id_catalogo_marca`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_catalogo_tienda` FOREIGN KEY (`id_catalogo_tienda`) REFERENCES `catalogo_tienda` (`id_catalogo_tienda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_catalogo_tipo_producto` FOREIGN KEY (`id_catalogo_tipo_producto`) REFERENCES `catalogo_tipo_producto` (`id_catalogo_tipo_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;

INSERT INTO `producto` (`id_producto`, `nombre_producto`, `precio`, `codigo_barras`, `presentacion`, `contenido`, `descripcion`, `aplica_promocion`, `vigencia_promocion`, `url_imagen_producto`, `cantidad_bonificacion`, `banner`, `color_banner`, `id_catalogo_marca`, `id_catalogo_tipo_producto`, `id_catalogo_tienda`, `img_url`, `active`)
VALUES
	(1,'Laptop',0,NULL,NULL,'1 pza','Dispositivo de streaming',0,NULL,NULL,90,0,'#FF6600',2,1,NULL,'',0),
	(2,'Agua Bonafont',0,NULL,NULL,'1 l','Agua natural',0,NULL,NULL,1,0,'#FF6600',1,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1582663344/shingshing/productos/Agua%20Bonafont.png',0),
	(3,'Agua Bonafont',0,NULL,NULL,'1.5 lt','Agua natural',0,NULL,NULL,1,1,'#FF6600',1,1,NULL,'',0),
	(4,'DEMO',0,NULL,NULL,'1.5 lt','Agua natural',0,NULL,NULL,5,1,'#FF6600',1,1,NULL,'',0),
	(5,'Aceite Quaker State',0,NULL,NULL,'960 ml','Aceite para motor, dale vida tu motor',0,NULL,NULL,10,1,'#FF6600',42,3,NULL,'',0),
	(6,'Cereal Ciniminis',0,NULL,NULL,'900 g','Cereal de trigo con canela',0,NULL,NULL,10,1,'#FF6600',1,1,NULL,'',0),
	(7,'Cereal Kellogs',0,NULL,NULL,'900 g','Cereal clasico de hojuelas de maiz',0,NULL,NULL,10,1,'#FF6600',8,1,NULL,'',0),
	(8,'Burrito Clásico',0,NULL,NULL,'182 g','Burrito de frijoles con queso en tortilla de ',0,NULL,NULL,10,1,'#FF6600',8,1,NULL,'',0),
	(9,'Agua Natural Bonafont',0,NULL,NULL,'1.2 lt','Agua Bonafont con sabor natural',0,NULL,NULL,10,1,'#FF6600',1,12,NULL,'',0),
	(10,'Desodorante en aerosol AXE',0,NULL,NULL,'125 ml','Desodorante en aerosol de 125 ml. con fragancia fresca que dura 24 horas',0,NULL,NULL,7,1,'#FF6600',9,17,NULL,'',0),
	(11,'Aceite 1-2-3 ',0,NULL,NULL,'1 lt','Aceite vegetal comestible, que no contiene colesterol',0,NULL,NULL,7,1,'#FF6600',10,1,NULL,'',0),
	(12,'Colgate triple acción',0,NULL,NULL,'150 ml','Pasta de dientes xtra frescura, que contiene anticaries con fluor',0,NULL,NULL,2,1,'#FF6600',24,17,NULL,'',0),
	(13,'Cacahuates Herdez',0,NULL,NULL,'250 g','Cacahuates japonés, sabor saladillo, crujiente ideal para calmar el hambre',0,NULL,NULL,5,0,'#FF6600',10,1,NULL,'',0),
	(14,'Desodorante Rexona Clinical',0,NULL,NULL,'250 ml','Aerosol en aerosol con olor fresco por 24 hrs',0,NULL,NULL,6,1,'#FF6600',9,8,NULL,'',0),
	(15,'Yougurt Natural Yoplait',0,NULL,NULL,'125 ml','Yogurt para beber Yoplait de sabor natural',0,NULL,NULL,2,1,'#FF6600',10,12,NULL,'',0),
	(16,'Desodorante en aerosol Stefano',0,NULL,NULL,'125 ml','Desodorante en spray para un olor fresco por 12 horas',0,NULL,NULL,3,0,'#FF6600',9,8,NULL,'',0),
	(17,'Desodorante en aerosol Stefano',0,NULL,NULL,'125 ml','Desodorante en aerosol, con fragancia fresca que para tener un olor agradable por hasta 24 horas ',0,NULL,NULL,5,0,'#FF6600',9,8,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589430039/shingshing/productos/Desodorante%20en%20aerosol%20Stefano.png',1),
	(18,'Area para Gato',0,NULL,NULL,'2 Kg','Arena para gato',0,NULL,NULL,2,1,'#FF6600',13,10,NULL,'',0),
	(19,'AMERICANO 5 DEMO',0,NULL,NULL,'BALON DE AMERICANO','BALON DE AMERICANO DE TAMAÑO REGULAR',0,NULL,NULL,30,1,'#FF6600',11,9,NULL,'',0),
	(20,'PELOTA',18.9,'7506271756965','PELOTA DE PLASTICO','PELOTA DE PLASTICO','PELOTA DE PLASTICO COLOR VERDE, TAMAÑO REGULAR',0,NULL,'/pelota',2,0,'#FF6600',11,9,3,NULL,0),
	(21,'Jugo Del Valle',0,NULL,NULL,'500 ml','Jugo Del Valle sabor mango',0,NULL,NULL,1,1,'#FF6600',12,12,NULL,'',0),
	(22,'Desodorante en barra Dove',0,NULL,NULL,'50 ml','Desodorante en barra',0,NULL,NULL,4,1,'#FF6600',9,8,NULL,'',0),
	(23,'Nintendo Switch',6999,'978128713','','1 pieza','',1,'2019-10-27','/home/img/chrome.jpg',90,0,'#FF6600',7,2,1,NULL,0),
	(24,'Nintendo Switch',6999,'978128713','','1 pieza','',1,'2019-10-29','/home/img/chrome.jpg',90,0,'#FF6600',7,2,1,NULL,0),
	(25,'Nintendo Switch',6999,'978128713','','1 pieza','',1,'2019-10-29','/home/img/chrome.jpg',99,0,'#FF6600',7,2,1,NULL,0),
	(26,'New Nintendo 3DS',5999,'210134987654','Caja con producto','1 pieza','Consola portátil de nintendo, con tecnologia 3D',1,NULL,'/new3ds',80,0,'#FF6600',7,2,3,NULL,0),
	(27,'SNES Mini',2999,'210198765409','1 Caja','Consola de videojuegos retro','Revive los mejores juegos del Super Nintendo',0,NULL,'/snes',75,1,'#FF6600',7,2,3,NULL,0),
	(28,'iPhone 7 (Refurbished)',0,NULL,NULL,'Dispositivo Movil Apple reacondicionado','Dispositivo Movil Apple reacondicionado.',0,NULL,NULL,50,1,'#FF6600',6,2,NULL,NULL,0),
	(29,'Nintendo Switch Hola',0,NULL,NULL,'1 pieza','',0,NULL,NULL,100,0,'#FF6600',7,16,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581443207/shingshing/productos/Nintendo%20Switch.jpg',0),
	(30,'Rancheritos',0,NULL,NULL,'50 gr','Rancheritos',0,NULL,NULL,1,1,'#FF6600',15,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581467902/shingshing/productos/Rancheritos.jpg',0),
	(31,'Papel Higiénico Pétalo ',0,NULL,NULL,'4 rollos','Papel higiénico con 234 hojas dobles C/U',0,NULL,NULL,5,1,'#FF6600',15,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581399965/shingshing/productos/7506425600397%20Petalo.png',0),
	(32,'Papel Higiénico Pétalo',0,NULL,NULL,'4 rollos','Papel higiénico con 234 hojas dobles C/U',0,NULL,NULL,2,1,'#FF6600',15,17,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581399965/shingshing/productos/7506425600397%20Petalo.png',1),
	(33,'Leche Santa Clara',0,NULL,NULL,'200 ml','Leche Entera Ultrapasterurizada',0,NULL,NULL,2,1,'#FF6600',16,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581474253/shingshing/productos/Leche%20Santa%20Clara.jpg',1),
	(34,'Shampoo Stefano',0,NULL,NULL,'532 ml','Shampoo Stefano alpha control caída para caballero.',0,NULL,NULL,2,1,'#FF6600',17,17,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581569032/shingshing/productos/Shampoo%20Stefano.png',1),
	(35,'Mega Chamoy',0,NULL,NULL,'1 kg','Mega Salsa de Chamoy ',0,NULL,NULL,1,1,'#FF6600',18,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581574863/shingshing/productos/Mega%20Chamoy.png',1),
	(36,'Huevo San Juan',0,NULL,NULL,'12 pzs','Huevo Blanco San Juan',0,NULL,NULL,1,1,'#FF6600',19,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581568618/shingshing/productos/Huevo%20San%20Juan.png',1),
	(37,'Crema Alpura',0,NULL,NULL,'900 ml','Crema Alpura ácida regular',0,NULL,NULL,1,1,'#FF6600',20,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581574509/shingshing/productos/Crema%20Alpura.png',1),
	(38,'Pure de Tomate',0,NULL,NULL,'1 kg','Pure de Tomate del Fuerte Natural',0,NULL,NULL,1,1,'#FF6600',21,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581575491/shingshing/productos/Pure%20de%20Tomate.png',1),
	(39,'Yogurth Danone',0,NULL,NULL,'140 g','Yoghurt Danone Mix Sabor Fresa Con Arroz Inflado',0,NULL,NULL,1,1,'#FF6600',22,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581478078/shingshing/productos/Yogurth%20Danone.png',1),
	(40,'Jugo Ades',0,NULL,NULL,'946 ml','Jugo de Soya Ades Sabor Manzana',0,NULL,NULL,0,1,'#FF6600',23,12,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581479100/shingshing/productos/Jugo%20Ades.png',1),
	(41,'Crema Dental Colgate',0,NULL,NULL,'50 ml','Cema dental con Flúor',0,NULL,NULL,1,1,'#FF6600',24,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581479348/shingshing/productos/Crema%20Dental%20Colgate.png',1),
	(42,'La Lechera',0,NULL,NULL,'387 g','Leche condensada Nestlé La Lechera',0,NULL,NULL,2,1,'#FF6600',25,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1582664004/shingshing/productos/La%20Lechera.png',1),
	(43,'Crema Lala',0,NULL,NULL,'450 ml','Crema Lala Entera Acida ',0,NULL,NULL,2,1,'#FF6600',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581480374/shingshing/productos/Crema%20Lala.png',1),
	(44,'Agua natural Epura',0,NULL,NULL,'5 lt','Agua Purificada Epura Natural',0,NULL,NULL,2,1,'#FF6600',27,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581656184/shingshing/productos/AGUA%20NATURAL%20EPURA.png',1),
	(45,'Sofùl',0,NULL,NULL,'105 g','Sofúl Yakult natural sin azúcar',0,NULL,NULL,1,1,'#FF6600',28,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581481413/shingshing/productos/Sof%C3%B9l.png',1),
	(46,'Avena Granvita',0,NULL,NULL,'35 g','Avena Instantánea Integral Con Arándanos',0,NULL,NULL,1,1,'#FF6600',30,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581653910/shingshing/productos/Avena%20Granvita.png',1),
	(47,'Bebida V8 Splash Kiwifresón',0,NULL,NULL,'200 ml','Bebida V8 Splash Kiwifresón De Zanahoria, Manzana, Kiwi Y Fresa, Con Vitamina A Y C, Sin Conservadores.',0,NULL,NULL,1,0,'#FF6600',31,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429912/shingshing/productos/Bebida%20V8%20Splash%20Kiwifres%C3%B3n.png',1),
	(48,'Leche Santa Clara',0,NULL,NULL,'1 litro','Leche Santa Clara Entera ',0,NULL,NULL,2,1,'#FF6600',32,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581483756/shingshing/productos/Leche%20Santa%20Clara.png',1),
	(49,'Papel Higiénico Kleenex Cottonelle',0,NULL,NULL,'4 rollos','Papel higiénico Cottonelle ULTRA ComfortCare \r\n',0,NULL,NULL,1,1,'#FF6600',33,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581657220/shingshing/productos/Papel%20Higi%C3%A9nico%20Kleenex%20Cottonelle.png',1),
	(50,'Yoghurt Lala',0,NULL,NULL,'125 g','Yoghurt Lala Con Fresa',0,NULL,NULL,1,1,'#FF6600',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581484962/shingshing/productos/Yoghurt%20Lala.png',1),
	(51,'Agua Bonafont',0,NULL,NULL,'1 lt','Agua Natural Bonafont',0,NULL,NULL,1,1,'#FF6600',1,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1582663344/shingshing/productos/Agua%20Bonafont.png',1),
	(52,'Azúcar',0,NULL,NULL,'1 kg','Azúcar Estándar',0,NULL,NULL,1,1,'#FF6600',34,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581531092/shingshing/productos/Az%C3%BAcar.png',1),
	(53,'Avena Quaker Instantanea',0,NULL,NULL,'296 g','Avena Quaker Instantanea Nuez, Pasas y Datiles \r\n',0,NULL,NULL,2,1,'#FF6600',35,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581487779/shingshing/productos/Avena%20Quaker%20Instantanea.png',1),
	(54,'Crema Alpura Selecta',0,NULL,NULL,'450 ml','Crema Alpura Selecta Premium',0,NULL,NULL,2,1,'#FF6600',20,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581490033/shingshing/productos/Crema%20Alpura%20Selecta.png',1),
	(55,'Leche Lala',0,NULL,NULL,'1 litro','Leche Lala Light Ultrapasteurizada\r\n',0,NULL,NULL,1,1,'#FF6600',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581491642/shingshing/productos/Leche%20Lala.png',1),
	(56,'Limpiador Fabuloso',0,NULL,NULL,'1 litro','Fabuloso Limpiador liquido multiusos Frescura Activa Antibacterial',0,NULL,NULL,1,1,'#FF6600',36,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1582663303/shingshing/productos/Limpiador%20Fabuloso.png',1),
	(57,'Axion',0,NULL,NULL,'750 ml','Lavatrastes líquido Axion aroma limón ',0,NULL,NULL,1,1,'#FF6600',37,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1582663258/shingshing/productos/Axion.png',1),
	(58,'Axion',0,NULL,NULL,'640  ml','Lavatrastes líquido Axion Complete Antibacterial',0,NULL,NULL,1,1,'#FF6600',37,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581651879/shingshing/productos/Axion.png',1),
	(59,'Ganchos Inova',0,NULL,NULL,'10 ganchos','Set de 10 Ganchos Inova Abadimex Premium Azul',0,NULL,NULL,2,1,'#FF6600',38,16,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581577269/shingshing/productos/Ganchos%20Inova.png',1),
	(60,'Shampoo Tressemé',0,NULL,NULL,'100 ml','Shampoo Tressemé Smooth and Silky',0,NULL,NULL,2,1,'#FF6600',39,17,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581577018/shingshing/productos/Shampoo%20Tressem%C3%A9.png',1),
	(61,'Tostadas',0,NULL,NULL,'360 g','Tostadas Milpa Real onduladas',0,NULL,NULL,1,1,'#FF6600',40,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581575590/shingshing/productos/Tostadas.png',1),
	(62,'Tortillas de Harinas',0,NULL,NULL,'12 pzas','Tortillinas Tía Rosa de Harina ',0,NULL,NULL,1,1,'#FF6600',41,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581575645/shingshing/productos/Tortillas%20de%20Harina.png',1),
	(63,'Avena instantanea',0,NULL,NULL,'1412 g','Cereal Quaker Instant de avena variedad de sabores.',0,NULL,NULL,1,1,'#FF6600',35,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581576397/shingshing/productos/Avena%20instantanea.png',1),
	(64,'Avena Quaker',0,NULL,NULL,'475 g','Avena Quaker de hojuela natural',0,NULL,NULL,1,1,'#FF6600',35,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581576019/shingshing/productos/Avena%20Quaker.png',1),
	(65,'Agua Levite Bonafont',0,NULL,NULL,'1 litro','Levite Agua clásica sabor Piña-coco',0,NULL,NULL,2,1,'#FF6600',1,12,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581576847/shingshing/productos/Agua%20Levite%20Bonafont.png',1),
	(66,'Hola',0,NULL,NULL,'Hola','',0,NULL,NULL,0,0,'#FF6600',2,3,NULL,'',0),
	(67,'Prueba',0,NULL,NULL,'Prueba','hola hola',0,NULL,NULL,5,1,'#FF6600',18,11,NULL,'',0),
	(68,'Prueba',0,NULL,NULL,'-mnbhjklkjhnbnxxxxx','',0,NULL,NULL,0,0,'#FF6600',2,3,NULL,'',0),
	(69,'hola!',0,NULL,NULL,'hola!','',0,NULL,NULL,1,0,'#FF6600',1,1,NULL,'',0),
	(70,'hola!',0,NULL,NULL,'hola!','',0,NULL,NULL,5,0,'#FF6600',1,1,NULL,'',0),
	(71,'hola',0,NULL,NULL,'hola','hola',0,NULL,NULL,6,1,'#FF6600',3,3,NULL,'',0),
	(72,'Demo',0,NULL,NULL,'1 l','',0,NULL,NULL,0,0,'#FF6600',1,1,NULL,'',0),
	(73,'Pelota amarrilla',0,NULL,NULL,'1 pza','Es una pelota de color amarillo, olor banana.',0,NULL,NULL,2,0,'#FF6600',1,9,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1582667790/shingshing/productos/Pelota%20amarrilla.png',0),
	(74,'Desodorante en aerosol Stefano',0,NULL,NULL,'s','ss',0,NULL,NULL,0,0,'#FF6600',14,1,NULL,'',0),
	(75,'Demoo',0,NULL,NULL,'demoo','hola',0,NULL,NULL,3,0,'#FF6600',14,1,NULL,'',0),
	(76,'Bug',0,NULL,NULL,'--...','aaaa',0,NULL,NULL,0,0,'#FF6600',14,1,NULL,'',0),
	(77,'Pruebaaaaa',0,NULL,NULL,'1 lt','hola descripción!',0,NULL,NULL,4,1,'#FF00C2',17,9,NULL,'',0),
	(78,'*Cheetos Torciditos',0,NULL,NULL,'100 g','Botana de cereal de maíz con sabores queso y chile, reducida en grasa saturada.',0,NULL,NULL,2,3,'#FF7D06',46,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429529/shingshing/productos/%2ACheetos%20Torciditos.png',1),
	(79,'*Choco Milk',0,NULL,NULL,'400 g',' Choco Milk®, que es un alimento en polvo fortificado para preparar una bebida a sabor chocolate. La fórmula Nutri Pantera tiene 23 Vitaminas y Minerales tales como Calcio, Hierro, Ácido Fólico y Vitaminas del complejo B que apoyan el desarrollo cada día',0,NULL,NULL,5,2,'#6591FF',47,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429787/shingshing/productos/%2AChoco%20Milk.png',1),
	(80,'*Heineken Six 00',0,NULL,NULL,'1 pza','¿En junta de trabajo? ¿Después del gym? ¿Antes manejar? ¡Tómate una Heineken 0.0. En serio, ahora puedes. conocer la primer cerveza premium sin alcohol en el mercado mexicano con Heineken 0.0. ',0,NULL,NULL,5,2,'#2B8A41',48,12,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429770/shingshing/productos/%2AHeineken%20Six%2000.png',1),
	(81,'*Yakultt',0,NULL,NULL,'1 pza',' Yakult es un producto lácteo que está enriquecido con el lactobacilo Casei Shirota que protege el estómago en contra de enfermedades para fortalecer el sistema inmune de manera natural ya que es una bebida buena para la salud. Consciente a tu familia con este paquete de 5 Yakult, y mantenlos sanos.',0,NULL,NULL,2,2,'#C20F0F',28,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429754/shingshing/productos/%2AYakultt.png',1),
	(82,'*Boing Fresa',0,NULL,NULL,'250 ml','¡Ofrece a tus pequeños un delicioso Boing, solo para saciar su antojo!\r\nHecho a base de pulpa de fresa con un tamaño práctico para llevarlo a cualquier lugar, aporta sólo 84 kcal. Ofrécelo frío de preferencia.',0,NULL,NULL,2,2,'#F90427',49,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429513/shingshing/productos/%2ABoing%20Fresa.png',1),
	(83,'Yogurt Yoplait',0,NULL,NULL,'1kg','El desayuno es la comida más importante del día, así que empieza con el pie derecho con el yogurt natural Yoplait de 1 Kg.  Alcanza para 10 porciones aproximadamente, te aporta nutrientes favorables a tu cuerpo, como calcio y proteínas, ademas y no contiene conservadores ',0,NULL,NULL,3,2,'#000000',51,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429697/shingshing/productos/Yogurt%20Yoplait.png',1),
	(84,'*Leche Nesquick',0,NULL,NULL,'330 ml','Leche parcialmente descremada ultrapasteurizada adicionada de vitaminas, ( A y D ) reducida en azucar y grasa. Lista para beber sabor chocolate ',0,NULL,NULL,4,2,'#000000',25,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429656/shingshing/productos/%2ALeche%20Nesquick.png',1),
	(85,'*Natural Good Shapess',0,NULL,NULL,'500 ml ','Hidrata tu cuerpo y pon a volar tu mente con los sabores más exóticos. ¡Dale a tu cuerpo un delicioso respiro! Mejora tu digestión, estabiliza tus niveles de alcalinidad y reduce el colesterol.',0,NULL,NULL,3,1,'#0C61A2',52,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429621/shingshing/productos/%2ANatural%20Good%20Shapess.png',1),
	(86,'*Choco Roles',0,NULL,NULL,'100 g','Los chocoroles Marinela son unos deliciosos pastelitos enrollados con relleno cremoso sabor piña y cobertura sabor chocolate, adicionado con vitaminas y minerales. Es un clásico de Marinela que puedes disfrutar en cualquier momento de antojo.',0,NULL,NULL,2,2,'#FB7F0E',53,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429568/shingshing/productos/%2AChoco%20Roles.png',1),
	(87,'*Mayonesa McCormic',0,NULL,NULL,'390 g','La mayonesa con jugo de limones McCormick es un aderezo que puedes incluir fácilmente en tus recetas favoritas y consentir a tu familia a la hora de la comida o cena. ',0,NULL,NULL,4,2,'#3ABE2C',54,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429555/shingshing/productos/%2AMayonesa%20McCormic.png',1),
	(88,'Cheetos Bolita',0,NULL,NULL,'100 g','Botana Cheetos® Bolitas es una deliciosa opción hecha de cereal de maíz con el sabor a queso y chile que tanto te gusta. Para esos momentos familiares y divertidos, botana Cheetos® Bolitas es la perfecta elección. Para disfrutarlos aún más puedes compartirlos con todos tus amigos y familiares.',0,NULL,NULL,5,2,'#E7E71D',46,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429543/shingshing/productos/Cheetos%20Bolita.png',1),
	(89,'*Boing Mango',0,NULL,NULL,'250 ml','¡Ofrece a tus pequeños un delicioso Boing, solo para saciar su antojo! Hecho a base de pulpa de fresa con un tamaño práctico para llevarlo a cualquier lugar, aporta sólo 84 kcal. Ofrécelo frío de preferencia.',0,NULL,NULL,2,2,'#F58A48',28,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429892/shingshing/productos/%2ABoing%20Mango.png',1),
	(90,'DEMO 4 MAYOOOO',0,NULL,NULL,'1 PZA','Este es un producto demo',0,NULL,NULL,5,1,'#2FF91A',55,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1588640328/shingshing/productos/DEMO%204%20MAYO.png',0),
	(91,'*Milanes',0,NULL,NULL,'1 pza','descripción de la milaneza',0,NULL,NULL,10,1,'#D68547',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429503/shingshing/productos/%2AMilanes.png',1),
	(92,'*Batido de frutos',0,NULL,NULL,'1 pza','Batido frutos del bosque',0,NULL,NULL,2,3,'#79D200',46,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429486/shingshing/productos/%2ABatido%20de%20frutos.png',1),
	(93,'*Jugo Ades Soya Manzana',0,NULL,NULL,'500 ml','Jugo Ades Soya Manzana',0,NULL,NULL,5,1,'#DBB100',23,12,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429473/shingshing/productos/%2AJugo%20Ades%20Soya%20Manzana.png',1),
	(94,'*Galletas Chokis',0,NULL,NULL,'200 g','Galletas chokis',0,NULL,NULL,2,3,'#6767FF',46,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429454/shingshing/productos/%2AGalletas%20Chokis.png',1),
	(95,'*Pan integral',0,NULL,NULL,'340 g','Pan integral',0,NULL,NULL,2,2,'#FF8ABC',46,7,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589429440/shingshing/productos/%2APan%20integral.png',1),
	(96,'Refresco Coca-Cola',0,NULL,NULL,'500 ml','Cocacola 500 ml',0,NULL,NULL,100,3,'#E71F1F',2,12,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1588726334/shingshing/productos/Refresco%20Coca-Cola.png',0),
	(97,'*Chiles Jalapeños',0,NULL,NULL,'159 g','Chiles jalapeños en escabeche',0,NULL,NULL,2,3,'#FFB600',57,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589427491/shingshing/productos/%2AChiles%20Jalape%C3%B1os.png',1),
	(98,'*Crema Alpura',0,NULL,NULL,'200 ml','Crema Alpura',0,NULL,NULL,4,3,'#04C3FF',20,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589427664/shingshing/productos/Crema%20Alpura.png',1),
	(99,'*Paq. Galletas Arcoiris',0,NULL,NULL,'185 g','Paquete de galletas Arcoiris',0,NULL,NULL,5,1,'#FF96FF',59,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589427904/shingshing/productos/Paq.%20Galletas%20Arcoiris.png',1),
	(100,'*Gelatina D\' Gary Light',0,NULL,NULL,'20 g','Gelatina D\' Gary light',0,NULL,NULL,2,2,'#CB77FF',60,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589428130/shingshing/productos/Gelatina%20D%27%20Gary%20Light.png',1),
	(101,'*Papel Higiénico Pétalo 12 rollos',0,NULL,NULL,'12 rollos','Papel higiénico Pétalo',0,NULL,NULL,10,2,'#4AD8B6',15,17,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589428323/shingshing/productos/%2APapel%20Higi%C3%A9nico%20P%C3%A9talo%2012%20rollos.png',1),
	(102,'*Salsa Inglesa',0,NULL,NULL,'145 ml','Salsa inglesa Crosse & Blackwell',0,NULL,NULL,3,2,'#F39D40',61,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589428512/shingshing/productos/Salsa%20Inglesa.png',1),
	(103,'*Salsa Valentina',0,NULL,NULL,'370 ml','Salsa Valentina',0,NULL,NULL,2,2,'#F37F00',62,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589428655/shingshing/productos/Salsa%20Valentina.png',1),
	(104,'*Tostadas Charras',0,NULL,NULL,'300 g','Tostadas Charras',0,NULL,NULL,2,3,'#F3DB00',63,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1589428796/shingshing/productos/%2ATostadas%20Charras.png',1),
	(105,'Demoo',0,NULL,NULL,'1 l','2345678',0,NULL,NULL,5,2,'#C396FF',41,16,NULL,'',0),
	(106,'Salsa de tomate',0,NULL,NULL,'750','Salsa de tomate para pizza',0,NULL,NULL,5,3,'#DF275B',10,1,NULL,'',1);

/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla producto_favorito
# ------------------------------------------------------------

DROP TABLE IF EXISTS `producto_favorito`;

CREATE TABLE `producto_favorito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario_idx` (`id_usuario`),
  KEY `id_producto_idx` (`id_producto`),
  CONSTRAINT `id_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `producto_favorito` WRITE;
/*!40000 ALTER TABLE `producto_favorito` DISABLE KEYS */;

INSERT INTO `producto_favorito` (`id`, `id_usuario`, `id_producto`)
VALUES
	(1,2,63),
	(2,2,60),
	(3,2,57),
	(5,2,61),
	(8,2,64),
	(14,107,5),
	(15,107,41),
	(17,42,74),
	(18,42,62),
	(21,42,65),
	(23,42,56),
	(27,42,77),
	(28,42,90),
	(29,42,96),
	(30,119,96),
	(31,88,96),
	(37,107,103);

/*!40000 ALTER TABLE `producto_favorito` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla producto_valoracion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `producto_valoracion`;

CREATE TABLE `producto_valoracion` (
  `id_producto_valoracion` int(11) NOT NULL AUTO_INCREMENT,
  `valoracion` int(11) NOT NULL,
  `comentario` varchar(200) NOT NULL,
  `producto_id_producto` int(11) NOT NULL,
  `usuario_id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_producto_valoracion`),
  KEY `fk_producto_valoracion_producto1_idx` (`producto_id_producto`),
  KEY `fk_producto_valoracion_usuario1_idx` (`usuario_id_usuario`),
  CONSTRAINT `fk_producto_valoracion_producto1` FOREIGN KEY (`producto_id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_valoracion_usuario1` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `producto_valoracion` WRITE;
/*!40000 ALTER TABLE `producto_valoracion` DISABLE KEYS */;

INSERT INTO `producto_valoracion` (`id_producto_valoracion`, `valoracion`, `comentario`, `producto_id_producto`, `usuario_id_usuario`)
VALUES
	(1,4,'',61,2),
	(2,1,'',64,2),
	(3,1,'',76,107),
	(4,4,'',75,107),
	(5,3,'',5,107),
	(6,1,'',41,107),
	(7,0,'',76,84),
	(8,0,'',75,84),
	(9,0,'',65,42),
	(10,0,'',56,42),
	(11,0,'',64,42),
	(12,0,'',63,42),
	(13,0,'',5,42),
	(14,4,'',77,42),
	(15,0,'',79,42),
	(16,0,'',78,42),
	(17,0,'',84,42),
	(18,0,'',88,42),
	(19,0,'',89,42),
	(20,0,'',90,42),
	(21,0,'',96,42),
	(22,3,'',96,88),
	(23,0,'',96,84),
	(24,0,'',83,84),
	(25,0,'',94,84),
	(26,0,'',95,84),
	(27,3,'',104,107),
	(28,2,'',103,107),
	(29,0,'',104,84),
	(30,0,'',101,84),
	(31,0,'',103,84),
	(32,0,'',104,106),
	(33,0,'',103,106),
	(34,0,'',93,106),
	(35,0,'',104,101),
	(36,0,'',104,2);

/*!40000 ALTER TABLE `producto_valoracion` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla productos_tiendas
# ------------------------------------------------------------

DROP TABLE IF EXISTS `productos_tiendas`;

CREATE TABLE `productos_tiendas` (
  `id_producto_tienda` int(11) NOT NULL AUTO_INCREMENT,
  `producto_tienda` varchar(45) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_catalogo_tienda` int(11) NOT NULL,
  PRIMARY KEY (`id_producto_tienda`),
  KEY `fk_id_producto` (`id_producto`),
  KEY `fk_id_catalogo_tienda` (`id_catalogo_tienda`),
  CONSTRAINT `fk_id_catalogo_tienda` FOREIGN KEY (`id_catalogo_tienda`) REFERENCES `catalogo_tienda` (`id_catalogo_tienda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `productos_tiendas` WRITE;
/*!40000 ALTER TABLE `productos_tiendas` DISABLE KEYS */;

INSERT INTO `productos_tiendas` (`id_producto_tienda`, `producto_tienda`, `id_producto`, `id_catalogo_tienda`)
VALUES
	(1,'AXE-WALMART',10,3),
	(2,'AXE-NETO',10,2),
	(3,'AXE-OXXO',10,1),
	(4,'STEFANO AERO',17,3),
	(5,'',28,3),
	(6,'',28,2),
	(7,'',28,1),
	(8,'',29,3),
	(9,'',29,2),
	(10,'',29,1),
	(11,'R-kmart',30,5),
	(12,'R-7eleven',30,4),
	(13,'R-walmart',30,3),
	(14,'R-neto',30,2),
	(15,'R-oxxo',30,1),
	(26,'',30,3),
	(27,'',30,2),
	(28,'',30,1),
	(29,'',19,3),
	(30,'',19,2),
	(31,'',19,1),
	(32,'345678765432123456789',9,3),
	(33,'',9,2),
	(34,'',9,1),
	(35,'7506425600397 Petalo ',31,3),
	(36,'',31,2),
	(37,'',31,1),
	(38,'7506425600397 Petalo ',32,3),
	(39,'',32,2),
	(40,'',32,1),
	(41,'',33,8),
	(42,'',33,7),
	(43,'',33,6),
	(44,'',33,5),
	(45,'',33,4),
	(46,'7501295600089 SCLARAENTERA',33,3),
	(47,'',33,2),
	(48,'',33,1),
	(49,'',34,8),
	(50,'',34,7),
	(51,'',34,6),
	(52,'',34,5),
	(53,'',34,4),
	(54,'7509546077956 STEFANO SH ',34,3),
	(55,'',34,2),
	(56,'',34,1),
	(57,'',35,8),
	(58,'',35,7),
	(59,'',35,6),
	(60,'',35,5),
	(61,'',35,4),
	(62,'738545020626 MEGA CHAMOY',35,3),
	(63,'',35,2),
	(64,'',35,1),
	(65,'',2,8),
	(66,'',2,7),
	(67,'',2,6),
	(68,'',2,5),
	(69,'',2,4),
	(70,'',2,3),
	(71,'',2,2),
	(72,'',2,1),
	(73,'',36,8),
	(74,'',36,7),
	(75,'',36,6),
	(76,'',36,5),
	(77,'',36,4),
	(78,'7503000555011 SJUAN HVO',36,3),
	(79,'',36,2),
	(80,'',36,1),
	(81,'',37,8),
	(82,'',37,7),
	(83,'',37,6),
	(84,'',37,5),
	(85,'',37,4),
	(86,'7501055903924 ALPURA CRE',37,3),
	(87,'',37,2),
	(88,'',37,1),
	(89,'',38,8),
	(90,'',38,7),
	(91,'',38,6),
	(92,'',38,5),
	(93,'',38,4),
	(94,'7501079700011 DFUERTE PU',38,3),
	(95,'',38,2),
	(96,'',38,1),
	(97,'',39,8),
	(98,'',39,7),
	(99,'',39,6),
	(100,'',39,5),
	(101,'',39,4),
	(102,'7501032395230 DANONE140G',39,3),
	(103,'',39,2),
	(104,'',39,1),
	(105,'',40,8),
	(106,'',40,7),
	(107,'',40,6),
	(108,'',40,5),
	(109,'',40,4),
	(110,'7501005102667 ADES JUGO',40,3),
	(111,'',40,2),
	(112,'',40,1),
	(113,'',41,8),
	(114,'',41,7),
	(115,'',41,6),
	(116,'',41,5),
	(117,'',41,4),
	(118,'632971598608 COLGATE',41,3),
	(119,'',41,2),
	(120,'',41,1),
	(121,'',42,8),
	(122,'',42,7),
	(123,'',42,6),
	(124,'',42,5),
	(125,'',42,4),
	(126,'7501058651662 LECHERA SF',42,3),
	(127,'',42,2),
	(128,'',42,1),
	(129,'',43,8),
	(130,'',43,7),
	(131,'',43,6),
	(132,'',43,5),
	(133,'',43,4),
	(134,'7501020511451 LALA CREMA',43,3),
	(135,'',43,2),
	(136,'',43,1),
	(137,'',44,8),
	(138,'',44,7),
	(139,'',44,6),
	(140,'',44,5),
	(141,'',44,4),
	(142,'AGUA NATURAL EPURA 5 LT',44,3),
	(143,'',44,2),
	(144,'',44,1),
	(145,'',45,8),
	(146,'',45,7),
	(147,'',45,6),
	(148,'ALIME LAC SOFUL',45,5),
	(149,'',45,4),
	(150,'',45,3),
	(151,'',45,2),
	(152,'',45,1),
	(153,'',46,8),
	(154,'',46,7),
	(155,'',46,6),
	(156,'AVENA GRANVITA 35GR',46,5),
	(157,'',46,4),
	(158,'',46,3),
	(159,'',46,2),
	(160,'',46,1),
	(161,'',47,8),
	(162,'',47,7),
	(163,'',47,6),
	(164,'JUGO V8 SPLASH KIWI FRES',47,5),
	(165,'',47,4),
	(166,'',47,3),
	(167,'',47,2),
	(168,'',47,1),
	(169,'',48,8),
	(170,'',48,7),
	(171,'',48,6),
	(172,'LECHE ULTRA SANTA CLAR 1',48,5),
	(173,'',48,4),
	(174,'',48,3),
	(175,'',48,2),
	(176,'',48,1),
	(177,'',49,8),
	(178,'',49,7),
	(179,'',49,6),
	(180,'PAPEL PU COTTONELLE 4ROL',49,5),
	(181,'',49,4),
	(182,'',49,3),
	(183,'',49,2),
	(184,'',49,1),
	(185,'',50,8),
	(186,'',50,7),
	(187,'',50,6),
	(188,'YOGHURT LALA 125GR',50,5),
	(189,'',50,4),
	(190,'',50,3),
	(191,'',50,2),
	(192,'',50,1),
	(193,'',51,8),
	(194,'',51,7),
	(195,'',51,6),
	(196,'AGUA NATURAL BONAFONT',51,5),
	(197,'',51,4),
	(198,'',51,3),
	(199,'',51,2),
	(200,'',51,1),
	(201,'',52,8),
	(202,'',52,7),
	(203,'',52,6),
	(204,'AZUCAR SORIANA 1KG',52,5),
	(205,'',52,4),
	(206,'',52,3),
	(207,'',52,2),
	(208,'',52,1),
	(209,'',53,8),
	(210,'',53,7),
	(211,'000 Avena Quaker Nuez',53,6),
	(212,'',53,5),
	(213,'',53,4),
	(214,'',53,3),
	(215,'',53,2),
	(216,'',53,1),
	(217,'7501055905034 ALPURA SEL ',54,8),
	(218,'',54,7),
	(219,'',54,6),
	(220,'',54,5),
	(221,'',54,4),
	(222,'',54,3),
	(223,'',54,2),
	(224,'',54,1),
	(225,'7501020547054 LALA LIGHT',55,8),
	(226,'',55,7),
	(227,'',55,6),
	(228,'',55,5),
	(229,'',55,4),
	(230,'',55,3),
	(231,'',55,2),
	(232,'',55,1),
	(233,'750035910041 FABULOSO 1',56,8),
	(234,'',56,7),
	(235,'',56,6),
	(236,'',56,5),
	(237,'',56,4),
	(238,'',56,3),
	(239,'',56,2),
	(240,'',56,1),
	(241,'7509546076218 AXION 64 OM',57,8),
	(242,'',57,7),
	(243,'',57,6),
	(244,'',57,5),
	(245,'',57,4),
	(246,'',57,3),
	(247,'',57,2),
	(248,'',57,1),
	(249,'7509546078397 AXION 640',58,8),
	(250,'',58,7),
	(251,'',58,6),
	(252,'',58,5),
	(253,'',58,4),
	(254,'',58,3),
	(255,'',58,2),
	(256,'',58,1),
	(257,'',59,8),
	(258,'',59,7),
	(259,'',59,6),
	(260,'',59,5),
	(261,'',59,4),
	(262,'7503025465074 GANCHO INF',59,3),
	(263,'',59,2),
	(264,'',59,1),
	(265,'',60,8),
	(266,'',60,7),
	(267,'',60,6),
	(268,'',60,5),
	(269,'',60,4),
	(270,'630509826452 TRS HAIR HU',60,3),
	(271,'',60,2),
	(272,'',60,1),
	(273,'',61,8),
	(274,'',61,7),
	(275,'',61,6),
	(276,'',61,5),
	(277,'',61,4),
	(278,'7501030457756 TOSTADA',61,3),
	(279,'',61,2),
	(280,'',61,1),
	(281,'',62,8),
	(282,'',62,7),
	(283,'',62,6),
	(284,'',62,5),
	(285,'',62,4),
	(286,'7501640111017 TORTILAS',62,3),
	(287,'',62,2),
	(288,'',62,1),
	(289,'',63,8),
	(290,'',63,7),
	(291,'',63,6),
	(292,'',63,5),
	(293,'',63,4),
	(294,'094331198758 AVENA INSTA',63,3),
	(295,'',63,2),
	(296,'',63,1),
	(297,'',64,8),
	(298,'',64,7),
	(299,'',64,6),
	(300,'',64,5),
	(301,'',64,4),
	(302,'094331238744 AVENA',64,3),
	(303,'',64,2),
	(304,'',64,1),
	(305,'',65,8),
	(306,'',65,7),
	(307,'',65,6),
	(308,'',65,5),
	(309,'',65,4),
	(310,'758104006359 LEVITE INF ',65,3),
	(311,'',65,2),
	(312,'',65,1),
	(313,'',16,8),
	(314,'',16,7),
	(315,'',16,6),
	(316,'',16,5),
	(317,'',16,4),
	(318,'',16,3),
	(319,'',16,2),
	(320,'',16,1),
	(321,'',66,8),
	(322,'',66,7),
	(323,'',66,6),
	(324,'',66,5),
	(325,'',66,4),
	(326,'',66,3),
	(327,'',66,2),
	(328,'345678765432123456789',67,8),
	(329,'',67,7),
	(330,'',67,6),
	(331,'',67,5),
	(332,'',67,4),
	(333,'',67,3),
	(334,'',67,2),
	(335,'',22,8),
	(336,'',22,7),
	(337,'',22,6),
	(338,'',22,5),
	(339,'',22,4),
	(340,'',22,3),
	(341,'',22,2),
	(342,'',68,8),
	(343,'',68,7),
	(344,'',68,6),
	(345,'',68,5),
	(346,'',68,4),
	(347,'',68,3),
	(348,'',68,2),
	(349,'',1,8),
	(350,'',1,7),
	(351,'',1,6),
	(352,'',1,5),
	(353,'',1,4),
	(354,'',1,3),
	(355,'',1,2),
	(356,'',1,1),
	(357,'1234567890\'',5,8),
	(358,'',5,7),
	(359,'',5,6),
	(360,'',5,5),
	(361,'',5,4),
	(362,'',5,3),
	(363,'',5,2),
	(364,'',5,1),
	(365,'',6,8),
	(366,'',6,7),
	(367,'',6,6),
	(368,'',6,5),
	(369,'',6,4),
	(370,'',6,3),
	(371,'',6,2),
	(372,'',6,1),
	(373,'',7,8),
	(374,'',7,7),
	(375,'',7,6),
	(376,'',7,5),
	(377,'',7,4),
	(378,'',7,3),
	(379,'',7,2),
	(380,'',7,1),
	(381,'',8,8),
	(382,'',8,7),
	(383,'',8,6),
	(384,'',8,5),
	(385,'',8,4),
	(386,'',8,3),
	(387,'',8,2),
	(388,'',8,1),
	(389,'demo',4,8),
	(390,'',4,7),
	(391,'',4,6),
	(392,'',4,5),
	(393,'',4,4),
	(394,'7501088505454 SYNALAR-S ',4,3),
	(395,'',4,2),
	(396,'',4,1),
	(397,'demo',3,8),
	(398,'',3,7),
	(399,'',3,6),
	(400,'',3,5),
	(401,'',3,4),
	(402,'',3,3),
	(403,'',3,2),
	(404,'',3,1),
	(405,'demo',11,8),
	(406,'',11,7),
	(407,'',11,6),
	(408,'',11,5),
	(409,'',11,4),
	(410,'',11,3),
	(411,'',11,2),
	(412,'',11,1),
	(413,'12345678',12,8),
	(414,'',12,7),
	(415,'',12,6),
	(416,'',12,5),
	(417,'',12,4),
	(418,'',12,3),
	(419,'',12,2),
	(420,'',12,1),
	(421,'',13,8),
	(422,'',13,7),
	(423,'',13,6),
	(424,'',13,5),
	(425,'',13,4),
	(426,'',13,3),
	(427,'',13,2),
	(428,'',13,1),
	(429,'',14,8),
	(430,'',14,7),
	(431,'',14,6),
	(432,'',14,5),
	(433,'',14,4),
	(434,'',14,3),
	(435,'',14,2),
	(436,'',14,1),
	(437,'12345678',15,8),
	(438,'',15,7),
	(439,'',15,6),
	(440,'',15,5),
	(441,'',15,4),
	(442,'',15,3),
	(443,'',15,2),
	(444,'',15,1),
	(445,'12345678',18,8),
	(446,'',18,7),
	(447,'',18,6),
	(448,'',18,5),
	(449,'',18,4),
	(450,'',18,3),
	(451,'',18,2),
	(452,'',18,1),
	(453,'12345678',21,8),
	(454,'',21,7),
	(455,'',21,6),
	(456,'',21,5),
	(457,'',21,4),
	(458,'',21,3),
	(459,'',21,2),
	(460,'',21,1),
	(461,'',69,8),
	(462,'',69,7),
	(463,'',69,6),
	(464,'',69,5),
	(465,'',69,4),
	(466,'',69,3),
	(467,'',69,2),
	(468,'',69,1),
	(469,'',70,8),
	(470,'',70,7),
	(471,'',70,6),
	(472,'',70,5),
	(473,'',70,4),
	(474,'',70,3),
	(475,'',70,2),
	(476,'',70,1),
	(477,'',71,8),
	(478,'',71,7),
	(479,'98765432qw3456789',71,6),
	(480,'',71,5),
	(481,'',71,4),
	(482,'',71,3),
	(483,'',71,2),
	(484,'',71,1),
	(485,'',72,8),
	(486,'',72,7),
	(487,'',72,6),
	(488,'',72,5),
	(489,'',72,4),
	(490,'',72,3),
	(491,'',72,2),
	(492,'',72,1),
	(493,'7506425600397 Petalo ',73,8),
	(494,'6545654354',73,7),
	(495,'341342345324r3',73,6),
	(496,'',73,5),
	(497,'',73,4),
	(498,'',73,3),
	(499,'',73,2),
	(500,'',73,1),
	(501,'s',74,8),
	(502,'',74,7),
	(503,'',74,6),
	(504,'',74,5),
	(505,'',74,4),
	(506,'',74,3),
	(507,'',74,2),
	(508,'',74,1),
	(509,'ee',75,8),
	(510,'',75,7),
	(511,'',75,6),
	(512,'',75,5),
	(513,'',75,4),
	(514,'',75,3),
	(515,'',75,2),
	(516,'',75,1),
	(517,'aa',76,12),
	(518,'',76,8),
	(519,'',76,7),
	(520,'',76,6),
	(521,'',76,5),
	(522,'',76,4),
	(523,'',76,3),
	(524,'',76,2),
	(525,'',76,1),
	(526,'345678765432123456789',77,18),
	(527,'',77,17),
	(528,'',77,16),
	(529,'',77,14),
	(530,'',77,13),
	(531,'',77,7),
	(532,'',77,4),
	(533,'',77,3),
	(534,'',77,1),
	(535,'',78,18),
	(536,'',78,17),
	(537,'',78,16),
	(538,'',78,14),
	(539,'',78,13),
	(540,'',78,7),
	(541,'',78,4),
	(542,'7501011167605 CHEETOS 10',78,3),
	(543,'',78,1),
	(544,'',79,18),
	(545,'',79,17),
	(546,'',79,16),
	(547,'',79,14),
	(548,'',79,13),
	(549,'',79,7),
	(550,'',79,4),
	(551,'7506205802157 CHOCOMILK',79,3),
	(552,'',79,1),
	(553,'',80,18),
	(554,'',80,17),
	(555,'',80,16),
	(556,'',80,14),
	(557,'',80,13),
	(558,'',80,7),
	(559,'',80,4),
	(560,'7503024416596 HNKN 0.0 6',80,3),
	(561,'',80,1),
	(562,'',81,18),
	(563,'',81,17),
	(564,'',81,16),
	(565,'',81,14),
	(566,'',81,13),
	(567,'',81,7),
	(568,'',81,4),
	(569,'7501025511005 YAKULT BEB',81,3),
	(570,'',81,1),
	(571,'',82,18),
	(572,'',82,17),
	(573,'',82,16),
	(574,'',82,14),
	(575,'',82,13),
	(576,'',82,7),
	(577,'',82,4),
	(578,'75001766 BOING BEB FR',82,3),
	(579,'',82,1),
	(580,'',83,18),
	(581,'',83,17),
	(582,'',83,16),
	(583,'',83,14),
	(584,'',83,13),
	(585,'',83,7),
	(586,'',83,4),
	(587,'7501040090028 YOPLAIT BA',83,3),
	(588,'',83,1),
	(589,'',84,18),
	(590,'',84,17),
	(591,'',84,16),
	(592,'',84,14),
	(593,'',84,13),
	(594,'',84,7),
	(595,'NESQUICK LECHE CHO 330ML',84,4),
	(596,'',84,3),
	(597,'',84,1),
	(598,'',85,18),
	(599,'',85,17),
	(600,'',85,16),
	(601,'',85,14),
	(602,'',85,13),
	(603,'',85,7),
	(604,'NATURAL GOOD ALOE Y MORA ',85,4),
	(605,'',85,3),
	(606,'',85,1),
	(607,'',86,18),
	(608,'',86,17),
	(609,'',86,16),
	(610,'',86,14),
	(611,'',86,13),
	(612,'',86,7),
	(613,'MARIN CHOCOROLE 80GR',86,4),
	(614,'',86,3),
	(615,'',86,1),
	(616,'',87,18),
	(617,'',87,17),
	(618,'',87,16),
	(619,'',87,14),
	(620,'',87,13),
	(621,'',87,7),
	(622,'7501003340139 MCCORMI MA',87,4),
	(623,'',87,3),
	(624,'',87,1),
	(625,'',88,18),
	(626,'',88,17),
	(627,'',88,16),
	(628,'',88,14),
	(629,'',88,13),
	(630,'',88,7),
	(631,'',88,4),
	(632,'7501011130920 CHETOS BO',88,3),
	(633,'',88,1),
	(634,'',89,18),
	(635,'',89,17),
	(636,'',89,16),
	(637,'',89,14),
	(638,'',89,13),
	(639,'',89,7),
	(640,'',89,4),
	(641,'75001759 BOING BEB MA',89,3),
	(642,'',89,1),
	(643,'',90,18),
	(644,'456789876545678987654 567yu',90,17),
	(645,'',90,16),
	(646,'',90,14),
	(647,'',90,13),
	(648,'',90,7),
	(649,'',90,4),
	(650,'',90,3),
	(651,'',90,1),
	(652,'',91,18),
	(653,'',91,17),
	(654,'',91,16),
	(655,'',91,14),
	(656,'',91,13),
	(657,'201961078439 MILANES PEC',91,7),
	(658,'',91,4),
	(659,'',91,3),
	(660,'',91,1),
	(661,'',92,18),
	(662,'',92,17),
	(663,'batido frutos del bosque',92,16),
	(664,'',92,14),
	(665,'',92,13),
	(666,'',92,7),
	(667,'',92,4),
	(668,'',92,3),
	(669,'',92,1),
	(670,'',93,18),
	(671,'',93,17),
	(672,'JUGO ADES SOYA MANZANA 9',93,16),
	(673,'',93,14),
	(674,'',93,13),
	(675,'',93,7),
	(676,'',93,4),
	(677,'',93,3),
	(678,'',93,1),
	(679,'',94,18),
	(680,'',94,17),
	(681,'CHOKIS PAKET',94,16),
	(682,'',94,14),
	(683,'',94,13),
	(684,'',94,7),
	(685,'',94,4),
	(686,'',94,3),
	(687,'',94,1),
	(688,'',95,18),
	(689,'',95,17),
	(690,'PAN BIMBO 340GR',95,16),
	(691,'',95,14),
	(692,'',95,13),
	(693,'7501000101238 PANBLANCO',95,7),
	(694,'',95,4),
	(695,'',95,3),
	(696,'',95,1),
	(697,'',96,18),
	(698,'',96,17),
	(699,'',96,16),
	(700,'',96,14),
	(701,'',96,13),
	(702,'041390047602 KIKOMAN SAL',96,7),
	(703,'',96,4),
	(704,'',96,3),
	(705,'',96,1),
	(706,'',97,22),
	(707,'',97,21),
	(708,'',97,20),
	(709,'',97,18),
	(710,'CHILE JALAPENO RAJAS LA',97,17),
	(711,'',97,16),
	(712,'',97,14),
	(713,'',97,13),
	(714,'',97,7),
	(715,'',97,4),
	(716,'',97,3),
	(717,'',97,1),
	(718,'',98,22),
	(719,'',98,21),
	(720,'',98,20),
	(721,'',98,18),
	(722,'CREMA ALPURA 200MLT',98,17),
	(723,'',98,16),
	(724,'',98,14),
	(725,'',98,13),
	(726,'',98,7),
	(727,'',98,4),
	(728,'',98,3),
	(729,'',98,1),
	(730,'',99,22),
	(731,'',99,21),
	(732,'',99,20),
	(733,'',99,18),
	(734,'GALLETA GAMESA 185GR',99,17),
	(735,'',99,16),
	(736,'',99,14),
	(737,'',99,13),
	(738,'',99,7),
	(739,'',99,4),
	(740,'',99,3),
	(741,'',99,1),
	(742,'',100,22),
	(743,'',100,21),
	(744,'',100,20),
	(745,'',100,18),
	(746,'GELATINA DGARI 20GR',100,17),
	(747,'',100,16),
	(748,'',100,14),
	(749,'',100,13),
	(750,'',100,7),
	(751,'',100,4),
	(752,'',100,3),
	(753,'',100,1),
	(754,'',101,22),
	(755,'',101,21),
	(756,'',101,20),
	(757,'',101,18),
	(758,'PAPEL RX PETALO 12 ROL',101,17),
	(759,'',101,16),
	(760,'',101,14),
	(761,'',101,13),
	(762,'',101,7),
	(763,'',101,4),
	(764,'',101,3),
	(765,'',101,1),
	(766,'',102,22),
	(767,'',102,21),
	(768,'',102,20),
	(769,'',102,18),
	(770,'SALSA INGLESA CROSSE & B',102,17),
	(771,'',102,16),
	(772,'',102,14),
	(773,'',102,13),
	(774,'',102,7),
	(775,'',102,4),
	(776,'',102,3),
	(777,'',102,1),
	(778,'',103,22),
	(779,'',103,21),
	(780,'',103,20),
	(781,'',103,18),
	(782,'SALSA PICANTE VALENTINA',103,17),
	(783,'',103,16),
	(784,'',103,14),
	(785,'',103,13),
	(786,'',103,7),
	(787,'',103,4),
	(788,'',103,3),
	(789,'',103,1),
	(790,'',104,22),
	(791,'',104,21),
	(792,'',104,20),
	(793,'',104,18),
	(794,'TOSTADA AMARILLA CHARRAS',104,17),
	(795,'',104,16),
	(796,'',104,14),
	(797,'',104,13),
	(798,'',104,7),
	(799,'',104,4),
	(800,'',104,3),
	(801,'',104,1),
	(802,'123456789',105,22),
	(803,'',105,21),
	(804,'',105,20),
	(805,'',105,18),
	(806,'',105,17),
	(807,'',105,16),
	(808,'',105,14),
	(809,'',105,13),
	(810,'',105,7),
	(811,'',105,4),
	(812,'',105,3),
	(813,'',105,1),
	(814,'',106,22),
	(815,'',106,21),
	(816,'',106,20),
	(817,'',106,18),
	(818,'',106,17),
	(819,'',106,16),
	(820,'',106,14),
	(821,'',106,13),
	(822,'',106,7),
	(823,'',106,4),
	(824,'7501006586640 SALSA VEGE',106,3),
	(825,'',106,1);

/*!40000 ALTER TABLE `productos_tiendas` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla proveedor
# ------------------------------------------------------------

DROP TABLE IF EXISTS `proveedor`;

CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `id_marca` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_proveedor_marca_idx` (`id_marca`),
  CONSTRAINT `fk_proveedor_marca` FOREIGN KEY (`id_marca`) REFERENCES `catalogo_marca` (`id_catalogo_marca`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;

INSERT INTO `proveedor` (`id`, `nombre`, `email`, `password`, `id_marca`, `active`)
VALUES
	(1,NULL,'kissthbw@gmail.com','kissthbw',7,1),
	(2,NULL,'kissthbw@hotmail.com','12345678',15,1),
	(3,NULL,'lab92mx@gmail.com','123456789',14,1),
	(4,NULL,'8ures92@gmail.com','12345678',46,1),
	(5,NULL,'lau.81002@gmail.com','12345678',46,1),
	(6,NULL,'delriocidj@gmail.com','12345678',46,1),
	(7,NULL,'roberto.guadarrama@ymail.com','12345678',46,1);

/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla proveedor_authority
# ------------------------------------------------------------

DROP TABLE IF EXISTS `proveedor_authority`;

CREATE TABLE `proveedor_authority` (
  `authority_id` int(11) DEFAULT NULL,
  `proveedor_id` int(11) DEFAULT NULL,
  KEY `fk_authority_p_idx` (`authority_id`),
  KEY `fk_proveedor_idx` (`proveedor_id`),
  CONSTRAINT `fk_authority_p` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_proveedor` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `proveedor_authority` WRITE;
/*!40000 ALTER TABLE `proveedor_authority` DISABLE KEYS */;

INSERT INTO `proveedor_authority` (`authority_id`, `proveedor_id`)
VALUES
	(1,3),
	(1,1),
	(1,2),
	(1,5),
	(1,6),
	(1,7);

/*!40000 ALTER TABLE `proveedor_authority` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla sugerencia_producto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sugerencia_producto`;

CREATE TABLE `sugerencia_producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(200) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario_idx` (`id_usuario`),
  CONSTRAINT `id_usuario_sugerencia` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `sugerencia_producto` WRITE;
/*!40000 ALTER TABLE `sugerencia_producto` DISABLE KEYS */;

INSERT INTO `sugerencia_producto` (`id`, `nombre_producto`, `id_usuario`)
VALUES
	(1,'arroz',73),
	(2,'bacardi',88),
	(3,'CHILES RELLENOS',89),
	(4,'videojuegos',2),
	(5,'Videojuegos',2),
	(6,'videojuegos ',2),
	(7,'Videojuegos ',2),
	(8,'Gadgets',2),
	(9,'Juegos',2),
	(10,'Juegos',2),
	(11,'PCs',2),
	(12,'Dulces',2),
	(13,'Magazine ',2),
	(14,'Victoria ',97),
	(15,'Botanas',2),
	(16,'Computadoras',2),
	(17,NULL,NULL),
	(18,NULL,NULL),
	(19,NULL,NULL),
	(20,NULL,NULL),
	(21,NULL,NULL),
	(22,'hola !',42),
	(23,'Electrónicos ',117),
	(24,'hola',42),
	(25,'CupShot',119),
	(26,'mostaza mckormick',88);

/*!40000 ALTER TABLE `sugerencia_producto` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla ticket
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `id_ticket` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_tienda` varchar(45) NOT NULL,
  `sucursal` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time DEFAULT NULL,
  `subtotal` double NOT NULL,
  `iva` double NOT NULL,
  `total` double NOT NULL,
  `ticket_tienda` varchar(45) DEFAULT NULL,
  `ticket_subtienda` varchar(45) DEFAULT NULL,
  `ticket_transaccion` varchar(45) DEFAULT NULL,
  `ticket_fecha` varchar(10) DEFAULT NULL,
  `ticket_hora` varchar(16) DEFAULT NULL,
  `ticket_code_bar` varchar(45) DEFAULT NULL,
  `ticket_cp_tienda` varchar(5) DEFAULT NULL,
  `ticket_cp_fiscal` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id_ticket`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;

INSERT INTO `ticket` (`id_ticket`, `nombre_tienda`, `sucursal`, `fecha`, `hora`, `subtotal`, `iva`, `total`, `ticket_tienda`, `ticket_subtienda`, `ticket_transaccion`, `ticket_fecha`, `ticket_hora`, `ticket_code_bar`, `ticket_cp_tienda`, `ticket_cp_fiscal`)
VALUES
	(44,'Grupo Walmart','Plaza Jardin','2019-07-04','13:18:09',91.56,17.44,109,'WALMART','','TDA#2670OP#00000214TE#003TR#08103','20/07/19','11:04',NULL,NULL,NULL),
	(45,'Grupo Walmart','Plaza Jardin','2019-12-04','13:18:09',91.56,17.44,109,'WALMART','','TDA#2670OP#00000214TE#003TR#08104','20/07/19','11:04',NULL,NULL,NULL),
	(46,'Grupo Walmart','Plaza Jardin','2019-12-05','13:18:09',91.56,17.44,109,'WALMART','','TDA#2670OP#00000214TE#003TR#08105','20/07/19','11:04',NULL,NULL,NULL),
	(47,'Grupo Walmart','Plaza Jardin','2019-05-21','19:18:01',91.56,17.44,109,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(48,'Grupo Walmart','Plaza Jardin','2020-01-20','13:18:09',91.56,17.44,109,'WALMART','','TDA#2670OP#00000214TE#003TR#08106','20/01/20','11:04',NULL,NULL,NULL),
	(49,'Oxxo','Plaza Jardin','2020-01-20','13:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#003TR#08107','20/01/20','11:10',NULL,NULL,NULL),
	(50,'Oxxo','Plaza Jardin','2019-12-20','13:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#003TR#08007','19/12/20','11:10',NULL,NULL,NULL),
	(51,'Grupo Walmart','Plaza Jardin','2019-05-21','22:37:07',91.56,17.44,109,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
	(52,'WALMART','','2020-03-11',NULL,2,0,2,'WALMART',NULL,'TDA#2229OP#00000027TE#002TR#07528','','',NULL,NULL,NULL),
	(53,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00001','28/04/20','15:34',NULL,NULL,NULL),
	(54,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00002','28/04/20','15:34',NULL,NULL,NULL),
	(55,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00003','28/04/20','15:34',NULL,NULL,NULL),
	(56,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00004','28/04/20','15:34',NULL,NULL,NULL),
	(57,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00005','28/04/20','15:34',NULL,NULL,NULL),
	(58,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00006','28/04/20','15:34',NULL,NULL,NULL),
	(59,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#0000','28/04/20','15:34',NULL,NULL,NULL),
	(60,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00007','28/04/20','15:34',NULL,NULL,NULL),
	(61,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00008','28/04/20','15:34',NULL,NULL,NULL),
	(62,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00009','28/04/20','15:34',NULL,NULL,NULL),
	(63,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00010','28/04/20','15:34',NULL,NULL,NULL),
	(64,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00011','28/04/20','15:34',NULL,NULL,NULL),
	(65,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00012','28/04/20','15:34',NULL,NULL,NULL),
	(66,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00013','28/04/20','15:34',NULL,NULL,NULL),
	(67,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00014','28/04/20','15:34',NULL,NULL,NULL),
	(68,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00015','28/04/20','15:34',NULL,NULL,NULL),
	(69,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00016','28/04/20','15:34',NULL,NULL,NULL),
	(70,'Oxxo','Plaza Jardin','2020-04-20','15:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00017','28/04/20','15:34',NULL,NULL,NULL),
	(71,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00018','28/04/20','15:49',NULL,NULL,NULL),
	(72,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00019','28/04/20','15:49',NULL,NULL,NULL),
	(73,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00020','28/04/20','15:49',NULL,NULL,NULL),
	(74,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00021','28/04/20','15:49',NULL,NULL,NULL),
	(75,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00022','28/04/20','15:49',NULL,NULL,NULL),
	(76,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00023','28/04/20','15:49',NULL,NULL,NULL),
	(77,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00024','28/04/20','15:49',NULL,NULL,NULL),
	(78,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00025','28/04/20','15:49',NULL,NULL,NULL),
	(79,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00026','28/04/20','15:49',NULL,NULL,NULL),
	(80,'Oxxo','Plaza Jardin','2020-04-20','15:49:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00027','28/04/20','15:49',NULL,NULL,NULL),
	(81,'Oxxo','Plaza Jardin','2020-04-20','15:54:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00028','28/04/20','15:54',NULL,NULL,NULL),
	(82,'Oxxo','Plaza Jardin','2020-04-20','15:54:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00029','28/04/20','15:54',NULL,NULL,NULL),
	(83,'Oxxo','Plaza Jardin','2020-04-20','15:54:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00030','28/04/20','15:54',NULL,NULL,NULL),
	(84,'Oxxo','Plaza Jardin','2020-04-20','15:54:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00031','28/04/20','15:54',NULL,NULL,NULL),
	(85,'Oxxo','Plaza Jardin','2020-04-20','15:54:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#028TR#00032','28/04/20','15:54',NULL,NULL,NULL),
	(86,'WALMART','','2020-05-04',NULL,12,0,12,'WALMART',NULL,'TDA#3851OP#00000022TE#010TR#07562','','',NULL,NULL,NULL),
	(87,'WALMART','','2020-05-04',NULL,2692,0,2692,'WALMART',NULL,'TDA#3851OP#00000022TE#010TR#07561','','',NULL,NULL,NULL),
	(88,'WALMART','','2020-05-05',NULL,100,0,100,'WALMART',NULL,'TDA#3840OP#00000015TE#005TR#01407','','',NULL,NULL,NULL),
	(89,'WALMART','','2020-05-18',NULL,2,0,2,'WALMART',NULL,'TDA#2229OP#00000027TE#002TR#07628','','',NULL,NULL,NULL),
	(90,'SORIANA','','2020-05-18',NULL,11,0,11,'SORIANA',NULL,'547151546','13/05/2020','18:03:05',NULL,NULL,NULL),
	(91,'WALMART','','2020-05-19',NULL,2,0,2,'WALMART','','00000000000','','','12345678ABCD',NULL,NULL),
	(92,'WALMART','','2020-05-19',NULL,5,0,5,'WALMART',NULL,'TDA#2079OP#00000065TE#009TR#06086','','','6CTUC$Z3MX9D72KW',NULL,NULL);

/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla user_authority
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_authority`;

CREATE TABLE `user_authority` (
  `authority_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  KEY `fk_authority_idx` (`authority_id`),
  KEY `fk_user_idx` (`user_id`),
  CONSTRAINT `fk_authority` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;

INSERT INTO `user_authority` (`authority_id`, `user_id`)
VALUES
	(1,2),
	(2,2),
	(2,42),
	(1,42),
	(2,59),
	(1,59),
	(2,73),
	(2,83),
	(2,84),
	(2,86),
	(2,87),
	(2,88),
	(2,89),
	(2,98),
	(2,105),
	(2,106),
	(2,107),
	(2,109),
	(2,110),
	(2,111),
	(2,112),
	(2,117);

/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla usuario
# ------------------------------------------------------------

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `ap_paterno` varchar(45) DEFAULT NULL,
  `ap_materno` varchar(45) DEFAULT NULL,
  `fecha_nac` date NOT NULL,
  `foto_usuario` varchar(45) DEFAULT NULL,
  `tel_movil` varchar(14) DEFAULT NULL,
  `correo_electronico` varchar(45) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `calle` varchar(45) DEFAULT NULL,
  `num_ext` varchar(45) DEFAULT NULL,
  `num_int` varchar(45) DEFAULT NULL,
  `colonia` varchar(45) DEFAULT NULL,
  `codigo_postal` varchar(5) NOT NULL,
  `del_mun` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `estatus_activacion` tinyint(1) NOT NULL,
  `codigo_verificacion` varchar(4) NOT NULL,
  `id_catalogo_sexo` int(11) DEFAULT NULL,
  `id_catalogo_red_social` int(11) DEFAULT NULL,
  `estatus` int(11) DEFAULT '1',
  `img_url` varchar(100) DEFAULT NULL,
  `hash` varchar(64) DEFAULT NULL,
  `password_restore_link` varchar(200) DEFAULT NULL,
  `time_restore_link` datetime DEFAULT NULL,
  `activation_link` varchar(200) DEFAULT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `device_token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  UNIQUE KEY `correo_electronico_UNIQUE` (`correo_electronico`),
  UNIQUE KEY `tel_movil_UNIQUE` (`tel_movil`),
  KEY `fk_id_catalogo_sexo_idx` (`id_catalogo_sexo`),
  KEY `fk_id_catalogo_red_social_idx` (`id_catalogo_red_social`),
  CONSTRAINT `fk_id_catalogo_red_social` FOREIGN KEY (`id_catalogo_red_social`) REFERENCES `catalogo_redes_sociales` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_catalogo_sexo` FOREIGN KEY (`id_catalogo_sexo`) REFERENCES `catalogo_sexo` (`id_catalogo_sexo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;

INSERT INTO `usuario` (`id_usuario`, `nombre`, `ap_paterno`, `ap_materno`, `fecha_nac`, `foto_usuario`, `tel_movil`, `correo_electronico`, `usuario`, `contrasenia`, `calle`, `num_ext`, `num_int`, `colonia`, `codigo_postal`, `del_mun`, `estado`, `estatus_activacion`, `codigo_verificacion`, `id_catalogo_sexo`, `id_catalogo_red_social`, `estatus`, `img_url`, `hash`, `password_restore_link`, `time_restore_link`, `activation_link`, `fecha_registro`, `device_token`)
VALUES
	(2,'Juan Oso',NULL,NULL,'1983-09-11',NULL,'+5215534714616','kissthbw@gmail.com','kissthbw@gmail.com','kiss2101',NULL,NULL,NULL,NULL,'57300',NULL,NULL,1,'6941',1,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1588269864/shingshing/usuarios/2.jpg','0cec40c1ba68e43ddc243e6a1f53418580c3fb1edf637e4111c9acc17d0886de','1afc9e2948e53965bd348f6988f4562606d1a2bc524a2a48312ef82ce024e30d','2020-05-01 22:32:38',NULL,'2019-07-10 18:04:50','cuTUqRUOqBQ:APA91bGitoCjnWK11Va1FZG_2G8Bly-C9HoVI9cfOJxz74KgBZB8050NBomOEmAMIj-48OIpvPB7Zjd5L7gG5OTWPWrGIEb1N43FG5ACwicZPns7RGEXTYL95jpPgDFvEVAl0XcMITUI'),
	(42,'Erick Alvarezz',NULL,NULL,'1992-02-15',NULL,'+5215550679875','lab92mx@gmail.com','lab92mx@gmail.com','123456789',NULL,NULL,NULL,NULL,'57800',NULL,NULL,1,'7543',1,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1588520977/shingshing/usuarios/42.jpg','15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225','b53a8ed52d290d791d7df82821a1f42555cc08c48fbfba7f0138ef021e53fc9c','2020-05-04 22:02:46',NULL,'2020-01-10 19:04:50','eOH1GQT-Xz4:APA91bEDR7LMUaQp4bRTkxQYpkGm9fKf-H7vAGzlBWlOuyBn1b4eHm4UQY-ycXlcsB_tdmGSLXnDQkTqp--CRWdFdVoBa73tLQm64_0y59mNps9lA_Ou8Z0GUVRkmtWSFqE7NcOEwIwe'),
	(59,'Juan Osorio Alvarez',NULL,NULL,'1983-09-11',NULL,'+5215555555551','juan.osorio@gmail.com','juan.osorio@gmail.com','shingshing',NULL,NULL,NULL,NULL,'57300',NULL,NULL,0,'7650',1,NULL,1,NULL,'df755c8f8edb665735260649c15691f8ea668045f0048673545f9035debb95c9',NULL,NULL,NULL,'2020-01-10 19:04:50',NULL),
	(73,'Roberto',NULL,NULL,'1990-09-17',NULL,'+5215520777555','roberto.htamayo@gmail.com','roberto.htamayo@gmail.com','robe2019',NULL,NULL,NULL,NULL,'14030',NULL,NULL,1,'3958',1,NULL,1,NULL,'8308506504be11dd0dccc8e4c8ee0c14ecc73c67ea2df1b4cf9fc71144686265','1e086d4e01679504d27cda152dc9cf213a0287674fcb9afcb0f3a8ea0567dd4d','2020-03-24 02:51:11',NULL,'2020-01-10 19:04:50',NULL),
	(81,'Juan Osorio Alvarez',NULL,NULL,'1970-01-01',NULL,'+5215555555555','kissthbw@hotmail.com','kissthbw@hotmail.com','kissthbw@hotmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,1,NULL,NULL,NULL,NULL,NULL,'2020-01-10 19:04:50',NULL),
	(82,'Adrian','Osorio','Alvarez','1984-10-02','','+5215540150544','masterboy@gmail.com','masterboy84','qwerty12387','Pataguas','115','','La Perla','57820','Nezahualcoyotl','Estado de México',0,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2020-01-15 19:18:00',NULL),
	(83,'Juan Osorio Alvarez',NULL,NULL,'1983-09-11',NULL,'*5215534714616','juan.osorio.alvarez2@gmail.com','juan.osorio.alvarez2@gmail.com','shingshing',NULL,NULL,NULL,NULL,'57300',NULL,NULL,0,'8549',1,NULL,1,NULL,'df755c8f8edb665735260649c15691f8ea668045f0048673545f9035debb95c9',NULL,NULL,'9cf39fd9dc66a0a5416f2e323e1ad2a2a1dbdf689089c8c919aaae1f0d8a2bc7','2020-03-05 16:35:41',NULL),
	(84,'jeovanny ',NULL,NULL,'1989-10-06',NULL,'+5215543197733','jeovanny156@gmail.com','jeovanny156@gmail.com','laplace1527',NULL,NULL,NULL,NULL,'06450',NULL,NULL,0,'6588',1,NULL,1,NULL,'3aea8623b78388086490d17483dfb4be0c25930a0c466bb9102e93c61c5c83b0','d93acf0e908eab19c72815a63e9b2eba0e99f1ccde85ab2d038f0b700388efad','2020-04-02 18:59:36','33822a00be34a988d706d0b4c1b34060ea62e2d3e4e25215155f900b5f7e8b38','2020-03-05 16:35:41',NULL),
	(86,'***Laboratorio',NULL,NULL,'1992-03-04',NULL,'***15531813109','***hello@lab92.mx','***hello@lab92.mx','***12345678',NULL,NULL,NULL,NULL,'57800',NULL,NULL,1,'6605',2,NULL,1,NULL,'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,NULL,NULL,'2020-03-05 17:31:57',NULL),
	(87,'Valeria ',NULL,NULL,'1992-03-05',NULL,'+5215526772646','lau.81002@gmail.com','lau.81002@gmail.com','vanesita',NULL,NULL,NULL,NULL,'80100',NULL,NULL,1,'9278',1,NULL,1,NULL,'cc7668ae305ef83b4654363c147e4c0a677361e69893e9cbca44b1c6346c3588',NULL,NULL,NULL,'2020-03-05 17:34:10',NULL),
	(88,'Roberto ',NULL,NULL,'1981-01-25',NULL,'+5215521891769','roberto.guadarrama@tradenial.com','roberto.guadarrama@tradenial.com','prueba123',NULL,NULL,NULL,NULL,'10640',NULL,NULL,1,'6236',1,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1588730730/shingshing/usuarios/88.jpg','ff960cb55673958c594d0daaab1e368651c75c02f9687192a1811e7b180336a7',NULL,'2020-04-22 17:52:12',NULL,'2020-03-05 20:48:39','dX7yvdhX7dQ:APA91bGay13p97LDHEvbx872FOpImyR7QTNZwZlC3oTatfbO6bm-R4-XwirYGRQToWIreSkJhmeK1_oWcz4qKvtoMIV5SXKsB4FSNmW7nZ3SRP81khYfygCEergoSfIJ95xSASw6l_Ct'),
	(89,'Joshua ',NULL,NULL,'2002-02-01',NULL,'+5215563753841','joshuagraff02@gmail.com','joshuagraff02@gmail.com','vidarendida',NULL,NULL,NULL,NULL,'57300',NULL,NULL,1,'3759',1,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1584139495/shingshing/usuarios/89.jpg','dee2f76503aa5aa28cb8b59b066caf44f1981c74068844a5834cbdf763d7e61f',NULL,NULL,NULL,'2020-03-13 22:40:28',NULL),
	(90,'Bures Bures',NULL,NULL,'1970-01-01',NULL,NULL,'buures@hotmail.com','buures@hotmail.com','buures@hotmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,1,NULL,NULL,NULL,NULL,NULL,'2020-03-22 03:59:23','eOH1GQT-Xz4:APA91bEDR7LMUaQp4bRTkxQYpkGm9fKf-H7vAGzlBWlOuyBn1b4eHm4UQY-ycXlcsB_tdmGSLXnDQkTqp--CRWdFdVoBa73tLQm64_0y59mNps9lA_Ou8Z0GUVRkmtWSFqE7NcOEwIwe'),
	(91,'Erick Alvarez',NULL,NULL,'1970-01-01',NULL,NULL,'herickov@gmail.com','herickov@gmail.com','herickov@gmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,1,1,NULL,NULL,'88b085083654edc49d1f079482b29aa105e4278d669b1cd4b7494290491c3f4a','2020-04-01 03:06:00',NULL,'2020-03-22 04:00:14',NULL),
	(92,'Laura Aguirre',NULL,NULL,'1970-01-01',NULL,NULL,'lauhrk81092@gmail.com','lauhrk81092@gmail.com','lauhrk81092@gmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,2,NULL,NULL,NULL,NULL,NULL,'2020-03-22 18:25:50',NULL),
	(94,'null null',NULL,NULL,'1970-01-01',NULL,NULL,'sistemas@zubalav.com.mx','sistemas@zubalav.com.mx','sistemas@zubalav.com.mx',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,1,1,NULL,NULL,NULL,NULL,NULL,'2020-04-08 18:04:41',NULL),
	(95,'Lidia Hernández',NULL,NULL,'1970-01-01',NULL,NULL,'lidiaht60@gmail.com','lidiaht60@gmail.com','lidiaht60@gmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,1,1,NULL,NULL,NULL,NULL,NULL,'2020-04-08 18:19:33',NULL),
	(96,'Jeovanny Nava',NULL,NULL,'1970-01-01',NULL,NULL,'jeovanny_hskqaek_nava@tfbnw.net','jeovanny_hskqaek_nava@tfbnw.net','jeovanny_hskqaek_nava@tfbnw.net',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,1,NULL,NULL,NULL,NULL,NULL,'2020-04-09 00:29:41',NULL),
	(97,'Jeovanny Nava',NULL,NULL,'1970-01-01',NULL,NULL,'jeovanny156@hotmail.com','jeovanny156@hotmail.com','jeovanny156@hotmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,1,NULL,'d8f81d8baa05e2cf440797e3488d6d1ba47bed6cf11e973afcfadfcde11c8a15',NULL,NULL,NULL,'2020-04-09 04:08:25',NULL),
	(98,'German RIvera',NULL,NULL,'1977-05-28',NULL,'+5215528809746','germanriverahdez@gmail.com','germanriverahdez@gmail.com','Alheloska2007',NULL,NULL,NULL,NULL,'02160',NULL,NULL,0,'3873',1,NULL,1,NULL,'9cc0bec56e0468e289efe01f122758efed219a89c717e4aaab44e15ca88be536',NULL,NULL,'beeb8977120dbae35f3557c6de8352bef68d5d2fe7bcfb62714df2895506c112','2020-04-12 21:21:52',NULL),
	(99,'Shing User-X',NULL,NULL,'1970-01-01',NULL,NULL,'shing_hmbwbtx_user@tfbnw.net-X','shing_hmbwbtx_user@tfbnw.net-X','shing_hmbwbtx_user@tfbnw.net-X',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,1,NULL,NULL,NULL,NULL,NULL,'2020-04-13 23:14:49',NULL),
	(100,'Emily Montes',NULL,NULL,'1970-01-01',NULL,NULL,'emily_fqbgtag_montes@tfbnw.net','emily_fqbgtag_montes@tfbnw.net','emily_fqbgtag_montes@tfbnw.net',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,1,NULL,NULL,NULL,NULL,NULL,'2020-04-14 02:49:32',NULL),
	(101,'null null',NULL,NULL,'1970-01-01',NULL,NULL,'ariacnenh@gmail.com','ariacnenh@gmail.com','ariacnenh@gmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,1,1,NULL,NULL,NULL,NULL,NULL,'2020-04-14 04:53:30',NULL),
	(102,'Jaime Ivan Trujillo',NULL,NULL,'1970-01-01',NULL,NULL,'jaimeivan@gmail.com','jaimeivan@gmail.com','jaimeivan@gmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,1,1,NULL,NULL,NULL,NULL,NULL,'2020-04-14 22:42:45',NULL),
	(103,'ERICK URIEL ALVAREZ NAVARRO',NULL,NULL,'1970-01-01',NULL,NULL,'erickuriel.alvarez@bbva.com','erickuriel.alvarez@bbva.com','erickuriel.alvarez@bbva.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,1,1,NULL,NULL,NULL,NULL,NULL,'2020-04-15 01:28:58',NULL),
	(104,'nombre',NULL,NULL,'1970-01-01',NULL,NULL,'email','email','email',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,NULL,1,NULL,NULL,NULL,NULL,NULL,'2020-04-15 05:56:29',NULL),
	(105,'Jeovanny',NULL,NULL,'2020-04-01',NULL,'+5215543197734','jeovanny1567@gmail.com','jeovanny1567@gmail.com','12345678',NULL,NULL,NULL,NULL,'10010',NULL,NULL,0,'9311',1,NULL,1,NULL,'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,NULL,'9957248cbc73181472295b7713db44cea74d341b797ffbe953594fcf37f662de','2020-04-15 07:06:22',NULL),
	(106,'Lucero zamora',NULL,NULL,'1994-05-17',NULL,'+5215543197766','lucero@gmail.com','lucero@gmail.com','12345678',NULL,NULL,NULL,NULL,'10010',NULL,NULL,1,'1322',2,NULL,2,NULL,'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,NULL,'e35496874939b07ab7535df03934e603dc04291cd900f48ccb0bd9e268f9c562','2020-04-15 07:19:41',NULL),
	(107,'Edgar',NULL,NULL,'1994-11-06',NULL,'+5215567904537','edgaravilaescom@gmail.com','edgaravilaescom@gmail.com','avilaolvera',NULL,NULL,NULL,NULL,'52910',NULL,NULL,1,'9209',1,NULL,1,NULL,'6ef5100c2b09ff5f689da9e8d09c0215c233f5259a90008eaead214be77d58ae','1ad81a1872d94611271307a8482a14521f0ae440a46f28bc2dddf2521aa8ee79','2020-05-13 02:31:20',NULL,'2020-04-15 21:19:27',NULL),
	(108,'Shing User',NULL,NULL,'1970-01-01',NULL,NULL,'shing_hmbwbtx_user@tfbnw.net','shing_hmbwbtx_user@tfbnw.net','shing_hmbwbtx_user@tfbnw.net',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,1,NULL,NULL,NULL,NULL,NULL,'2020-04-16 01:52:16',NULL),
	(109,'nonbre ',NULL,NULL,'2020-04-07',NULL,'+5215549463464','nonbr@gmail.com','nonbr@gmail.com','12345678',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0658',2,NULL,2,NULL,'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,NULL,NULL,'2020-04-18 00:57:28',NULL),
	(110,'Nombre',NULL,NULL,'2020-04-02',NULL,'+5215512345678','nombre@gmail.com','nombre@gmail.com','12345678',NULL,NULL,NULL,NULL,'01010',NULL,NULL,1,'3381',2,NULL,1,NULL,'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,NULL,NULL,'2020-04-18 01:12:50',NULL),
	(111,'Martin Nava',NULL,NULL,'1963-02-25',NULL,'+5215541759253','martinnava@gmail.com','martinnava@gmail.com','12345678',NULL,NULL,NULL,NULL,'06450',NULL,NULL,1,'1256',1,NULL,1,NULL,'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,NULL,NULL,'2020-04-18 01:23:34',NULL),
	(112,'erick',NULL,NULL,'1992-02-15',NULL,'+5215531813109','hello@lab92.mx','hello@lab92.mx','12345678',NULL,NULL,NULL,NULL,'08100',NULL,NULL,1,'5161',1,NULL,1,NULL,'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,NULL,NULL,'2020-04-21 04:56:24',NULL),
	(117,'Paola Patricia ',NULL,NULL,'1982-10-10',NULL,'+5215548998389','beyota_paola@hotmail.com','beyota_paola@hotmail.com','10PAOLA10',NULL,NULL,NULL,NULL,'57300',NULL,NULL,1,'8667',1,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1588361864/shingshing/usuarios/117.jpg','c293f727c75868bc0dd3fc067b4f1d384710b6dc0cc18315c5c3a8162a2f9ccd','1c427835ca0f71fe90ebcc77eb0be4a2b32af44e49d5dd18ab646091de715b05','2020-05-01 22:43:13',NULL,'2020-05-01 15:51:10','cuTUqRUOqBQ:APA91bGitoCjnWK11Va1FZG_2G8Bly-C9HoVI9cfOJxz74KgBZB8050NBomOEmAMIj-48OIpvPB7Zjd5L7gG5OTWPWrGIEb1N43FG5ACwicZPns7RGEXTYL95jpPgDFvEVAl0XcMITUI'),
	(118,'Laura Aguirre',NULL,NULL,'1970-01-01',NULL,NULL,'lau.aguirre810@gmail.com','lau.aguirre810@gmail.com','lau.aguirre810@gmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,1,1,NULL,NULL,NULL,NULL,NULL,'2020-05-04 00:17:27',NULL),
	(119,'Jorge Del Rio C',NULL,NULL,'1970-01-01',NULL,NULL,'delriocidj@yahoo.com','delriocidj@yahoo.com','delriocidj@yahoo.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,0,'4069',3,2,1,NULL,NULL,NULL,NULL,NULL,'2020-05-05 01:14:13','fTl3gwZHLak:APA91bHK3WsrjOAUxN5zyJTrJarPB_hawJkaxcHomtsg8cP-6wIlIvUMAr7HfukxKo4MaV_t8GM6MIB-sQ6ARgtAFWVD64M9Qb6q7VMpfanwr3ZlLqemM_5m1FTg2pgT6zmjtpYFHmSW'),
	(120,'Martin Nava Rivero',NULL,NULL,'1970-01-01',NULL,NULL,'martinnava426@gmail.com','martinnava426@gmail.com','martinnava426@gmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,1,1,NULL,NULL,NULL,NULL,NULL,'2020-05-20 05:59:13',NULL);

/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
