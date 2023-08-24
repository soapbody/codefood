package com.mateusulrich.codefood.domain.service;

import com.mateusulrich.codefood.domain.exception.EntidadeEmUsoException;
import com.mateusulrich.codefood.domain.exception.EstadoNaoEncontradoException;
import com.mateusulrich.codefood.domain.model.Estado;
import com.mateusulrich.codefood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado buscarEstado(Long estadoId) {
		return estadoRepository.findById (estadoId).orElseThrow (()-> new EstadoNaoEncontradoException (estadoId));
	}
	@Transactional
	public Estado salvar(Estado estado) {
		return estadoRepository.save (estado);
	}
	@Transactional
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById (estadoId);
			estadoRepository.flush ();


		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException (
					String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
		}
	}

}
