package com.bit.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.controllers.rest.ProductosTiendasRestController;
import com.bit.model.ProductosTiendas;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.ProductosTiendasService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ProductosTiendasDAOTest {

	private static final Logger log = LoggerFactory.getLogger(ProductosTiendasRestController.class);

	@Autowired
	private ProductosTiendasDAO productosTiendasDAO;

	@Autowired
	private ProductosTiendasService productosTiendasService;

	@Autowired
	private ProductoDAO poductoDAO;

	@Autowired
	private CatalogoTiendaDAO catalogoTiendaDAO;

	@Transactional
	@Test
	@Rollback(false)
	public void registrar() {

		log.info("Registra los valores de un nuevo producto por tienda");

		ProductosTiendas pt = new ProductosTiendas();
		pt.setProductoTienda("Computadora de Escritorio");
		pt.setIdProducto(1l);
		pt.setIdCatalogoTienda(5l);

		SimpleResponse respuesta = productosTiendasService.registrarProductosPorTienda(pt);
		System.out.println(respuesta.getMessage() + "\n" + respuesta.getCode());
	}

	@Transactional
	@Test
	@Rollback(false)
	public void actualizar() {

		log.info("Actualiza los valores de un producto por tienda");

		ProductosTiendas pt = productosTiendasDAO.findByPK(1l);
		pt.setProductoTienda("tennis blancos");

		productosTiendasService.actualizarProductosPorTienda(pt);
	}

	@Transactional
	@Test
	public void obtenerUno() {

		log.info("Obtiene un producto por tienda por id");

		ProductosTiendas pt = productosTiendasDAO.findByPK(2l);
		System.out.println(pt.getProductoTienda());
	}

	@Transactional
	@Test
	public void obtenerLista() {

		log.info("Obtiene lista de productos por tienda");

		List<ProductosTiendas> list = productosTiendasDAO.getProductosTiendas();

		for (ProductosTiendas item : list) {
			System.out.println(item.getProductoTienda());
		}
	}
}
