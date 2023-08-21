package com.mateusulrich.codefood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Entity
public class Permissao {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id", nullable = false)
	private Long id;

	@Column (nullable = false)
	private String nome;

	@Column(nullable = false)
	private String descricao;

}
