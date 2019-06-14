package com.bit.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.ProductoValoracion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ProductoValoracionDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(ProductoValoracionDAOTest.class);
	
	@Autowired
	private ProductoValoracionDAO productoValoracionDAO;

	@Transactional
	@Test
	public void crudTest() {

		ProductoValoracion pv = productoValoracionDAO.findByPK(1L);
		System.out.println("Producto: " + pv.getProducto().getNombreProducto() + "\n" + "Valoracion: "
				+ pv.getValoracion() + " puntos de 5 " + "\n" + "Usuario: " + pv.getUsuario().getUsuario());
	}

	@Transactional
	@Test
	public void obtenerProductosValoracion() {
	List<ProductoValoracion> listProductoValoracion = productoValoracionDAO.getProductosValoracion();
		System.out.println("Total de productos valorados: " + listProductoValoracion.size());
		
		for(ProductoValoracion nombre : listProductoValoracion) {
			System.out.println("Producto: " + nombre.getProducto().getNombreProducto());
		}
		
	}

}
