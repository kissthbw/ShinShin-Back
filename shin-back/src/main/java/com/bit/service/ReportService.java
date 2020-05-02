package com.bit.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import com.bit.model.dto.Item;

public interface ReportService {
	List<Item> getBonificacionesGeneralInfo();
	HashMap<String, List<Item>> getBonificacionesRecargasInfo();
	
	/**
	 * 
	 * @param pWriter
	 */
	void makeEstadisticasGeneralCSVReport(PrintWriter pWriter);
}
