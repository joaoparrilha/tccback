package com.devtcc.tccback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devtcc.tccback.entities.CheckAprov;

public interface CheckAprovRepository extends JpaRepository<CheckAprov, Long>{
	
	Optional<CheckAprov> findByAprovadores_Id(Long aprovadoresId);
}
