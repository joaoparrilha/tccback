package com.devtcc.tccback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Aprovadores;
import com.devtcc.tccback.entities.CheckAprov;
import com.devtcc.tccback.entities.Checklist;
import com.devtcc.tccback.entities.Usuario;
import com.devtcc.tccback.repositories.AprovadoresRepository;
import com.devtcc.tccback.repositories.CheckAprovRepository;
import com.devtcc.tccback.repositories.ChecklistRepository;
import com.devtcc.tccback.repositories.UsuarioRepository;
import com.devtcc.tccback.services.exception.AtivoUpdateException;

@Service
public class CheckAprovService {
	
	@Autowired
	private CheckAprovRepository repo;

	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private AprovadoresRepository repoAprov;
	
	@Autowired
	private ChecklistRepository repoCheck;
	
	public List<CheckAprov> findAll(){
		return repo.findAll();
	}
	
	public CheckAprov findById(Long id){
		Optional<CheckAprov> checkAprov = repo.findById(id);
		return checkAprov.get();
	}
	
	public CheckAprov insert(CheckAprov obj){
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public CheckAprov update(Long id, CheckAprov obj){
		CheckAprov checkAprov = repo.getReferenceById(id);
		
		updateData(checkAprov, obj);
		
		return repo.save(checkAprov);
	}
	
	public void updateData(CheckAprov entity, CheckAprov obj) {
		
		if(obj.getId() != null) {
			entity.setId(obj.getId());
		}
		
		if(entity.getTeste() ==  true) {
			if(obj.getHomologacao() != null) {
				entity.setHomologacao(obj.getHomologacao());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(entity.getRefinamento() == true) {
			if(obj.getTeste() != null) {
				entity.setTeste(obj.getTeste());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(entity.getRevisao() == true) {
			if(obj.getRefinamento() != null) {
				entity.setRefinamento(obj.getRefinamento());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(obj.getRevisao() != null) {
			entity.setRevisao(obj.getRevisao());
		}
	}
}
