package com.devtcc.tccback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Avaliacao;
import com.devtcc.tccback.repositories.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository repo;
	
	public List<Avaliacao> findAll(){
		return repo.findAll();
	}
	
	public Avaliacao findById(Long id){
		Optional<Avaliacao> avaliacao = repo.findById(id);
		return avaliacao.get();
	}
	
	public Avaliacao insert(Avaliacao obj) {
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Avaliacao update(Long id, Avaliacao obj){
		Avaliacao entity = repo.getReferenceById(id);
		updateData(entity, obj);
		return repo.save(entity);
	}

	private void updateData(Avaliacao entity, Avaliacao obj) {
		
		if(obj.getId() != null) {
			entity.setId(obj.getId());
		}
		
		if(obj.getAvaliacao() != null) {
			entity.setAvaliacao(obj.getAvaliacao());
		}
		
		if(obj.getComentario() != null) {
			entity.setComentario(obj.getComentario());
		}		
	}
}
