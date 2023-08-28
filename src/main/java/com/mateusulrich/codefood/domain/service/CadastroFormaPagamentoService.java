package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.EntidadeEmUsoException;
import com.mateusulrich.codefood.domain.exception.FormaPagamentoNaoEncontradaException;
import com.mateusulrich.codefood.domain.model.FormaPagamento;
import com.mateusulrich.codefood.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroFormaPagamentoService {
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	public FormaPagamento buscarFormaPagamento(Long id) {
		return formaPagamentoRepository.findById (id).orElseThrow (()-> new FormaPagamentoNaoEncontradaException (id));
	}
	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save (formaPagamento);
	}
	@Transactional
	public void excluir(Long id) {
		try {
			formaPagamentoRepository.deleteById (id);
			formaPagamentoRepository.flush ();


		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException (id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException (
					String.format("A forma de pagamento de código %d não pode ser removida, pois está em uso", id));
		}
	}
}
