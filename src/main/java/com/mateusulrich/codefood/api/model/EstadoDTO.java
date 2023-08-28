package com.mateusulrich.codefood.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
public class EstadoDTO {
	private Long id;
	@NotBlank
	@Column (nullable = false)
	private String nome;
}
