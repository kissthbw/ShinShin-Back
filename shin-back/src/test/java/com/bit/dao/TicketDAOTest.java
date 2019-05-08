package com.bit.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.bit.model.Ticket;
import com.bit.model.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class TicketDAOTest {

	@Autowired
	private TicketDAO ticketDAO;
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private ProductoDAO productoDAO;

	@Transactional
	@Test
	public void crudTest() {
		Ticket t = ticketDAO.findByPK(1L);
		System.out.println(t.getNombreTienda());
	}

	@Transactional
	@Test
	public void findById() {
		Ticket item = ticketDAO.findByPK(5l);

		System.out.printf("Ticket: %s \n", item.getIdTicket());

		System.out.println(item.getProductos().isEmpty() ? "No tiene promocion" : "Producto(s) con promocion:");

		for (Producto p : item.getProductos()) {
			System.out.printf(" - %s %s %s \n", p.getNombreProducto(), 
					p.getMarca().getNombreMarca(), 
					p.getTipoProducto().getNombreTipoProducto());
		}

		System.out.println();

	}

	@Transactional
	@Test
	@Rollback(false)
	public void saveHistoricoBonificaciones() {
		Ticket item = ticketDAO.findByPK(1l);
		Producto p = productoDAO.findByPK(4l);
		List<Producto> list = new ArrayList<>();
		list.add(p);

		item.addProducto(p);

		ticketDAO.save(item);
	}

	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		Ticket item = new Ticket();
		item.setNombreTienda("Walmart");
		item.setSucursal("Plaza Jardin");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, Calendar.APRIL);
		c.set(Calendar.YEAR, 2019);

		item.setFecha(c.getTime());
		item.setHora(new Date());
		item.setSubtotal(587.16);
		item.setIva(111.84);
		item.setTotal(699.00);
		
//		CatalogoMarca m = new CatalogoMarca();
//		m.setNombreMarca("Roku");
//		
//		TipoProducto t = new TipoProducto();
//		t.setNombreTipoProducto("Streaming");
//		
//		Producto p = new Producto();
//		p.setMarca(m);
//		p.setTipoProducto(t);
//		p.setNombreProducto( "Laptop" );
//		p.setPrecio( 699.00 );
//		p.setCodigoBarras( "978128713" );
//		p.setPresentacion( "" );
//		p.setContenido( "1 pieza" );
//		p.setDescripcion( "Dispositivo de streaming" );
//		p.setAplicaPromocion( true );
//		
//		Calendar c1 = Calendar.getInstance();
//		c.add(Calendar.MONTH, 2);
//		p.setUrlImagenProducto( "/home/img/chrome.jpg" );
//		p.setVigenciaPromocion( c1.getTime() );
//		p.setCantidadBonificacion( 100.00 );
		
		Producto p = productoDAO.findByPK(1l);
//		p.setIdProducto(1L);
		item.addProducto(p);


		ticketDAO.save(item);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void updateTicketTest() {
		//Agregar productos a un ticket
		Ticket item = ticketDAO.findByPK(1l);
		Producto p1 = new Producto();
		p1.setIdProducto(2L);
		item.addProducto(p1);
		ticketDAO.update(item);
		
		//Asignar ticket a usuario
		Usuario u = usuarioDAO.findByPK(1l);
		u.addTicket(item);
		
		usuarioDAO.update(u);
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		Ticket item = new Ticket();
		item.setNombreTienda("Walmart");
		item.setSucursal("Plaza Jardin");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, Calendar.APRIL);
		c.set(Calendar.YEAR, 2019);

		item.setFecha(c.getTime());
		item.setHora(new Date());
		item.setSubtotal(587.16);
		item.setIva(111.84);
		item.setTotal(699.00);
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(new ObjectMapper().writeValueAsString(item));

		System.out.println( stringBuilder.toString() );
		
	}
}
