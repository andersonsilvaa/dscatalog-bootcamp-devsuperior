package com.devsuperior.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * MÉTODO RESPONSÁVEL POR TRATAR O ERRO DE ENTIDADE NÃO LOCALIZADA E DISPARAR UM
	 * ERRO PERSONALIZADO PARA O USUÁRIO
	 */
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Error> entityNotFound(EntityNotFoundException exception, HttpServletRequest request) {

		Error error = new Error();
		error.setTimestamp(Instant.now());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setError("Resource not found");
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
