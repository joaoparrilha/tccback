package com.devtcc.tccback.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devtcc.tccback.entities.Aprovador;
import com.devtcc.tccback.services.AprovadorService;

@RestController
@RequestMapping(value = "/aprovador")
public class AprovadorResource {
	
	@Autowired
	private AprovadorService service;
	
	@GetMapping
	public ResponseEntity<List<Aprovador>> findAll(){
		List<Aprovador> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Aprovador> findById(@PathVariable Long id){
		Aprovador aprovador = service.findById(id);
		return ResponseEntity.ok().body(aprovador);
	}	
}
