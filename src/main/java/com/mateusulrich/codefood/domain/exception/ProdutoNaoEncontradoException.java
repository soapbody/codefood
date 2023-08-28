package com.mateusulrich.codefood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
	public ProdutoNaoEncontradoException (Long id) {
		this(String.format ("Não existe um Produto com código %d", id));
	}

	public ProdutoNaoEncontradoException (String mensagem) {
		super (mensagem);
	}
}
