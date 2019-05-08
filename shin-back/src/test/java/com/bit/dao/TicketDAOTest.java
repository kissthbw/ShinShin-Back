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
			System.out.printf(" - %s %s %s \n", p.getNombreProducto(), p.getCatalogoMarca(), p.getTipoProducto());
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
		item.setNombreTienda("Sanborns");
		item.setSucursal("Plaza Jardín");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 10);
		c.set(Calendar.MONTH, Calendar.APRIL);
		c.set(Calendar.YEAR, 2019);

		item.setFecha(c.getTime());
		item.setHora(new Date());
		item.setSubtotal(200.00);
		item.setIva(30.00);
		item.setTotal(230.00);

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
