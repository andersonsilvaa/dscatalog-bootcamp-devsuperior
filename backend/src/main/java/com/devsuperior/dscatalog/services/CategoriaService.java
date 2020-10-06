package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoriaDto;
import com.devsuperior.dscatalog.entities.Categoria;
import com.devsuperior.dscatalog.repositories.CategoriaRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public List<CategoriaDto> consultarTodos() {

		List<Categoria> lista = this.repository.findAll();
		return lista.stream().map(categoria -> new CategoriaDto(categoria)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public CategoriaDto consultarPorId(Long id) {

		Optional<Categoria> obj = this.repository.findById(id);
		Categoria categoria = obj.orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
		return new CategoriaDto(categoria);
	}

	@Transactional
	public CategoriaDto salvar(CategoriaDto dto) {

		Categoria categoria = dto.getDtoToEntity();
		categoria = this.repository.save(categoria);
		return new CategoriaDto(categoria);
	}
}
