package com.mateusulrich.codefood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class RestauranteDTO {

	private Long id;
	private String nome;
	private Boolean ativo;
	private Boolean aberto;
	private BigDecimal taxaFrete;
	private CozinhaDTO cozinha;
	private EnderecoDTO endereco;
}
