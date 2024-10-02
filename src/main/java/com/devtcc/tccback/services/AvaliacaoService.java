package com.devtcc.tccback.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.entities.Avaliacao;
import com.devtcc.tccback.entities.Checklist;
import com.devtcc.tccback.entities.Usuario;
import com.devtcc.tccback.repositories.AtivoRepository;
import com.devtcc.tccback.repositories.AvaliacaoRepository;
import com.devtcc.tccback.repositories.UsuarioRepository;

@Service
public class AvaliacaoService {
	
	@Autowired
	private AvaliacaoRepository repo;
	
   @Autowired
    private UsuarioRepository repoUsuario;

    @Autowired
    private AtivoRepository ativoRepo; 
	
	public List<Avaliacao> findAll(){
		return repo.findAll();
	}
	
	public Avaliacao findById(Long id){
		Optional<Avaliacao> avaliacao = repo.findById(id);
		return avaliacao.get();
	}
	
	public List<Avaliacao> findByAtivoId(Long fk_ativo_id) {
	    return repo.findByAtivoId(fk_ativo_id); // Isso já retorna a lista completa
	}
	
	public Avaliacao insert(Avaliacao obj, Long fk_ativo_id, Long fk_usuario_id) {
	   
		Optional<Ativo> opAtivo = ativoRepo.findById(fk_ativo_id);
		if(opAtivo.isPresent()){
			Ativo ativo = opAtivo.get();
			obj.setAtivo(ativo);
		}

		Optional<Usuario> opUsuario = repoUsuario.findById(fk_usuario_id);
        if (opUsuario.isPresent()) {
            Usuario usuario = opUsuario.get();
            obj.setUsuario(usuario);
		}

	    return repo.save(obj);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Avaliacao update(Long id, Avaliacao obj){
		Avaliacao entity = repo.getReferenceById(id);
		updateData(entity, obj);
		return repo.save(entity);
	}

	private void updateData(Avaliacao entity, Avaliacao obj) {
		
		if(obj.getId() != null) {
			entity.setId(obj.getId());
		}
		
		if(obj.getAvaliacao() != null) {
			entity.setAvaliacao(obj.getAvaliacao());
		}
		
		if(obj.getComentario() != null) {
			entity.setComentario(obj.getComentario());
		}		
	}
}
