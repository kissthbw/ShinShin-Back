package com.bit.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.bit.common.Analizer;
import com.bit.common.Utils;
import com.bit.config.WebConfig;
import com.bit.exception.TicketException;
import com.bit.model.Producto;
import com.bit.model.Ticket;
import com.bit.model.Usuario;
import com.bit.model.dto.request.OCRTicketRQT;
import com.bit.model.dto.response.OCRTicketRSP;
import com.bit.service.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class TicketDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(TicketDAOTest.class);
	
	@Autowired
	private TicketDAO ticketDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private TicketService ticketService;
	
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
//		Usuario u = usuarioDAO.findByPK(1l);
		Usuario u = new Usuario();
		u.setIdUsuario(1L);
		
		Ticket item = new Ticket();
		item.setNombreTienda("Walmart");
		item.setSucursal("Plaza Jardin");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 21);
		c.set(Calendar.MONTH, Calendar.MAY);
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
		item.setSubtotal(91.56);
		item.setIva(17.44);
		item.setTotal(109.00);

		Producto p1 = new Producto();
		p1.setIdProducto(1L);
		
		Producto p2 = new Producto();
		p2.setIdProducto(2L);
		
//		Producto p3 = new Producto();
//		p3.setIdProducto(3L);
//		
//		Producto p4 = new Producto();
//		p4.setIdProducto(4L);
//		
//		Producto p5 = new Producto();
//		p5.setIdProducto(9L);
		
//		Producto p6 = new Producto();
//		p5.setIdProducto(10L);
		
		List<Producto> list = new ArrayList<>();
		list.add(p1);
//		list.add(p2);
//		list.add(p3);
//		list.add(p4);
//		list.add(p5);
//		list.add(p6);
		item.setProductos(list);
		u.addTicket(item);
		try {
			System.out.println( Utils.objectToJSON(u) );
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usuarioDAO.update(u);
//		ticketDAO.save(item);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void updateTicketTest() {
		// Agregar productos a un ticket
		Ticket item = ticketDAO.findByPK(2l);
		Producto p1 = new Producto();
		p1.setIdProducto(2L);
		item.addProducto(p1);
		ticketDAO.update(item);

		// Asignar ticket a usuario
		Usuario u = usuarioDAO.findByPK(1l);
		u.addTicket(item);

		usuarioDAO.update(u);
	}

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
		System.out.println("Tienda: " + t1.getNombreTienda() + "\n Sucursal: " + t1.getSucursal() + "\n Fecha: "
				+ t1.getFecha() + "\n Hora: " + t1.getHora() + "\n Subtotal: " + t1.getSubtotal() + "\n Iva 15%: "
				+ t1.getIva() + "\n Total: " + t1.getTotal());

		ticketDAO.save(t1);
	}
	
	@Test
	@Transactional
	public void analizaOCR() throws JsonProcessingException {
		
		BufferedReader buffered = null;
		List<String> lineas = new ArrayList<>();
		String line = null;
		
		try {
			buffered = new 
					BufferedReader( 
							new FileReader("/Users/juanosorioalvarez/Documents/Bit/ShinShin/ocr-ticket-soriana.txt") );
			
			while((line = buffered.readLine()) != null) {
                lineas.add(line);
            }   
			
			if( buffered != null ){
				buffered.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		OCRTicketRQT rqt = new OCRTicketRQT();
		rqt.setLineas(lineas);
		
		OCRTicketRSP rsp = ticketService.analizarOCR(rqt, false);
		
		for( Producto p : rsp.getProductos() ) {
			System.out.println( p.getNombreProducto() + " - " + p.getCantidadBonificacion() );
		}
	}
	
	public static void main(String[] args) {
		System.out.println( "Inicio: " + new Date() );
		BufferedReader buffered = null;
		List<String> lineas = new ArrayList<>();
		String line = null;
		
		try {
			buffered = new 
					BufferedReader(
							new FileReader("/Users/juanosorioalvarez/Documents/Bit/ShinShin/ocr-ticket-7eleven-3.txt") );
//							new FileReader("/Users/juanosorioalvarez/Documents/Bit/ShinShin/ocr-ticket-walmart-3.txt") );
//							new FileReader("/Users/juanosorioalvarez/Documents/Bit/ShinShin/ocr-ticket-walmart-2.txt") );
//							new FileReader("/Users/juanosorioalvarez/Documents/Bit/ShinShin/ocr-ticket-aurrera.txt") );
//							new FileReader("/Users/juanosorioalvarez/Documents/Bit/ShinShin/ocr-ticket-superama.txt") );
//							new FileReader("/Users/juanosorioalvarez/Documents/Bit/ShinShin/ocr-ticket-oxxo.txt") );
			
			while((line = buffered.readLine()) != null) {
                lineas.add(line);
            }   
			
			if( buffered != null ){
				buffered.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			
			Analizer.analize(lineas, false);
			System.out.println( "Fin: " + new Date() );
		} catch (TicketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
