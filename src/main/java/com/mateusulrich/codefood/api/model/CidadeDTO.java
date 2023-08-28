package com.mateusulrich.codefood.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class CidadeDTO {
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	private EstadoDTO estado;
}
