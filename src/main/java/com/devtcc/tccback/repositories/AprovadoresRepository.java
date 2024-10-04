package com.devtcc.tccback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devtcc.tccback.entities.Aprovadores;

public interface AprovadoresRepository extends JpaRepository<Aprovadores, Long>{

	Optional<Aprovadores> findByUsuarioId(Long id);
}
