package com.mateusulrich.codefood.domain.model.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {

	CRIADO("Criado"),
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO),
	CANCELADO("Cancelado", CRIADO, CONFIRMADO);

	private String descricao;
	private List<StatusPedido> statusAnteriores;

	 StatusPedido (String descricao, StatusPedido... statusAnteriores) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList (statusAnteriores);
	}
	public boolean naoPodeAlterarPara(StatusPedido novoStatus) {
		 return !novoStatus.statusAnteriores.contains (this);
	}

	public String getDescricao () {
		return descricao;
	}

	@Override
	public String toString () {
		return descricao;
	}
}
