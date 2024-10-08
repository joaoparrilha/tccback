package com.devtcc.tccback.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devtcc.tccback.entities.Aprovadores;
import com.devtcc.tccback.services.AprovadoresService;

@RestController
@RequestMapping(value = "/aprovador")
@CrossOrigin(origins = "http://localhost:3000")
public class AprovadoresResource {
	
	@Autowired
	private AprovadoresService service;
	
	@GetMapping
	public ResponseEntity<List<Aprovadores>> findAll(){
		List<Aprovadores> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Aprovadores> findById(@PathVariable Long id){
		Aprovadores aprovador = service.findById(id);
		return ResponseEntity.ok().body(aprovador);
	}	
	
	@GetMapping(value = "/usuario/{fk_usuario_id}")
	public ResponseEntity<Aprovadores> findByUsuarioId(@PathVariable Long fk_usuario_id){
		Aprovadores aprovador = service.findByUsuarioId(fk_usuario_id);
		return ResponseEntity.ok().body(aprovador);
	}	
	
	@PostMapping
	public ResponseEntity<Aprovadores> insert(@RequestBody Aprovadores obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Aprovadores> update(@PathVariable Long id, @RequestBody Aprovadores obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
}
