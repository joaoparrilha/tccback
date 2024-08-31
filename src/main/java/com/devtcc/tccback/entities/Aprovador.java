package com.devtcc.tccback.entities;

import java.io.Serializable;
import java.util.Objects;

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
public class Aprovador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String aprovador;
	
	@ManyToOne	
	@JoinColumn(name = "fk_Usuario_id")
	private Usuario usuario;
	
	//private Checklist checklist;
	
	public Aprovador (){
		
	}

	public Aprovador(Long id, String aprovador, Usuario usuario) {
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
		Aprovador other = (Aprovador) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
