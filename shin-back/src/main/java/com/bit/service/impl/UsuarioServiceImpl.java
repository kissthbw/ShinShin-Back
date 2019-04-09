package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.UsuarioDAO;
import com.bit.model.Usuario;
import com.bit.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public List<Usuario> getUsuarios() {
		List<Usuario> list = usuarioDAO.getUsuarios();
		return list;
	}
	
	@Override
	@Transactional
	public void guardarUsuarios(Usuario item) {
		usuarioDAO.save(item);
	}

}
