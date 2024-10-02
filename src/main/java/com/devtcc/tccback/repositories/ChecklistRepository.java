package com.devtcc.tccback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devtcc.tccback.entities.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long>{
	
	   Optional<Checklist> findByAtivoId(Long ativo_id);

}
