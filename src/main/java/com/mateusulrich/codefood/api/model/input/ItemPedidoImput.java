package com.mateusulrich.codefood.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


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
