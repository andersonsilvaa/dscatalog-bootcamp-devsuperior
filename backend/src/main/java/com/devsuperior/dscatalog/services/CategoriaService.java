package com.devsuperior.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoriaDto;
import com.devsuperior.dscatalog.entities.Categoria;
import com.devsuperior.dscatalog.repositories.CategoriaRepository;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	@Transactional(readOnly = true)
	public Page<CategoriaDto> consultarPorPaginacao(PageRequest pageRequest) {

		Page<Categoria> lista = this.repository.findAll(pageRequest);
		return lista.map(categoria -> new CategoriaDto(categoria));
	}

	@Transactional(readOnly = true)
	public CategoriaDto consultarPorId(Long id) {

		Optional<Categoria> obj = this.repository.findById(id);
		Categoria categoria = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		return new CategoriaDto(categoria);
	}

	@Transactional
	public CategoriaDto salvar(CategoriaDto dto) {

		Categoria categoria = dto.getDtoToEntity();
		categoria = this.repository.save(categoria);
		return new CategoriaDto(categoria);
	}

	@Transactional
	public CategoriaDto atualizar(Long id, CategoriaDto dto) {

		try {

			Categoria categoria = this.repository.getOne(id);
			categoria.setDescricao(dto.getDescricao());
			categoria = this.repository.save(categoria);
			return new CategoriaDto(categoria);
		} 
		catch (EntityNotFoundException e) {

			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}

	public void deletar(Long id) {

		try {
			this.repository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {

			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
		catch (DataIntegrityViolationException e) {
			
			throw new DataBaseException("Violação de integridade");
		}
	}
}
