package com.bit.controllers.portal.administrador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bit.common.Utils;
import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.ProductosTiendas;
import com.bit.model.Usuario;
import com.bit.model.dto.BonificacionItem;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.TicketItem;
import com.bit.model.dto.response.EstadisticasBonificacionRSP;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.EstadisticasRSP;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.report.MarcaReport;
import com.bit.model.report.ProductoReport;
import com.bit.model.report.UsuarioReport;
import com.bit.service.CatalogoMarcaService;
import com.bit.service.CatalogoMediosBonificacionService;
import com.bit.service.CatalogoTiendaService;
import com.bit.service.CatalogoTipoProductoService;
import com.bit.service.EstadisticasService;
import com.bit.service.ProductoService;
import com.bit.service.TicketService;
import com.bit.service.UsuarioService;
import com.bit.service.UsuarioShingShingDetailService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/portal-administrador")
public class CatalogosController {

	@Autowired
	private CatalogoTipoProductoService catalogoTipoProductoService;

	@Autowired
	private CatalogoMarcaService catalogoMarcaService;

	@Autowired
	private CatalogoMediosBonificacionService catalogoMediosBonificacionService;

	@Autowired
	private CatalogoTiendaService catalogoTiendaService;

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private EstadisticasService estadisticasService;
	
	private static Logger log = LoggerFactory.getLogger( CatalogosController.class );

	/*
	 * Seccion de formularios y tablas
	 * DEPARTAMENTOS
	 */
	@RequestMapping(value = "/departamento/save", method = RequestMethod.GET)
	public String gerCatDepartamento(Model model) {
		log.info( "Entrando en gerCatDepartamento" );
		model.addAttribute("item", new CatalogoTipoProducto());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de gerCatDepartamento" );
		
		return "cat_departamentos";
	}

	@RequestMapping(value = "/departamento/save", method = RequestMethod.POST)
	public String postCatDepartamento(@RequestParam MultipartFile file, @ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatDepartamento" );
		catalogoTipoProductoService.registrarCatalogoTipoProductos(file, item);
		
		log.info( "Saliendo de postCatDepartamento" );

		return "redirect:/portal-administrador/departamento/list";
	}
	
	@RequestMapping(value = "/departamento/edit/{id}", method = RequestMethod.GET)
	public String getCatDepartamentoEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatDepartamentoEdit" );
		CatalogoTipoProducto item = catalogoTipoProductoService.findById(Long.parseLong(id));
		model.addAttribute("item", item);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatDepartamentoEdit" );

//		return "redirect:/portal-administrador/departamento/save";
		return "cat_departamentos";
	}
	
	@RequestMapping(value = "/departamento/edit/{id}", method = RequestMethod.POST)
	public String postCatDepartamentoEdit(@RequestParam MultipartFile file, @ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model, @PathVariable String id) {
		
		log.info( "Entrando en postCatDepartamentoEdit" );
		item.setIdCatalogoTipoProducto( Long.parseLong(id) );
		SimpleResponse rsp = catalogoTipoProductoService.actualizarCatalogoTipoProductos(file, item);
		
		log.info( "Saliendo de postCatDepartamentoEdit" );

		return "redirect:/portal-administrador/departamento/list";
	}
	
	@RequestMapping(value = "/departamento/delete/{id}", method = RequestMethod.POST)
	public String postCatDepartamentoDelete(@RequestParam MultipartFile file, @ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model, @PathVariable String id) {
		
		log.info( "Entrando en postCatDepartamentoDelete" );
		item.setIdCatalogoTipoProducto( Long.parseLong(id) );
		SimpleResponse rsp = catalogoTipoProductoService.actualizarCatalogoTipoProductos(file, item);
		
		log.info( "Saliendo de postCatDepartamentoDelete" );

		return "redirect:/portal-administrador/departamento/list";
	}

	/*
	 * MARCA
	 */
	@RequestMapping(value = "/marca/save", method = RequestMethod.GET)
	public String getCatMarca(Model model) {

		log.info( "Entrando en getCatMarca" );
		model.addAttribute("item", new CatalogoMarca());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatMarca" );

		return "catalogo_marca";
	}
	
	@RequestMapping(value = "/marca/report", method = RequestMethod.GET)
	@ResponseBody
	public void getMarcaReport(Model model, HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/Marcas.jasper");
	    Map<String,Object> params = new HashMap<>();
	    List<MarcaReport> list = new ArrayList<>();
	    MarcaReport p = new MarcaReport( 1L, "Comex" );
	    MarcaReport p1 = new MarcaReport( 1L, "Herdez" );
	    MarcaReport p2 = new MarcaReport( 1L, "Bonafont" );
	    
	    list.add(p);
	    list.add(p1);
	    list.add(p2);
	    
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=Marcas.pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
	}

	@RequestMapping(value = "/marca/save", method = RequestMethod.POST)
	public String postCatMarca(@RequestParam MultipartFile file, @ModelAttribute CatalogoMarca item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatMarca" );
		catalogoMarcaService.registrarMarcas(file, item);

		log.info( "Saliendo de postCatMarca" );
		return "redirect:/portal-administrador/marca/list";
	}
	
	@RequestMapping(value = "/marca/edit/{id}", method = RequestMethod.GET)
	public String getCatMarcaEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatMarcaEdit" );
		CatalogoMarca item = catalogoMarcaService.findById(Long.parseLong(id));
		model.addAttribute("item", item);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatMarcaEdit" );

		return "catalogo_marca";
	}
	
	@RequestMapping(value = "/marca/edit/{id}", method = RequestMethod.POST)
	public String postCatMarcaEdit(@RequestParam MultipartFile file, @ModelAttribute CatalogoMarca item, BindingResult errors, Model model, @PathVariable String id) {
		
		log.info( "Entrando en postCatMarcaEdit" );
		item.setIdCatalogoMarca( Long.parseLong(id) );
		SimpleResponse rsp = catalogoMarcaService.actualizarMarcas(file, item);

		log.info( "Saliendo de postCatMarcaEdit" );
		
		return "redirect:/portal-administrador/marca/list";
	}

	/*
	 * MEDIOS DE BONIFICACION
	 */
	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.GET)
	public String getCatMedioBonificacion(Model model) {

		log.info( "Entrando en getCatMedioBonificacion" );
		model.addAttribute("item", new CatalogoMediosBonificacion());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatMedioBonificacion" );

		return "catalogo_medios_bonificacion";
	}

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.POST)
	public String postCatMedioBonificacion(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatMarcaEdit" );
		catalogoMediosBonificacionService.registrarCatalogoMediosBonificacion(item);
		
		log.info( "Saliendo de postCatMarcaEdit" );

		return "redirect:/portal-administrador/mediosBonificacion/list";
	}
	
	@RequestMapping(value = "/medioBonificacion/edit/{id}", method = RequestMethod.GET)
	public String getCatMedioBonificacionEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatMedioBonificacionEdit" );
		CatalogoMediosBonificacion item = catalogoMediosBonificacionService.findById(Long.parseLong(id));
		model.addAttribute("item", item);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatMedioBonificacionEdit" );

		return "catalogo_medios_bonificacion";
	}
	
	@RequestMapping(value = "/medioBonificacion/edit/{id}", method = RequestMethod.POST)
	public String postCatMedioBonificacionEdit(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model, @PathVariable String id) {
		
		log.info( "Entrando en postCatMedioBonificacionEdit" );
		item.setIdCatalogoMedioBonificacion( Long.parseLong(id) );
		SimpleResponse rsp = catalogoMediosBonificacionService.actualizarCatalogoMediosBonificacion(item);
		
		log.info( "Saliendo de postCatMedioBonificacionEdit" );

		return "redirect:/portal-administrador/mediosBonificacion/list";
	}
	

	/*
	 * TIENDA
	 */
	@RequestMapping(value = "tienda/save", method = RequestMethod.GET)
	public String getCatTienda(Model model) {

		log.info( "Entrando en getCatTienda" );
		model.addAttribute("item", new CatalogoTienda());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatTienda" );

		return "catalogo_tienda";
	}

	@RequestMapping(value = "/tienda/save", method = RequestMethod.POST)
	public String postCatTienda(@RequestParam MultipartFile file, @ModelAttribute CatalogoTienda item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatTienda" );
		catalogoTiendaService.registrarTiendas(file, item);
		
		log.info( "Saliendo de postCatTienda" );

//		return "redirect:/portal-administrador/tienda/save";
		return "redirect:/portal-administrador/tienda/list";
	}
	
	@RequestMapping(value = "/tienda/edit/{id}", method = RequestMethod.GET)
	public String getCatTiendaEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatTiendaEdit" );
		CatalogoTienda item = catalogoTiendaService.findById(Long.parseLong(id));
		model.addAttribute("item", item);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatTiendaEdit" );

		return "catalogo_tienda";
	}
	
	@RequestMapping(value = "/tienda/edit/{id}", method = RequestMethod.POST)
	public String postCatTiendaEdit(@RequestParam MultipartFile file, @ModelAttribute CatalogoTienda item, BindingResult errors, Model model, @PathVariable String id) {
		
		log.info( "Entrando en postCatTiendaEdit" );
		item.setIdCatalogoTienda( Long.parseLong(id) );
		SimpleResponse rsp = catalogoTiendaService.actualizarTiendas(file, item);
		
		log.info( "Saliendo de postCatTiendaEdit" );

		return "redirect:/portal-administrador/tienda/list";
	}

	/*
	 * PRODUCTOS
	 */
	@RequestMapping(value = "/producto/save", method = RequestMethod.GET)
	public String getCatProducto(Model model) {

		log.info( "Entrando en getCatProducto" );
		Producto item = new Producto();
		
		
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());
		
		List<CatalogoTienda> list = catalogoTiendaService.getCatalogoTienda().getTiendas();
		
		model.addAttribute("catalogoTiendas", list);
		
		List<ProductosTiendas> nombres = new ArrayList<>();
		for( CatalogoTienda c : list ) {
			ProductosTiendas tmp = new ProductosTiendas();
			tmp.setCatalogoTienda( c );
			nombres.add( tmp );
		}
		item.setTiendas(nombres);
		model.addAttribute("nombres", nombres);
		model.addAttribute("item", item);
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatProducto" );

		return "producto";
	}
	
	@RequestMapping(value = "/producto/report", method = RequestMethod.GET)
	@ResponseBody
	public void getProductoReport(Model model, HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/Productos1.2.jasper");
	    Map<String,Object> params = new HashMap<>();
	    List<ProductoReport> list = new ArrayList<>();
//	    ProductoReport p = new ProductoReport( 1L, "SN:098713123123", "Switch", "Nintendo", true );
//	    ProductoReport p1 = new ProductoReport( 1L, "SN:098713123123", "Switch", "Nintendo", true );
//	    ProductoReport p2 = new ProductoReport( 1L, "SN:098713123123", "Switch", "Nintendo", true );
//	    
//	    list.add(p);
//	    list.add(p1);
//	    list.add(p2);
	    
	    list = productoService.getAllProductoReport();
	    
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

	    
	    //For PDF 
	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=Productos.pdf");

//	    String fileName = "productos.xls";
//	    response.setContentType("application/octet-stream");
//	    response.setHeader("Connection", "close");
//	    response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(fileName.getBytes("utf-8"),"ISO-8859-1") + "\"");
	    
	    final OutputStream outStream = response.getOutputStream();
	    
//	    JRXlsExporter xlsExporter = new JRXlsExporter();
//	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
//	    xlsExporter.setExporterInput( new SimpleExporterInput(jasperPrint) );
//	    xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));
//	    xlsExporter.setParameter(JRExporterParameter.INPUT_FILE_NAME,
//	    		jasperPrint);
//	    xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
//	               "C://sample_report.xls");
	    

//	    xlsExporter.exportReport();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
	}

	@RequestMapping(value = "/producto/save", method = RequestMethod.POST)
	public String postCatProducto(@RequestParam MultipartFile file, @ModelAttribute Producto item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatProducto" );
		productoService.registrarProductos(file, item);
		
		log.info( "Saliendo de postCatProducto" );

		return "redirect:/portal-administrador/producto/list";
	}
	
	@RequestMapping(value = "/producto/edit/{id}", method = RequestMethod.GET)
	public String getCatProductoEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatProductoEdit" );
		Producto item = productoService.findById(Long.parseLong(id));
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());
		
		List<CatalogoTienda> list = catalogoTiendaService.getCatalogoTienda().getTiendas();
		
		model.addAttribute("catalogoTiendas", list);
		
		List<ProductosTiendas> nombres = new ArrayList<>();
		for( CatalogoTienda c : list ) {
			ProductosTiendas tmp = new ProductosTiendas();
			tmp.setCatalogoTienda( c );
			nombres.add( tmp );
		}
		
		if( item.getTiendas().isEmpty() ) {
			item.setTiendas(nombres);
		}
		
		model.addAttribute("nombres", nombres);
		model.addAttribute("item", item);
		
//		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());
		
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("user", current.getUsuario());
		}
		
		log.info( "Saliendo de getCatProductoEdit" );

		return "producto";
	}
	
	@RequestMapping(value = "/producto/edit/{id}", method = RequestMethod.POST)
	public String postCatProductoEdit(@RequestParam MultipartFile file, @ModelAttribute Producto item, BindingResult errors, Model model, @PathVariable String id) {
		
		log.info( "Entrando en postCatProductoEdit" );
		item.setIdProducto( Long.parseLong(id) );
		SimpleResponse rsp = productoService.actualizarProductos(file, item);

		log.info( "Saliendo de postCatProductoEdit" );
		
		return "redirect:/portal-administrador/producto/list";
	}
	
	
	/*
	 * SECCION DE TABLAS
	 */
	@RequestMapping(value = "/mediosBonificacion/list", method = RequestMethod.GET)
	public String redireccionaListaMediosBonificacion(Model model) {
		log.info( "Entrando en redireccionaListaMediosBonificacion" );
		model.addAttribute("medios", catalogoMediosBonificacionService.getCatalogoMediosBonificacion().getMediosBonificacion());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		log.info( "Saliendo de redireccionaListaMediosBonificacion" );
		return "lista_mediosBonificacion";
	}
	
	@RequestMapping(value = "/marca/list", method = RequestMethod.GET)
	public String redirectionaListaMarca(Model model) {
		log.info( "Entrando en redirectionaListaMarca" );
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		log.info( "Saliendo de redirectionaListaMarca" );
		return "lista_marcas";
	}

	@RequestMapping(value = "/tienda/list", method = RequestMethod.GET)
	public String redirectionaListaTienda(Model model) {
		log.info( "Entrando en redirectionaListaTienda" );
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		log.info( "Saliendo de redirectionaListaTienda" );
		return "lista_tiendas";
	}
	
	@RequestMapping(value = "/departamento/list", method = RequestMethod.GET)
	public String redirectionaListaDepartamento(Model model) {
		log.info( "Entrando en redirectionaListaDepartamento" );
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		log.info( "Saliendo de redirectionaListaDepartamento" );
		return "lista_departamentos";
	}
	
	@RequestMapping(value = "/producto/list", method = RequestMethod.GET)
	public String redirectionaListaProducto(Model model) {
		log.info( "Entrando en redirectionaListaProducto" );
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		model.addAttribute("productos", productoService.getProductos().getProductos());
		
		log.info( "Saliendo de redirectionaListaProducto" );
		return "lista_productos";
	}
	
	@RequestMapping(value = "/usuario/report", method = RequestMethod.GET)
	@ResponseBody
	public void getUsuarioReport(Model model, HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/Usuarios.jasper");
	    Map<String,Object> params = new HashMap<>();
	    List<UsuarioReport> list = new ArrayList<>();
	    UsuarioReport p = new UsuarioReport( 1L, "Comex", "", "" );
	    UsuarioReport p1 = new UsuarioReport( 1L, "Herdez", "", "" );
	    UsuarioReport p2 = new UsuarioReport( 1L, "Bonafont", "", "" );
	    
	    list.add(p);
	    list.add(p1);
	    list.add(p2);
	    
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=Usuarios.pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
	}
	
	private UsuarioShingShingDetailService getAuthenticationUser() {
		UsuarioShingShingDetailService user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UsuarioShingShingDetailService) {
			user = (UsuarioShingShingDetailService) principal;
		}
		
		return user;
	}
	
	//setting para pruebas en los html
	@RequestMapping(value = "/estadisticas-tickets", method = RequestMethod.GET)
	public String redirectionalEstadisticasTickets(Model model) {
		Log.info("Entrando en redirectionalEstadisticasTicket");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		EstadisticasRSP rsp = estadisticasService.obtieneEstadisticasTickets();
		model.addAttribute("totalTickets", rsp.getTotalTickets());
		model.addAttribute("tickets", rsp.getHistoricoTickets());
		
		Log.info("Saliendo de redirectionalEstadisticasTickets");
		return "administrador/estadisticas-tickets";
	}
	
	@RequestMapping(value = "/estadisticas-tickets-detalle/{fecha}", method = RequestMethod.GET)
	public String redirectionalEstadisticasTicketsDetalle(Model model, @PathVariable String fecha) {
		Log.info("Entrando en redirectionalEstadisticasTicket");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		List<TicketItem> list = estadisticasService.obtieneTicketsPorFecha( fecha );
		model.addAttribute("fecha", fecha);
		model.addAttribute("tickets", list);
		
		Log.info("Saliendo de redirectionalEstadisticasTickets");
		return "administrador/estadisticas-tickets-detalle";
	}
	
	@RequestMapping(value = "/estadisticas-tickets-detalle/ticket/{id}", method = RequestMethod.GET)
	public String redirectionalEstadisticasTicketsDetalleSegundoDetalle(Model model, @PathVariable String id) {
		Log.info("Entrando en redirectionalEstadisticasTicket");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		List<TicketItem> list = estadisticasService.obtieneDetalleTicketPorId( Integer.parseInt(id) );
		Integer total = list.stream().map( x -> x.getCantidad().intValue() ).reduce( 0, Integer::sum );
		Double importeTotal = list.stream().map( x -> x.getImporte() ).reduce( 0.0, Double::sum );
		String strImporteTotal = Utils.formatNumeros(importeTotal, "$###,###,###.00");
		
		model.addAttribute("id", id);
		model.addAttribute("tickets", list);
		model.addAttribute("total", total);
		model.addAttribute("importeTotal", strImporteTotal);
		
		Log.info("Saliendo de redirectionalEstadisticasTickets");
		return "administrador/estadisticas-tickets-detalle-segundoDetalle";
	}
	
	@RequestMapping(value = "/estadisticas-general", method = RequestMethod.GET)
	public String redirectionalEstadisticasGeneral(Model model) {
		Log.info("Entrando en redirectionalEstadisticasGeneral");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasGeneral();
		model.addAttribute("totalUsuarios", rsp.getTotalUsuarios());
		model.addAttribute("totalTickets", rsp.getTotalTicketsEscaneados());
		model.addAttribute("totalProductosEscaneados", rsp.getTotalProductosEscaneados());
		
		Log.info("Saliendo de redirectionalEstadisticasGeneral");
		return "administrador/estadisticas-general";
	}
	
	@RequestMapping(value = "/estadisticas-marcas", method = RequestMethod.GET)
	public String redirectionalEstadisticasMarcas(Model model) {
		Log.info("Entrando en redirectionalEstadisticasMarcas");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasMarcas();
		model.addAttribute("totalMarcas", rsp.getTotalMarcas());
		model.addAttribute("totalProductos", rsp.getTotalProductos());
		model.addAttribute("totalProductosEscaneados", rsp.getTotalProductosEscaneados());
		model.addAttribute("listaResumenTiendas", rsp.getListaResumenTiendas());
		model.addAttribute("listaResumenDepartamentos", rsp.getListaResumenDepartamentos());
		
		Log.info("Saliendo de redirectionalEstadisticasMarcas");
		return "administrador/estadisticas-marcas";
	}
	
	@RequestMapping(value = "/estadisticas-usuarios", method = RequestMethod.GET)
	public String redirectionalEstadisticasUsuarios(Model model) {
		Log.info("Entrando en redirectionalEstadisticasUsuarios");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		ListItemsRSP usuarios = usuarioService.getUsuarios();
		EstadisticasRSP rsp = estadisticasService.obtieneEstadisticasUsuarios();
		model.addAttribute("totalUsuarios", rsp.getTotalUsuarios());
		model.addAttribute("promedioEdad", rsp.getPromedioEdadUsuarios());
		model.addAttribute("listaUsuarios", usuarios.getUsuarios());
		
		Log.info("Saliendo de redirectionalEstadisticasUsuarios");
		return "administrador/estadisticas-usuarios";
	}
	
	@RequestMapping(value = "/usuario-detalle/{id}", method = RequestMethod.GET)
	public String getObtenerUsuarioDetalle(Model model, @PathVariable String id) {
		
		log.info("Entrando a getObtenerUsuarioDetalle");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		Usuario item = new Usuario();
		item.setIdUsuario( Long.parseLong(id) );
		InformacionUsuarioRSP rsp = usuarioService.obtieneDetalleUsuario(item);
		model.addAttribute("rsp", rsp);
		
		return "administrador/usuario-detalle";
	}
	
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
		item.setIdTipo( BigInteger.valueOf( 3 ) );
		List<BonificacionItem> list = estadisticasService.obtieneDetalleHistoricoBonificacionesPorFechaYTipo(item);
		model.addAttribute("list", list);
		model.addAttribute("solicitudes", Utils.formatNumeros(Integer.valueOf( list.size() ).doubleValue(), "###,###,###"));
		
		double total = list.stream().mapToDouble( i -> i.getImporte() ).sum();
		
		model.addAttribute("importe", Utils.formatNumeros(total, "$###,###,###.00"));
		
		return "administrador/bonificaciones-depositos-detalle";
	}
	
	@RequestMapping(value = "/bonificaciones-recargas", method = RequestMethod.GET)
	public String redirectionalBonificacionesRecargas(Model model) {
		Log.info("Entrando en redirectionalBonificacionesRecargas");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		EstadisticasBonificacionRSP rsp = estadisticasService.obtieneBonificacionesGenerales(null, null);
		model.addAttribute("totalRecargas", rsp.getTotalRecargas());
		
		List<BonificacionItem> list = estadisticasService.obtieneHistoricoBonificacionesPorTipo( new Integer[] {3} );
		model.addAttribute("list", list);
		
		Log.info("Saliendo de redirectionalBonificacionesRecargas");
		return "administrador/bonificaciones-recargas";
	}
	
	@RequestMapping(value = "/bonificaciones-general", method = RequestMethod.GET)
	public String redirectionalBonificacionesGeneral(Model model) {
		Log.info("Entrando en redirectionalBonificacionesGeneral");
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			model.addAttribute("item", current.getUsuario());
		}
		
		EstadisticasBonificacionRSP rsp = estadisticasService.obtieneBonificacionesGenerales(null, null);
		model.addAttribute("totalDepositos", rsp.getTotalDepositos());
		model.addAttribute("totalRecargas", rsp.getTotalRecargas());
		model.addAttribute("totalBonificaciones", rsp.getTotalBonificaciones());
		
		Log.info("Saliendo de redirectionalBonificacionesGeneral");
		return "administrador/bonificaciones-general";
	}
}
