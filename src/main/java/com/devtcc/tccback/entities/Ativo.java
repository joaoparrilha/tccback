package com.devtcc.tccback.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Ativo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private String tipo;
	private String dominio;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
	private Date criado;
	private Float versao;
	private Integer download;
	private Boolean validacao;
	
	@Lob
	private byte[] arquivo;
	
	@ManyToOne
	@JoinColumn(name = "fk_Usuario_id")
	private Usuario usuario;
	
	@OneToOne(mappedBy = "ativo", cascade = CascadeType.ALL)
	private Checklist checklist;
	
	@OneToMany(mappedBy = "ativo")
	private List<Avaliacao> avaliacao = new ArrayList<>();
	
	public Ativo() {
		
	}

	public Ativo(Long id, String nome, String descricao, String tipo, String dominio, Date criado, Float versao,
			Integer download, Boolean validacao, byte[] arquivo, Usuario usuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
		this.dominio = dominio;
		this.criado = criado;
		this.versao = versao;
		this.download = download;
		this.validacao = validacao;
		this.arquivo = arquivo;
		this.usuario = usuario;
	}

	public Ativo(Long id, String nome, String descricao, String tipo, String dominio, Date criado, Float versao,
			Integer download, Boolean validacao, byte[] arquivo, Usuario usuario, Checklist checklist,
			List<Avaliacao> avaliacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
		this.dominio = dominio;
		this.criado = criado;
		this.versao = versao;
		this.download = download;
		this.validacao = validacao;
		this.arquivo = arquivo;
		this.usuario = usuario;
		this.checklist = checklist;
		this.avaliacao = avaliacao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public Date getCriado() {
		return criado;
	}

	public void setCriado(Date criado) {
		this.criado = criado;
	}

	public Float getVersao() {
		return versao;
	}

	public void setVersao(Float versao) {
		this.versao = versao;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Boolean getValidacao() {
		return validacao;
	}

	public void setValidacao(Boolean validacao) {
		this.validacao = validacao;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public Usuario getUsuario() {
		return usuario;
	}	

	public List<Avaliacao> getAvaliacao() {
		return avaliacao;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void setAvaliacao(List<Avaliacao> avaliacao) {
		this.avaliacao = avaliacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public Checklist getChecklist() {
		return checklist;
	}

	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ativo other = (Ativo) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
