package com.bit.controllers.portal.administrador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.CatalogoMarcaService;
import com.bit.service.CatalogoMediosBonificacionService;
import com.bit.service.CatalogoTiendaService;
import com.bit.service.CatalogoTipoProductoService;
import com.bit.service.ProductoService;

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

	/*
	 * Seccion de formularios y tablas
	 * DEPARTAMENTOS
	 */
	@RequestMapping(value = "/departamento/save", method = RequestMethod.GET)
	public String gerCatDepartamento(Model model) {

		model.addAttribute("item", new CatalogoTipoProducto());

		return "cat_departamentos";
	}

	@RequestMapping(value = "/departamento/save", method = RequestMethod.POST)
	public String postCatDepartamento(@ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTipoProducto());

		catalogoTipoProductoService.registrarCatalogoTipoProductos(item);

		return "redirect:/portal-administrador/departamento/save";
	}
	
	@RequestMapping(value = "/departamento/edit/{id}", method = RequestMethod.GET)
	public String getCatDepartamentoEdit(Model model, @PathVariable String id) {
		
		CatalogoTipoProducto item = catalogoTipoProductoService.findById(Long.parseLong(id));
		model.addAttribute("item", item);

		return "cat_departamentos";
	}
	
	@RequestMapping(value = "/departamento/edit/{id}", method = RequestMethod.POST)
	public String postCatDepartamentoEdit(@ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model, @PathVariable String id) {
		
		System.out.println( "Editando" );
		item.setIdCatalogoTipoProducto( Long.parseLong(id) );
		SimpleResponse rsp = catalogoTipoProductoService.actualizarCatalogoTipoProductos(item);

		return "redirect:/portal-administrador/departamento/list";
	}

	/*
	 * MARCA
	 */
	@RequestMapping(value = "/marca/save", method = RequestMethod.GET)
	public String getCatMarca(Model model) {

		model.addAttribute("item", new CatalogoMarca());

		return "catalogo_marca";
	}

	@RequestMapping(value = "/marca/save", method = RequestMethod.POST)
	public String postCatMarca(@RequestParam MultipartFile file, @ModelAttribute CatalogoMarca item, BindingResult errors, Model model) {
		
		System.out.println(item.getNombreMarca());

		catalogoMarcaService.registrarMarcas(item);

		return "redirect:/portal-administrador/marca/save";
	}
	
	@RequestMapping(value = "/marca/edit/{id}", method = RequestMethod.GET)
	public String getCatMarcaEdit(Model model, @PathVariable String id) {
		
		CatalogoMarca item = catalogoMarcaService.findById(Long.parseLong(id));
		model.addAttribute("item", item);

		return "catalogo_marca";
	}
	
	@RequestMapping(value = "/marca/edit/{id}", method = RequestMethod.POST)
	public String postCatMarcaEdit(@ModelAttribute CatalogoMarca item, BindingResult errors, Model model, @PathVariable String id) {
		
		System.out.println( "Editando" );
		item.setIdCatalogoMarca( Long.parseLong(id) );
		SimpleResponse rsp = catalogoMarcaService.actualizarMarcas(item);

		return "redirect:/portal-administrador/marca/list";
	}

	/*
	 * MEDIOS DE BONIFICACION
	 */
	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.GET)
	public String getCatMedioBonificacion(Model model) {

		model.addAttribute("item", new CatalogoMediosBonificacion());

		return "catalogo_medios_bonificacion";
	}

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.POST)
	public String postCatMedioBonificacion(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model) {

		System.out.println(item.getNombreMedioBonificacion());

		catalogoMediosBonificacionService.registrarCatalogoMediosBonificacion(item);

		return "redirect:/portal-administrador/medioBonificacion/save";
	}
	
	@RequestMapping(value = "/medioBonificacion/edit/{id}", method = RequestMethod.GET)
	public String getCatMedioBonificacionEdit(Model model, @PathVariable String id) {
		
		CatalogoMediosBonificacion item = catalogoMediosBonificacionService.findById(Long.parseLong(id));
		model.addAttribute("item", item);

		return "catalogo_medios_bonificacion";
	}
	
	@RequestMapping(value = "/medioBonificacion/edit/{id}", method = RequestMethod.POST)
	public String postCatMedioBonificacionEdit(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model, @PathVariable String id) {
		
		System.out.println( "Editando" );
		item.setIdCatalogoMedioBonificacion( Long.parseLong(id) );
		SimpleResponse rsp = catalogoMediosBonificacionService.actualizarCatalogoMediosBonificacion(item);

		return "redirect:/portal-administrador/mediosBonificacion/list";
	}
	

	/*
	 * TIENDA
	 */
	@RequestMapping(value = "tienda/save", method = RequestMethod.GET)
	public String getCatTienda(Model model) {

		model.addAttribute("item", new CatalogoTienda());

		return "catalogo_tienda";
	}

	@RequestMapping(value = "/tienda/save", method = RequestMethod.POST)
	public String postCatTienda(@ModelAttribute CatalogoTienda item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTienda());

		catalogoTiendaService.registrarTiendas(item);

		return "redirect:/portal-administrador/tienda/save";
	}
	
	@RequestMapping(value = "/tienda/edit/{id}", method = RequestMethod.GET)
	public String getCatTiendaEdit(Model model, @PathVariable String id) {
		
		CatalogoTienda item = catalogoTiendaService.findById(Long.parseLong(id));
		model.addAttribute("item", item);

		return "catalogo_tienda";
	}
	
	@RequestMapping(value = "/tienda/edit/{id}", method = RequestMethod.POST)
	public String postCatTiendaEdit(@ModelAttribute CatalogoTienda item, BindingResult errors, Model model, @PathVariable String id) {
		
		System.out.println( "Editando" );
		item.setIdCatalogoTienda( Long.parseLong(id) );
		SimpleResponse rsp = catalogoTiendaService.actualizarTiendas(item);

		return "redirect:/portal-administrador/tienda/list";
	}

	/*
	 * PRODUCTOS
	 */
	@RequestMapping(value = "/producto/save", method = RequestMethod.GET)
	public String getCatProducto(Model model) {

		model.addAttribute("item", new Producto());
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());

		return "producto";
	}

	@RequestMapping(value = "/producto/save", method = RequestMethod.POST)
	public String postCatProducto(@ModelAttribute Producto item, BindingResult errors, Model model) {

		System.out.println(item.getNombreProducto());

		productoService.registrarProductos(item);

		return "redirect:/portal-administrador/producto/save";
	}
	
	@RequestMapping(value = "/producto/edit/{id}", method = RequestMethod.GET)
	public String getCatProductoEdit(Model model, @PathVariable String id) {
		
		Producto item = productoService.findById(Long.parseLong(id));
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());
		model.addAttribute("item", item);

		return "producto";
	}
	
	@RequestMapping(value = "/producto/edit/{id}", method = RequestMethod.POST)
	public String postCatProductoEdit(@ModelAttribute Producto item, BindingResult errors, Model model, @PathVariable String id) {
		
		System.out.println( "Editando" );
		item.setIdProducto( Long.parseLong(id) );
		SimpleResponse rsp = productoService.actualizarProductos(item);

		return "redirect:/portal-administrador/producto/list";
	}
	
	
	/*
	 * SECCION DE TABLAS
	 */
	@RequestMapping(value = "/mediosBonificacion/list", method = RequestMethod.GET)
	public String redireccionaListaMediosBonificacion(Model model) {
		model.addAttribute("medios", catalogoMediosBonificacionService.getCatalogoMediosBonificacion().getMediosBonificacion());
		return "lista_mediosBonificacion";
	}
	
	@RequestMapping(value = "/marca/list", method = RequestMethod.GET)
	public String redirectionaListaMarca(Model model) {
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());
		return "lista_marcas";
	}

	@RequestMapping(value = "/tienda/list", method = RequestMethod.GET)
	public String redirectionaListaTienda(Model model) {
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());
		return "lista_tiendas";
	}
	
	@RequestMapping(value = "/departamento/list", method = RequestMethod.GET)
	public String redirectionaListaDepartamento(Model model) {
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());
		return "lista_departamentos";
	}
	
	@RequestMapping(value = "/producto/list", method = RequestMethod.GET)
	public String redirectionaListaProducto(Model model) {
		model.addAttribute("productos", productoService.getProductos().getProductos());
		return "lista_productos";
	}
}
