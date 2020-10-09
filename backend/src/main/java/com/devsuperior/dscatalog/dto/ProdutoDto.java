package com.devsuperior.dscatalog.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.devsuperior.dscatalog.entities.Categoria;
import com.devsuperior.dscatalog.entities.Produto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private BigDecimal valor;
	private String urlImagem;
	private Instant data;
	private List<CategoriaDto> categorias = new ArrayList<CategoriaDto>();

	/***************************************
	 ** CONSTRUTORES
	 **************************************/

	public ProdutoDto(Long id, String nome, String descricao, BigDecimal valor, String urlImagem, Instant data) {

		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.urlImagem = urlImagem;
		this.data = data;
	}

	public ProdutoDto(Produto produto) {

		this.id = produto.getId();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.valor = produto.getValor();
		this.urlImagem = produto.getUrlImagem();
		this.data = produto.getData();
	}

	public ProdutoDto(Produto produto, Set<Categoria> categorias) {

		this(produto);
		categorias.forEach(categoria -> this.categorias.add(new CategoriaDto(categoria)));
	}

}
