package com.devtcc.tccback.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String matricula;
	private String email;
	private String senha;
	private String telefone;
	private UsuarioRole role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Ativo> ativos = new ArrayList<>();
	
	
	public Usuario() {
		
	}
	
	public Usuario(Long id, String nome, String matricula, String email, String senha, String telefone,
			UsuarioRole role) {
		super();
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.role = role;
	}
	
	public Usuario(String nome, String matricula, String email, String senha, String telefone, UsuarioRole role) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.role = role;
	}
	
	

	public Usuario(Long id, String nome, String matricula, String email, String senha, String telefone,
			UsuarioRole role, List<Ativo> ativos) {
		super();
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.role = role;
		this.ativos = ativos;
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public UsuarioRole getRole() {
		return role;
	}

	public void setRole(UsuarioRole role) {
		this.role = role;
	}
	
	public List<Ativo> getAtivos() {
		return ativos;
	}

	public void setAtivos(List<Ativo> ativos) {
		this.ativos = ativos;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
	

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", email=" + email + ", senha="
				+ senha + ", telefone=" + telefone + ", role=" + role + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		if(this.role == UsuarioRole.ADMINISTRADOR) return List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"), new SimpleGrantedAuthority("ROLE_USUARIO"),new SimpleGrantedAuthority("ROLE_VALIDADOR"));
		else return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
