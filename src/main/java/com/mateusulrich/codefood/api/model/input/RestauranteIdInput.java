package com.mateusulrich.codefood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class RestauranteIdInput {
	@NotNull
	private Long id;
}
