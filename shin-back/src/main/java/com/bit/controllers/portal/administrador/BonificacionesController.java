package com.bit.controllers.portal.administrador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
	
	private static Logger log = LoggerFactory.getLogger( BonificacionesController.class );
	
	/*
	 * INICIO SECCION DE BONIFICACIONES
	 */
	@RequestMapping(value = "/bonificaciones-depositos", method = RequestMethod.GET)
	public String redirectionalBonificacionesDepositos(Model model) {
		Log.info("Entrando en redirectionalBonificacionesDepositos");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		//Mostrar la fecha actual en formato dd/MMM/yyyyy
		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");
		
		BonificacionItem item = new BonificacionItem();
		item.setFechaFormateada( Utils.getCurrentFormatDate("yyyy-MM-dd") );
		
		List<BonificacionItem> currentList = estadisticasService.obtieneHistoricoBonificaciones( item );
		List<BonificacionItem> list = estadisticasService.obtieneHistoricoBonificaciones( null );
		model.addAttribute("currentList", currentList);
		model.addAttribute("list", list);
 		model.addAttribute("fecha", "Hoy " + fecha);
		
		
		Log.info("Saliendo de redirectionalBonificacionesDepositos");
		return "administrador/bonificaciones-depositos";
	}
	
	@RequestMapping(value = "/bonificaciones-depositos/report", method = RequestMethod.GET)
	public void reportBonificacionesDepositos(Model model, HttpServletResponse response) throws JRException, IOException {
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                "bonificaciones-depositos.csv");
        response.setHeader(headerKey, headerValue);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}

		
		List<List<Object>> rows = bonificacionesService.obtieneInfoReporteBonificacionesDepositosGeneral();

		CSVExporter csv = new CSVExporterImpl();
		
		String [] headers = {"Tipo", "Fecha", "Solicitudes", "Importe"};
		
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
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		BonificacionItem item = new BonificacionItem();
		//La fecha viene como dd-MMM-yyyy
		//Convertir a yyyy-MM-dd
		Date d = Utils.formatStringToDate(fecha, "dd-MMM-yyyy");
		String f = Utils.formatDateToString(d, "yyyy-MM-dd");
		
		item.setFechaFormateada( f );
//		item.setIdTipo( BigInteger.valueOf( 3 ) );
		List<BonificacionItem> list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item,
				new Integer[] {MedioBonificacionID.BANCARIA.value(), MedioBonificacionID.PAYPAL.value(), MedioBonificacionID.RECARGA.value()});
		model.addAttribute("fecha", fecha);
		model.addAttribute("list", list);
		model.addAttribute("solicitudes", Utils.formatNumeros(Integer.valueOf( list.size() ).doubleValue(), "###,###,###"));
		
		double total = list.stream().mapToDouble( i -> i.getImporte() ).sum();
		
		model.addAttribute("importe", Utils.formatNumeros(total, "$###,###,###.00"));
		
		return "administrador/bonificaciones-depositos-detalle";
	}
	
	@RequestMapping(value = "/bonificaciones-depositos-detalle/report/{fecha}", method = RequestMethod.GET)
	public void obtieneReporteDepositosDetalle(Model model, HttpServletResponse response, @PathVariable String fecha) {
		
		log.info("Entrando a obtieneReporteDepositosDetalle");
		String fileName = "depositos-";
		int p = fecha.indexOf("fn");
		if( p >= 0 ) {
			fileName = fecha.substring( (p + 2), fecha.length());
			fecha = fecha.substring(0, p);
		}
		
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                fileName + ".csv");
        response.setHeader(headerKey, headerValue);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		BonificacionItem item = new BonificacionItem();
		//La fecha viene como dd-MMM-yyyy
		//Convertir a yyyy-MM-dd
		Date d = Utils.formatStringToDate(fecha, "dd-MMM-yyyy");
		String f = Utils.formatDateToString(d, "yyyy-MM-dd");
		
		item.setFechaFormateada( f );
//		item.setIdTipo( BigInteger.valueOf( 3 ) );
		List<BonificacionItem> list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item,
				new Integer[] {MedioBonificacionID.BANCARIA.value(), MedioBonificacionID.PAYPAL.value(), MedioBonificacionID.RECARGA.value()});

		CSVExporter csv = new CSVExporterImpl();
		
		String [] headers = {"Id", "Usuario", "Tipo", "Fecha", "Importe"};
		
		List<List<Object>> rows = new ArrayList<>();
		for( BonificacionItem i : list ) {			
			rows.add( Arrays.asList( new Object[] { i.getId(), i.getIdUsuario(), i.getTipo(), i.getFechaFormateada(), i.getImporte() } ) );
		}
		
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
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");
		EstadisticasBonificacionRSP rsp = estadisticasService.obtieneBonificacionesGenerales(null, null);
		model.addAttribute("totalRecargas", rsp.getTotalRecargas());
		
		List<BonificacionItem> list = estadisticasService.obtieneHistoricoBonificacionesPorTipo( new Integer[] { MedioBonificacionID.RECARGA.value() } );
		model.addAttribute("list", list);
		
		model.addAttribute("fecha", fecha);
		
		Log.info("Saliendo de redirectionalBonificacionesRecargas");
		return "administrador/bonificaciones-recargas";
	}
	
	@RequestMapping(value = "/bonificaciones-recargas/report", method = RequestMethod.GET)
	public void reportBonificacionesRecargas(Model model, HttpServletResponse response) throws JRException, IOException {
		log.info("Entrando en bonificaciones-recargas report");
		
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                "recargas-general.csv");
        response.setHeader(headerKey, headerValue);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}

		
		List<List<Object>> rows = bonificacionesService.obtieneInfoReporteRecargasGeneral();

		CSVExporter csv = new CSVExporterImpl();
		
		String [] headers = {"Dia", "Cantidad", "Compañia"};
		
		
		try {
			csv.writeCSV(response.getWriter(), headers, rows);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		Log.info("Saliendo en bonificaciones-recargas report");
	}
	
	@RequestMapping(value = "/bonificaciones-recargas-compañias/report", method = RequestMethod.GET)
	public void reportBonificacionesRecargasCompañias(Model model, HttpServletResponse response) throws JRException, IOException {
		Log.info("Entrando en bonificaciones-recargas report");
		
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                "recargas.csv");
        response.setHeader(headerKey, headerValue);
       
        List<BonificacionItem> list = estadisticasService.obtieneHistoricoBonificaciones( null );
        
		CSVExporter csv = new CSVExporterImpl();
		
		String [] headers = {"ID","tipo", "fechaBonificacion", "horaBonificacion","cantidadBonificacion"};
		
		
		List<List<Object>> rows = new ArrayList<>();
		for(int j=0;j<list.size();j++) {
			rows.add( Arrays.asList( new Object[] { list.get(j).getId(),list.get(j).getTipo(), list.get(j).getFecha(),list.get(j).getHoraFormateada(),list.get(j).getImporteFormateado() } ) );
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
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
//		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");
//		EstadisticasBonificacionRSP rsp = estadisticasService.obtieneBonificacionesGenerales(null, null);
//		model.addAttribute("totalRecargas", rsp.getTotalRecargas());
		
//		List<BonificacionItem> list = estadisticasService.obtieneHistoricoBonificacionesPorTipo( new Integer[] {3} );
//		model.addAttribute("list", list);
		//"2020-01-28"
		
		Date d = Utils.formatStringToDate(fecha, "dd-MMM-yyyy");
		String f = Utils.formatDateToString(d, "yyyy-MM-dd");
		
		BonificacionItem item = new BonificacionItem();
		item.setFechaFormateada( f );
		List<BonificacionItem> list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item, new Integer[] {MedioBonificacionID.RECARGA.value()});
		model.addAttribute("list", list);
		
		model.addAttribute("fecha", fecha);
		
		Log.info("Saliendo de redirectionalBonificacionesRecargasDetalle");
		return "administrador/bonificaciones-recargas-detalle";
	}
	
	@RequestMapping(value = "/bonificaciones-recargas-detalle/report/{fecha}", method = RequestMethod.GET)
	public void obtieneReporteRecargasDetalle(Model model, HttpServletResponse response, @PathVariable String fecha) {
		
		log.info("Entrando a obtieneReporteDepositosDetalle");
		
		String fileName = "recargas-";
		int p = fecha.indexOf("fn");
		if( p >= 0 ) {
			fileName = fecha.substring( (p + 2), fecha.length());
			fecha = fecha.substring(0, p);
		}
		
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                fileName + ".csv");
        response.setHeader(headerKey, headerValue);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		BonificacionItem item = new BonificacionItem();
		//La fecha viene como dd-MMM-yyyy
		//Convertir a yyyy-MM-dd
		Date d = Utils.formatStringToDate(fecha, "dd-MMM-yyyy");
		String f = Utils.formatDateToString(d, "yyyy-MM-dd");
		
		item.setFechaFormateada( f );
//		item.setIdTipo( BigInteger.valueOf( 3 ) );
		List<BonificacionItem> list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item,
				new Integer[] {MedioBonificacionID.RECARGA.value()});

		CSVExporter csv = new CSVExporterImpl();
		
		String [] headers = {"Id", "Compañia", "Fecha", "Hora", "Importe"};
		
		List<List<Object>> rows = new ArrayList<>();
		for( BonificacionItem i : list ) {			
			rows.add( Arrays.asList( new Object[] { i.getId(), i.getTipo(), i.getFechaFormateada(), i.getHoraFormateada(), i.getImporte() } ) );
		}
		
		try {
			csv.writeCSV(response.getWriter(), headers, rows);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/bonificaciones-general", method = RequestMethod.GET)
	public String redirectionalBonificacionesGeneral(Model model) {
		Log.info("Entrando en redirectionalBonificacionesGeneral");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
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
        String headerValue = String.format("attachment; filename=\"%s\"",
                "depositos-general.csv");
        response.setHeader(headerKey, headerValue);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		String [] headers = {"Tipo", "Fecha", "Solicitudes", "Importe"};

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
