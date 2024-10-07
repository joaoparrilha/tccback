package com.devtcc.tccback.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.entities.CheckAprov;
import com.devtcc.tccback.entities.Checklist;
import com.devtcc.tccback.repositories.AprovadoresRepository;
import com.devtcc.tccback.repositories.AtivoRepository;
import com.devtcc.tccback.repositories.CheckAprovRepository;
import com.devtcc.tccback.repositories.ChecklistRepository;
import com.devtcc.tccback.repositories.UsuarioRepository;
import com.devtcc.tccback.services.exception.AtivoUpdateException;
import com.devtcc.tccback.services.exception.DoubleRegisterException;

@Service
public class CheckAprovService {
	
	@Autowired
	private CheckAprovRepository repo;

	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private AprovadoresRepository repoAprov;
	
	@Autowired
	private ChecklistRepository repoCheck;
	
	@Autowired
	private AtivoRepository repoAtivo;
	
	public List<CheckAprov> findAll(){
		return repo.findAll();
	}
	
	public CheckAprov findById(Long id){
		Optional<CheckAprov> checkAprov = repo.findById(id);
		return checkAprov.get();
	}
	
//	public List<CheckAprov> findByAprovId(Long id){
//		return repo.findByAprovadores_Id(id);
//	}
	
	public List<CheckAprov> findByAprovId(Long id){
		List<CheckAprov> checkAprovs = repo.findByAprovadores_Id(id);
		List<CheckAprov> retorno = new ArrayList<>();
		for (CheckAprov checkAprov : checkAprovs) {
			CheckAprov checkAprovR =  new CheckAprov();
			checkAprovR.setId(checkAprov.getId());
			checkAprovR.setRevisao(checkAprov.getRevisao());
			checkAprovR.setRefinamento(checkAprov.getRefinamento());
			checkAprovR.setTeste(checkAprov.getTeste());
			checkAprovR.setHomologacao(checkAprov.getHomologacao());
			Checklist checklist = new Checklist();
		      checklist.setId(checkAprov.getChecklist().getId());
		      checklist.setNome(checkAprov.getChecklist().getNome());
		      checkAprovR.setChecklist(checklist);
		      
			retorno.add(checkAprovR);
		}
		
		return retorno;
	}
	
	public CheckAprov insert(CheckAprov obj){
		
		List<CheckAprov> list = repo.findByAprovadores_Id(obj.getAprovador().getId());
		CheckAprov objInsert = new CheckAprov(); 
		objInsert = obj;
		
		for(CheckAprov check : list) {
			if(!(obj.getAprovador().getId()== check.getAprovador().getId()) && !(obj.getChecklist().getId() == check.getChecklist().getId())) {
				objInsert = repo.save(obj);
			}else{			
				throw new DoubleRegisterException();
			}
		}		
		return obj;
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public CheckAprov update(Long id, CheckAprov obj){
		Optional<CheckAprov> opCheckAprov = repo.findById(id);
		CheckAprov checkAprov = opCheckAprov.get();
		
		Long idChecklist = checkAprov.getChecklist().getId();
		
		List<CheckAprov> listCA = repo.findByChecklist_Id(idChecklist);
		
		Optional<Checklist> opCheck = repoCheck.findById(idChecklist);
		Checklist checkObj = opCheck.get();
		
		//Optional<Ativo> opAtivo = repoAtivo.findById(idChecklist);
		//Ativo ativo = opAtivo.get();
		
		updateData(checkAprov, obj);
		CheckAprov checkAprovSave = repo.save(checkAprov);
		
		Integer countRevisao = 0;
		Integer countRefinamento = 0;
		Integer countTeste = 0;
		Integer countHomologacao = 0;
		
		for(CheckAprov check: listCA) {			
			if(check.getRevisao() == true) {
				countRevisao += 1;
				if(countRevisao >= 4) {
					checkObj.setRevisao(true);
				}
			}			
			if(check.getRefinamento() == true) {
				countRefinamento += 1;
				if(countRefinamento >= 4) {
					checkObj.setRefinamento(true);
				}
			}
			if(check.getTeste() == true) {
				countTeste += 1;
				if(countTeste >= 4) {
					checkObj.setTeste(true);
				}
			}
			if(check.getHomologacao() == true) {
				countHomologacao += 1;
				if(countHomologacao >= 4) {
					checkObj.setHomologacao(true);
				}
			}
		}		
		checkObj = repoCheck.save(checkObj);
		
		/*if(checkObj.getHomologacao() == true) {
			ativo.setValidacao(true);
		}
		ativo = repoAtivo.save(ativo);*/
		
		//repo.save(checkAprovSave)			
		return checkAprovSave;
	}
	
	public void updateData(CheckAprov entity, CheckAprov obj) {
		
		Optional<CheckAprov> opCheckAprov = repo.findById(entity.getId());
		CheckAprov checkAprov = opCheckAprov.get();
		//CheckAprov opCheckAprov = repo.getReferenceById(entity.getId());
		Long id = checkAprov.getChecklist().getId();
		
		Optional<Checklist> opCheck = repoCheck.findById(id);
		Checklist check = opCheck.get();
		
		
			if(obj.getId() != null) {
				entity.setId(obj.getId());
			}
			
			if(obj.getRevisao() != null) {
				entity.setRevisao(obj.getRevisao());
			}
			
			if(obj.getHomologacao() != null) {
				if(check.getTeste() ==  true) {
					entity.setHomologacao(obj.getHomologacao());
				}else {
					entity.setHomologacao(false);
					throw new AtivoUpdateException();
				}
			}
			
			if(obj.getTeste() != null) {
				if(check.getRefinamento() == true) {
					entity.setTeste(obj.getTeste());
				}else {
					entity.setTeste(false);
					throw new AtivoUpdateException();
				}
			}
					
			if(obj.getRefinamento() != null) {
				if(check.getRevisao() == true) {
					entity.setRefinamento(obj.getRefinamento());
				}else {
					entity.setRefinamento(false);
					throw new AtivoUpdateException();
				}
					
			}
		
		
	}
}
