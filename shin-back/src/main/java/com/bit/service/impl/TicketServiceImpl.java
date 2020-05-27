package com.bit.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.dao.TicketDAO;
import com.bit.exception.TicketException;
import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.Ticket;
import com.bit.model.dto.ImageItem;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.TicketItem;
import com.bit.model.dto.request.OCRTicketRQT;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.dto.response.OCRTicketRSP;
import com.bit.service.ProductoService;
import com.bit.service.TicketService;
import com.bit.service.analizer.Analizer;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class TicketServiceImpl implements TicketService {
	
	private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	@Autowired
	private TicketDAO ticketDAO;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private Analizer analizer;

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
		
		log.info( "Subiendo imagenes a cloudinary" );
		if ( null != item.getTicketPhotos() ) {
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
					  "cloud_name", "shingshing",
					  "api_key", "657472936977876",
					  "api_secret", "cZ8wZWzSvTqXdqBO7P1e62xnzVY")); 
			
			if( !item.getTicketPhotos().isEmpty() ) {
				
				for( ImageItem image : item.getTicketPhotos() ) {
					
					if( !image.equals( "" ) ) {
						try {
							Map params = ObjectUtils.asMap(
									   "public_id", "shingshing/tickets/" + image.getIdentifier(), 
									   "overwrite", true
									);
							
							log.info( "Subiendo imagen de usuario" );
							byte[] bytes = Base64.getMimeDecoder().decode( image.getImageData() );
							
							Map uploadResult = cloudinary.uploader().upload(bytes, params);
							String url2 = (String) uploadResult.get("url");
							log.info( "Url: {}", url2 );
//							entity.setImgUrl(url2);
						} catch (Exception e) {
							log.error( "Ocurrio un error al subir imagen: {}", e );
						}
					}
				}
			}
		}
		
		item = ticketDAO.save(item);
		rsp.setId(item.getIdTicket());
		return rsp;
	}

	@Override
	public OCRTicketRSP analizarOCR(OCRTicketRQT rqt, boolean fake) {
		OCRTicketRSP rsp = new OCRTicketRSP();
		
		log.info( "Analizando lineas: " + rqt.getLineas() );
		
		if( rqt.getLineas().isEmpty() ) {
			rsp.setCode(202);
			rsp.setMessage("No existen elementos para analizar");
			
			return rsp;
		}
		
		try {
//			rsp = AnalizerImpl.analize(rqt.getLineas(), fake);
			rsp = analizer.analize(rqt.getLineas(), fake);
			log.info("Ticket correcto");
		} catch (TicketException e) {
			log.error("Error al analizar ticket", e);
			rsp.setCode( e.getCode() );
			rsp.setMessage( e.getMessage() );
			
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
		
		
		log.info( "Guardando informacion de ticket con id de transaccion: {}", rsp.getTransaccion() );
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
			pTemp.setBanner(item.getBanner());
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

	/*
	 * Metodos relacionados a las paginas de estadisticas-tickets-detalle, 
	 * estadisticas-tickets-detalle-segundoDetalle
	 */
	@Override
	public List<TicketItem> obtieneTicketsPorFecha(String fecha) {
		List<TicketItem> list = ticketDAO.obtieneTicketsPorFecha(fecha);
		
		for( TicketItem t : list ) {
			t.setFechaFormateada( Utils.formatDateToString(t.getFecha(), "dd-MMM-yyyy") );
			t.setImporteFormateado( Utils.formatNumeros(t.getImporte(), "$###,###,###.00") );
		}
		
		return list;
	}

	@Override
	public List<TicketItem> obtieneDetalleTicketPorId(Integer idTicket) {
		List<TicketItem> list = ticketDAO.obtieneDetalleTicketPorId(idTicket);
		
		for( TicketItem t : list ) {
			t.setImporteFormateado( Utils.formatNumeros(t.getImporte(), "$###,###,###.00") );
		}
		
		return list;
	}
}
