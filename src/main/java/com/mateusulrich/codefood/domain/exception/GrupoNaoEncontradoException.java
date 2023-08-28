package com.mateusulrich.codefood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{
	public GrupoNaoEncontradoException (String mensagem) {
		super (mensagem);
	}
	public GrupoNaoEncontradoException (Long id) {
		this(String.format ("Não existe um Grupo com código %d", id));
	}
}
