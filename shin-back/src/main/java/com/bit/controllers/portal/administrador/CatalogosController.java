package com.bit.controllers.portal.administrador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.Usuario;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.report.ProductoReport;
import com.bit.service.CatalogoMarcaService;
import com.bit.service.CatalogoMediosBonificacionService;
import com.bit.service.CatalogoTiendaService;
import com.bit.service.CatalogoTipoProductoService;
import com.bit.service.ProductoService;
import com.bit.service.UsuarioService;
import com.bit.service.UsuarioShingShingDetailService;

import net.sf.jasperreports.engine.JREmptyDataSource;
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
	
	private static Logger log = LoggerFactory.getLogger( CatalogosController.class );

	/*
	 * Seccion de formularios y tablas
	 * DEPARTAMENTOS
	 */
	@RequestMapping(value = "/departamento/save", method = RequestMethod.GET)
	public String gerCatDepartamento(Model model) {
		log.info( "Entrando en gerCatDepartamento" );
		model.addAttribute("item", new CatalogoTipoProducto());
		
		log.info( "Saliendo de gerCatDepartamento" );
		
		return "cat_departamentos";
	}

	@RequestMapping(value = "/departamento/save", method = RequestMethod.POST)
	public String postCatDepartamento(@RequestParam MultipartFile file, @ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatDepartamento" );
		catalogoTipoProductoService.registrarCatalogoTipoProductos(file, item);
		
		log.info( "Saliendo de postCatDepartamento" );

		return "redirect:/portal-administrador/departamento/save";
	}
	
	@RequestMapping(value = "/departamento/edit/{id}", method = RequestMethod.GET)
	public String getCatDepartamentoEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatDepartamentoEdit" );
		CatalogoTipoProducto item = catalogoTipoProductoService.findById(Long.parseLong(id));
		model.addAttribute("item", item);
		
		log.info( "Saliendo de getCatDepartamentoEdit" );

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

	/*
	 * MARCA
	 */
	@RequestMapping(value = "/marca/save", method = RequestMethod.GET)
	public String getCatMarca(Model model) {

		log.info( "Entrando en getCatMarca" );
		model.addAttribute("item", new CatalogoMarca());
		
		log.info( "Saliendo de getCatMarca" );

		return "catalogo_marca";
	}

	@RequestMapping(value = "/marca/save", method = RequestMethod.POST)
	public String postCatMarca(@RequestParam MultipartFile file, @ModelAttribute CatalogoMarca item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatMarca" );
		catalogoMarcaService.registrarMarcas(file, item);

		log.info( "Saliendo de postCatMarca" );
		return "redirect:/portal-administrador/marca/save";
	}
	
	@RequestMapping(value = "/marca/edit/{id}", method = RequestMethod.GET)
	public String getCatMarcaEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatMarcaEdit" );
		CatalogoMarca item = catalogoMarcaService.findById(Long.parseLong(id));
		model.addAttribute("item", item);
		
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
		
		log.info( "Saliendo de getCatMedioBonificacion" );

		return "catalogo_medios_bonificacion";
	}

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.POST)
	public String postCatMedioBonificacion(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatMarcaEdit" );
		catalogoMediosBonificacionService.registrarCatalogoMediosBonificacion(item);
		
		log.info( "Saliendo de postCatMarcaEdit" );

		return "redirect:/portal-administrador/medioBonificacion/save";
	}
	
	@RequestMapping(value = "/medioBonificacion/edit/{id}", method = RequestMethod.GET)
	public String getCatMedioBonificacionEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatMedioBonificacionEdit" );
		CatalogoMediosBonificacion item = catalogoMediosBonificacionService.findById(Long.parseLong(id));
		model.addAttribute("item", item);
		
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
		
		log.info( "Saliendo de getCatTienda" );

		return "catalogo_tienda";
	}

	@RequestMapping(value = "/tienda/save", method = RequestMethod.POST)
	public String postCatTienda(@ModelAttribute CatalogoTienda item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatTienda" );
		catalogoTiendaService.registrarTiendas(item);
		
		log.info( "Saliendo de postCatTienda" );

		return "redirect:/portal-administrador/tienda/save";
	}
	
	@RequestMapping(value = "/tienda/edit/{id}", method = RequestMethod.GET)
	public String getCatTiendaEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatTiendaEdit" );
		CatalogoTienda item = catalogoTiendaService.findById(Long.parseLong(id));
		model.addAttribute("item", item);
		
		log.info( "Saliendo de getCatTiendaEdit" );

		return "catalogo_tienda";
	}
	
	@RequestMapping(value = "/tienda/edit/{id}", method = RequestMethod.POST)
	public String postCatTiendaEdit(@ModelAttribute CatalogoTienda item, BindingResult errors, Model model, @PathVariable String id) {
		
		log.info( "Entrando en postCatTiendaEdit" );
		item.setIdCatalogoTienda( Long.parseLong(id) );
		SimpleResponse rsp = catalogoTiendaService.actualizarTiendas(item);
		
		log.info( "Saliendo de postCatTiendaEdit" );

		return "redirect:/portal-administrador/tienda/list";
	}

	/*
	 * PRODUCTOS
	 */
	@RequestMapping(value = "/producto/save", method = RequestMethod.GET)
	public String getCatProducto(Model model) {

		log.info( "Entrando en getCatProducto" );
		model.addAttribute("item", new Producto());
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());
		
		log.info( "Saliendo de getCatProducto" );

		return "producto";
	}
	
	@RequestMapping(value = "/producto/report", method = RequestMethod.GET)
	@ResponseBody
	public void getProductoReport(Model model, HttpServletResponse response) throws JRException, IOException {
		InputStream jasperStream = this.getClass().getResourceAsStream("/Productos1.2.jasper");
	    Map<String,Object> params = new HashMap<>();
	    List<ProductoReport> list = new ArrayList<>();
	    ProductoReport p = new ProductoReport( 1L, "SN:098713123123", "Switch", "Nintendo", true );
	    ProductoReport p1 = new ProductoReport( 1L, "SN:098713123123", "Switch", "Nintendo", true );
	    ProductoReport p2 = new ProductoReport( 1L, "SN:098713123123", "Switch", "Nintendo", true );
	    
	    list.add(p);
	    list.add(p1);
	    list.add(p2);
	    
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=Productos.pdf");

	    final OutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
	}

	@RequestMapping(value = "/producto/save", method = RequestMethod.POST)
	public String postCatProducto(@RequestParam MultipartFile file, @ModelAttribute Producto item, BindingResult errors, Model model) {

		log.info( "Entrando en postCatProducto" );
		productoService.registrarProductos(file, item);
		
		log.info( "Saliendo de postCatProducto" );

		return "redirect:/portal-administrador/producto/save";
	}
	
	@RequestMapping(value = "/producto/edit/{id}", method = RequestMethod.GET)
	public String getCatProductoEdit(Model model, @PathVariable String id) {
		
		log.info( "Entrando en getCatProductoEdit" );
		Producto item = productoService.findById(Long.parseLong(id));
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());
		model.addAttribute("item", item);
		
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
		
		log.info( "Saliendo de redireccionaListaMediosBonificacion" );
		return "lista_mediosBonificacion";
	}
	
	@RequestMapping(value = "/marca/list", method = RequestMethod.GET)
	public String redirectionaListaMarca(Model model) {
		log.info( "Entrando en redirectionaListaMarca" );
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		
		log.info( "Saliendo de redirectionaListaMarca" );
		return "lista_marcas";
	}

	@RequestMapping(value = "/tienda/list", method = RequestMethod.GET)
	public String redirectionaListaTienda(Model model) {
		log.info( "Entrando en redirectionaListaTienda" );
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());
		
		log.info( "Saliendo de redirectionaListaTienda" );
		return "lista_tiendas";
	}
	
	@RequestMapping(value = "/departamento/list", method = RequestMethod.GET)
	public String redirectionaListaDepartamento(Model model) {
		log.info( "Entrando en redirectionaListaDepartamento" );
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());
		
		log.info( "Saliendo de redirectionaListaDepartamento" );
		return "lista_departamentos";
	}
	
	@RequestMapping(value = "/producto/list", method = RequestMethod.GET)
	public String redirectionaListaProducto(Model model) {
		log.info( "Entrando en redirectionaListaProducto" );
		
		UsuarioShingShingDetailService current = getAuthenticationUser();
		
		if ( null != current ) {
			Usuario item = new Usuario();
			item.setIdUsuario( current.getUsuario().getIdUsuario() );
			item.setUsuario( current.getUsuario().getUsuario() );
			
			Usuario rsp = usuarioService.findUserByPK(item);
			
			model.addAttribute("item", rsp);
		}
		
		model.addAttribute("productos", productoService.getProductos().getProductos());
		
		log.info( "Saliendo de redirectionaListaProducto" );
		return "lista_productos";
	}
	
	private UsuarioShingShingDetailService getAuthenticationUser() {
		UsuarioShingShingDetailService user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UsuarioShingShingDetailService) {
			user = (UsuarioShingShingDetailService) principal;
		}
		
		return user;
	}
}
