package com.devtcc.tccback.services.exception;

public class DoubleRegisterException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	
	public DoubleRegisterException(){
		super("Checklist já aderido ao aprovador.");
	}
}
