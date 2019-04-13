package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.TipoProductoDAO;
import com.bit.model.TipoProducto;
import com.bit.service.TipoProductoService;

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

	@Autowired
	private TipoProductoDAO tipoProductoDAO;

	@Override
	@Transactional
	public List<TipoProducto> getTipoProductos() {
		List<TipoProducto> list = tipoProductoDAO.getTipoProductos();
		return list;
	}

	@Override
	@Transactional
	public void guardarTipoProductos(TipoProducto item) {
		tipoProductoDAO.save(item);
	}

	@Override
	public void actualizarTipoProductos(TipoProducto item) {
		tipoProductoDAO.save(item);

	}
}
