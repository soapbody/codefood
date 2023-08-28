package com.mateusulrich.codefood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {
	public PedidoNaoEncontradoException (String codigo) {
		super(String.format ("Não existe um Pedido com código %d", codigo));
	}


}
