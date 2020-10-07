package com.devsuperior.dscatalog.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "tb_categoria")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Getter
	@Setter
	@Column(name = "DESCRICAO")
	private String descricao;

	@Getter
	@Column(name = "HORARIO_CRIADO", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant horarioCriado;

	@Getter
	@Column(name = "HORARIO_ATUALIZADO", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant horarioAtualizado;

	/*************************************
	 * CONSTRUTORES
	 ************************************/

	public Categoria() {
	}

	public Categoria(Long id, String descricao) {

		this.id = id;
		this.descricao = descricao;
	}

	/*************************************
	 * METODOS ACESSORES
	 ************************************/

	@PrePersist
	public void prePersist() {

		this.horarioCriado = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {

		this.horarioAtualizado = Instant.now();
	}

}
