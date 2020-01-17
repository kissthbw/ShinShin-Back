package com.bit.service;

import com.bit.model.dto.response.EstadisticasRSP;

public interface EstadisticasService {
	
	/**
	 * Para obtener la informacion de la pagina estadisticas-usuarios
	 * @return
	 */
	EstadisticasRSP obtieneEstadisticasUsuarios();
	
}
