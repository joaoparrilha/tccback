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

import com.devtcc.tccback.entities.Usuario;
import com.devtcc.tccback.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id){
		Usuario usuario = service.findById(id);
		return ResponseEntity.ok().body(usuario);
	}	
	
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	/*@PostMapping("/validacao")
	public ResponseEntity<String> login(@RequestBody Usuario obj){
		try {
			
            Usuario usuario = service.login(obj.getEmail(), obj.getSenha());
            String retorno = "Sucess";
            return ResponseEntity.ok().body(retorno);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(null); // Status 401 Unauthorized
        }
	}*/
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	/*@PostMapping("/validacao")
	public ResponseEntity<Usuario> login(@RequestBody Usuario obj){
		try {
			System.out.println("Tentando autenticar o usuário com email: " + obj.getEmail());
			System.out.println("Tentando autenticar o usuário com email: " + obj.getSenha());
            Usuario usuario = service.login(obj.getEmail(), obj.getSenha());
            System.out.println("Usuário autenticado com sucesso.");
           //String retorno = "Sucess";
            return ResponseEntity.ok().body(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(null); // Status 401 Unauthorized
        }
	}*/
	
	
}
