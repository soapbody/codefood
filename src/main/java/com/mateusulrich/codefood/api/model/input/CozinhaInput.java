package com.mateusulrich.codefood.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CozinhaInput {
	@NotBlank
	private String nome;
}
