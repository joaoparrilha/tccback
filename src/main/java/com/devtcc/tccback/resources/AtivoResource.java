package com.devtcc.tccback.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.services.AtivoService;

@RestController
@RequestMapping(value = "/ativo")
public class AtivoResource {
	
	@Autowired
	private AtivoService service;
	
	@GetMapping
	public ResponseEntity<List<Ativo>> findAll(){
		List<Ativo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Ativo> findById(@PathVariable Long id){
		Ativo usuario = service.findById(id);
		return ResponseEntity.ok().body(usuario);
	}	
}
