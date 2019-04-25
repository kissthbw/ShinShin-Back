package com.bit.dao;

import java.util.ArrayList;
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
import com.bit.model.Producto;
import com.bit.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class UsuarioDAOTest {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ProductoDAO productoDAO;
	
	@Transactional
	@Test
	public void crudTest() {
		Usuario u = usuarioDAO.findByPK(1L);
		System.out.println(u.getUsuario());
	}

	@Transactional
	@Test
	public void findById() {
		Usuario item = usuarioDAO.findByPK(1l);
		
		System.out.printf( "Usuario: %s %s %s \n", item.getNombre(), item.getApPaterno(), item.getApMaterno()  );
		
		System.out.println( item.getProductos().isEmpty() 
				? "No tiene productos favoritos" 
				: "Productos favoritos:"  );
		
		for( Producto p : item.getProductos() ) {
			System.out.printf(" - %s %s %s \n", 
					p.getNombreProducto(), p.getMarca().getNombreMarca(), p.getTipoProducto().getNombreTipoProducto());
		}
		
		System.out.println();
		
	}
	
	@Transactional
	@Test
	@Rollback(false)
	public void saveProductosFavoritos() {
		Usuario item = usuarioDAO.findByPK(1l);
		Producto p = productoDAO.findByPK(4l);
		List<Producto> list = new ArrayList<>();
		list.add(p);
		
		item.addProducto(p);
		
		usuarioDAO.save( item );
	}
	
	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		Usuario item = new Usuario();
		item.setNombre( "Juan" );
		item.setApPaterno( "Osorio" );
		item.setApMaterno( "Alvarez" );
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 11);
		c.set(Calendar.MONTH, Calendar.SEPTEMBER);
		c.set(Calendar.YEAR, 1983);
		
		item.setFechaNac( c.getTime() );
		item.setUsuario( "kissthbw" );
		item.setContrasenia( "kiss2101" );
		item.setCalle( "Virgen de los Remedios" );
		item.setNumeroExt( "71" );
		item.setNumeroInt( "" );
		item.setColonia( "Virgencitas" );
		item.setCodigoPostal( "57300" );
		item.setDelMun( "Nezahualcoyotl" );
		item.setEstado( "Estado de México" );
		item.setTelLocal( "51121423" );
		
		
		usuarioDAO.save(item);
	}
}
