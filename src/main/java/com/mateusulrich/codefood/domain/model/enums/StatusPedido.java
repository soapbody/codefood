package com.mateusulrich.codefood.domain.model.enums;

public enum StatusPedido {

	CRIADO("Criado"),
	CONFIRMADO("Confirmado"),
	ENTREGUE("Entregue"),
	CANCELADO("Cancelado");

	private String descricao;

	private StatusPedido (String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao () {
		return descricao;
	}

	@Override
	public String toString () {
		return "StatusPedido{" +
				"descricao='" + descricao + '\'' +
				'}';
	}
}
