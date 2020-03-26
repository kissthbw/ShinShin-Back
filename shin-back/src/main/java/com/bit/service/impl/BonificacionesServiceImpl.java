package com.bit.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.dao.CatalogoMediosBonificacionDAO.MedioBonificacionID;
import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.Item;
import com.bit.service.BonificacionesService;

@Service
public class BonificacionesServiceImpl implements BonificacionesService {

	private static final String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
	
	private static Integer[] mediosFull = new Integer[] {MedioBonificacionID.BANCARIA.value(), 
			MedioBonificacionID.PAYPAL.value(), 
			MedioBonificacionID.RECARGA.value()};
	
	private static Integer[] mediosWithoutRecarga = new Integer[] {MedioBonificacionID.BANCARIA.value(), 
			MedioBonificacionID.PAYPAL.value()};
	
	private static Integer[] mediosOnlyRecarga = new Integer[] {
			MedioBonificacionID.RECARGA.value()};
	
	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;
	
	@Override
	@Transactional
	public List<List<Object>> obtieneReporteDepositosGeneral() {
		
		List<List<Object>> rows = new ArrayList<>();
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		int day = now.getDayOfMonth();
		
		year = 2020;
		month = 1;
		day = 28;
		
		List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day, 
				mediosFull);
		rows.add( Arrays.asList( new Object[] { "Fecha", "" } ) );
		for( Item i : list1 ) {
			rows.add( Arrays.asList( new Object[] { i.getIndice(), i.getTotal() } ) );
		}
		
		List<Item> list2 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month, 
				mediosFull);
		Utils.initListaSemanal(list2);
		rows.add( Arrays.asList( new Object[] { "Fecha", "" } ) );
		for( Item i : list2 ) {
			rows.add( Arrays.asList( new Object[] { i.getTopico(), i.getTotal() } ) );
		}
		
		
		List<Item> list3 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, 
				mediosFull);
		List<Item> listaMensual = new ArrayList<>();
		Utils.initListaMensual(listaMensual, list3);
		rows.add( Arrays.asList( new Object[] { "Fecha", "" } ) );
		for( Item i : list3 ) {
			rows.add( Arrays.asList( new Object[] { i.getTopico(), i.getTotal() } ) );
		}
		
		return rows;
	}
	
	@Override
	@Transactional
	public List<List<Object>> obtieneInfoReporteBonificacionesDepositosGeneral() {
		List<List<Object>> rows = new ArrayList<>();
		
		List<BonificacionItem> list =  historicoMediosBonificacionDAO.obtieneBonificacionesDepositosGeneral();
		for( BonificacionItem i : list ) {
//			item.setFechaFormateada( Utils.formatDateToString(item.getFecha(), "dd-MMM-yyyy") );
			rows.add( Arrays.asList( new Object[] { Utils.formatDateToString(i.getFecha(), "dd-MMM-yyyy"), 
					i.getSolicitudes(),
					i.getCompany()} ) );
		}

		
		return rows;
	}

	@Override
	@Transactional
	public List<List<Object>> obtieneInfoReporteRecargasGeneral() {
		
		List<List<Object>> rows = new ArrayList<>();
		
		List<BonificacionItem> list =  historicoMediosBonificacionDAO.obtieneBonificacionesRecargasGeneral();
		for( BonificacionItem i : list ) {
//			item.setFechaFormateada( Utils.formatDateToString(item.getFecha(), "dd-MMM-yyyy") );
			rows.add( Arrays.asList( new Object[] { Utils.formatDateToString(i.getFecha(), "dd-MMM-yyyy"), 
					i.getSolicitudes(),
					i.getCompany()} ) );
		}

		
		return rows;
	}
}
