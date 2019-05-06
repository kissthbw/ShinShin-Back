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
			System.out.printf(" - %s %s %s \n", p.getNombreProducto(), p.getMarca(), p.getTipoProducto());
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
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.MONTH, Calendar.APRIL);
		c.set(Calendar.YEAR, 2019);

		item.setFecha(c.getTime());
		item.setHora(new Date());
		item.setSubtotal(587.16);
		item.setIva(111.84);
		item.setTotal(699.00);

		ticketDAO.save(item);
	}

}
