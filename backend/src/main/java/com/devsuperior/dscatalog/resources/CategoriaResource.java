package com.devsuperior.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.entities.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {

		List<Categoria> lista = new ArrayList<>();
		lista.add(new Categoria(1L, "Livros"));
		lista.add(new Categoria(2L, "Eletrônicos"));

		return ResponseEntity.ok().body(lista);
	}
}
