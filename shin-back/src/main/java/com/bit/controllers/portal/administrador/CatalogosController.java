package com.bit.controllers.portal.administrador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.TicketItem;
import com.bit.model.dto.response.EstadisticasGeneralRSP;
import com.bit.model.dto.response.EstadisticasRSP;
import com.bit.model.dto.response.InformacionUsuarioRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.report.ProductoReport;
import com.bit.model.report.UsuarioReport;
import com.bit.service.CSVExporter;
import com.bit.service.CatalogoMarcaService;
import com.bit.service.CatalogoMediosBonificacionService;
import com.bit.service.CatalogoTiendaService;
import com.bit.service.CatalogoTipoProductoService;
import com.bit.service.EstadisticasService;
import com.bit.service.ProductoService;
import com.bit.service.ReportService;
import com.bit.service.TicketService;
import com.bit.service.UsuarioService;
import com.bit.service.UsuarioShingShingDetailService;
import com.bit.service.impl.CSVExporterImpl;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

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
	private ReportService reportService;

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

	private static Logger log = LoggerFactory.getLogger(CatalogosController.class);

	/*
	 * Seccion de formularios y tablas DEPARTAMENTOS
	 */
	@RequestMapping(value = "/departamento/save", method = RequestMethod.GET)
	public String gerCatDepartamento(Model model) {
		log.info("Entrando en gerCatDepartamento");
		model.addAttribute("item", new CatalogoTipoProducto());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de gerCatDepartamento");

		return "cat_departamentos";
	}

	@RequestMapping(value = "/departamento/save", method = RequestMethod.POST)
	public String postCatDepartamento(@RequestParam MultipartFile file, @ModelAttribute CatalogoTipoProducto item,
			BindingResult errors, Model model) {

		log.info("Entrando en postCatDepartamento");
		catalogoTipoProductoService.registrarCatalogoTipoProductos(file, item);

		log.info("Saliendo de postCatDepartamento");

		return "redirect:/portal-administrador/departamento/list?id=1&type=1";
	}

	@RequestMapping(value = "/departamento/edit/{id}", method = RequestMethod.GET)
	public String getCatDepartamentoEdit(Model model, @PathVariable String id) {

		log.info("Entrando en getCatDepartamentoEdit");
		CatalogoTipoProducto item = catalogoTipoProductoService.findById(Long.parseLong(id));
		model.addAttribute("item", item);

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de getCatDepartamentoEdit");

//		return "redirect:/portal-administrador/departamento/save";
		return "cat_departamentos";
	}

	@RequestMapping(value = "/departamento/delete/{id}", method = RequestMethod.GET)
	public String deleteCatDepartamento(Model model, @PathVariable String id) {
		log.info("Entrando a deleteCatDepartamento " + id);
		CatalogoTipoProducto item = catalogoTipoProductoService.findById(Long.parseLong(id));
		SimpleResponse rsp = catalogoTipoProductoService.eliminaCatalogoTipoProductos(item);
		log.info("saliendo a deleteCatDepartamento " + id);
		return "redirect:/portal-administrador/departamento/list?id=" + id + "&type=0";
	}

	@RequestMapping(value = "/departamento/edit/{id}", method = RequestMethod.POST)
	public String postCatDepartamentoEdit(@RequestParam MultipartFile file, @ModelAttribute CatalogoTipoProducto item,
			BindingResult errors, Model model, @PathVariable String id) {

		log.info("Entrando en postCatDepartamentoEdit");
		item.setIdCatalogoTipoProducto(Long.parseLong(id));
		SimpleResponse rsp = catalogoTipoProductoService.actualizarCatalogoTipoProductos(file, item);

		log.info("Saliendo de postCatDepartamentoEdit");

		return "redirect:/portal-administrador/departamento/list?id=" + id + "&type=1";
	}

	@RequestMapping(value = "/departamento/delete/{id}", method = RequestMethod.POST)
	public String postCatDepartamentoDelete(@RequestParam MultipartFile file, @ModelAttribute CatalogoTipoProducto item,
			BindingResult errors, Model model, @PathVariable String id) {

		log.info("Entrando en postCatDepartamentoDelete");
		item.setIdCatalogoTipoProducto(Long.parseLong(id));
		SimpleResponse rsp = catalogoTipoProductoService.actualizarCatalogoTipoProductos(file, item);

		log.info("Saliendo de postCatDepartamentoDelete");

		return "redirect:/portal-administrador/departamento/list?id=" + id + "&type=0";
	}

	/*
	 * MARCA
	 */
	@RequestMapping(value = "/marca/save", method = RequestMethod.GET)
	public String getCatMarca(Model model) {

		log.info("Entrando en getCatMarca");
		model.addAttribute("item", new CatalogoMarca());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de getCatMarca");

		return "catalogo_marca";
	}

	@RequestMapping(value = "/marca/save", method = RequestMethod.POST)
	public String postCatMarca(@RequestParam MultipartFile file, @ModelAttribute CatalogoMarca item,
			BindingResult errors, Model model) {

		log.info("Entrando en postCatMarca");
		catalogoMarcaService.registrarMarcas(file, item);

		log.info("Saliendo de postCatMarca");
		return "redirect:/portal-administrador/marca/list?id=1&type=1";
	}

	@RequestMapping(value = "/marca/edit/{id}", method = RequestMethod.GET)
	public String getCatMarcaEdit(Model model, @PathVariable String id) {

		log.info("Entrando en getCatMarcaEdit");
		CatalogoMarca item = catalogoMarcaService.findById(Long.parseLong(id));
		model.addAttribute("item", item);

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de getCatMarcaEdit");

		return "catalogo_marca";
	}

	// Si hay que eliminar la imagen de la marca agregar metodo sin file, agregar
	// codigo y comentar el anterior
	@RequestMapping(value = "/marca/edit/{id}", method = RequestMethod.POST)
	public String postCatMarcaEdit(@RequestParam MultipartFile file, @ModelAttribute CatalogoMarca item,
			BindingResult errors, Model model, @PathVariable String id) {

		log.info("Entrando en postCatMarcaEdit");
		item.setIdCatalogoMarca(Long.parseLong(id));
		SimpleResponse rsp = catalogoMarcaService.actualizarMarcas(file, item);

		log.info("Saliendo de postCatMarcaEdit");

		return "redirect:/portal-administrador/marca/list?id=" + id + "&type=1";
	}

	@RequestMapping(value = "/marca/delete/{id}", method = RequestMethod.GET)
	public String deleteMarca(Model model, @PathVariable String id) {
		log.info("Entrando a deleteMarcas " + id);
		CatalogoMarca item = catalogoMarcaService.findById(Long.parseLong(id));
		SimpleResponse rsp = catalogoMarcaService.eliminaMarcas(item);
		log.info("saliendo a deleteMarcas " + id);
		return "redirect:/portal-administrador/marca/list?id=" + id + "&type=0";
	}

	/*
	 * MEDIOS DE BONIFICACION
	 */
	@RequestMapping(value = "/medioBonificacion/delete/{id}", method = RequestMethod.GET)
	public String deletemedioBonificacion(Model model, @PathVariable String id) {
		log.info("Entrando a deleteMarcas " + id);
		CatalogoMediosBonificacion item = catalogoMediosBonificacionService.findById(Long.parseLong(id));
		SimpleResponse rsp = catalogoMediosBonificacionService.eliminaMedioBonificacion(item);
		log.info("saliendo a deleteMarcas " + id);
		return "redirect:/portal-administrador/mediosBonificacion/list?id=" + id + "&type=0";
	}

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.GET)
	public String getCatMedioBonificacion(Model model) {

		log.info("Entrando en getCatMedioBonificacion");
		model.addAttribute("item", new CatalogoMediosBonificacion());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de getCatMedioBonificacion");

		return "catalogo_medios_bonificacion";
	}

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.POST)
	public String postCatMedioBonificacion(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors,
			Model model) {

		log.info("Entrando en postCatMarcaEdit");
		catalogoMediosBonificacionService.registrarCatalogoMediosBonificacion(item);

		log.info("Saliendo de postCatMarcaEdit");

		return "redirect:/portal-administrador/mediosBonificacion/list?id=1&type=1";
	}

	@RequestMapping(value = "/medioBonificacion/edit/{id}", method = RequestMethod.GET)
	public String getCatMedioBonificacionEdit(Model model, @PathVariable String id) {

		log.info("Entrando en getCatMedioBonificacionEdit");
		CatalogoMediosBonificacion item = catalogoMediosBonificacionService.findById(Long.parseLong(id));
		model.addAttribute("item", item);

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de getCatMedioBonificacionEdit");

		return "catalogo_medios_bonificacion";
	}

	@RequestMapping(value = "/medioBonificacion/edit/{id}", method = RequestMethod.POST)
	public String postCatMedioBonificacionEdit(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors,
			Model model, @PathVariable String id) {

		log.info("Entrando en postCatMedioBonificacionEdit");
		item.setIdCatalogoMedioBonificacion(Long.parseLong(id));
		SimpleResponse rsp = catalogoMediosBonificacionService.actualizarCatalogoMediosBonificacion(item);

		log.info("Saliendo de postCatMedioBonificacionEdit");

		return "redirect:/portal-administrador/mediosBonificacion/list?id=" + id + "&type=1";
	}

	/*
	 * TIENDA
	 */
	@RequestMapping(value = "tienda/save", method = RequestMethod.GET)
	public String getCatTienda(Model model) {

		log.info("Entrando en getCatTienda");
		model.addAttribute("item", new CatalogoTienda());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de getCatTienda");

		return "catalogo_tienda";
	}

	@RequestMapping(value = "/tienda/save", method = RequestMethod.POST)
	public String postCatTienda(@RequestParam MultipartFile file, @ModelAttribute CatalogoTienda item,
			BindingResult errors, Model model) {

		log.info("Entrando en postCatTienda");
		catalogoTiendaService.registrarTiendas(file, item);

		log.info("Saliendo de postCatTienda");

//		return "redirect:/portal-administrador/tienda/save";
		return "redirect:/portal-administrador/tienda/list?id=1&type=1";
	}

	@RequestMapping(value = "/tienda/edit/{id}", method = RequestMethod.GET)
	public String getCatTiendaEdit(Model model, @PathVariable String id) {

		log.info("Entrando en getCatTiendaEdit");
		CatalogoTienda item = catalogoTiendaService.findById(Long.parseLong(id));
		model.addAttribute("item", item);

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de getCatTiendaEdit");

		return "catalogo_tienda";
	}

	@RequestMapping(value = "/tienda/edit/{id}", method = RequestMethod.POST)
	public String postCatTiendaEdit(@RequestParam MultipartFile file, @ModelAttribute CatalogoTienda item,
			BindingResult errors, Model model, @PathVariable String id) {

		log.info("Entrando en postCatTiendaEdit");
		item.setIdCatalogoTienda(Long.parseLong(id));
		SimpleResponse rsp = catalogoTiendaService.actualizarTiendas(file, item);

		log.info("Saliendo de postCatTiendaEdit");

		return "redirect:/portal-administrador/tienda/list?id=" + id + "&type=1";
	}

	@RequestMapping(value = "/tienda/delete/{id}", method = RequestMethod.GET)
	public String deleteCatTienda(Model model, @PathVariable String id) {
		log.info("Entrando a deleteCatTienda " + id);
		CatalogoTienda item = catalogoTiendaService.findById(Long.parseLong(id));
		SimpleResponse rsp = catalogoTiendaService.eliminaCatalogoTienda(item);
		log.info("saliendo a deleteCatTienda " + id);
		return "redirect:/portal-administrador/tienda/list?id=" + id + "&type=0";
	}

	/*
	 * PRODUCTOS
	 */
	@RequestMapping(value = "/producto/save", method = RequestMethod.GET)
	public String getCatProducto(Model model) {

		log.info("Entrando en getCatProducto");
		Producto item = new Producto();

		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());

		List<CatalogoTienda> list = catalogoTiendaService.getCatalogoTienda().getTiendas();

		model.addAttribute("catalogoTiendas", list);

		List<ProductosTiendas> nombres = new ArrayList<>();
		for (CatalogoTienda c : list) {
			ProductosTiendas tmp = new ProductosTiendas();
			tmp.setCatalogoTienda(c);
			nombres.add(tmp);
		}
		item.setTiendas(nombres);
		model.addAttribute("nombres", nombres);
		model.addAttribute("item", item);

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de getCatProducto");

		return "producto";
	}

	@RequestMapping(value = "/producto/report", method = RequestMethod.GET)
	@ResponseBody
	public void getProductoReport(Model model, HttpServletResponse response) throws JRException, IOException {
		log.info("Entrando report");

		List<ProductoReport> list = new ArrayList<>();
		Date fechaActual = new Date();
	   	DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	   	String fecha=formatoFecha.format(fechaActual);
	   	String nombreDocumento="SS-Productos-"+fecha+".csv";
		list = productoService.getAllProductoReport();
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", nombreDocumento);
		response.setHeader(headerKey, headerValue);

		CSVExporter csv = new CSVExporterImpl();

		String[] headers = { "Producto", "Contenido", "Marca", "Dpto", "Tipo" , "Bonificaci�n" };

		List<List<Object>> rows = new ArrayList<>();
		for (int j = 0; j < list.size(); j++) {
			rows.add(Arrays.asList(new Object[] { list.get(j).getNombreProducto(), list.get(j).getContenido(),
					 list.get(j).getMarca(),list.get(j).getDepto(), list.get(j).getTipo(),list.get(j).getBonificacion() }));
		}
		try {
			csv.writeCSV(response.getWriter(), headers, rows);

		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("Saliendo report");
	}

	@RequestMapping(value = "/producto/save", method = RequestMethod.POST)
	public String postCatProducto(@RequestParam MultipartFile file, @ModelAttribute Producto item, BindingResult errors,
			Model model) {

		log.info("Entrando en postCatProducto");
		productoService.registrarProductos(file, item);

		log.info("Saliendo de postCatProducto");

		return "redirect:/portal-administrador/producto/list?id=1&type=1";
	}

	@RequestMapping(value = "/producto/edit/{id}", method = RequestMethod.GET)
	public String getCatProductoEdit(Model model, @PathVariable String id) {

		log.info("Entrando en getCatProductoEdit");
		Producto item = productoService.findById(Long.parseLong(id));
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());

		List<CatalogoTienda> list = catalogoTiendaService.getCatalogoTienda().getTiendas();

		model.addAttribute("catalogoTiendas", list);

		List<ProductosTiendas> nombres = new ArrayList<>();
		for (CatalogoTienda c : list) {
			ProductosTiendas tmp = new ProductosTiendas();
			tmp.setCatalogoTienda(c);
			nombres.add(tmp);
		}

		if (item.getTiendas().isEmpty()) {
			item.setTiendas(nombres);
		}

		model.addAttribute("nombres", nombres);
		model.addAttribute("item", item);
		log.info("Controller  " + Integer.toString(item.getBanner()));

//		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("user", current.getUsuario());
		}

		log.info("Saliendo de getCatProductoEdit");

		return "producto";
	}

	@RequestMapping(value = "/producto/edit/{id}", method = RequestMethod.POST)
	public String postCatProductoEdit(@RequestParam MultipartFile file, @ModelAttribute Producto item,
			BindingResult errors, Model model, @PathVariable String id) {

		log.info("Entrando en postCatProductoEdit");
		item.setIdProducto(Long.parseLong(id));
		SimpleResponse rsp = productoService.actualizarProductos(file, item);

		log.info("Saliendo de postCatProductoEdit");

		return "redirect:/portal-administrador/producto/list?id=" + id + "&type=1";
	}

	@RequestMapping(value = "/producto/delete/{id}", method = RequestMethod.GET)
	public String deleteProducto(Model model, @PathVariable String id) {
		log.info("Entrando a deleteProducto " + id);
		Producto item = productoService.findById(Long.parseLong(id));
		SimpleResponse rsp = productoService.eliminaProducto(item);
		log.info("saliendo a deleteProducto " + id);
		return "redirect:/portal-administrador/producto/list?id=" + id + "&type=0";
	}

	/*
	 * SECCION DE TABLAS
	 */
	@RequestMapping(value = "/mediosBonificacion/list", method = RequestMethod.GET)
	public String redireccionaListaMediosBonificacion(Model model) {
		log.info("Entrando en redireccionaListaMediosBonificacion");
		model.addAttribute("medios",
				catalogoMediosBonificacionService.getCatalogoMediosBonificacion().getMediosBonificacion());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		log.info("Saliendo de redireccionaListaMediosBonificacion");
		return "lista_mediosBonificacion";
	}

	@RequestMapping(value = "/marca/list", method = RequestMethod.GET)
	public String redirectionaListaMarca(Model model) {
		log.info("Entrando en redirectionaListaMarca");
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("proveedores", catalogoMarcaService.getProveedoresMarca().getProveedores());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		log.info("Saliendo de redirectionaListaMarca");
		return "lista_marcas";
	}

	@RequestMapping(value = "/marca/reporte", method = RequestMethod.GET)
	public void reporteMarca(Model model, HttpServletResponse response) throws JRException, IOException {
	
		String headerKey = "Content-Disposition";
		
		String f = Utils.formatDateToString(new Date(), "dd/MM/yyyy");
		String nombreArchivo = "SS-Marcas-"+f;
		
		String headerValue = String.format("attachment; filename=\"%s\"", nombreArchivo);

		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/csv");
		response.setHeader(headerKey, headerValue);
		
		reportService.makeReporteCatalogoMarcas(response.getWriter());
				
	}
	
	@RequestMapping(value = "/marca/productos/reporte/{id}", method = RequestMethod.GET)
	public void getReporteProductosPorMarca(Model model, HttpServletResponse response, @PathVariable String id) throws JRException, IOException {
		
		
//		String headerKey = "Content-Disposition";
//		
//		String f = Utils.formatDateToString(new Date(), "dd/MM/yyyy");
//		String nombreArchivo = "SS-["+"la marca"+"]-"+f;
//		
//		String headerValue = String.format("attachment; filename=\"%s\"", nombreArchivo);
//		
//		response.setContentType("text/csv");
//		response.setHeader(headerKey, headerValue);
		
		// Por razones de simplicidad, el metodo para hacer el reporte 
		// también se encarga de poner los headers al response
		reportService.makeReporteCatalogoProductosPorMarca(id, response);
		
		
	}
	
	@RequestMapping(value = "/cliente/list", method = RequestMethod.GET)
	public String redirectionaListaCliente(Model model) {
		log.info("Entrando en redirectionaListaCliente");
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("proveedores", catalogoMarcaService.getProveedoresMarca().getProveedores());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		log.info("Saliendo de redirectionaListaCliente");
		return "lista_clientes";
	}

	@RequestMapping(value = "/tienda/report", method = RequestMethod.GET)
	public void getReporteTiendas(Model model, HttpServletResponse response) throws JRException, IOException {
		String headerKey = "Content-Disposition";
		
		String f = Utils.formatDateToString(new Date(), "dd/MM/yyyy");
		String nombreArchivo = "SS-Tiendas-"+f;
		
		String headerValue = String.format("attachment; filename=\"%s\"", nombreArchivo);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/csv");
		response.setHeader(headerKey, headerValue);
		
		reportService.makeReporteCatalogoTiendas(response.getWriter());
	}
	
	@RequestMapping(value = "/tienda/reporte/{id}", method = RequestMethod.GET)
	public void getReportePorTienda(Model model, 
			HttpServletResponse response,
			@PathVariable String id) throws JRException, IOException {
	
		log.debug("Entra al repporte por tienda");
		// Por razones de simplicidad, el metodo para hacer el reporte 
		// también se encarga de poner los headers al response
		reportService.makeReporteCatalogoTiendasPorTienda(id, response);
	}
	
	@RequestMapping(value = "/tienda/list", method = RequestMethod.GET)
	public String redirectionaListaTienda(Model model) {
		log.info("Entrando en redirectionaListaTienda");
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		log.info("Saliendo de redirectionaListaTienda");
		return "lista_tiendas";
	}


	@RequestMapping(value = "/departamento/list", method = RequestMethod.GET)
	public String redirectionaListaDepartamento(Model model) {
		log.info("Entrando en redirectionaListaDepartamento");
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		log.info("Saliendo de redirectionaListaDepartamento");
		return "lista_departamentos";
	}

	@RequestMapping(value = "/departamento/report", method = RequestMethod.GET)
	public void reportBonificacionesDepositos(Model model, HttpServletResponse response)
			throws JRException, IOException {
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", "departamentos.csv");
		response.setHeader(headerKey, headerValue);
		List<CatalogoTipoProducto> list = catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos();

		CSVExporter csv = new CSVExporterImpl();

		String[] headers = { "ID", "Nombre", "#Productos" };

		List<List<Object>> rows = new ArrayList<>();
		for (int j = 0; j < list.size(); j++) {
			rows.add(Arrays.asList(new Object[] { list.get(j).getIdCatalogoTipoProducto(),
					list.get(j).getNombreTipoProducto(), list.get(j).getProducts() }));
		}
		try {
			csv.writeCSV(response.getWriter(), headers, rows);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/producto/list", method = RequestMethod.GET)
	public String redirectionaListaProducto(Model model) {
		log.info("Entrando en redirectionaListaProducto");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		model.addAttribute("productos", productoService.getProductos().getProductos());
		log.info("Saliendo de redirectionaListaProducto");
		return "lista_productos";
	}

	@RequestMapping(value = "/usuario/report", method = RequestMethod.GET)
	@ResponseBody
	public void getUsuarioReport(Model model, HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/Usuarios.jasper");
		Map<String, Object> params = new HashMap<>();
		List<UsuarioReport> list = new ArrayList<>();
		UsuarioReport p = new UsuarioReport(1L, "Comex", "", "");
		UsuarioReport p1 = new UsuarioReport(1L, "Herdez", "", "");
		UsuarioReport p2 = new UsuarioReport(1L, "Bonafont", "", "");

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

	/*
	 * INICIO SECCION DE ESTADISTICAS
	 */
	@RequestMapping(value = "/estadisticas-tickets", method = RequestMethod.GET)
	public String redirectionalEstadisticasTickets(Model model) {
		Log.info("Entrando en redirectionalEstadisticasTicket");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}
		EstadisticasRSP rsp = estadisticasService.obtieneEstadisticasTickets();
		model.addAttribute("totalTickets", rsp.getTotalTickets());
		model.addAttribute("tickets", rsp.getHistoricoTickets());

		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");
		model.addAttribute("fecha", fecha);

		Log.info("Saliendo de redirectionalEstadisticasTickets");
		return "administrador/estadisticas-tickets";
	}

	@RequestMapping(value = "/estadisticas-tickets-detalle/{fecha}", method = RequestMethod.GET)
	public String redirectionalEstadisticasTicketsDetalle(Model model, @PathVariable String fecha) {
		Log.info("Entrando en redirectionalEstadisticasTicket");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		List<TicketItem> list = estadisticasService.obtieneTicketsPorFecha(fecha);
		model.addAttribute("fecha", fecha);
		model.addAttribute("tickets", list);

		Log.info("Saliendo de redirectionalEstadisticasTickets");
		return "administrador/estadisticas-tickets-detalle";
	}

	@RequestMapping(value = "/estadisticas-tickets-detalle/ticket/{id}", method = RequestMethod.GET)
	public String redirectionalEstadisticasTicketsDetalleSegundoDetalle(Model model, @PathVariable String id) {
		Log.info("Entrando en redirectionalEstadisticasTicket");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		List<TicketItem> list = estadisticasService.obtieneDetalleTicketPorId(Integer.parseInt(id));
		Integer total = list.stream().map(x -> x.getCantidad().intValue()).reduce(0, Integer::sum);
		Double importeTotal = list.stream().map(x -> x.getImporte()).reduce(0.0, Double::sum);
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

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasGeneral();
		model.addAttribute("totalUsuarios", rsp.getTotalUsuarios());
		model.addAttribute("totalTickets", rsp.getTotalTicketsEscaneados());
		model.addAttribute("totalProductosEscaneados", rsp.getTotalProductosEscaneados());

		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");
		model.addAttribute("fecha", fecha);

		Log.info("Saliendo de redirectionalEstadisticasGeneral");
		return "administrador/estadisticas-general";
	}

	@RequestMapping(value = "/estadisticas-marcas", method = RequestMethod.GET)
	public String redirectionalEstadisticasMarcas(Model model) {
		Log.info("Entrando en redirectionalEstadisticasMarcas");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}
		EstadisticasGeneralRSP rsp = estadisticasService.obtieneEstadisticasMarcas();
		model.addAttribute("totalMarcas", rsp.getTotalMarcas());
		model.addAttribute("totalProductos", rsp.getTotalProductos());
		model.addAttribute("totalProductosEscaneados", rsp.getTotalProductosEscaneados());
		model.addAttribute("listaResumenTiendas", rsp.getListaResumenTiendas());
		model.addAttribute("listaResumenDepartamentos", rsp.getListaResumenDepartamentos());

		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");
		model.addAttribute("fecha", fecha);

		Log.info("Saliendo de redirectionalEstadisticasMarcas");
		return "administrador/estadisticas-marcas";
	}

	@RequestMapping(value = "/estadisticas-usuarios", method = RequestMethod.GET)
	public String redirectionalEstadisticasUsuarios(Model model) {
		Log.info("Entrando en redirectionalEstadisticasUsuarios");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}
		ListItemsRSP usuarios = usuarioService.getUsuarios();
		EstadisticasRSP rsp = estadisticasService.obtieneEstadisticasUsuarios();
		model.addAttribute("totalUsuarios", rsp.getTotalUsuarios());
		model.addAttribute("promedioEdad", rsp.getPromedioEdadUsuarios());
		model.addAttribute("listaUsuarios", usuarios.getUsuarios());

		String fecha = Utils.getCurrentFormatDate("dd / MMM / yyyy");
		model.addAttribute("fecha", fecha);

		Log.info("Saliendo de redirectionalEstadisticasUsuarios");
		return "administrador/estadisticas-usuarios";
	}

	@RequestMapping(value = "/usuario-detalle/{id}", method = RequestMethod.GET)
	public String getObtenerUsuarioDetalle(Model model, @PathVariable String id) {

		log.info("Entrando a getObtenerUsuarioDetalle");

		UsuarioShingShingDetailService current = getAuthenticationUser();

		if (null != current) {
			model.addAttribute("item", current.getUsuario());
		}

		Usuario item = new Usuario();
		item.setIdUsuario(Long.parseLong(id));
		InformacionUsuarioRSP rsp = usuarioService.obtieneDetalleUsuario(item);
		model.addAttribute("rsp", rsp);

		return "administrador/usuario-detalle";
	}
	/*
	 * FIN SECCION DE ESTADISTICAS
	 */

}
