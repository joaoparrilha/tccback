package com.devtcc.tccback.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.repositories.AtivoRepository;

@Service
public class AtivoService {
	
	@Autowired
	private AtivoRepository repo;
	
	public List<Ativo> findAll(){
		return repo.findAll();
	}
	
	public Ativo findById(Long id){
		Optional<Ativo> usuario = repo.findById(id);
		return usuario.get();
	}
	
	public List<Ativo> findValidacao(){
		return repo.findByValidacao(false);
	}
	
	public Ativo insert(Ativo obj) {
		
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		obj.setCriado(date);
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Ativo update(Long id, Ativo obj){
		Ativo entity = repo.getReferenceById(id);
		updateData(entity, obj);
		return repo.save(entity);
	}

	private void updateData(Ativo entity, Ativo obj) {
		
		if(obj.getId() != null) {
			entity.setId(obj.getId());
		}
		
		if(obj.getNome() != null) {
			entity.setNome(obj.getNome());
		}
		
		if(obj.getDescricao() != null) {
			entity.setDescricao(obj.getDescricao());
		}
		
		if(obj.getTipo() != null) {
			entity.setTipo(obj.getTipo());
		}
		
		if(obj.getDominio() != null) {
			entity.setDominio(obj.getDominio());
		}
		
		if(obj.getDominio() != null) {
			entity.setDominio(obj.getDominio());
		}
		
		if(obj.getCriado() != null) {
			entity.setCriado(obj.getCriado());
		}
		
		if(obj.getVersao() != null) {
			entity.setVersao(obj.getVersao());
		}
		
		if(obj.getDownload() != null) {
			entity.setDownload(obj.getDownload());
		}
		
		if(obj.getValidacao() != null) {
			entity.setValidacao(obj.getValidacao());
		}
		
		if(obj.getArquivo() != null) {
			entity.setArquivo(obj.getArquivo());
		}
		
		if(obj.getUsuario() != null) {
			entity.setUsuario(obj.getUsuario());
		}
	}
}
