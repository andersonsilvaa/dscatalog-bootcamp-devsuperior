package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.devsuperior.dscatalog.dto.ProdutoDto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(name = "NOME")
	private String nome;

	@Getter
	@Setter
	@Column(name = "DESCRICAO", columnDefinition = "TEXT")
	private String descricao;

	@Getter
	@Setter
	@Column(name = "VALOR")
	private BigDecimal valor;

	@Getter
	@Setter
	@Column(name = "URL_IMAGEM")
	private String urlImagem;

	@Getter
	@Setter
	@Column(name = "DATA", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant data;

	@ManyToMany
	@JoinTable(name = "tb_produto_categoria", 
		joinColumns = @JoinColumn(name = "produto_id"), 
		inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categorias = new HashSet<Categoria>();
	
	/*************************************
	 * CONSTRUTORES
	 ************************************/
	
	public Produto(ProdutoDto dto) {
		
		new ModelMapper().map(dto, this);
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/
	
	public Set<Categoria> getCategorias() {
		return Collections.unmodifiableSet(categorias);
	}
	
	public void addCategoria(Categoria categoria) {
		
		if (categoria != null) {

			this.categorias.add(categoria);
		}
	}

}
