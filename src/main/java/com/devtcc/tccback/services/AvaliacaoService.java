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
}
