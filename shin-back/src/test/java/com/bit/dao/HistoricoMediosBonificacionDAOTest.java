package com.bit.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.config.WebConfig;
import com.bit.model.HistoricoMediosBonificacion;
import com.bit.model.MediosBonificacion;
import com.bit.model.Usuario;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.HistoricoMediosBonificacionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class HistoricoMediosBonificacionDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(HistoricoMediosBonificacionDAOTest.class);
	
	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;
	
	@Autowired
	private HistoricoMediosBonificacionService historicoMediosBonificacionService;

	@Transactional
	@Test
	public void crudTest() {
		HistoricoMediosBonificacion hmb = historicoMediosBonificacionDAO.findByPK(1L);
		System.out.println(hmb.getCantidadBonificacion());
	}
	
	@Transactional
	@Test
	@Rollback(value=false)
	public void registraSolicitudDeBonificacion() {
		HistoricoMediosBonificacion item = new HistoricoMediosBonificacion();
		MediosBonificacion medio = new MediosBonificacion();
		medio.setIdMediosBonificacion(13L);
		
		Usuario u = new Usuario();
		u.setIdUsuario(2L);
		
		item.setCantidadBonificacion(50.0);
		item.setFechaBonificacion(new Date());
		item.setHoraBonificacion(new Date());
		item.setMediosBonificacion(medio);
		item.setUsuario(u);
		
		try {
			System.out.println( Utils.objectToJSON(item) );
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		historicoMediosBonificacionService.registrarHistoricosMediosBonificacion(item);
	}
	
	@Transactional
	@Test
	public void getBonificacionesPorUsuario() {
		Usuario item = new Usuario();
		item.setIdUsuario(2l);
				
		ListItemsRSP rsp = historicoMediosBonificacionService.getHistoricosMediosBonificacionPorUsuario(item);
		
		for( HistoricoMediosBonificacion h : rsp.getHistoricoMediosBonificaciones() ) {
			System.out.println( h.getCantidadBonificacion() + " a " + h.getMediosBonificacion().getAliasMedioBonificacion() );
		}
	}
}
