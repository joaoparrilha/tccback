package com.devtcc.tccback.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devtcc.tccback.entities.Avaliacao;
import com.devtcc.tccback.services.AvaliacaoService;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoResource {
	
	@Autowired
	private AvaliacaoService service;
	
	@GetMapping
	public ResponseEntity<List<Avaliacao>> findAll(){
		List<Avaliacao> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Avaliacao> findById(@PathVariable Long id){
		Avaliacao avaliacao = service.findById(id);
		return ResponseEntity.ok().body(avaliacao);
	}	
	
	@PostMapping
	public ResponseEntity<Avaliacao> insert(@RequestBody Avaliacao obj){
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
	public ResponseEntity<Avaliacao> update(@PathVariable Long id, @RequestBody Avaliacao obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
