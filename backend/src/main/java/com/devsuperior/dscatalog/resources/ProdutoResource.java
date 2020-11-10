package com.devsuperior.dscatalog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.dto.ProdutoDto;
import com.devsuperior.dscatalog.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping
	public ResponseEntity<Page<ProdutoDto>> consultarPorPaginacao(
			@RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@RequestParam(value = "quantidadeRegistros", defaultValue = "12") Integer quantidadeRegistros,
			@RequestParam(value = "direcao", defaultValue = "DESC") String direcao,
			@RequestParam(value = "ordenacao", defaultValue = "descricao") String ordenacao) {
		
		PageRequest pageRequest = PageRequest.of(pagina, quantidadeRegistros, Direction.valueOf(direcao), ordenacao);

		Page<ProdutoDto> lista = this.service.consultarPorPaginacao(pageRequest);
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDto> consultarPorId(@PathVariable Long id) {

		ProdutoDto dto = this.service.consultarPorId(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<ProdutoDto> salvar(@RequestBody ProdutoDto dto) {

		dto = this.service.salvar(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody ProdutoDto dto) {

		dto = this.service.atualizar(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProdutoDto> deletar(@PathVariable Long id) {
		
		this.service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
