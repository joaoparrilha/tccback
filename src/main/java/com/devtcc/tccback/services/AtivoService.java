package com.devtcc.tccback.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.entities.Usuario;
import com.devtcc.tccback.repositories.AtivoRepository;
import com.devtcc.tccback.repositories.UsuarioRepository;

@Service
public class AtivoService {
	
	@Autowired
	private AtivoRepository repo;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	public List<Ativo> findAll(){
		List<Ativo> ativos = repo.findAll();
		List<Ativo> retorno = new ArrayList<>();
		for (Ativo ativo : ativos) {
			Ativo ativoR =  new Ativo();
			ativoR.setId(ativo.getId());
			ativoR.setNome(ativo.getNome());
			ativoR.setDescricao(ativo.getDescricao());
			ativoR.setDominio(ativo.getDominio());
			ativoR.setTipo(ativo.getTipo());
			ativoR.setDownload(ativo.getDownload());
			ativoR.setValidacao(ativo.getValidacao());
			ativoR.setVersao(ativo.getVersao());
			ativoR.setDependencia(ativo.getDependencia());
			retorno.add(ativoR);
		}
		
		return retorno;
	}
	
	
	public Ativo findById(Long id){
		Optional<Ativo> usuario = repo.findById(id);
		return usuario.get();
	}
	
	public List<Ativo> findByUsuario(Long fk_usuario_id){
		List<Ativo> ativos = repo.findByUsuarioId(fk_usuario_id);
		List<Ativo> retorno = new ArrayList<>();
		for (Ativo ativo : ativos) {
			Ativo ativoR =  new Ativo();
			ativoR.setId(ativo.getId());
			ativoR.setNome(ativo.getNome());
			ativoR.setDescricao(ativo.getDescricao());
			ativoR.setDominio(ativo.getDominio());
			ativoR.setTipo(ativo.getTipo());
			ativoR.setDownload(ativo.getDownload());
			ativoR.setValidacao(ativo.getValidacao());
			ativoR.setVersao(ativo.getVersao());
			ativoR.setDependencia(ativo.getDependencia());
			retorno.add(ativoR);
		}
		
		return retorno;
	}
	
	public List<Ativo> findValidacaoFalse(){
		List<Ativo> ativos = repo.findByValidacao(false);
		List<Ativo> retorno = new ArrayList<>();
		for (Ativo ativo : ativos) {
			Ativo ativoR =  new Ativo();
			ativoR.setId(ativo.getId());
			ativoR.setNome(ativo.getNome());
			ativoR.setDescricao(ativo.getDescricao());
			ativoR.setDominio(ativo.getDominio());
			ativoR.setTipo(ativo.getTipo());
			ativoR.setValidacao(ativo.getValidacao());
			ativoR.setVersao(ativo.getVersao());
			ativoR.setDependencia(ativo.getDependencia());
			retorno.add(ativoR);
		}
		
		return retorno;
		//return repo.findByValidacao(false);
	}
	
	public List<Ativo> findValidacaoTrue(){
		List<Ativo> ativos = repo.findByValidacao(true);
		List<Ativo> retorno = new ArrayList<>();
		for (Ativo ativo : ativos) {
			Ativo ativoR =  new Ativo();
			ativoR.setId(ativo.getId());
			ativoR.setNome(ativo.getNome());
			ativoR.setDescricao(ativo.getDescricao());
			ativoR.setDominio(ativo.getDominio());
			ativoR.setTipo(ativo.getTipo());
			ativoR.setValidacao(ativo.getValidacao());
			ativoR.setVersao(ativo.getVersao());
			ativoR.setDependencia(ativo.getDependencia());
			retorno.add(ativoR);
		}
		
		return retorno;
		
		//return repo.findByValidacao(true);
	}
	
	public Ativo insert(Ativo obj, Long id) {
		
		Optional<Usuario> opUsuario = repoUsuario.findById(id);
		if(opUsuario.isPresent()){
			Usuario usuario = opUsuario.get();
			obj.setUsuario(usuario);
		}
				
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		obj.setCriado(date);
		return repo.save(obj);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Ativo update(Long id, Ativo obj){
		Ativo entity = repo.getReferenceById(id);
		updateData(entity, obj);
		return repo.save(entity);
	}

	private void updateData(Ativo entity, Ativo obj) {
		
		if(obj.getId() != null) {
			entity.setId(obj.getId());
		}
		
		if(obj.getNome() != null) {
			entity.setNome(obj.getNome());
		}
		
		if(obj.getDescricao() != null) {
			entity.setDescricao(obj.getDescricao());
		}
		
		if(obj.getTipo() != null) {
			entity.setTipo(obj.getTipo());
		}
		
		if(obj.getDominio() != null) {
			entity.setDominio(obj.getDominio());
		}
		
		if(obj.getDominio() != null) {
			entity.setDominio(obj.getDominio());
		}
		
		if(obj.getCriado() != null) {
			entity.setCriado(obj.getCriado());
		}
		
		if(obj.getVersao() != null) {
			entity.setVersao(obj.getVersao());
		}
		
		if(obj.getDownload() != null) {
			entity.setDownload(obj.getDownload());
		}
		
		if(obj.getValidacao() != null) {
			entity.setValidacao(obj.getValidacao());
		}
		
		if(obj.getArquivo() != null) {
			entity.setArquivo(obj.getArquivo());
		}
		
		if(obj.getUsuario() != null) {
			entity.setUsuario(obj.getUsuario());
		}
		
		if(obj.getDependencia() != null) {
			entity.setDependencia(obj.getDependencia());
		}
	}
	
	
}
