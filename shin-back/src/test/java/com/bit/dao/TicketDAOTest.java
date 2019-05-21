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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class TicketDAOTest {

	@Autowired
	private TicketDAO ticketDAO;

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
		
		Ticket item = ticketDAO.findByPK(1l);
		System.out.printf("Ticket: %s %s %s \n", item.getIdTicket());
		System.out.println(item.getProductos().isEmpty() ? "No tiene promoción" : "Producto(s) con promoción:");

		for (Producto p : item.getProductos()) {
			System.out.printf(" - %s %s %s \n", p.getNombreProducto(), p.getCatalogoMarca(), p.getCatalogoTipoProducto());
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
		item.setSucursal("Plaza Jardín");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 21);
		c.set(Calendar.MONTH, Calendar.MAY);
		c.set(Calendar.YEAR, 2019);
		
		item.setFecha(c.getTime());
		item.setHora(new Date());
		item.setSubtotal(91.56);
		item.setIva(17.44);
		item.setTotal(109.00);

		Producto p1 = new Producto();
		p1.setIdProducto(1L);
		
		Producto p2 = new Producto();
		p2.setIdProducto(2L);
		
		Producto p3 = new Producto();
		p3.setIdProducto(3L);
		
		Producto p4 = new Producto();
		p4.setIdProducto(4L);
		
		Producto p5 = new Producto();
		p5.setIdProducto(9L);
		
		Producto p6 = new Producto();
		p5.setIdProducto(10L);
		
		List<Producto> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		item.setProductos(list);
		
		ticketDAO.save(item);
	}
	
	@Test
	public void objetosTest() {
		Ticket t1 = new Ticket();
		t1.setNombreTienda("Mi Tiendita");
		t1.setSucursal("La Perla");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 07);
		c.set(Calendar.MONTH, Calendar.MAY);
		c.set(Calendar.YEAR, 2019);

		t1.setFecha(c.getTime());
		t1.setHora(new Date());
		t1.setSubtotal(15.00);
		t1.setIva(2.25);
		t1.setTotal(17.25);
		
		System.out.println("Tienda: " + t1.getNombreTienda() + "\n Sucursal: " + t1.getSucursal() +
				"\n Fecha: " + t1.getFecha() + "\n Hora: " + t1.getHora() + "\n Subtotal: " + t1.getSubtotal() +
				"\n Iva 15%: " + t1.getIva() + "\n Total: " + t1.getTotal());

		ticketDAO.save(t1);
	}

}
