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

import com.bit.common.Utils;
import com.bit.config.WebConfig;
import com.bit.model.Producto;
import com.bit.model.ProductoFavorito;
import com.bit.model.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ProductoFavoritoDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(ProductoFavoritoDAOTest.class);
	
	@Autowired
	private ProductoFavoritoDAO productoFavoritoDAO;

	@Transactional
	@Test
	@Rollback(false)
	public void guardarProductoFavorito() throws JsonProcessingException {

		ProductoFavorito pf = new ProductoFavorito();
		Usuario u = new Usuario();
		u.setIdUsuario( 89L );
		Producto p = new Producto( );
		p.setIdProducto( 64L );
		pf.setUsuario(u);
		pf.setProducto(p);
		
		System.out.println( Utils.objectToJSON( pf ) );
		
		productoFavoritoDAO.save( pf );
	}

	@Transactional
	@Test
	public void obtenerProductosFavoritosPorUsuario() {
		
		long idProducto = 75L;
		long idUsuario = 89L;
		
		boolean b = productoFavoritoDAO.existeFavoritoPorProductoYUsuario(idProducto, idUsuario);
		System.out.println( "Existe producto? " + b );
		
		List<ProductoFavorito> list = productoFavoritoDAO.getProductosFavoritosPorUsuario( idUsuario );
		
		for( ProductoFavorito pf : list ) {
			System.out.println( pf.getProducto().getNombreProducto() );
		}
	}

}
