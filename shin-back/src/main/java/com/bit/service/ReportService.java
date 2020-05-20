package com.bit.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.bit.model.dto.Item;

public interface ReportService {
	List<Item> getBonificacionesGeneralInfo();
	HashMap<String, List<Item>> getBonificacionesRecargasInfo();
	
	/**
	 * 
	 * @param pWriter
	 */
	void makeEstadisticasGeneralCSVReport(PrintWriter pWriter);
	
	/**
	 * Recupera la data para el reporte de los catalogo de laas marcas en CSV.
	 * @return
	 */
	void makeReporteCatalogoMarcas(PrintWriter pWriter);
	
	/**
	 * Fabrica reporte del catalogo de Productos por una Marca en CSV.
	 * 
	 * @param idMarca La marca a desglozar.
	 * @param pWriter
	 */
	void makeReporteCatalogoProductosPorMarca(String idMarca, HttpServletResponse response);
	
	/**
	 * Fabrica el reporte Individual de Recarga.
	 * 
	 * @param fecha La fecha a consultar.
	 * @param pWriter
	 */
	void makeReporteIndividualRecarga(String fecha, HttpServletResponse response);
	
	/**
	 * Fabrica el reporte General de Recarga.
	 * 
	 * @param response La respuesta
	 */
	void makeReporteGeneralRecargas(HttpServletResponse response);
	
	/**
	 * Fabrica el reporte Catalogo Tiendas.
	 * 
	 * @param pWriter
	 */
	void makeReporteCatalogoTiendas(PrintWriter pWriter);
	
	/**
	 * Fabrica reporte del catalogo de Tiendas por una tienda en CSV.
	 * 
	 * @param idTienda La tienda a desglozar.
	 * @param pWriter
	 */
	void makeReporteCatalogoTiendasPorTienda(String idTienda, HttpServletResponse response);
}
