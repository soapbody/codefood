package com.mateusulrich.codefood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EstadoID {
	@NotNull
	private Long id;
}
