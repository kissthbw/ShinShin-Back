package com.bit.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoMediosBonificacion;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.service.CatalogoMarcaService;
import com.bit.service.CatalogoTiendaService;
import com.bit.service.CatalogoTipoProductoService;
import com.bit.service.ProductoService;

@Controller
@RequestMapping("/catalogos")
public class CatalogosController {
	
	@Autowired
	private ProductoService productoService;

	@Autowired
	private CatalogoTipoProductoService catalogoTipoProductoService;

	@Autowired
	private CatalogoMarcaService catalogoMarcaService;

	@Autowired
	private CatalogoTiendaService catalogoTiendaService;

	@RequestMapping(value = "/departamento/save", method = RequestMethod.GET)
	public String redireccionaCatDepartamentos(Model model) {

		model.addAttribute("item", new CatalogoTipoProducto());

		return "cat_departamentos";
	}
	
	@RequestMapping(value = "/departamento/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoTipoProducto item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTipoProducto());
//		catalogoTipoProductoService.registrarCatalogoTipoProductos(item);

		return "redirect:/catalogos/departamento/save";
	}
	
	@RequestMapping(value = "/marca/save", method = RequestMethod.GET)
	public String redireccionaCatalogoMarca(Model model) {

		model.addAttribute("item", new CatalogoMarca());

		return "catalogo_marca";
	}

	@RequestMapping(value = "/marca/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoMarca item, BindingResult errors, Model model) {

		System.out.println(item.getNombreMarca());
		//catalogoMarcaService.registrarMarcas(item);

		return "redirect:/marca/save";
	}	
	
	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.GET)
	public String redireccionaCatalogoMediosBonificacion(Model model) {

		model.addAttribute("item", new CatalogoMediosBonificacion());

		return "catalogo_medios_bonificacion";
	}

	@RequestMapping(value = "/medioBonificacion/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoMediosBonificacion item, BindingResult errors, Model model) {

		System.out.println(item.getNombreMedioBonificacion());
		//catalogoMediosBonificacionService.registrarCatalogoMediosBonificacion(item);

		return "redirect:/medioBonificacion/save";
	}
	
	@RequestMapping(value = "tienda/save", method = RequestMethod.GET)
	public String redirectionCatalogoTienda(Model model) {
		
		model.addAttribute("item", new CatalogoTienda());

		return "catalogo_tienda";
	}

	@RequestMapping(value = "/tienda/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute CatalogoTienda item, BindingResult errors, Model model) {

		System.out.println(item.getNombreTienda());
		//catalogoTiendaService.registrarTiendas(item);

		return "redirect:/tienda/save";
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

	@RequestMapping(value = "/producto/save", method = RequestMethod.POST)
	public String saveMedio(@ModelAttribute Producto item, BindingResult errors, Model model) {

		System.out.println(item.getNombreProducto());

		// productoService.registrarProductos(item);

		return "redirect:/producto/save";
	}
}
