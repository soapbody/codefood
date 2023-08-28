package com.mateusulrich.codefood.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
@Getter
@Setter
public class ProdutoMinDTO {
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
