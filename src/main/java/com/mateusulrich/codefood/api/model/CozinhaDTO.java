package com.mateusulrich.codefood.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mateusulrich.codefood.api.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaDTO {
	@JsonView (RestauranteView.Resumo.class)
	private Long id;
	@JsonView (RestauranteView.Resumo.class)
	private String nome;
}
