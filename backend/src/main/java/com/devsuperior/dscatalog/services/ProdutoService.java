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

import com.devsuperior.dscatalog.dto.ProdutoDto;
import com.devsuperior.dscatalog.entities.Produto;
import com.devsuperior.dscatalog.repositories.ProdutoRepository;
import com.devsuperior.dscatalog.services.exceptions.DataBaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Transactional(readOnly = true)
	public Page<ProdutoDto> consultarPorPaginacao(PageRequest pageRequest) {

		Page<Produto> lista = this.repository.findAll(pageRequest);
		return lista.map(produto -> new ProdutoDto(produto));
	}

	@Transactional(readOnly = true)
	public ProdutoDto consultarPorId(Long id) {

		Optional<Produto> obj = this.repository.findById(id);
		Produto produto = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
		return new ProdutoDto(produto, produto.getCategorias());
	}

	@Transactional
	public ProdutoDto salvar(ProdutoDto dto) {

		Produto produto = dto.getDtoToEntity();
		produto = this.repository.save(produto);
		return new ProdutoDto(produto);
	}

	@Transactional
	public ProdutoDto atualizar(Long id, ProdutoDto dto) {

		try {

			Produto produto = this.repository.getOne(id);
			produto.setDescricao(dto.getDescricao());
			produto = this.repository.save(produto);
			return new ProdutoDto(produto);
		} catch (EntityNotFoundException e) {

			throw new ResourceNotFoundException("Id não encontrado " + id);
		}
	}

	public void deletar(Long id) {

		try {
			this.repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {

			throw new ResourceNotFoundException("Id não encontrado " + id);
		} catch (DataIntegrityViolationException e) {

			throw new DataBaseException("Violação de integridade");
		}
	}
}
