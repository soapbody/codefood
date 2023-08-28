package com.mateusulrich.codefood.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
	@Size(min = 1)
	@NotNull
	private List<ItemPedidoImput> itens;
}
