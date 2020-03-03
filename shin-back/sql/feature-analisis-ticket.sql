-- MySQL dump 10.13  Distrib 5.7.22, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: ShinShin
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Table structure for table `catalogo_diccionario_tiendas`
--

DROP TABLE IF EXISTS `catalogo_diccionario_tiendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogo_diccionario_tiendas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clave_diccionario` varchar(45) DEFAULT NULL,
  `valor_diccionario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_diccionario_tiendas`
--

LOCK TABLES `catalogo_diccionario_tiendas` WRITE;
/*!40000 ALTER TABLE `catalogo_diccionario_tiendas` DISABLE KEYS */;
INSERT INTO `catalogo_diccionario_tiendas` VALUES (1,'xxo','OXXO'),(2,'OXXO','OXXO'),(3,'xxo','OXXO'),(4,'Oxx0','OXXO'),(5,'Oxxa','OXXO'),(6,'Oxx0','OXXO'),(7,'GxD','OXXO'),(8,'Oxxo','OXXO'),(9,'Oyxo','OXXO'),(10,'OvxO','OXXO'),(11,'OwxO','OXXO'),(12,'Gxxo','OXXO'),(13,'xxo','OXXO');
/*!40000 ALTER TABLE `catalogo_diccionario_tiendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogo_tienda_pattern`
--

DROP TABLE IF EXISTS `catalogo_tienda_pattern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogo_tienda_pattern` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_tienda` varchar(45) DEFAULT NULL,
  `id_pattern` varchar(45) DEFAULT NULL,
  `pattern` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogo_tienda_pattern`
--

LOCK TABLES `catalogo_tienda_pattern` WRITE;
/*!40000 ALTER TABLE `catalogo_tienda_pattern` DISABLE KEYS */;
INSERT INTO `catalogo_tienda_pattern` VALUES (1,'WALMART','ID_WALMART_HORA_PATTERN','(\\d\\d:\\d\\d)'),(2,'WALMART','ID_TDA_PATTERN','TDA[\\#|H|M|N][0-9]+\\b'),(3,'WALMART','ID_OP_PATTERN','[O|0]P[\\#|H|M|N][A-Z0-9]+\\b'),(4,'WALMART','ID_TE_PATTERN','TE[\\#|H|M|N]\\s[0-9]+\\b'),(5,'WALMART','ID_TR_PATTERN','TR[\\#|H|M|N|\\s]\\s[0-9]+\\b'),(6,'WALMART','ID_WALMART_FECHA_PATTERN','(0[1-9]|[12][0-9]|3[01])[/ .](0[1-9]|1[012])[/ .](19|20)'),(8,'SORIANA','ID_SORIANA_FECHA_PATTERN','(0[1-9]|[12][0-9]|3[01])[/ .](0[1-9]|1[012])[/ .](20)\\d\\d'),(9,'SORIANA','ID_SORIANA_HORA_PATTERN','(\\d\\d:\\d\\d:\\d\\d)'),(10,'SORIANA','ID_SUCURSAL','\\d{3}\\s\\d{3}\\s\\d+\\s\\d+'),(11,'SORIANA','ID_SERIAL_NUMBER','[0-9]{5}'),(12,'7ELEVEN','ID_FECHA_PATTERN','(0[1-9]|1[012])[/ .](0[1-9]|[12][0-9]|3[01])[/ .](19|20)'),(13,'7ELEVEN','ID_HORA_PATTERN','(\\d\\d:\\d\\d:\\d\\d\\s(PM|AM))'),(14,'7ELEVEN','ID_SUCURSAL','\\d+\\s\\d+\\s\\d+\\s\\d+'),(15,'7ELEVEN','ID_SERIAL_NUMBER','[0-9]{5}'),(16,'7ELEVEN','ID_CANTIDAD','\\d+\\s'),(17,'OXXO','ID_OXXO_FECHA_PATTERN','(0[1-9]|[12][0-9]|3[01])[/ ).](0[1-9]|1[012])[/ .](19|20)\\d\\d'),(18,'OXXO','ID_OXXO_HORA_PATTERN','(\\d\\d:\\d\\d))'),(19,'OXXO','ID_OXXO_PRODUCTOS_PATTERN','[a-zA-Z0-9]*\\.?\\d*\\.?\\d*)'),(20,'OXXO','ID_OXXO_FOLIO_VENTA','[a-zA-Z]+\\s?[a-zA-Z]+:[0-9]+)'),(21,'OXXO','ID_OXXO_ID_VENTA','ID[=|a-zA-Z0-9]+)'),(22,'OXXO','ID_OXXO_CIFRAS','^-?\\d*\\.{1,1}\\d+$)'),(23,'OXXO','ID_OXXO_CAJA','\\b[0-9]+\\b)');
/*!40000 ALTER TABLE `catalogo_tienda_pattern` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-27 11:07:17
