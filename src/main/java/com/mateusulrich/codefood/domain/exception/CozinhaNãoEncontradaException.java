package com.mateusulrich.codefood.domain.exception;

public class CozinhaNãoEncontradaException extends EntidadeNaoEncontradaException{
	public CozinhaNãoEncontradaException (String mensagem) {
		super (mensagem);
	}

	public CozinhaNãoEncontradaException (Long cozinhaId) {
		this(String.format ("Não existe um cadastro de cozinha com código %d", cozinhaId));
	}
}
