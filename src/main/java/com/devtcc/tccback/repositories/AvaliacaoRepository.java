package com.devtcc.tccback.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devtcc.tccback.entities.Avaliacao;
import com.devtcc.tccback.entities.Checklist;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
	
	 List<Avaliacao> findByAtivoId(Long fk_ativo_id);

}
