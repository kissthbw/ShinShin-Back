package com.bit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.dao.UsuarioDAO;
import com.bit.model.Usuario;
import com.bit.service.UsuarioShingShingDetailService;

@Service
public class UserService implements  UserDetailsService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public UserService() {
		System.out.println("");
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Usuario> user = usuarioDAO.findUserByUser2(username);
		
		if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
		
		return new UsuarioShingShingDetailService(user.get(0));
	}
}
