package com.mateusulrich.codefood.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PedidoInput {

	@Valid
	@NotNull
	RestauranteIdInput restaurante;
	@Valid
	@NotNull
	EnderecoInput enderecoEntrega;
	@Valid
	@NotNull
	private FormaPagamentoIdInput formaPagamento;
	@Valid
	@Size (min = 1)
	@NotNull
	private List<ItemPedidoImput> itens;
}
