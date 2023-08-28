package com.mateusulrich.codefood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException{
	public PermissaoNaoEncontradaException (String mensagem) {
		super (mensagem);
	}
	public PermissaoNaoEncontradaException (Long id) {
		this(String.format ("Não existe uma Permissao com código %d", id));
	}
}
