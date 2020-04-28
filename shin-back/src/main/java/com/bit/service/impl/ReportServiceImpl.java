package com.bit.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.CSVExporter;
import com.bit.dao.CatalogoMarcaDAO;
import com.bit.dao.CatalogoTiendaDAO;
import com.bit.dao.CatalogoTipoProductoDAO;
import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.dao.ProductoDAO;
import com.bit.dao.TicketDAO;
import com.bit.dao.UsuarioDAO;
import com.bit.model.dto.Item;
import com.bit.model.dto.response.EstadisticaGeneralCSV;
import com.bit.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private CatalogoTiendaDAO catalogoTiendaDAO;

	@Autowired
	private CatalogoTipoProductoDAO catalogoTipoProductoDAO;

	@Autowired
	private CatalogoMarcaDAO catalogoMarcaDAO;

	@Autowired
	private TicketDAO ticketDAO;

	@Autowired
	private HistoricoMediosBonificacionDAO historicoMediosBonificacionDAO;

	@Override
	@Transactional
	public void getEstadisticasGeneralCSV(PrintWriter pWriter) {

		// TODO Obtener la data de los daos
		List<EstadisticaGeneralCSV> data = ticketDAO.obtieneEstadisticasTickets();
		// TODO Procesar la data

		// TODO Escribir data en el stream del CSV
		CSVExporter csv = new CSVExporter();

		String[] headers = { "Fecha", "Total Usuarios", "Edad Prom" };
		try {

			List<List<Object>> rows = new ArrayList<>();
			for (int j = 0; j < data.size(); j++) {
				EstadisticaGeneralCSV es = data.get(j);
				rows.add(Arrays
						.asList(new Object[] { es.getFecha().toString(), es.getTotalUsuarios(), es.getTotalEdadPromedio().floatValue()
								}));
			}
			csv.writeCSV(pWriter, headers, rows);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	@Transactional
	public List<Item> getBonificacionesGeneralInfo() {
		int year = 2020;
		int month = 1;
		int day = 1;
		Integer[] tipos = new Integer[] { 1, 2, 3 };

		List<Item> list1 = historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month, day,
				tipos);
		List<Item> list2 = historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year, month,
				tipos);
		List<Item> list3 = historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year, tipos);

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

		// Recargas, dia, semana, mes
		List<Item> recargasDia = historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoDiaMesAnio(year, month,
				day, new Integer[] { 3 });
		List<Item> recargasSemana = historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoSemanaMesAnio(year,
				month, new Integer[] { 3 });
		List<Item> recargasMes = historicoMediosBonificacionDAO.obtieneTotalBonificacionesPorTipoMesAnio(year,
				new Integer[] { 3 });
//		initListaMensual(listaMensual, list1);

		List<Item> recargas = new ArrayList<Item>();
		recargas.addAll(recargasDia);
		recargas.addAll(recargasSemana);
		recargas.addAll(recargasMes);
		result.put("Recargas", recargas);

		// Recargas por compania, dia, semana,mes
		String[] companias = { "Telcel", "ATT&T" };
		List<Item> listCompanias = new ArrayList<Item>();

		for (String com : companias) {
			List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaDiaMesAnio(year,
					month, day, com);
			listCompanias.addAll(listaTmpRecargas);
		}

		for (String com : companias) {
			List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaDiaMesAnio(year,
					month, day, com);
			listCompanias.addAll(listaTmpRecargas);
		}
		for (String com : companias) {
			List<Item> listaTmpRecargas = historicoMediosBonificacionDAO.obtieneRecargasPorCompaniaMesAnio(year, com);
			listCompanias.addAll(listaTmpRecargas);
		}

		result.put("Companias", listCompanias);

		return result;
	}
}
