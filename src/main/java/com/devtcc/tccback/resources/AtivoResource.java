package com.devtcc.tccback.resources;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.services.AtivoService;

@RestController
@RequestMapping(value = "/ativo")
@CrossOrigin(origins = "http://localhost:3000")
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
	
	@GetMapping("/validar")
	public ResponseEntity<List<Ativo>> findByValidacaoFalse(){
		List<Ativo> list = service.findValidacao();
		return ResponseEntity.ok().body(list);
	}
	
	//@PostMapping
	//public ResponseEntity<Ativo> insert(@RequestBody Ativo obj){
	//	obj = service.insert(obj);
	//	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	//	return ResponseEntity.created(uri).body(obj);
	//}
	
	@PostMapping
	public ResponseEntity<Ativo> insert(@RequestBody Ativo obj, @RequestParam("arquivo") MultipartFile file){
		try {
			obj.setArquivo(file.getBytes());
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).body(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Ativo> update(@PathVariable Long id, @RequestBody Ativo obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
