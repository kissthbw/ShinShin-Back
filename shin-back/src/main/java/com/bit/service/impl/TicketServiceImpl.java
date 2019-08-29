package com.bit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Analizer;
import com.bit.dao.TicketDAO;
import com.bit.exception.TicketException;
import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.Ticket;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.request.OCRTicketRQT;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.dto.response.OCRTicketRSP;
import com.bit.service.ProductoService;
import com.bit.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	
	private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	@Autowired
	private TicketDAO ticketDAO;
	
	@Autowired
	private ProductoService productoService;

	@Override
	@Transactional
	public ListItemsRSP getTickets() {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obteniendo lista de tickets");
		
		List<Ticket> list = ticketDAO.getTickets();
		
		rsp.setTickets(list);
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse registrarTickets(Ticket item) {
		
		log.info("Registrando ticket");
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		log.info( "Verificando id transaccion de ticket y tienda: {}, {}", 
				item.getTicket_tienda(), item.getTicket_transaccion() );
		
		item = ticketDAO.save(item);
		rsp.setId(item.getIdTicket());
		return rsp;
	}

	@Override
	public OCRTicketRSP analizarOCR(OCRTicketRQT rqt, boolean fake) {
		OCRTicketRSP rsp = new OCRTicketRSP();
		
		if( rqt.getLineas().isEmpty() ) {
			rsp.setCode(202);
			rsp.setMessage("No existen elementos para analizar");
			
			return rsp;
		}
		
		try {
			rsp = Analizer.analize(rqt.getLineas(), fake);
			log.info("Ticket correcto");
		} catch (TicketException e) {
			log.error("Error al analizar ticket", e);
			rsp.setCode(204);
			rsp.setMessage("No se poseee informacion suficiente para analizar");
			
			return rsp;
		}
		
		//Guardar información del ticket
		List<Producto> productos = productoService.getProductosPorIDYEmpresa(rsp.getLineas(), 0);
		
		log.info( "Ticket con id de transaccion: {}", rsp.getTransaccion() );
		
		if( productos.isEmpty() ) {
			rsp.setCode(203);
			rsp.setMessage("No se encontraron productos validos");
			
			return rsp;
		}
		
		
		log.info( "Guardando indormacio de ticket con id de transaccion: {}", rsp.getTransaccion() );
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		rsp.setProductos(productos);
		
		return rsp;
	}
	
	@Override
	@Transactional
	public ListItemsRSP getDetalleTicket(Long id) {
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		log.info("Obteniendo ticket con id: {}", id);
		
		Ticket item = ticketDAO.findByPK(id);
		if ( null != item ) {
			
			rsp.setProductos( transform(item.getProductos()) );
		}
		else {
			rsp.setMessage("No existe ticket");
			rsp.setCode(500);
		}
//		List<Ticket> list = ticketDAO.getTickets();
		
		
		return rsp;
	}
	
	/*
	 * Helper methods
	 */
	private List<Producto> transform(List<Producto> list) {

		List<Producto> tmp = new ArrayList<>();

		for (Producto item : list) {
			Producto pTemp = new Producto();
			CatalogoMarca m = new CatalogoMarca();
			CatalogoTipoProducto t = new CatalogoTipoProducto();
			CatalogoTienda ct = new CatalogoTienda();
			pTemp.setIdProducto(item.getIdProducto());
			pTemp.setNombreProducto(item.getNombreProducto());
			pTemp.setPrecio(item.getPrecio());
			pTemp.setCodigoBarras(item.getCodigoBarras());
			pTemp.setPresentacion(item.getPresentacion());
			pTemp.setContenido(item.getContenido());
			pTemp.setDescripcion(item.getDescripcion());
			pTemp.setAplicaPromocion(item.isAplicaPromocion());
			pTemp.setVigenciaPromocion(item.getVigenciaPromocion());
			pTemp.setUrlImagenProducto(item.getUrlImagenProducto());
			pTemp.setCantidadBonificacion(item.getCantidadBonificacion());
			pTemp.setBanner(item.isBanner());
			pTemp.setColorBanner(item.getColorBanner());

			m.setIdCatalogoMarca(item.getCatalogoMarca().getIdCatalogoMarca());
			m.setNombreMarca(item.getCatalogoMarca().getNombreMarca());
			pTemp.setCatalogoMarca(m);

			t.setIdCatalogoTipoProducto(item.getCatalogoTipoProducto().getIdCatalogoTipoProducto());
			t.setNombreTipoProducto(item.getCatalogoTipoProducto().getNombreTipoProducto());
			pTemp.setCatalogoTipoProducto(t);

			ct.setIdCatalogoTienda(item.getCatalogoTienda().getIdCatalogoTienda());
			ct.setNombreTienda(item.getCatalogoTienda().getNombreTienda());
			ct.setImagenTienda(item.getCatalogoTienda().getImagenTienda());
			pTemp.setCatalogoTienda(ct);

			tmp.add(pTemp);
		}

		return tmp;
	}
}
