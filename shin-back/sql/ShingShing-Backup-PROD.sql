# ************************************************************
# Sequel Pro SQL dump
# Versi�n 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: shinshin.cvvjdff1wc6u.us-east-2.rds.amazonaws.com (MySQL 5.7.22-log)
# Base de datos: ShinShin
# Tiempo de Generaci�n: 2020-02-25 01:52:52 +0000
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
	(13,'xxo','OXXO');

/*!40000 ALTER TABLE `catalogo_diccionario_tiendas` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_marca
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_marca`;

CREATE TABLE `catalogo_marca` (
  `id_catalogo_marca` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_marca` varchar(45) NOT NULL,
  `img_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_catalogo_marca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_marca` WRITE;
/*!40000 ALTER TABLE `catalogo_marca` DISABLE KEYS */;

INSERT INTO `catalogo_marca` (`id_catalogo_marca`, `nombre_marca`, `img_url`)
VALUES
	(1,'Bonafont',NULL),
	(2,'Coca Cola',NULL),
	(3,'Nestle',NULL),
	(4,'Herdez',NULL),
	(5,'Sony',NULL),
	(6,'Microsoft',NULL),
	(7,'Nintendo',NULL),
	(8,'Kellogs',NULL),
	(9,'AXE',NULL),
	(10,'HERDEZ',NULL),
	(11,'STARTER',NULL),
	(12,'Del Valle',NULL),
	(13,'Royal','http://res.cloudinary.com/shingshing/image/upload/v1570290421/shingshing/Royal.png'),
	(14,'Yakult',NULL),
	(15,'Petalo',NULL),
	(16,'Santa Clara ',''),
	(17,'Stefano',''),
	(18,'MegaChamoy',''),
	(19,'San Juan',''),
	(20,'Alpura',''),
	(21,'Del Fuerte',''),
	(22,'Danone',''),
	(23,'Ades',''),
	(24,'Colgate',''),
	(25,'Nestlé',''),
	(26,'Lala',''),
	(27,'Epura',''),
	(28,'Yakult',''),
	(29,'Chips Ahoy',''),
	(30,'Granvita',''),
	(31,'Splash',''),
	(32,'Santa Clara ',''),
	(33,'Kleenex Cottonelle',''),
	(34,'Soriana ',''),
	(35,'Quaker',''),
	(36,'Fabuloso',''),
	(37,'Axion',''),
	(38,'Inova',''),
	(39,'Tressemé',''),
	(40,'Milpa Real ',''),
	(41,'Tía Rosa',''),
	(42,'Quaker',''),
	(43,'Gamesa',''),
	(44,'Eco Rubber',''),
	(45,'Cheetos',''),
	(46,'Garnier Nutrisse',''),
	(47,'Hometrends',''),
	(48,'ACT II',''),
	(49,'Chips Ahoy',''),
	(50,'Marinela','');

/*!40000 ALTER TABLE `catalogo_marca` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla catalogo_medios_bonificacion
# ------------------------------------------------------------

DROP TABLE IF EXISTS `catalogo_medios_bonificacion`;

CREATE TABLE `catalogo_medios_bonificacion` (
  `id_catalogo_medio_bonificacion` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_medio_bonificacion` varchar(45) NOT NULL,
  PRIMARY KEY (`id_catalogo_medio_bonificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_medios_bonificacion` WRITE;
/*!40000 ALTER TABLE `catalogo_medios_bonificacion` DISABLE KEYS */;

INSERT INTO `catalogo_medios_bonificacion` (`id_catalogo_medio_bonificacion`, `nombre_medio_bonificacion`)
VALUES
	(1,'Bancaria'),
	(2,'PayPal'),
	(3,'Recarga Teléfonica');

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
  `imagen_tienda` varchar(45) NOT NULL,
  PRIMARY KEY (`id_catalogo_tienda`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_tienda` WRITE;
/*!40000 ALTER TABLE `catalogo_tienda` DISABLE KEYS */;

INSERT INTO `catalogo_tienda` (`id_catalogo_tienda`, `nombre_tienda`, `imagen_tienda`)
VALUES
	(1,'Oxxo',''),
	(2,'Neto',''),
	(3,'Walmart',''),
	(4,'Aurrera',''),
	(5,'Soriana',''),
	(6,'Chedraui',''),
	(7,'Superama',''),
	(8,'Mega',''),
	(9,'Marinela',''),
	(10,'Garnier Nutrisse','');

/*!40000 ALTER TABLE `catalogo_tienda` ENABLE KEYS */;
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
  `active` tinyint(4) DEFAULT '1' COMMENT '0 = a Dado de baja\n1 = a Activo',
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
	(5,'Belleza','http://res.cloudinary.com/shingshing/image/upload/v1570551753/shingshing/departamentos/Belleza.png',1),
	(6,'Gadgets','http://res.cloudinary.com/shingshing/image/upload/v1570551933/shingshing/departamentos/Gadgets.png',1),
	(7,'Ropa',NULL,1),
	(8,'Cosméticos','http://res.cloudinary.com/shingshing/image/upload/v1570552046/shingshing/departamentos/Cosm%C3%A9ticos.png',1),
	(9,'Deportes','http://res.cloudinary.com/shingshing/image/upload/v1570551972/shingshing/departamentos/Deportes.png',1),
	(10,'Mascotas',NULL,1),
	(11,'Autos','http://res.cloudinary.com/shingshing/image/upload/v1570550960/shingshing/departamentos/Autos.png',1),
	(12,'Bebidas','http://res.cloudinary.com/shingshing/image/upload/v1570552024/shingshing/departamentos/Bebidas.png',1),
	(13,'Blancos','http://res.cloudinary.com/shingshing/image/upload/v1581434676/shingshing/departamentos/Blancos.png',1),
	(14,'Cremeria','',1),
	(15,'Electronica','',1),
	(16,'Hogar','',1),
	(17,'Higiene','',1),
	(18,'Limpieza','',1);

/*!40000 ALTER TABLE `catalogo_tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla contacto
# ------------------------------------------------------------

DROP TABLE IF EXISTS `contacto`;

CREATE TABLE `contacto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topico` varchar(200) DEFAULT NULL,
  `detalle` varchar(200) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario_contacto_idx` (`id_usuario`),
  CONSTRAINT `id_usuario_contacto` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
	(44,'2020-01-23',NULL,0,131,87),
	(45,'2020-01-24',NULL,0,131,87),
	(46,'2020-01-24',NULL,0,131,87),
	(47,'2020-02-03',NULL,0,127,2),
	(48,'2020-02-03',NULL,0,127,2),
	(49,'2020-02-03',NULL,0,127,2),
	(50,'2020-02-03',NULL,0,127,2),
	(51,'2020-02-03',NULL,0,127,2),
	(52,'2020-02-03',NULL,0,127,2),
	(53,'2020-02-03',NULL,0,127,2);

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
	(127,'Personal','5534714616','Telcel',3,NULL,NULL,2,NULL,1,NULL),
	(128,'Esposa','5548998389','Telcel',3,NULL,NULL,2,NULL,2,NULL),
	(129,'Vale','4152 3135 6505 5048',NULL,1,NULL,NULL,87,3,1,'BBVA'),
	(130,'Vale ','Lau.81002@gmail.com',NULL,2,NULL,'1234567891234',87,NULL,1,NULL),
	(131,'Laurita ','5534694824','Telcel',3,NULL,NULL,87,NULL,1,NULL),
	(132,'Lau','111122223333444455',NULL,1,NULL,NULL,87,1,1,'HSBC'),
	(133,'hola','111122223333444455',NULL,1,NULL,NULL,87,1,1,'SCOTIA BANK'),
	(134,'Bure','1111 22223 333444 4',NULL,1,NULL,NULL,87,3,2,'BBVA'),
	(135,'hola es una prueba ','555555555558888888',NULL,1,NULL,NULL,42,1,1,'SANTANDER'),
	(136,'hola','5550679875','Unefon',3,NULL,NULL,42,NULL,1,NULL);

/*!40000 ALTER TABLE `medios_bonificacion` ENABLE KEYS */;
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
  `banner` tinyint(1) DEFAULT NULL,
  `color_banner` varchar(11) DEFAULT NULL,
  `id_catalogo_marca` int(11) NOT NULL,
  `id_catalogo_tipo_producto` int(11) NOT NULL,
  `id_catalogo_tienda` int(11) DEFAULT NULL,
  `img_url` varchar(200) DEFAULT NULL,
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

INSERT INTO `producto` (`id_producto`, `nombre_producto`, `precio`, `codigo_barras`, `presentacion`, `contenido`, `descripcion`, `aplica_promocion`, `vigencia_promocion`, `url_imagen_producto`, `cantidad_bonificacion`, `banner`, `color_banner`, `id_catalogo_marca`, `id_catalogo_tipo_producto`, `id_catalogo_tienda`, `img_url`)
VALUES
	(1,'Laptop',699,'978128713','','1 pieza','Dispositivo de streaming',1,NULL,'/home/img/chrome.jpg',90,0,'#000000',2,1,1,NULL),
	(2,'Agua Bonafont ',0,NULL,NULL,'1 L','Agua natural',0,NULL,NULL,1,0,'#000000',1,1,NULL,''),
	(3,'Agua Bonafont',12,'978128713','Botella','1.5 L','Agua natural',1,'2019-08-17','/home/img/chrome.jpg',1,0,NULL,1,1,1,NULL),
	(4,'Paq 2 Aguas Bonafont',20,'978128713','Botella','1.5 L','Agua natural',1,'2019-08-17','/home/img/chrome.jpg',5,1,'255,134,195',1,1,1,NULL),
	(5,'Aceite Quaker State',90,'098712321','Botella','960 ml.','Aceite para motor, dale vida tu motor',1,'2019-12-15','/home/img/img.test',10,1,'0,131,70',1,3,1,NULL),
	(6,'Cereal Ciniminis',90,'00988128713','Caja','900 grs','Cereal de trigo con canela',1,'2019-08-17','/home/img/chrome.jpg',10,1,'229,204,205',1,1,1,NULL),
	(7,'Cereal Kellogs',90,'00988128713','Caja','900 grs','Cereal clasico de hojuelas de maiz',1,'2019-08-17','/home/img/chrome.jpg',10,1,'0,153,76',8,1,1,NULL),
	(8,'BURRIT CLASIC 182GR',25,'00988128713','Caja','182 GR','Burrito de frijoles con queso en tortilla de ',1,'2019-08-17','/home/img/chrome.jpg',10,1,'0,153,76',8,1,1,NULL),
	(9,'Agua Bonafont',0,NULL,NULL,'1.2 lt','Agua Bonafont con sabor natural',0,NULL,NULL,10,1,'0,153,86',1,1,NULL,NULL),
	(10,'Desodorante en aerosol AXE',0,NULL,NULL,'125 ml','Desodorante en aerosol de 125 ml. con fragancia fresca que dura 24 horas',0,NULL,NULL,7,1,'14,111,198',9,8,NULL,NULL),
	(11,'Aceite 1-2-3 ',45,'75002343','Botella 1L','1L','Aceite vegetal comestible, que no contiene colesterol',1,NULL,'/aceite123',7,1,'249, 17, 6',10,1,3,NULL),
	(12,'Colgate triple acción',18,'7580','Tubo de 150Ml','150Ml','Pasta de dientes xtra frescura, que contiene anticaries con fluor',1,NULL,'/pasta',2,1,'130,231,140',10,1,3,NULL),
	(13,'CACAHUATES',19.5,'7501030459958','Bolsa de cacahuates','Bolsa 250 gr','Cacahuates japonés, sabor saladillo, crujiente ideal para calmar el hambre',1,NULL,'/cacahuates',5,0,'31,38,170',10,1,3,NULL),
	(14,'DESOD AP REXONA CLINICAL',66,'SN','Frasco  de aerosol','Frasco 250 Ml','Aerosol en aerosol con olor fresco por 24 hrs',0,NULL,'/rexona',6,1,'15,206,79',9,8,3,NULL),
	(15,'YOPLAIT PLAC',10.4,'7501040092565','Envase','125 ml','Yogurt para beber Yoplait de sabor natural',0,NULL,'/yoplait',2,1,'235,17,131',10,8,3,NULL),
	(16,'STEFANO AERO 7509546064697',0,NULL,NULL,'125 ml','Desodorante en spray para un olor fresco por 12 horas',0,NULL,NULL,3,0,'154,184,26',9,8,NULL,''),
	(17,'STEFANO AERO',35.1,'21019864553','Botella de aerosol','125 ml.','Desodorante en aerosol, con fragancia fresca que para tener un olor agradable por hasta 24 horas ',0,NULL,'/aero',5,0,'49,186,241',9,8,3,NULL),
	(18,'AU ARENA 2KG',13.9,'7501791664462','Bolsa 2Kg','Bolsa 2Kg','Arena para gato',0,NULL,'/comida',2,1,'170,106,51',10,1,3,NULL),
	(19,'Americano 5',0,NULL,NULL,'Balón de americano','Balón de futbol americano tamaño regular',0,NULL,NULL,30,1,'49,104,241',11,9,NULL,NULL),
	(20,'PELOTA',18.9,'7506271756965','PELOTA DE PLASTICO','PELOTA DE PLASTICO','PELOTA DE PLASTICO COLOR VERDE, TAMAÑO REGULAR',0,NULL,'/pelota',2,0,'49,241,86',11,9,3,NULL),
	(21,'JDV MANGO PET 500ML',15,'210345789','Envase de 500ML','Envase de 500ML','Jugo Del Valle sabor mango',0,NULL,'/jdv_mango',1,0,'241,138,49',12,1,3,NULL),
	(22,'DOVE STICK C',41,'2143657890','Desodorante en barra','50 Ml','Desodorante en barra',1,NULL,'/dove',4,1,'159,49,241',9,8,3,NULL),
	(23,'Nintendo Switch',6999,'978128713','','1 pieza','',1,'2019-10-27','/home/img/chrome.jpg',90,0,NULL,7,2,1,NULL),
	(24,'Nintendo Switch',6999,'978128713','','1 pieza','',1,'2019-10-29','/home/img/chrome.jpg',90,0,NULL,7,2,1,NULL),
	(25,'Nintendo Switch',6999,'978128713','','1 pieza','',1,'2019-10-29','/home/img/chrome.jpg',99,0,NULL,7,2,1,NULL),
	(26,'New Nintendo 3DS',5999,'210134987654','Caja con producto','1 pieza','Consola portátil de nintendo, con tecnologia 3D',1,NULL,'/new3ds',80,0,'241,138,49',7,2,3,NULL),
	(27,'SNES Mini',2999,'210198765409','1 Caja','Consola de videojuegos retro','Revive los mejores juegos del Super Nintendo',0,NULL,'/snes',75,1,'83,17,190',7,2,3,NULL),
	(28,'iPhone 8 (Refurbished)',0,NULL,NULL,'Dispositivo Movil Apple reacondicionado','Dispositivo Movil Apple reacondicionado',0,NULL,NULL,50,1,'67,127,192',6,2,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581442811/shingshing/productos/iPhone%208%20%28Refurbished%29.jpg'),
	(29,'BATIDOR ME',0,NULL,NULL,'1 Pieza','ARTICULO DE COCINA',0,NULL,NULL,5,0,'241,138,49',13,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1579822332/shingshing/productos/BATIDOR%20ME.jpg'),
	(30,'Yakult',0,NULL,NULL,'80 ml ','',0,NULL,NULL,1,0,'221,30,221',14,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581396341/shingshing/productos/Yakult.png'),
	(31,'Papel Higiénico Pétalo ',0,NULL,NULL,'4 Rollos','Papel higiénico con 234 hojas dobles C/U',0,NULL,NULL,0,1,'5,93,172',15,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581399965/shingshing/productos/7506425600397%20Petalo.png'),
	(32,'Papel Higiénico Pétalo',0,NULL,NULL,'4 Rollos','Papel higiénico con 234 hojas dobles C/U',0,NULL,NULL,2,0,'21,110,190',15,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581399965/shingshing/productos/7506425600397%20Petalo.png'),
	(33,'Leche Santa Clara',0,NULL,NULL,'200 ML','Leche Entera Ultrapasterurizada',0,NULL,NULL,2,1,'101,23,125',16,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581474253/shingshing/productos/Leche%20Santa%20Clara.jpg'),
	(34,'Shampoo Stefano',0,NULL,NULL,'532 ML ','Shampoo Stefano alpha control caída para caballero.',0,NULL,NULL,2,1,'55,47,40',17,17,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581569032/shingshing/productos/Shampoo%20Stefano.png'),
	(35,'Mega Chamoy',0,NULL,NULL,'1 kg','Mega Salsa de Chamoy ',0,NULL,NULL,1,1,'82,14,144',18,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581574863/shingshing/productos/Mega%20Chamoy.png'),
	(36,'Huevo San Juan',0,NULL,NULL,'12 Huevos','Huevo Blanco San Juan',0,NULL,NULL,1,1,'87,215,245',19,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581568618/shingshing/productos/Huevo%20San%20Juan.png'),
	(37,'Crema Alpura',0,NULL,NULL,'900 ml','Crema Alpura ácida regular',0,NULL,NULL,1,1,'39,9,219',20,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581574509/shingshing/productos/Crema%20Alpura.png'),
	(38,'Pure de Tomate',0,NULL,NULL,'1 kl','Pure de Tomate del Fuerte Natural',0,NULL,NULL,1,1,'198,22,22',21,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581575491/shingshing/productos/Pure%20de%20Tomate.png'),
	(39,'Yogurth Danone',0,NULL,NULL,'140 g','Yoghurt Danone Mix Sabor Fresa Con Arroz Inflado',0,NULL,NULL,1,1,'253,215,244',22,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581478078/shingshing/productos/Yogurth%20Danone.png'),
	(40,'Jugo Ades',0,NULL,NULL,'946 ml','Jugo de Soya Ades Sabor Manzana',0,NULL,NULL,0,0,'19,146,13',23,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581652291/shingshing/productos/Jugo%20Ades.png'),
	(41,'Crema Dental Colgate',0,NULL,NULL,'50 ml','Cema dental con Flúor',0,NULL,NULL,1,1,'102,149,243',24,5,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581479348/shingshing/productos/Crema%20Dental%20Colgate.png'),
	(42,'La Lechera',0,NULL,NULL,'387 g','Leche condensada Nestlé La Lechera',0,NULL,NULL,2,0,'227,162,106',25,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581479639/shingshing/productos/La%20Lechera.jpg'),
	(43,'Crema Lala',0,NULL,NULL,'450 ml','Crema Lala Entera Acida ',0,NULL,NULL,2,1,'223,135,139',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581656122/shingshing/productos/Crema%20Lala.png'),
	(44,'AGUA NATURAL EPURA',0,NULL,NULL,'5 lt','Agua Purificada Epura Natural',0,NULL,NULL,2,1,'121,152,226',27,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581656184/shingshing/productos/AGUA%20NATURAL%20EPURA.png'),
	(45,'Sofùl',0,NULL,NULL,'105 g','Los lácteos te ofrecen beneficios vitales para la nutrición y el desarrollo de tu cuerpo. Lleva de Walmart súper en línea el Sofúl Yakult, sabor natural y sin azúcar de 105 g; disfruta de grandes beneficios para tu salud.',0,NULL,NULL,1,1,'254,71,86',28,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581657998/shingshing/productos/Sof%C3%B9l.png'),
	(46,'Avena Granvita',0,NULL,NULL,'35 g','Avena Instantánea Integral Con Arándanos',0,NULL,NULL,1,1,'199,122,60',30,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581653910/shingshing/productos/Avena%20Granvita.png'),
	(47,'Bebida V8 Splash Kiwifresón',0,NULL,NULL,'200 ml','Bebida V8 Splash Kiwifresón De Zanahoria, Manzana, Kiwi Y Fresa, Con Vitamina A Y C, Sin Conservadores.',0,NULL,NULL,1,0,'225,122,5',31,1,NULL,''),
	(48,'Leche Santa Clara',0,NULL,NULL,'1 Litro','Leche Santa Clara Entera ',0,NULL,NULL,2,1,'217,186,206',32,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581483756/shingshing/productos/Leche%20Santa%20Clara.png'),
	(49,'Papel Higiénico Kleenex Cottonelle',0,NULL,NULL,'4 Rollos','Papel higiénico Cottonelle ULTRA ComfortCare \r\n',0,NULL,NULL,1,1,'142,98,162',33,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581657220/shingshing/productos/Papel%20Higi%C3%A9nico%20Kleenex%20Cottonelle.png'),
	(50,'Yoghurt Lala',0,NULL,NULL,'125 g','Yoghurt Lala Con Fresa',0,NULL,NULL,1,1,'241,138,49',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581484962/shingshing/productos/Yoghurt%20Lala.png'),
	(51,'Agua Bonafont',0,NULL,NULL,'1 Litro','Agua Natural Bonafont',0,NULL,NULL,1,1,'225,138,110',1,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581485284/shingshing/productos/Agua%20Bonafont.png'),
	(52,'Azúcar',0,NULL,NULL,'1 kg','Azúcar Estándar',0,NULL,NULL,1,1,'252,90,71',34,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581653988/shingshing/productos/Az%C3%BAcar.png'),
	(53,'Avena Quaker Instantanea',0,NULL,NULL,'296 Gr','Avena Quaker Instantanea Nuez, Pasas y Datiles \r\n',0,NULL,NULL,2,1,'217,183,154',35,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581657863/shingshing/productos/Avena%20Quaker%20Instantanea.png'),
	(54,'Crema Alpura Selecta',0,NULL,NULL,'450 Ml ','Crema Alpura Selecta Premium',0,NULL,NULL,2,0,'241,138,49',20,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581652460/shingshing/productos/Crema%20Alpura%20Selecta.png'),
	(55,'Leche Lala',0,NULL,NULL,'1 Litro','Leche Lala Light Ultrapasteurizada\r\n',0,NULL,NULL,1,1,'112,173,174',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581491642/shingshing/productos/Leche%20Lala.png'),
	(56,'Fabuloso Limpiador liquido multiusos',0,NULL,NULL,'1 Litro','Cuidar la limpieza en el hogar es importante para tener un ambiente sano, eliminar bacterias y evitar enfermedades, con la finalidad de tener la casa siempre presentable con Fabuloso Frescura Profunda podrás eliminar olores gracias a su formula avanzada antibacterial.',0,NULL,NULL,1,1,'242,2,135',36,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581656684/shingshing/productos/Fabuloso%20Limpiador%20liquido%20multiusos.png'),
	(57,'Axion',0,NULL,NULL,'750 ml','Lavatrastes líquido Axion aroma limón ',0,NULL,NULL,1,1,'242,25,5',37,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581651879/shingshing/productos/Axion.png'),
	(58,'Axion',0,NULL,NULL,'640  Ml','Lavatrastes líquido Axion Complete Antibacterial',0,NULL,NULL,1,1,'12,130,86',37,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581623858/shingshing/productos/Axion.png'),
	(59,'Ganchos Inova',0,NULL,NULL,'10 Ganchos','Set de 10 Ganchos Inova Abadimex Premium Azul',0,NULL,NULL,2,1,'26,144,217',38,16,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581622895/shingshing/productos/Ganchos%20Inova.png'),
	(60,'Shampoo Tressemé',0,NULL,NULL,'100 ml','Shampoo Tressemé Smooth and Silky',0,NULL,NULL,2,1,'8,100,185',39,17,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581577018/shingshing/productos/Shampoo%20Tressem%C3%A9.png'),
	(61,'Tostadas',0,NULL,NULL,'360 g','Tostadas Milpa Real onduladas',0,NULL,NULL,1,1,'255,217,49',40,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581575590/shingshing/productos/Tostadas.png'),
	(62,'Tortillas de Harina',0,NULL,NULL,'12 Piezas','Tortillinas Tía Rosa de Harina ',0,NULL,NULL,1,1,'222,80,78',41,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581575645/shingshing/productos/Tortillas%20de%20Harina.png'),
	(63,'Avena instantanea',0,NULL,NULL,'1412 g','Cereal Quaker Instant de avena variedad de sabores.',0,NULL,NULL,1,1,'242,159,5',42,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581622746/shingshing/productos/Avena%20instantanea.png'),
	(64,'Avena Quaker',0,NULL,NULL,'475 g','Avena Quaker de hojuela natural',0,NULL,NULL,1,0,'191,42,55',35,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581576019/shingshing/productos/Avena%20Quaker.png'),
	(65,'Agua Levite Bonafont',0,NULL,NULL,'1 Litro','Levite Agua clásica sabor Piña-coco',0,NULL,NULL,2,1,'217,176,54',1,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581622654/shingshing/productos/Agua%20Levite%20Bonafont.png'),
	(66,'Galletas Doradas Marias Gamesa',0,NULL,NULL,'278 g','Galletas Marías Gamesa® Doradas, son ideales para un momento en que quieres consentirte o pasar un tiempo con familia o amigos y acompañar con la bebida favorita de cualquiera: café, leche, té, chocolate, etc. Sin duda te encantará su delicioso sabor a leche y su textura hojaldrada. ',0,NULL,NULL,2,1,'217,178,68',43,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581633458/shingshing/productos/Galletas%20Doradas%20Marias%20Gamesa.png'),
	(67,'Tapete de Entrada Eco Rubber',0,NULL,NULL,'1 Pieza','Tapete de Entrada clásico en color negro, ideal para recibir a tus visitas y evitar que se ensucie en exceso tu hogar.',0,NULL,NULL,2,1,'166,166,166',44,16,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581654678/shingshing/productos/Tapete%20de%20Entrada%20Eco%20Rubber.png'),
	(68,'Cheetos Bolitas',0,NULL,NULL,'100 g','Botana Cheetos® Bolitas Es Una Deliciosa Opción Hecha De Cereal De Maíz Con El Sabor A Queso Y Chile Que Tanto Te Gusta. Para Esos Momentos Familiares Y Divertidos, Botana Cheetos® Bolitas Es La Perfecta Elección.',0,NULL,NULL,2,1,'217,105,7',45,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581645390/shingshing/productos/Cheetos%20Bolitas.png'),
	(69,'Tinte para cabello Garnier Nutrisse',0,NULL,NULL,'180 g ','Cabello nutrido, mejor color. Con todo el poder de 3 aceites (aguacate, uva y mineral), nutre tu cabello para un mejor color con Garnier Nutrisse',0,NULL,NULL,2,1,'211,217,48',46,5,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581645971/shingshing/productos/Tinte%20para%20cabello%20Garnier%20Nutrisse.png'),
	(70,'Agua natural Bonafont',0,NULL,NULL,'2 Litros','El agua es un líquido vital que no debe faltar en tu alimentación, ya que además de ayudarte a mantener una vida sana, no contiene sodio. Mantente bien hidratado con el Agua Bonafont de 2 litros.',0,NULL,NULL,2,1,'242,104,73',1,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581647802/shingshing/productos/Agua%20natural%20Bonafont.png'),
	(71,'Portaretrato Hometrends',0,NULL,NULL,'1 Pieza','Coloca en tu hogar u oficina el portarretratos en color negro de la marca Hometrends ideal para 3 fotos, donde podrás mostrar a tus familiares y amigos esos momentos especiales.',0,NULL,NULL,2,1,'241,138,49',47,16,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581653133/shingshing/productos/Portaretrato%20Hometrends.png'),
	(72,'Palomitas para microondas ACT II',0,NULL,NULL,'80 g','Las palomitas son una buena opción cuando se trata de snacks saludables pues está elaborado con ingredientes naturales que contribuyen a una dieta balanceada sin olvidar su rico toque de mantequilla extra que las hará una excelente botana para fiestas o reuniones.',0,NULL,NULL,2,1,'95,148,235',48,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581647337/shingshing/productos/Palomitas%20para%20microondas%20ACT%20II.png'),
	(73,'ChipsAhoy',0,NULL,NULL,'460 g ','Galletas con chispas de Choolote,  Chips Ahoy en su presentación individual, son  indispesables en tu alacena para el deleite de tus pequeños.',0,NULL,NULL,2,1,'242,228,29',49,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581654822/shingshing/productos/ChipsAhoy.png'),
	(74,'Choco Roles',0,NULL,NULL,'67 g','Los chocoroles Marinela son unos deliciosos pastelitos enrollados con relleno cremoso sabor piña y cobertura sabor chocolate, adicionado con vitaminas y minerales. Es un clásico de Marinela que puedes disfrutar en cualquier momento de antojo.',0,NULL,NULL,2,1,'217,91,24',50,1,NULL,'');

/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla producto_favorito
# ------------------------------------------------------------

DROP TABLE IF EXISTS `producto_favorito`;

CREATE TABLE `producto_favorito` (
  `id_usuario` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  KEY `id_usuario_idx` (`id_usuario`),
  KEY `id_producto_idx` (`id_producto`),
  CONSTRAINT `id_producto` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
	(4,'BATIDOR ME',29,3),
	(5,'BATIDOR ME',29,2),
	(6,'BATIDOR ME',29,1),
	(7,'',28,3),
	(8,'',28,2),
	(9,'',28,1),
	(10,'7501025511005 YAKULT BEB',30,3),
	(11,'',30,2),
	(12,'',30,1),
	(13,'',19,3),
	(14,'',19,2),
	(15,'',19,1),
	(16,'',9,3),
	(17,'',9,2),
	(18,'',9,1),
	(19,'7506425600397 Petalo ',31,3),
	(20,'',31,2),
	(21,'',31,1),
	(22,'7506425600397 Petalo ',32,3),
	(23,'',32,2),
	(24,'',32,1),
	(52,'',33,8),
	(53,'',33,7),
	(54,'',33,6),
	(55,'',33,5),
	(56,'',33,4),
	(57,'7501295600089 SCLARAENTERA',33,3),
	(58,'',33,2),
	(59,'',33,1),
	(60,'',34,8),
	(61,'',34,7),
	(62,'',34,6),
	(63,'',34,5),
	(64,'',34,4),
	(65,'7509546077956 STEFANO SH ',34,3),
	(66,'',34,2),
	(67,'',34,1),
	(68,'',35,8),
	(69,'',35,7),
	(70,'',35,6),
	(71,'',35,5),
	(72,'',35,4),
	(73,'738545020626 MEGA CHAMOY',35,3),
	(74,'',35,2),
	(75,'',35,1),
	(76,'',2,8),
	(77,'',2,7),
	(78,'',2,6),
	(79,'',2,5),
	(80,'',2,4),
	(81,'',2,3),
	(82,'',2,2),
	(83,'',2,1),
	(84,'',36,8),
	(85,'',36,7),
	(86,'',36,6),
	(87,'',36,5),
	(88,'',36,4),
	(89,'7503000555011 SJUAN HVO',36,3),
	(90,'',36,2),
	(91,'',36,1),
	(92,'',37,8),
	(93,'',37,7),
	(94,'',37,6),
	(95,'',37,5),
	(96,'',37,4),
	(97,'7501055903924 ALPURA CRE',37,3),
	(98,'',37,2),
	(99,'',37,1),
	(100,'',38,8),
	(101,'',38,7),
	(102,'',38,6),
	(103,'',38,5),
	(104,'',38,4),
	(105,'7501079700011 DFUERTE PU',38,3),
	(106,'',38,2),
	(107,'',38,1),
	(108,'',39,8),
	(109,'',39,7),
	(110,'',39,6),
	(111,'',39,5),
	(112,'',39,4),
	(113,'7501032395230 DANONE140G',39,3),
	(114,'',39,2),
	(115,'',39,1),
	(116,'',40,8),
	(117,'',40,7),
	(118,'',40,6),
	(119,'',40,5),
	(120,'',40,4),
	(121,'7501005102667 ADES JUGO',40,3),
	(122,'',40,2),
	(123,'',40,1),
	(124,'',41,8),
	(125,'',41,7),
	(126,'',41,6),
	(127,'',41,5),
	(128,'',41,4),
	(129,'632971598608 COLGATE',41,3),
	(130,'',41,2),
	(131,'',41,1),
	(132,'',42,8),
	(133,'',42,7),
	(134,'',42,6),
	(135,'',42,5),
	(136,'',42,4),
	(137,'7501058651662 LECHERA SF',42,3),
	(138,'',42,2),
	(139,'',42,1),
	(140,'',43,8),
	(141,'',43,7),
	(142,'',43,6),
	(143,'',43,5),
	(144,'',43,4),
	(145,'7501020511451 LALA CREMA',43,3),
	(146,'',43,2),
	(147,'',43,1),
	(148,'',44,8),
	(149,'',44,7),
	(150,'',44,6),
	(151,'',44,5),
	(152,'',44,4),
	(153,'AGUA NATURAL EPURA 5 LT',44,3),
	(154,'',44,2),
	(155,'',44,1),
	(156,'',45,8),
	(157,'',45,7),
	(158,'',45,6),
	(159,'ALIME LAC SOFUL',45,5),
	(160,'',45,4),
	(161,'',45,3),
	(162,'',45,2),
	(163,'',45,1),
	(164,'',46,8),
	(165,'',46,7),
	(166,'',46,6),
	(167,'AVENA GRANVITA 35GR',46,5),
	(168,'',46,4),
	(169,'',46,3),
	(170,'',46,2),
	(171,'',46,1),
	(172,'',47,8),
	(173,'',47,7),
	(174,'',47,6),
	(175,'JUGO V8 SPLASH KIWI FRES',47,5),
	(176,'',47,4),
	(177,'',47,3),
	(178,'',47,2),
	(179,'',47,1),
	(180,'',48,8),
	(181,'',48,7),
	(182,'',48,6),
	(183,'LECHE ULTRA SANTA CLAR 1',48,5),
	(184,'',48,4),
	(185,'',48,3),
	(186,'',48,2),
	(187,'',48,1),
	(188,'',49,8),
	(189,'',49,7),
	(190,'',49,6),
	(191,'PAPEL PU COTTONELLE 4ROL',49,5),
	(192,'',49,4),
	(193,'',49,3),
	(194,'',49,2),
	(195,'',49,1),
	(196,'',50,8),
	(197,'',50,7),
	(198,'',50,6),
	(199,'YOGHURT LALA 125GR',50,5),
	(200,'',50,4),
	(201,'',50,3),
	(202,'',50,2),
	(203,'',50,1),
	(204,'',51,8),
	(205,'',51,7),
	(206,'',51,6),
	(207,'AGUA NATURAL BONAFONT',51,5),
	(208,'',51,4),
	(209,'',51,3),
	(210,'',51,2),
	(211,'',51,1),
	(212,'',52,8),
	(213,'',52,7),
	(214,'',52,6),
	(215,'AZUCAR SORIANA 1KG',52,5),
	(216,'',52,4),
	(217,'',52,3),
	(218,'',52,2),
	(219,'',52,1),
	(220,'',53,8),
	(221,'',53,7),
	(222,'000 Avena Quaker Nuez',53,6),
	(223,'',53,5),
	(224,'',53,4),
	(225,'',53,3),
	(226,'',53,2),
	(227,'',53,1),
	(228,'7501055905034 ALPURA SEL ',54,8),
	(229,'',54,7),
	(230,'',54,6),
	(231,'',54,5),
	(232,'',54,4),
	(233,'',54,3),
	(234,'',54,2),
	(235,'',54,1),
	(236,'7501020547054 LALA LIGHT',55,8),
	(237,'',55,7),
	(238,'',55,6),
	(239,'',55,5),
	(240,'',55,4),
	(241,'',55,3),
	(242,'',55,2),
	(243,'',55,1),
	(244,'750035910041 FABULOSO 1',56,8),
	(245,'',56,7),
	(246,'',56,6),
	(247,'',56,5),
	(248,'',56,4),
	(249,'',56,3),
	(250,'',56,2),
	(251,'',56,1),
	(252,'7509546076218 AXION 64 OM',57,8),
	(253,'',57,7),
	(254,'',57,6),
	(255,'',57,5),
	(256,'',57,4),
	(257,'',57,3),
	(258,'',57,2),
	(259,'',57,1),
	(260,'7509546078397 AXION 640',58,8),
	(261,'',58,7),
	(262,'',58,6),
	(263,'',58,5),
	(264,'',58,4),
	(265,'',58,3),
	(266,'',58,2),
	(267,'',58,1),
	(268,'',59,8),
	(269,'',59,7),
	(270,'',59,6),
	(271,'',59,5),
	(272,'',59,4),
	(273,'7503025465074 GANCHO INF',59,3),
	(274,'',59,2),
	(275,'',59,1),
	(276,'',60,8),
	(277,'',60,7),
	(278,'',60,6),
	(279,'',60,5),
	(280,'',60,4),
	(281,'630509826452 TRS HAIR HU',60,3),
	(282,'',60,2),
	(283,'',60,1),
	(284,'',61,8),
	(285,'',61,7),
	(286,'',61,6),
	(287,'',61,5),
	(288,'',61,4),
	(289,'7501030457756 TOSTADA',61,3),
	(290,'',61,2),
	(291,'',61,1),
	(292,'',62,8),
	(293,'',62,7),
	(294,'',62,6),
	(295,'',62,5),
	(296,'',62,4),
	(297,'7501640111017 TORTILAS',62,3),
	(298,'',62,2),
	(299,'',62,1),
	(300,'',63,8),
	(301,'',63,7),
	(302,'',63,6),
	(303,'',63,5),
	(304,'',63,4),
	(305,'094331198758 AVENA INSTA',63,3),
	(306,'',63,2),
	(307,'',63,1),
	(308,'',64,8),
	(309,'',64,7),
	(310,'',64,6),
	(311,'',64,5),
	(312,'',64,4),
	(313,'094331238744 AVENA',64,3),
	(314,'',64,2),
	(315,'',64,1),
	(316,'',65,8),
	(317,'',65,7),
	(318,'',65,6),
	(319,'',65,5),
	(320,'',65,4),
	(321,'758104006359 LEVITE INF ',65,3),
	(322,'',65,2),
	(323,'',65,1),
	(324,'',16,8),
	(325,'',16,7),
	(326,'',16,6),
	(327,'',16,5),
	(328,'',16,4),
	(329,'',16,3),
	(330,'',16,2),
	(331,'',16,1),
	(332,'',66,8),
	(333,'',66,7),
	(334,'000 Galletas doradas',66,6),
	(335,'',66,5),
	(336,'',66,4),
	(337,'',66,3),
	(338,'',66,2),
	(339,'',66,1),
	(340,'',67,8),
	(341,'',67,7),
	(342,'',67,6),
	(343,'',67,5),
	(344,'',67,4),
	(345,'7501965044151 TAPETE BAN',67,3),
	(346,'',67,2),
	(347,'',67,1),
	(348,'',68,8),
	(349,'',68,7),
	(350,'',68,6),
	(351,'',68,5),
	(352,'',68,4),
	(353,'7501011167605 CHEETOS 10',68,3),
	(354,'',68,2),
	(355,'',68,1),
	(356,'',69,10),
	(357,'',69,9),
	(358,'',69,8),
	(359,'',69,7),
	(360,'',69,6),
	(361,'',69,5),
	(362,'',69,4),
	(363,'7509552913446 NUTRISSE  T',69,3),
	(364,'',69,2),
	(365,'',69,1),
	(366,'',70,10),
	(367,'',70,9),
	(368,'',70,8),
	(369,'',70,7),
	(370,'',70,6),
	(371,'',70,5),
	(372,'',70,4),
	(373,'7501059236745 AGUA NATUR',70,3),
	(374,'',70,2),
	(375,'',70,1),
	(376,'',71,10),
	(377,'',71,9),
	(378,'',71,8),
	(379,'',71,7),
	(380,'',71,6),
	(381,'',71,5),
	(382,'',71,4),
	(383,'888556136547 PORTARETRA',71,3),
	(384,'',71,2),
	(385,'',71,1),
	(386,'',72,10),
	(387,'',72,9),
	(388,'',72,8),
	(389,'',72,7),
	(390,'',72,6),
	(391,'',72,5),
	(392,'7501006559002 PALOMITAS',72,4),
	(393,'',72,3),
	(394,'',72,2),
	(395,'',72,1),
	(396,'',73,10),
	(397,'',73,9),
	(398,'',73,8),
	(399,'',73,7),
	(400,'',73,6),
	(401,'',73,5),
	(402,'7622300744724 CHIPSAHOY',73,4),
	(403,'',73,3),
	(404,'',73,2),
	(405,'',73,1),
	(406,'',74,10),
	(407,'',74,9),
	(408,'',74,8),
	(409,'',74,7),
	(410,'',74,6),
	(411,'',74,5),
	(412,'',74,4),
	(413,'75002275 CHOCORROLES',74,3),
	(414,'',74,2),
	(415,'',74,1);

/*!40000 ALTER TABLE `productos_tiendas` ENABLE KEYS */;
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
	(20,'lsd ',87),
	(21,'hola',87),
	(22,'mari',87),
	(23,'muchos',42),
	(24,'uuu',42),
	(25,'uuuu',42);

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
  PRIMARY KEY (`id_ticket`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



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
	(2,78),
	(2,87),
	(2,90),
	(2,142),
	(2,146),
	(2,153),
	(2,155),
	(2,164);

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
  `fecha_registro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
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

INSERT INTO `usuario` (`id_usuario`, `nombre`, `ap_paterno`, `ap_materno`, `fecha_nac`, `foto_usuario`, `tel_movil`, `correo_electronico`, `usuario`, `contrasenia`, `calle`, `num_ext`, `num_int`, `colonia`, `codigo_postal`, `del_mun`, `estado`, `estatus_activacion`, `codigo_verificacion`, `id_catalogo_sexo`, `id_catalogo_red_social`, `estatus`, `img_url`, `hash`, `password_restore_link`, `time_restore_link`, `activation_link`, `fecha_registro`)
VALUES
	(2,'Juan Oso',NULL,NULL,'1983-09-11',NULL,'+5215534714616','kissthbw@gmail.com','kissthbw@gmail.com','isaias53',NULL,NULL,NULL,NULL,'57300',NULL,NULL,1,'6241',1,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1573231784/shingshing/usuarios/2.jpg','12ba4e44b53b2434aeb6fa48f6f1e08ff845151a8077f595dc76018e89eb4a3b','55d45a800b2d6d8794af76e97e20db2e03af8a7ab78d28ed1be0974318d118c6','2019-11-27 19:25:47',NULL,'2020-02-03 19:17:06'),
	(42,'Erick Alvarez',NULL,NULL,'1992-02-15',NULL,'+5215550679875','lab92mx@gmail.com','lab92mx@gmail.com','12345678',NULL,NULL,NULL,NULL,'57800',NULL,NULL,1,'7543',1,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1573850413/shingshing/usuarios/42.jpg','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','a05767f5d724a631a7cb47531229a4ed51bfb22e537b9010e0bc1cbc63d3f041','2019-11-21 19:19:39',NULL,'2020-02-03 19:17:06'),
	(59,'Juan Osorio Alvarez',NULL,NULL,'1983-09-11',NULL,'+5215555555551','juan.osorio@gmail.com','juan.osorio@gmail.com','shingshing',NULL,NULL,NULL,NULL,'57300',NULL,NULL,0,'7650',1,NULL,1,NULL,'df755c8f8edb665735260649c15691f8ea668045f0048673545f9035debb95c9',NULL,NULL,NULL,'2020-02-03 19:17:06'),
	(73,'Roberto',NULL,NULL,'1990-09-17',NULL,'+5215520777555','roberto.htamayo@gmail.com','roberto.htamayo@gmail.com','robe2019',NULL,NULL,NULL,NULL,'14030',NULL,NULL,1,'3958',1,NULL,1,NULL,'8308506504be11dd0dccc8e4c8ee0c14ecc73c67ea2df1b4cf9fc71144686265',NULL,NULL,NULL,'2020-02-03 19:17:06'),
	(78,'Paola Patricia',NULL,NULL,'1982-10-10',NULL,'+5215548998388','beyota_paola@hotmail.com','beyota_paola@hotmail.com','10PAOLA10',NULL,NULL,NULL,NULL,'57300',NULL,NULL,1,'4714',2,NULL,1,NULL,'c293f727c75868bc0dd3fc067b4f1d384710b6dc0cc18315c5c3a8162a2f9ccd',NULL,NULL,'133effcb4bfe768975657285171833f9b714c2359779dd7881d10f0f7e9956d2','2020-02-03 19:17:06'),
	(81,'Juan Osorio Alvarez',NULL,NULL,'1970-01-01',NULL,NULL,'kissthbw@hotmail.com','kissthbw@hotmail.com','kissthbw@hotmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,1,NULL,NULL,NULL,NULL,NULL,'2020-02-03 19:17:06'),
	(87,'Valeria',NULL,NULL,'1995-08-03',NULL,'+5215526772646','lau.81002@gmail.com','lau.81002@gmail.com','12345678',NULL,NULL,NULL,NULL,'57000',NULL,NULL,1,'3693',2,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1581616587/shingshing/usuarios/87.jpg','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,'2020-02-12 18:03:02',NULL,'2020-02-03 19:17:06'),
	(90,'Lau',NULL,NULL,'2002-01-25',NULL,'+5215534694824','hello@lab92.mx','hello@lab92.mx','12345678',NULL,NULL,NULL,NULL,'11111',NULL,NULL,0,'5451',1,NULL,1,NULL,'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,NULL,'106acb667a506e83ef0aa706ad3884367c4b1d02aeefceccf0b6ccaded9fc6d0','2020-02-03 19:17:06'),
	(142,'Paola Pardo',NULL,NULL,'1982-10-10',NULL,'+5215548998387','beyota_paola@gmail.com','beyota_paola@gmail.com','10paola10',NULL,NULL,NULL,NULL,'57300',NULL,NULL,1,'5650',1,NULL,1,NULL,'a5a5baa18ea0852834b1a4f342f8bc996f211383c02027d19470eab05695c923',NULL,NULL,NULL,NULL),
	(146,'Paola Sanchez Pardo',NULL,NULL,'1982-10-10',NULL,'+52188888888','beyotapao@gmail.com','beyotapao@gmail.com','10paola10',NULL,NULL,NULL,NULL,'57300',NULL,NULL,0,'2813',1,NULL,1,NULL,'a5a5baa18ea0852834b1a4f342f8bc996f211383c02027d19470eab05695c923',NULL,NULL,'1ae037c01223be62827c03c1b3aea38c16e6799d8c5713cc5c5e4b0190293396',NULL),
	(153,'Uriel',NULL,NULL,'1992-02-15',NULL,'+5215555556666','herickov@gmail.com','herickov@gmail.com','123456',NULL,NULL,NULL,NULL,'57800',NULL,NULL,0,'7855',1,NULL,1,NULL,'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',NULL,'2020-02-23 03:06:21','587d59d4325eb7b9e86a6ce6327b37d08bd5bb1f5beef48287b34b22d58c208d',NULL),
	(155,'Roberto',NULL,NULL,'2018-12-02',NULL,'+5215549442851','aghquenefasto@gmail.com','aghquenefasto@gmail.com','Prueba123',NULL,NULL,NULL,NULL,'10640',NULL,NULL,1,'0089',1,NULL,1,NULL,'0d9d09e157f7c29a43c02e57f081915d6fe2f10da1710672086f8ad73bb95cb2',NULL,NULL,NULL,NULL),
	(164,'Lau',NULL,NULL,'2002-02-19',NULL,'+5215566948','lab92@gmail.com','lab92@gmail.com','12345678',NULL,NULL,NULL,NULL,'87848',NULL,NULL,0,'4914',1,NULL,1,NULL,'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f',NULL,NULL,'47ed195130213ebd6e8f446bebf37362938c52e7444843e50e45968541418e5c',NULL);

/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
