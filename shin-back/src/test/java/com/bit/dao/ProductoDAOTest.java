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
import com.bit.model.Producto;
import com.bit.model.TipoProducto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ProductoDAOTest {

	@Autowired
	private ProductoDAO productoDAO;
	
	@Autowired
	private CatalogoMarcaDAO catalogoMarcaDAO;
	
	@Autowired
	private TipoProductoDAO tipoProductoDAO;
	

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
	public void saveMarca() {
		CatalogoMarca catalogoMarca = new CatalogoMarca();
		catalogoMarca.setIdCatalogoMarca( 2l );
		catalogoMarca.setNombreMarca( "Google" );
		
		catalogoMarcaDAO.save(catalogoMarca);
	}
	
	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		Producto item = new Producto();
		CatalogoMarca marca = new CatalogoMarca();
		marca.setIdCatalogoMarca( 2l );
//		marca.setNombreMarca( "Roku" );
		
		TipoProducto tipo = new TipoProducto();
		tipo.setIdTipoProducto( 1L );
//		tipo.setNombreTipoProducto( "Streaming" );
		
//		Marca marca = marcaDAO.findByPK(1l);
//		TipoProducto tipo = tipoProductoDAO.findByPK(1l);
		
		item.setNombreProducto( "Roku Stick Express ()" );
		item.setPrecio( 1199.00 );
		item.setCodigoBarras( "978128713" );
		item.setPresentacion( "" );
		item.setContenido( "1 pieza" );
		item.setDescripcion( "Dispositivo de streaming" );
		item.setAplicaPromocion( true );
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 2);
		item.setVigenciaPromocion( c.getTime() );
		item.setUrlImagenProducto( "/home/img/chrome.jpg" );
		item.setCantidadBonificacion( 100.00 );
		item.setCatalogoMarca(marca);
		item.setTipoProducto(tipo);
		
		productoDAO.save(item);
	}
}
