package com.bit.service;

import java.util.List;

import com.bit.model.Proveedor;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.InformacionDashboardProveedorRSP;

public interface ProveedorService {

	InformacionDashboardProveedorRSP obtieneTotalesDashboard( Proveedor item );
	
	/**
	 * 
	 * @param item
	 * @param tipo, null si se requiere obtener consulta inicial, 1 para bonificaciones, 2 para para productos, 3 para usuarios
	 * @param categoria, null si se requiere obtener consulta inicial, d para dia, s para semana, m para mes
	 * @return
	 */
	EstadisticasGeneralRSP obtieneEstadisticasEmpresaGeneral( Proveedor item, String tipo, String categoria);
	
	public List<List<Object>> obtieneInfoReporteEmpresaGeneral( Proveedor item );
}
