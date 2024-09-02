package com.devtcc.tccback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Usuario;
import com.devtcc.tccback.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	public Usuario findById(Long id){
		Optional<Usuario> usuario = repo.findById(id);
		return usuario.get();
	}
	
	public Usuario insert(Usuario obj) {
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Usuario update(Long id, Usuario obj){
		Usuario entity = repo.getReferenceById(id);
		updateData(entity, obj);
		return repo.save(entity);
	}

	private void updateData(Usuario entity, Usuario obj) {
		
		if(obj.getId() != null) {
			entity.setId(obj.getId());
		}
		
		if(obj.getNome() != null) {
			entity.setNome(obj.getNome());
		}
		
		if(obj.getMatricula() != null) {
			entity.setMatricula(obj.getMatricula());
		}
		
		if(obj.getEmail() != null) {
			entity.setEmail(obj.getEmail());
		}
		
		if(obj.getSenha() != null) {
			entity.setSenha(obj.getSenha());
		}
		
		if(obj.getTelefone() != null) {
			entity.setTelefone(obj.getTelefone());
		}
		
		if(obj.getTelefone() != null) {
			entity.setStatus(obj.getStatus());
		}	
	}
	
	public Usuario login(String email, String senha){
		Optional<Usuario> usuario = repo.findByEmail(email);
		if(usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
			if("administrador".equals(usuario.get().getStatus()) || "validador".equals(usuario.get().getStatus())) {
				return usuario.get();
			}else {
				 throw new RuntimeException("Usuário inválido");
			}
		}else {
			 throw new RuntimeException("Email ou senha inválidos");
		}
	}
	
}
