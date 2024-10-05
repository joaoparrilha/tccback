package com.devtcc.tccback.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devtcc.tccback.entities.CheckAprov;

public interface CheckAprovRepository extends JpaRepository<CheckAprov, Long>{
	
	List<CheckAprov> findByAprovadores_Id(Long aprovadoresId);
	List<CheckAprov> findByChecklist_Id(Long checklistId);
}
