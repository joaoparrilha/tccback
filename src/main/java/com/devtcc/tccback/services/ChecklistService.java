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
	    Checklist entity = repo.getReferenceById(id);

	    updateData(entity, obj);
	    //updateData(entity, obj);
	    
	    return repo.save(entity); 
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
		
		if(entity.getTeste() == true) {
			if(obj.getHomologacao() != null) {
				entity.setHomologacao(obj.getHomologacao());
			}else{
				throw new AtivoUpdateException();
			}
		}
		
		if(entity.getTeste() == true) {
			if(obj.getDochomo() != null) {
				entity.setDochomo(obj.getDochomo());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(entity.getRefinamento() == true) {
			if(obj.getTeste() != null) {
				entity.setTeste(obj.getTeste());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(entity.getRefinamento() == true) {
			if(obj.getDocteste() != null){
				entity.setDocteste(obj.getDocteste());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(entity.getRevisao() == true) {			
			if(obj.getRefinamento() != null) {
				entity.setRefinamento(obj.getRefinamento());
			}else {
				throw new AtivoUpdateException();
			}
		}
		
		if(entity.getRevisao() == true) {
			if(obj.getDocrefi() != null) {
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
