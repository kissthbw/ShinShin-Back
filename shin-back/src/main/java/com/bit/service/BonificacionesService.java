package com.bit.service;

import java.util.List;

public interface BonificacionesService {
	
	List<List<Object>> obtieneReporteDepositosGeneral();
	List<List<Object>> obtieneInfoReporteBonificacionesDepositosGeneral();
	List<List<Object>> obtieneInfoReporteRecargasGeneral();
	
}
