package com.devsuperior.dscatalog.dto;

import java.io.Serializable;

import com.devsuperior.dscatalog.entities.Categoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;

	/***************************************
	 ** CONSTRUTORES
	 **************************************/

	public CategoriaDto(Categoria categoria) {

		this.setId(categoria.getId());
		this.setDescricao(categoria.getDescricao());
	}
}
