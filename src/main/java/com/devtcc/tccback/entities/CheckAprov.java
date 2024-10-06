package com.devtcc.tccback.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_check_aprov")
public class CheckAprov implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "aprovadores_id", referencedColumnName = "id")
	private Aprovadores aprovadores;

	@ManyToOne
	@JoinColumn(name = "checklist_id", referencedColumnName = "id")
	private Checklist checklist;

	private Boolean revisao;

	private Boolean refinamento;

	private Boolean teste;

	private Boolean homologacao;
	
	public CheckAprov() {
		
	}

	public CheckAprov(Long id, Aprovadores aprovador, Checklist checklist, Boolean revisao, Boolean refinamento,
			Boolean teste, Boolean homologacao) {
		super();
		this.id = id;
		this.aprovadores = aprovador;
		this.checklist = checklist;
		this.revisao = revisao;
		this.refinamento = refinamento;
		this.teste = teste;
		this.homologacao = homologacao;
	}
	
	public CheckAprov(Aprovadores aprovador, Checklist checklist, Boolean revisao, Boolean refinamento,
			Boolean teste, Boolean homologacao) {
		super();
		this.aprovadores = aprovador;
		this.checklist = checklist;
		this.revisao = revisao;
		this.refinamento = refinamento;
		this.teste = teste;
		this.homologacao = homologacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aprovadores getAprovador() {
		return aprovadores;
	}

	public void setAprovador(Aprovadores aprovador) {
		this.aprovadores = aprovador;
	}

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
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

	public Boolean getTeste() {
		return teste;
	}

	public void setTeste(Boolean teste) {
		this.teste = teste;
	}

	public Boolean getHomologacao() {
		return homologacao;
	}

	public void setHomologacao(Boolean homologacao) {
		this.homologacao = homologacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(checklist);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckAprov other = (CheckAprov) obj;
		return Objects.equals(checklist, other.checklist);
	}

	@Override
	public String toString() {
		return "CheckAprov [id=" + id + ", aprovador=" + aprovadores + ", checklist=" + checklist + ", revisao=" + revisao
				+ ", refinamento=" + refinamento + ", teste=" + teste + ", homologacao=" + homologacao + "]";
	}
	 
	
}
