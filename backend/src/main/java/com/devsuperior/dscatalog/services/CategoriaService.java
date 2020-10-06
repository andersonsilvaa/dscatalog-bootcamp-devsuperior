package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoriaDto;
import com.devsuperior.dscatalog.entities.Categoria;
import com.devsuperior.dscatalog.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public List<CategoriaDto> consultarTodos() {

		List<Categoria> lista = this.repository.findAll();
		return lista.stream().map(categoria -> new CategoriaDto(categoria)).collect(Collectors.toList());
	}
}
