package com.devsuperior.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * MÉTODO RESPONSÁVEL POR TRATAR O ERRO DE ENTIDADE NÃO LOCALIZADA E DISPARAR UM
	 * ERRO PERSONALIZADO PARA O USUÁRIO
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Error> entityNotFound(ResourceNotFoundException exception, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		Error error = new Error();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("Resource not found");
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}

	/**
	 * MÉTODO RESPONSÁVEL POR TRATAR O ERRO DE VIOLAÇÃO DE INTEGRIDADE E DISPARAR UM
	 * ERRO PERSONALIZADO PARA O USUÁRIO
	 */
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<Error> dataBase(DataBaseException exception, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		Error error = new Error();
		error.setTimestamp(Instant.now());
		error.setStatus(status.value());
		error.setError("DataBase exception");
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(status).body(error);
	}
}
