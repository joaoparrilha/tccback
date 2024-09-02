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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Aprovadores")
@AllArgsConstructor
@NoArgsConstructor
public class Aprovadores implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String aprovador;
	
	@ManyToOne	
	@JoinColumn(name = "fk_Usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "fk_Checklist_id")
	@JsonIgnore
	private Checklist checklist;
	
	public Aprovadores (){
		
	}

	public Aprovadores(Long id, String aprovador, Usuario usuario) {
		super();
		this.id = id;
		this.aprovador = aprovador;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAprovador() {
		return aprovador;
	}

	public void setAprovador(String aprovador) {
		this.aprovador = aprovador;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
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
		Aprovadores other = (Aprovadores) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
