package com.devtcc.tccback.services.exception;

public class AtivoUpdateException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AtivoUpdateException (){
		super("Etapas anteriores não validadas.");
	}
}
