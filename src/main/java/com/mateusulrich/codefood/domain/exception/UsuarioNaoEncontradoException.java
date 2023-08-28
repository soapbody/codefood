package com.mateusulrich.codefood.domain.exception;
public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	public UsuarioNaoEncontradoException (String mensagem) {
		super (mensagem);
	}

	public UsuarioNaoEncontradoException (Long id) {
		this(String.format ("Não existe um Usuário com código %d", id));
	}
}
