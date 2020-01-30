package com.bit.service;

import java.util.List;

import com.bit.model.Usuario;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.response.EstadisticasBonificacionRSP;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.EstadisticasRSP;

public interface EstadisticasService {
	
	/**
	 * Para obtener la informacion de la pagina estadisticas-usuarios
	 * @return
	 */
	EstadisticasRSP obtieneEstadisticasUsuarios();
	
	/**
	 * Obtiene la informacion de las graficas en la pantalla de detalle usuario
	 * @return
	 */
	EstadisticasGeneralRSP obtieneEstadisticasUsuarioDetalle(Usuario item);

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
	
	/**
	 * Obtiene resumen de bonificaciones por tipo
	 * @return
	 */
	List<BonificacionItem> obtieneHistoricoBonificaciones();
	
	/**
	 * Obtiene el detalle de bonificaciones por dia y tipo
	 * @param fecha en formato 2020-01-28
	 * @return
	 */
	List<BonificacionItem> obtieneDetalleHistoricoBonificacionesPorFechaYTipo( BonificacionItem item );
	
	//Funcionalidad relacionada a la pagina bonificaciones-general
	EstadisticasBonificacionRSP obtieneBonificacionesGenerales( String tipo, String categoria, List<Integer> tipos );
	
}
