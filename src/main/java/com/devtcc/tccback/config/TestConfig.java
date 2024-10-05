/*package com.devtcc.tccback.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devtcc.tccback.entities.Aprovadores;
import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.entities.Avaliacao;
import com.devtcc.tccback.entities.CheckAprov;
import com.devtcc.tccback.entities.Checklist;
import com.devtcc.tccback.entities.Usuario;
import com.devtcc.tccback.repositories.AprovadoresRepository;
import com.devtcc.tccback.repositories.AtivoRepository;
import com.devtcc.tccback.repositories.AvaliacaoRepository;
import com.devtcc.tccback.repositories.CheckAprovRepository;
import com.devtcc.tccback.repositories.ChecklistRepository;
import com.devtcc.tccback.repositories.UsuarioRepository;
import com.devtcc.tccback.resources.AuthResource;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private UsuarioRepository userRepo;
	
	@Autowired
	private AtivoRepository ativoRepo;
	
	@Autowired
	private AprovadoresRepository aprovRepo;
	
	@Autowired
	private ChecklistRepository checkRepo;
	
	@Autowired
	private CheckAprovRepository checkAprovRepo;
	
	@Autowired
	private AvaliacaoRepository avaRepo;
	
	@Autowired
	private AuthResource authRes;

	@Override
	public void run(String... args) throws Exception {
		
		//Usuario
		
		//ADMINISTRADOR
		Optional<Usuario> op1 = userRepo.findById(1L);
		Optional<Usuario> op2 = userRepo.findById(2L);
		Optional<Usuario> op3 = userRepo.findById(3L);
		
		//VALIDADOR
		Optional<Usuario> op4 = userRepo.findById(4L);
		Optional<Usuario> op5 = userRepo.findById(5L);
		Optional<Usuario> op6 = userRepo.findById(6L);
		Optional<Usuario> op7 = userRepo.findById(7L);
		Optional<Usuario> op8 = userRepo.findById(8L);
		Optional<Usuario> op9 = userRepo.findById(9L);
		Optional<Usuario> op10 = userRepo.findById(10L);
		
		//USUARIO
		Optional<Usuario> op11 = userRepo.findById(11L);
		Optional<Usuario> op12 = userRepo.findById(12L);
		Optional<Usuario> op13 = userRepo.findById(13L);
		Optional<Usuario> op14 = userRepo.findById(14L);
		Optional<Usuario> op15 = userRepo.findById(15L);
		
		//ADMINISTRADOR
		Usuario u1 = op1.get();
		Usuario u2 = op2.get();
		Usuario u3 = op3.get();
		
		//VALIDADOR
		Usuario u4 = op4.get();
		Usuario u5 = op5.get();
		Usuario u6 = op6.get();
		Usuario u7 = op7.get();
		Usuario u8 = op8.get();
		Usuario u9 = op9.get();
		Usuario u10 = op10.get();
		
		//USUARIO
		Usuario u11 = op11.get();
		Usuario u12 = op12.get();
		Usuario u13 = op13.get();
		Usuario u14 = op14.get();
		Usuario u15 = op15.get();
		
		
		//Ativo
		Path path = Paths.get("C:\\Users\\jlpar\\Downloads\\POLIWEEK_2024_Dec_Estudante.pdf");
		byte[] arquivoBytes = Files.readAllBytes(path);
		Ativo ati1 = new Ativo(null, "teste01", "desc teste01", "proativo", "teste01", new Date(), 1.0f, 0, false, arquivoBytes, u1);
		Ativo ati2 = new Ativo(null, "teste02", "desc teste02", "incremental", "teste02", new Date(), 1.0f, 0, false, arquivoBytes, u2);
		Ativo ati3 = new Ativo(null, "teste03", "desc teste03", "iterativo", "teste03", new Date(), 1.0f, 0, false, arquivoBytes, u3);
		Ativo ati4 = new Ativo(null, "teste04", "desc teste04", "iterativo", "teste04", new Date(), 1.0f, 0, false, arquivoBytes, u4);
		Ativo ati5 = new Ativo(null, "teste05", "desc teste05", "proativo", "teste05", new Date(), 1.0f, 0, false, arquivoBytes, u5);
		Ativo ati6 = new Ativo(null, "teste06", "desc teste06", "incremental", "teste06", new Date(), 1.0f, 0, false, arquivoBytes, u6);
		Ativo ati7 = new Ativo(null, "teste07", "desc teste07", "proativo", "teste07", new Date(), 1.0f, 0, false, arquivoBytes, u7);
		Ativo ati8 = new Ativo(null, "teste08", "desc teste08", "proativo", "teste08", new Date(), 1.0f, 0, false, arquivoBytes, u8);
		Ativo ati9 = new Ativo(null, "teste09", "desc teste09", "iterativo", "teste09", new Date(), 1.0f, 0, false, arquivoBytes, u9);
		Ativo ati10 = new Ativo(null, "teste10", "desc teste10", "proativo", "teste10", new Date(), 1.0f, 0, false, arquivoBytes, u10);
		Ativo ati11 = new Ativo(null, "teste11", "desc teste11", "iterativo", "teste11", new Date(), 1.0f, 0, false, arquivoBytes, u11);
		Ativo ati12 = new Ativo(null, "teste12", "desc teste12", "incremental", "teste12", new Date(), 1.0f, 0, false, arquivoBytes, u12);
		Ativo ati13 = new Ativo(null, "teste13", "desc teste13", "proativo", "teste13", new Date(), 1.0f, 0, false, arquivoBytes, u13);
		Ativo ati14 = new Ativo(null, "teste14", "desc teste14", "iterativo", "teste14", new Date(), 1.0f, 0, false, arquivoBytes, u14);
		Ativo ati15 = new Ativo(null, "teste15", "desc teste15", "incremental", "teste15", new Date(), 1.0f, 0, false, arquivoBytes, u15);
		
		ativoRepo.saveAll(Arrays.asList(ati1, ati2, ati3));
		
		//Aprovadores
		
		Aprovadores apro1 = new Aprovadores(null, "Joao Parrilha", u1);
		Aprovadores apro2 = new Aprovadores(null, "Pedro Krassuski", u2);
		Aprovadores apro3 = new Aprovadores(null, "Felipe Reginato", u3);
		Aprovadores apro4 = new Aprovadores(null, "isabela", u4);
		Aprovadores apro5 = new Aprovadores(null, "natalia", u5);
		Aprovadores apro6 = new Aprovadores(null, "mariana", u6);
		Aprovadores apro7 = new Aprovadores(null, "cezar", u7);
		Aprovadores apro8 = new Aprovadores(null, "komarcheuski", u8);
		Aprovadores apro9 = new Aprovadores(null, "alexandre", u9);
		Aprovadores apro10 = new Aprovadores(null, "gustavo mattos", u10);
		
		aprovRepo.saveAll(Arrays.asList(apro1, apro2, apro3, apro4, apro5, apro6, apro7, apro8, apro9, apro10));
		
		//Checklist
		
		Checklist check1 = new Checklist(null, "teste01", "teste dom01", false, false, false, false, arquivoBytes, arquivoBytes, arquivoBytes);
		Checklist check2 = new Checklist(null, "teste02", "teste dom02", false, false, false, false, arquivoBytes, arquivoBytes, arquivoBytes);
		Checklist check3 = new Checklist(null, "teste03", "teste dom03", false, false, false, false, arquivoBytes, arquivoBytes, arquivoBytes);
		Checklist check4 = new Checklist(null, "teste04", "teste dom04", false, false, false, false, arquivoBytes, arquivoBytes, arquivoBytes);
		Checklist check5 = new Checklist(null, "teste05", "teste dom05", false, false, false, false, arquivoBytes, arquivoBytes, arquivoBytes);
		
		checkRepo.saveAll(Arrays.asList(check1, check2, check3, check4, check5));
		
		//CheckAprov
		
		//Check1
		CheckAprov checkAprov1 = new CheckAprov(null, apro1, check1, false, false, false, false);
		CheckAprov checkAprov2 = new CheckAprov(null, apro2, check1, false, false, false, false);
		CheckAprov checkAprov3 = new CheckAprov(null, apro3, check1, false, false, false, false);
		CheckAprov checkAprov4 = new CheckAprov(null, apro4, check1, false, false, false, false);
		CheckAprov checkAprov5 = new CheckAprov(null, apro5, check1, false, false, false, false);
		
		//Check2
		CheckAprov checkAprov6 = new CheckAprov(null, apro5, check2, false, false, false, false);
		CheckAprov checkAprov7 = new CheckAprov(null, apro5, check2, false, false, false, false);
		CheckAprov checkAprov8 = new CheckAprov(null, apro5, check2, false, false, false, false);
		CheckAprov checkAprov9 = new CheckAprov(null, apro5, check2, false, false, false, false);
		CheckAprov checkAprov10 = new CheckAprov(null, apro5, check2, false, false, false, false);
		
		checkAprovRepo.saveAll(Arrays.asList(checkAprov1, checkAprov2, checkAprov3, checkAprov4, checkAprov5, checkAprov6, checkAprov7, checkAprov8, checkAprov9, checkAprov10));
		
		//Avaliacao
		//Ativo1
		Avaliacao av1 = new Avaliacao(null, 5, "otimo", u15, ati1);
		Avaliacao av2 = new Avaliacao(null, 3, "bom", u10, ati1);
		Avaliacao av3 = new Avaliacao(null, 1, "ruim", u8, ati1);
		
		//Ativo2
		Avaliacao av4 = new Avaliacao(null, 2, "ok", u1, ati2);
		Avaliacao av5 = new Avaliacao(null, 5, "muito bom", u2, ati2);
		Avaliacao av6 = new Avaliacao(null, 1, "necessita mudanças", u3, ati2);
		
		//Ativo3
		Avaliacao av7 = new Avaliacao(null, 1, "achei pesismo", u7, ati3);
		Avaliacao av8 = new Avaliacao(null, 3, "bom", u9, ati3);
		Avaliacao av9 = new Avaliacao(null, 3, "podemos melhorar alguns pontos", u11, ati3);
		
		//Ativo4
		Avaliacao av10 = new Avaliacao(null, 5, "excelente", u4, ati4);
		Avaliacao av11 = new Avaliacao(null, 4, "parabens a equipe", u5, ati4);
		Avaliacao av12 = new Avaliacao(null, 5, "otimo", u6, ati4);
		
		//Ativo5
		Avaliacao av13 = new Avaliacao(null, 1, "muito rim", u12, ati5);
		Avaliacao av14 = new Avaliacao(null, 2, "nao serve para o que diz", u13, ati5);
		Avaliacao av15 = new Avaliacao(null, 1, "necessita de refinamentos urgentes", u14, ati5);
		
		avaRepo.saveAll(Arrays.asList(av1, av2, av3, av4, av5, av6, av7, av8, av9));
	}
	
	
}*/
