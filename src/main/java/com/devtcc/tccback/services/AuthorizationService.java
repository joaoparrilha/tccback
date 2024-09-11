package com.devtcc.tccback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.devtcc.tccback.repositories.UsuarioRepository;

public class AuthorizationService implements UserDetailsService{
	
	@Autowired
	UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return repo.findByEmail(username);
	}

}
