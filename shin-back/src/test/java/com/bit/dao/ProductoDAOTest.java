package com.bit.dao;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ProductoDAOTest {

	@Autowired
	private ProductoDAO productoDAO;

	@Transactional
	@Test
	public void crudTest() {

		List<Producto> list = productoDAO.getProductosPorMarca();
		
		for( Producto item : list ) {
			System.out.println( item.getNombreProducto() );
		}
	}

	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		Producto item = new Producto();
		CatalogoMarca marca = new CatalogoMarca();

		marca.setIdCatalogoMarca(2l);
		// marca.setNombreMarca( "Roku" );

		CatalogoTipoProducto tipo = new CatalogoTipoProducto();
		tipo.setIdCatalogoTipoProducto(1L);
		// tipo.setNombreTipoProducto( "Streaming" );

		// Marca marca = marcaDAO.findByPK(1l);
		// TipoProducto tipo = tipoProductoDAO.findByPK(1l);

		item.setNombreProducto("Laptop");
		item.setPrecio(699.00);
		item.setCodigoBarras("978128713");
		item.setPresentacion("");
		item.setContenido("1 pieza");
		item.setDescripcion("Dispositivo de streaming");
		item.setAplicaPromocion(true);

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 2);
		item.setVigenciaPromocion(c.getTime());
		item.setUrlImagenProducto("/home/img/chrome.jpg");
		item.setCantidadBonificacion(100.00);
		item.setCatalogoMarca(marca);
		item.setCatalogoTipoProducto(tipo);

		productoDAO.save(item);
	}

	@Transactional
	@Test
	public void getProductosPorTipo() {
		String tipoProducto = "Lacteos";
		String nombreProducto = "Leche Entera";
		List<Producto> list = productoDAO.getProductosPorTipo(tipoProducto, nombreProducto);
		for (Producto p : list) {
			System.out.println("Producto: " + p.getNombreProducto() + " Precio: " + p.getPrecio());
		}
	}

	@Transactional
	@Test
	public void getProductosPorMarca() {
		String marca = "Alpura";
		String nombreProducto = "Leche Entera";
		List<Producto> list = productoDAO.getProductosPorMarca(marca, nombreProducto);
		for (Producto p : list) {
			System.out.println("Producto: " + p.getNombreProducto() + " Precio: " + p.getPrecio());
		}
	}
	
	@Transactional
	@Test
	public void getProductosPorNombre() {
		String nombreProducto = "Leche Entera";
		List<Producto> list = productoDAO.getProductosPorNombre(nombreProducto);
		for (Producto p : list) {
			System.out.println("Producto: " + p.getNombreProducto() + " Precio: " + p.getPrecio());
		}
	}

}
