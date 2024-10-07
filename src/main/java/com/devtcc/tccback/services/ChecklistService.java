package com.devtcc.tccback.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.entities.Checklist;
import com.devtcc.tccback.entities.Usuario;
import com.devtcc.tccback.repositories.AtivoRepository;
import com.devtcc.tccback.repositories.ChecklistRepository;
import com.devtcc.tccback.services.exception.AtivoUpdateException;

@Service
public class ChecklistService {
	
	@Autowired
	private ChecklistRepository repo;
	
	@Autowired
	private AtivoRepository ativoRepo;
	
	public List<Checklist> findAll(){
		List<Checklist> checklists = repo.findAll();
		List<Checklist> retorno = new ArrayList<>();
		for (Checklist checklist : checklists) {
			Checklist checklistR =  new Checklist();
			checklistR.setId(checklist.getId());
			checklistR.setNome(checklist.getNome());
			checklistR.setDominio(checklist.getDominio());
			checklistR.setTeste(checklist.getTeste());
			checklistR.setRefinamento(checklist.getRefinamento());
			checklistR.setRevisao(checklist.getRevisao());
			checklistR.setHomologacao(checklist.getHomologacao());
			retorno.add(checklistR);
		}
		
		return retorno;
	}
	
	public Checklist findById(Long id) {
	    Optional<Checklist> optionalChecklist = repo.findById(id);
	    if (optionalChecklist.isPresent()) {
	        Checklist checklist = optionalChecklist.get();
	        Checklist checklistR = new Checklist();
	        checklistR.setId(checklist.getId());
	        checklistR.setNome(checklist.getNome());
	        checklistR.setDominio(checklist.getDominio());
	        checklistR.setTeste(checklist.getTeste());
	        checklistR.setRefinamento(checklist.getRefinamento());
	        checklistR.setRevisao(checklist.getRevisao());
	        checklistR.setHomologacao(checklist.getHomologacao());
	        return checklistR;
	    } else {
	        return null; // ou lance uma exceção, se preferir
	    }
	}

	public Checklist findByAtivoId(Long ativo_id) {
	    Optional<Checklist> optionalChecklist = repo.findByAtivoId(ativo_id);
	    if (optionalChecklist.isPresent()) {
	        Checklist checklist = optionalChecklist.get();
	        Checklist checklistR = new Checklist();
	        checklistR.setId(checklist.getId());
	        checklistR.setNome(checklist.getNome());
	        checklistR.setDominio(checklist.getDominio());
	        checklistR.setTeste(checklist.getTeste());
	        checklistR.setRefinamento(checklist.getRefinamento());
	        checklistR.setRevisao(checklist.getRevisao());
	        checklistR.setHomologacao(checklist.getHomologacao());
	        checklistR.setDocrefi(checklist.getDocrefi());
	        checklistR.setDocteste(checklist.getDocteste());
	        checklistR.setDochomo(checklist.getDochomo());
	        return checklistR;
	    } else {
	        return null; // ou lance uma exceção, se preferir
	    }
	}
	
	public Checklist insert(Checklist obj, Long id) {
		
		Optional<Ativo> opAtivo = ativoRepo.findById(id);
		if(opAtivo.isPresent()){
			Ativo ativo = opAtivo.get();
			obj.setAtivo(ativo);
		}
				
		
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Checklist update(Long id, Checklist obj) {
		
		Optional<Checklist> opCheck = repo.findById(id);
	    Checklist entity = opCheck.get();

	    Long idAtivo = entity.getAtivo().getId();
	    Optional<Ativo> opAtivo = ativoRepo.findById(idAtivo);
	    Ativo ativo = opAtivo.get();
	    
	    updateData(entity, obj);
	    //updateData(entity, obj);
	    
	    Checklist entityRet = entity;
	    
	    entity = repo.save(entity);
	    
	    if(entity.getDochomo() != null) {
	    	ativo.setValidacao(true);	    	
	    }
	    ativoRepo.save(ativo);
	    
	    return entityRet; 
	}

	private void updateData(Checklist entity, Checklist obj) {
		
		if(obj.getId() != null) {
			entity.setId(obj.getId());
		}
		
		if(obj.getNome() != null) {
			entity.setNome(obj.getNome());
		}
		
		if(obj.getDominio() != null) {
			entity.setDominio(obj.getDominio());
		}
		
		if(obj.getHomologacao() != null) {
			if(entity.getTeste() == true) {
				entity.setHomologacao(obj.getHomologacao());
			}else{
				throw new AtivoUpdateException();
			}
		}
		
		if(obj.getDochomo() != null) {
			if(entity.getTeste() == true) {
				entity.setDochomo(obj.getDochomo());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(obj.getTeste() != null) {
			if(entity.getRefinamento() == true) {
				entity.setTeste(obj.getTeste());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(obj.getDocteste() != null) {
			if(entity.getRefinamento() == true){
				entity.setDocteste(obj.getDocteste());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(obj.getRefinamento() != null) {			
			if(entity.getRevisao() == true) {
				entity.setRefinamento(obj.getRefinamento());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(obj.getDocrefi() != null) {
			if(entity.getRevisao() == true) {
				entity.setDocrefi(obj.getDocrefi());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(obj.getRevisao() != null) {
			entity.setRevisao(obj.getRevisao());
		}
		
		
		if(obj.getAprovadores() != null) {
			entity.setAprovadores(obj.getAprovadores());
		}
	}
}
