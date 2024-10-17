/*EJECUTAR QUERY*/
CREATE DATABASE BDPOLIZA;

USE BDPOLIZA;

CREATE TABLE `usuario` (
  `dni` varchar(8) NOT NULL,
  `id_usuario` bigint NOT NULL AUTO_INCREMENT,
  `correo` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`)
);

CREATE TABLE `polizas` (
  `fecha_inicio` date NOT NULL,
  `fecha_vencimiento` date NOT NULL,
  `monto_asegurado` double NOT NULL,
  `id_poliza` bigint NOT NULL AUTO_INCREMENT,
  `id_usuario` bigint NOT NULL,
  `detalles_adicionales` text,
  `tipo_seguro` varchar(255) NOT NULL,
  PRIMARY KEY (`id_poliza`),
  KEY (`id_usuario`),
  CONSTRAINT FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`)
);
/*REVERSION*/
/*
DROP DATABASE BDPOLIZA
*/
