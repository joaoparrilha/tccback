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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devtcc.tccback.entities.Aprovadores;
import com.devtcc.tccback.entities.CheckAprov;
import com.devtcc.tccback.entities.Checklist;
import com.devtcc.tccback.services.AprovadoresService;
import com.devtcc.tccback.services.CheckAprovService;
import com.devtcc.tccback.services.ChecklistService;

@RestController
@RequestMapping(value = "/checkaprov")
@CrossOrigin(origins = "http://localhost:3000")
public class CheckAprovResource {
	
	@Autowired
	private CheckAprovService service;
	
	@Autowired
	private AprovadoresService aprovService;
	
	@Autowired 
	private ChecklistService checkService;
	
	@GetMapping
	public ResponseEntity<List<CheckAprov>> findAll(){
		List<CheckAprov> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<CheckAprov>> findByAprovId(@PathVariable Long id){
		List<CheckAprov> list = service.findByAprovId(id);
		return ResponseEntity.ok(list);
	}
	
	@PostMapping
	public ResponseEntity<CheckAprov> insert (
			@RequestParam (value = "aprovadores_id") Long aprovadores_id,
			@RequestParam (value = "checklist_id") Long checklist_id,
			@RequestParam (value = "revisao", required = false) Boolean revisao,
			@RequestParam (value = "refinamento", required = false) Boolean refinamento,
			@RequestParam (value = "teste", required = false) Boolean teste,
			@RequestParam (value = "homologacao", required = false) Boolean homologacao){
		
		CheckAprov obj = new CheckAprov();
		obj.setRevisao(false);
		obj.setRefinamento(false);
		obj.setTeste(false);
		obj.setHomologacao(false);
		
		Aprovadores aprovadores = aprovService.findById(aprovadores_id);
		Checklist checklist = checkService.findById(checklist_id);
		
		obj.setAprovador(aprovadores);
		obj.setChecklist(checklist);
		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CheckAprov> update(
	    @PathVariable Long id,
	    @RequestBody CheckAprov checkAprov) {

	    CheckAprov obj = service.findById(id);
	    if (checkAprov.getId() != null) obj.setId(checkAprov.getId());
	    if (checkAprov.getRevisao() != null) obj.setRevisao(checkAprov.getRevisao());
	    if (checkAprov.getRefinamento() != null) obj.setRefinamento(checkAprov.getRefinamento());
	    if (checkAprov.getTeste() != null) obj.setTeste(checkAprov.getTeste());
	    if (checkAprov.getHomologacao() != null) obj.setHomologacao(checkAprov.getHomologacao());
	    
	    obj = service.update(id, obj);
	    return ResponseEntity.ok().body(obj);
	}

	
	@DeleteMapping
	public ResponseEntity<Void>delete(@RequestParam(value = "id") Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
