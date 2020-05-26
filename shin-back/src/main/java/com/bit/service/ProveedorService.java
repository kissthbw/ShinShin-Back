package com.bit.service;

import java.util.List;

import com.bit.model.Proveedor;
import com.bit.model.dto.Item;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.InformacionDashboardProveedorRSP;
import com.bit.model.report.ProductoTicketUsuarioReport;

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
	
	List<Item> obtieneEstadisticasEmpresaTopCP( Proveedor item );
	
	public List<List<Object>> obtieneInfoReporteEmpresaGeneral( Proveedor item );
	
	/*
	 * Dashboard empresa - Finanzas -
	 */
	InformacionDashboardProveedorRSP obtieneTotalesDashboardFinanzasProductos( Proveedor item );
	public List<List<Object>> obtieneInfoReporteEmpresaFinanzas( Proveedor item );
	
	/*
	 * Dashboard empresa - Productos -
	 */
	InformacionDashboardProveedorRSP obtieneTotalesDashboardEmpresaProductos( Proveedor item );
	public List<List<Object>> obtieneInfoReporteEmpresaProductos( Proveedor item );
	
	/*
	 * Dashboard empresa - Usuarios -
	 */
	InformacionDashboardProveedorRSP obtieneTotalesDashboardEmpresaUsuarios( Proveedor item );
	List<ProductoTicketUsuarioReport> obtieneInfoReporteEmpresaUsuarios( Proveedor item );
}
