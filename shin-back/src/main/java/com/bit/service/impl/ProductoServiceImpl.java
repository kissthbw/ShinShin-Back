package com.bit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.ProductoDAO;
import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTienda;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

	@Autowired
	private ProductoDAO productoDAO;

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
	public SimpleResponse registrarProductos(Producto item) {

		log.info("Registrando un nuevo producto en la base de datos");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		item = productoDAO.save(item);
		rsp.setId(item.getIdProducto());
		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarProductos(Producto item) {

		log.info("Modificando uno o varios valores de un producto de la base de datos");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setMessage("Exitoso");
		rsp.setCode(200);

		productoDAO.findByPK(item.getIdProducto());

		item = productoDAO.update(item);
		rsp.setId(item.getIdProducto());
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
}
