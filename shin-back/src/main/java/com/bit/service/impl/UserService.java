package com.bit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.common.Utils;
import com.bit.config.security.SimpleAuthenticationFilter;
import com.bit.dao.UsuarioDAO;
import com.bit.model.Authority;
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
		
		boolean isSocialNetworkLogin = false;
		
		//1. Si viene el prefijo hash:
		if( username.contains( SimpleAuthenticationFilter.SOCIAL_NETWORK_TOKEN ) ) {
			username = username.replace(SimpleAuthenticationFilter.SOCIAL_NETWORK_TOKEN, "");
			isSocialNetworkLogin  = !isSocialNetworkLogin;
		}
		
		List<Usuario> user = null;
		
		//2. Pueder ser por correo o numero de telefono
		//Si es numerico es telefono
		if( Utils.isNumeric( username ) ) {
			user = usuarioDAO.findUserByPhone("+521" + username);
		}
		else {
			user = usuarioDAO.findUserByUser2(username);
		}
		
		if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
		
		UsuarioShingShingDetailService detail = new UsuarioShingShingDetailService( user.get(0), 
				getGrantedAuthorities( user.get(0) ),
				isSocialNetworkLogin );
		
		return detail;
		
//		return new org.springframework.security.core.userdetails.User(user.get(0).getUsuario(), user.get(0).getContrasenia(), 
//                true, true, true, true, getGrantedAuthorities(user.get(0)));
	}
	
	@Transactional
	private List<GrantedAuthority> getGrantedAuthorities(Usuario user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(Authority userProfile : user.getAuthorities()){
            authorities.add(new SimpleGrantedAuthority("" + userProfile.getName()));
        }
        return authorities;
    }
}
