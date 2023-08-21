package com.mateusulrich.codefood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{
	public CidadeNaoEncontradaException (String mensagem) {
		super (mensagem);
	}
	public CidadeNaoEncontradaException (Long cidadeId) {
		this(String.format("Não existe um cadastro de cidade com código %d", cidadeId));
	}
}
