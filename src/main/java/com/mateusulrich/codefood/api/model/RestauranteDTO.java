package com.mateusulrich.codefood.api.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class RestauranteDTO {

	private Long id;
	private String nome;
	private BigDecimal precoFrete;
	private CozinhaDTO cozinha;
}
