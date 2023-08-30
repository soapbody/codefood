package com.mateusulrich.codefood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioSenhaAttInput {
	@NotBlank
	private String senhaAtual;
	@NotBlank
	private String senhaNova;
}
