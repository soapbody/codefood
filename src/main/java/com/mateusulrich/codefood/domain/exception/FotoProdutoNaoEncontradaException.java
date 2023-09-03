package com.mateusulrich.codefood.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {
	public FotoProdutoNaoEncontradaException (String mensagem) {
		super (mensagem);
	}

	public FotoProdutoNaoEncontradaException (Long restauranteId, Long produtoId) {
		this(String.format("Não existe uma Foto do Produto %d, do Restaurante %d", produtoId, restauranteId));
	}
}
