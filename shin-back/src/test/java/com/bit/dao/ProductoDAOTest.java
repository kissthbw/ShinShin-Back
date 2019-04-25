package com.bit.dao;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.bit.config.WebConfig;
import com.bit.model.Marca;
import com.bit.model.Producto;
import com.bit.model.TipoProducto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ProductoDAOTest {

	@Autowired
	private ProductoDAO productoDAO;
	
	@Autowired
	private MarcaDAO marcaDAO;
	
	@Autowired
	private TipoProductoDAO tipoProductoDAO;
	

	@Transactional
	@Test
	public void crudTest() {
		Producto p = productoDAO.findByPK(1L);
		System.out.println(p.getNombreProducto());
	}
	
	@Transactional
	@Test
	@Rollback(false)
	public void saveMarca() {
		Marca marca = new Marca();
		marca.setIdMarca( 2l );
		marca.setNombreMarca( "Google" );
		
		marcaDAO.save(marca);
	}
	
	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		Producto item = new Producto();
		Marca marca = new Marca();
		marca.setIdMarca( 2l );
//		marca.setNombreMarca( "Roku" );
		
		TipoProducto tipo = new TipoProducto();
		tipo.setIdTipoProducto( 1L );
//		tipo.setNombreTipoProducto( "Streaming" );
		
//		Marca marca = marcaDAO.findByPK(1l);
//		TipoProducto tipo = tipoProductoDAO.findByPK(1l);
		
		item.setNombreProducto( "Chromecast" );
		item.setPrecio( 699.00 );
		item.setCodigoBarras( "210121213" );
		item.setPresentacion( "" );
		item.setContenido( "" );
		item.setDescripcion( "Dispositivo de streaming" );
		item.setAplicaPromocion( true );
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 2);
		item.setVigenciaPromocion( c.getTime() );
		item.setUrlImagenProducto( "/home/img/chrome.jpg" );
		item.setCantidadBonificacion( 100.00 );
		item.setMarca(marca);
		item.setTipoProducto(tipo);
		
		productoDAO.save(item);
	}
}
