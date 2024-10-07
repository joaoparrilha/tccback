package com.devtcc.tccback.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.dto.SupportRequestDTO;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendSupportEmail(SupportRequestDTO supportRequest){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("apoio.reuse@gmail.com");
		message.setSubject(supportRequest.assunto());
		message.setText("Nome: " + supportRequest.nome() + "\n"
                + "Email: " + supportRequest.email() + "\n"
                + "Assunto:" + supportRequest.assunto() + "\n"
                + "Mensagem: " + supportRequest.mensagem());
		
		mailSender.send(message);
	}
	
	public void sendApproveEmail(String email, Long idAtivo){
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Ativo Aprovado!");
		message.setText("O ativo " + idAtivo + " foi validado e se encontra público!");
			
		mailSender.send(message);
	}


}
