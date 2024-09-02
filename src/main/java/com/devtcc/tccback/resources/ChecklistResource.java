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

import com.devtcc.tccback.entities.Checklist;
import com.devtcc.tccback.services.ChecklistService;

@RestController
@RequestMapping(value = "/checklist")
@CrossOrigin(origins = "http://localhost:3000")
public class ChecklistResource {
	
	@Autowired
	private ChecklistService service;
	
	@GetMapping
	public ResponseEntity<List<Checklist>> findAll(){
		List<Checklist> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Checklist> findById(@PathVariable Long id){
		Checklist checklist = service.findById(id);
		return ResponseEntity.ok().body(checklist);
	}	
	
	@PostMapping
	public ResponseEntity<Checklist> insert(@RequestBody Checklist obj){
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
	public ResponseEntity<Checklist> update(@PathVariable Long id, @RequestBody Checklist obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
