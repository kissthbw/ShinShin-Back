package com.bit.controllers.portal.administrador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
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

	@RequestMapping(value = "/departamento/save", method = RequestMethod.GET)
	public String redireccionaCatDepartamentos(Model model) {

		model.addAttribute("item", new CatalogoTipoProducto());

		return "cat_departamentos";
	}

	@RequestMapping(value = "/departamento/edit{id}", method = RequestMethod.GET)
	public String redireccionaCatDepartamentos(Model model, @PathVariable String id) {

		// Crear un service que busque el departamento por {id}

		CatalogoTipoProducto item = catalogoTipoProductoService.findDepartamentoById(Long.valueOf(id));
		model.addAttribute("item", item);

		return "cat_departamentos";
	}

	@RequestMapping(value = "/departamento/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTipoProducto());

		catalogoTipoProductoService.registrarCatalogoTipoProductos(item);

		return "redirect:/portal-administrador/departamento/save";
	}

	@RequestMapping(value = "/departamento/edit", method = RequestMethod.POST)
	public String editMedio(@ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTipoProducto());

		catalogoTipoProductoService.actualizarCatalogoTipoProductos(item);

		return "redirect:/portal-administrador/departamento/list";
	}

	@RequestMapping(value = "/marca/save", method = RequestMethod.GET)
	public String redireccionaCatalogoMarca(Model model) {

		model.addAttribute("item", new CatalogoMarca());

		return "catalogo_marca";
	}

	@RequestMapping(value = "/marca/edit/{id}", method = RequestMethod.GET)
	public String redireccionaCatalogoMarca(Model model, @PathVariable String id) {

		CatalogoMarca item = catalogoMarcaService.findMarcaById(Long.valueOf(id));
		model.addAttribute("item", item);

		return "catalogo_marca";
	}

	@RequestMapping(value = "/marca/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoMarca item, BindingResult errors, Model model,
			@RequestParam("imagen_marca") MultipartFile imgMarca) {

		System.out.println(item.getNombreMarca());

		catalogoMarcaService.registrarMarcas(item);

		return "redirect:/portal-administrador/marca/save";
	}

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.GET)
	public String redireccionaCatalogoMediosBonificacion(Model model) {

		model.addAttribute("item", new CatalogoMediosBonificacion());

		return "catalogo_medios_bonificacion";
	}

	@RequestMapping(value = "medioBonificacion/edit/{id}", method = RequestMethod.GET)
	public String redireccionaCatalogoMediosBonificacion(Model model, @PathVariable String id) {

		CatalogoMediosBonificacion item = catalogoMediosBonificacionService.findMedioBonificacionById(Long.valueOf(id));
		model.addAttribute("item", item);

		return "catalogo_medios_bonificacion";
	}

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model) {

		System.out.println(item.getNombreMedioBonificacion());

		catalogoMediosBonificacionService.registrarCatalogoMediosBonificacion(item);

		return "redirect:/portal-administrador/medioBonificacion/save";
	}

	@RequestMapping(value = "/tienda/save", method = RequestMethod.GET)
	public String redirectionCatalogoTienda(Model model) {

		model.addAttribute("item", new CatalogoTienda());

		return "catalogo_tienda";
	}

	@RequestMapping(value = "/tienda/edit/{id}", method = RequestMethod.GET)
	public String redirectionCatalogoTienda(Model model, @PathVariable String id) {

		CatalogoTienda item = catalogoTiendaService.findTiendaById(Long.valueOf(id));
		model.addAttribute("item", item);

		return "catalogo_tienda";
	}

	@RequestMapping(value = "/tienda/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoTienda item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTienda());

		catalogoTiendaService.registrarTiendas(item);

		return "redirect:/portal-administrador/tienda/save";
	}

	@PostMapping(value = "/tienda/edit/{id}")
	public String editMedio(@ModelAttribute CatalogoTienda item, BindingResult errors, Model model,
			@PathVariable("id") Long id) {

		System.out.println(item.getNombreTienda());

		catalogoTiendaService.actualizarTiendas(item);

		return "redirect:/portal-administrador/tienda/list";
	}

	@RequestMapping(value = "/producto/save", method = RequestMethod.GET)
	public String redireccionaProducto(Model model) {

		model.addAttribute("item", new Producto());

		List<CatalogoMarca> listMarca = new ArrayList<>();
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());

		List<CatalogoTipoProducto> listTipo = new ArrayList<>();
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());

		List<CatalogoTienda> listTienda = new ArrayList<>();
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());

		return "producto";
	}

	@RequestMapping(value = "/producto/edit/{id}", method = RequestMethod.GET)
	public String redireccionaProducto(Model model, @PathVariable String id) {

		Producto item = productoService.findProductoById(Long.valueOf(id));
		model.addAttribute("item", item);

		List<CatalogoMarca> listMarca = new ArrayList<>();
		model.addAttribute("marcas", catalogoMarcaService.getCatalogoMarca().getMarcas());

		List<CatalogoTipoProducto> listTipo = new ArrayList<>();
		model.addAttribute("tipos", catalogoTipoProductoService.getCatalogoTipoProductos().getTipoProductos());

		List<CatalogoTienda> listTienda = new ArrayList<>();
		model.addAttribute("tiendas", catalogoTiendaService.getCatalogoTienda().getTiendas());

		return "producto";
	}

	@RequestMapping(value = "/producto/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute Producto item, BindingResult errors, Model model) {

		System.out.println(item.getNombreProducto());

		productoService.registrarProductos(item);

		return "redirect:/portal-administrador/producto/save";
	}

	@RequestMapping(value = "/mediosBonificacion/list", method = RequestMethod.GET)
	public String redireccionaListaMediosBonificacion(Model model) {
		model.addAttribute("medios",
				catalogoMediosBonificacionService.getCatalogoMediosBonificacion().getMediosBonificacion());
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
