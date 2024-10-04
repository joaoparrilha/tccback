package com.devtcc.tccback.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devtcc.tccback.dto.LoginRequestDTO;
import com.devtcc.tccback.dto.RegisterRequestDTO;
import com.devtcc.tccback.dto.ResponseDTO;
import com.devtcc.tccback.entities.Usuario;
import com.devtcc.tccback.entities.UsuarioRole;
import com.devtcc.tccback.infra.security.TokenService;
import com.devtcc.tccback.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthResource {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
		
		/*Usuario usuario = this.repo.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
		
		//String retorno = "Sucess";
        //return ResponseEntity.ok().body(retorno);
		
		if(passwordEncoder.matches(body.senha(), usuario.getSenha())) {
			String token = this.tokenService.generateToken(usuario);
			//return ResponseEntity.ok(new ResponseDTO(usuario.getNome(), token));
			String retorno = "Sucess";
	        return ResponseEntity.ok().body(retorno);
		}
		
		return ResponseEntity.badRequest().build();*/
		
		var userPassword = new UsernamePasswordAuthenticationToken(body.email(), body.senha());
		var auth = this.authenticationManager.authenticate(userPassword);
		var token = this.tokenService.generateToken((Usuario)auth.getPrincipal());
		var usuario = this.repo.findByEmail(body.email());
		var role = usuario.getRole().toString();
		
        return ResponseEntity.ok().body(new ResponseDTO(body.email(), token, role));
	}
	
	@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDTO body) {
		
		/*Optional<Usuario> usuario = this.repo.findByEmail(body.email());
		
		if(usuario.isEmpty()) {
			Usuario newUser = new Usuario();
			newUser.setNome(body.nome());
			newUser.setMatricula(body.matricula());
			newUser.setEmail(body.email());
			newUser.setSenha(passwordEncoder.encode(body.senha()));
			newUser.setTelefone(body.telefone());
			this.repo.save(newUser);
			
			String token = this.tokenService.generateToken(newUser);
			return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token));
		}
		
		return ResponseEntity.badRequest().build();*/
		
		if(this.repo.findByEmail(body.email()) != null) return ResponseEntity.badRequest().build();
		
		String encryptedSenha = new BCryptPasswordEncoder().encode(body.senha());
		Usuario newUser = new Usuario(body.nome(), body.matricula(), body.email(), encryptedSenha, body.telefone(), UsuarioRole.USUARIO);
		
		this.repo.save(newUser);
		String token = this.tokenService.generateToken(newUser);
		var usuario = this.repo.findByEmail(body.email());
		var role = usuario.getRole().toString();
		return ResponseEntity.ok(new ResponseDTO(newUser.getNome(), token, role));
		//return ResponseEntity.ok().build();
	}
}
