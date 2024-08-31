package com.devtcc.tccback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Aprovador;
import com.devtcc.tccback.repositories.AprovadorRepository;

@Service
public class AprovadorService {
	
	@Autowired
	private AprovadorRepository repo;
	
	public List<Aprovador> findAll(){
		return repo.findAll();
	}
	
	public Aprovador findById(Long id){
		Optional<Aprovador> aprovador = repo.findById(id);
		return aprovador.get();
	}
}
