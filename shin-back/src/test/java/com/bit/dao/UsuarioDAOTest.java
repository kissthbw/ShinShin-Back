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
import com.bit.model.Ticket;
import com.bit.model.Usuario;
import com.bit.service.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class UsuarioDAOTest {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Autowired
	private ProductoDAO productoDAO;

	@Autowired
	private TicketDAO ticketDAO;

	@Autowired
	private UsuarioService usuarioService;

	@Transactional
	@Test
	public void crudTest() {
		Usuario u = usuarioDAO.findByPK(1L);
		System.out.println(u.getUsuario());
	}

	@Transactional
	@Test
	@Rollback(false)
	public void guardarUsuarios() {

		Usuario u = new Usuario();
		u.setNombre("Adrian");
		u.setApPaterno("Osorio");
		u.setApMaterno("Alvarez");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 02);
		c.set(Calendar.MONTH, Calendar.OCTOBER);
		c.set(Calendar.YEAR, 1984);

		u.setFechaNac(c.getTime());
		u.setUsuario("AdrianBoy");
		u.setContrasenia("masterBoy");
		u.setCalle("Pataguas");
		u.setNumeroExt("115");
		u.setNumeroInt("");
		u.setColonia("La Perla");
		u.setCodigoPostal("57820");
		u.setDelMun("Nezahualcóyotl");
		u.setEstado("Estado de México");
		u.setTelLocal("+5215534714616");
		u.setEstatusActivacion(false);
		u.setCodigoVerificacion("");

		usuarioService.registrarUsuarios(u);
	}

	@Transactional
	@Test
	@Rollback(false)
	public void actualizarUsuarios() {

		Usuario item = usuarioDAO.findByPK(12l);
		item.setContrasenia("mexicangreat");

		usuarioService.actualizarUsuarios(item);

	}

	@Transactional
	@Test
	@Rollback(false)
	public void activarUsuarios() {
		
		Usuario item = new Usuario();
		item.setIdUsuario(5l);
		item.setCodigoVerificacion("5215");

		usuarioService.activarUsuarios(item);
	}

	@Transactional
	@Test
	public void findById() {
		Usuario item = usuarioDAO.findByPK(1l);

		System.out.printf("Usuario: %s %s %s \n", item.getNombre(), item.getApPaterno(), item.getApMaterno());

		System.out.println(item.getProductos().isEmpty() ? "No tiene productos favoritos" : "Productos favoritos:");

		System.out.println(item.getTickets().isEmpty() ? "No tiene tickets guardados" : "Tickets guardados:");

		for (Producto p : item.getProductos()) {
			System.out.printf(" - %s %s %s \n", p.getNombreProducto(), p.getCatalogoMarca().getNombreMarca(),
					p.getCatalogoTipoProducto().getNombreTipoProducto());
		}

		for (Ticket t : item.getTickets()) {
			System.out.printf(" - %s %s %s \n", t.getNombreTienda(), t.getSucursal(), t.getFecha(), t.getHora(),
					t.getTotal());
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

		usuarioDAO.save(item);
	}

	@Transactional
	@Test
	@Rollback(false)
	public void saveHistoricoTickets() {
		Usuario item = usuarioDAO.findByPK(1l);
		Ticket t = ticketDAO.findByPK(4l);
		List<Ticket> list = new ArrayList<>();
		list.add(t);

		item.addTicket(t);

		usuarioDAO.save(item);
	}

	@Transactional
	@Test
	@Rollback(false)
	public void save() {
		Usuario item = new Usuario();
		item.setNombre("Juan");
		item.setApPaterno("Osorio");
		item.setApMaterno("Alvarez");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 11);
		c.set(Calendar.MONTH, Calendar.SEPTEMBER);
		c.set(Calendar.YEAR, 1983);

		item.setFechaNac(c.getTime());
		item.setUsuario("kissthbw");
		item.setContrasenia("kiss2101");
		item.setCalle("Virgen de los Remedios");
		item.setNumeroExt("71");
		item.setNumeroInt("");
		item.setColonia("Virgencitas");
		item.setCodigoPostal("57300");
		item.setDelMun("Nezahualcoyotl");
		item.setEstado("Estado de MÃ©xico");
		item.setTelLocal("51121423");
		item.setEstatusActivacion(false);
		item.setCodigoVerificacion("");

		usuarioDAO.save(item);
	}
}
