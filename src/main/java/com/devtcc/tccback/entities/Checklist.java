package com.devtcc.tccback.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Checklist")
//@AllArgsConstructor
//@NoArgsConstructor
public class Checklist implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String dominio;
	private Boolean revisao;
	private Boolean refinamento;
	private byte[] docrefi;
	private Boolean teste;
	private byte[] docteste;
	private Boolean homologacao;
	private byte[] dochomo;
	
	@ManyToMany(mappedBy = "checklists")
	private List<Aprovadores> aprovadores = new ArrayList<>();
	
	@OneToOne
	@MapsId
	private Ativo ativo;
	
	public Checklist() {
		
	}
 
	public Checklist(Long id, String nome, String dominio, Boolean revisao, Boolean refinamento, byte[] docrefi,
			Boolean teste, byte[] docteste, Boolean homologacao, byte[] dochomo) {
		super();
		this.id = id;
		this.nome = nome;
		this.dominio = dominio;
		this.revisao = revisao;
		this.refinamento = refinamento;
		this.docrefi = docrefi;
		this.teste = teste;
		this.docteste = docteste;
		this.homologacao = homologacao;
		this.dochomo = dochomo;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public Boolean getRevisao() {
		return revisao;
	}

	public void setRevisao(Boolean revisao) {
		this.revisao = revisao;
	}

	public Boolean getRefinamento() {
		return refinamento;
	}

	public void setRefinamento(Boolean refinamento) {
		this.refinamento = refinamento;
	}

	public byte[] getDocrefi() {
		return docrefi;
	}

	public void setDocrefi(byte[] docrefi) {
		this.docrefi = docrefi;
	}

	public Boolean getTeste() {
		return teste;
	}

	public void setTeste(Boolean teste) {
		this.teste = teste;
	}

	public byte[] getDocteste() {
		return docteste;
	}

	public void setDocteste(byte[] docteste) {
		this.docteste = docteste;
	}

	public Boolean getHomologacao() {
		return homologacao;
	}

	public void setHomologacao(Boolean homologacao) {
		this.homologacao = homologacao;
	}

	public byte[] getDochomo() {
		return dochomo;
	}

	public void setDochomo(byte[] dochomo) {
		this.dochomo = dochomo;
	}

	public List<Aprovadores> getAprovadores() {
		return aprovadores;
	}

	public void setAprovadores(List<Aprovadores> aprovadores) {
		this.aprovadores = aprovadores;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Checklist other = (Checklist) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
