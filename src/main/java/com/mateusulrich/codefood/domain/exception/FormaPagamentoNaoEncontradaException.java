package com.mateusulrich.codefood.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{
	public FormaPagamentoNaoEncontradaException (String mensagem) {
		super (mensagem);
	}

	public FormaPagamentoNaoEncontradaException (Long id) {
		this(String.format("Não existe uma Forma de pagamento com código %d", id));
	}
}
