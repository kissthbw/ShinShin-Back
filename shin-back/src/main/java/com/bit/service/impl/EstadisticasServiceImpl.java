package com.bit.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.UsuarioDAO;
import com.bit.model.dto.response.EstadisticasRSP;
import com.bit.model.dto.response.Item;
import com.bit.service.EstadisticasService;
import com.ibm.icu.util.Calendar;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {

	private static final Logger log = LoggerFactory.getLogger(EstadisticasServiceImpl.class);

	@Autowired
	private UsuarioDAO usuarioDAO;

	private static final String[] meses = {"Ene", "Feb", "Mar", "Abr", "Mar", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
	
	
	@Override
	@Transactional
	public EstadisticasRSP obtieneEstadisticasUsuarios() {

		log.info("Obteniendo informacion de estadisticas de usuarios");
		
		EstadisticasRSP rsp = new EstadisticasRSP();
		
		// Total de usuarios
		BigInteger total = usuarioDAO.obtieneTotalUsuarios();
		rsp.setTotalUsuarios(  null != total ? total.intValue() : 0);

		// Promedio de edad
		BigDecimal edad = usuarioDAO.obtienePromedioEdadUsuarios();
		rsp.setPromedioEdadUsuarios( null != edad ? edad.floatValue() : 0.0f );

		// Promedio de sexo
		List<Item> genero = usuarioDAO.obtieneGeneroUsuarios();
		rsp.setTotalUsuariosPorGenero(genero);
		
		//Promedio de edades
		List<Item> edades = usuarioDAO.obtieneRangoEdadUsuarios();
		rsp.setRangoEdadUsuarios(edades);
		
		
		// Usuarios por mes
		List<Item> usuariosMensuales = usuarioDAO.obtieneUsuariosPorMesYAnio(2020);
		List<Item> listaUsuariosMensuales = new ArrayList<>();
		initUsuariosPorMes(listaUsuariosMensuales, usuariosMensuales);
		rsp.setTotalUsuariosMes(listaUsuariosMensuales);
		
		// Usuarios por semana
		//Se debe determinar el anio y mes actual
		//para mostrar los usuarios registrados del anio y mes en curso
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		List<Item> usuariosSemanales = usuarioDAO.obtieneUsuariosPorSemanaMesAnio(year, month);
		rsp.setTotalUsuariosSemana(usuariosSemanales);
		
		List<Item> usuariosDiarios = usuarioDAO.obtieneUsuariosPorDiaMesAnio(year, month);
		rsp.setTotalUsuariosDias(usuariosDiarios);

		return rsp;
	}
	
	private void initUsuariosPorMes(List<Item> list, List<Item> resultData) {
		
		//Se inicializa la lista con objetos Item, con los valores del mes
		for( String mes : meses ) {
			list.add( new Item(mes, BigInteger.valueOf(0)) );
		}
		
		//Se busca el elemento en la lista que coincida con el valor del campo indice
		//del resultData para asignar el total correspondiente
		for( Item data : resultData ) {
			Item i = list.get( data.getIndice() - 1 );
			
			if( null != i ) {
				i.setTotal( data.getTotal() );
			}
			
		}
	}
}
