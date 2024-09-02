package com.devtcc.tccback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Aprovadores;
import com.devtcc.tccback.repositories.AprovadoresRepository;

@Service
public class AprovadoresService {
	
	@Autowired
	private AprovadoresRepository repo;
	
	public List<Aprovadores> findAll(){
		return repo.findAll();
	}
	
	public Aprovadores findById(Long id){
		Optional<Aprovadores> aprovador = repo.findById(id);
		return aprovador.get();
	}
	
	public Aprovadores insert(Aprovadores obj) {
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Aprovadores update(Long id, Aprovadores obj){
		Aprovadores entity = repo.getReferenceById(id);
		updateData(entity, obj);
		return repo.save(entity);
	}

	private void updateData(Aprovadores entity, Aprovadores obj) {
		
		if(obj.getId() != null) {
			entity.setId(obj.getId());
		}
		
		if(obj.getAprovador() != null) {
			entity.setAprovador(obj.getAprovador());
		}
		
		if(obj.getChecklist() != null) {
			entity.setChecklist(obj.getChecklist());
		}
	}
}
