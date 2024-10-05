package com.devtcc.tccback.services;

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
	
	public List<CheckAprov> findByAprovId(Long id){
		return repo.findByAprovadores_Id(id);
	}
	
	public CheckAprov insert(CheckAprov obj){
		return repo.save(obj);
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
		
		Optional<Ativo> opAtivo = repoAtivo.findById(idChecklist);
		Ativo ativo = opAtivo.get();
		
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
		
		if(checkObj.getHomologacao() == true) {
			ativo.setValidacao(true);
		}
		ativo = repoAtivo.save(ativo);
		
		//repo.save(checkAprovSave)			
		return checkAprovSave;
	}
	
	public void updateData(CheckAprov entity, CheckAprov obj) {
		
		CheckAprov checkAprov = repo.getReferenceById(entity.getId());
		//CheckAprov opCheckAprov = repo.getReferenceById(entity.getId());
		Long id = checkAprov.getChecklist().getId();
		
		Optional<Checklist> opCheck = repoCheck.findById(id);
		Checklist check = opCheck.get();
		
		if(obj.getId() != null) {
			entity.setId(obj.getId());
		}
		
		if(obj.getHomologacao() != null) {
			if(check.getTeste() ==  true) {
				entity.setHomologacao(obj.getHomologacao());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(obj.getTeste() != null) {
			if(check.getRefinamento() == true) {
				entity.setTeste(obj.getTeste());
			}else {
				throw new AtivoUpdateException();
			}
		}
				
		if(obj.getRefinamento() != null) {
			if(check.getRevisao() == true) {
				entity.setRefinamento(obj.getRefinamento());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(obj.getRevisao() != null) {
			entity.setRevisao(obj.getRevisao());
		}
	}
}
