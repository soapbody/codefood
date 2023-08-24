package com.mateusulrich.codefood.api.model;

import com.mateusulrich.codefood.api.model.input.EstadoID;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class EstadoDTO {
	private EstadoID estadoID;
	@NotBlank
	@Column (nullable = false)
	private String nome;
}
