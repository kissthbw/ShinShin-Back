package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.TicketAnalizer;
import com.bit.dao.TicketDAO;
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

		item = ticketDAO.save(item);
		rsp.setId(item.getIdTicket());
		return rsp;
	}

	@Override
	public OCRTicketRSP analizarOCR(OCRTicketRQT rqt) {
		OCRTicketRSP rsp = new OCRTicketRSP();
		
		if( rqt.getLineas().isEmpty() ) {
			rsp.setCode(202);
			rsp.setMessage("No existen elementos para analizar");
			
			return rsp;
		}
		
		rsp = TicketAnalizer.analize(rqt.getLineas());
		
		List<Producto> productos = productoService.getProductosPorIDYEmpresa(rsp.getLineas(), 0);
		
		if( productos.isEmpty() ) {
			rsp.setCode(203);
			rsp.setMessage("No se encontraron productos validos");
			
			return rsp;
		}
		
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		rsp.setProductos(productos);
		
		return rsp;
	}
}
