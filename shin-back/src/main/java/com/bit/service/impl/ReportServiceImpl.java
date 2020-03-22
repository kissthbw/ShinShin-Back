package com.bit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.model.dto.Category;
import com.bit.model.dto.Item;
import com.bit.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;
	
	@Override
	@Transactional
	public List<Item> getBonificacionesGeneralInfo() {
		int year = 2020;
		int month = 1;
		int day = 1;
		Integer[] tipos = new Integer[] {1, 2, 3};
	    
	    List<Item> list1 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day, tipos);
		List<Item> list2 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month, tipos);		
		List<Item> list3 =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, tipos);

		List<Item> list = new ArrayList<>();
		for (Item item : list1) {
			list.add(item);
		}
		
		for (Item item : list2) {
			list.add(item);
		}
		
		for (Item item : list3) {
			list.add(item);
		}
		
		return list;
	}

	@Override
	@Transactional
	public HashMap<String, List<Item>> getBonificacionesRecargasInfo() {
		HashMap<String, List<Item>> result = new HashMap<String, List<Item>>();
		int year = 2020;
		int month = 1;
		int day = 1;
		
		//Recargas, dia, semana, mes
		List<Item> recargasDia =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day, new Integer[] {3});
		List<Item> recargasSemana =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month, new Integer[] {3});
		List<Item> recargasMes =  historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, new Integer[] {3});
//		initListaMensual(listaMensual, list1);
		
		List<Item> recargas = new ArrayList<Item>();
		recargas.addAll(recargasDia);
		recargas.addAll(recargasSemana);
		recargas.addAll(recargasMes);
		result.put("Recargas", recargas);
		
		//Recargas por compania, dia, semana,mes 
		String[] companias = {"Telcel", "ATT&T"};
		List<Item> listCompanias = new ArrayList<Item>();
		
		for( String com : companias ) {
			List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaDiaMesAnio(year, month, day, com);
			listCompanias.addAll(listaTmpRecargas);
		}
		
		for( String com : companias ) {
			List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaDiaMesAnio(year, month, day, com);
			listCompanias.addAll(listaTmpRecargas);
		}
		for( String com : companias ) {
			List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaMesAnio(year, com);
			listCompanias.addAll(listaTmpRecargas);
		}

		result.put("Companias", listCompanias);
		
		return result;
	}

}
