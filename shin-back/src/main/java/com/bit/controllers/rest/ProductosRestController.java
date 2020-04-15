package com.bit.controllers.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.SugerenciaProducto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductosRestController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductosRestController.class);

	@Autowired
	private ProductoService productoService;

	/*
	 * @ResponseBody, esta anotacion da la capacidad de regresar una respuesta de
	 * acuerdo a la configuración del recurso. En nuestro caso como la libreria
	 * jackson-databind esta disponible (ver pom.xml) se formatea en automatico a
	 * JSON
	 * 
	 */

	/**
	 * Recurso REST para obtener una lista de productos.
	 */
	@GetMapping(value = "/list")
	public @ResponseBody ListItemsRSP getProductos() {
		
		log.info("Entrando a getProductos");
		ListItemsRSP rsp =  productoService.getProductos();

		return rsp;
	}
	
	@GetMapping(value = "/list/porTipo")
	public @ResponseBody ListItemsRSP getProductosPortTipoProducto(@RequestParam(name = "id") String id) {
		
		log.info("Entrando a getProductos");
		ListItemsRSP rsp =  productoService.getProductosPorTipoProducto( Long.parseLong( id ) );

		return rsp;
	}
	
	@GetMapping(value = "/tiendas/porProducto")
	public @ResponseBody List<CatalogoTienda> getObtieneTiendasPorProducto(@RequestParam(name = "id") String id) {
		
		log.info("Entrando a getProductos");
		List<CatalogoTienda> rsp =  productoService.getTiendasPorProducto( Long.parseLong( id ) );

		return rsp;
	}
	
	//Este servcio de ve regresar N numero de registros segun se especifique en los paramemtros
	//Debe regresar el total de registros en BD
	//La pagina que se esta mostrando
	//Un indicador si hay mas paginas que mostrar
	@GetMapping(value = "/listPag")
	public @ResponseBody ListItemsRSP getProductosPorPaginas(@RequestParam String page, 
			@RequestParam String maxResults) {
		
		log.info("Entrando a getProductos page: {}, max results: {}", page, maxResults);
		ListItemsRSP rsp =  productoService.getProductosPorPaginas(Integer.valueOf(page), Integer.valueOf(maxResults));

		return rsp;
	}
	
	@GetMapping(value = "/listBanner")
	public @ResponseBody ListItemsRSP getBanners() {
		
		log.info("Entrando a getBanners");
		ListItemsRSP rsp =  productoService.getBanners();

		return rsp;
	}
	
	/*
	 * actualizar tabla producto esBanner
	 * actualizar entity
	 * agregar metodo listBanners
	 * agregar por criteria restriction isBanner
	 */

	/*
	 * @RequestBody, esta anotacion habilita la recepcion de mensajes segun el tipo
	 * de mensaje que se haya configurado, en este caso se recibe un mensaje JSON
	 * Por default detecta el formato JSON debido a la libreria jackson-databind
	 * esta disponible (ver pom.xml)
	 */

	/**
	 * 
	 * @param item
	 */
	@PostMapping(value = "/producto/registrar")
	public @ResponseBody SimpleResponse registrarProducto(@RequestBody Producto item) {
		
		log.info("Registrando un producto en registrarProductos");
		SimpleResponse rsp = productoService.registrarProductos(null, item);

		return rsp;
	}

	@PostMapping(value = "/producto/actualizar")
	public @ResponseBody SimpleResponse actualizarProducto(@RequestBody Producto item) {
		
		log.info("Actualizando uno o varios valores de un producto en actualizarProductos");
		SimpleResponse rsp = productoService.actualizarProductos(null, item);

		return rsp;
	}

	@PostMapping(value = "/producto/porMarca")
	public @ResponseBody ListItemsRSP getProductosPorMarca(CatalogoMarca marca, Producto nombreProducto) {
		
		log.info("Entrando a getProductosPorMarca");
		ListItemsRSP rsp = productoService.getProductosPorMarca(marca, nombreProducto);

		return rsp;
	}

	@PostMapping(value = "/producto/porTipo")
	public @ResponseBody ListItemsRSP getProductosPorTipo(CatalogoTipoProducto tipoProducto, Producto nombreProducto) {
		
		log.info("Entrando a getProductosPorTipo");
		ListItemsRSP rsp = productoService.getProductosPorTipo(tipoProducto, nombreProducto);

		return rsp;
	}

	@PostMapping(value = "/producto/porNombre")
	public @ResponseBody ListItemsRSP getProductosPorNombre(Producto nombreProducto) {
		
		log.info("Entrando a getProductosPorNombre");
		ListItemsRSP rsp = productoService.getProductosPorNombre(nombreProducto);

		return rsp;
	}
	
	@PostMapping(value="/sugerencias")
	public @ResponseBody SimpleResponse registraSugerencia(@RequestBody SugerenciaProducto item) {
		log.info("Entrando en registraSugerencia");
		SimpleResponse rsp = productoService.registraSugerencia(item);
		
		return rsp;
	}
}
