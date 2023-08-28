package com.mateusulrich.codefood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mateusulrich.codefood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class RestauranteDTO {
	@JsonView ({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private Long id;
	@JsonView ({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;
	private Boolean ativo;
	private Boolean aberto;
	@JsonView (RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	@JsonView (RestauranteView.Resumo.class)
	private CozinhaDTO cozinha;
	private EnderecoDTO endereco;
}
