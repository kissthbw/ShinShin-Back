package com.bit.communication;

import com.bit.exception.CommunicationException;
import com.bit.model.dto.BonificacionDTO;
import com.bit.model.dto.SimpleResponse;

/**
 * Se definen las frimas de los metodos que se encargaran de la aplicacion
 * de las bonificaciones (PayPal o TiempoAire) al usuario que lo solicite
 * @author juanosorioalvarez
 *
 */
public interface BonificacionService {
	
	/**
	 * A través del API provisto por PayPal, este metodo se encarga de aplicar un abono
	 * a la cuenta del usuario (su cuenta puede ser cuuenta PayPal, correo, o telefono movil)
	 * @param data Contiene informacion de la cuenta PayPal del Usuario, el monto a bonificar
	 * @return SimpleResponse con el estatus del exito o fracaso de la operacion
	 * @throws CommunicationException en caso de ocurrir un error
	 */
	SimpleResponse bonificacionPayPal( BonificacionDTO data ) throws CommunicationException ;
	
	/**
	 * A tarvés del API prvisto por ClickBox, este metodo se encarga de aplicar un abono
	 * al numero movil del usuario
	 * @param data Contiene informacion del numero telefonico del Usuario, el monto a bonificar
	 * @return SimpleResponse con el estatus del exito o fracaso de la operacion
	 * @throws CommunicationException en caso de ocurrir un error
	 */
	SimpleResponse bonificacionTiempoAire( BonificacionDTO data ) throws CommunicationException;
}
