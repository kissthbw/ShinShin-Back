package com.bit.controllers.portal.administrador;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.common.Utils;
import com.bit.dao.CatalogoMediosBonificacionDAO.MedioBonificacionID;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.response.EstadisticasBonificacionRSP;
import com.bit.service.BonificacionesService;
import com.bit.service.CSVExporter;
import com.bit.service.EstadisticasService;
import com.bit.service.ReportService;
import com.bit.service.UsuarioShingShingDetailService;
import com.bit.service.impl.CSVExporterImpl;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping("/portal-administrador")
public class BonificacionesController {

	@Autowired
	private EstadisticasService estadisticasService;

	@Autowired
	private BonificacionesService bonificacionesService;
	
	@Autowired
	private ReportService reportService;
	
	private static Logger log = LoggerFactory.getLogger( BonificacionesController.class );
	
	/*
	 * INICIO SECCION DE BONIFICACIONES
	 */
	@RequestMapping(value = "/bonificaciones-depositos", method = RequestMethod.GET)
	public String redirectionalBonificacionesDepositos(Model model) {
		Log.info("Entrando en redirectionalBonificacionesDepositos");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}
		String zona = TimeZone.getDefault().getDisplayName();
		log.info(zona);

		SimpleDateFormat sdf = new SimpleDateFormat("dd / MMM / yyyy"); // note: time zone not in format!
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		sdf1.setTimeZone(TimeZone.getTimeZone("GMT-5"));
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
		zona = TimeZone.getTimeZone("GMT-5").getDisplayName();
		log.info(zona);
		Date date = new Date();
		log.info(sdf.format(date));
		// Mostrar la fecha actual en formato dd/MMM/yyyyy
		// String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");

		BonificacionItem item = new BonificacionItem();
		item.setFechaFormateada(sdf1.format(date));

		List<BonificacionItem> currentList = estadisticasService.obtieneHistoricoBonificaciones(item);
		List<BonificacionItem> list = estadisticasService.obtieneHistoricoBonificaciones(null);
		model.addAttribute("currentList", currentList);
		model.addAttribute("list", list);
		model.addAttribute("fecha", "Hoy " + sdf.format(date));

		Log.info("Saliendo de redirectionalBonificacionesDepositos");
		return "administrador/bonificaciones-depositos";
	}

	@RequestMapping(value = "/bonificaciones-depositos/report", method = RequestMethod.GET)
	public void reportBonificacionesDepositos(Model model, HttpServletResponse response)
			throws JRException, IOException {
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-5"));
		String document="R-BAPP-"+sdf.format(new Date())+".csv";
		String headerValue = String.format("attachment; filename=\"%s\"", document);
		response.setHeader(headerKey, headerValue);

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		List<List<Object>> rows = bonificacionesService.obtieneInfoReporteBonificacionesDepositosGeneral();

		CSVExporter csv = new CSVExporterImpl();

		String[] headers = { "Usuario", "Tipo", "#", "Banco", "Importe" , "Nombre de Titular" , "Fecha", "Hora" };

		try {
			csv.writeCSV(response.getWriter(), headers, rows);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/bonificaciones-depositos-detalle/{fecha}", method = RequestMethod.GET)
	public String getObtenerDepositosDetalle(Model model, @PathVariable String fecha) {

		log.info("Entrando a getObtenerDeporistosDetalle");

		UsuarioShingShingDetailService current = getAuthenticationUser();
		String fileName = "";
		int p = fecha.indexOf("fn");
		if (p >= 0) {
			fileName = fecha.substring((p + 2), fecha.length());
		}

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		BonificacionItem item = new BonificacionItem();
		// La fecha viene como dd-MMM-yyyy
		// Convertir a yyyy-MM-dd
		Date d = Utils.formatStringToDate(fecha, "dd-MMM-yyyy");
		String f = Utils.formatDateToString(d, "yyyy-MM-dd");

		item.setFechaFormateada(f);
//		item.setIdTipo( BigInteger.valueOf( 3 ) );
		List<BonificacionItem> list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item,
				new Integer[] { MedioBonificacionID.BANCARIA.value(), MedioBonificacionID.PAYPAL.value(),
						MedioBonificacionID.RECARGA.value() });
		model.addAttribute("fecha", fecha);
		model.addAttribute("fileName", fileName);
		model.addAttribute("list", list);
		model.addAttribute("solicitudes",
				Utils.formatNumeros(Integer.valueOf(list.size()).doubleValue(), "###,###,###"));

		double total = list.stream().mapToDouble(i -> i.getImporte()).sum();

		model.addAttribute("importe", Utils.formatNumeros(total, "$###,###,###.00"));

		return "administrador/bonificaciones-depositos-detalle";
	}

	@RequestMapping(value = "/bonificaciones-depositos-detalle/report/{fecha}", method = RequestMethod.GET)
	public void obtieneReporteDepositosDetalle(Model model, HttpServletResponse response, @PathVariable String fecha) {
		String resp = fecha;
		log.info("Entrando a obtieneReporteDepositosDetalle");
		log.debug("Entrando a obtieneReporteDepositosDetalle 1. fecha: "+fecha);
		String fileName = "depositos-";
		int p = fecha.indexOf("fn");
		if (p >= 0) {
			fileName = fecha.substring((p + 2), fecha.length());
			fecha = fecha.substring(0, p);
		}

		UsuarioShingShingDetailService current = getAuthenticationUser();
		Integer tipo[] = new Integer[] { MedioBonificacionID.PAYPAL.value(), MedioBonificacionID.RECARGA.value() };
		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}
		CSVExporter csv = new CSVExporterImpl();
		String[] headers = new String[] {};
		BonificacionItem item = new BonificacionItem();
		// La fecha viene como dd-MMM-yyyy
		// Convertir a yyyy-MM-dd
		Date d = Utils.formatStringToDate(fecha, "dd-MMM-yyyy");
		String f = Utils.formatDateToString(d, "yyyy-MM-dd");
		String fe = Utils.formatDateToString(d, "dd/MM/yyyy");
		String docu = "R-";
		item.setFechaFormateada(f);
		List<BonificacionItem> list = new ArrayList<>();
		List<List<Object>> rows = new ArrayList<>();
		int solicitudes=0;
		if (resp.indexOf("PP") != -1) {
			tipo = new Integer[] { MedioBonificacionID.PAYPAL.value() };
			headers = new String[] { "Usuario", "ID", "Importe", "Correo", "Fecha", "Hora" };
			list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item, tipo);
			for (BonificacionItem i : list) {
				rows.add(Arrays.asList(new Object[] { i.getIdUsuario(), i.getId(), i.getImporteFormateado(),
						i.getCuentaMedioBonificacion(), i.getFechaFormateada(), i.getHoraFormateada() }));
			}
			solicitudes=list.size();
			docu = docu + "PP-" + current.getUsername() + "-" + solicitudes + "-" + fe;
		} else if (resp.indexOf("BA") != -1) {
			tipo = new Integer[] { MedioBonificacionID.BANCARIA.value() };
			headers = new String[] { "Usuario", "Tipo", "#", "Banco", "Importe", "Nombre de Titular", "Fecha", "Hora" };
			list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item, tipo);
			for (BonificacionItem i : list) {
				rows.add(Arrays.asList(
						new Object[] { i.getIdUsuario(), i.getTipo(), i.getCuentaMedioBonificacion(), i.getBanco(),
								i.getImporteFormateado(), i.getNombreTitular(), i.getFechaFormateada(), i.getHoraFormateada() }));
			}
			solicitudes=list.size();
			docu = docu + "BA-" + current.getUsername() + "-" + solicitudes + "-" + fe;
		} else if (resp.indexOf("RT") != -1) {
			tipo = new Integer[] { MedioBonificacionID.RECARGA.value() };
			headers = new String[] { "Id", "Usuario", "Tipo", "Fecha", "Importe" };
			list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item, tipo);
			for (BonificacionItem i : list) {
				rows.add(Arrays.asList(new Object[] { i.getId(), i.getNombreTitular(), i.getTipo(),
						i.getFechaFormateada(), i.getImporteFormateado() }));
			}
			solicitudes=list.size();
			docu = docu + "RT-" + current.getUsername() + "-" + solicitudes + "-" + fe;
		}

		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", docu + ".csv");
		response.setHeader(headerKey, headerValue);

		try {
			csv.writeCSV(response.getWriter(), headers, rows);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/bonificaciones-recargas", method = RequestMethod.GET)
	public String redirectionalBonificacionesRecargas(Model model) {
		Log.info("Entrando en redirectionalBonificacionesRecargas");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");
		EstadisticasBonificacionRSP rsp = estadisticasService.obtieneBonificacionesGenerales(null, null);
		model.addAttribute("totalRecargas", rsp.getTotalRecargas());

		List<BonificacionItem> list = estadisticasService
				.obtieneHistoricoBonificacionesPorTipo(new Integer[] { MedioBonificacionID.RECARGA.value() });
		model.addAttribute("list", list);

		model.addAttribute("fecha", fecha);

		Log.info("Saliendo de redirectionalBonificacionesRecargas");
		return "administrador/bonificaciones-recargas";
	}

	@RequestMapping(value = "/bonificaciones-recargas/report", method = RequestMethod.GET)
	public void reportBonificacionesRecargas(Model model, HttpServletResponse response)
			throws JRException, IOException {
		log.info("Entrando en bonificaciones-recargas report");
		
		
		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

reportService.makeReporteGeneralRecargas(response);

		Log.info("Saliendo en bonificaciones-recargas report");
	}

	@RequestMapping(value = "/bonificaciones-recargas-compañias/report", method = RequestMethod.GET)
	public void reportBonificacionesRecargasCompanias(Model model, HttpServletResponse response)
			throws JRException, IOException {
		Log.info("Entrando en bonificaciones-recargas report");

		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "recargas.csv");
		response.setHeader(headerKey, headerValue);

		List<BonificacionItem> list = estadisticasService.obtieneHistoricoBonificaciones(null);

		CSVExporter csv = new CSVExporterImpl();

		String[] headers = { "ID", "tipo", "fechaBonificacion", "horaBonificacion", "cantidadBonificacion" };

		List<List<Object>> rows = new ArrayList<>();
		for (int j = 0; j < list.size(); j++) {
			rows.add(Arrays.asList(new Object[] { list.get(j).getId(), list.get(j).getTipo(), list.get(j).getFecha(),
					list.get(j).getHoraFormateada(), list.get(j).getImporteFormateado() }));
		}
		try {
			csv.writeCSV(response.getWriter(), headers, rows);

		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.info("Saliendo en bonificaciones-recargas report");
	}

//	@RequestMapping(value = "/bonificaciones-depositos-detalle/{fecha}", method = RequestMethod.GET)
//	public String getObtenerDepositosDetalle(Model model, @PathVariable String fecha) {

	@RequestMapping(value = "/bonificaciones-recargas-detalle/{fecha}", method = RequestMethod.GET)
	public String redirectionalBonificacionesRecargasDetalle(Model model, @PathVariable String fecha) {
		Log.info("Entrando en redirectionalBonificacionesRecargas");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

//		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");
//		EstadisticasBonificacionRSP rsp = estadisticasService.obtieneBonificacionesGenerales(null, null);
//		model.addAttribute("totalRecargas", rsp.getTotalRecargas());

//		List<BonificacionItem> list = estadisticasService.obtieneHistoricoBonificacionesPorTipo( new Integer[] {3} );
//		model.addAttribute("list", list);
		// "2020-01-28"

		Date d = Utils.formatStringToDate(fecha, "dd-MMM-yyyy");
		String f = Utils.formatDateToString(d, "yyyy-MM-dd");

		BonificacionItem item = new BonificacionItem();
		item.setFechaFormateada(f);
		List<BonificacionItem> list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item,
				new Integer[] { MedioBonificacionID.RECARGA.value() });
		model.addAttribute("list", list);

		model.addAttribute("fecha", fecha);

		Log.info("Saliendo de redirectionalBonificacionesRecargasDetalle");
		return "administrador/bonificaciones-recargas-detalle";
	}

	@RequestMapping(value = "/bonificaciones-recargas-detalle/report/{fecha}", method = RequestMethod.GET)
	public void obtieneReporteRecargasDetalle(Model model, HttpServletResponse response, @PathVariable String fecha) {

		log.info("Entrando a obtieneReporteDepositosDetalle");
		log.debug("Entrando a obtieneReporteDepositosDetalle 2 fecha: "+fecha);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}
		
		reportService.makeReporteIndividualRecarga(fecha, response);

	}

	@RequestMapping(value = "/bonificaciones-general", method = RequestMethod.GET)
	public String redirectionalBonificacionesGeneral(Model model) {
		Log.info("Entrando en redirectionalBonificacionesGeneral");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");

		EstadisticasBonificacionRSP rsp = estadisticasService.obtieneBonificacionesGenerales(null, null);
		model.addAttribute("totalDepositos", rsp.getTotalDepositos());
		model.addAttribute("totalRecargas", rsp.getTotalRecargas());
		model.addAttribute("totalBonificaciones", rsp.getTotalBonificaciones());
		model.addAttribute("fecha", fecha);

		Log.info("Saliendo de redirectionalBonificacionesGeneral");
		return "administrador/bonificaciones-general";
	}

	@RequestMapping(value = "/bonificaciones-general/report", method = RequestMethod.GET)
	public void reportBonificacionesGeneral(Model model, HttpServletResponse response) throws JRException, IOException {
		log.info("Entrando a obtieneReporteDepositosDetalle");
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "depositos-general.csv");
		response.setHeader(headerKey, headerValue);

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		String[] headers = { "Tipo", "Fecha", "Solicitudes", "Importe" };

		List<List<Object>> rows = bonificacionesService.obtieneInfoReporteBonificacionesDepositosGeneral();

		CSVExporter csv = new CSVExporterImpl();

		try {
			csv.writeCSV(response.getWriter(), headers, rows);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private UsuarioShingShingDetailService getAuthenticationUser() {
		UsuarioShingShingDetailService user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UsuarioShingShingDetailService) {
			user = (UsuarioShingShingDetailService) principal;
		}

		return user;
	}

	/*
	 * FIN SECCION DE BONIFICACIONES
	 */
}
