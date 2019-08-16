package com.bit.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.ProductosTiendasDAO;
import com.bit.model.ProductosTiendas;
import com.bit.model.dto.SimpleResponse;
import com.bit.model.dto.response.ListItemsRSP;
import com.bit.service.ProductosTiendasService;

@Service
public class ProductosTiendasServiceImpl implements ProductosTiendasService {

	private static final Logger log = LoggerFactory.getLogger(ProductosTiendasServiceImpl.class);

	@Autowired
	private ProductosTiendasDAO productosTiendasDAO;

	@Override
	@Transactional
	public ListItemsRSP getProductosTiendas() {

		ListItemsRSP rsp = new ListItemsRSP();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		log.info("Obteniento lista de productos por tienda de la base de datos");

		List<ProductosTiendas> list = productosTiendasDAO.getProductosTiendas();
		rsp.setProductosTienda(list);

		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse registrarProductosPorTienda(ProductosTiendas item) {

		log.info("Registrando un nuevo producto por tienda en la base de datos");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		item = productosTiendasDAO.save(item);
		rsp.setId(item.getIdProductoTienda());

		return rsp;
	}

	@Override
	@Transactional
	public SimpleResponse actualizarProductosPorTienda(ProductosTiendas item) {

		log.info("Actualizando un producto por tienda en la base de datos");

		SimpleResponse rsp = new SimpleResponse();
		rsp.setCode(200);
		rsp.setMessage("Exitoso");

		productosTiendasDAO.findByPK(item.getIdProductoTienda());

		item = productosTiendasDAO.update(item);
		rsp.setId(item.getIdProductoTienda());

		return rsp;
	}
}
