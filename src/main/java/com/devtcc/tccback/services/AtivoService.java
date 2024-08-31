package com.devtcc.tccback.services;

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
}
