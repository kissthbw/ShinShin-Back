package com.bit.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.Category;
import com.bit.model.dto.Item;
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
	
	@Transactional
	@Test
	public void getHistoricoBonificaciones() {
		
				
		historicoMediosBonificacionDAO.obtieneHistoricoBonificaciones(null);
		
		
	}
	
	@Transactional
	@Test
	public void getDetakkeHistoricoBonificaciones() {
		BonificacionItem item = new BonificacionItem();
		
		item.setFecha( new Date() );
		item.setFechaFormateada("2020-01-28");
		item.setIdTipo( BigInteger.valueOf( 3 ) );
		
		//historicoMediosBonificacionDAO.obtieneDetalleHistoricoBonificaciones(item);		
		
	}
	
	@Transactional
	@Test
	public void obtieneEstadisticasBonificacionesGeneral() throws JsonProcessingException {
		int year = 2020;
		int month = 1;
		int day = 1;
		Integer[] tipos = new Integer[] {1, 2, 3};
		
		historicoMediosBonificacionDAO.obtieneTotalBonificaciones();
		
		
		List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day, tipos);
		System.out.println( Utils.objectToJSON(list1) );
		Category c = new Category();
		c.setTitulo("Diario");
		c.setItems(list1);
		
		List<Item> list2 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month, tipos);
		System.out.println( Utils.objectToJSON(list2) );
		Category c2 = new Category();
		c2.setTitulo("Semanal");
		c2.setItems(list2);
		
		List<Item> list3 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, tipos);
		System.out.println( Utils.objectToJSON(list3) );
		Category c3 = new Category();
		c3.setTitulo("Mensual");
		c3.setItems(list3);
		
		List<Category> list = new ArrayList<Category>();
		list.add(c);
		list.add(c2);
		list.add(c2);
		System.out.println( Utils.objectToJSON(list) );
	}
	
	@Transactional
	@Test
	public void obtieneEstadisticasBonificacionesRecargas() {
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		
		//Agregar catalogo en BD
		String[] companias = {"Telcel", "ATT&T"};
		
		List<Category> list = new ArrayList<Category>();
		for( String com : companias ) {
			List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaDiaMesAnio(year, month, day, com);
			List<Item> listaEscaneoDeptos = new ArrayList<>();
//			initEscaneosPorMes(listaEscaneoDeptos, listaTmpRecargas);
			
			Category c = new Category( com, listaTmpRecargas );
			
			list.add( c );
		}
		
		System.out.println( list ) ;
		
	}
}
