package com.bit.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bit.model.Usuario;

public class UsuarioShingShingDetailService implements UserDetails {

	private static final long serialVersionUID = 7194255311203623879L;
	private Usuario user;
	private boolean isSocialNetworkLogin = false;
	List<GrantedAuthority> authorities = new ArrayList<>();
	
	public UsuarioShingShingDetailService(Usuario user, List<GrantedAuthority> authorities, boolean isSocialNetworkLogin) {
		this.user = user;
		this.authorities = authorities;
		this.isSocialNetworkLogin = isSocialNetworkLogin;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName().toString())).collect(Collectors.toList());
		return authorities; 
	}

	@Override
	public String getPassword() {
		if (isSocialNetworkLogin){
			return user.getHash();
		}
		else {
			return user.getContrasenia();
		}
		
	}

	@Override
	public String getUsername() {
		return user.getUsuario();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public Usuario getUsuario() {
		return user;
	}

}
