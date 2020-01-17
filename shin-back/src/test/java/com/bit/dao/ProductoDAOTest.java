package com.bit.dao;

import java.math.BigInteger;
import java.util.Calendar;
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

import com.bit.config.WebConfig;
import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.ProductosTiendas;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.ProductoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
public class ProductoDAOTest {
	
	private static final Logger log = LoggerFactory.getLogger(ProductoDAOTest.class);

	@Autowired
	private ProductoDAO productoDAO;
	
	@Autowired
	private ProductosTiendasDAO productosTiendasDAO;
	
	@Autowired
	private ProductoService productoService;

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
	public void save() {
		Producto item = new Producto();
		CatalogoMarca marca = new CatalogoMarca();

		marca.setIdCatalogoMarca(7l);
		// marca.setNombreMarca( "Roku" );

		CatalogoTipoProducto tipo = new CatalogoTipoProducto();
		tipo.setIdCatalogoTipoProducto(2L);
		// tipo.setNombreTipoProducto( "Streaming" );

		// Marca marca = marcaDAO.findByPK(1l);
		// TipoProducto tipo = tipoProductoDAO.findByPK(1l);

		item.setNombreProducto("Nintendo Switch");
		item.setPrecio(6999.00);
		item.setCodigoBarras("978128713");
		item.setPresentacion("");
		item.setContenido("1 pieza");
		item.setDescripcion("");
		item.setAplicaPromocion(true);

		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 2);
		item.setVigenciaPromocion(c.getTime());
		item.setUrlImagenProducto("/home/img/chrome.jpg");
		item.setCantidadBonificacion(100.00);
		item.setCatalogoMarca(marca);
		item.setCatalogoTipoProducto(tipo);

		productoDAO.save(item);
	}

	@Transactional
	@Test
	public void getProductosPorTipo() {
		String tipoProducto = "Lacteos";
		String nombreProducto = "Leche Entera";
		List<Producto> list = productoDAO.getProductosPorTipo(tipoProducto, nombreProducto);
		for (Producto p : list) {
			System.out.println("Producto: " + p.getNombreProducto() + " Precio: " + p.getPrecio());
		}
	}

	@Transactional
	@Test
	public void getProductosPorMarca() {
		String marca = "Alpura";
		String nombreProducto = "Leche Entera";
		List<Producto> list = productoDAO.getProductosPorMarca(marca, nombreProducto);
		for (Producto p : list) {
			System.out.println("Producto: " + p.getNombreProducto() + " Precio: " + p.getPrecio());
		}
	}
	
	@Transactional
	@Test
	public void getProductosPorNombre() {
		String nombreProducto = "Leche Entera";
		List<Producto> list = productoDAO.getProductosPorNombre(nombreProducto);
		for (Producto p : list) {
			System.out.println("Producto: " + p.getNombreProducto() + " Precio: " + p.getPrecio());
		}
	}

	@Transactional
	@Test
	public void paginacion() {
//		int maxResults = 10;
//		long total = productoDAO.getTotal();
//		
//		long pages = total / maxResults;
//		
//		List<Producto> list = null;
//		
//		for( int page = 0; page <= pages; page++ ) {
//			System.out.println( "Pagina: " + (page +1) );
//			
//			list = productoDAO.getProductosPorPaginas(page, maxResults);
//			for (Producto p : list) {
//				System.out.println( p.getIdProducto() + " - Producto: " + p.getNombreProducto() + " Precio: " + p.getPrecio());
//			}
//
//		}
		
		ListItemsRSP rsp = productoService.getProductosPorPaginas(4, 5);

		for( Producto p : rsp.getProductos()) {
			System.out.println( p.getIdProducto() + " - Producto: " + p.getNombreProducto() + " Precio: " + p.getPrecio());
		}
		
	}
	
	//Asignacion de tiendas
	@Test
	@Transactional
	@Rollback(value=false)
	public void asignarTiendaTest() {
		//En la pantalla de producto se debe mostrar 
		//las n tiendas que se hayan dado de alta en el catalogo de tiendas
		//Se debe permitir ingresar la CLAVE del producto para esa tienda en particular
		
		Producto p = productoDAO.findByPK(24L);
		ProductosTiendas tienda = new ProductosTiendas();
//		tienda.setIdCatalogoTienda(3L);
		tienda.setProducto(p);
		tienda.setProductoTienda("ESPECIAL");
		
		p.addTienda(tienda);
		productoDAO.update(p);
//		productosTiendasDAO.save(tienda);
		
//		for(ProductosTiendas t : p.getTiendas()) {
//			System.out.println( t.getProducto().getIdProducto() + "-" + t.getProductoTienda() );
//		}
		
		
		
//		ProductosTiendas t = new ProductosTiendas();
//		t.setIdCatalogoTienda(2L);
//		t.setProductoTienda("S/N");
//		
//		p.addTienda( t );
//		productoDAO.update(p);
		
//		List<Producto> list = productoDAO.getProductos();
//		
//		for( Producto p : list ) {
//			System.out.println(  p.getIdProducto() + " - " +  p.getNombreProducto() );
//			System.out.println( "Disponible en: " + p.getTiendas().size() + " tiendas");
//			
//		}
	}
	
	public static void main( String[] args ) {
		//Ejemplo 
		//70 registros, con max registros por pagina de 30
		int page = 7;
		int maxResults = 10;
		int total = 70;
		
		int tmp =  (maxResults * page);
		int diferencia = total - tmp;
		
		System.out.println( "Registros pendientes: " + diferencia + ", hasNextPage?: " + ( diferencia == 0 ? "false" : "true" ));
	}
	
	@Test
	@Transactional
	public void productosEscaneados() {
		BigInteger total = productoDAO.obtieneTotalEscaneosProductos();
		System.out.println("Productos escaneados: " + total);
	}
	
	@Test
	@Transactional
	public void productosEscaneadosPorDia() {
		List<Object> total = productoDAO.obtieneTotalEscaneosProductosPorDia();
		for(int i = 0; i <= total.size(); i++) {
			Object t[] = (Object[]) total.get(i);
			System.out.println("Productos: " + t[0] + " dia: " + t[1] + " fecha: " + t[2]);
		}
	}
	
	@Test
	@Transactional
	public void productosEscaneadosPorSemana() {
		List<Object> total = productoDAO.obtieneTotalEscaneosProductosPorSemana();
		for(int i = 0; i <= total.size(); i++) {
			Object t[] = (Object[]) total.get(i);
			System.out.println("Productos: " + t[0] + " semana: " + t[1] + " año: " + t[2]);
		}
	}
	
	@Test
	@Transactional
	public void productosEscaneadosPorMes() {
		List<Object> total = productoDAO.obtieneTotalEscaneosProductosPorMes();
		for(int i = 0; i <= total.size(); i++) {
			Object t[] = (Object[]) total.get(i);
			System.out.println("Productos: " + t[0] + " mes: " + t[1] + " año: " + t[2]);
		}
	}
}
