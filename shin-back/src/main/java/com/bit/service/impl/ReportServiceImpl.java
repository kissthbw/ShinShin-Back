package com.bit.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.CSVExporter;
import com.bit.common.Utils;
import com.bit.dao.CatalogoMarcaDAO;
import com.bit.dao.CatalogoTiendaDAO;
import com.bit.dao.CatalogoTipoProductoDAO;
import com.bit.dao.HistoricoMediosBonificacionDAO;
import com.bit.dao.ProductoDAO;
import com.bit.dao.TicketDAO;
import com.bit.dao.UsuarioDAO;
import com.bit.model.dto.Item;
import com.bit.model.dto.response.EstadisticaGeneralCSV;
import com.bit.model.report.EstadisticaGeneralTotalTicketCSV;
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
	public void makeEstadisticasGeneralCSVReport(PrintWriter pWriter) {

		// Headers
		List<String> hList = new ArrayList<String>();
		hList.add("Fecha");
		hList.add("Total Usuarios");
		hList.add("Edad Prom");
		hList.add("18-24");
		hList.add("25-29");
		hList.add("30-39");
		hList.add("40-49");
		hList.add("50-59");
		hList.add("60 +");
		hList.add("Hombres");
		hList.add("Mujeres");
		hList.add("Total Tickets Escaneados por tienda");

		// DONE Obtener la data de los daos
		List<EstadisticaGeneralCSV> dataPrincipal = ticketDAO.obtieneEstadisticasTickets();

		// El dato del total de los tickets por la tienda
		dataPrincipal = ticketDAO.calculaTotalTicketEscaneadosTiendas(dataPrincipal);
		
		// Las Tiendas activas.
		List<String> tiendas = ticketDAO.obtieneTiendasConTicket();
		tiendas.forEach(tienda -> hList.add(tienda)); // Los encabezados.
		
		// Los deptos en tickets
		hList.add("Total Productos Escaneados por Dpto");
		List<String> deptos = ticketDAO.obtieneDepartamentosConTicket();
		deptos.forEach(nomDep -> hList.add(nomDep)); // Agrega encabezados
		// El dato del total de los producto escaneados en el ticket
		dataPrincipal = ticketDAO.calculaTotalProductosEscaneados(dataPrincipal);
		
		// Los productos por marca
		hList.add("Total Productos Escaneados por Marca");
		List<String> marcas = ticketDAO.obtieneMarcasConTicket();
		marcas.forEach(nomDep -> hList.add(nomDep)); // Agrega encabezados
		// El dato del total de los producto escaneados en el ticket
		dataPrincipal = ticketDAO.calculaTotalMarcaProductosEscaneados(dataPrincipal);
		
		
		// DONE Procesar la data
		
		dataPrincipal.forEach(data -> {
			
			// Para cada fecha traer por cada tienda la estadistica.
			tiendas.forEach(tienda -> {
				EstadisticaGeneralTotalTicketCSV egtt = null;
				egtt = ticketDAO.obtieneEstadisticaGeneralTotalTicketCSVPorFechaYTienda(data.getFecha(), tienda);
				if(egtt == null) {
					egtt = new EstadisticaGeneralTotalTicketCSV(data.getFecha());
				}
				data.getTotalesDeTiendas().add(egtt);
			});
			
			// Por cada depto ir por la estadistica en la fecha
			deptos.forEach(depto -> {
				EstadisticaGeneralTotalTicketCSV egtt = null;
				egtt = ticketDAO.obtieneEstadisticaGeneralTotalTicketCSVPorFechaYDepto(data.getFecha(), depto);
				if(egtt == null) {
					egtt = new EstadisticaGeneralTotalTicketCSV(data.getFecha());
				}
				data.getTotalesDeDeptos().add(egtt);
			});
			
			// Por cada marcair por la estadistica en la fecha
			marcas.forEach(depto -> {
				EstadisticaGeneralTotalTicketCSV egtt = null;
				egtt = ticketDAO.obtieneEstadisticaGeneralTotalTicketCSVPorFechaYMarca(data.getFecha(), depto);
				if(egtt == null) {
					egtt = new EstadisticaGeneralTotalTicketCSV(data.getFecha());
				}
				data.getTotalesDeMarcas().add(egtt);
			});
			
		});

		List<List<Object>> rows = new ArrayList<>();
		for (EstadisticaGeneralCSV es : dataPrincipal) {
			List<Object> objts = new ArrayList<Object>();
			objts.add(Utils.formatDateToString(es.getFecha(), "dd-MMM-yyyy"));
			objts.add(es.getTotalUsuarios());
			objts.add(es.getTotalEdadPromedio().floatValue());
			objts.add(es.getAvg1824()); 
			objts.add(es.getAvg2529()); 
			objts.add(es.getAvg3039());
			objts.add(es.getAvg4049());
			objts.add(es.getAvg5059());
			objts.add(es.getAvgMAS60()); 
			objts.add(es.getHombres()); 
			objts.add(es.getMujeres());
			objts.add(es.getTotalEscaneadosTiendas());
			es.getTotalesDeTiendas().forEach(tt -> objts.add(tt.getTotalEscaneos()));
			objts.add(es.getTotalProductosEscaneadosDesptos());
			es.getTotalesDeDeptos().forEach(td -> objts.add(td.getTotalEscaneos()));
			objts.add(es.getTotalProductosEscaneadosMarcas());
			es.getTotalesDeMarcas().forEach(td -> objts.add(td.getTotalEscaneos()));
			
		rows.add(objts);
//			rows.add(Arrays.asList(new Object[] { 
//					es.getFecha().toString(),
//					es.getTotalUsuarios(),
//					es.getTotalEdadPromedio(),
//					es.getAvg1824(), 
//					es.getAvg2529(), 
//					es.getAvg3039(),
//					es.getAvg4049(),
//					es.getAvg5059(),
//					es.getAvgMAS60(), 
//					es.getHombres(), 
//					es.getMujeres(),
//					es.getTotalEscaneadosTiendas() 
//					}));
		}
		
		// TODO Escribir data en el stream del CSV
		CSVExporter csv = new CSVExporter();

		try {

			csv.writeCSV(pWriter, hList.toArray(new String[0]), rows);

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
