# ************************************************************
# Sequel Pro SQL dump
# Versi�n 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.23)
# Base de datos: shingshing
# Tiempo de Generaci�n: 2020-02-25 02:27:28 +0000
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
  `active` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id_catalogo_marca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_marca` WRITE;
/*!40000 ALTER TABLE `catalogo_marca` DISABLE KEYS */;

INSERT INTO `catalogo_marca` (`id_catalogo_marca`, `nombre_marca`, `img_url`, `active`)
VALUES
	(1,'Bonafont','',0),
	(2,'Coca Cola',NULL,1),
	(3,'Nestle',NULL,1),
	(4,'Herdez',NULL,1),
	(5,'Sony',NULL,1),
	(6,'Microsoft',NULL,1),
	(7,'Nintendo',NULL,1),
	(8,'Kellogs',NULL,1),
	(9,'AXE',NULL,1),
	(10,'HERDEZ','http://res.cloudinary.com/shingshing/image/upload/v1581459501/shingshing/marcas/HERDEZ.png',1),
	(11,'STARTER',NULL,1),
	(12,'Del Valle',NULL,1),
	(13,'Royal','http://res.cloudinary.com/shingshing/image/upload/v1570290421/shingshing/Royal.png',1),
	(14,'Yakult','',1),
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
	(29,'Chips Ahoy','',1),
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
	(42,'Quaker','',1);

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
	(1,'BANCARIA'),
	(2,'PAYPAL'),
	(3,'RECARGA TELEFÓNICA');

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
	(1,'Oxxo','',0),
	(2,'Neto','',1),
	(3,'Grupo Walmart','',1),
	(4,'7 Eleven','http://res.cloudinary.com/shingshing/image/upload/v1581461735/shingshing/tiendas/7%20Eleven.png',1),
	(5,'K-Mart','',1),
	(6,'Chedraui','',1),
	(7,'Superama','',1),
	(8,'Mega','',1);

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
  `active` tinyint(4) DEFAULT '1' COMMENT '0 = a Dado de baja\\n1 = a Activo',
  PRIMARY KEY (`id_catalogo_tipo_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `catalogo_tipo_producto` WRITE;
/*!40000 ALTER TABLE `catalogo_tipo_producto` DISABLE KEYS */;

INSERT INTO `catalogo_tipo_producto` (`id_catalogo_tipo_producto`, `nombre_tipo_producto`, `img_url`, `active`)
VALUES
	(1,'Abarrotes','http://res.cloudinary.com/shingshing/image/upload/v1570550786/shingshing/departamentos/Abarrotes.png',0),
	(2,'Electrónica','http://res.cloudinary.com/shingshing/image/upload/v1570550933/shingshing/departamentos/Electr%C3%B3nicos.png',0),
	(3,'Autos','http://res.cloudinary.com/shingshing/image/upload/v1570550960/shingshing/departamentos/Autos.png',1),
	(4,'Bebé','http://res.cloudinary.com/shingshing/image/upload/v1570550983/shingshing/departamentos/Beb%C3%A9.png',1),
	(5,'Belleza','http://res.cloudinary.com/shingshing/image/upload/v1570551753/shingshing/departamentos/Belleza.png',1),
	(6,'Gadgets','http://res.cloudinary.com/shingshing/image/upload/v1570551933/shingshing/departamentos/Gadgets.png',1),
	(7,'Ropa',NULL,1),
	(8,'Cosméticos','http://res.cloudinary.com/shingshing/image/upload/v1570552046/shingshing/departamentos/Cosm%C3%A9ticos.png',1),
	(9,'Deportes','http://res.cloudinary.com/shingshing/image/upload/v1570551972/shingshing/departamentos/Deportes.png',1),
	(10,'Mascotas','http://res.cloudinary.com/shingshing/image/upload/v1570551954/shingshing/departamentos/Mascotas.png',1),
	(11,'Autos','http://res.cloudinary.com/shingshing/image/upload/v1570550960/shingshing/departamentos/Autos.png',1),
	(12,'Bebidas','http://res.cloudinary.com/shingshing/image/upload/v1570552024/shingshing/departamentos/Bebidas.png',1),
	(13,'Blancos','http://res.cloudinary.com/shingshing/image/upload/v1581434430/shingshing/departamentos/Blancos.png',1),
	(14,'Cremeria','',1),
	(15,'Electrónica','',1),
	(16,'Hogar',NULL,1),
	(17,'Higiene',NULL,1),
	(18,'Limpieza','',0);

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
	(51,1);

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
	(6,'2020-02-03','12:38:05',20,127,2);

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
	(2,51);

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
	(130,'Hija','5534714616','ATT&T',3,NULL,NULL,2,NULL,2,NULL);

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
	(1,'Laptop',699,'978128713','','1 pieza','Dispositivo de streaming',1,NULL,'/home/img/chrome.jpg',90,0,'#000000',2,1,1,NULL,0),
	(2,'Agua Bonafont',10,'978128713','Botella','1 L','Agua natural',1,'2019-08-17','/home/img/chrome.jpg',1,0,NULL,1,1,1,NULL,0),
	(3,'Agua Bonafont',12,'978128713','Botella','1.5 L','Agua natural',1,'2019-08-17','/home/img/chrome.jpg',1,0,NULL,1,1,1,NULL,0),
	(4,'Paq 2 Aguas Bonafont',20,'978128713','Botella','1.5 L','Agua natural',1,'2019-08-17','/home/img/chrome.jpg',5,1,'255,134,195',1,1,1,NULL,0),
	(5,'Aceite Quaker State',90,'098712321','Botella','960 ml.','Aceite para motor, dale vida tu motor',1,'2019-12-15','/home/img/img.test',10,1,'0,131,70',1,3,1,NULL,1),
	(6,'Cereal Ciniminis',90,'00988128713','Caja','900 grs','Cereal de trigo con canela',1,'2019-08-17','/home/img/chrome.jpg',10,1,'229,204,205',1,1,1,NULL,0),
	(7,'Cereal Kellogs',90,'00988128713','Caja','900 grs','Cereal clasico de hojuelas de maiz',1,'2019-08-17','/home/img/chrome.jpg',10,1,'0,153,76',8,1,1,NULL,0),
	(8,'BURRIT CLASIC 182GR',25,'00988128713','Caja','182 GR','Burrito de frijoles con queso en tortilla de ',1,'2019-08-17','/home/img/chrome.jpg',10,1,'0,153,76',8,1,1,NULL,0),
	(9,'BONAFONT 1.2',12,'00988128713','Caja','1.2 L','Agua Bonafont con sabor natural',1,'2019-08-17','/home/img/chrome.jpg',10,1,'0,153,86',8,1,1,NULL,0),
	(10,'Desodorante en aerosol AXE',0,NULL,NULL,'125 ml','Desodorante en aerosol de 125 ml. con fragancia fresca que dura 24 horas',0,NULL,NULL,7,1,'14,111,198',9,8,NULL,NULL,1),
	(11,'Aceite 1-2-3 ',45,'75002343','Botella 1L','1L','Aceite vegetal comestible, que no contiene colesterol',1,NULL,'/aceite123',7,1,'249, 17, 6',10,1,3,NULL,0),
	(12,'Colgate triple acción',18,'7580','Tubo de 150Ml','150Ml','Pasta de dientes xtra frescura, que contiene anticaries con fluor',1,NULL,'/pasta',2,1,'130,231,140',10,1,3,NULL,0),
	(13,'CACAHUATES',19.5,'7501030459958','Bolsa de cacahuates','Bolsa 250 gr','Cacahuates japonés, sabor saladillo, crujiente ideal para calmar el hambre',1,NULL,'/cacahuates',5,0,'31,38,170',10,1,3,NULL,0),
	(14,'DESOD AP REXONA CLINICAL',66,'SN','Frasco  de aerosol','Frasco 250 Ml','Aerosol en aerosol con olor fresco por 24 hrs',0,NULL,'/rexona',6,1,'15,206,79',9,8,3,NULL,1),
	(15,'YOPLAIT PLAC',10.4,'7501040092565','Envase','125 ml','Yogurt para beber Yoplait de sabor natural',0,NULL,'/yoplait',2,1,'235,17,131',10,8,3,NULL,1),
	(16,'STEFANO AERO 7509546064697',35.1,'7509546064697','Frasco de aerosol','Envase de 125 ml','Desodorante en spray para un olor fresco por 12 horas',0,NULL,'/stefano',3,0,'154,184,26',9,8,3,NULL,1),
	(17,'STEFANO AERO',35.1,'21019864553','Botella de aerosol','125 ml.','Desodorante en aerosol, con fragancia fresca que para tener un olor agradable por hasta 24 horas ',0,NULL,'/aero',5,0,'49,186,241',9,8,3,NULL,1),
	(18,'AU ARENA 2KG',13.9,'7501791664462','Bolsa 2Kg','Bolsa 2Kg','Arena para gato',0,NULL,'/comida',2,1,'170,106,51',10,1,3,NULL,0),
	(19,'AMERICANO 5',129,'7502276170119','BALON DE AMERICANO','BALON DE AMERICANO','BALON DE AMERICANO DE TAMAÑO REGULAR',0,NULL,'/americano',30,1,'49,104,241',11,9,3,NULL,1),
	(20,'PELOTA',18.9,'7506271756965','PELOTA DE PLASTICO','PELOTA DE PLASTICO','PELOTA DE PLASTICO COLOR VERDE, TAMAÑO REGULAR',0,NULL,'/pelota',2,0,'49,241,86',11,9,3,NULL,1),
	(21,'JDV MANGO PET 500ML',15,'210345789','Envase de 500ML','Envase de 500ML','Jugo Del Valle sabor mango',0,NULL,'/jdv_mango',1,0,'241,138,49',12,1,3,NULL,0),
	(22,'DOVE STICK C',41,'2143657890','Desodorante en barra','50 Ml','Desodorante en barra',1,NULL,'/dove',4,1,'159,49,241',9,8,3,NULL,1),
	(23,'Nintendo Switch',6999,'978128713','','1 pieza','',1,'2019-10-27','/home/img/chrome.jpg',90,0,NULL,7,2,1,NULL,1),
	(24,'Nintendo Switch',6999,'978128713','','1 pieza','',1,'2019-10-29','/home/img/chrome.jpg',90,0,NULL,7,2,1,NULL,1),
	(25,'Nintendo Switch',6999,'978128713','','1 pieza','',1,'2019-10-29','/home/img/chrome.jpg',99,0,NULL,7,2,1,NULL,1),
	(26,'New Nintendo 3DS',5999,'210134987654','Caja con producto','1 pieza','Consola portátil de nintendo, con tecnologia 3D',1,NULL,'/new3ds',80,0,'241,138,49',7,2,3,NULL,1),
	(27,'SNES Mini',2999,'210198765409','1 Caja','Consola de videojuegos retro','Revive los mejores juegos del Super Nintendo',0,NULL,'/snes',75,1,'83,17,190',7,2,3,NULL,1),
	(28,'iPhone 7 (Refurbished)',0,NULL,NULL,'Dispositivo Movil Apple reacondicionado','Dispositivo Movil Apple reacondicionado.',0,NULL,NULL,50,1,'67,127,192',6,2,NULL,NULL,1),
	(29,'Nintendo Switch',0,NULL,NULL,'1 pieza','',0,NULL,NULL,100,0,'#000000',7,2,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581443207/shingshing/productos/Nintendo%20Switch.jpg',1),
	(30,'Rancheritos',0,NULL,NULL,'50 gr','Rancheritos',0,NULL,NULL,1,0,'241,138,49',15,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581467902/shingshing/productos/Rancheritos.jpg',0),
	(31,'Papel Higiénico Pétalo ',0,NULL,NULL,'4 Rollos','Papel higiénico con 234 hojas dobles C/U',0,NULL,NULL,0,1,'5,93,172',15,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581399965/shingshing/productos/7506425600397%20Petalo.png',0),
	(32,'Papel Higiénico Pétalo',0,NULL,NULL,'4 Rollos','Papel higiénico con 234 hojas dobles C/U',0,NULL,NULL,2,0,'21,110,190',15,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581399965/shingshing/productos/7506425600397%20Petalo.png',0),
	(33,'Leche Santa Clara',0,NULL,NULL,'200 ML','Leche Entera Ultrapasterurizada',0,NULL,NULL,2,1,'101,23,125',16,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581474253/shingshing/productos/Leche%20Santa%20Clara.jpg',1),
	(34,'Shampoo Stefano',0,NULL,NULL,'532 ML ','Shampoo Stefano alpha control caída para caballero.',0,NULL,NULL,2,1,'55,47,40',17,17,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581569032/shingshing/productos/Shampoo%20Stefano.png',1),
	(35,'Mega Chamoy',0,NULL,NULL,'1 kg','Mega Salsa de Chamoy ',0,NULL,NULL,1,1,'82,14,144',18,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581574863/shingshing/productos/Mega%20Chamoy.png',0),
	(36,'Huevo San Juan',0,NULL,NULL,'12 Huevos','Huevo Blanco San Juan',0,NULL,NULL,1,1,'87,215,245',19,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581568618/shingshing/productos/Huevo%20San%20Juan.png',0),
	(37,'Crema Alpura',0,NULL,NULL,'900 ml','Crema Alpura ácida regular',0,NULL,NULL,1,1,'39,9,219',20,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581574509/shingshing/productos/Crema%20Alpura.png',1),
	(38,'Pure de Tomate',0,NULL,NULL,'1 kl','Pure de Tomate del Fuerte Natural',0,NULL,NULL,1,1,'198,22,22',21,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581575491/shingshing/productos/Pure%20de%20Tomate.png',0),
	(39,'Yogurth Danone',0,NULL,NULL,'140 g','Yoghurt Danone Mix Sabor Fresa Con Arroz Inflado',0,NULL,NULL,1,1,'253,215,244',22,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581478078/shingshing/productos/Yogurth%20Danone.png',1),
	(40,'Jugo Ades',0,NULL,NULL,'946 ml','Jugo de Soya Ades Sabor Manzana',0,NULL,NULL,0,0,'19,146,13',23,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581479100/shingshing/productos/Jugo%20Ades.png',0),
	(41,'Crema Dental Colgate',0,NULL,NULL,'50 ml','Cema dental con Flúor',0,NULL,NULL,1,1,'102,149,243',24,5,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581479348/shingshing/productos/Crema%20Dental%20Colgate.png',1),
	(42,'La Lechera',0,NULL,NULL,'387 g','Leche condensada Nestlé La Lechera',0,NULL,NULL,2,0,'227,162,106',25,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581479639/shingshing/productos/La%20Lechera.jpg',0),
	(43,'Crema Lala',0,NULL,NULL,'450 ml','Crema Lala Entera Acida ',0,NULL,NULL,2,1,'223,135,139',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581480374/shingshing/productos/Crema%20Lala.png',1),
	(44,'AGUA NATURAL EPURA',0,NULL,NULL,'5 lt','Agua Purificada Epura Natural',0,NULL,NULL,2,1,'121,152,226',27,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581480781/shingshing/productos/AGUA%20NATURAL%20EPURA.jpg',0),
	(45,'Sofùl',0,NULL,NULL,'105 g','Sofúl Yakult natural sin azúcar',0,NULL,NULL,1,1,'254,71,86',28,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581481413/shingshing/productos/Sof%C3%B9l.png',1),
	(46,'Avena Granvita',0,NULL,NULL,'35 g','Avena Instantánea Integral Con Arándanos',0,NULL,NULL,1,1,'199,122,60',30,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581482488/shingshing/productos/Avena%20Granvita.jpg',0),
	(47,'Bebida V8 Splash Kiwifresón',0,NULL,NULL,'200 ml','Bebida V8 Splash Kiwifresón De Zanahoria, Manzana, Kiwi Y Fresa, Con Vitamina A Y C, Sin Conservadores.',0,NULL,NULL,1,0,'225,122,5',31,1,NULL,'',0),
	(48,'Leche Santa Clara',0,NULL,NULL,'1 Litro','Leche Santa Clara Entera ',0,NULL,NULL,2,1,'217,186,206',32,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581483756/shingshing/productos/Leche%20Santa%20Clara.png',1),
	(49,'Papel Higiénico Kleenex Cottonelle',0,NULL,NULL,'4 Rollos','Papel higiénico Cottonelle ULTRA ComfortCare \r\n',0,NULL,NULL,1,1,'142,98,162',33,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581484422/shingshing/productos/Papel%20Higi%C3%A9nico%20Kleenex%20Cottonelle.jpg',0),
	(50,'Yoghurt Lala',0,NULL,NULL,'125 g','Yoghurt Lala Con Fresa',0,NULL,NULL,1,1,'241,138,49',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581484962/shingshing/productos/Yoghurt%20Lala.png',1),
	(51,'Agua Bonafont',0,NULL,NULL,'1 Litro','Agua Natural Bonafont',0,NULL,NULL,1,1,'225,138,110',1,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581485284/shingshing/productos/Agua%20Bonafont.png',0),
	(52,'Azúcar',0,NULL,NULL,'1 kg','Azúcar Estándar',0,NULL,NULL,1,1,'252,90,71',34,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581531092/shingshing/productos/Az%C3%BAcar.png',0),
	(53,'Avena Quaker Instantanea',0,NULL,NULL,'296 Gr','Avena Quaker Instantanea Nuez, Pasas y Datiles \r\n',0,NULL,NULL,2,1,'217,183,154',35,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581487779/shingshing/productos/Avena%20Quaker%20Instantanea.png',0),
	(54,'Crema Alpura Selecta',0,NULL,NULL,'450 Ml ','Crema Alpura Selecta Premium',0,NULL,NULL,2,0,'241,138,49',20,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581490033/shingshing/productos/Crema%20Alpura%20Selecta.png',1),
	(55,'Leche Lala',0,NULL,NULL,'1 Litro','Leche Lala Light Ultrapasteurizada\r\n',0,NULL,NULL,1,1,'112,173,174',26,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581491642/shingshing/productos/Leche%20Lala.png',1),
	(56,'Fabuloso',0,NULL,NULL,'1 Litro','Fabuloso Limpiador liquido multiusos Frescura Activa Antibacterial',0,NULL,NULL,1,1,'204,167,55',36,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581526036/shingshing/productos/Fabuloso.png',1),
	(57,'Axion',0,NULL,NULL,'750 ml','Lavatrastes líquido Axion aroma limón ',0,NULL,NULL,1,1,'242,25,5',37,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581527114/shingshing/productos/Axion.jpg',1),
	(58,'Axion',0,NULL,NULL,'640  Ml','Lavatrastes líquido Axion Complete Antibacterial',0,NULL,NULL,1,1,'140,4,118',37,18,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581531023/shingshing/productos/Axion.jpg',1),
	(59,'Ganchos Inova',0,NULL,NULL,'10 Ganchos','Set de 10 Ganchos Inova Abadimex Premium Azul',0,NULL,NULL,2,1,'26,144,217',38,16,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581577269/shingshing/productos/Ganchos%20Inova.png',1),
	(60,'Shampoo Tressemé',0,NULL,NULL,'100 ml','Shampoo Tressemé Smooth and Silky',0,NULL,NULL,2,1,'8,100,185',39,17,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581577018/shingshing/productos/Shampoo%20Tressem%C3%A9.png',1),
	(61,'Tostadas',0,NULL,NULL,'360 g','Tostadas Milpa Real onduladas',0,NULL,NULL,1,1,'255,217,49',40,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581575590/shingshing/productos/Tostadas.png',1),
	(62,'Tortillas de Harina',0,NULL,NULL,'12 Piezas','Tortillinas Tía Rosa de Harina ',0,NULL,NULL,1,1,'222,80,78',41,14,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581575645/shingshing/productos/Tortillas%20de%20Harina.png',1),
	(63,'Avena instantanea',0,NULL,NULL,'1412 g','Cereal Quaker Instant de avena variedad de sabores.',0,NULL,NULL,1,1,'242,159,5',42,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581576397/shingshing/productos/Avena%20instantanea.png',0),
	(64,'Avena Quaker',0,NULL,NULL,'475 g','Avena Quaker de hojuela natural',0,NULL,NULL,1,0,'191,42,55',35,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581576019/shingshing/productos/Avena%20Quaker.png',0),
	(65,'Agua Levite Bonafont',0,NULL,NULL,'1 Litro','Levite Agua clásica sabor Piña-coco',0,NULL,NULL,2,1,'217,176,54',1,1,NULL,'http://res.cloudinary.com/shingshing/image/upload/v1581576847/shingshing/productos/Agua%20Levite%20Bonafont.png',0);

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
	(26,'7501025511005 YAKULT BEB',30,3),
	(27,'',30,2),
	(28,'',30,1),
	(29,'',19,3),
	(30,'',19,2),
	(31,'',19,1),
	(32,'',9,3),
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
	(320,'',16,1);

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

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;

INSERT INTO `ticket` (`id_ticket`, `nombre_tienda`, `sucursal`, `fecha`, `hora`, `subtotal`, `iva`, `total`, `ticket_tienda`, `ticket_subtienda`, `ticket_transaccion`, `ticket_fecha`, `ticket_hora`)
VALUES
	(44,'Grupo Walmart','Plaza Jardin','2019-07-04','13:18:09',91.56,17.44,109,'WALMART','','TDA#2670OP#00000214TE#003TR#08103','20/07/19','11:04'),
	(45,'Grupo Walmart','Plaza Jardin','2019-12-04','13:18:09',91.56,17.44,109,'WALMART','','TDA#2670OP#00000214TE#003TR#08104','20/07/19','11:04'),
	(46,'Grupo Walmart','Plaza Jardin','2019-12-05','13:18:09',91.56,17.44,109,'WALMART','','TDA#2670OP#00000214TE#003TR#08105','20/07/19','11:04'),
	(47,'Grupo Walmart','Plaza Jardin','2019-05-21','19:18:01',91.56,17.44,109,NULL,NULL,NULL,NULL,NULL),
	(48,'Grupo Walmart','Plaza Jardin','2020-01-20','13:18:09',91.56,17.44,109,'WALMART','','TDA#2670OP#00000214TE#003TR#08106','20/01/20','11:04'),
	(49,'Oxxo','Plaza Jardin','2020-01-20','13:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#003TR#08107','20/01/20','11:10'),
	(50,'Oxxo','Plaza Jardin','2019-12-20','13:18:10',91.56,17.44,109,'Oxxo','','TDA#2670OP#00000214TE#003TR#08007','19/12/20','11:10'),
	(51,'Grupo Walmart','Plaza Jardin','2019-05-21','22:37:07',91.56,17.44,109,NULL,NULL,NULL,NULL,NULL);

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
	(2,78);

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
	(2,'Juan Oso',NULL,NULL,'1983-09-11',NULL,'+5215534714616','kissthbw@gmail.com','kissthbw@gmail.com','isaias53',NULL,NULL,NULL,NULL,'57300',NULL,NULL,1,'6241',1,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1573231784/shingshing/usuarios/2.jpg','12ba4e44b53b2434aeb6fa48f6f1e08ff845151a8077f595dc76018e89eb4a3b','55d45a800b2d6d8794af76e97e20db2e03af8a7ab78d28ed1be0974318d118c6','2019-11-27 19:25:47',NULL,'2019-07-10 18:04:50'),
	(42,'Erick Alvarez',NULL,NULL,'1992-02-15',NULL,'+5215550679875','lab92mx@gmail.com','lab92mx@gmail.com','12345678',NULL,NULL,NULL,NULL,'57800',NULL,NULL,1,'7543',1,NULL,1,'http://res.cloudinary.com/shingshing/image/upload/v1573850413/shingshing/usuarios/42.jpg','ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f','a05767f5d724a631a7cb47531229a4ed51bfb22e537b9010e0bc1cbc63d3f041','2019-11-21 19:19:39',NULL,'2020-01-10 19:04:50'),
	(59,'Juan Osorio Alvarez',NULL,NULL,'1983-09-11',NULL,'+5215555555551','juan.osorio@gmail.com','juan.osorio@gmail.com','shingshing',NULL,NULL,NULL,NULL,'57300',NULL,NULL,0,'7650',1,NULL,1,NULL,'df755c8f8edb665735260649c15691f8ea668045f0048673545f9035debb95c9',NULL,NULL,NULL,'2020-01-10 19:04:50'),
	(73,'Roberto',NULL,NULL,'1990-09-17',NULL,'+5215520777555','roberto.htamayo@gmail.com','roberto.htamayo@gmail.com','robe2019',NULL,NULL,NULL,NULL,'14030',NULL,NULL,1,'3958',1,NULL,1,NULL,'8308506504be11dd0dccc8e4c8ee0c14ecc73c67ea2df1b4cf9fc71144686265',NULL,NULL,NULL,'2020-01-10 19:04:50'),
	(78,'Paola Patricia',NULL,NULL,'1982-10-10',NULL,'+5215548998388','beyota_paola@hotmail.com','beyota_paola@hotmail.com','10PAOLA10',NULL,NULL,NULL,NULL,'57300',NULL,NULL,1,'4714',2,NULL,1,NULL,'c293f727c75868bc0dd3fc067b4f1d384710b6dc0cc18315c5c3a8162a2f9ccd',NULL,NULL,'133effcb4bfe768975657285171833f9b714c2359779dd7881d10f0f7e9956d2','2020-01-10 19:04:50'),
	(81,'Juan Osorio Alvarez',NULL,NULL,'1970-01-01',NULL,'+5215555555555','kissthbw@hotmail.com','kissthbw@hotmail.com','kissthbw@hotmail.com',NULL,NULL,NULL,NULL,'00000',NULL,NULL,1,'0000',3,2,1,NULL,NULL,NULL,NULL,NULL,'2020-01-10 19:04:50'),
	(82,'Adrian','Osorio','Alvarez','1984-10-02','','+5215540150544','masterboy@gmail.com','masterboy84','qwerty12387','Pataguas','115','','La Perla','57820','Nezahualcoyotl','Estado de México',0,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2020-01-15 19:18:00');

/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
