package com.mateusulrich.codefood.api.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO {
	private Long id;
	@NotBlank
	@Column (nullable = false)
	private String nome;
}
