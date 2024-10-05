package com.devtcc.tccback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devtcc.tccback.entities.Ativo;

public interface AtivoRepository extends JpaRepository<Ativo, Long>{

	List<Ativo> findByValidacao(Boolean validacao);
	
	List<Ativo> findByUsuarioId(Long fk_usuario_id);
}
