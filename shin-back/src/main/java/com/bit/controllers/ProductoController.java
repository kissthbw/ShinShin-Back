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
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.service.CatalogoMarcaService;
import com.bit.service.CatalogoTiendaService;
import com.bit.service.CatalogoTipoProductoService;
import com.bit.service.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private CatalogoTipoProductoService catalogoTipoProductoService;

	@Autowired
	private CatalogoMarcaService catalogoMarcaService;

	@Autowired
	private CatalogoTiendaService catalogoTiendaService;

	@RequestMapping(value = "/producto/save", method = RequestMethod.GET)
	public String redireccionaMedio(Model model) {

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
