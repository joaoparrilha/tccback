package com.devtcc.tccback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devtcc.tccback.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
