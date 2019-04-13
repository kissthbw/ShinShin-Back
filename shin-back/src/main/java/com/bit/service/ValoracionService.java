package com.bit.service;

import java.util.List;

import com.bit.model.Valoracion;

public interface ValoracionService {
	
	List<Valoracion> getValoraciones();
	
	void guardarValoraciones(Valoracion item);
	
	void actualizarValoraciones(Valoracion item);

}
