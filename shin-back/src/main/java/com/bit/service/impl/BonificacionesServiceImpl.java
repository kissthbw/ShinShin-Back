package com.bit.service.impl;

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
	/*
	 * Reporte de la pagina: bonificaciones-depositos
	 */
	public List<List<Object>> obtieneInfoReporteBonificacionesDepositosGeneral() {
		List<List<Object>> rows = new ArrayList<>();
		
		//List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneHistoricoBonificaciones( null );
		List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneDetalleHistoricoBonificaciones( null ,mediosFull);
		
		//Formatear fecha dd-MMM-yyyy
		//Formatear solicitudes y bonificaciones
		for (BonificacionItem b : list) {
			b.setFechaFormateada( Utils.formatDateToString(b.getFecha(), "dd/MM/yyyy") );
			b.setImporteFormateado( Utils.formatNumeros(b.getImporte(), "$###,###,###.00") );
			b.setHoraFormateada( Utils.formatDateToString(b.getHora(), "hh:mm:ss") );
		}
		
		
//		List<BonificacionItem> list =  historicoMediosBonificacionDAO.obtieneBonificacionesDepositosGeneral();
		for( BonificacionItem i : list ) {
			if(i.getIdTipo().intValue()==MedioBonificacionID.PAYPAL.value()){
				rows.add( Arrays.asList( new Object[] {
					i.getIdUsuario(),
					i.getTipo(), 
					i.getId(),
					i.getBanco(),
					i.getImporteFormateado(),
					i.getNombreTitular(),
					i.getFechaFormateada(),
					i.getHoraFormateada()
					} ) );
			}else{
				rows.add( Arrays.asList( new Object[] {
					i.getIdUsuario(),
					i.getTipo(), 
					i.getCuentaMedioBonificacion(),
					i.getBanco(),
					i.getImporteFormateado(),
					i.getNombreTitular(),
					i.getFechaFormateada(),
					i.getHoraFormateada()
					} ) );
			}
		
		}

		
		return rows;
	}

	@Override
	@Transactional
	public List<List<Object>> obtieneInfoReporteRecargasGeneral() {
		
		List<BonificacionItem> list = historicoMediosBonificacionDAO.obtieneHistoricoBonificacionesPorTipo( new Integer[] { MedioBonificacionID.RECARGA.value() } );
		
		//Formatear fecha dd-MMM-yyyy
		//Formatear solicitudes y bonificaciones
		for (BonificacionItem item : list) {
			item.setFechaFormateada( Utils.formatDateToString(item.getFecha(), "dd-MMM-yyyy") );
			item.setImporteFormateado( Utils.formatNumeros(item.getImporte(), "$###,###,###.00") );
		}
		
		
		List<List<Object>> rows = new ArrayList<>();
		
//		List<BonificacionItem> list =  historicoMediosBonificacionDAO.obtieneBonificacionesRecargasGeneral();
		for( BonificacionItem i : list ) {
//			item.setFechaFormateada( Utils.formatDateToString(item.getFecha(), "dd-MMM-yyyy") );
			rows.add( Arrays.asList( new Object[] { 
					i.getFechaFormateada(), 
					i.getSolicitudes(),
					i.getImporteFormateado()
			} ) );
		}

		
		return rows;
	}
}
