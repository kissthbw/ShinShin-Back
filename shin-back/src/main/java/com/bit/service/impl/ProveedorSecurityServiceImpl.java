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

import com.bit.dao.ProveedorDAO;
import com.bit.model.Authority;
import com.bit.model.Proveedor;
import com.bit.service.ProveedorDetailService;

@Service
public class ProveedorSecurityServiceImpl implements  UserDetailsService {
	
	@Autowired
	private ProveedorDAO proveedorDAO;
	
	public ProveedorSecurityServiceImpl() {
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Proveedor item = proveedorDAO.finfByEmail(username);
		
		
		ProveedorDetailService detail = new ProveedorDetailService(item, getGrantedAuthorities(item));
		
		return detail;
	}
	
	@Transactional
	private List<GrantedAuthority> getGrantedAuthorities(Proveedor user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(Authority userProfile : user.getAuthorities()){
            authorities.add(new SimpleGrantedAuthority("" + userProfile.getName()));
        }
        return authorities;
    }
}
