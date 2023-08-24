package com.mateusulrich.codefood.api.model;

import com.mateusulrich.codefood.api.model.input.EstadoID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CidadeDTO {
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	private EstadoID estadoID;
}
