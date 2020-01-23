package com.bit.service;

import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.EstadisticasRSP;

public interface EstadisticasService {
	
	/**
	 * Para obtener la informacion de la pagina estadisticas-usuarios
	 * @return
	 */
	EstadisticasRSP obtieneEstadisticasUsuarios();

	EstadisticasRSP obtieneEstadisticasTickets();
	
	/**
	 * Para poder obtener la informacion de la pagina estadisticas-general
	 * @return
	 */
	EstadisticasGeneralRSP obtieneEstadisticasGeneral();
	
	/**
	 * Obtiene la informacion de la pagina  estadisticas-marcas
	 * @return
	 */
	EstadisticasGeneralRSP obtieneEstadisticasMarcas();
	
	
}
