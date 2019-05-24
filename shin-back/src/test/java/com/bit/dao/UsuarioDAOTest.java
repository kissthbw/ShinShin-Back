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
		u.setDelMun("Nezahualcoyotl");
		u.setEstado("Estado de Mexico");
		u.setTelMovil("+5215534714616");
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

		System.out.println(
				item.getProductosFavoritos().isEmpty() ? "No tiene productos favoritos" : "Productos favoritos:");

		for (Producto p : item.getProductosFavoritos()) {
			System.out.printf(" - %s %s %s \n", p.getNombreProducto(), p.getCatalogoMarca().getNombreMarca());
		}

		System.out.println(item.getTickets().isEmpty() ? "No tiene tickets guardados" : "Tickets guardados:");
		for (Ticket t : item.getTickets()) {
			System.out.printf(" - %s %s %s \n", t.getNombreTienda(), t.getSucursal(), t.getFecha(), t.getHora(),
					t.getTotal());

			int total = t.getProductos().size();
			String etiqueta = total == 1 ? "producto" : "productos";
			System.out.printf(" - Este ticket contiene %d %s de promoción. \n", t.getProductos().size(), etiqueta);

			for (Producto p : t.getProductos()) {
				System.out.printf("   - Producto: %s con $ %.2f de promoción. \n", p.getNombreProducto(),
						p.getCantidadBonificacion());
			}
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

		item.addProductoFavorito(p);

		usuarioDAO.save(item);
	}

	@Transactional
	@Test
	@Rollback(false)
	public void saveHistoricoTickets() {
		Usuario item = usuarioDAO.findByPK(1l);
		Ticket t = ticketDAO.findByPK(1l);
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
		item.setNombre("Adrian");
		item.setApPaterno("Osorio");
		item.setApMaterno("Alvarez");

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 02);
		c.set(Calendar.MONTH, Calendar.OCTOBER);
		c.set(Calendar.YEAR, 1984);

		item.setFechaNac(c.getTime());
		item.setTelMovil("+5215540150544");
		item.setCorreoElectronico("masteboy@example.com");
		item.setUsuario("masterboy84");
		item.setContrasenia("qwerty123");
		item.setCalle("Pataguas");
		item.setNumeroExt("115");
		item.setNumeroInt("");
		item.setColonia("La Perla");
		item.setCodigoPostal("57820");
		item.setDelMun("Nezahualcoyotl");
		item.setEstado("Estado de México");
		item.setEstatusActivacion(false);
		item.setCodigoVerificacion("");

		usuarioDAO.save(item);
	}

	@Transactional
	@Test
	@Rollback(false)
	public void asignarTicket() {

		Usuario item = usuarioDAO.findByPK(1l);
		Ticket ticket = ticketDAO.findByPK(5l);
		item.addTicket(ticket);

		System.out.println("Nombre: " + item.getNombre() + " " + item.getApPaterno() + " \n" + "Usuario: "
				+ item.getUsuario() + " Id Ticket: " + ticket.getIdTicket() + "\n Sucursal: " + ticket.getSucursal()
				+ "\n Tienda: " + ticket.getNombreTienda());

	}

	@Transactional
	@Test
	@Rollback(false)
	public void imprimirInformacionUsuario() {
		Usuario u = usuarioDAO.findByPK(1l);
		System.out.println("Nombre: " + u.getNombre() + " " + u.getApPaterno() + " " + u.getApMaterno() + "\n"
				+ "Usuario: " + u.getUsuario());

		List<Ticket> list = u.getTickets();

		for (Ticket t : list) {

			System.out.println("Ticket: " + t.getIdTicket() + "Tienda: " + t.getNombreTienda() + "Sucursal: "
					+ t.getSucursal() + "Total: " + t.getTotal());

			for (Producto p : t.getProductos()) {

				System.out.println("Producto: " + p.getNombreProducto() + "Contenido " + p.getContenido() + " Precio: "
						+ p.getPrecio());

				double totalBonificacion = 0;
				for (Producto pb : t.getProductos()) {
					totalBonificacion += pb.getCantidadBonificacion();

					System.out.println("Producto: " + pb.getNombreProducto() + " tiene: $"
							+ pb.getCantidadBonificacion() + " de bonificacion");

				}

				System.out.println("Bonificaci�n total: $" + totalBonificacion);
			}
		}
	}
}
