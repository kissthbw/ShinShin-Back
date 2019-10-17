package com.bit.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bit.model.Usuario;

public class UsuarioShingShingDetailService implements UserDetails {

	private static final long serialVersionUID = 7194255311203623879L;
	private Usuario user;
	
	public UsuarioShingShingDetailService(Usuario user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName().toString())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getContrasenia();
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
