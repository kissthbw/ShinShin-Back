package com.bit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.ProductoDAO;
import com.bit.model.CatalogoMarca;
import com.bit.model.CatalogoTipoProducto;
import com.bit.model.Producto;
import com.bit.model.dto.SimpleResponse;
import com.bit.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDAO productoDAO;

	@Override
	@Transactional
	public List<Producto> getProductos() {

		List<Producto> list = productoDAO.getProductos();
		
		return transform(list);
	}

	@Override
	@Transactional
	public SimpleResponse registrarProductos(Producto item) {

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
	private List<Producto> transform( List<Producto> list ) {
		List<Producto> tmp = new ArrayList<>();
		
		for( Producto item : list ) {
			Producto pTemp = new Producto();
			CatalogoMarca m = new CatalogoMarca();
			CatalogoTipoProducto t = new CatalogoTipoProducto();
			pTemp.setIdProducto( item.getIdProducto() );
			pTemp.setNombreProducto( item.getNombreProducto() );
			pTemp.setPrecio( item.getPrecio() );
			pTemp.setCodigoBarras( item.getCodigoBarras() );
			pTemp.setPresentacion( item.getPresentacion() );
			pTemp.setContenido( item.getContenido() );
			pTemp.setDescripcion( item.getDescripcion() );
			pTemp.setAplicaPromocion( item.isAplicaPromocion() );
			pTemp.setVigenciaPromocion( item.getVigenciaPromocion() );
			pTemp.setUrlImagenProducto( item.getUrlImagenProducto() );
			pTemp.setCantidadBonificacion( item.getCantidadBonificacion() );
			
			m.setIdCatalogoMarca( item.getCatalogoMarca().getIdCatalogoMarca() );
			m.setNombreMarca( item.getCatalogoMarca().getNombreMarca() );
			pTemp.setCatalogoMarca( m );
			
			t.setIdCatalogoTipoProducto( item.getCatalogoTipoProducto().getIdCatalogoTipoProducto() );
			t.setNombreTipoProducto( item.getCatalogoTipoProducto().getNombreTipoProducto() );
			pTemp.setCatalogoTipoProducto( t );
			
			tmp.add(pTemp);
		}
		
		
		return tmp;
	}
	
	@Override
	@Transactional
	public List<Producto> getProductosPorMarca(CatalogoMarca item, Producto i) {
		
		String marca = item.getNombreMarca();
		String nombreProducto = i.getNombreProducto();
		
		List<Producto> list = productoDAO.getProductosPorMarca(marca, nombreProducto);
		
		return list;
	}

	@Override
	@Transactional
	public List<Producto> getProductosPorTipo(CatalogoTipoProducto item, Producto i) {
		
		String tipoProducto = item.getNombreTipoProducto();
		String nombreProducto = i.getNombreProducto();
		
		List<Producto> list = productoDAO.getProductosPorTipo(tipoProducto, nombreProducto);
		
		return list;
	}

	@Override
	@Transactional
	public List<Producto> getProductosPorNombre(Producto i) {

		String nombreProducto = i.getNombreProducto();
		
		List<Producto> list = productoDAO.getProductosPorNombre(nombreProducto);
		
		return list;
	}
}
