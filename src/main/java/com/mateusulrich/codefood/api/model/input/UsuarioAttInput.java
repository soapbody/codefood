package com.mateusulrich.codefood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioAttInput {
	@NotBlank
	private String nome;
	@Email
	private String email;
}
