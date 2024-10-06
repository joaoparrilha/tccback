package com.devtcc.tccback.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devtcc.tccback.dto.SupportRequestDTO;
import com.devtcc.tccback.services.EmailService;

@RestController
@RequestMapping(value = "/suporte")
@CrossOrigin(origins = "http://localhost:3000")
public class SupportResource {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public ResponseEntity<String> sendSupportEmail(@RequestBody SupportRequestDTO supportRequest){
		emailService.sendSupportEmail(supportRequest);
        return ResponseEntity.ok("Email enviado com sucesso!");
	}
}
