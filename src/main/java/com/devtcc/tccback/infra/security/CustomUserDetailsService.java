package com.devtcc.tccback.infra.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.devtcc.tccback.entities.Usuario;
import com.devtcc.tccback.repositories.UsuarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		//Usuario usuario = (Usuario) this.repo.findByEmail(username);//.orElseThrow(()-> new UsernameNotFoundException("User not found"));
		//return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
		Usuario usuario = repo.findByEmail(username);
        
        if (usuario == null) {
            // Lança exceção se o usuário não for encontrado
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + username);
        }

        // Retorna o próprio objeto Usuario, que já implementa UserDetails
        return usuario;
	}
	
}
