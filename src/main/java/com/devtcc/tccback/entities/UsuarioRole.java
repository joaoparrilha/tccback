package com.devtcc.tccback.entities;

public enum UsuarioRole {
	
	USUARIO("usuario"),
	VALIDADOR("validador"),
	ADMINISTRADOR("administrador");
	
	private String role;
	
	UsuarioRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
}
