package com.devtcc.tccback.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devtcc.tccback.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByEmail(String email);
}
