package com.bit.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bit.communication.CloundinaryService;
import com.bit.communication.MediosComunicacionService;
import com.bit.dao.ProductoDAO;
import com.bit.dao.ProductoFavoritoDAO;
import com.bit.dao.ProductoValoracionDAO;
import com.bit.dao.ProductosTiendasDAO;
import com.bit.dao.SugerenciaProductoDAO;
import com.bit.exception.CommunicationException;
import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.ProductoFavorito;
import com.bit.model.ProductoValoracion;
import com.bit.model.ProductosTiendas;
import com.bit.model.SugerenciaProducto;
import com.bit.model.Usuario;
import com.bit.model.dto.EMailDTO;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.DetalleProducoRSP;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.model.report.ProductoReport;
import com.bit.service.ProductoService;
import com.cloudinary.utils.ObjectUtils;
import com.sendgrid.helpers.mail.objects.Personalization;

@Service
public class ProductoServiceImpl implements ProductoService {

	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

	@Autowired
	private ProductoDAO productoDAO;
	
	@Autowired
	private ProductosTiendasDAO productosTiendasDAO;
	
	@Autowired
	private SugerenciaProductoDAO sugerenciaProductoDAO;

	@Autowired
	private CloundinaryService cloundinaryService;
	
	@Autowired
	private ProductoFavoritoDAO productoFavoritoDAO;
	
	@Autowired
	private ProductoValoracionDAO productoValoracionDAO;
	
	@Autowired
	private MediosComunicacionService mediosComunicacionService;
	
	@Override
	@Transactional
	public ListItemsRSP getProductos() {

		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		log.info("Obteniento lista de productos de la base de datos");

		List<Producto> list = productoDAO.getProductos();
		
		rsp.setProductos(transform(list));
		return rsp;
	}
	
	@Override
	@Transactional
	public ListItemsRSP getProductosPorTipoProducto(long idCatalogoTipoProducto) {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		log.info("Obteniento lista de productos de la base de datos");

		List<Producto> list = productoDAO.getProductosPorTipoProducto(idCatalogoTipoProducto);
		
		rsp.setProductos(transform(list));
		return rsp;
		
	}
	
	@Override
	@Transactional
	public DetalleProducoRSP getDetalleProducto( long idProducto, long idUsuario ){
		
		DetalleProducoRSP rsp = new DetalleProducoRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");
		
		//Determina si es favorito para el usuario que lo esta consultando
		boolean isFavorite = productoFavoritoDAO.existeFavoritoPorProductoYUsuario(idProducto, idUsuario);
		rsp.setFavorite( isFavorite );
		
		//Determina el ranking asignado por el usuario
		ProductoValoracion pv = new ProductoValoracion();
		Producto p = new Producto();
		p.setIdProducto( idProducto );
		Usuario u = new Usuario();
		u.setIdUsuario( idUsuario );
		pv.setProducto(p);
		pv.setUsuario(u);
		
		List<ProductoValoracion> pvList = productoValoracionDAO.getValoracionPorProdcutoyUsuario(pv);
		for( ProductoValoracion i : pvList ) {
			rsp.setRanking( i.getValoracion() );
		}
		
		//Determina las tiendas donde esta disponible el producto
		List<ProductosTiendas> list = productosTiendasDAO.getTiendasPorProducto( idProducto );
		List<CatalogoTienda> result = new ArrayList<>();
		
		for( ProductosTiendas i : list ) {
			
			if( null != i.getProductoTienda() && !"".equals( i.getProductoTienda() ) ) {
				CatalogoTienda c = new CatalogoTienda();
				c.setNombreTienda( i.getCatalogoTienda().getNombreTienda() );
				c.setImagenTienda( i.getCatalogoTienda().getImagenTienda() );
				result.add(c);
			}
		}
		
		rsp.setTiendas(result);
		
		return rsp;
	}
	
	@Override
	@Transactional
	public ListItemsRSP getProductosPorPaginas(int page, int max) {
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		log.info("Obteniento lista de productos de la base de datos");

		long total = productoDAO.getTotal();
		rsp.setTotal(total);
		long pages = total / max;
		long tmp = pages * max;
		long dif = total - tmp;
		boolean hasMore = false;
		
		
		//Determinar numero de paginas
		if( dif > 0 ) {
//			hasMore = false;
			pages++;
		}
		
		//Determinar si existen mas paginas a mostrar
		//hasMore = true;
		if( ( page * max ) > total ) {
			hasMore = false;
		}
		else {
			hasMore = true;
		}
		
		if( page > pages ) {
			rsp.setCode(500);
			rsp.setMessage("Pagina fuera del rango");
		}
		
		rsp.setHasMore( hasMore );
		rsp.setPage( Long.valueOf(page) );
		rsp.setPages( pages );
		
		List<Producto> list = productoDAO.getProductosPorPaginas(page, max);

		rsp.setProductos(transform(list));
		return rsp;
	}
	
	@Override
	@Transactional
	public ListItemsRSP getBanners() {

		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		log.info("Obteniento lista de banners de la base de datos");

		List<Producto> list = productoDAO.getBanners();

		rsp.setProductos(transform(list));
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse registrarProductos(MultipartFile file, Producto item) {

		log.info("Registrando un nuevo producto en la base de datos");
		
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/productos/" + item.getNombreProducto(), 
				   "overwrite", true
				);		
		
		if ( !file.isEmpty() ) {
			try {
				log.info("Subiendo imagen de: {}", item.getNombreProducto());
				String url = cloundinaryService.uploadImage(file.getBytes(), params);
				item.setImgUrl( url );
			} catch (IOException e) {
				log.error("Ocurrio un error al subir imagen", e);
			}
		}
		
		//El color del producto viene el formato
		//rgb(241, 138, 49)
		//dejar solo 241, 138, 49
		item.setColorBanner( bannerColor( item.getColorBanner() ) );
		item.setActive(1);
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		//Analizando la lista de nombres para las tiendas
		for( ProductosTiendas t : item.getTiendas() ) {
			t.setProducto(item);
		}
		
		item = productoDAO.save(item);
		
		
		
		
		
		rsp.setId(item.getIdProducto());
		return rsp;
	}

	private String bannerColor(String color) {
		color = color.replace("rgb(", "").trim();
		color = color.replace(")", "").trim();
		color = color.replace(" ", "").trim();
		
		return color;
	}
	
	@Override
	@Transactional
	public SimpleResponse actualizarProductos(MultipartFile file, Producto item) {

		log.info("Modificando uno o varios valores de un producto de la base de datos");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

//		productoDAO.findByPK(item.getIdProducto());
		
		Map params = ObjectUtils.asMap(
				   "public_id", "shingshing/productos/" + item.getNombreProducto(), 
				   "overwrite", true
				);
		
		if ( !file.isEmpty() ) {
			try {
				log.info("Subiendo imagen de: {}", item.getNombreProducto());
				String url = cloundinaryService.uploadImage(file.getBytes(), params);
				item.setImgUrl( url );
			} catch (IOException e) {
				log.error("Ocurrio un error al subir imagen", e);
			}
		}
		
		//item.setColorBanner( bannerColor( item.getColorBanner() ) );

		//Analizando la lista de nombres para las tiendas
		for( ProductosTiendas t : item.getTiendas() ) {
			t.setProducto(item);
		}
		item.setActive(1);
		log.info("Color del banner "+item.getColorBanner());
		Producto t = productoDAO.update(item);
		rsp.setId(t.getIdProducto());
		return rsp;
	}

	@Override
	@Transactional
	public ListItemsRSP getProductosPorMarca(CatalogoMarca item, Producto i) {

		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		log.info("Obteniento lista de productos por marca de la base de datos");

		String marca = item.getNombreMarca();
		String nombreProducto = i.getNombreProducto();

		List<Producto> list = productoDAO.getProductosPorMarca(marca, nombreProducto);

		rsp.setProductos(transform(list));
		return rsp;
	}

	@Override
	@Transactional
	public ListItemsRSP getProductosPorTipo(CatalogoTipoProducto item, Producto i) {

		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		log.info("Obteniento lista de productos por tipo de producto de la base de datos");

		String tipoProducto = item.getNombreTipoProducto();
		String nombreProducto = i.getNombreProducto();

		List<Producto> list = productoDAO.getProductosPorTipo(tipoProducto, nombreProducto);
		
		rsp.setProductos(transform(list));
		return rsp;
	}

	@Override
	@Transactional
	public ListItemsRSP getProductosPorNombre(Producto i) {

		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		log.info("Obteniento lista de productos por nombre de producto de la base de datos");

		String nombreProducto = i.getNombreProducto();

		List<Producto> list = productoDAO.getProductosPorNombre(nombreProducto);
		
		rsp.setProductos(transform(list));
		return rsp;
	}
	
	@Override
	@Transactional
	public List<Producto> getProductosPorIDYEmpresa(List<String> items, int idEmpresa) {
		
		if( items.isEmpty() ) {
			return new ArrayList<Producto>();
		}
		
		log.info("Obteniento lista de productos: {}", items.toString());
		log.info("Obteniento lista de productos por identificador de tienda: {}", idEmpresa);

//		List<Producto> list = productoDAO.getProductosPorIDYEmpresa(items);
		log.info("Inicio: {}", new Date());
		List<ProductosTiendas> tmpList = productosTiendasDAO.getProductosPorIDTienda( null );
		List<Producto> productos = findProductosValidos(tmpList, items);
		log.info("Fin: {}", new Date());
		
//		List<Producto> productos = new ArrayList<>();
//		List<ProductosTiendas> list = productosTiendasDAO.getProductosPorIDYEmpresa(items);
//
//		for( ProductosTiendas p : list ) {
////			log.info( p.getProducto().getNombreProducto() );
//			productos.add( p.getProducto() );
//		}
		
		return transform(productos);
	}
	
	private List<Producto> findProductosValidos( List<ProductosTiendas> tmpList, List<String> items ) {
		log.info( "productos totales: {}", tmpList.size() );
		List<Producto> productos = new ArrayList<>();
		
		//Itera sobre los productos en BD y verifica si existen 
		for (ProductosTiendas e : tmpList) {
			for (String toFind : items) {
				if ( toFind.contains(e.getProductoTienda())  || e.getProductoTienda().contains(toFind) ) {
					log.info( "Producto encontrado" );
					productos.add( e.getProducto() );
				}
			}
		}
		
		return productos;
	}

	@Override
	@Transactional
	public Producto findById(Long id) {
		log.info("Buscando producto por id: {}", id);
		Producto item = productoDAO.findByPK(id);
		
		return transform(item);
	}
	
	private Producto transform( Producto entity ) {
		Producto item = new Producto();
		
		CatalogoMarca m = new CatalogoMarca();
		CatalogoTipoProducto t = new CatalogoTipoProducto();
		CatalogoTienda ct = new CatalogoTienda();
		
		item.setIdProducto(entity.getIdProducto());
		item.setNombreProducto(entity.getNombreProducto());
		item.setPrecio(entity.getPrecio());
		item.setCodigoBarras(entity.getCodigoBarras());
		item.setPresentacion(entity.getPresentacion());
		item.setContenido(entity.getContenido());
		item.setDescripcion(entity.getDescripcion());
		item.setAplicaPromocion(entity.isAplicaPromocion());
		item.setVigenciaPromocion(entity.getVigenciaPromocion());
		item.setUrlImagenProducto(entity.getUrlImagenProducto());
		item.setCantidadBonificacion(entity.getCantidadBonificacion());
		item.setBanner(entity.getBanner());
		item.setColorBanner( "rgb(" + entity.getColorBanner() + ")");
		item.setImgUrl( entity.getImgUrl() );
		switch (entity.getBanner()) {
		case 1:
			log.info("1");
			item.setTipoString("General");
			break;
		case 2:
			log.info("2");
			item.setTipoString("Popular");
			break;
		case 3:
			log.info("3");
			item.setTipoString("Principal");
			break;
		default:
			log.info("cero");
			item.setTipoString("Sin especificar");
			break;
		}
		
		m.setIdCatalogoMarca(entity.getCatalogoMarca().getIdCatalogoMarca());
		m.setNombreMarca(entity.getCatalogoMarca().getNombreMarca());
		item.setCatalogoMarca(m);
		
		t.setIdCatalogoTipoProducto(entity.getCatalogoTipoProducto().getIdCatalogoTipoProducto());
		t.setNombreTipoProducto(entity.getCatalogoTipoProducto().getNombreTipoProducto());
		item.setCatalogoTipoProducto(t);
		
		if( null != entity.getCatalogoTienda() ) {
			ct.setIdCatalogoTienda(entity.getCatalogoTienda().getIdCatalogoTienda());
			ct.setNombreTienda(entity.getCatalogoTienda().getNombreTienda());
			ct.setImagenTienda(entity.getCatalogoTienda().getImagenTienda());
			item.setCatalogoTienda(ct);
		}
		
		for( ProductosTiendas pt : entity.getTiendas() ) {
			ProductosTiendas i = new ProductosTiendas();
			CatalogoTienda c = new CatalogoTienda();
			c.setIdCatalogoTienda( pt.getCatalogoTienda().getIdCatalogoTienda() );
			c.setNombreTienda( pt.getCatalogoTienda().getNombreTienda() );
			
			i.setCatalogoTienda( c );
//			i.setProducto( pt.getProducto() );
			i.setIdProductoTienda( pt.getIdProductoTienda() );
			i.setProductoTienda( pt.getProductoTienda() );
			
			item.addTienda(i);
		}
		
		return item;
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
			pTemp.setImgUrl( item.getImgUrl() );
			switch (item.getBanner()) {
			case 1:
				log.info("1");
				pTemp.setTipoString("General");
				break;
			case 2:
				log.info("2");
				pTemp.setTipoString("Popular");
				break;
			case 3:
				log.info("3");
				pTemp.setTipoString("Principal");
				break;
			default:
				log.info("cero");
				pTemp.setTipoString("Sin especificar");
				break;
			}

			m.setIdCatalogoMarca(item.getCatalogoMarca().getIdCatalogoMarca());
			m.setNombreMarca(item.getCatalogoMarca().getNombreMarca());
			pTemp.setCatalogoMarca(m);

			t.setIdCatalogoTipoProducto(item.getCatalogoTipoProducto().getIdCatalogoTipoProducto());
			t.setNombreTipoProducto(item.getCatalogoTipoProducto().getNombreTipoProducto());
			pTemp.setCatalogoTipoProducto(t);

			if ( null != item.getCatalogoTienda() ) {
				ct.setIdCatalogoTienda(item.getCatalogoTienda().getIdCatalogoTienda());
				ct.setNombreTienda(item.getCatalogoTienda().getNombreTienda());
				ct.setImagenTienda(item.getCatalogoTienda().getImagenTienda());
				pTemp.setCatalogoTienda(ct);
			}
			

			tmp.add(pTemp);
		}
		
		return tmp;
	}

	@Override
	@Transactional
	public SimpleResponse registraSugerencia(SugerenciaProducto item) {
		log.info("Guardando sugerencia de producto: {}, del usuario: {}", item.getNombreProducto(), item.getIdUsuario());
		SimpleResponse rsp = new SimpleResponse();
		rsp.setCode(200);
		rsp.setMessage("Exito");
		
		EMailDTO data = new EMailDTO();
		data.setSubject( "Nueva sugerencia de producto" );
		data.setToAccount( "contacto@tradenial.com" );
//		data.setToAccount( "kissthbw@gmail.com" );
		
		Personalization personalization = new Personalization();

		
		personalization.addDynamicTemplateData("link", item.getNombreProducto());		
		data.setPersonalization( personalization );
		data.setTemplateId("d-053e00bef38e47d7a143572ebdeb26f7");

		try {
			mediosComunicacionService.sendCustomEmail(data);
		} catch (CommunicationException e) {
			log.error("", e);
		}
		
		sugerenciaProductoDAO.save(item);
		return rsp;
	}

	@Override
	@Transactional
	public List<ProductoReport> getAllProductoReport() {
		List<ProductoReport> list = new ArrayList<>();
		List<Producto> entities = productoDAO.getProductos();
		
		for( Producto e : entities ) {
			ProductoReport item = new ProductoReport(e.getNombreProducto(),
					e.getContenido(),
					e.getCatalogoMarca().getNombreMarca(),
					e.getCatalogoTipoProducto().getNombreTipoProducto(),
					getTipoString(e.getBanner()),
					"$"+Double.toString(e.getCantidadBonificacion())) ;
					
					
					
			list.add(item);
		}
		
		
		return list;
	}
	
	public String getTipoString(int tipo) {
		String tipoString="";
		switch (tipo) {
		case 1:
			log.info("1");
			tipoString="General";
			break;
		case 2:
			log.info("2");
			tipoString="Popular";
			break;
		case 3:
			log.info("3");
			tipoString="Principal";
			break;
		default:
			log.info("cero");
			tipoString="Sin especificar";
			break;
		}
		return tipoString;
	}
	
	@Override
	@Transactional
	public SimpleResponse eliminaProducto(Producto item) {
		log.info("Eliminado logico de producto con id: {}", item.getIdProducto());
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		item = productoDAO.findByPK( item.getIdProducto() );
		item.setActive(0);
		productoDAO.update(item);
		
		item = productoDAO.update(item);
		//int rows = catalogoTiendaDAO.eliminaTienda(item);
		//log.info("Filas afectadas "+rows);;
		rsp.setId(item.getIdProducto());
		return rsp;
	}
	
	/*
	 * Productos Favoritos
	 */
	@Override
	@Transactional
	public SimpleResponse agregarProductoFavoritoUsuario(ProductoFavorito item) {
		log.info( "Agregando producto: {} a usuario: {}", item.getProducto().getIdProducto(), item.getUsuario().getIdUsuario() );
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		productoFavoritoDAO.save( item );
		
		return rsp;
	}
	
	@Override
	@Transactional
	public SimpleResponse eliminarProductoFavoritoUsuario(ProductoFavorito item) {
		log.info( "Eliminando producto: {} a usuario: {}", item.getProducto().getIdProducto(), item.getUsuario().getIdUsuario() );
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		productoFavoritoDAO.deletePorProductoYUsuario(item.getProducto().getIdProducto(), item.getUsuario().getIdUsuario());
		
		return rsp;
	}

	@Override
	@Transactional
	public ListItemsRSP getProductosFavoritosPorUsuario(long idUsuario) {
		
		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		log.info("Obteniento lista de productos de la base de datos");
		
		List<ProductoFavorito> list = productoFavoritoDAO.getProductosFavoritosPorUsuario( idUsuario );
		List<Producto> productos = new ArrayList<>();
		
		for( ProductoFavorito pf : list ) {
			productos.add( pf.getProducto() );
		}
		
		rsp.setProductos(transform(productos));
		return rsp;
	}

	/*
	 * Productos valoracion
	 */
	@Override
	@Transactional
	public SimpleResponse agregarProductoValoracionUsuario(ProductoValoracion item) {
		log.info( "Agregando producto valoracion: {} a usuario: {}", item.getProducto().getIdProducto(), item.getUsuario().getIdUsuario() );
		
		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);
		
		//Verifica que exista el ranking del producto para el usuario
		List<ProductoValoracion> list = productoValoracionDAO.getValoracionPorProdcutoyUsuario(item);
		if ( list.isEmpty() ) {
			productoValoracionDAO.save(item);
		}
		else {
			for( ProductoValoracion pv : list ) {
				pv.setValoracion( item.getValoracion() );
				productoValoracionDAO.update(pv);
			}
		}
		
		return rsp;
	}

	@Override
	public SimpleResponse eliminarProductoValoracionUsuario(ProductoValoracion item) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
