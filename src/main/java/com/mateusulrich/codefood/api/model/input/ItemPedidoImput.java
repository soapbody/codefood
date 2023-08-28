package com.mateusulrich.codefood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class ItemPedidoImput {
	@NotNull
	private Long produtoId;
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	@NotBlank
	private String observacao;
}
