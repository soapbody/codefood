package com.mateusulrich.codefood.domain.exception;

public class ItemPedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
	public ItemPedidoNaoEncontradoException (Long id) {
		this(String.format ("Não existe o Item de pedido com código %d", id));
	}

	public ItemPedidoNaoEncontradoException (String mensagem) {
		super (mensagem);
	}
}
