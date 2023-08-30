package com.mateusulrich.codefood.api.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@NotNull
	@PositiveOrZero
	private BigDecimal preco;
	@NotNull
	private Boolean ativo;

}
