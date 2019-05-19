package com.bit.service.impl;

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
		return list;
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
