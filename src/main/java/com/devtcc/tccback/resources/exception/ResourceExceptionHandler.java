package com.devtcc.tccback.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devtcc.tccback.services.exception.AtivoUpdateException;
import com.devtcc.tccback.services.exception.DoubleRegisterException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(AtivoUpdateException.class)
	public ResponseEntity<StandardError> ativoUpdateException(AtivoUpdateException e, HttpServletRequest request){
		String error = "Não é possível pular etapas de validação.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
				
	}
	
	@ExceptionHandler(DoubleRegisterException.class)
	public ResponseEntity<StandardError> doubleRegisterException(DoubleRegisterException e, HttpServletRequest request){
		String error = "Checklist já aderido ao aprovador.";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
