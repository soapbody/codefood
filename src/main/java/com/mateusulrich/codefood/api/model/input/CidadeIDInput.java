package com.mateusulrich.codefood.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeIDInput {
	@NotNull
	private Long id;
}
